/*      */ package org.apache.xalan.transformer;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.Result;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.sax.TransformerHandler;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.ref.IncrementalSAXSource_Filter;
/*      */ import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TransformerHandlerImpl
/*      */   implements TransformerHandler, ContentHandler, DTDHandler, EntityResolver, ErrorHandler, DeclHandler, LexicalHandler
/*      */ {
/*      */   private boolean m_insideParse = false;
/*      */   
/*      */   protected void clearCoRoutine() {
/*   99 */     clearCoRoutine(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void clearCoRoutine(SAXException ex) {
/*  107 */     if (null != ex) {
/*  108 */       this.m_transformer.setExceptionThrown(ex);
/*      */     }
/*  110 */     if (this.m_dtm instanceof SAX2DTM) {
/*      */       
/*  112 */       if (DEBUG) {
/*  113 */         System.err.println("In clearCoRoutine...");
/*      */       }
/*      */       
/*  116 */       try { SAX2DTM sax2dtm = (SAX2DTM)this.m_dtm;
/*  117 */         if (null != this.m_contentHandler && this.m_contentHandler instanceof IncrementalSAXSource_Filter) {
/*      */ 
/*      */           
/*  120 */           IncrementalSAXSource_Filter sp = (IncrementalSAXSource_Filter)this.m_contentHandler;
/*      */ 
/*      */           
/*  123 */           sp.deliverMoreNodes(false);
/*      */         } 
/*      */         
/*  126 */         sax2dtm.clearCoRoutine(true);
/*  127 */         this.m_contentHandler = null;
/*  128 */         this.m_dtdHandler = null;
/*  129 */         this.m_entityResolver = null;
/*  130 */         this.m_errorHandler = null;
/*  131 */         this.m_lexicalHandler = null; } catch (Throwable throwable)
/*      */       
/*      */       { 
/*      */         
/*  135 */         throwable.printStackTrace(); }
/*      */ 
/*      */       
/*  138 */       if (DEBUG) {
/*  139 */         System.err.println("...exiting clearCoRoutine");
/*      */       }
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
/*      */   public void setResult(Result result) throws IllegalArgumentException {
/*  158 */     if (null == result) {
/*  159 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_RESULT_NULL", null));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  166 */     try { SerializationHandler xoh = this.m_transformer.createSerializationHandler(result);
/*      */       
/*  168 */       this.m_transformer.setSerializationHandler(xoh); } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/*  172 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_RESULT_COULD_NOT_BE_SET", null)); }
/*      */ 
/*      */     
/*  175 */     this.m_result = result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSystemId(String systemID) {
/*  185 */     this.m_baseSystemID = systemID;
/*  186 */     this.m_dtm.setDocumentBaseURI(systemID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSystemId() {
/*  196 */     return this.m_baseSystemID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transformer getTransformer() {
/*  207 */     return this.m_transformer;
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
/*      */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
/*  233 */     if (this.m_entityResolver != null)
/*      */     {
/*  235 */       return this.m_entityResolver.resolveEntity(publicId, systemId);
/*      */     }
/*      */ 
/*      */     
/*  239 */     return null;
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
/*      */   public void notationDecl(String name, String publicId, String systemId) throws SAXException {
/*  261 */     if (this.m_dtdHandler != null)
/*      */     {
/*  263 */       this.m_dtdHandler.notationDecl(name, publicId, systemId);
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
/*      */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
/*  283 */     if (this.m_dtdHandler != null)
/*      */     {
/*  285 */       this.m_dtdHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
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
/*      */   public void setDocumentLocator(Locator locator) {
/*  302 */     if (DEBUG) {
/*  303 */       System.out.println("TransformerHandlerImpl#setDocumentLocator: " + locator.getSystemId());
/*      */     }
/*      */     
/*  306 */     this.m_locator = locator;
/*      */     
/*  308 */     if (null == this.m_baseSystemID)
/*      */     {
/*  310 */       setSystemId(locator.getSystemId());
/*      */     }
/*      */     
/*  313 */     if (this.m_contentHandler != null)
/*      */     {
/*  315 */       this.m_contentHandler.setDocumentLocator(locator);
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
/*      */   public void startDocument() throws SAXException {
/*  329 */     if (DEBUG) {
/*  330 */       System.out.println("TransformerHandlerImpl#startDocument");
/*      */     }
/*  332 */     this.m_insideParse = true;
/*      */ 
/*      */ 
/*      */     
/*  336 */     if (this.m_contentHandler != null) {
/*      */ 
/*      */       
/*  339 */       if (DTMManager.getIncremental()) {
/*      */         
/*  341 */         this.m_transformer.setSourceTreeDocForThread(this.m_dtm.getDocument());
/*      */         
/*  343 */         int cpriority = Thread.currentThread().getPriority();
/*      */ 
/*      */ 
/*      */         
/*  347 */         this.m_transformer.runTransformThread(cpriority);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  354 */       this.m_contentHandler.startDocument();
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
/*      */   public void endDocument() throws SAXException {
/*  372 */     if (DEBUG) {
/*  373 */       System.out.println("TransformerHandlerImpl#endDocument");
/*      */     }
/*  375 */     this.m_insideParse = false;
/*      */     
/*  377 */     if (this.m_contentHandler != null)
/*      */     {
/*  379 */       this.m_contentHandler.endDocument();
/*      */     }
/*      */     
/*  382 */     if (DTMManager.getIncremental()) {
/*      */       
/*  384 */       this.m_transformer.waitTransformThread();
/*      */     }
/*      */     else {
/*      */       
/*  388 */       this.m_transformer.setSourceTreeDocForThread(this.m_dtm.getDocument());
/*  389 */       this.m_transformer.run();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  428 */     if (DEBUG) {
/*  429 */       System.out.println("TransformerHandlerImpl#startPrefixMapping: " + prefix + ", " + uri);
/*      */     }
/*      */     
/*  432 */     if (this.m_contentHandler != null)
/*      */     {
/*  434 */       this.m_contentHandler.startPrefixMapping(prefix, uri);
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
/*      */   public void endPrefixMapping(String prefix) throws SAXException {
/*  449 */     if (DEBUG) {
/*  450 */       System.out.println("TransformerHandlerImpl#endPrefixMapping: " + prefix);
/*      */     }
/*      */     
/*  453 */     if (this.m_contentHandler != null)
/*      */     {
/*  455 */       this.m_contentHandler.endPrefixMapping(prefix);
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
/*      */   public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
/*  476 */     if (DEBUG) {
/*  477 */       System.out.println("TransformerHandlerImpl#startElement: " + qName);
/*      */     }
/*  479 */     if (this.m_contentHandler != null)
/*      */     {
/*  481 */       this.m_contentHandler.startElement(uri, localName, qName, atts);
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
/*      */   public void endElement(String uri, String localName, String qName) throws SAXException {
/*  500 */     if (DEBUG) {
/*  501 */       System.out.println("TransformerHandlerImpl#endElement: " + qName);
/*      */     }
/*  503 */     if (this.m_contentHandler != null)
/*      */     {
/*  505 */       this.m_contentHandler.endElement(uri, localName, qName);
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
/*      */   public void characters(char[] ch, int start, int length) throws SAXException {
/*  522 */     if (DEBUG) {
/*  523 */       System.out.println("TransformerHandlerImpl#characters: " + start + ", " + length);
/*      */     }
/*      */     
/*  526 */     if (this.m_contentHandler != null)
/*      */     {
/*  528 */       this.m_contentHandler.characters(ch, start, length);
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
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*  546 */     if (DEBUG) {
/*  547 */       System.out.println("TransformerHandlerImpl#ignorableWhitespace: " + start + ", " + length);
/*      */     }
/*      */     
/*  550 */     if (this.m_contentHandler != null)
/*      */     {
/*  552 */       this.m_contentHandler.ignorableWhitespace(ch, start, length);
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
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/*  569 */     if (DEBUG) {
/*  570 */       System.out.println("TransformerHandlerImpl#processingInstruction: " + target + ", " + data);
/*      */     }
/*      */     
/*  573 */     if (this.m_contentHandler != null)
/*      */     {
/*  575 */       this.m_contentHandler.processingInstruction(target, data);
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
/*      */   public void skippedEntity(String name) throws SAXException {
/*  590 */     if (DEBUG) {
/*  591 */       System.out.println("TransformerHandlerImpl#skippedEntity: " + name);
/*      */     }
/*  593 */     if (this.m_contentHandler != null)
/*      */     {
/*  595 */       this.m_contentHandler.skippedEntity(name);
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
/*      */   public void warning(SAXParseException e) throws SAXException {
/*  617 */     ErrorListener errorListener = this.m_transformer.getErrorListener();
/*  618 */     if (errorListener instanceof ErrorHandler) {
/*      */       
/*  620 */       ((ErrorHandler)errorListener).warning(e);
/*      */     } else {
/*      */ 
/*      */ 
/*      */       
/*      */       try { 
/*  626 */         errorListener.warning(new TransformerException(e)); } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  630 */         throw e; }
/*      */     
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
/*      */   public void error(SAXParseException e) throws SAXException {
/*  652 */     ErrorListener errorListener = this.m_transformer.getErrorListener();
/*  653 */     if (errorListener instanceof ErrorHandler) {
/*      */       
/*  655 */       ((ErrorHandler)errorListener).error(e);
/*  656 */       if (null != this.m_errorHandler) {
/*  657 */         this.m_errorHandler.error(e);
/*      */       }
/*      */     } else {
/*      */ 
/*      */ 
/*      */       
/*  663 */       try { errorListener.error(new TransformerException(e));
/*  664 */         if (null != this.m_errorHandler)
/*  665 */           this.m_errorHandler.error(e);  } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  669 */         throw e; }
/*      */     
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
/*      */   public void fatalError(SAXParseException e) throws SAXException {
/*  684 */     if (null != this.m_errorHandler) {
/*      */ 
/*      */       
/*      */       try { 
/*  688 */         this.m_errorHandler.fatalError(e); } catch (SAXParseException sAXParseException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  701 */     ErrorListener errorListener = this.m_transformer.getErrorListener();
/*      */     
/*  703 */     if (errorListener instanceof ErrorHandler) {
/*      */       
/*  705 */       ((ErrorHandler)errorListener).fatalError(e);
/*  706 */       if (null != this.m_errorHandler) {
/*  707 */         this.m_errorHandler.fatalError(e);
/*      */       }
/*      */     } else {
/*      */ 
/*      */ 
/*      */       
/*  713 */       try { errorListener.fatalError(new TransformerException(e));
/*  714 */         if (null != this.m_errorHandler)
/*  715 */           this.m_errorHandler.fatalError(e);  } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  719 */         throw e; }
/*      */     
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/*  753 */     if (DEBUG) {
/*  754 */       System.out.println("TransformerHandlerImpl#startDTD: " + name + ", " + publicId + ", " + systemId);
/*      */     }
/*      */     
/*  757 */     if (null != this.m_lexicalHandler)
/*      */     {
/*  759 */       this.m_lexicalHandler.startDTD(name, publicId, systemId);
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
/*      */   public void endDTD() throws SAXException {
/*  772 */     if (DEBUG) {
/*  773 */       System.out.println("TransformerHandlerImpl#endDTD");
/*      */     }
/*  775 */     if (null != this.m_lexicalHandler)
/*      */     {
/*  777 */       this.m_lexicalHandler.endDTD();
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
/*      */ 
/*      */   
/*      */   public void startEntity(String name) throws SAXException {
/*  806 */     if (DEBUG) {
/*  807 */       System.out.println("TransformerHandlerImpl#startEntity: " + name);
/*      */     }
/*  809 */     if (null != this.m_lexicalHandler)
/*      */     {
/*  811 */       this.m_lexicalHandler.startEntity(name);
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
/*      */   public void endEntity(String name) throws SAXException {
/*  825 */     if (DEBUG) {
/*  826 */       System.out.println("TransformerHandlerImpl#endEntity: " + name);
/*      */     }
/*  828 */     if (null != this.m_lexicalHandler)
/*      */     {
/*  830 */       this.m_lexicalHandler.endEntity(name);
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
/*      */   public void startCDATA() throws SAXException {
/*  847 */     if (DEBUG) {
/*  848 */       System.out.println("TransformerHandlerImpl#startCDATA");
/*      */     }
/*  850 */     if (null != this.m_lexicalHandler)
/*      */     {
/*  852 */       this.m_lexicalHandler.startCDATA();
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
/*      */   public void endCDATA() throws SAXException {
/*  865 */     if (DEBUG) {
/*  866 */       System.out.println("TransformerHandlerImpl#endCDATA");
/*      */     }
/*  868 */     if (null != this.m_lexicalHandler)
/*      */     {
/*  870 */       this.m_lexicalHandler.endCDATA();
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
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/*  889 */     if (DEBUG) {
/*  890 */       System.out.println("TransformerHandlerImpl#comment: " + start + ", " + length);
/*      */     }
/*      */     
/*  893 */     if (null != this.m_lexicalHandler)
/*      */     {
/*  895 */       this.m_lexicalHandler.comment(ch, start, length);
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
/*      */   public void elementDecl(String name, String model) throws SAXException {
/*  919 */     if (DEBUG) {
/*  920 */       System.out.println("TransformerHandlerImpl#elementDecl: " + name + ", " + model);
/*      */     }
/*      */     
/*  923 */     if (null != this.m_declHandler)
/*      */     {
/*  925 */       this.m_declHandler.elementDecl(name, model);
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
/*      */   
/*      */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {
/*  953 */     if (DEBUG) {
/*  954 */       System.out.println("TransformerHandlerImpl#attributeDecl: " + eName + ", " + aName + ", etc...");
/*      */     }
/*      */     
/*  957 */     if (null != this.m_declHandler)
/*      */     {
/*  959 */       this.m_declHandler.attributeDecl(eName, aName, type, valueDefault, value);
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
/*      */   public void internalEntityDecl(String name, String value) throws SAXException {
/*  980 */     if (DEBUG) {
/*  981 */       System.out.println("TransformerHandlerImpl#internalEntityDecl: " + name + ", " + value);
/*      */     }
/*      */     
/*  984 */     if (null != this.m_declHandler)
/*      */     {
/*  986 */       this.m_declHandler.internalEntityDecl(name, value);
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
/*      */   public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {
/* 1009 */     if (DEBUG) {
/* 1010 */       System.out.println("TransformerHandlerImpl#externalEntityDecl: " + name + ", " + publicId + ", " + systemId);
/*      */     }
/*      */     
/* 1013 */     if (null != this.m_declHandler)
/*      */     {
/* 1015 */       this.m_declHandler.externalEntityDecl(name, publicId, systemId);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean DEBUG = false;
/*      */   
/*      */   private TransformerImpl m_transformer;
/*      */   
/*      */   private String m_baseSystemID;
/*      */   private Result m_result;
/*      */   private Locator m_locator;
/*      */   private EntityResolver m_entityResolver;
/*      */   private DTDHandler m_dtdHandler;
/*      */   private ContentHandler m_contentHandler;
/*      */   private ErrorHandler m_errorHandler;
/*      */   private LexicalHandler m_lexicalHandler;
/*      */   private DeclHandler m_declHandler;
/*      */   DTM m_dtm;
/*      */   
/*      */   public TransformerHandlerImpl(TransformerImpl transformer, boolean doFragment, String baseSystemID) {
/* 1036 */     this.m_result = null;
/*      */ 
/*      */     
/* 1039 */     this.m_locator = null;
/*      */ 
/*      */     
/* 1042 */     this.m_entityResolver = null;
/*      */ 
/*      */     
/* 1045 */     this.m_dtdHandler = null;
/*      */ 
/*      */     
/* 1048 */     this.m_contentHandler = null;
/*      */ 
/*      */     
/* 1051 */     this.m_errorHandler = null;
/*      */ 
/*      */     
/* 1054 */     this.m_lexicalHandler = null;
/*      */ 
/*      */     
/* 1057 */     this.m_declHandler = null;
/*      */     this.m_transformer = transformer;
/*      */     this.m_baseSystemID = baseSystemID;
/*      */     XPathContext xctxt = transformer.getXPathContext();
/*      */     DTM dtm = xctxt.getDTM(null, true, transformer, true, true);
/*      */     this.m_dtm = dtm;
/*      */     dtm.setDocumentBaseURI(baseSystemID);
/*      */     this.m_contentHandler = dtm.getContentHandler();
/*      */     this.m_dtdHandler = dtm.getDTDHandler();
/*      */     this.m_entityResolver = dtm.getEntityResolver();
/*      */     this.m_errorHandler = dtm.getErrorHandler();
/*      */     this.m_lexicalHandler = dtm.getLexicalHandler();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/TransformerHandlerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */