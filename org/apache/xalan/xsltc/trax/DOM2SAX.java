/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Stack;
/*     */ import java.util.Vector;
/*     */ import org.apache.xalan.xsltc.dom.SAXImpl;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.ext.LexicalHandler;
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
/*     */ public class DOM2SAX
/*     */   implements Locator, XMLReader
/*     */ {
/*     */   private static final String EMPTYSTRING = "";
/*     */   private static final String XMLNS_PREFIX = "xmlns";
/*  53 */   private Node _dom = null;
/*  54 */   private ContentHandler _sax = null;
/*  55 */   private LexicalHandler _lex = null;
/*  56 */   private SAXImpl _saxImpl = null;
/*  57 */   private Hashtable _nsPrefixes = new Hashtable();
/*     */   
/*     */   public DOM2SAX(Node root) {
/*  60 */     this._dom = root;
/*     */   }
/*     */   
/*     */   public ContentHandler getContentHandler() {
/*  64 */     return this._sax;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentHandler(ContentHandler handler) throws NullPointerException {
/*  70 */     this._sax = handler;
/*  71 */     if (handler instanceof LexicalHandler) {
/*  72 */       this._lex = (LexicalHandler)handler;
/*     */     }
/*     */     
/*  75 */     if (handler instanceof SAXImpl) {
/*  76 */       this._saxImpl = (SAXImpl)handler;
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
/*     */   private boolean startPrefixMapping(String prefix, String uri) throws SAXException {
/*  88 */     boolean pushed = true;
/*  89 */     Stack uriStack = (Stack)this._nsPrefixes.get(prefix);
/*     */     
/*  91 */     if (uriStack != null) {
/*  92 */       if (uriStack.isEmpty()) {
/*  93 */         this._sax.startPrefixMapping(prefix, uri);
/*  94 */         uriStack.push(uri);
/*     */       } else {
/*     */         
/*  97 */         String lastUri = uriStack.peek();
/*  98 */         if (!lastUri.equals(uri)) {
/*  99 */           this._sax.startPrefixMapping(prefix, uri);
/* 100 */           uriStack.push(uri);
/*     */         } else {
/*     */           
/* 103 */           pushed = false;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 108 */       this._sax.startPrefixMapping(prefix, uri);
/* 109 */       this._nsPrefixes.put(prefix, uriStack = new Stack());
/* 110 */       uriStack.push(uri);
/*     */     } 
/* 112 */     return pushed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void endPrefixMapping(String prefix) throws SAXException {
/* 122 */     Stack uriStack = (Stack)this._nsPrefixes.get(prefix);
/*     */     
/* 124 */     if (uriStack != null) {
/* 125 */       this._sax.endPrefixMapping(prefix);
/* 126 */       uriStack.pop();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getLocalName(Node node) {
/* 136 */     String localName = node.getLocalName();
/*     */     
/* 138 */     if (localName == null) {
/* 139 */       String qname = node.getNodeName();
/* 140 */       int col = qname.lastIndexOf(':');
/* 141 */       return (col > 0) ? qname.substring(col + 1) : qname;
/*     */     } 
/* 143 */     return localName;
/*     */   }
/*     */   
/*     */   public void parse(InputSource unused) throws IOException, SAXException {
/* 147 */     parse(this._dom);
/*     */   }
/*     */   
/*     */   public void parse() throws IOException, SAXException {
/* 151 */     if (this._dom != null) {
/* 152 */       boolean isIncomplete = (this._dom.getNodeType() != 9);
/*     */ 
/*     */       
/* 155 */       if (isIncomplete) {
/* 156 */         this._sax.startDocument();
/* 157 */         parse(this._dom);
/* 158 */         this._sax.endDocument();
/*     */       } else {
/*     */         
/* 161 */         parse(this._dom);
/*     */       } 
/*     */     }  } private void parse(Node node) throws IOException, SAXException { String cdata;
/*     */     Node next;
/*     */     Vector pushedPrefixes;
/*     */     AttributesImpl attrs;
/*     */     NamedNodeMap map;
/*     */     int length, i, j;
/*     */     String qname, uri, localName;
/*     */     int nPushedPrefixes, k;
/*     */     String data;
/* 172 */     Node first = null;
/* 173 */     if (node == null)
/*     */       return; 
/* 175 */     switch (node.getNodeType()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 185 */         cdata = node.getNodeValue();
/* 186 */         if (this._lex != null) {
/* 187 */           this._lex.startCDATA();
/* 188 */           this._sax.characters(cdata.toCharArray(), 0, cdata.length());
/* 189 */           this._lex.endCDATA();
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 194 */         this._sax.characters(cdata.toCharArray(), 0, cdata.length());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 8:
/* 199 */         if (this._lex != null) {
/* 200 */           String value = node.getNodeValue();
/* 201 */           this._lex.comment(value.toCharArray(), 0, value.length());
/*     */         } 
/*     */         break;
/*     */       case 9:
/* 205 */         this._sax.setDocumentLocator(this);
/*     */         
/* 207 */         this._sax.startDocument();
/* 208 */         next = node.getFirstChild();
/* 209 */         while (next != null) {
/* 210 */           parse(next);
/* 211 */           next = next.getNextSibling();
/*     */         } 
/* 213 */         this._sax.endDocument();
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 218 */         pushedPrefixes = new Vector();
/* 219 */         attrs = new AttributesImpl();
/* 220 */         map = node.getAttributes();
/* 221 */         length = map.getLength();
/*     */ 
/*     */         
/* 224 */         for (i = 0; i < length; i++) {
/* 225 */           Node attr = map.item(i);
/* 226 */           String qnameAttr = attr.getNodeName();
/*     */ 
/*     */           
/* 229 */           if (qnameAttr.startsWith("xmlns")) {
/* 230 */             String uriAttr = attr.getNodeValue();
/* 231 */             int colon = qnameAttr.lastIndexOf(':');
/* 232 */             String prefix = (colon > 0) ? qnameAttr.substring(colon + 1) : "";
/* 233 */             if (startPrefixMapping(prefix, uriAttr)) {
/* 234 */               pushedPrefixes.addElement(prefix);
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 240 */         for (j = 0; j < length; j++) {
/* 241 */           Node attr = map.item(j);
/* 242 */           String qnameAttr = attr.getNodeName();
/*     */ 
/*     */           
/* 245 */           if (!qnameAttr.startsWith("xmlns")) {
/* 246 */             String uriAttr = attr.getNamespaceURI();
/* 247 */             String localNameAttr = getLocalName(attr);
/*     */ 
/*     */             
/* 250 */             if (uriAttr != null) {
/* 251 */               int colon = qnameAttr.lastIndexOf(':');
/* 252 */               String str = (colon > 0) ? qnameAttr.substring(0, colon) : "";
/* 253 */               if (startPrefixMapping(str, uriAttr)) {
/* 254 */                 pushedPrefixes.addElement(str);
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/* 259 */             attrs.addAttribute(attr.getNamespaceURI(), getLocalName(attr), qnameAttr, "CDATA", attr.getNodeValue());
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 265 */         qname = node.getNodeName();
/* 266 */         uri = node.getNamespaceURI();
/* 267 */         localName = getLocalName(node);
/*     */ 
/*     */         
/* 270 */         if (uri != null) {
/* 271 */           int colon = qname.lastIndexOf(':');
/* 272 */           String str = (colon > 0) ? qname.substring(0, colon) : "";
/* 273 */           if (startPrefixMapping(str, uri)) {
/* 274 */             pushedPrefixes.addElement(str);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 279 */         if (this._saxImpl != null) {
/* 280 */           this._saxImpl.startElement(uri, localName, qname, attrs, node);
/*     */         } else {
/*     */           
/* 283 */           this._sax.startElement(uri, localName, qname, attrs);
/*     */         } 
/*     */ 
/*     */         
/* 287 */         next = node.getFirstChild();
/* 288 */         while (next != null) {
/* 289 */           parse(next);
/* 290 */           next = next.getNextSibling();
/*     */         } 
/*     */ 
/*     */         
/* 294 */         this._sax.endElement(uri, localName, qname);
/*     */ 
/*     */         
/* 297 */         nPushedPrefixes = pushedPrefixes.size();
/* 298 */         for (k = 0; k < nPushedPrefixes; k++) {
/* 299 */           endPrefixMapping(pushedPrefixes.elementAt(k));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 7:
/* 304 */         this._sax.processingInstruction(node.getNodeName(), node.getNodeValue());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 309 */         data = node.getNodeValue();
/* 310 */         this._sax.characters(data.toCharArray(), 0, data.length());
/*     */         break;
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTDHandler getDTDHandler() {
/* 320 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getErrorHandler() {
/* 328 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
/* 338 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse(String sysId) throws IOException, SAXException {
/* 355 */     throw new IOException("This method is not yet implemented.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDTDHandler(DTDHandler handler) throws NullPointerException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntityResolver(EntityResolver resolver) throws NullPointerException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityResolver getEntityResolver() {
/* 379 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorHandler(ErrorHandler handler) throws NullPointerException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
/* 406 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumnNumber() {
/* 414 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineNumber() {
/* 422 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPublicId() {
/* 430 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/* 438 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getNodeTypeFromCode(short code) {
/* 443 */     String retval = null;
/* 444 */     switch (code) {
/*     */       case 2:
/* 446 */         retval = "ATTRIBUTE_NODE"; break;
/*     */       case 4:
/* 448 */         retval = "CDATA_SECTION_NODE"; break;
/*     */       case 8:
/* 450 */         retval = "COMMENT_NODE"; break;
/*     */       case 11:
/* 452 */         retval = "DOCUMENT_FRAGMENT_NODE"; break;
/*     */       case 9:
/* 454 */         retval = "DOCUMENT_NODE"; break;
/*     */       case 10:
/* 456 */         retval = "DOCUMENT_TYPE_NODE"; break;
/*     */       case 1:
/* 458 */         retval = "ELEMENT_NODE"; break;
/*     */       case 6:
/* 460 */         retval = "ENTITY_NODE"; break;
/*     */       case 5:
/* 462 */         retval = "ENTITY_REFERENCE_NODE"; break;
/*     */       case 12:
/* 464 */         retval = "NOTATION_NODE"; break;
/*     */       case 7:
/* 466 */         retval = "PROCESSING_INSTRUCTION_NODE"; break;
/*     */       case 3:
/* 468 */         retval = "TEXT_NODE"; break;
/*     */     } 
/* 470 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/DOM2SAX.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */