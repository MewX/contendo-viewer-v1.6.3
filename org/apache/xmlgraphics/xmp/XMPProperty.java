/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.util.QName;
/*     */ import org.apache.xmlgraphics.util.XMLizable;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMPProperty
/*     */   implements XMLizable
/*     */ {
/*     */   private QName name;
/*     */   private Object value;
/*     */   private String xmllang;
/*     */   private Map qualifiers;
/*     */   
/*     */   public XMPProperty(QName name, Object value) {
/*  50 */     this.name = name;
/*  51 */     this.value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public QName getName() {
/*  56 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNamespace() {
/*  61 */     return getName().getNamespaceURI();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/*  69 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*  77 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLLang(String lang) {
/*  86 */     this.xmllang = lang;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLLang() {
/*  93 */     return this.xmllang;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isArray() {
/* 101 */     return this.value instanceof XMPArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPArray getArrayValue() {
/* 106 */     return isArray() ? (XMPArray)this.value : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMPArray convertSimpleValueToArray(XMPArrayType type) {
/* 115 */     if (getArrayValue() == null) {
/* 116 */       XMPArray array = new XMPArray(type);
/* 117 */       if (getXMLLang() != null) {
/* 118 */         array.add(getValue().toString(), getXMLLang());
/*     */       } else {
/* 120 */         array.add(getValue());
/*     */       } 
/* 122 */       setValue(array);
/* 123 */       setXMLLang(null);
/* 124 */       return array;
/*     */     } 
/* 126 */     return getArrayValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PropertyAccess getStructureValue() {
/* 132 */     return (this.value instanceof XMPStructure) ? (XMPStructure)this.value : null;
/*     */   }
/*     */   
/*     */   private boolean hasPropertyQualifiers() {
/* 136 */     return (this.qualifiers == null || this.qualifiers.size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isQualifiedProperty() {
/* 146 */     PropertyAccess props = getStructureValue();
/* 147 */     if (props != null) {
/* 148 */       XMPProperty rdfValue = props.getValueProperty();
/* 149 */       return (rdfValue != null);
/*     */     } 
/* 151 */     return hasPropertyQualifiers();
/*     */   }
/*     */ 
/*     */   
/*     */   public void simplify() {
/* 156 */     PropertyAccess props = getStructureValue();
/* 157 */     if (props != null) {
/* 158 */       XMPProperty rdfValue = props.getValueProperty();
/* 159 */       if (rdfValue != null) {
/* 160 */         if (hasPropertyQualifiers()) {
/* 161 */           throw new IllegalStateException("Illegal internal state (qualifiers present on non-simplified property)");
/*     */         }
/*     */         
/* 164 */         XMPProperty prop = new XMPProperty(getName(), rdfValue);
/* 165 */         Iterator<QName> iter = props.iterator();
/* 166 */         while (iter.hasNext()) {
/* 167 */           QName name = iter.next();
/* 168 */           if (!XMPConstants.RDF_VALUE.equals(name)) {
/* 169 */             prop.setPropertyQualifier(name, props.getProperty(name));
/*     */           }
/*     */         } 
/* 172 */         props.setProperty(prop);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPropertyQualifier(QName name, XMPProperty property) {
/* 179 */     if (this.qualifiers == null) {
/* 180 */       this.qualifiers = new HashMap<Object, Object>();
/*     */     }
/* 182 */     this.qualifiers.put(name, property);
/*     */   }
/*     */   
/*     */   private String getEffectiveQName() {
/* 186 */     String prefix = getName().getPrefix();
/* 187 */     if (prefix == null || "".equals(prefix)) {
/* 188 */       XMPSchema schema = XMPSchemaRegistry.getInstance().getSchema(getNamespace());
/* 189 */       prefix = schema.getPreferredPrefix();
/*     */     } 
/* 191 */     return prefix + ":" + getName().getLocalName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void toSAX(ContentHandler handler) throws SAXException {
/* 196 */     AttributesImpl atts = new AttributesImpl();
/* 197 */     String qName = getEffectiveQName();
/* 198 */     if (this.value instanceof URI) {
/* 199 */       atts.addAttribute("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "resource", "rdf:resource", "CDATA", ((URI)this.value).toString());
/*     */     }
/* 201 */     handler.startElement(getName().getNamespaceURI(), getName().getLocalName(), qName, atts);
/*     */     
/* 203 */     if (this.value instanceof XMPComplexValue) {
/* 204 */       XMPComplexValue cv = (XMPComplexValue)this.value;
/* 205 */       cv.toSAX(handler);
/* 206 */     } else if (!(this.value instanceof URI)) {
/* 207 */       char[] chars = this.value.toString().toCharArray();
/* 208 */       handler.characters(chars, 0, chars.length);
/*     */     } 
/* 210 */     handler.endElement(getName().getNamespaceURI(), getName().getLocalName(), qName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     StringBuffer sb = new StringBuffer("XMP Property ");
/* 217 */     sb.append(getName()).append(": ");
/* 218 */     sb.append(getValue());
/* 219 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPProperty.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */