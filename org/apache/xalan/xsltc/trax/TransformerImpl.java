/*      */ package org.apache.xalan.xsltc.trax;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.net.UnknownServiceException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
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
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import javax.xml.transform.stream.StreamSource;
/*      */ import org.apache.xalan.xsltc.DOM;
/*      */ import org.apache.xalan.xsltc.DOMCache;
/*      */ import org.apache.xalan.xsltc.Translet;
/*      */ import org.apache.xalan.xsltc.TransletException;
/*      */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*      */ import org.apache.xalan.xsltc.dom.DOMWSFilter;
/*      */ import org.apache.xalan.xsltc.dom.SAXImpl;
/*      */ import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
/*      */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*      */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*      */ import org.apache.xalan.xsltc.runtime.output.TransletOutputHandlerFactory;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.serializer.OutputPropertiesFactory;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.utils.SystemIDResolver;
/*      */ import org.apache.xml.utils.XMLReaderManager;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.XMLReader;
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
/*      */ public final class TransformerImpl
/*      */   extends Transformer
/*      */   implements ErrorListener, DOMCache
/*      */ {
/*      */   private static final String EMPTY_STRING = "";
/*      */   private static final String NO_STRING = "no";
/*      */   private static final String YES_STRING = "yes";
/*      */   private static final String XML_STRING = "xml";
/*      */   private static final String LEXICAL_HANDLER_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
/*      */   private static final String NAMESPACE_FEATURE = "http://xml.org/sax/features/namespaces";
/*  102 */   private AbstractTranslet _translet = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   private String _method = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   private String _encoding = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   private String _sourceSystemId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   private ErrorListener _errorListener = this;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   private URIResolver _uriResolver = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private Properties _properties;
/*      */ 
/*      */   
/*      */   private Properties _propertiesClone;
/*      */ 
/*      */   
/*  137 */   private TransletOutputHandlerFactory _tohFactory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  142 */   private DOM _dom = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int _indentNumber;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  153 */   private TransformerFactoryImpl _tfactory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  159 */   private XSLTCDTMManager _dtmManager = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  164 */   private XMLReaderManager _readerManager = XMLReaderManager.getInstance();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _isIdentity = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  182 */   private Hashtable _parameters = null;
/*      */ 
/*      */ 
/*      */   
/*      */   static class MessageHandler
/*      */     extends org.apache.xalan.xsltc.runtime.MessageHandler
/*      */   {
/*      */     private ErrorListener _errorListener;
/*      */ 
/*      */ 
/*      */     
/*      */     public MessageHandler(ErrorListener errorListener) {
/*  194 */       this._errorListener = errorListener;
/*      */     }
/*      */     
/*      */     public void displayMessage(String msg) {
/*  198 */       if (this._errorListener == null) {
/*  199 */         System.err.println(msg);
/*      */       } else {
/*      */ 
/*      */         
/*  203 */         try { this._errorListener.warning(new TransformerException(msg)); } catch (TransformerException transformerException) {}
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
/*      */   protected TransformerImpl(Properties outputProperties, int indentNumber, TransformerFactoryImpl tfactory) {
/*  215 */     this(null, outputProperties, indentNumber, tfactory);
/*  216 */     this._isIdentity = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TransformerImpl(Translet translet, Properties outputProperties, int indentNumber, TransformerFactoryImpl tfactory) {
/*  223 */     this._translet = (AbstractTranslet)translet;
/*  224 */     this._properties = createOutputProperties(outputProperties);
/*  225 */     this._propertiesClone = (Properties)this._properties.clone();
/*  226 */     this._indentNumber = indentNumber;
/*  227 */     this._tfactory = tfactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractTranslet getTranslet() {
/*  236 */     return this._translet;
/*      */   }
/*      */   
/*      */   public boolean isIdentity() {
/*  240 */     return this._isIdentity;
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
/*      */   public void transform(Source source, Result result) throws TransformerException {
/*  253 */     if (!this._isIdentity) {
/*  254 */       if (this._translet == null) {
/*  255 */         ErrorMsg err = new ErrorMsg("JAXP_NO_TRANSLET_ERR");
/*  256 */         throw new TransformerException(err.toString());
/*      */       } 
/*      */       
/*  259 */       transferOutputProperties(this._translet);
/*      */     } 
/*      */     
/*  262 */     SerializationHandler toHandler = getOutputHandler(result);
/*  263 */     if (toHandler == null) {
/*  264 */       ErrorMsg err = new ErrorMsg("JAXP_NO_HANDLER_ERR");
/*  265 */       throw new TransformerException(err.toString());
/*      */     } 
/*      */     
/*  268 */     if (this._uriResolver != null && !this._isIdentity) {
/*  269 */       this._translet.setDOMCache(this);
/*      */     }
/*      */ 
/*      */     
/*  273 */     if (this._isIdentity) {
/*  274 */       transferOutputProperties(toHandler);
/*      */     }
/*      */     
/*  277 */     transform(source, toHandler, this._encoding);
/*      */     
/*  279 */     if (result instanceof DOMResult) {
/*  280 */       ((DOMResult)result).setNode(this._tohFactory.getNode());
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
/*      */   public SerializationHandler getOutputHandler(Result result) throws TransformerException {
/*  293 */     this._method = (String)this._properties.get("method");
/*      */ 
/*      */     
/*  296 */     this._encoding = this._properties.getProperty("encoding");
/*      */     
/*  298 */     this._tohFactory = TransletOutputHandlerFactory.newInstance();
/*  299 */     this._tohFactory.setEncoding(this._encoding);
/*  300 */     if (this._method != null) {
/*  301 */       this._tohFactory.setOutputMethod(this._method);
/*      */     }
/*      */ 
/*      */     
/*  305 */     if (this._indentNumber >= 0) {
/*  306 */       this._tohFactory.setIndentNumber(this._indentNumber);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  312 */     try { if (result instanceof SAXResult) {
/*  313 */         SAXResult target = (SAXResult)result;
/*  314 */         ContentHandler handler = target.getHandler();
/*      */         
/*  316 */         this._tohFactory.setHandler(handler);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  323 */         LexicalHandler lexicalHandler = target.getLexicalHandler();
/*      */         
/*  325 */         if (lexicalHandler != null) {
/*  326 */           this._tohFactory.setLexicalHandler(lexicalHandler);
/*      */         }
/*      */         
/*  329 */         this._tohFactory.setOutputType(1);
/*  330 */         return this._tohFactory.getSerializationHandler();
/*      */       } 
/*  332 */       if (result instanceof DOMResult) {
/*  333 */         this._tohFactory.setNode(((DOMResult)result).getNode());
/*  334 */         this._tohFactory.setOutputType(2);
/*  335 */         return this._tohFactory.getSerializationHandler();
/*      */       } 
/*  337 */       if (result instanceof StreamResult)
/*      */       
/*  339 */       { StreamResult target = (StreamResult)result;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  345 */         this._tohFactory.setOutputType(0);
/*      */ 
/*      */         
/*  348 */         Writer writer = target.getWriter();
/*  349 */         if (writer != null) {
/*  350 */           this._tohFactory.setWriter(writer);
/*  351 */           return this._tohFactory.getSerializationHandler();
/*      */         } 
/*      */ 
/*      */         
/*  355 */         OutputStream ostream = target.getOutputStream();
/*  356 */         if (ostream != null) {
/*  357 */           this._tohFactory.setOutputStream(ostream);
/*  358 */           return this._tohFactory.getSerializationHandler();
/*      */         } 
/*      */ 
/*      */         
/*  362 */         String systemId = result.getSystemId();
/*  363 */         if (systemId == null) {
/*  364 */           ErrorMsg err = new ErrorMsg("JAXP_NO_RESULT_ERR");
/*  365 */           throw new TransformerException(err.toString());
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  371 */         URL url = null;
/*  372 */         if (systemId.startsWith("file:")) {
/*  373 */           url = new URL(systemId);
/*  374 */           this._tohFactory.setOutputStream(new FileOutputStream(url.getFile()));
/*      */           
/*  376 */           return this._tohFactory.getSerializationHandler();
/*      */         } 
/*  378 */         if (systemId.startsWith("http:")) {
/*  379 */           url = new URL(systemId);
/*  380 */           URLConnection connection = url.openConnection();
/*  381 */           this._tohFactory.setOutputStream(connection.getOutputStream());
/*  382 */           return this._tohFactory.getSerializationHandler();
/*      */         } 
/*      */ 
/*      */         
/*  386 */         url = (new File(systemId)).toURL();
/*  387 */         this._tohFactory.setOutputStream(new FileOutputStream(url.getFile()));
/*      */         
/*  389 */         return this._tohFactory.getSerializationHandler(); }  } catch (UnknownServiceException e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/*  395 */       throw new TransformerException(e); } catch (ParserConfigurationException e)
/*      */     
/*      */     { 
/*  398 */       throw new TransformerException(e); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/*  402 */       throw new TransformerException(e); }
/*      */     
/*  404 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setDOM(DOM dom) {
/*  411 */     this._dom = dom;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private DOM getDOM(Source source) throws TransformerException {
/*      */     
/*  419 */     try { DOM dom = null;
/*      */       
/*  421 */       if (source != null) {
/*      */         DTMWSFilter dTMWSFilter;
/*  423 */         if (this._translet != null && this._translet instanceof org.apache.xalan.xsltc.StripFilter) {
/*  424 */           dTMWSFilter = (DTMWSFilter)new DOMWSFilter(this._translet);
/*      */         } else {
/*  426 */           dTMWSFilter = null;
/*      */         } 
/*      */         
/*  429 */         boolean hasIdCall = (this._translet != null) ? this._translet.hasIdCall() : false;
/*      */ 
/*      */         
/*  432 */         if (this._dtmManager == null) {
/*  433 */           this._dtmManager = this._tfactory.getDTMManagerClass().newInstance();
/*      */         }
/*      */ 
/*      */         
/*  437 */         dom = (DOM)this._dtmManager.getDTM(source, false, dTMWSFilter, true, false, false, 0, hasIdCall);
/*      */       }
/*  439 */       else if (this._dom != null) {
/*  440 */         dom = this._dom;
/*  441 */         this._dom = null;
/*      */       } else {
/*  443 */         return null;
/*      */       } 
/*      */       
/*  446 */       if (!this._isIdentity)
/*      */       {
/*      */         
/*  449 */         this._translet.prepassDocument(dom);
/*      */       }
/*      */       
/*  452 */       return dom; } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  456 */       if (this._errorListener != null) {
/*  457 */         postErrorToListener(e.getMessage());
/*      */       }
/*  459 */       throw new TransformerException(e); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TransformerFactoryImpl getTransformerFactory() {
/*  468 */     return this._tfactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void transformIdentity(Source source, SerializationHandler handler) throws Exception {
/*  475 */     if (source != null) {
/*  476 */       this._sourceSystemId = source.getSystemId();
/*      */     }
/*      */     
/*  479 */     if (source instanceof StreamSource) {
/*  480 */       StreamSource stream = (StreamSource)source;
/*  481 */       InputStream streamInput = stream.getInputStream();
/*  482 */       Reader streamReader = stream.getReader();
/*  483 */       XMLReader reader = this._readerManager.getXMLReader();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  488 */       try { reader.setProperty("http://xml.org/sax/properties/lexical-handler", handler); } catch (SAXException sAXException)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */          }
/*      */       
/*      */       finally
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  516 */         this._readerManager.releaseXMLReader(reader); }
/*      */     
/*  518 */     } else if (source instanceof SAXSource) {
/*  519 */       SAXSource sax = (SAXSource)source;
/*  520 */       XMLReader reader = sax.getXMLReader();
/*  521 */       InputSource input = sax.getInputSource();
/*  522 */       boolean userReader = true;
/*      */ 
/*      */       
/*      */       try {
/*  526 */         if (reader == null) {
/*  527 */           reader = this._readerManager.getXMLReader();
/*  528 */           userReader = false;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  533 */         try { reader.setProperty("http://xml.org/sax/properties/lexical-handler", handler); } catch (SAXException inputSource) {}
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  538 */         reader.setContentHandler((ContentHandler)handler);
/*      */ 
/*      */         
/*  541 */         reader.parse(input);
/*      */       } finally {
/*  543 */         if (!userReader) {
/*  544 */           this._readerManager.releaseXMLReader(reader);
/*      */         }
/*      */       } 
/*  547 */     } else if (source instanceof DOMSource) {
/*  548 */       DOMSource domsrc = (DOMSource)source;
/*  549 */       (new DOM2TO(domsrc.getNode(), handler)).parse();
/*  550 */     } else if (source instanceof XSLTCSource) {
/*  551 */       DOM dom = ((XSLTCSource)source).getDOM(null, this._translet);
/*  552 */       ((SAXImpl)dom).copy(handler);
/*      */     } else {
/*  554 */       ErrorMsg err = new ErrorMsg("JAXP_NO_SOURCE_ERR");
/*  555 */       throw new TransformerException(err.toString());
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
/*      */   private void transform(Source source, SerializationHandler handler, String encoding) throws TransformerException {
/*      */     
/*  574 */     try { if ((source instanceof StreamSource && source.getSystemId() == null && ((StreamSource)source).getInputStream() == null && ((StreamSource)source).getReader() == null) || (source instanceof SAXSource && ((SAXSource)source).getInputSource() == null && ((SAXSource)source).getXMLReader() == null) || (source instanceof DOMSource && ((DOMSource)source).getNode() == null)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  582 */         DocumentBuilderFactory builderF = DocumentBuilderFactory.newInstance();
/*      */         
/*  584 */         DocumentBuilder builder = builderF.newDocumentBuilder();
/*      */         
/*  586 */         String systemID = source.getSystemId();
/*  587 */         source = new DOMSource(builder.newDocument());
/*      */ 
/*      */         
/*  590 */         if (systemID != null) {
/*  591 */           source.setSystemId(systemID);
/*      */         }
/*      */       } 
/*  594 */       if (this._isIdentity)
/*  595 */       { transformIdentity(source, handler); }
/*      */       else
/*  597 */       { this._translet.transform(getDOM(source), handler); }  } catch (TransletException e)
/*      */     
/*      */     { 
/*  600 */       if (this._errorListener != null) postErrorToListener(e.getMessage()); 
/*  601 */       throw new TransformerException(e); } catch (RuntimeException e)
/*      */     
/*  603 */     { if (this._errorListener != null) postErrorToListener(e.getMessage()); 
/*  604 */       throw new TransformerException(e); } catch (Exception e)
/*      */     
/*  606 */     { if (this._errorListener != null) postErrorToListener(e.getMessage()); 
/*  607 */       throw new TransformerException(e); }
/*      */     finally
/*  609 */     { this._dtmManager = null; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorListener getErrorListener() {
/*  620 */     return this._errorListener;
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
/*      */   public void setErrorListener(ErrorListener listener) throws IllegalArgumentException {
/*  634 */     if (listener == null) {
/*  635 */       ErrorMsg err = new ErrorMsg("ERROR_LISTENER_NULL_ERR", "Transformer");
/*      */       
/*  637 */       throw new IllegalArgumentException(err.toString());
/*      */     } 
/*  639 */     this._errorListener = listener;
/*      */ 
/*      */     
/*  642 */     if (this._translet != null) {
/*  643 */       this._translet.setMessageHandler(new MessageHandler(this._errorListener));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void postErrorToListener(String message) {
/*      */     
/*  651 */     try { this._errorListener.error(new TransformerException(message)); } catch (TransformerException transformerException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void postWarningToListener(String message) {
/*      */     
/*  663 */     try { this._errorListener.warning(new TransformerException(message)); } catch (TransformerException transformerException) {}
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
/*      */   private String makeCDATAString(Hashtable cdata) {
/*  677 */     if (cdata == null) return null;
/*      */     
/*  679 */     StringBuffer result = new StringBuffer();
/*      */ 
/*      */     
/*  682 */     Enumeration elements = cdata.keys();
/*  683 */     if (elements.hasMoreElements()) {
/*  684 */       result.append(elements.nextElement());
/*  685 */       while (elements.hasMoreElements()) {
/*  686 */         String element = elements.nextElement();
/*  687 */         result.append(' ');
/*  688 */         result.append(element);
/*      */       } 
/*      */     } 
/*      */     
/*  692 */     return result.toString();
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
/*      */   public Properties getOutputProperties() {
/*  707 */     return (Properties)this._properties.clone();
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
/*      */   public String getOutputProperty(String name) throws IllegalArgumentException {
/*  722 */     if (!validOutputProperty(name)) {
/*  723 */       ErrorMsg err = new ErrorMsg("JAXP_UNKNOWN_PROP_ERR", name);
/*  724 */       throw new IllegalArgumentException(err.toString());
/*      */     } 
/*  726 */     return this._properties.getProperty(name);
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
/*      */   public void setOutputProperties(Properties properties) throws IllegalArgumentException {
/*  741 */     if (properties != null) {
/*  742 */       Enumeration names = properties.propertyNames();
/*      */       
/*  744 */       while (names.hasMoreElements()) {
/*  745 */         String name = (String)names.nextElement();
/*      */ 
/*      */         
/*  748 */         if (isDefaultProperty(name, properties))
/*      */           continue; 
/*  750 */         if (validOutputProperty(name)) {
/*  751 */           this._properties.setProperty(name, properties.getProperty(name));
/*      */           continue;
/*      */         } 
/*  754 */         ErrorMsg err = new ErrorMsg("JAXP_UNKNOWN_PROP_ERR", name);
/*  755 */         throw new IllegalArgumentException(err.toString());
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  760 */       this._properties = this._propertiesClone;
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
/*      */   public void setOutputProperty(String name, String value) throws IllegalArgumentException {
/*  777 */     if (!validOutputProperty(name)) {
/*  778 */       ErrorMsg err = new ErrorMsg("JAXP_UNKNOWN_PROP_ERR", name);
/*  779 */       throw new IllegalArgumentException(err.toString());
/*      */     } 
/*  781 */     this._properties.setProperty(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void transferOutputProperties(AbstractTranslet translet) {
/*  791 */     if (this._properties == null) {
/*      */       return;
/*      */     }
/*  794 */     Enumeration names = this._properties.propertyNames();
/*  795 */     while (names.hasMoreElements()) {
/*      */       
/*  797 */       String name = (String)names.nextElement();
/*  798 */       String value = (String)this._properties.get(name);
/*      */ 
/*      */       
/*  801 */       if (value == null) {
/*      */         continue;
/*      */       }
/*  804 */       if (name.equals("encoding")) {
/*  805 */         translet._encoding = value; continue;
/*      */       } 
/*  807 */       if (name.equals("method")) {
/*  808 */         translet._method = value; continue;
/*      */       } 
/*  810 */       if (name.equals("doctype-public")) {
/*  811 */         translet._doctypePublic = value; continue;
/*      */       } 
/*  813 */       if (name.equals("doctype-system")) {
/*  814 */         translet._doctypeSystem = value; continue;
/*      */       } 
/*  816 */       if (name.equals("media-type")) {
/*  817 */         translet._mediaType = value; continue;
/*      */       } 
/*  819 */       if (name.equals("standalone")) {
/*  820 */         translet._standalone = value; continue;
/*      */       } 
/*  822 */       if (name.equals("version")) {
/*  823 */         translet._version = value; continue;
/*      */       } 
/*  825 */       if (name.equals("omit-xml-declaration")) {
/*  826 */         translet._omitHeader = (value != null && value.toLowerCase().equals("yes"));
/*      */         continue;
/*      */       } 
/*  829 */       if (name.equals("indent")) {
/*  830 */         translet._indent = (value != null && value.toLowerCase().equals("yes"));
/*      */         continue;
/*      */       } 
/*  833 */       if (name.equals("cdata-section-elements") && 
/*  834 */         value != null) {
/*  835 */         translet._cdata = null;
/*  836 */         StringTokenizer e = new StringTokenizer(value);
/*  837 */         while (e.hasMoreTokens()) {
/*  838 */           translet.addCdataElement(e.nextToken());
/*      */         }
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
/*      */   public void transferOutputProperties(SerializationHandler handler) {
/*  852 */     if (this._properties == null)
/*      */       return; 
/*  854 */     String doctypePublic = null;
/*  855 */     String doctypeSystem = null;
/*      */ 
/*      */     
/*  858 */     Enumeration names = this._properties.propertyNames();
/*  859 */     while (names.hasMoreElements()) {
/*      */       
/*  861 */       String name = (String)names.nextElement();
/*  862 */       String value = (String)this._properties.get(name);
/*      */ 
/*      */       
/*  865 */       if (value == null) {
/*      */         continue;
/*      */       }
/*  868 */       if (name.equals("doctype-public")) {
/*  869 */         doctypePublic = value; continue;
/*      */       } 
/*  871 */       if (name.equals("doctype-system")) {
/*  872 */         doctypeSystem = value; continue;
/*      */       } 
/*  874 */       if (name.equals("media-type")) {
/*  875 */         handler.setMediaType(value); continue;
/*      */       } 
/*  877 */       if (name.equals("standalone")) {
/*  878 */         handler.setStandalone(value); continue;
/*      */       } 
/*  880 */       if (name.equals("version")) {
/*  881 */         handler.setVersion(value); continue;
/*      */       } 
/*  883 */       if (name.equals("omit-xml-declaration")) {
/*  884 */         handler.setOmitXMLDeclaration((value != null && value.toLowerCase().equals("yes")));
/*      */         continue;
/*      */       } 
/*  887 */       if (name.equals("indent")) {
/*  888 */         handler.setIndent((value != null && value.toLowerCase().equals("yes")));
/*      */         continue;
/*      */       } 
/*  891 */       if (name.equals("cdata-section-elements") && 
/*  892 */         value != null) {
/*  893 */         StringTokenizer e = new StringTokenizer(value);
/*  894 */         Vector uriAndLocalNames = null;
/*  895 */         while (e.hasMoreTokens()) {
/*  896 */           Object object; String str1, token = e.nextToken();
/*      */ 
/*      */ 
/*      */           
/*  900 */           int lastcolon = token.lastIndexOf(':');
/*      */ 
/*      */           
/*  903 */           if (lastcolon > 0) {
/*  904 */             object = token.substring(0, lastcolon);
/*  905 */             str1 = token.substring(lastcolon + 1);
/*      */           }
/*      */           else {
/*      */             
/*  909 */             object = null;
/*  910 */             str1 = token;
/*      */           } 
/*      */           
/*  913 */           if (uriAndLocalNames == null) {
/*  914 */             uriAndLocalNames = new Vector();
/*      */           }
/*      */           
/*  917 */           uriAndLocalNames.addElement(object);
/*  918 */           uriAndLocalNames.addElement(str1);
/*      */         } 
/*  920 */         handler.setCdataSectionElements(uriAndLocalNames);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  926 */     if (doctypePublic != null || doctypeSystem != null) {
/*  927 */       handler.setDoctype(doctypeSystem, doctypePublic);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Properties createOutputProperties(Properties outputProperties) {
/*  938 */     Properties defaults = new Properties();
/*  939 */     setDefaults(defaults, "xml");
/*      */ 
/*      */     
/*  942 */     Properties base = new Properties(defaults);
/*  943 */     if (outputProperties != null) {
/*  944 */       Enumeration names = outputProperties.propertyNames();
/*  945 */       while (names.hasMoreElements()) {
/*  946 */         String name = (String)names.nextElement();
/*  947 */         base.setProperty(name, outputProperties.getProperty(name));
/*      */       } 
/*      */     } else {
/*      */       
/*  951 */       base.setProperty("encoding", this._translet._encoding);
/*  952 */       if (this._translet._method != null) {
/*  953 */         base.setProperty("method", this._translet._method);
/*      */       }
/*      */     } 
/*      */     
/*  957 */     String method = base.getProperty("method");
/*  958 */     if (method != null) {
/*  959 */       if (method.equals("html")) {
/*  960 */         setDefaults(defaults, "html");
/*      */       }
/*  962 */       else if (method.equals("text")) {
/*  963 */         setDefaults(defaults, "text");
/*      */       } 
/*      */     }
/*      */     
/*  967 */     return base;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setDefaults(Properties props, String method) {
/*  978 */     Properties method_props = OutputPropertiesFactory.getDefaultMethodProperties(method);
/*      */ 
/*      */     
/*  981 */     Enumeration names = method_props.propertyNames();
/*  982 */     while (names.hasMoreElements()) {
/*      */       
/*  984 */       String name = (String)names.nextElement();
/*  985 */       props.setProperty(name, method_props.getProperty(name));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean validOutputProperty(String name) {
/*  994 */     return (name.equals("encoding") || name.equals("method") || name.equals("indent") || name.equals("doctype-public") || name.equals("doctype-system") || name.equals("cdata-section-elements") || name.equals("media-type") || name.equals("omit-xml-declaration") || name.equals("standalone") || name.equals("version") || name.charAt(0) == '{');
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
/*      */   private boolean isDefaultProperty(String name, Properties properties) {
/* 1011 */     return (properties.get(name) == null);
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
/*      */   public void setParameter(String name, Object value) {
/* 1025 */     if (value == null) {
/* 1026 */       ErrorMsg err = new ErrorMsg("JAXP_INVALID_SET_PARAM_VALUE", name);
/* 1027 */       throw new IllegalArgumentException(err.toString());
/*      */     } 
/*      */     
/* 1030 */     if (this._isIdentity) {
/* 1031 */       if (this._parameters == null) {
/* 1032 */         this._parameters = new Hashtable();
/*      */       }
/* 1034 */       this._parameters.put(name, value);
/*      */     } else {
/*      */       
/* 1037 */       this._translet.addParameter(name, value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearParameters() {
/* 1047 */     if (this._isIdentity && this._parameters != null) {
/* 1048 */       this._parameters.clear();
/*      */     } else {
/*      */       
/* 1051 */       this._translet.clearParameters();
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
/*      */   public final Object getParameter(String name) {
/* 1064 */     if (this._isIdentity) {
/* 1065 */       return (this._parameters != null) ? this._parameters.get(name) : null;
/*      */     }
/*      */     
/* 1068 */     return this._translet.getParameter(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URIResolver getURIResolver() {
/* 1079 */     return this._uriResolver;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setURIResolver(URIResolver resolver) {
/* 1089 */     this._uriResolver = resolver;
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
/*      */   public DOM retrieveDocument(String baseURI, String href, Translet translet) {
/*      */     
/* 1109 */     try { if (href.length() == 0) {
/* 1110 */         href = new String(baseURI);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1121 */       Source resolvedSource = this._uriResolver.resolve(href, baseURI);
/* 1122 */       if (resolvedSource == null) {
/* 1123 */         StreamSource streamSource = new StreamSource(SystemIDResolver.getAbsoluteURI(href, baseURI));
/*      */         
/* 1125 */         return getDOM(streamSource);
/*      */       } 
/*      */       
/* 1128 */       return getDOM(resolvedSource); } catch (TransformerException e)
/*      */     
/*      */     { 
/* 1131 */       if (this._errorListener != null)
/* 1132 */         postErrorToListener("File not found: " + e.getMessage()); 
/* 1133 */       return null; }
/*      */   
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
/*      */   public void error(TransformerException e) throws TransformerException {
/* 1151 */     Throwable wrapped = e.getException();
/* 1152 */     if (wrapped != null) {
/* 1153 */       System.err.println(new ErrorMsg("ERROR_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
/*      */     }
/*      */     else {
/*      */       
/* 1157 */       System.err.println(new ErrorMsg("ERROR_MSG", e.getMessageAndLocation()));
/*      */     } 
/*      */     
/* 1160 */     throw e;
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
/*      */   public void fatalError(TransformerException e) throws TransformerException {
/* 1179 */     Throwable wrapped = e.getException();
/* 1180 */     if (wrapped != null) {
/* 1181 */       System.err.println(new ErrorMsg("FATAL_ERR_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
/*      */     }
/*      */     else {
/*      */       
/* 1185 */       System.err.println(new ErrorMsg("FATAL_ERR_MSG", e.getMessageAndLocation()));
/*      */     } 
/*      */     
/* 1188 */     throw e;
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
/*      */   public void warning(TransformerException e) throws TransformerException {
/* 1207 */     Throwable wrapped = e.getException();
/* 1208 */     if (wrapped != null) {
/* 1209 */       System.err.println(new ErrorMsg("WARNING_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
/*      */     }
/*      */     else {
/*      */       
/* 1213 */       System.err.println(new ErrorMsg("WARNING_MSG", e.getMessageAndLocation()));
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/TransformerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */