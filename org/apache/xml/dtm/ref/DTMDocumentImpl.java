/*      */ package org.apache.xml.dtm.ref;
/*      */ 
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMAxisTraverser;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.utils.FastStringBuffer;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.apache.xml.utils.XMLStringFactory;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.DeclHandler;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DTMDocumentImpl
/*      */   implements DTM, ContentHandler, LexicalHandler
/*      */ {
/*      */   protected static final byte DOCHANDLE_SHIFT = 22;
/*      */   protected static final int NODEHANDLE_MASK = 8388607;
/*      */   protected static final int DOCHANDLE_MASK = -8388608;
/*   74 */   int m_docHandle = -1;
/*   75 */   int m_docElement = -1;
/*      */ 
/*      */   
/*   78 */   int currentParent = 0;
/*   79 */   int previousSibling = 0;
/*   80 */   protected int m_currentNode = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean previousSiblingWasParent = false;
/*      */ 
/*      */ 
/*      */   
/*   88 */   int[] gotslot = new int[4];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean done = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean m_isError = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean DEBUG = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String m_documentBaseURI;
/*      */ 
/*      */ 
/*      */   
/*  110 */   private IncrementalSAXSource m_incrSAXSource = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  119 */   ChunkedIntArray nodes = new ChunkedIntArray(4);
/*      */ 
/*      */ 
/*      */   
/*  123 */   private FastStringBuffer m_char = new FastStringBuffer();
/*      */ 
/*      */   
/*  126 */   private int m_char_current_start = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   private DTMStringPool m_localNames = new DTMStringPool();
/*  134 */   private DTMStringPool m_nsNames = new DTMStringPool();
/*  135 */   private DTMStringPool m_prefixNames = new DTMStringPool();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  143 */   private ExpandedNameTable m_expandedNames = new ExpandedNameTable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private XMLStringFactory m_xsf;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMDocumentImpl(DTMManager mgr, int documentNumber, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory) {
/*  162 */     initDocument(documentNumber);
/*  163 */     this.m_xsf = xstringfactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIncrementalSAXSource(IncrementalSAXSource source) {
/*  179 */     this.m_incrSAXSource = source;
/*      */ 
/*      */     
/*  182 */     source.setContentHandler(this);
/*  183 */     source.setLexicalHandler(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int appendNode(int w0, int w1, int w2, int w3) {
/*  207 */     int slotnumber = this.nodes.appendSlot(w0, w1, w2, w3);
/*      */ 
/*      */ 
/*      */     
/*  211 */     if (this.previousSiblingWasParent) {
/*  212 */       this.nodes.writeEntry(this.previousSibling, 2, slotnumber);
/*      */     }
/*  214 */     this.previousSiblingWasParent = false;
/*      */     
/*  216 */     return slotnumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String featureId, boolean state) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocalNameTable(DTMStringPool poolRef) {
/*  239 */     this.m_localNames = poolRef;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMStringPool getLocalNameTable() {
/*  248 */     return this.m_localNames;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNsNameTable(DTMStringPool poolRef) {
/*  259 */     this.m_nsNames = poolRef;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMStringPool getNsNameTable() {
/*  268 */     return this.m_nsNames;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrefixNameTable(DTMStringPool poolRef) {
/*  279 */     this.m_prefixNames = poolRef;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMStringPool getPrefixNameTable() {
/*  288 */     return this.m_prefixNames;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setContentBuffer(FastStringBuffer buffer) {
/*  298 */     this.m_char = buffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   FastStringBuffer getContentBuffer() {
/*  307 */     return this.m_char;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContentHandler getContentHandler() {
/*  321 */     if (this.m_incrSAXSource instanceof IncrementalSAXSource_Filter) {
/*  322 */       return (ContentHandler)this.m_incrSAXSource;
/*      */     }
/*  324 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LexicalHandler getLexicalHandler() {
/*  340 */     if (this.m_incrSAXSource instanceof IncrementalSAXSource_Filter) {
/*  341 */       return (LexicalHandler)this.m_incrSAXSource;
/*      */     }
/*  343 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/*  354 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTDHandler getDTDHandler() {
/*  365 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorHandler getErrorHandler() {
/*  376 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DeclHandler getDeclHandler() {
/*  387 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean needsTwoThreads() {
/*  397 */     return (null != this.m_incrSAXSource);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] ch, int start, int length) throws SAXException {
/*  411 */     this.m_char.append(ch, start, length);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void processAccumulatedText() {
/*  417 */     int len = this.m_char.length();
/*  418 */     if (len != this.m_char_current_start) {
/*      */ 
/*      */       
/*  421 */       appendTextChild(this.m_char_current_start, len - this.m_char_current_start);
/*  422 */       this.m_char_current_start = len;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument() throws SAXException {
/*  430 */     appendEndDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/*  436 */     processAccumulatedText();
/*      */ 
/*      */     
/*  439 */     appendEndElement();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endPrefixMapping(String prefix) throws SAXException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/*  454 */     processAccumulatedText();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void skippedEntity(String name) throws SAXException {
/*  464 */     processAccumulatedText();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() throws SAXException {
/*  470 */     appendStartDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/*  476 */     processAccumulatedText();
/*      */ 
/*      */     
/*  479 */     String prefix = null;
/*  480 */     int colon = qName.indexOf(':');
/*  481 */     if (colon > 0) {
/*  482 */       prefix = qName.substring(0, colon);
/*      */     }
/*      */     
/*  485 */     System.out.println("Prefix=" + prefix + " index=" + this.m_prefixNames.stringToIndex(prefix));
/*  486 */     appendStartElement(this.m_nsNames.stringToIndex(namespaceURI), this.m_localNames.stringToIndex(localName), this.m_prefixNames.stringToIndex(prefix));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  493 */     int nAtts = (atts == null) ? 0 : atts.getLength();
/*      */     
/*  495 */     for (int i = nAtts - 1; i >= 0; i--) {
/*      */       
/*  497 */       qName = atts.getQName(i);
/*  498 */       if (qName.startsWith("xmlns:") || "xmlns".equals(qName)) {
/*      */         
/*  500 */         prefix = null;
/*  501 */         colon = qName.indexOf(':');
/*  502 */         if (colon > 0) {
/*      */           
/*  504 */           prefix = qName.substring(0, colon);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  509 */           prefix = null;
/*      */         } 
/*      */ 
/*      */         
/*  513 */         appendNSDeclaration(this.m_prefixNames.stringToIndex(prefix), this.m_nsNames.stringToIndex(atts.getValue(i)), atts.getType(i).equalsIgnoreCase("ID"));
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  520 */     for (int j = nAtts - 1; j >= 0; j--) {
/*      */       
/*  522 */       qName = atts.getQName(j);
/*  523 */       if (!qName.startsWith("xmlns:") && !"xmlns".equals(qName)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  528 */         prefix = null;
/*  529 */         colon = qName.indexOf(':');
/*  530 */         if (colon > 0) {
/*      */           
/*  532 */           prefix = qName.substring(0, colon);
/*  533 */           localName = qName.substring(colon + 1);
/*      */         }
/*      */         else {
/*      */           
/*  537 */           prefix = "";
/*  538 */           localName = qName;
/*      */         } 
/*      */ 
/*      */         
/*  542 */         this.m_char.append(atts.getValue(j));
/*  543 */         int contentEnd = this.m_char.length();
/*      */         
/*  545 */         if (!"xmlns".equals(prefix) && !"xmlns".equals(qName)) {
/*  546 */           appendAttribute(this.m_nsNames.stringToIndex(atts.getURI(j)), this.m_localNames.stringToIndex(localName), this.m_prefixNames.stringToIndex(prefix), atts.getType(j).equalsIgnoreCase("ID"), this.m_char_current_start, contentEnd - this.m_char_current_start);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  551 */         this.m_char_current_start = contentEnd;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/*  568 */     processAccumulatedText();
/*      */     
/*  570 */     this.m_char.append(ch, start, length);
/*  571 */     appendComment(this.m_char_current_start, length);
/*  572 */     this.m_char_current_start += length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endCDATA() throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDTD() throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endEntity(String name) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startCDATA() throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDTD(String name, String publicId, String systemId) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startEntity(String name) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void initDocument(int documentNumber) {
/*  623 */     this.m_docHandle = documentNumber << 22;
/*      */ 
/*      */     
/*  626 */     this.nodes.writeSlot(0, 9, -1, -1, 0);
/*      */     
/*  628 */     this.done = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasChildNodes(int nodeHandle) {
/*  998 */     return (getFirstChild(nodeHandle) != -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstChild(int nodeHandle) {
/* 1012 */     nodeHandle &= 0x7FFFFF;
/*      */     
/* 1014 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/*      */ 
/*      */     
/* 1017 */     short type = (short)(this.gotslot[0] & 0xFFFF);
/*      */ 
/*      */     
/* 1020 */     if (type == 1 || type == 9 || type == 5) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1030 */       int kid = nodeHandle + 1;
/* 1031 */       this.nodes.readSlot(kid, this.gotslot);
/* 1032 */       while (2 == (this.gotslot[0] & 0xFFFF)) {
/*      */         
/* 1034 */         kid = this.gotslot[2];
/*      */         
/* 1036 */         if (kid == -1) return -1; 
/* 1037 */         this.nodes.readSlot(kid, this.gotslot);
/*      */       } 
/*      */       
/* 1040 */       if (this.gotslot[1] == nodeHandle) {
/*      */         
/* 1042 */         int firstChild = kid | this.m_docHandle;
/*      */         
/* 1044 */         return firstChild;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1049 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLastChild(int nodeHandle) {
/* 1063 */     nodeHandle &= 0x7FFFFF;
/*      */     
/* 1065 */     int lastChild = -1;
/* 1066 */     for (int nextkid = getFirstChild(nodeHandle); nextkid != -1; 
/* 1067 */       nextkid = getNextSibling(nextkid)) {
/* 1068 */       lastChild = nextkid;
/*      */     }
/* 1070 */     return lastChild | this.m_docHandle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAttributeNode(int nodeHandle, String namespaceURI, String name) {
/* 1086 */     int nsIndex = this.m_nsNames.stringToIndex(namespaceURI);
/* 1087 */     int nameIndex = this.m_localNames.stringToIndex(name);
/* 1088 */     nodeHandle &= 0x7FFFFF;
/* 1089 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/* 1090 */     short type = (short)(this.gotslot[0] & 0xFFFF);
/*      */     
/* 1092 */     if (type == 1) {
/* 1093 */       nodeHandle++;
/*      */     }
/* 1095 */     while (type == 2) {
/* 1096 */       if (nsIndex == this.gotslot[0] << 16 && this.gotslot[3] == nameIndex) {
/* 1097 */         return nodeHandle | this.m_docHandle;
/*      */       }
/* 1099 */       nodeHandle = this.gotslot[2];
/* 1100 */       this.nodes.readSlot(nodeHandle, this.gotslot);
/*      */     } 
/* 1102 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstAttribute(int nodeHandle) {
/* 1112 */     nodeHandle &= 0x7FFFFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1120 */     if (1 != (this.nodes.readEntry(nodeHandle, 0) & 0xFFFF)) {
/* 1121 */       return -1;
/*      */     }
/* 1123 */     nodeHandle++;
/* 1124 */     return (2 == (this.nodes.readEntry(nodeHandle, 0) & 0xFFFF)) ? (nodeHandle | this.m_docHandle) : -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstNamespaceNode(int nodeHandle, boolean inScope) {
/* 1143 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextSibling(int nodeHandle) {
/* 1164 */     nodeHandle &= 0x7FFFFF;
/*      */     
/* 1166 */     if (nodeHandle == 0) {
/* 1167 */       return -1;
/*      */     }
/* 1169 */     short type = (short)(this.nodes.readEntry(nodeHandle, 0) & 0xFFFF);
/* 1170 */     if (type == 1 || type == 2 || type == 5) {
/*      */       
/* 1172 */       int nextSib = this.nodes.readEntry(nodeHandle, 2);
/* 1173 */       if (nextSib == -1)
/* 1174 */         return -1; 
/* 1175 */       if (nextSib != 0) {
/* 1176 */         return this.m_docHandle | nextSib;
/*      */       }
/*      */     } 
/*      */     
/* 1180 */     int thisParent = this.nodes.readEntry(nodeHandle, 1);
/*      */     
/* 1182 */     if (this.nodes.readEntry(++nodeHandle, 1) == thisParent) {
/* 1183 */       return this.m_docHandle | nodeHandle;
/*      */     }
/* 1185 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPreviousSibling(int nodeHandle) {
/* 1198 */     nodeHandle &= 0x7FFFFF;
/*      */     
/* 1200 */     if (nodeHandle == 0) {
/* 1201 */       return -1;
/*      */     }
/* 1203 */     int parent = this.nodes.readEntry(nodeHandle, 1);
/* 1204 */     int kid = -1;
/* 1205 */     for (int nextkid = getFirstChild(parent); nextkid != nodeHandle; 
/* 1206 */       nextkid = getNextSibling(nextkid)) {
/* 1207 */       kid = nextkid;
/*      */     }
/* 1209 */     return kid | this.m_docHandle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextAttribute(int nodeHandle) {
/* 1222 */     nodeHandle &= 0x7FFFFF;
/* 1223 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1229 */     short type = (short)(this.gotslot[0] & 0xFFFF);
/*      */     
/* 1231 */     if (type == 1)
/* 1232 */       return getFirstAttribute(nodeHandle); 
/* 1233 */     if (type == 2 && 
/* 1234 */       this.gotslot[2] != -1) {
/* 1235 */       return this.m_docHandle | this.gotslot[2];
/*      */     }
/* 1237 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextNamespaceNode(int baseHandle, int namespaceHandle, boolean inScope) {
/* 1252 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextDescendant(int subtreeRootHandle, int nodeHandle) {
/* 1266 */     subtreeRootHandle &= 0x7FFFFF;
/* 1267 */     nodeHandle &= 0x7FFFFF;
/*      */     
/* 1269 */     if (nodeHandle == 0)
/* 1270 */       return -1; 
/* 1271 */     while (!this.m_isError) {
/*      */       
/* 1273 */       if (this.done && nodeHandle > this.nodes.slotsUsed())
/*      */         break; 
/* 1275 */       if (nodeHandle > subtreeRootHandle) {
/* 1276 */         this.nodes.readSlot(nodeHandle + 1, this.gotslot);
/* 1277 */         if (this.gotslot[2] != 0) {
/* 1278 */           short type = (short)(this.gotslot[0] & 0xFFFF);
/* 1279 */           if (type == 2) {
/* 1280 */             nodeHandle += 2; continue;
/*      */           } 
/* 1282 */           int nextParentPos = this.gotslot[1];
/* 1283 */           if (nextParentPos >= subtreeRootHandle) {
/* 1284 */             return this.m_docHandle | nodeHandle + 1;
/*      */           }
/*      */           break;
/*      */         } 
/* 1288 */         if (!this.done) {
/*      */           continue;
/*      */         }
/*      */         break;
/*      */       } 
/* 1293 */       nodeHandle++;
/*      */     } 
/*      */ 
/*      */     
/* 1297 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextFollowing(int axisContextHandle, int nodeHandle) {
/* 1310 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextPreceding(int axisContextHandle, int nodeHandle) {
/* 1323 */     nodeHandle &= 0x7FFFFF;
/* 1324 */     while (nodeHandle > 1) {
/* 1325 */       nodeHandle--;
/* 1326 */       if (2 == (this.nodes.readEntry(nodeHandle, 0) & 0xFFFF)) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1337 */       return this.m_docHandle | this.nodes.specialFind(axisContextHandle, nodeHandle);
/*      */     } 
/* 1339 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParent(int nodeHandle) {
/* 1353 */     return this.m_docHandle | this.nodes.readEntry(nodeHandle, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocumentRoot() {
/* 1361 */     return this.m_docHandle | this.m_docElement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocument() {
/* 1371 */     return this.m_docHandle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOwnerDocument(int nodeHandle) {
/* 1389 */     if ((nodeHandle & 0x7FFFFF) == 0)
/* 1390 */       return -1; 
/* 1391 */     return nodeHandle & 0xFF800000;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocumentRoot(int nodeHandle) {
/* 1408 */     if ((nodeHandle & 0x7FFFFF) == 0)
/* 1409 */       return -1; 
/* 1410 */     return nodeHandle & 0xFF800000;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLString getStringValue(int nodeHandle) {
/* 1424 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/* 1425 */     int nodetype = this.gotslot[0] & 0xFF;
/* 1426 */     String value = null;
/*      */     
/* 1428 */     switch (nodetype) {
/*      */       case 3:
/*      */       case 4:
/*      */       case 8:
/* 1432 */         value = this.m_char.getString(this.gotslot[2], this.gotslot[3]);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1441 */     return this.m_xsf.newstr(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStringValueChunkCount(int nodeHandle) {
/* 1472 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getStringValueChunk(int nodeHandle, int chunkIndex, int[] startAndLen) {
/* 1501 */     return new char[0];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(int nodeHandle) {
/* 1511 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/* 1512 */     String qName = this.m_localNames.indexToString(this.gotslot[3]);
/*      */ 
/*      */     
/* 1515 */     int colonpos = qName.indexOf(":");
/* 1516 */     String localName = qName.substring(colonpos + 1);
/*      */     
/* 1518 */     String namespace = this.m_nsNames.indexToString(this.gotslot[0] << 16);
/*      */     
/* 1520 */     String expandedName = namespace + ":" + localName;
/* 1521 */     int expandedNameID = this.m_nsNames.stringToIndex(expandedName);
/*      */     
/* 1523 */     return expandedNameID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(String namespace, String localName, int type) {
/* 1543 */     String expandedName = namespace + ":" + localName;
/* 1544 */     int expandedNameID = this.m_nsNames.stringToIndex(expandedName);
/*      */     
/* 1546 */     return expandedNameID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalNameFromExpandedNameID(int ExpandedNameID) {
/* 1559 */     String expandedName = this.m_localNames.indexToString(ExpandedNameID);
/*      */     
/* 1561 */     int colonpos = expandedName.indexOf(":");
/* 1562 */     String localName = expandedName.substring(colonpos + 1);
/* 1563 */     return localName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceFromExpandedNameID(int ExpandedNameID) {
/* 1576 */     String expandedName = this.m_localNames.indexToString(ExpandedNameID);
/*      */     
/* 1578 */     int colonpos = expandedName.indexOf(":");
/* 1579 */     String nsName = expandedName.substring(0, colonpos);
/*      */     
/* 1581 */     return nsName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1588 */   static final String[] fixednames = new String[] { null, null, null, "#text", "#cdata_section", null, null, null, "#comment", "#document", null, "#document-fragment", null };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName(int nodeHandle) {
/* 1607 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/* 1608 */     short type = (short)(this.gotslot[0] & 0xFFFF);
/* 1609 */     String name = fixednames[type];
/* 1610 */     if (null == name) {
/* 1611 */       int i = this.gotslot[3];
/* 1612 */       System.out.println("got i=" + i + " " + (i >> 16) + "/" + (i & 0xFFFF));
/*      */       
/* 1614 */       name = this.m_localNames.indexToString(i & 0xFFFF);
/* 1615 */       String prefix = this.m_prefixNames.indexToString(i >> 16);
/* 1616 */       if (prefix != null && prefix.length() > 0)
/* 1617 */         name = prefix + ":" + name; 
/*      */     } 
/* 1619 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeNameX(int nodeHandle) {
/* 1630 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalName(int nodeHandle) {
/* 1644 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/* 1645 */     short type = (short)(this.gotslot[0] & 0xFFFF);
/* 1646 */     String name = "";
/* 1647 */     if (type == 1 || type == 2) {
/* 1648 */       int i = this.gotslot[3];
/* 1649 */       name = this.m_localNames.indexToString(i & 0xFFFF);
/* 1650 */       if (name == null) name = ""; 
/*      */     } 
/* 1652 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix(int nodeHandle) {
/* 1670 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/* 1671 */     short type = (short)(this.gotslot[0] & 0xFFFF);
/* 1672 */     String name = "";
/* 1673 */     if (type == 1 || type == 2) {
/* 1674 */       int i = this.gotslot[3];
/* 1675 */       name = this.m_prefixNames.indexToString(i >> 16);
/* 1676 */       if (name == null) name = ""; 
/*      */     } 
/* 1678 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceURI(int nodeHandle) {
/* 1690 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeValue(int nodeHandle) {
/* 1703 */     this.nodes.readSlot(nodeHandle, this.gotslot);
/* 1704 */     int nodetype = this.gotslot[0] & 0xFF;
/* 1705 */     String value = null;
/*      */     
/* 1707 */     switch (nodetype) {
/*      */       case 2:
/* 1709 */         this.nodes.readSlot(nodeHandle + 1, this.gotslot);
/*      */       case 3:
/*      */       case 4:
/*      */       case 8:
/* 1713 */         value = this.m_char.getString(this.gotslot[2], this.gotslot[3]);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1721 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType(int nodeHandle) {
/* 1733 */     return (short)(this.nodes.readEntry(nodeHandle, 0) & 0xFFFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getLevel(int nodeHandle) {
/* 1745 */     short count = 0;
/* 1746 */     while (nodeHandle != 0) {
/* 1747 */       count = (short)(count + 1);
/* 1748 */       nodeHandle = this.nodes.readEntry(nodeHandle, 1);
/*      */     } 
/* 1750 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSupported(String feature, String version) {
/* 1767 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentBaseURI() {
/* 1779 */     return this.m_documentBaseURI;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentBaseURI(String baseURI) {
/* 1790 */     this.m_documentBaseURI = baseURI;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentSystemIdentifier(int nodeHandle) {
/* 1800 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentEncoding(int nodeHandle) {
/* 1809 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentStandalone(int nodeHandle) {
/* 1821 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentVersion(int documentHandle) {
/* 1833 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDocumentAllDeclarationsProcessed() {
/* 1845 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationSystemIdentifier() {
/* 1855 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationPublicIdentifier() {
/* 1867 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getElementById(String elementId) {
/* 1886 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnparsedEntityURI(String name) {
/* 1922 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsPreStripping() {
/* 1934 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNodeAfter(int nodeHandle1, int nodeHandle2) {
/* 1954 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCharacterElementContentWhitespace(int nodeHandle) {
/* 1972 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDocumentAllDeclarationsProcessed(int documentHandle) {
/* 1988 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAttributeSpecified(int attributeHandle) {
/* 2001 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispatchCharactersEvents(int nodeHandle, ContentHandler ch, boolean normalize) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispatchToEvents(int nodeHandle, ContentHandler ch) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getNode(int nodeHandle) {
/* 2043 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendChild(int newChild, boolean clone, boolean cloneDepth) {
/* 2064 */     boolean sameDoc = ((newChild & 0xFF800000) == this.m_docHandle);
/* 2065 */     if (clone || !sameDoc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendTextChild(String str) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendTextChild(int m_char_current_start, int contentLength) {
/* 2103 */     int w0 = 3;
/*      */     
/* 2105 */     int w1 = this.currentParent;
/*      */     
/* 2107 */     int w2 = m_char_current_start;
/*      */     
/* 2109 */     int w3 = contentLength;
/*      */     
/* 2111 */     int ourslot = appendNode(w0, w1, w2, w3);
/* 2112 */     this.previousSibling = ourslot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendComment(int m_char_current_start, int contentLength) {
/* 2126 */     int w0 = 8;
/*      */     
/* 2128 */     int w1 = this.currentParent;
/*      */     
/* 2130 */     int w2 = m_char_current_start;
/*      */     
/* 2132 */     int w3 = contentLength;
/*      */     
/* 2134 */     int ourslot = appendNode(w0, w1, w2, w3);
/* 2135 */     this.previousSibling = ourslot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendStartElement(int namespaceIndex, int localNameIndex, int prefixIndex) {
/* 2162 */     int w0 = namespaceIndex << 16 | 0x1;
/*      */     
/* 2164 */     int w1 = this.currentParent;
/*      */     
/* 2166 */     int w2 = 0;
/*      */     
/* 2168 */     int w3 = localNameIndex | prefixIndex << 16;
/* 2169 */     System.out.println("set w3=" + w3 + " " + (w3 >> 16) + "/" + (w3 & 0xFFFF));
/*      */ 
/*      */     
/* 2172 */     int ourslot = appendNode(w0, w1, w2, w3);
/* 2173 */     this.currentParent = ourslot;
/* 2174 */     this.previousSibling = 0;
/*      */ 
/*      */     
/* 2177 */     if (this.m_docElement == -1) {
/* 2178 */       this.m_docElement = ourslot;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendNSDeclaration(int prefixIndex, int namespaceIndex, boolean isID) {
/* 2205 */     int namespaceForNamespaces = this.m_nsNames.stringToIndex("http://www.w3.org/2000/xmlns/");
/*      */ 
/*      */     
/* 2208 */     int w0 = 0xD | this.m_nsNames.stringToIndex("http://www.w3.org/2000/xmlns/") << 16;
/*      */ 
/*      */     
/* 2211 */     int w1 = this.currentParent;
/*      */     
/* 2213 */     int w2 = 0;
/*      */     
/* 2215 */     int w3 = namespaceIndex;
/*      */     
/* 2217 */     int ourslot = appendNode(w0, w1, w2, w3);
/* 2218 */     this.previousSibling = ourslot;
/* 2219 */     this.previousSiblingWasParent = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendAttribute(int namespaceIndex, int localNameIndex, int prefixIndex, boolean isID, int m_char_current_start, int contentLength) {
/* 2245 */     int w0 = 0x2 | namespaceIndex << 16;
/*      */ 
/*      */     
/* 2248 */     int w1 = this.currentParent;
/*      */     
/* 2250 */     int w2 = 0;
/*      */     
/* 2252 */     int w3 = localNameIndex | prefixIndex << 16;
/* 2253 */     System.out.println("set w3=" + w3 + " " + (w3 >> 16) + "/" + (w3 & 0xFFFF));
/*      */     
/* 2255 */     int ourslot = appendNode(w0, w1, w2, w3);
/* 2256 */     this.previousSibling = ourslot;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2261 */     w0 = 3;
/*      */     
/* 2263 */     w1 = ourslot;
/*      */     
/* 2265 */     w2 = m_char_current_start;
/*      */     
/* 2267 */     w3 = contentLength;
/* 2268 */     appendNode(w0, w1, w2, w3);
/*      */ 
/*      */     
/* 2271 */     this.previousSiblingWasParent = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisTraverser getAxisTraverser(int axis) {
/* 2285 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getAxisIterator(int axis) {
/* 2301 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedAxisIterator(int axis, int type) {
/* 2317 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendEndElement() {
/* 2328 */     if (this.previousSiblingWasParent) {
/* 2329 */       this.nodes.writeEntry(this.previousSibling, 2, -1);
/*      */     }
/*      */     
/* 2332 */     this.previousSibling = this.currentParent;
/* 2333 */     this.nodes.readSlot(this.currentParent, this.gotslot);
/* 2334 */     this.currentParent = this.gotslot[1] & 0xFFFF;
/*      */ 
/*      */ 
/*      */     
/* 2338 */     this.previousSiblingWasParent = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendStartDocument() {
/* 2352 */     this.m_docElement = -1;
/* 2353 */     initDocument(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void appendEndDocument() {
/* 2361 */     this.done = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProperty(String property, Object value) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SourceLocator getSourceLocatorFor(int node) {
/* 2386 */     return null;
/*      */   }
/*      */   
/*      */   public void documentRegistration() {}
/*      */   
/*      */   public void documentRelease() {}
/*      */   
/*      */   public void migrateTo(DTMManager manager) {}
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMDocumentImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */