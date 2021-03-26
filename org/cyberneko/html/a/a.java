/*     */ package org.cyberneko.html.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import org.apache.xerces.util.ErrorHandlerWrapper;
/*     */ import org.apache.xerces.util.XMLChar;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLDocumentHandler;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XMLResourceIdentifier;
/*     */ import org.apache.xerces.xni.XMLString;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLConfigurationException;
/*     */ import org.apache.xerces.xni.parser.XMLDocumentSource;
/*     */ import org.apache.xerces.xni.parser.XMLErrorHandler;
/*     */ import org.apache.xerces.xni.parser.XMLInputSource;
/*     */ import org.apache.xerces.xni.parser.XMLParseException;
/*     */ import org.apache.xerces.xni.parser.XMLParserConfiguration;
/*     */ import org.cyberneko.html.c;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.Text;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.SAXParseException;
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
/*     */   implements XMLDocumentHandler
/*     */ {
/*     */   protected static final String a = "http://cyberneko.org/html/features/document-fragment";
/*  82 */   protected static final String[] b = new String[] { "http://cyberneko.org/html/features/document-fragment" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String c = "http://apache.org/xml/properties/internal/error-handler";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String d = "http://apache.org/xml/properties/dom/current-element-node";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected static final String[] e = new String[] { "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/dom/current-element-node" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLParserConfiguration f;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLDocumentSource g;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DocumentFragment h;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Document i;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node j;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean k;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a() {
/* 130 */     this.f = (XMLParserConfiguration)new c();
/* 131 */     this.f.addRecognizedFeatures(b);
/* 132 */     this.f.addRecognizedProperties(e);
/* 133 */     this.f.setFeature("http://cyberneko.org/html/features/document-fragment", true);
/* 134 */     this.f.setDocumentHandler(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String systemId, DocumentFragment fragment) throws SAXException, IOException {
/* 144 */     a(new InputSource(systemId), fragment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(InputSource source, DocumentFragment fragment) throws SAXException, IOException {
/* 151 */     this.j = this.h = fragment;
/* 152 */     this.i = this.h.getOwnerDocument();
/*     */     
/*     */     try {
/* 155 */       String pubid = source.getPublicId();
/* 156 */       String sysid = source.getSystemId();
/* 157 */       String encoding = source.getEncoding();
/* 158 */       InputStream stream = source.getByteStream();
/* 159 */       Reader reader = source.getCharacterStream();
/*     */       
/* 161 */       XMLInputSource inputSource = new XMLInputSource(pubid, sysid, sysid);
/*     */       
/* 163 */       inputSource.setEncoding(encoding);
/* 164 */       inputSource.setByteStream(stream);
/* 165 */       inputSource.setCharacterStream(reader);
/*     */       
/* 167 */       this.f.parse(inputSource);
/*     */     }
/* 169 */     catch (XMLParseException e) {
/* 170 */       Exception ex = e.getException();
/* 171 */       if (ex != null) {
/* 172 */         throw new SAXParseException(e.getMessage(), null, ex);
/*     */       }
/* 174 */       throw new SAXParseException(e.getMessage(), null);
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
/*     */   public void a(ErrorHandler errorHandler) {
/* 198 */     this.f.setErrorHandler((XMLErrorHandler)new ErrorHandlerWrapper(errorHandler));
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
/*     */   public ErrorHandler a() {
/* 210 */     ErrorHandler errorHandler = null;
/*     */     try {
/* 212 */       XMLErrorHandler xmlErrorHandler = (XMLErrorHandler)this.f.getProperty("http://apache.org/xml/properties/internal/error-handler");
/*     */       
/* 214 */       if (xmlErrorHandler != null && xmlErrorHandler instanceof ErrorHandlerWrapper)
/*     */       {
/* 216 */         errorHandler = ((ErrorHandlerWrapper)xmlErrorHandler).getErrorHandler();
/*     */       }
/*     */     }
/* 219 */     catch (XMLConfigurationException xMLConfigurationException) {}
/*     */ 
/*     */     
/* 222 */     return errorHandler;
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
/*     */ 
/*     */   
/*     */   public void a(String featureId, boolean state) throws SAXNotRecognizedException, SAXNotSupportedException {
/*     */     try {
/* 244 */       this.f.setFeature(featureId, state);
/*     */     }
/* 246 */     catch (XMLConfigurationException e) {
/* 247 */       String message = e.getMessage();
/* 248 */       if (e.getType() == 0) {
/* 249 */         throw new SAXNotRecognizedException(message);
/*     */       }
/* 251 */       throw new SAXNotSupportedException(message);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(String featureId) throws SAXNotRecognizedException, SAXNotSupportedException {
/*     */     try {
/* 274 */       return this.f.getFeature(featureId);
/*     */     }
/* 276 */     catch (XMLConfigurationException e) {
/* 277 */       String message = e.getMessage();
/* 278 */       if (e.getType() == 0) {
/* 279 */         throw new SAXNotRecognizedException(message);
/*     */       }
/* 281 */       throw new SAXNotSupportedException(message);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String propertyId, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
/*     */     try {
/* 305 */       this.f.setProperty(propertyId, value);
/*     */     }
/* 307 */     catch (XMLConfigurationException e) {
/* 308 */       String message = e.getMessage();
/* 309 */       if (e.getType() == 0) {
/* 310 */         throw new SAXNotRecognizedException(message);
/*     */       }
/* 312 */       throw new SAXNotSupportedException(message);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object b(String propertyId) throws SAXNotRecognizedException, SAXNotSupportedException {
/* 334 */     if (propertyId.equals("http://apache.org/xml/properties/dom/current-element-node")) {
/* 335 */       return (this.j != null && this.j.getNodeType() == 1) ? this.j : null;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 340 */       return this.f.getProperty(propertyId);
/*     */     }
/* 342 */     catch (XMLConfigurationException e) {
/* 343 */       String message = e.getMessage();
/* 344 */       if (e.getType() == 0) {
/* 345 */         throw new SAXNotRecognizedException(message);
/*     */       }
/* 347 */       throw new SAXNotSupportedException(message);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentSource(XMLDocumentSource source) {
/* 358 */     this.g = source;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLDocumentSource getDocumentSource() {
/* 363 */     return this.g;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(XMLLocator locator, String encoding, Augmentations augs) throws XNIException {
/* 369 */     startDocument(locator, encoding, null, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) throws XNIException {
/* 378 */     this.k = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doctypeDecl(String root, String pubid, String sysid, Augmentations augs) throws XNIException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
/* 397 */     String s = data.toString();
/* 398 */     if (XMLChar.isValidName(s)) {
/* 399 */       ProcessingInstruction pi = this.i.createProcessingInstruction(target, s);
/* 400 */       this.j.appendChild(pi);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/* 407 */     Comment comment = this.i.createComment(text.toString());
/* 408 */     this.j.appendChild(comment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String prefix, String uri, Augmentations augs) throws XNIException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String prefix, Augmentations augs) throws XNIException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/* 424 */     Element elementNode = this.i.createElement(element.rawname);
/* 425 */     int count = (attrs != null) ? attrs.getLength() : 0;
/* 426 */     for (int i = 0; i < count; i++) {
/* 427 */       String aname = attrs.getQName(i);
/* 428 */       String avalue = attrs.getValue(i);
/* 429 */       if (XMLChar.isValidName(aname)) {
/* 430 */         elementNode.setAttribute(aname, avalue);
/*     */       }
/*     */     } 
/* 433 */     this.j.appendChild(elementNode);
/* 434 */     this.j = elementNode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void emptyElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/* 440 */     startElement(element, attrs, augs);
/* 441 */     endElement(element, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(XMLString text, Augmentations augs) throws XNIException {
/* 448 */     if (this.k) {
/* 449 */       Node node = this.j.getLastChild();
/* 450 */       if (node != null && node.getNodeType() == 4) {
/* 451 */         CDATASection cdata = (CDATASection)node;
/* 452 */         cdata.appendData(text.toString());
/*     */       } else {
/*     */         
/* 455 */         CDATASection cdata = this.i.createCDATASection(text.toString());
/* 456 */         this.j.appendChild(cdata);
/*     */       } 
/*     */     } else {
/*     */       
/* 460 */       Node node = this.j.getLastChild();
/* 461 */       if (node != null && node.getNodeType() == 3) {
/* 462 */         Text textNode = (Text)node;
/* 463 */         textNode.appendData(text.toString());
/*     */       } else {
/*     */         
/* 466 */         Text textNode = this.i.createTextNode(text.toString());
/* 467 */         this.j.appendChild(textNode);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
/* 476 */     characters(text, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startGeneralEntity(String name, XMLResourceIdentifier id, String encoding, Augmentations augs) throws XNIException {
/* 483 */     EntityReference entityRef = this.i.createEntityReference(name);
/* 484 */     this.j.appendChild(entityRef);
/* 485 */     this.j = entityRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
/* 496 */     this.j = this.j.getParentNode();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startCDATA(Augmentations augs) throws XNIException {
/* 501 */     this.k = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void endCDATA(Augmentations augs) throws XNIException {
/* 506 */     this.k = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(QName element, Augmentations augs) throws XNIException {
/* 512 */     this.j = this.j.getParentNode();
/*     */   }
/*     */   
/*     */   public void endDocument(Augmentations augs) throws XNIException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/a/a.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */