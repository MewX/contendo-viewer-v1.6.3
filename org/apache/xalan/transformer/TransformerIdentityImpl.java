/*      */ package org.apache.xalan.transformer;
/*      */ 
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Properties;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.Result;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.URIResolver;
/*      */ import javax.xml.transform.dom.DOMResult;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.sax.SAXResult;
/*      */ import javax.xml.transform.sax.SAXSource;
/*      */ import javax.xml.transform.sax.TransformerHandler;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import javax.xml.transform.stream.StreamSource;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xalan.templates.OutputProperties;
/*      */ import org.apache.xml.serializer.Serializer;
/*      */ import org.apache.xml.serializer.SerializerFactory;
/*      */ import org.apache.xml.utils.DOM2Helper;
/*      */ import org.apache.xml.utils.DOMBuilder;
/*      */ import org.apache.xml.utils.DOMHelper;
/*      */ import org.apache.xml.utils.DefaultErrorHandler;
/*      */ import org.apache.xml.utils.TreeWalker;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ import org.apache.xml.utils.XMLReaderManager;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentFragment;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXNotRecognizedException;
/*      */ import org.xml.sax.XMLReader;
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
/*      */ public class TransformerIdentityImpl
/*      */   extends Transformer
/*      */   implements TransformerHandler, DeclHandler
/*      */ {
/*   86 */   private OutputProperties m_outputFormat = new OutputProperties("xml");
/*      */ 
/*      */ 
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
/*   99 */     if (null == result)
/*  100 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_RESULT_NULL", null)); 
/*  101 */     this.m_result = result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSystemId(String systemID) {
/*  111 */     this.m_systemID = systemID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSystemId() {
/*  121 */     return this.m_systemID;
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
/*  132 */     return this;
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
/*      */   private void createResultContentHandler(Result outputTarget) throws TransformerException {
/*  151 */     if (outputTarget instanceof SAXResult) {
/*      */       
/*  153 */       SAXResult saxResult = (SAXResult)outputTarget;
/*      */       
/*  155 */       this.m_resultContentHandler = saxResult.getHandler();
/*  156 */       this.m_resultLexicalHandler = saxResult.getLexicalHandler();
/*      */       
/*  158 */       if (this.m_resultContentHandler instanceof Serializer)
/*      */       {
/*      */ 
/*      */         
/*  162 */         this.m_serializer = (Serializer)this.m_resultContentHandler;
/*      */       }
/*      */     }
/*  165 */     else if (outputTarget instanceof DOMResult) {
/*      */       Document document; short s;
/*  167 */       DOMResult domResult = (DOMResult)outputTarget;
/*  168 */       Node outputNode = domResult.getNode();
/*      */ 
/*      */ 
/*      */       
/*  172 */       if (null != outputNode) {
/*      */         
/*  174 */         s = outputNode.getNodeType();
/*  175 */         document = (9 == s) ? (Document)outputNode : outputNode.getOwnerDocument();
/*      */       } else {
/*      */ 
/*      */ 
/*      */         
/*      */         try { 
/*      */           
/*  182 */           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/*      */           
/*  184 */           dbf.setNamespaceAware(true);
/*      */           
/*  186 */           DocumentBuilder db = dbf.newDocumentBuilder();
/*      */           
/*  188 */           document = db.newDocument(); } catch (ParserConfigurationException pce)
/*      */         
/*      */         { 
/*      */           
/*  192 */           throw new TransformerException(pce); }
/*      */ 
/*      */         
/*  195 */         outputNode = document;
/*  196 */         s = outputNode.getNodeType();
/*      */         
/*  198 */         ((DOMResult)outputTarget).setNode(outputNode);
/*      */       } 
/*      */       
/*  201 */       this.m_resultContentHandler = (11 == s) ? (ContentHandler)new DOMBuilder(document, (DocumentFragment)outputNode) : (ContentHandler)new DOMBuilder(document, outputNode);
/*      */ 
/*      */ 
/*      */       
/*  205 */       this.m_resultLexicalHandler = (LexicalHandler)this.m_resultContentHandler;
/*      */     }
/*  207 */     else if (outputTarget instanceof StreamResult) {
/*      */       
/*  209 */       StreamResult sresult = (StreamResult)outputTarget;
/*  210 */       String method = this.m_outputFormat.getProperty("method");
/*      */ 
/*      */ 
/*      */       
/*  214 */       try { Serializer serializer = SerializerFactory.getSerializer(this.m_outputFormat.getProperties());
/*      */ 
/*      */         
/*  217 */         this.m_serializer = serializer;
/*      */         
/*  219 */         if (null != sresult.getWriter()) {
/*  220 */           serializer.setWriter(sresult.getWriter());
/*  221 */         } else if (null != sresult.getOutputStream()) {
/*  222 */           serializer.setOutputStream(sresult.getOutputStream());
/*  223 */         } else if (null != sresult.getSystemId()) {
/*      */           
/*  225 */           String fileURL = sresult.getSystemId();
/*      */           
/*  227 */           if (fileURL.startsWith("file:///"))
/*      */           {
/*  229 */             if (fileURL.substring(8).indexOf(":") > 0) {
/*  230 */               fileURL = fileURL.substring(8);
/*      */             } else {
/*  232 */               fileURL = fileURL.substring(7);
/*      */             } 
/*      */           }
/*  235 */           this.m_outputStream = new FileOutputStream(fileURL);
/*  236 */           serializer.setOutputStream(this.m_outputStream);
/*      */         } else {
/*      */           
/*  239 */           throw new TransformerException(XSLMessages.createMessage("ER_NO_OUTPUT_SPECIFIED", null));
/*      */         } 
/*  241 */         this.m_resultContentHandler = serializer.asContentHandler(); } catch (IOException ioe)
/*      */       
/*      */       { 
/*      */         
/*  245 */         throw new TransformerException(ioe); }
/*      */ 
/*      */     
/*      */     } else {
/*      */       
/*  250 */       throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_TRANSFORM_TO_RESULT_TYPE", new Object[] { outputTarget.getClass().getName() }));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  255 */     if (this.m_resultContentHandler instanceof DTDHandler) {
/*  256 */       this.m_resultDTDHandler = (DTDHandler)this.m_resultContentHandler;
/*      */     }
/*  258 */     if (this.m_resultContentHandler instanceof DeclHandler) {
/*  259 */       this.m_resultDeclHandler = (DeclHandler)this.m_resultContentHandler;
/*      */     }
/*  261 */     if (this.m_resultContentHandler instanceof LexicalHandler) {
/*  262 */       this.m_resultLexicalHandler = (LexicalHandler)this.m_resultContentHandler;
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
/*      */   public void transform(Source source, Result outputTarget) throws TransformerException {
/*  278 */     createResultContentHandler(outputTarget);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  288 */     if ((source instanceof StreamSource && source.getSystemId() == null && ((StreamSource)source).getInputStream() == null && ((StreamSource)source).getReader() == null) || (source instanceof SAXSource && ((SAXSource)source).getInputSource() == null && ((SAXSource)source).getXMLReader() == null) || (source instanceof DOMSource && ((DOMSource)source).getNode() == null)) {
/*      */ 
/*      */       
/*      */       try { 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  296 */         DocumentBuilderFactory builderF = DocumentBuilderFactory.newInstance();
/*  297 */         DocumentBuilder builder = builderF.newDocumentBuilder();
/*  298 */         String systemID = source.getSystemId();
/*  299 */         source = new DOMSource(builder.newDocument());
/*      */ 
/*      */         
/*  302 */         if (systemID != null)
/*  303 */           source.setSystemId(systemID);  } catch (ParserConfigurationException e)
/*      */       
/*      */       { 
/*  306 */         throw new TransformerException(e.getMessage()); }
/*      */     
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  312 */       if (source instanceof DOMSource) {
/*      */         
/*  314 */         DOMSource dsource = (DOMSource)source;
/*      */         
/*  316 */         this.m_systemID = dsource.getSystemId();
/*      */         
/*  318 */         Node dNode = dsource.getNode();
/*      */         
/*  320 */         if (null != dNode) {
/*      */ 
/*      */ 
/*      */           
/*  324 */           try { if (dNode.getNodeType() == 2) {
/*  325 */               startDocument();
/*      */             }
/*      */             
/*  328 */             try { if (dNode.getNodeType() == 2)
/*      */               {
/*  330 */                 String data = dNode.getNodeValue();
/*  331 */                 char[] chars = data.toCharArray();
/*  332 */                 characters(chars, 0, chars.length);
/*      */               }
/*      */               else
/*      */               {
/*  336 */                 TreeWalker walker = new TreeWalker(this, (DOMHelper)new DOM2Helper(), this.m_systemID);
/*  337 */                 walker.traverse(dNode);
/*      */               }
/*      */                }
/*      */             finally
/*      */             
/*  342 */             { if (dNode.getNodeType() == 2)
/*  343 */                 endDocument();  }  } catch (SAXException se)
/*      */           
/*      */           { 
/*      */ 
/*      */             
/*  348 */             throw new TransformerException(se); }
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  355 */         String messageStr = XSLMessages.createMessage("ER_ILLEGAL_DOMSOURCE_INPUT", null);
/*      */ 
/*      */         
/*  358 */         throw new IllegalArgumentException(messageStr);
/*      */       } 
/*      */ 
/*      */       
/*  362 */       InputSource xmlSource = SAXSource.sourceToInputSource(source);
/*      */       
/*  364 */       if (null == xmlSource)
/*      */       {
/*  366 */         throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_TRANSFORM_SOURCE_TYPE", new Object[] { source.getClass().getName() }));
/*      */       }
/*      */ 
/*      */       
/*  370 */       if (null != xmlSource.getSystemId()) {
/*  371 */         this.m_systemID = xmlSource.getSystemId();
/*      */       }
/*  373 */       XMLReader reader = null;
/*  374 */       boolean managedReader = false;
/*      */ 
/*      */ 
/*      */       
/*  378 */       try { if (source instanceof SAXSource) {
/*  379 */           reader = ((SAXSource)source).getXMLReader();
/*      */         }
/*      */         
/*  382 */         if (null == reader) {
/*      */           
/*  384 */           try { reader = XMLReaderManager.getInstance().getXMLReader();
/*  385 */             managedReader = true; } catch (SAXException se)
/*      */           
/*  387 */           { throw new TransformerException(se); }
/*      */         
/*      */         } else {
/*      */           
/*  391 */           try { reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true); } catch (SAXException sAXException) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  400 */         ContentHandler inputHandler = this;
/*      */         
/*  402 */         reader.setContentHandler(inputHandler);
/*      */         
/*  404 */         if (inputHandler instanceof DTDHandler) {
/*  405 */           reader.setDTDHandler((DTDHandler)inputHandler);
/*      */         }
/*      */ 
/*      */         
/*  409 */         try { if (inputHandler instanceof LexicalHandler) {
/*  410 */             reader.setProperty("http://xml.org/sax/properties/lexical-handler", inputHandler);
/*      */           }
/*      */           
/*  413 */           if (inputHandler instanceof DeclHandler)
/*  414 */             reader.setProperty("http://xml.org/sax/properties/declaration-handler", inputHandler);  } catch (SAXException sAXException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  422 */         try { if (inputHandler instanceof LexicalHandler) {
/*  423 */             reader.setProperty("http://xml.org/sax/handlers/LexicalHandler", inputHandler);
/*      */           }
/*      */           
/*  426 */           if (inputHandler instanceof DeclHandler)
/*  427 */             reader.setProperty("http://xml.org/sax/handlers/DeclHandler", inputHandler);  } catch (SAXNotRecognizedException sAXNotRecognizedException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  432 */         reader.parse(xmlSource); } catch (WrappedRuntimeException wre)
/*      */       
/*      */       { 
/*      */         
/*  436 */         Throwable throwable = wre.getException();
/*      */ 
/*      */         
/*  439 */         while (throwable instanceof WrappedRuntimeException)
/*      */         {
/*  441 */           throwable = ((WrappedRuntimeException)throwable).getException();
/*      */         }
/*      */ 
/*      */         
/*  445 */         throw new TransformerException(wre.getException()); } catch (SAXException se)
/*      */       
/*      */       { 
/*      */         
/*  449 */         throw new TransformerException(se); } catch (IOException ioe)
/*      */       
/*      */       { 
/*      */         
/*  453 */         throw new TransformerException(ioe); }
/*      */       finally
/*  455 */       { if (managedReader) {
/*  456 */           XMLReaderManager.getInstance().releaseXMLReader(reader);
/*      */         } }
/*      */ 
/*      */     
/*      */     } finally {
/*      */       
/*  462 */       if (null != this.m_outputStream) {
/*      */ 
/*      */ 
/*      */         
/*  466 */         try { this.m_outputStream.close(); } catch (IOException iOException) {}
/*      */ 
/*      */         
/*  469 */         this.m_outputStream = null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParameter(String name, Object value) {
/*  495 */     if (value == null) {
/*  496 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_SET_PARAM_VALUE", new Object[] { name }));
/*      */     }
/*      */     
/*  499 */     if (null == this.m_params)
/*      */     {
/*  501 */       this.m_params = new Hashtable();
/*      */     }
/*      */     
/*  504 */     this.m_params.put(name, value);
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
/*      */   public Object getParameter(String name) {
/*  522 */     if (null == this.m_params) {
/*  523 */       return null;
/*      */     }
/*  525 */     return this.m_params.get(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearParameters() {
/*  534 */     if (null == this.m_params) {
/*      */       return;
/*      */     }
/*  537 */     this.m_params.clear();
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
/*      */   public void setURIResolver(URIResolver resolver) {
/*  552 */     this.m_URIResolver = resolver;
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
/*      */   public URIResolver getURIResolver() {
/*  564 */     return this.m_URIResolver;
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
/*      */   public void setOutputProperties(Properties oformat) throws IllegalArgumentException {
/*  600 */     if (null != oformat) {
/*      */ 
/*      */ 
/*      */       
/*  604 */       String method = (String)oformat.get("method");
/*      */       
/*  606 */       if (null != method) {
/*  607 */         this.m_outputFormat = new OutputProperties(method);
/*      */       } else {
/*  609 */         this.m_outputFormat = new OutputProperties();
/*      */       } 
/*  611 */       this.m_outputFormat.copyFrom(oformat);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  616 */       this.m_outputFormat = null;
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
/*      */   public Properties getOutputProperties() {
/*  653 */     return (Properties)this.m_outputFormat.getProperties().clone();
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
/*      */   public void setOutputProperty(String name, String value) throws IllegalArgumentException {
/*  686 */     this; if (!OutputProperties.isLegalPropertyKey(name)) {
/*  687 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { name }));
/*      */     }
/*      */     
/*  690 */     this.m_outputFormat.setProperty(name, value);
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
/*      */   public String getOutputProperty(String name) throws IllegalArgumentException {
/*  712 */     String value = null;
/*  713 */     OutputProperties props = this.m_outputFormat;
/*      */     
/*  715 */     value = props.getProperty(name);
/*      */     
/*  717 */     if (null == value)
/*      */     {
/*  719 */       if (!OutputProperties.isLegalPropertyKey(name)) {
/*  720 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { name }));
/*      */       }
/*      */     }
/*      */     
/*  724 */     return value;
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
/*      */   public void setErrorListener(ErrorListener listener) throws IllegalArgumentException {
/*  736 */     if (listener == null) {
/*  737 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_NULL_ERROR_HANDLER", null));
/*      */     }
/*  739 */     this.m_errorListener = listener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorListener getErrorListener() {
/*  749 */     return this.m_errorListener;
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
/*      */   public void notationDecl(String name, String publicId, String systemId) throws SAXException {
/*  776 */     if (null != this.m_resultDTDHandler) {
/*  777 */       this.m_resultDTDHandler.notationDecl(name, publicId, systemId);
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
/*      */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
/*  803 */     if (null != this.m_resultDTDHandler) {
/*  804 */       this.m_resultDTDHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
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
/*      */   public void setDocumentLocator(Locator locator) {
/*      */     
/*  827 */     try { if (null == this.m_resultContentHandler)
/*  828 */         createResultContentHandler(this.m_result);  } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/*  832 */       throw new WrappedRuntimeException(te); }
/*      */ 
/*      */     
/*  835 */     this.m_resultContentHandler.setDocumentLocator(locator);
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
/*      */   public void startDocument() throws SAXException {
/*      */     
/*  857 */     try { if (null == this.m_resultContentHandler)
/*  858 */         createResultContentHandler(this.m_result);  } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/*  862 */       throw new SAXException(te.getMessage(), te); }
/*      */ 
/*      */ 
/*      */     
/*  866 */     this.m_flushedStartDoc = false;
/*  867 */     this.m_foundFirstElement = false;
/*      */   }
/*      */ 
/*      */   
/*      */   boolean m_flushedStartDoc = false;
/*      */ 
/*      */   
/*      */   protected final void flushStartDoc() throws SAXException {
/*  875 */     if (!this.m_flushedStartDoc) {
/*      */       
/*  877 */       if (this.m_resultContentHandler == null) {
/*      */ 
/*      */         
/*      */         try { 
/*  881 */           createResultContentHandler(this.m_result); } catch (TransformerException te)
/*      */         
/*      */         { 
/*      */           
/*  885 */           throw new SAXException(te); }
/*      */       
/*      */       }
/*  888 */       this.m_resultContentHandler.startDocument();
/*  889 */       this.m_flushedStartDoc = true;
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
/*      */   public void endDocument() throws SAXException {
/*  909 */     flushStartDoc();
/*  910 */     this.m_resultContentHandler.endDocument();
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
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  931 */     flushStartDoc();
/*  932 */     this.m_resultContentHandler.startPrefixMapping(prefix, uri);
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
/*      */   public void endPrefixMapping(String prefix) throws SAXException {
/*  951 */     flushStartDoc();
/*  952 */     this.m_resultContentHandler.endPrefixMapping(prefix);
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
/*      */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*  983 */     if (!this.m_foundFirstElement && null != this.m_serializer) {
/*      */       Serializer newSerializer;
/*  985 */       this.m_foundFirstElement = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  991 */       try { newSerializer = SerializerSwitcher.switchSerializerIfHTML(uri, localName, this.m_outputFormat.getProperties(), this.m_serializer); } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/*  996 */         throw new SAXException(te); }
/*      */ 
/*      */       
/*  999 */       if (newSerializer != this.m_serializer) {
/*      */ 
/*      */ 
/*      */         
/* 1003 */         try { this.m_resultContentHandler = newSerializer.asContentHandler(); } catch (IOException ioe)
/*      */         
/*      */         { 
/*      */           
/* 1007 */           throw new SAXException(ioe); }
/*      */ 
/*      */         
/* 1010 */         if (this.m_resultContentHandler instanceof DTDHandler) {
/* 1011 */           this.m_resultDTDHandler = (DTDHandler)this.m_resultContentHandler;
/*      */         }
/* 1013 */         if (this.m_resultContentHandler instanceof LexicalHandler) {
/* 1014 */           this.m_resultLexicalHandler = (LexicalHandler)this.m_resultContentHandler;
/*      */         }
/* 1016 */         this.m_serializer = newSerializer;
/*      */       } 
/*      */     } 
/* 1019 */     flushStartDoc();
/* 1020 */     this.m_resultContentHandler.startElement(uri, localName, qName, attributes);
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
/*      */   public void endElement(String uri, String localName, String qName) throws SAXException {
/* 1050 */     this.m_resultContentHandler.endElement(uri, localName, qName);
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
/*      */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 1073 */     flushStartDoc();
/* 1074 */     this.m_resultContentHandler.characters(ch, start, length);
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
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 1098 */     this.m_resultContentHandler.ignorableWhitespace(ch, start, length);
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
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/* 1121 */     flushStartDoc();
/* 1122 */     this.m_resultContentHandler.processingInstruction(target, data);
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
/*      */   public void skippedEntity(String name) throws SAXException {
/* 1142 */     flushStartDoc();
/* 1143 */     this.m_resultContentHandler.skippedEntity(name);
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
/*      */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/* 1170 */     flushStartDoc();
/* 1171 */     if (null != this.m_resultLexicalHandler) {
/* 1172 */       this.m_resultLexicalHandler.startDTD(name, publicId, systemId);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDTD() throws SAXException {
/* 1183 */     if (null != this.m_resultLexicalHandler) {
/* 1184 */       this.m_resultLexicalHandler.endDTD();
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
/*      */   public void startEntity(String name) throws SAXException {
/* 1211 */     if (null != this.m_resultLexicalHandler) {
/* 1212 */       this.m_resultLexicalHandler.startEntity(name);
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
/*      */   public void endEntity(String name) throws SAXException {
/* 1224 */     if (null != this.m_resultLexicalHandler) {
/* 1225 */       this.m_resultLexicalHandler.endEntity(name);
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
/*      */   public void startCDATA() throws SAXException {
/* 1240 */     if (null != this.m_resultLexicalHandler) {
/* 1241 */       this.m_resultLexicalHandler.startCDATA();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endCDATA() throws SAXException {
/* 1252 */     if (null != this.m_resultLexicalHandler) {
/* 1253 */       this.m_resultLexicalHandler.endCDATA();
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
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 1270 */     flushStartDoc();
/* 1271 */     if (null != this.m_resultLexicalHandler) {
/* 1272 */       this.m_resultLexicalHandler.comment(ch, start, length);
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
/*      */   public void elementDecl(String name, String model) throws SAXException {
/* 1293 */     if (null != this.m_resultDeclHandler) {
/* 1294 */       this.m_resultDeclHandler.elementDecl(name, model);
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
/*      */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {
/* 1324 */     if (null != this.m_resultDeclHandler) {
/* 1325 */       this.m_resultDeclHandler.attributeDecl(eName, aName, type, valueDefault, value);
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
/*      */   public void internalEntityDecl(String name, String value) throws SAXException {
/* 1345 */     if (null != this.m_resultDeclHandler) {
/* 1346 */       this.m_resultDeclHandler.internalEntityDecl(name, value);
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
/* 1369 */     if (null != this.m_resultDeclHandler) {
/* 1370 */       this.m_resultDeclHandler.externalEntityDecl(name, publicId, systemId);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1376 */   private FileOutputStream m_outputStream = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1409 */   private ErrorListener m_errorListener = (ErrorListener)new DefaultErrorHandler();
/*      */   private ContentHandler m_resultContentHandler;
/*      */   private LexicalHandler m_resultLexicalHandler;
/*      */   private DTDHandler m_resultDTDHandler;
/*      */   private DeclHandler m_resultDeclHandler;
/*      */   private Serializer m_serializer;
/*      */   private Result m_result;
/*      */   private String m_systemID;
/*      */   private Hashtable m_params;
/*      */   URIResolver m_URIResolver;
/*      */   boolean m_foundFirstElement;
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/TransformerIdentityImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */