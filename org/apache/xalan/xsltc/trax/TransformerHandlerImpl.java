/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.dom.DOMWSFilter;
/*     */ import org.apache.xalan.xsltc.dom.SAXImpl;
/*     */ import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.DeclHandler;
/*     */ import org.xml.sax.ext.LexicalHandler;
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
/*     */ public class TransformerHandlerImpl
/*     */   implements TransformerHandler, DeclHandler
/*     */ {
/*     */   private TransformerImpl _transformer;
/*  52 */   private AbstractTranslet _translet = null;
/*     */   private String _systemId;
/*  54 */   private SAXImpl _dom = null;
/*  55 */   private ContentHandler _handler = null;
/*  56 */   private LexicalHandler _lexHandler = null;
/*  57 */   private DTDHandler _dtdHandler = null;
/*  58 */   private DeclHandler _declHandler = null;
/*  59 */   private Result _result = null;
/*  60 */   private Locator _locator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _done = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _isIdentity = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformerHandlerImpl(TransformerImpl transformer) {
/*  75 */     this._transformer = transformer;
/*     */     
/*  77 */     if (transformer.isIdentity()) {
/*     */       
/*  79 */       this._handler = new DefaultHandler();
/*  80 */       this._isIdentity = true;
/*     */     }
/*     */     else {
/*     */       
/*  84 */       this._translet = this._transformer.getTranslet();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/*  95 */     return this._systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSystemId(String id) {
/* 105 */     this._systemId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer getTransformer() {
/* 115 */     return this._transformer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResult(Result result) throws IllegalArgumentException {
/* 126 */     this._result = result;
/*     */     
/* 128 */     if (null == result) {
/* 129 */       ErrorMsg err = new ErrorMsg("ER_RESULT_NULL");
/* 130 */       throw new IllegalArgumentException(err.toString());
/*     */     } 
/*     */     
/* 133 */     if (this._isIdentity) {
/*     */ 
/*     */       
/* 136 */       try { SerializationHandler outputHandler = this._transformer.getOutputHandler(result);
/*     */         
/* 138 */         this._transformer.transferOutputProperties(outputHandler);
/*     */         
/* 140 */         this._handler = (ContentHandler)outputHandler;
/* 141 */         this._lexHandler = (LexicalHandler)outputHandler; } catch (TransformerException e)
/*     */       
/*     */       { 
/* 144 */         this._result = null; }
/*     */ 
/*     */     
/* 147 */     } else if (this._done) {
/*     */ 
/*     */       
/* 150 */       try { this._transformer.setDOM((DOM)this._dom);
/* 151 */         this._transformer.transform(null, this._result); } catch (TransformerException e)
/*     */       
/*     */       { 
/*     */         
/* 155 */         throw new IllegalArgumentException(e.getMessage()); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 167 */     this._handler.characters(ch, start, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument() throws SAXException {
/* 176 */     if (this._result == null) {
/* 177 */       ErrorMsg err = new ErrorMsg("JAXP_SET_RESULT_ERR");
/* 178 */       throw new SAXException(err.toString());
/*     */     } 
/*     */     
/* 181 */     if (!this._isIdentity) {
/* 182 */       DTMWSFilter dTMWSFilter; boolean hasIdCall = (this._translet != null) ? this._translet.hasIdCall() : false;
/* 183 */       XSLTCDTMManager dtmManager = null;
/*     */ 
/*     */ 
/*     */       
/* 187 */       try { dtmManager = this._transformer.getTransformerFactory().getDTMManagerClass().newInstance(); } catch (Exception e)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 192 */         throw new SAXException(dTMWSFilter); }
/*     */ 
/*     */ 
/*     */       
/* 196 */       if (this._translet != null && this._translet instanceof org.apache.xalan.xsltc.StripFilter) {
/* 197 */         dTMWSFilter = (DTMWSFilter)new DOMWSFilter(this._translet);
/*     */       } else {
/* 199 */         dTMWSFilter = null;
/*     */       } 
/*     */ 
/*     */       
/* 203 */       this._dom = (SAXImpl)dtmManager.getDTM(null, false, dTMWSFilter, true, false, hasIdCall);
/*     */ 
/*     */       
/* 206 */       this._handler = (ContentHandler)this._dom.getBuilder();
/* 207 */       this._lexHandler = (LexicalHandler)this._handler;
/* 208 */       this._dtdHandler = (DTDHandler)this._handler;
/* 209 */       this._declHandler = (DeclHandler)this._handler;
/*     */ 
/*     */ 
/*     */       
/* 213 */       this._dom.setDocumentURI(this._systemId);
/*     */       
/* 215 */       if (this._locator != null) {
/* 216 */         this._handler.setDocumentLocator(this._locator);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 221 */     this._handler.startDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {
/* 230 */     this._handler.endDocument();
/*     */     
/* 232 */     if (!this._isIdentity) {
/*     */       
/* 234 */       if (this._result != null) {
/*     */         
/* 236 */         try { this._transformer.setDOM((DOM)this._dom);
/* 237 */           this._transformer.transform(null, this._result); } catch (TransformerException e)
/*     */         
/*     */         { 
/* 240 */           throw new SAXException(e); }
/*     */       
/*     */       }
/*     */       
/* 244 */       this._done = true;
/*     */ 
/*     */       
/* 247 */       this._transformer.setDOM((DOM)this._dom);
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
/*     */   public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException {
/* 259 */     this._handler.startElement(uri, localName, qname, attributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qname) throws SAXException {
/* 269 */     this._handler.endElement(namespaceURI, localName, qname);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 279 */     this._handler.processingInstruction(target, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startCDATA() throws SAXException {
/* 286 */     if (this._lexHandler != null) {
/* 287 */       this._lexHandler.startCDATA();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCDATA() throws SAXException {
/* 295 */     if (this._lexHandler != null) {
/* 296 */       this._lexHandler.endCDATA();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 307 */     if (this._lexHandler != null) {
/* 308 */       this._lexHandler.comment(ch, start, length);
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
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 320 */     this._handler.ignorableWhitespace(ch, start, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/* 328 */     this._locator = locator;
/*     */     
/* 330 */     if (this._handler != null) {
/* 331 */       this._handler.setDocumentLocator(locator);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {
/* 340 */     this._handler.skippedEntity(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 349 */     this._handler.startPrefixMapping(prefix, uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/* 357 */     this._handler.endPrefixMapping(prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/* 366 */     if (this._lexHandler != null) {
/* 367 */       this._lexHandler.startDTD(name, publicId, systemId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDTD() throws SAXException {
/* 375 */     if (this._lexHandler != null) {
/* 376 */       this._lexHandler.endDTD();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startEntity(String name) throws SAXException {
/* 384 */     if (this._lexHandler != null) {
/* 385 */       this._lexHandler.startEntity(name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endEntity(String name) throws SAXException {
/* 393 */     if (this._lexHandler != null) {
/* 394 */       this._lexHandler.endEntity(name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
/* 404 */     if (this._dtdHandler != null) {
/* 405 */       this._dtdHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notationDecl(String name, String publicId, String systemId) throws SAXException {
/* 416 */     if (this._dtdHandler != null) {
/* 417 */       this._dtdHandler.notationDecl(name, publicId, systemId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {
/* 427 */     if (this._declHandler != null) {
/* 428 */       this._declHandler.attributeDecl(eName, aName, type, valueDefault, value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void elementDecl(String name, String model) throws SAXException {
/* 438 */     if (this._declHandler != null) {
/* 439 */       this._declHandler.elementDecl(name, model);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {
/* 449 */     if (this._declHandler != null) {
/* 450 */       this._declHandler.externalEntityDecl(name, publicId, systemId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalEntityDecl(String name, String value) throws SAXException {
/* 460 */     if (this._declHandler != null)
/* 461 */       this._declHandler.internalEntityDecl(name, value); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/TransformerHandlerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */