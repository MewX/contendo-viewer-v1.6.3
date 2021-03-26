/*     */ package org.cyberneko.html.filters;
/*     */ 
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLDocumentHandler;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XMLResourceIdentifier;
/*     */ import org.apache.xerces.xni.XMLString;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLComponentManager;
/*     */ import org.apache.xerces.xni.parser.XMLConfigurationException;
/*     */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*     */ import org.apache.xerces.xni.parser.XMLDocumentSource;
/*     */ import org.cyberneko.html.b;
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
/*     */ public class a
/*     */   implements XMLDocumentFilter, b
/*     */ {
/*     */   protected XMLDocumentHandler d;
/*     */   protected XMLDocumentSource e;
/*     */   
/*     */   public void setDocumentHandler(XMLDocumentHandler handler) {
/*  63 */     this.d = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLDocumentHandler getDocumentHandler() {
/*  70 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDocumentSource(XMLDocumentSource source) {
/*  75 */     this.e = source;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLDocumentSource getDocumentSource() {
/*  80 */     return this.e;
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
/*     */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) throws XNIException {
/*  93 */     if (this.d != null) {
/*  94 */       org.cyberneko.html.b.a.a().a(this.d, locator, encoding, nscontext, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
/* 103 */     if (this.d != null) {
/* 104 */       this.d.xmlDecl(version, encoding, standalone, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doctypeDecl(String root, String publicId, String systemId, Augmentations augs) throws XNIException {
/* 111 */     if (this.d != null) {
/* 112 */       this.d.doctypeDecl(root, publicId, systemId, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/* 119 */     if (this.d != null) {
/* 120 */       this.d.comment(text, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
/* 127 */     if (this.d != null) {
/* 128 */       this.d.processingInstruction(target, data, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/* 135 */     if (this.d != null) {
/* 136 */       this.d.startElement(element, attributes, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/* 143 */     if (this.d != null) {
/* 144 */       this.d.emptyElement(element, attributes, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(XMLString text, Augmentations augs) throws XNIException {
/* 151 */     if (this.d != null) {
/* 152 */       this.d.characters(text, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
/* 159 */     if (this.d != null) {
/* 160 */       this.d.ignorableWhitespace(text, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startGeneralEntity(String name, XMLResourceIdentifier id, String encoding, Augmentations augs) throws XNIException {
/* 167 */     if (this.d != null) {
/* 168 */       this.d.startGeneralEntity(name, id, encoding, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
/* 175 */     if (this.d != null) {
/* 176 */       this.d.textDecl(version, encoding, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
/* 183 */     if (this.d != null) {
/* 184 */       this.d.endGeneralEntity(name, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void startCDATA(Augmentations augs) throws XNIException {
/* 190 */     if (this.d != null) {
/* 191 */       this.d.startCDATA(augs);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void endCDATA(Augmentations augs) throws XNIException {
/* 197 */     if (this.d != null) {
/* 198 */       this.d.endCDATA(augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(QName element, Augmentations augs) throws XNIException {
/* 205 */     if (this.d != null) {
/* 206 */       this.d.endElement(element, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void endDocument(Augmentations augs) throws XNIException {
/* 212 */     if (this.d != null) {
/* 213 */       this.d.endDocument(augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(XMLLocator locator, String encoding, Augmentations augs) throws XNIException {
/* 222 */     startDocument(locator, encoding, null, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String prefix, String uri, Augmentations augs) throws XNIException {
/* 228 */     if (this.d != null) {
/* 229 */       org.cyberneko.html.b.a.a().a(this.d, prefix, uri, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String prefix, Augmentations augs) throws XNIException {
/* 236 */     if (this.d != null) {
/* 237 */       org.cyberneko.html.b.a.a().a(this.d, prefix, augs);
/*     */     }
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
/*     */   public String[] getRecognizedFeatures() {
/* 251 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getFeatureDefault(String featureId) {
/* 260 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getRecognizedProperties() {
/* 269 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getPropertyDefault(String propertyId) {
/* 278 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {}
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
/*     */   public void setFeature(String featureId, boolean state) throws XMLConfigurationException {}
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
/*     */   public void setProperty(String propertyId, Object value) throws XMLConfigurationException {}
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
/*     */   protected static String[] a(String[] array1, String[] array2) {
/* 345 */     if (array1 == array2) {
/* 346 */       return array1;
/*     */     }
/* 348 */     if (array1 == null) {
/* 349 */       return array2;
/*     */     }
/* 351 */     if (array2 == null) {
/* 352 */       return array1;
/*     */     }
/*     */ 
/*     */     
/* 356 */     String[] array3 = new String[array1.length + array2.length];
/* 357 */     System.arraycopy(array1, 0, array3, 0, array1.length);
/* 358 */     System.arraycopy(array2, 0, array3, array1.length, array2.length);
/*     */     
/* 360 */     return array3;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/filters/a.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */