/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.apache.xml.utils.ThreadControllerWrapper;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.XMLReader;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IncrementalSAXSource_Filter
/*     */   implements Runnable, IncrementalSAXSource, ContentHandler, DTDHandler, ErrorHandler, LexicalHandler
/*     */ {
/*     */   boolean DEBUG = false;
/*  73 */   private CoroutineManager fCoroutineManager = null;
/*  74 */   private int fControllerCoroutineID = -1;
/*  75 */   private int fSourceCoroutineID = -1;
/*     */   
/*  77 */   private ContentHandler clientContentHandler = null;
/*  78 */   private LexicalHandler clientLexicalHandler = null;
/*  79 */   private DTDHandler clientDTDHandler = null;
/*  80 */   private ErrorHandler clientErrorHandler = null;
/*     */   private int eventcounter;
/*  82 */   private int frequency = 5;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean fNoMoreEvents = false;
/*     */ 
/*     */ 
/*     */   
/*  90 */   private XMLReader fXMLReader = null;
/*  91 */   private InputSource fXMLReaderInputSource = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IncrementalSAXSource_Filter() {
/*  98 */     init(new CoroutineManager(), -1, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IncrementalSAXSource_Filter(CoroutineManager co, int controllerCoroutineID) {
/* 106 */     init(co, controllerCoroutineID, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IncrementalSAXSource createIncrementalSAXSource(CoroutineManager co, int controllerCoroutineID) {
/* 113 */     return new IncrementalSAXSource_Filter(co, controllerCoroutineID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(CoroutineManager co, int controllerCoroutineID, int sourceCoroutineID) {
/* 123 */     if (co == null)
/* 124 */       co = new CoroutineManager(); 
/* 125 */     this.fCoroutineManager = co;
/* 126 */     this.fControllerCoroutineID = co.co_joinCoroutineSet(controllerCoroutineID);
/* 127 */     this.fSourceCoroutineID = co.co_joinCoroutineSet(sourceCoroutineID);
/* 128 */     if (this.fControllerCoroutineID == -1 || this.fSourceCoroutineID == -1) {
/* 129 */       throw new RuntimeException(XMLMessages.createXMLMessage("ER_COJOINROUTINESET_FAILED", null));
/*     */     }
/* 131 */     this.fNoMoreEvents = false;
/* 132 */     this.eventcounter = this.frequency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLReader(XMLReader eventsource) {
/* 142 */     this.fXMLReader = eventsource;
/* 143 */     eventsource.setContentHandler(this);
/* 144 */     eventsource.setDTDHandler(this);
/* 145 */     eventsource.setErrorHandler(this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     try { eventsource.setProperty("http://xml.org/sax/properties/lexical-handler", this); } catch (SAXNotRecognizedException e)
/*     */     
/*     */     { 
/*     */        }
/*     */     
/* 155 */     catch (SAXNotSupportedException sAXNotSupportedException) {}
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
/*     */   public void setContentHandler(ContentHandler handler) {
/* 170 */     this.clientContentHandler = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDTDHandler(DTDHandler handler) {
/* 175 */     this.clientDTDHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLexicalHandler(LexicalHandler handler) {
/* 183 */     this.clientLexicalHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrHandler(ErrorHandler handler) {
/* 189 */     this.clientErrorHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnFrequency(int events) {
/* 196 */     if (events < 1) events = 1; 
/* 197 */     this.frequency = this.eventcounter = events;
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
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 222 */     if (--this.eventcounter <= 0) {
/*     */       
/* 224 */       co_yield(true);
/* 225 */       this.eventcounter = this.frequency;
/*     */     } 
/* 227 */     if (this.clientContentHandler != null) {
/* 228 */       this.clientContentHandler.characters(ch, start, length);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {
/* 234 */     if (this.clientContentHandler != null) {
/* 235 */       this.clientContentHandler.endDocument();
/*     */     }
/* 237 */     this.eventcounter = 0;
/* 238 */     co_yield(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 244 */     if (--this.eventcounter <= 0) {
/*     */       
/* 246 */       co_yield(true);
/* 247 */       this.eventcounter = this.frequency;
/*     */     } 
/* 249 */     if (this.clientContentHandler != null) {
/* 250 */       this.clientContentHandler.endElement(namespaceURI, localName, qName);
/*     */     }
/*     */   }
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/* 255 */     if (--this.eventcounter <= 0) {
/*     */       
/* 257 */       co_yield(true);
/* 258 */       this.eventcounter = this.frequency;
/*     */     } 
/* 260 */     if (this.clientContentHandler != null) {
/* 261 */       this.clientContentHandler.endPrefixMapping(prefix);
/*     */     }
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 266 */     if (--this.eventcounter <= 0) {
/*     */       
/* 268 */       co_yield(true);
/* 269 */       this.eventcounter = this.frequency;
/*     */     } 
/* 271 */     if (this.clientContentHandler != null) {
/* 272 */       this.clientContentHandler.ignorableWhitespace(ch, start, length);
/*     */     }
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 277 */     if (--this.eventcounter <= 0) {
/*     */       
/* 279 */       co_yield(true);
/* 280 */       this.eventcounter = this.frequency;
/*     */     } 
/* 282 */     if (this.clientContentHandler != null)
/* 283 */       this.clientContentHandler.processingInstruction(target, data); 
/*     */   }
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/* 287 */     if (--this.eventcounter <= 0)
/*     */     {
/*     */ 
/*     */       
/* 291 */       this.eventcounter = this.frequency;
/*     */     }
/* 293 */     if (this.clientContentHandler != null) {
/* 294 */       this.clientContentHandler.setDocumentLocator(locator);
/*     */     }
/*     */   }
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {
/* 299 */     if (--this.eventcounter <= 0) {
/*     */       
/* 301 */       co_yield(true);
/* 302 */       this.eventcounter = this.frequency;
/*     */     } 
/* 304 */     if (this.clientContentHandler != null) {
/* 305 */       this.clientContentHandler.skippedEntity(name);
/*     */     }
/*     */   }
/*     */   
/*     */   public void startDocument() throws SAXException {
/* 310 */     co_entry_pause();
/*     */ 
/*     */     
/* 313 */     if (--this.eventcounter <= 0) {
/*     */       
/* 315 */       co_yield(true);
/* 316 */       this.eventcounter = this.frequency;
/*     */     } 
/* 318 */     if (this.clientContentHandler != null) {
/* 319 */       this.clientContentHandler.startDocument();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 325 */     if (--this.eventcounter <= 0) {
/*     */       
/* 327 */       co_yield(true);
/* 328 */       this.eventcounter = this.frequency;
/*     */     } 
/* 330 */     if (this.clientContentHandler != null) {
/* 331 */       this.clientContentHandler.startElement(namespaceURI, localName, qName, atts);
/*     */     }
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 336 */     if (--this.eventcounter <= 0) {
/*     */       
/* 338 */       co_yield(true);
/* 339 */       this.eventcounter = this.frequency;
/*     */     } 
/* 341 */     if (this.clientContentHandler != null) {
/* 342 */       this.clientContentHandler.startPrefixMapping(prefix, uri);
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
/*     */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 358 */     if (null != this.clientLexicalHandler) {
/* 359 */       this.clientLexicalHandler.comment(ch, start, length);
/*     */     }
/*     */   }
/*     */   
/*     */   public void endCDATA() throws SAXException {
/* 364 */     if (null != this.clientLexicalHandler) {
/* 365 */       this.clientLexicalHandler.endCDATA();
/*     */     }
/*     */   }
/*     */   
/*     */   public void endDTD() throws SAXException {
/* 370 */     if (null != this.clientLexicalHandler) {
/* 371 */       this.clientLexicalHandler.endDTD();
/*     */     }
/*     */   }
/*     */   
/*     */   public void endEntity(String name) throws SAXException {
/* 376 */     if (null != this.clientLexicalHandler) {
/* 377 */       this.clientLexicalHandler.endEntity(name);
/*     */     }
/*     */   }
/*     */   
/*     */   public void startCDATA() throws SAXException {
/* 382 */     if (null != this.clientLexicalHandler) {
/* 383 */       this.clientLexicalHandler.startCDATA();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/* 389 */     if (null != this.clientLexicalHandler) {
/* 390 */       this.clientLexicalHandler.startDTD(name, publicId, systemId);
/*     */     }
/*     */   }
/*     */   
/*     */   public void startEntity(String name) throws SAXException {
/* 395 */     if (null != this.clientLexicalHandler) {
/* 396 */       this.clientLexicalHandler.startEntity(name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notationDecl(String a, String b, String c) throws SAXException {
/* 404 */     if (null != this.clientDTDHandler)
/* 405 */       this.clientDTDHandler.notationDecl(a, b, c); 
/*     */   }
/*     */   
/*     */   public void unparsedEntityDecl(String a, String b, String c, String d) throws SAXException {
/* 409 */     if (null != this.clientDTDHandler) {
/* 410 */       this.clientDTDHandler.unparsedEntityDecl(a, b, c, d);
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
/*     */   public void error(SAXParseException exception) throws SAXException {
/* 430 */     if (null != this.clientErrorHandler) {
/* 431 */       this.clientErrorHandler.error(exception);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatalError(SAXParseException exception) throws SAXException {
/* 438 */     if (null != this.clientErrorHandler) {
/* 439 */       this.clientErrorHandler.error(exception);
/*     */     }
/* 441 */     this.eventcounter = 0;
/* 442 */     co_yield(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void warning(SAXParseException exception) throws SAXException {
/* 448 */     if (null != this.clientErrorHandler) {
/* 449 */       this.clientErrorHandler.error(exception);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSourceCoroutineID() {
/* 458 */     return this.fSourceCoroutineID;
/*     */   }
/*     */   public int getControllerCoroutineID() {
/* 461 */     return this.fControllerCoroutineID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CoroutineManager getCoroutineManager() {
/* 471 */     return this.fCoroutineManager;
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
/*     */   protected void count_and_yield(boolean moreExpected) throws SAXException {
/* 487 */     if (!moreExpected) this.eventcounter = 0;
/*     */     
/* 489 */     if (--this.eventcounter <= 0) {
/*     */       
/* 491 */       co_yield(true);
/* 492 */       this.eventcounter = this.frequency;
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
/*     */   private void co_entry_pause() throws SAXException {
/* 505 */     if (this.fCoroutineManager == null)
/*     */     {
/*     */       
/* 508 */       init(null, -1, -1);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 513 */     try { Object arg = this.fCoroutineManager.co_entry_pause(this.fSourceCoroutineID);
/* 514 */       if (arg == Boolean.FALSE)
/* 515 */         co_yield(false);  } catch (NoSuchMethodException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 521 */       if (this.DEBUG) e.printStackTrace(); 
/* 522 */       throw new SAXException(e); }
/*     */   
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void co_yield(boolean moreRemains) throws SAXException {
/* 551 */     if (this.fNoMoreEvents) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 556 */     try { Object arg = Boolean.FALSE;
/* 557 */       if (moreRemains)
/*     */       {
/*     */         
/* 560 */         arg = this.fCoroutineManager.co_resume(Boolean.TRUE, this.fSourceCoroutineID, this.fControllerCoroutineID);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 566 */       if (arg == Boolean.FALSE)
/*     */       
/* 568 */       { this.fNoMoreEvents = true;
/*     */         
/* 570 */         if (this.fXMLReader != null) {
/* 571 */           throw new StopException(this);
/*     */         }
/*     */         
/* 574 */         this.fCoroutineManager.co_exit_to(Boolean.FALSE, this.fSourceCoroutineID, this.fControllerCoroutineID); }  } catch (NoSuchMethodException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 582 */       this.fNoMoreEvents = true;
/* 583 */       this.fCoroutineManager.co_exit(this.fSourceCoroutineID);
/* 584 */       throw new SAXException(e); }
/*     */   
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
/*     */   public void startParse(InputSource source) throws SAXException {
/* 603 */     if (this.fNoMoreEvents)
/* 604 */       throw new SAXException(XMLMessages.createXMLMessage("ER_INCRSAXSRCFILTER_NOT_RESTARTABLE", null)); 
/* 605 */     if (this.fXMLReader == null) {
/* 606 */       throw new SAXException(XMLMessages.createXMLMessage("ER_XMLRDR_NOT_BEFORE_STARTPARSE", null));
/*     */     }
/* 608 */     this.fXMLReaderInputSource = source;
/*     */ 
/*     */ 
/*     */     
/* 612 */     ThreadControllerWrapper.runThread(this, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 620 */     if (this.fXMLReader == null)
/*     */       return; 
/* 622 */     if (this.DEBUG) System.out.println("IncrementalSAXSource_Filter parse thread launched");
/*     */ 
/*     */     
/* 625 */     Object arg = Boolean.FALSE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 633 */     try { this.fXMLReader.parse(this.fXMLReaderInputSource); } catch (IOException ex)
/*     */     
/*     */     { 
/*     */       
/* 637 */       arg = ex; } catch (StopException ex)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 642 */       if (this.DEBUG) System.out.println("Active IncrementalSAXSource_Filter normal stop exception");  } catch (SAXException ex)
/*     */     
/*     */     { 
/*     */       
/* 646 */       Exception inner = ex.getException();
/* 647 */       if (inner instanceof StopException) {
/*     */         
/* 649 */         if (this.DEBUG) System.out.println("Active IncrementalSAXSource_Filter normal stop exception");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 654 */         if (this.DEBUG) {
/*     */           
/* 656 */           System.out.println("Active IncrementalSAXSource_Filter UNEXPECTED SAX exception: " + inner);
/* 657 */           inner.printStackTrace();
/*     */         } 
/* 659 */         arg = ex;
/*     */       }  }
/*     */ 
/*     */ 
/*     */     
/* 664 */     this.fXMLReader = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 669 */     try { this.fNoMoreEvents = true;
/* 670 */       this.fCoroutineManager.co_exit_to(arg, this.fSourceCoroutineID, this.fControllerCoroutineID); } catch (NoSuchMethodException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 677 */       e.printStackTrace(System.err);
/* 678 */       this.fCoroutineManager.co_exit(this.fSourceCoroutineID); }
/*     */   
/*     */   }
/*     */   class StopException extends RuntimeException { private final IncrementalSAXSource_Filter this$0;
/*     */     
/*     */     StopException(IncrementalSAXSource_Filter this$0) {
/* 684 */       this.this$0 = this$0;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object deliverMoreNodes(boolean parsemore) {
/* 708 */     if (this.fNoMoreEvents) {
/* 709 */       return Boolean.FALSE;
/*     */     }
/*     */ 
/*     */     
/* 713 */     try { Object result = this.fCoroutineManager.co_resume(parsemore ? Boolean.TRUE : Boolean.FALSE, this.fControllerCoroutineID, this.fSourceCoroutineID);
/*     */ 
/*     */       
/* 716 */       if (result == Boolean.FALSE) {
/* 717 */         this.fCoroutineManager.co_exit(this.fControllerCoroutineID);
/*     */       }
/* 719 */       return result; } catch (NoSuchMethodException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 727 */       return e; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/IncrementalSAXSource_Filter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */