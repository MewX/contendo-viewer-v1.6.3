/*     */ package org.apache.batik.dom.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.batik.util.HaltingThread;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
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
/*     */ public class SAXDocumentFactory
/*     */   extends DefaultHandler
/*     */   implements DocumentFactory, LexicalHandler
/*     */ {
/*     */   protected DOMImplementation implementation;
/*     */   protected String parserClassName;
/*     */   protected XMLReader parser;
/*     */   protected Document document;
/*     */   protected DocumentDescriptor documentDescriptor;
/*     */   protected boolean createDocumentDescriptor;
/*     */   protected Node currentNode;
/*     */   protected Locator locator;
/* 109 */   protected StringBuffer stringBuffer = new StringBuffer();
/*     */ 
/*     */ 
/*     */   
/*     */   protected DocumentType doctype;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean stringContent;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean inDTD;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean inCDATA;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean inProlog;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidating;
/*     */ 
/*     */   
/*     */   protected boolean isStandalone;
/*     */ 
/*     */   
/*     */   protected String xmlVersion;
/*     */ 
/*     */   
/*     */   protected HashTableStack namespaces;
/*     */ 
/*     */   
/*     */   protected ErrorHandler errorHandler;
/*     */ 
/*     */   
/*     */   protected List preInfo;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static interface PreInfo
/*     */   {
/*     */     Node createNode(Document param1Document);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class ProcessingInstructionInfo
/*     */     implements PreInfo
/*     */   {
/*     */     public String target;
/*     */ 
/*     */     
/*     */     public String data;
/*     */ 
/*     */ 
/*     */     
/*     */     public ProcessingInstructionInfo(String target, String data) {
/* 170 */       this.target = target;
/* 171 */       this.data = data;
/*     */     }
/*     */     public Node createNode(Document doc) {
/* 174 */       return doc.createProcessingInstruction(this.target, this.data);
/*     */     } }
/*     */   
/*     */   static class CommentInfo implements PreInfo {
/*     */     public String comment;
/*     */     
/*     */     public CommentInfo(String comment) {
/* 181 */       this.comment = comment;
/*     */     }
/*     */     public Node createNode(Document doc) {
/* 184 */       return doc.createComment(this.comment);
/*     */     }
/*     */   }
/*     */   
/*     */   static class CDataInfo implements PreInfo { public String cdata;
/*     */     
/*     */     public CDataInfo(String cdata) {
/* 191 */       this.cdata = cdata;
/*     */     }
/*     */     public Node createNode(Document doc) {
/* 194 */       return doc.createCDATASection(this.cdata);
/*     */     } }
/*     */   
/*     */   static class TextInfo implements PreInfo {
/*     */     public String text;
/*     */     
/*     */     public TextInfo(String text) {
/* 201 */       this.text = text;
/*     */     }
/*     */     public Node createNode(Document doc) {
/* 204 */       return doc.createTextNode(this.text);
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
/*     */   public SAXDocumentFactory(DOMImplementation impl, String parser) {
/* 222 */     this.implementation = impl;
/* 223 */     this.parserClassName = parser;
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
/*     */   public SAXDocumentFactory(DOMImplementation impl, String parser, boolean dd) {
/* 235 */     this.implementation = impl;
/* 236 */     this.parserClassName = parser;
/* 237 */     this.createDocumentDescriptor = dd;
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
/*     */   public Document createDocument(String ns, String root, String uri) throws IOException {
/* 249 */     return createDocument(ns, root, uri, new InputSource(uri));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document createDocument(String uri) throws IOException {
/* 259 */     return createDocument(new InputSource(uri));
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
/*     */   public Document createDocument(String ns, String root, String uri, InputStream is) throws IOException {
/* 272 */     InputSource inp = new InputSource(is);
/* 273 */     inp.setSystemId(uri);
/* 274 */     return createDocument(ns, root, uri, inp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document createDocument(String uri, InputStream is) throws IOException {
/* 285 */     InputSource inp = new InputSource(is);
/* 286 */     inp.setSystemId(uri);
/* 287 */     return createDocument(inp);
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
/*     */   public Document createDocument(String ns, String root, String uri, Reader r) throws IOException {
/* 300 */     InputSource inp = new InputSource(r);
/* 301 */     inp.setSystemId(uri);
/* 302 */     return createDocument(ns, root, uri, inp);
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
/*     */   public Document createDocument(String ns, String root, String uri, XMLReader r) throws IOException {
/* 315 */     r.setContentHandler(this);
/* 316 */     r.setDTDHandler(this);
/* 317 */     r.setEntityResolver(this);
/*     */     try {
/* 319 */       r.parse(uri);
/* 320 */     } catch (SAXException e) {
/* 321 */       Exception ex = e.getException();
/* 322 */       if (ex != null && ex instanceof InterruptedIOException) {
/* 323 */         throw (InterruptedIOException)ex;
/*     */       }
/* 325 */       throw new SAXIOException(e);
/*     */     } 
/* 327 */     this.currentNode = null;
/* 328 */     Document ret = this.document;
/* 329 */     this.document = null;
/* 330 */     this.doctype = null;
/* 331 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document createDocument(String uri, Reader r) throws IOException {
/* 341 */     InputSource inp = new InputSource(r);
/* 342 */     inp.setSystemId(uri);
/* 343 */     return createDocument(inp);
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
/*     */   protected Document createDocument(String ns, String root, String uri, InputSource is) throws IOException {
/* 357 */     Document ret = createDocument(is);
/* 358 */     Element docElem = ret.getDocumentElement();
/*     */     
/* 360 */     String lname = root;
/* 361 */     String nsURI = ns;
/* 362 */     if (ns == null) {
/* 363 */       int idx = lname.indexOf(':');
/* 364 */       String nsp = (idx == -1 || idx == lname.length() - 1) ? "" : lname.substring(0, idx);
/*     */ 
/*     */       
/* 367 */       nsURI = this.namespaces.get(nsp);
/* 368 */       if (idx != -1 && idx != lname.length() - 1) {
/* 369 */         lname = lname.substring(idx + 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 374 */     String docElemNS = docElem.getNamespaceURI();
/* 375 */     if (docElemNS != nsURI && (docElemNS == null || !docElemNS.equals(nsURI)))
/*     */     {
/* 377 */       throw new IOException("Root element namespace does not match that requested:\nRequested: " + nsURI + "\n" + "Found: " + docElemNS);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 382 */     if (docElemNS != null) {
/* 383 */       if (!docElem.getLocalName().equals(lname)) {
/* 384 */         throw new IOException("Root element does not match that requested:\nRequested: " + lname + "\n" + "Found: " + docElem.getLocalName());
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 389 */     else if (!docElem.getNodeName().equals(lname)) {
/* 390 */       throw new IOException("Root element does not match that requested:\nRequested: " + lname + "\n" + "Found: " + docElem.getNodeName());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 401 */   static SAXParserFactory saxFactory = SAXParserFactory.newInstance(); static {
/*     */     try {
/* 403 */       saxFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
/* 404 */       saxFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
/* 405 */       saxFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/* 406 */     } catch (SAXNotRecognizedException e) {
/* 407 */       e.printStackTrace();
/* 408 */     } catch (SAXNotSupportedException e) {
/* 409 */       e.printStackTrace();
/* 410 */     } catch (ParserConfigurationException e) {
/* 411 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Document createDocument(InputSource is) throws IOException {
/*     */     try {
/* 423 */       if (this.parserClassName != null) {
/* 424 */         this.parser = XMLReaderFactory.createXMLReader(this.parserClassName);
/*     */       } else {
/*     */         SAXParser saxParser;
/*     */         try {
/* 428 */           saxParser = saxFactory.newSAXParser();
/* 429 */         } catch (ParserConfigurationException pce) {
/* 430 */           throw new IOException("Could not create SAXParser: " + pce.getMessage());
/*     */         } 
/*     */         
/* 433 */         this.parser = saxParser.getXMLReader();
/*     */       } 
/*     */       
/* 436 */       this.parser.setContentHandler(this);
/* 437 */       this.parser.setDTDHandler(this);
/* 438 */       this.parser.setEntityResolver(this);
/* 439 */       this.parser.setErrorHandler((this.errorHandler == null) ? this : this.errorHandler);
/*     */ 
/*     */       
/* 442 */       this.parser.setFeature("http://xml.org/sax/features/namespaces", true);
/*     */       
/* 444 */       this.parser.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
/*     */       
/* 446 */       this.parser.setFeature("http://xml.org/sax/features/validation", this.isValidating);
/*     */       
/* 448 */       this.parser.setFeature("http://xml.org/sax/features/external-general-entities", false);
/* 449 */       this.parser.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
/* 450 */       this.parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/* 451 */       this.parser.setProperty("http://xml.org/sax/properties/lexical-handler", this);
/*     */       
/* 453 */       this.parser.parse(is);
/* 454 */     } catch (SAXException e) {
/* 455 */       Exception ex = e.getException();
/* 456 */       if (ex != null && ex instanceof InterruptedIOException) {
/* 457 */         throw (InterruptedIOException)ex;
/*     */       }
/* 459 */       throw new SAXIOException(e);
/*     */     } 
/*     */     
/* 462 */     this.currentNode = null;
/* 463 */     Document ret = this.document;
/* 464 */     this.document = null;
/* 465 */     this.doctype = null;
/* 466 */     this.locator = null;
/* 467 */     this.parser = null;
/* 468 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentDescriptor getDocumentDescriptor() {
/* 477 */     return this.documentDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentLocator(Locator l) {
/* 485 */     this.locator = l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValidating(boolean isValidating) {
/* 496 */     this.isValidating = isValidating;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidating() {
/* 504 */     return this.isValidating;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorHandler(ErrorHandler eh) {
/* 511 */     this.errorHandler = eh;
/*     */   }
/*     */   
/*     */   public DOMImplementation getDOMImplementation(String ver) {
/* 515 */     return this.implementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatalError(SAXParseException ex) throws SAXException {
/* 523 */     throw ex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(SAXParseException ex) throws SAXException {
/* 531 */     throw ex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warning(SAXParseException ex) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument() throws SAXException {
/* 546 */     this.preInfo = new LinkedList();
/* 547 */     this.namespaces = new HashTableStack();
/* 548 */     this.namespaces.put("xml", "http://www.w3.org/XML/1998/namespace");
/* 549 */     this.namespaces.put("xmlns", "http://www.w3.org/2000/xmlns/");
/* 550 */     this.namespaces.put("", null);
/*     */     
/* 552 */     this.inDTD = false;
/* 553 */     this.inCDATA = false;
/* 554 */     this.inProlog = true;
/* 555 */     this.currentNode = null;
/* 556 */     this.document = null;
/* 557 */     this.doctype = null;
/* 558 */     this.isStandalone = false;
/* 559 */     this.xmlVersion = "1.0";
/*     */     
/* 561 */     this.stringBuffer.setLength(0);
/* 562 */     this.stringContent = false;
/*     */     
/* 564 */     if (this.createDocumentDescriptor) {
/* 565 */       this.documentDescriptor = new DocumentDescriptor();
/*     */     } else {
/* 567 */       this.documentDescriptor = null;
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
/*     */   public void startElement(String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*     */     Element e;
/* 580 */     if (HaltingThread.hasBeenHalted()) {
/* 581 */       throw new SAXException(new InterruptedIOException());
/*     */     }
/*     */     
/* 584 */     if (this.inProlog) {
/* 585 */       this.inProlog = false;
/* 586 */       if (this.parser != null) {
/*     */         try {
/* 588 */           this.isStandalone = this.parser.getFeature("http://xml.org/sax/features/is-standalone");
/*     */         }
/* 590 */         catch (SAXNotRecognizedException sAXNotRecognizedException) {}
/*     */         
/*     */         try {
/* 593 */           this.xmlVersion = (String)this.parser.getProperty("http://xml.org/sax/properties/document-xml-version");
/*     */         }
/* 595 */         catch (SAXNotRecognizedException sAXNotRecognizedException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 601 */     int len = attributes.getLength();
/* 602 */     this.namespaces.push();
/* 603 */     String version = null;
/* 604 */     for (int i = 0; i < len; i++) {
/* 605 */       String aname = attributes.getQName(i);
/* 606 */       int slen = aname.length();
/* 607 */       if (slen >= 5)
/*     */       {
/* 609 */         if (aname.equals("version")) {
/* 610 */           version = attributes.getValue(i);
/*     */         
/*     */         }
/* 613 */         else if (aname.startsWith("xmlns")) {
/*     */           
/* 615 */           if (slen == 5) {
/* 616 */             String ns = attributes.getValue(i);
/* 617 */             if (ns.length() == 0)
/* 618 */               ns = null; 
/* 619 */             this.namespaces.put("", ns);
/* 620 */           } else if (aname.charAt(5) == ':') {
/* 621 */             String ns = attributes.getValue(i);
/* 622 */             if (ns.length() == 0) {
/* 623 */               ns = null;
/*     */             }
/* 625 */             this.namespaces.put(aname.substring(6), ns);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 630 */     appendStringData();
/*     */ 
/*     */ 
/*     */     
/* 634 */     int idx = rawName.indexOf(':');
/* 635 */     String nsp = (idx == -1 || idx == rawName.length() - 1) ? "" : rawName.substring(0, idx);
/*     */ 
/*     */     
/* 638 */     String nsURI = this.namespaces.get(nsp);
/* 639 */     if (this.currentNode == null) {
/* 640 */       this.implementation = getDOMImplementation(version);
/* 641 */       this.document = this.implementation.createDocument(nsURI, rawName, this.doctype);
/* 642 */       Iterator<PreInfo> iterator = this.preInfo.iterator();
/* 643 */       this.currentNode = e = this.document.getDocumentElement();
/* 644 */       while (iterator.hasNext()) {
/* 645 */         PreInfo pi = iterator.next();
/* 646 */         Node n = pi.createNode(this.document);
/* 647 */         this.document.insertBefore(n, e);
/*     */       } 
/* 649 */       this.preInfo = null;
/*     */     } else {
/* 651 */       e = this.document.createElementNS(nsURI, rawName);
/* 652 */       this.currentNode.appendChild(e);
/* 653 */       this.currentNode = e;
/*     */     } 
/*     */ 
/*     */     
/* 657 */     if (this.createDocumentDescriptor && this.locator != null) {
/* 658 */       this.documentDescriptor.setLocation(e, this.locator.getLineNumber(), this.locator.getColumnNumber());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 664 */     for (int j = 0; j < len; j++) {
/* 665 */       String aname = attributes.getQName(j);
/* 666 */       if (aname.equals("xmlns")) {
/* 667 */         e.setAttributeNS("http://www.w3.org/2000/xmlns/", aname, attributes.getValue(j));
/*     */       }
/*     */       else {
/*     */         
/* 671 */         idx = aname.indexOf(':');
/* 672 */         nsURI = (idx == -1) ? null : this.namespaces.get(aname.substring(0, idx));
/*     */ 
/*     */         
/* 675 */         e.setAttributeNS(nsURI, aname, attributes.getValue(j));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String uri, String localName, String rawName) throws SAXException {
/* 686 */     appendStringData();
/*     */     
/* 688 */     if (this.currentNode != null)
/* 689 */       this.currentNode = this.currentNode.getParentNode(); 
/* 690 */     this.namespaces.pop();
/*     */   }
/*     */   
/*     */   public void appendStringData() {
/* 694 */     if (!this.stringContent)
/*     */       return; 
/* 696 */     String str = this.stringBuffer.toString();
/* 697 */     this.stringBuffer.setLength(0);
/* 698 */     this.stringContent = false;
/* 699 */     if (this.currentNode == null) {
/* 700 */       if (this.inCDATA) { this.preInfo.add(new CDataInfo(str)); }
/* 701 */       else { this.preInfo.add(new TextInfo(str)); }
/*     */     
/*     */     } else {
/* 704 */       Node n; if (this.inCDATA) { n = this.document.createCDATASection(str); }
/* 705 */       else { n = this.document.createTextNode(str); }
/* 706 */        this.currentNode.appendChild(n);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 716 */     this.stringBuffer.append(ch, start, length);
/* 717 */     this.stringContent = true;
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
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 729 */     this.stringBuffer.append(ch, start, length);
/* 730 */     this.stringContent = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 739 */     if (this.inDTD) {
/*     */       return;
/*     */     }
/* 742 */     appendStringData();
/*     */     
/* 744 */     if (this.currentNode == null) {
/* 745 */       this.preInfo.add(new ProcessingInstructionInfo(target, data));
/*     */     } else {
/* 747 */       this.currentNode.appendChild(this.document.createProcessingInstruction(target, data));
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
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/* 759 */     appendStringData();
/* 760 */     this.doctype = this.implementation.createDocumentType(name, publicId, systemId);
/* 761 */     this.inDTD = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDTD() throws SAXException {
/* 768 */     this.inDTD = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startEntity(String name) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endEntity(String name) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startCDATA() throws SAXException {
/* 790 */     appendStringData();
/* 791 */     this.inCDATA = true;
/* 792 */     this.stringContent = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCDATA() throws SAXException {
/* 800 */     appendStringData();
/* 801 */     this.inCDATA = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 809 */     if (this.inDTD)
/* 810 */       return;  appendStringData();
/*     */     
/* 812 */     String str = new String(ch, start, length);
/* 813 */     if (this.currentNode == null) {
/* 814 */       this.preInfo.add(new CommentInfo(str));
/*     */     } else {
/* 816 */       this.currentNode.appendChild(this.document.createComment(str));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/SAXDocumentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */