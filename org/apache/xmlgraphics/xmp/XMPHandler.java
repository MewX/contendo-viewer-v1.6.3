/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Stack;
/*     */ import org.apache.xmlgraphics.util.QName;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.DefaultHandler;
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
/*     */ public class XMPHandler
/*     */   extends DefaultHandler
/*     */ {
/*     */   private Metadata meta;
/*  42 */   private StringBuffer content = new StringBuffer();
/*  43 */   private Stack attributesStack = new Stack();
/*  44 */   private Stack nestingInfoStack = new Stack();
/*  45 */   private Stack contextStack = new Stack();
/*     */ 
/*     */   
/*     */   public Metadata getMetadata() {
/*  49 */     return this.meta;
/*     */   }
/*     */   
/*     */   private boolean hasComplexContent() {
/*  53 */     Object obj = this.contextStack.peek();
/*  54 */     return !(obj instanceof QName);
/*     */   }
/*     */   
/*     */   private PropertyAccess getCurrentProperties() {
/*  58 */     Object obj = this.contextStack.peek();
/*  59 */     if (obj instanceof PropertyAccess) {
/*  60 */       return (PropertyAccess)obj;
/*     */     }
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private QName getCurrentPropName() {
/*  67 */     Object obj = this.contextStack.peek();
/*  68 */     if (obj instanceof QName) {
/*  69 */       return (QName)obj;
/*     */     }
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private QName popCurrentPropName() throws SAXException {
/*  76 */     Object obj = this.contextStack.pop();
/*  77 */     this.nestingInfoStack.pop();
/*  78 */     if (obj instanceof QName) {
/*  79 */       return (QName)obj;
/*     */     }
/*  81 */     throw new SAXException("Invalid XMP structure. Property name expected");
/*     */   }
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
/*     */   private XMPStructure getCurrentStructure() {
/*  95 */     Object obj = this.contextStack.peek();
/*  96 */     if (obj instanceof XMPStructure) {
/*  97 */       return (XMPStructure)obj;
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private XMPArray getCurrentArray(boolean required) throws SAXException {
/* 104 */     Object obj = this.contextStack.peek();
/* 105 */     if (obj instanceof XMPArray) {
/* 106 */       return (XMPArray)obj;
/*     */     }
/* 108 */     if (required) {
/* 109 */       throw new SAXException("Invalid XMP structure. Not in array");
/*     */     }
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/* 121 */     super.startElement(uri, localName, qName, attributes);
/* 122 */     this.content.setLength(0);
/* 123 */     this.attributesStack.push(new AttributesImpl(attributes));
/*     */     
/* 125 */     if ("adobe:ns:meta/".equals(uri))
/* 126 */     { if (!"xmpmeta".equals(localName)) {
/* 127 */         throw new SAXException("Expected x:xmpmeta element, not " + qName);
/*     */       }
/* 129 */       if (this.meta != null) {
/* 130 */         throw new SAXException("Invalid XMP document. Root already received earlier.");
/*     */       }
/* 132 */       this.meta = new Metadata();
/* 133 */       this.contextStack.push(this.meta);
/* 134 */       this.nestingInfoStack.push("metadata"); }
/* 135 */     else if ("http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(uri))
/* 136 */     { if ("RDF".equals(localName)) {
/* 137 */         if (this.meta == null) {
/* 138 */           this.meta = new Metadata();
/* 139 */           this.contextStack.push(this.meta);
/* 140 */           this.nestingInfoStack.push("metadata");
/*     */         } 
/* 142 */       } else if ("Description".equals(localName)) {
/* 143 */         String about = attributes.getValue("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "about");
/* 144 */         for (int i = 0, c = attributes.getLength(); i < c; i++) {
/* 145 */           String ns = attributes.getURI(i);
/* 146 */           if (!"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(ns))
/*     */           {
/* 148 */             if (!"http://www.w3.org/2000/xmlns/".equals(ns))
/*     */             {
/* 150 */               if (!"".equals(ns)) {
/*     */ 
/*     */                 
/* 153 */                 String qn = attributes.getQName(i);
/* 154 */                 String v = attributes.getValue(i);
/* 155 */                 XMPProperty prop = new XMPProperty(new QName(ns, qn), v);
/* 156 */                 getCurrentProperties().setProperty(prop);
/*     */               }  }  } 
/*     */         } 
/* 159 */         if (!this.contextStack.peek().equals(this.meta)) {
/*     */ 
/*     */           
/* 162 */           if (about != null) {
/* 163 */             throw new SAXException("Nested rdf:Description elements may not have an about property");
/*     */           }
/*     */           
/* 166 */           startStructure();
/*     */         } 
/* 168 */       } else if ("Seq".equals(localName)) {
/* 169 */         XMPArray array = new XMPArray(XMPArrayType.SEQ);
/* 170 */         this.contextStack.push(array);
/* 171 */         this.nestingInfoStack.push("Seq");
/* 172 */       } else if ("Bag".equals(localName)) {
/* 173 */         XMPArray array = new XMPArray(XMPArrayType.BAG);
/* 174 */         this.contextStack.push(array);
/* 175 */         this.nestingInfoStack.push("Bag");
/* 176 */       } else if ("Alt".equals(localName)) {
/* 177 */         XMPArray array = new XMPArray(XMPArrayType.ALT);
/* 178 */         this.contextStack.push(array);
/* 179 */         this.nestingInfoStack.push("Alt");
/* 180 */       } else if (!"li".equals(localName)) {
/*     */         
/* 182 */         if ("value".equals(localName)) {
/* 183 */           QName name = new QName(uri, qName);
/* 184 */           this.contextStack.push(name);
/* 185 */           this.nestingInfoStack.push("prop:" + name);
/*     */         } else {
/* 187 */           throw new SAXException("Unexpected element in the RDF namespace: " + localName);
/*     */         } 
/*     */       }  }
/* 190 */     else { if (getCurrentPropName() != null)
/*     */       {
/* 192 */         startStructure();
/*     */       }
/* 194 */       QName name = new QName(uri, qName);
/* 195 */       this.contextStack.push(name);
/* 196 */       this.nestingInfoStack.push("prop:" + name); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private void startStructure() {
/* 202 */     XMPStructure struct = new XMPStructure();
/* 203 */     this.contextStack.push(struct);
/* 204 */     this.nestingInfoStack.push("struct");
/*     */   }
/*     */ 
/*     */   
/*     */   public void endElement(String uri, String localName, String qName) throws SAXException {
/* 209 */     Attributes atts = this.attributesStack.pop();
/* 210 */     if (!"adobe:ns:meta/".equals(uri))
/*     */     {
/* 212 */       if ("http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(uri) && !"value".equals(localName)) {
/* 213 */         if ("li".equals(localName)) {
/* 214 */           XMPStructure struct = getCurrentStructure();
/* 215 */           if (struct != null) {
/*     */             
/* 217 */             this.contextStack.pop();
/* 218 */             this.nestingInfoStack.pop();
/* 219 */             getCurrentArray(true).add(struct);
/*     */           } else {
/* 221 */             String s = this.content.toString().trim();
/* 222 */             if (s.length() > 0) {
/* 223 */               String lang = atts.getValue("http://www.w3.org/XML/1998/namespace", "lang");
/* 224 */               if (lang != null) {
/* 225 */                 getCurrentArray(true).add(s, lang);
/*     */               } else {
/* 227 */                 getCurrentArray(true).add(s);
/*     */               } 
/*     */             } else {
/* 230 */               String res = atts.getValue("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "resource");
/*     */               
/* 232 */               if (res != null) {
/*     */                 try {
/* 234 */                   URI resource = new URI(res);
/* 235 */                   getCurrentArray(true).add(resource);
/* 236 */                 } catch (URISyntaxException e) {
/* 237 */                   throw new SAXException("rdf:resource value is not a well-formed URI", e);
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/* 242 */         } else if ("Description".equals(localName)) {
/*     */         
/*     */         } 
/*     */       } else {
/*     */         XMPProperty prop;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 255 */         if (hasComplexContent()) {
/*     */           
/* 257 */           Object obj = this.contextStack.pop();
/* 258 */           this.nestingInfoStack.pop();
/*     */           
/* 260 */           QName name = popCurrentPropName();
/*     */           
/* 262 */           if (obj instanceof XMPComplexValue) {
/* 263 */             XMPComplexValue complexValue = (XMPComplexValue)obj;
/* 264 */             prop = new XMPProperty(name, complexValue);
/*     */           } else {
/* 266 */             throw new UnsupportedOperationException("NYI");
/*     */           } 
/*     */         } else {
/* 269 */           QName name = popCurrentPropName();
/*     */           
/* 271 */           String s = this.content.toString().trim();
/* 272 */           prop = new XMPProperty(name, s);
/* 273 */           String lang = atts.getValue("http://www.w3.org/XML/1998/namespace", "lang");
/* 274 */           String res = atts.getValue("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "resource");
/* 275 */           if (lang != null) {
/* 276 */             prop.setXMLLang(lang);
/*     */           }
/* 278 */           if (res != null) {
/*     */             try {
/* 280 */               URI resource = new URI(res);
/* 281 */               prop.setValue(resource);
/* 282 */             } catch (URISyntaxException e) {
/* 283 */               throw new SAXException("rdf:resource value is not a well-formed URI", e);
/*     */             } 
/*     */           }
/*     */         } 
/* 287 */         if (prop.getName() == null) {
/* 288 */           throw new IllegalStateException("No content in XMP property");
/*     */         }
/* 290 */         assert getCurrentProperties() != null : "no current property";
/* 291 */         getCurrentProperties().setProperty(prop);
/*     */       } 
/*     */     }
/* 294 */     this.content.setLength(0);
/* 295 */     super.endElement(uri, localName, qName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 306 */     this.content.append(ch, start, length);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */