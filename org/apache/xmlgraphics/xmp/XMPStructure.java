/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.util.QName;
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
/*     */ public class XMPStructure
/*     */   extends XMPComplexValue
/*     */   implements PropertyAccess
/*     */ {
/*  36 */   private Map properties = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getSimpleValue() {
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProperty(XMPProperty prop) {
/*  51 */     this.properties.put(prop.getName(), prop);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty getProperty(String uri, String localName) {
/*  56 */     return getProperty(new QName(uri, localName));
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty getValueProperty() {
/*  61 */     return getProperty(XMPConstants.RDF_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty getProperty(QName name) {
/*  66 */     XMPProperty prop = (XMPProperty)this.properties.get(name);
/*  67 */     return prop;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty removeProperty(QName name) {
/*  72 */     return (XMPProperty)this.properties.remove(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPropertyCount() {
/*  77 */     return this.properties.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  82 */     return this.properties.keySet().iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public void toSAX(ContentHandler handler) throws SAXException {
/*  87 */     AttributesImpl atts = new AttributesImpl();
/*  88 */     atts.clear();
/*  89 */     handler.startElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "RDF", "rdf:Description", atts);
/*     */     
/*  91 */     Iterator<XMPProperty> props = this.properties.values().iterator();
/*  92 */     while (props.hasNext()) {
/*  93 */       XMPProperty prop = props.next();
/*     */       
/*  95 */       prop.toSAX(handler);
/*     */     } 
/*     */     
/*  98 */     handler.endElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "RDF", "rdf:Description");
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     return "XMP structure: " + getPropertyCount();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */