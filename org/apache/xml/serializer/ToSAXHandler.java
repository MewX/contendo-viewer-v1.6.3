/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ToSAXHandler
/*     */   extends SerializerBase
/*     */ {
/*     */   protected ContentHandler m_saxHandler;
/*     */   protected LexicalHandler m_lexHandler;
/*     */   
/*     */   public ToSAXHandler() {}
/*     */   
/*     */   public ToSAXHandler(ContentHandler hdlr, LexicalHandler lex, String encoding) {
/*  45 */     setContentHandler(hdlr);
/*  46 */     setLexHandler(lex);
/*  47 */     setEncoding(encoding);
/*     */   }
/*     */   
/*     */   public ToSAXHandler(ContentHandler handler, String encoding) {
/*  51 */     setContentHandler(handler);
/*  52 */     setEncoding(encoding);
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
/*     */   private boolean m_shouldGenerateNSAttribute = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected TransformStateSetter m_state = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void startDocumentInternal() throws SAXException {
/*  87 */     if (this.m_needToCallStartDocument) {
/*     */       
/*  89 */       super.startDocumentInternal();
/*     */       
/*  91 */       this.m_saxHandler.startDocument();
/*  92 */       this.m_needToCallStartDocument = false;
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
/*     */   public void startDTD(String arg0, String arg1, String arg2) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(String characters) throws SAXException {
/* 116 */     int len = characters.length();
/* 117 */     if (len > this.m_charsBuff.length)
/*     */     {
/* 119 */       this.m_charsBuff = new char[len * 2 + 1];
/*     */     }
/* 121 */     characters.getChars(0, len, this.m_charsBuff, 0);
/* 122 */     characters(this.m_charsBuff, 0, len);
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
/*     */   public void comment(String comment) throws SAXException {
/* 134 */     if (this.m_elemContext.m_startTagOpen) {
/*     */       
/* 136 */       closeStartTag();
/*     */     }
/* 138 */     else if (this.m_cdataTagOpen) {
/*     */       
/* 140 */       closeCDATA();
/*     */     } 
/*     */ 
/*     */     
/* 144 */     if (this.m_lexHandler != null) {
/*     */       
/* 146 */       int len = comment.length();
/* 147 */       if (len > this.m_charsBuff.length)
/*     */       {
/* 149 */         this.m_charsBuff = new char[len * 2 + 1];
/*     */       }
/* 151 */       comment.getChars(0, len, this.m_charsBuff, 0);
/* 152 */       this.m_lexHandler.comment(this.m_charsBuff, 0, len);
/*     */       
/* 154 */       if (this.m_tracer != null) {
/* 155 */         fireCommentEvent(this.m_charsBuff, 0, len);
/*     */       }
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
/*     */   public void processingInstruction(String target, String data) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closeStartTag() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closeCDATA() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
/* 211 */     if (this.m_state != null) {
/* 212 */       this.m_state.resetState(getTransformer());
/*     */     }
/*     */ 
/*     */     
/* 216 */     if (this.m_tracer != null) {
/* 217 */       fireStartElem(arg2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLexHandler(LexicalHandler _lexHandler) {
/* 226 */     this.m_lexHandler = _lexHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentHandler(ContentHandler _saxHandler) {
/* 235 */     this.m_saxHandler = _saxHandler;
/* 236 */     if (this.m_lexHandler == null && _saxHandler instanceof LexicalHandler)
/*     */     {
/*     */ 
/*     */       
/* 240 */       this.m_lexHandler = (LexicalHandler)_saxHandler;
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
/*     */   public void setCdataSectionElements(Vector URI_and_localNames) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShouldOutputNSAttr(boolean doOutputNSAttr) {
/* 262 */     this.m_shouldGenerateNSAttribute = doOutputNSAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean getShouldOutputNSAttr() {
/* 273 */     return this.m_shouldGenerateNSAttribute;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushPending() throws SAXException {
/* 283 */     if (this.m_needToCallStartDocument) {
/*     */       
/* 285 */       startDocumentInternal();
/* 286 */       this.m_needToCallStartDocument = false;
/*     */     } 
/*     */     
/* 289 */     if (this.m_elemContext.m_startTagOpen) {
/*     */       
/* 291 */       closeStartTag();
/* 292 */       this.m_elemContext.m_startTagOpen = false;
/*     */     } 
/*     */     
/* 295 */     if (this.m_cdataTagOpen) {
/*     */       
/* 297 */       closeCDATA();
/* 298 */       this.m_cdataTagOpen = false;
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
/*     */   public void setTransformState(TransformStateSetter ts) {
/* 312 */     this.m_state = ts;
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
/*     */   public void startElement(String uri, String localName, String qName) throws SAXException {
/* 328 */     if (this.m_state != null) {
/* 329 */       this.m_state.resetState(getTransformer());
/*     */     }
/*     */ 
/*     */     
/* 333 */     if (this.m_tracer != null) {
/* 334 */       fireStartElem(qName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String qName) throws SAXException {
/* 345 */     if (this.m_state != null) {
/* 346 */       this.m_state.resetState(getTransformer());
/*     */     }
/*     */     
/* 349 */     if (this.m_tracer != null) {
/* 350 */       fireStartElem(qName);
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
/*     */   public void characters(Node node) throws SAXException {
/* 363 */     if (this.m_state != null)
/*     */     {
/* 365 */       this.m_state.setCurrentNode(node);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 370 */     String data = node.getNodeValue();
/* 371 */     if (data != null) {
/* 372 */       characters(data);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatalError(SAXParseException exc) throws SAXException {
/* 380 */     super.fatalError(exc);
/*     */     
/* 382 */     this.m_needToCallStartDocument = false;
/*     */     
/* 384 */     if (this.m_saxHandler instanceof ErrorHandler) {
/* 385 */       ((ErrorHandler)this.m_saxHandler).fatalError(exc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(SAXParseException exc) throws SAXException {
/* 393 */     super.error(exc);
/*     */     
/* 395 */     if (this.m_saxHandler instanceof ErrorHandler) {
/* 396 */       ((ErrorHandler)this.m_saxHandler).error(exc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warning(SAXParseException exc) throws SAXException {
/* 404 */     super.warning(exc);
/*     */     
/* 406 */     if (this.m_saxHandler instanceof ErrorHandler) {
/* 407 */       ((ErrorHandler)this.m_saxHandler).warning(exc);
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
/*     */   public boolean reset() {
/* 421 */     boolean wasReset = false;
/* 422 */     if (super.reset()) {
/*     */       
/* 424 */       resetToSAXHandler();
/* 425 */       wasReset = true;
/*     */     } 
/* 427 */     return wasReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetToSAXHandler() {
/* 436 */     this.m_lexHandler = null;
/* 437 */     this.m_saxHandler = null;
/* 438 */     this.m_state = null;
/* 439 */     this.m_shouldGenerateNSAttribute = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUniqueAttribute(String qName, String value, int flags) throws SAXException {
/* 448 */     addAttribute(qName, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToSAXHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */