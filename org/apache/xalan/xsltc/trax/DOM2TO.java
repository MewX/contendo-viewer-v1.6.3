/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.xml.serializer.NamespaceMappings;
/*     */ import org.apache.xml.serializer.SerializationHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOM2TO
/*     */   implements Locator, XMLReader
/*     */ {
/*     */   private static final String EMPTYSTRING = "";
/*     */   private static final String XMLNS_PREFIX = "xmlns";
/*     */   private Node _dom;
/*     */   private SerializationHandler _handler;
/*     */   
/*     */   public DOM2TO(Node root, SerializationHandler handler) {
/*  58 */     this._dom = root;
/*  59 */     this._handler = handler;
/*     */   }
/*     */   
/*     */   public ContentHandler getContentHandler() {
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContentHandler(ContentHandler handler) {}
/*     */ 
/*     */   
/*     */   public void parse(InputSource unused) throws IOException, SAXException {
/*  71 */     parse(this._dom);
/*     */   }
/*     */   
/*     */   public void parse() throws IOException, SAXException {
/*  75 */     if (this._dom != null) {
/*  76 */       boolean isIncomplete = (this._dom.getNodeType() != 9);
/*     */ 
/*     */       
/*  79 */       if (isIncomplete) {
/*  80 */         this._handler.startDocument();
/*  81 */         parse(this._dom);
/*  82 */         this._handler.endDocument();
/*     */       } else {
/*     */         
/*  85 */         parse(this._dom);
/*     */       } 
/*     */     } 
/*     */   } private void parse(Node node) throws IOException, SAXException {
/*     */     Node next;
/*     */     String qname;
/*     */     NamedNodeMap map;
/*     */     int length;
/*     */     NamespaceMappings nm;
/*     */     int i;
/*     */     String uri;
/*     */     String localName;
/*  97 */     if (node == null)
/*     */       return; 
/*  99 */     switch (node.getNodeType()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 108 */         this._handler.startCDATA();
/* 109 */         this._handler.characters(node.getNodeValue());
/* 110 */         this._handler.endCDATA();
/*     */         break;
/*     */       
/*     */       case 8:
/* 114 */         this._handler.comment(node.getNodeValue());
/*     */         break;
/*     */       
/*     */       case 9:
/* 118 */         this._handler.startDocument();
/* 119 */         next = node.getFirstChild();
/* 120 */         while (next != null) {
/* 121 */           parse(next);
/* 122 */           next = next.getNextSibling();
/*     */         } 
/* 124 */         this._handler.endDocument();
/*     */         break;
/*     */       
/*     */       case 11:
/* 128 */         next = node.getFirstChild();
/* 129 */         while (next != null) {
/* 130 */           parse(next);
/* 131 */           next = next.getNextSibling();
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 137 */         qname = node.getNodeName();
/* 138 */         this._handler.startElement(null, null, qname);
/*     */ 
/*     */         
/* 141 */         map = node.getAttributes();
/* 142 */         length = map.getLength();
/*     */ 
/*     */         
/* 145 */         nm = new NamespaceMappings();
/* 146 */         for (i = 0; i < length; i++) {
/*     */           
/* 148 */           Node attr = map.item(i);
/* 149 */           String qnameAttr = attr.getNodeName();
/*     */           
/* 151 */           if (qnameAttr.startsWith("xmlns")) {
/* 152 */             String uriAttr = attr.getNodeValue();
/* 153 */             int colon = qnameAttr.lastIndexOf(':');
/* 154 */             String prefix = (colon > 0) ? qnameAttr.substring(colon + 1) : "";
/*     */             
/* 156 */             this._handler.namespaceAfterStartElement(prefix, uriAttr);
/*     */           } else {
/*     */             
/* 159 */             String uriAttr = attr.getNamespaceURI();
/*     */             
/* 161 */             if (uriAttr != null && !uriAttr.equals("")) {
/* 162 */               int j = qnameAttr.lastIndexOf(':');
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 168 */               String newPrefix = nm.lookupPrefix(uriAttr);
/* 169 */               if (newPrefix == null)
/* 170 */                 newPrefix = nm.generateNextPrefix(); 
/* 171 */               String str1 = (j > 0) ? qnameAttr.substring(0, j) : newPrefix;
/*     */               
/* 173 */               this._handler.namespaceAfterStartElement(str1, uriAttr);
/* 174 */               this._handler.addAttribute(str1 + ":" + qnameAttr, attr.getNodeValue());
/*     */             } else {
/*     */               
/* 177 */               this._handler.addAttribute(qnameAttr, attr.getNodeValue());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 183 */         uri = node.getNamespaceURI();
/* 184 */         localName = node.getLocalName();
/*     */ 
/*     */         
/* 187 */         if (uri != null) {
/* 188 */           int colon = qname.lastIndexOf(':');
/* 189 */           String str = (colon > 0) ? qname.substring(0, colon) : "";
/* 190 */           this._handler.namespaceAfterStartElement(str, uri);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 197 */         else if (uri == null && localName != null) {
/* 198 */           String str = "";
/* 199 */           this._handler.namespaceAfterStartElement(str, "");
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 204 */         next = node.getFirstChild();
/* 205 */         while (next != null) {
/* 206 */           parse(next);
/* 207 */           next = next.getNextSibling();
/*     */         } 
/*     */ 
/*     */         
/* 211 */         this._handler.endElement(qname);
/*     */         break;
/*     */       
/*     */       case 7:
/* 215 */         this._handler.processingInstruction(node.getNodeName(), node.getNodeValue());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 220 */         this._handler.characters(node.getNodeValue());
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTDHandler getDTDHandler() {
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getErrorHandler() {
/* 238 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
/* 248 */     return false;
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
/* 265 */     throw new IOException("This method is not yet implemented.");
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
/* 289 */     return null;
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
/* 316 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumnNumber() {
/* 324 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineNumber() {
/* 332 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPublicId() {
/* 340 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/* 348 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getNodeTypeFromCode(short code) {
/* 353 */     String retval = null;
/* 354 */     switch (code) {
/*     */       case 2:
/* 356 */         retval = "ATTRIBUTE_NODE"; break;
/*     */       case 4:
/* 358 */         retval = "CDATA_SECTION_NODE"; break;
/*     */       case 8:
/* 360 */         retval = "COMMENT_NODE"; break;
/*     */       case 11:
/* 362 */         retval = "DOCUMENT_FRAGMENT_NODE"; break;
/*     */       case 9:
/* 364 */         retval = "DOCUMENT_NODE"; break;
/*     */       case 10:
/* 366 */         retval = "DOCUMENT_TYPE_NODE"; break;
/*     */       case 1:
/* 368 */         retval = "ELEMENT_NODE"; break;
/*     */       case 6:
/* 370 */         retval = "ENTITY_NODE"; break;
/*     */       case 5:
/* 372 */         retval = "ENTITY_REFERENCE_NODE"; break;
/*     */       case 12:
/* 374 */         retval = "NOTATION_NODE"; break;
/*     */       case 7:
/* 376 */         retval = "PROCESSING_INSTRUCTION_NODE"; break;
/*     */       case 3:
/* 378 */         retval = "TEXT_NODE"; break;
/*     */     } 
/* 380 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/DOM2TO.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */