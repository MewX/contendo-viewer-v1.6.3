/*      */ package org.apache.xalan.xsltc.trax;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Properties;
/*      */ import java.util.Vector;
/*      */ import java.util.zip.ZipEntry;
/*      */ import java.util.zip.ZipFile;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.parsers.SAXParser;
/*      */ import javax.xml.parsers.SAXParserFactory;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.Templates;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerConfigurationException;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.URIResolver;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.sax.SAXSource;
/*      */ import javax.xml.transform.sax.SAXTransformerFactory;
/*      */ import javax.xml.transform.sax.TemplatesHandler;
/*      */ import javax.xml.transform.sax.TransformerHandler;
/*      */ import org.apache.xalan.xsltc.compiler.SourceLoader;
/*      */ import org.apache.xalan.xsltc.compiler.XSLTC;
/*      */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*      */ import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
/*      */ import org.apache.xml.utils.StopParseException;
/*      */ import org.apache.xml.utils.StylesheetPIHandler;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.XMLFilter;
/*      */ import org.xml.sax.XMLReader;
/*      */ import org.xml.sax.helpers.XMLReaderFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TransformerFactoryImpl
/*      */   extends SAXTransformerFactory
/*      */   implements ErrorListener, SourceLoader
/*      */ {
/*      */   public static final String TRANSLET_NAME = "translet-name";
/*      */   public static final String DESTINATION_DIRECTORY = "destination-directory";
/*      */   public static final String PACKAGE_NAME = "package-name";
/*      */   public static final String JAR_NAME = "jar-name";
/*      */   public static final String GENERATE_TRANSLET = "generate-translet";
/*      */   public static final String AUTO_TRANSLET = "auto-translet";
/*      */   public static final String USE_CLASSPATH = "use-classpath";
/*      */   public static final String DEBUG = "debug";
/*      */   public static final String ENABLE_INLINING = "enable-inlining";
/*      */   public static final String INDENT_NUMBER = "indent-number";
/*   97 */   private ErrorListener _errorListener = this;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  102 */   private URIResolver _uriResolver = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  114 */   protected static String DEFAULT_TRANSLET_NAME = "GregorSamsa";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  119 */   private String _transletName = DEFAULT_TRANSLET_NAME;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  124 */   private String _destinationDirectory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   private String _packageName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   private String _jarFileName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  140 */   private Hashtable _piParams = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   static ThreadLocal _xmlReader = new ThreadLocal();
/*      */ 
/*      */ 
/*      */   
/*      */   private static class PIParamWrapper
/*      */   {
/*  152 */     public String _media = null;
/*  153 */     public String _title = null;
/*  154 */     public String _charset = null;
/*      */     
/*      */     public PIParamWrapper(String media, String title, String charset) {
/*  157 */       this._media = media;
/*  158 */       this._title = title;
/*  159 */       this._charset = charset;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _debug = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _enableInlining = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _generateTranslet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _autoTranslet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _useClasspath = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   private int _indentNumber = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Class m_DTMManagerClass;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TransformerFactoryImpl() {
/*  210 */     this.m_DTMManagerClass = XSLTCDTMManager.getDTMManagerClass();
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
/*      */   public void setErrorListener(ErrorListener listener) throws IllegalArgumentException {
/*  225 */     if (listener == null) {
/*  226 */       ErrorMsg err = new ErrorMsg("ERROR_LISTENER_NULL_ERR", "TransformerFactory");
/*      */       
/*  228 */       throw new IllegalArgumentException(err.toString());
/*      */     } 
/*  230 */     this._errorListener = listener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorListener getErrorListener() {
/*  240 */     return this._errorListener;
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
/*      */   public Object getAttribute(String name) throws IllegalArgumentException {
/*  255 */     if (name.equals("translet-name")) {
/*  256 */       return this._transletName;
/*      */     }
/*  258 */     if (name.equals("generate-translet")) {
/*  259 */       return new Boolean(this._generateTranslet);
/*      */     }
/*  261 */     if (name.equals("auto-translet")) {
/*  262 */       return new Boolean(this._autoTranslet);
/*      */     }
/*      */ 
/*      */     
/*  266 */     ErrorMsg err = new ErrorMsg("JAXP_INVALID_ATTR_ERR", name);
/*  267 */     throw new IllegalArgumentException(err.toString());
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
/*      */   public void setAttribute(String name, Object value) throws IllegalArgumentException {
/*  283 */     if (name.equals("translet-name") && value instanceof String) {
/*  284 */       this._transletName = (String)value;
/*      */       return;
/*      */     } 
/*  287 */     if (name.equals("destination-directory") && value instanceof String) {
/*  288 */       this._destinationDirectory = (String)value;
/*      */       return;
/*      */     } 
/*  291 */     if (name.equals("package-name") && value instanceof String) {
/*  292 */       this._packageName = (String)value;
/*      */       return;
/*      */     } 
/*  295 */     if (name.equals("jar-name") && value instanceof String) {
/*  296 */       this._jarFileName = (String)value;
/*      */       return;
/*      */     } 
/*  299 */     if (name.equals("generate-translet")) {
/*  300 */       if (value instanceof Boolean) {
/*  301 */         this._generateTranslet = ((Boolean)value).booleanValue();
/*      */         return;
/*      */       } 
/*  304 */       if (value instanceof String) {
/*  305 */         this._generateTranslet = ((String)value).equalsIgnoreCase("true");
/*      */         
/*      */         return;
/*      */       } 
/*  309 */     } else if (name.equals("auto-translet")) {
/*  310 */       if (value instanceof Boolean) {
/*  311 */         this._autoTranslet = ((Boolean)value).booleanValue();
/*      */         return;
/*      */       } 
/*  314 */       if (value instanceof String) {
/*  315 */         this._autoTranslet = ((String)value).equalsIgnoreCase("true");
/*      */         
/*      */         return;
/*      */       } 
/*  319 */     } else if (name.equals("use-classpath")) {
/*  320 */       if (value instanceof Boolean) {
/*  321 */         this._useClasspath = ((Boolean)value).booleanValue();
/*      */         return;
/*      */       } 
/*  324 */       if (value instanceof String) {
/*  325 */         this._useClasspath = ((String)value).equalsIgnoreCase("true");
/*      */         
/*      */         return;
/*      */       } 
/*  329 */     } else if (name.equals("debug")) {
/*  330 */       if (value instanceof Boolean) {
/*  331 */         this._debug = ((Boolean)value).booleanValue();
/*      */         return;
/*      */       } 
/*  334 */       if (value instanceof String) {
/*  335 */         this._debug = ((String)value).equalsIgnoreCase("true");
/*      */         
/*      */         return;
/*      */       } 
/*  339 */     } else if (name.equals("enable-inlining")) {
/*  340 */       if (value instanceof Boolean) {
/*  341 */         this._enableInlining = ((Boolean)value).booleanValue();
/*      */         return;
/*      */       } 
/*  344 */       if (value instanceof String) {
/*  345 */         this._enableInlining = ((String)value).equalsIgnoreCase("true");
/*      */         
/*      */         return;
/*      */       } 
/*  349 */     } else if (name.equals("indent-number")) {
/*  350 */       if (value instanceof String) {
/*      */         try {
/*  352 */           this._indentNumber = Integer.parseInt((String)value); return;
/*  353 */         } catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  359 */       else if (value instanceof Integer) {
/*  360 */         this._indentNumber = ((Integer)value).intValue();
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  366 */     ErrorMsg err = new ErrorMsg("JAXP_INVALID_ATTR_ERR", name);
/*      */     
/*  368 */     throw new IllegalArgumentException(err.toString());
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
/*      */   public boolean getFeature(String name) {
/*  382 */     String[] features = { "http://javax.xml.transform.dom.DOMSource/feature", "http://javax.xml.transform.dom.DOMResult/feature", "http://javax.xml.transform.sax.SAXSource/feature", "http://javax.xml.transform.sax.SAXResult/feature", "http://javax.xml.transform.stream.StreamSource/feature", "http://javax.xml.transform.stream.StreamResult/feature", "http://javax.xml.transform.sax.SAXTransformerFactory/feature", "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  394 */     for (int i = 0; i < features.length; i++) {
/*  395 */       if (name.equals(features[i])) {
/*  396 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  400 */     return false;
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
/*  412 */     return this._uriResolver;
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
/*      */   public void setURIResolver(URIResolver resolver) {
/*  426 */     this._uriResolver = resolver;
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
/*      */   public Source getAssociatedStylesheet(Source source, String media, String title, String charset) throws TransformerConfigurationException {
/*  449 */     XMLReader reader = null;
/*  450 */     InputSource isource = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  456 */     StylesheetPIHandler _stylesheetPIHandler = new StylesheetPIHandler(null, media, title, charset);
/*      */ 
/*      */ 
/*      */     
/*  460 */     try { if (source instanceof DOMSource) {
/*  461 */         DOMSource domsrc = (DOMSource)source;
/*  462 */         String baseId = domsrc.getSystemId();
/*  463 */         Node node = domsrc.getNode();
/*  464 */         DOM2SAX dom2sax = new DOM2SAX(node);
/*      */         
/*  466 */         _stylesheetPIHandler.setBaseId(baseId);
/*      */         
/*  468 */         dom2sax.setContentHandler((ContentHandler)_stylesheetPIHandler);
/*  469 */         dom2sax.parse();
/*      */       } else {
/*  471 */         isource = SAXSource.sourceToInputSource(source);
/*  472 */         String str = isource.getSystemId();
/*      */         
/*  474 */         SAXParserFactory factory = SAXParserFactory.newInstance();
/*  475 */         factory.setNamespaceAware(true);
/*  476 */         SAXParser jaxpParser = factory.newSAXParser();
/*      */         
/*  478 */         reader = jaxpParser.getXMLReader();
/*  479 */         if (reader == null) {
/*  480 */           reader = XMLReaderFactory.createXMLReader();
/*      */         }
/*      */         
/*  483 */         _stylesheetPIHandler.setBaseId(str);
/*  484 */         reader.setContentHandler((ContentHandler)_stylesheetPIHandler);
/*  485 */         reader.parse(isource);
/*      */       } 
/*      */ 
/*      */       
/*  489 */       if (this._uriResolver != null)
/*  490 */         _stylesheetPIHandler.setURIResolver(this._uriResolver);  } catch (StopParseException e)
/*      */     
/*      */     {  }
/*  493 */     catch (ParserConfigurationException e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  498 */       throw new TransformerConfigurationException("getAssociatedStylesheets failed", e); } catch (SAXException se)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  503 */       throw new TransformerConfigurationException("getAssociatedStylesheets failed", se); } catch (IOException ioe)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  508 */       throw new TransformerConfigurationException("getAssociatedStylesheets failed", ioe); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  513 */     return _stylesheetPIHandler.getAssociatedStylesheet();
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
/*      */   public Transformer newTransformer() throws TransformerConfigurationException {
/*  527 */     TransformerImpl result = new TransformerImpl(new Properties(), this._indentNumber, this);
/*      */     
/*  529 */     if (this._uriResolver != null) {
/*  530 */       result.setURIResolver(this._uriResolver);
/*      */     }
/*  532 */     return result;
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
/*      */   public Transformer newTransformer(Source source) throws TransformerConfigurationException {
/*  548 */     Templates templates = newTemplates(source);
/*  549 */     Transformer transformer = templates.newTransformer();
/*  550 */     if (this._uriResolver != null) {
/*  551 */       transformer.setURIResolver(this._uriResolver);
/*      */     }
/*  553 */     return transformer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void passWarningsToListener(Vector messages) throws TransformerException {
/*  562 */     if (this._errorListener == null || messages == null) {
/*      */       return;
/*      */     }
/*      */     
/*  566 */     int count = messages.size();
/*  567 */     for (int pos = 0; pos < count; pos++) {
/*  568 */       String message = messages.elementAt(pos).toString();
/*  569 */       this._errorListener.error(new TransformerConfigurationException(message));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void passErrorsToListener(Vector messages) {
/*      */     try {
/*  579 */       if (this._errorListener == null || messages == null) {
/*      */         return;
/*      */       }
/*      */       
/*  583 */       int count = messages.size();
/*  584 */       for (int pos = 0; pos < count; pos++) {
/*  585 */         String message = messages.elementAt(pos).toString();
/*  586 */         this._errorListener.error(new TransformerException(message));
/*      */       } 
/*      */     } catch (TransformerException transformerException) {}
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
/*      */   public Templates newTemplates(Source source) throws TransformerConfigurationException {
/*  609 */     if (this._useClasspath) {
/*  610 */       String str = getTransletBaseName(source);
/*      */       
/*  612 */       if (this._packageName != null) {
/*  613 */         str = this._packageName + "." + str;
/*      */       }
/*      */       
/*  616 */       try { Class clazz = ObjectFactory.findProviderClass(str, ObjectFactory.findClassLoader(), true);
/*      */         
/*  618 */         resetTransientAttributes();
/*      */         
/*  620 */         return new TemplatesImpl(new Class[] { clazz }, str, null, this._indentNumber, this); } catch (ClassNotFoundException cnfe)
/*      */       
/*      */       { 
/*  623 */         ErrorMsg err = new ErrorMsg("CLASS_NOT_FOUND_ERR", str);
/*  624 */         throw new TransformerConfigurationException(err.toString()); } catch (Exception e)
/*      */       
/*      */       { 
/*  627 */         ErrorMsg err = new ErrorMsg(new ErrorMsg("RUNTIME_ERROR_KEY") + e.getMessage());
/*      */ 
/*      */         
/*  630 */         throw new TransformerConfigurationException(err.toString()); }
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  636 */     if (this._autoTranslet) {
/*  637 */       byte[][] arrayOfByte = null;
/*  638 */       String transletClassName = getTransletBaseName(source);
/*      */       
/*  640 */       if (this._packageName != null) {
/*  641 */         transletClassName = this._packageName + "." + transletClassName;
/*      */       }
/*  643 */       if (this._jarFileName != null) {
/*  644 */         arrayOfByte = getBytecodesFromJar(source, transletClassName);
/*      */       } else {
/*  646 */         arrayOfByte = getBytecodesFromClasses(source, transletClassName);
/*      */       } 
/*  648 */       if (arrayOfByte != null) {
/*  649 */         if (this._debug) {
/*  650 */           if (this._jarFileName != null) {
/*  651 */             System.err.println(new ErrorMsg("TRANSFORM_WITH_JAR_STR", transletClassName, this._jarFileName));
/*      */           } else {
/*      */             
/*  654 */             System.err.println(new ErrorMsg("TRANSFORM_WITH_TRANSLET_STR", transletClassName));
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  660 */         resetTransientAttributes();
/*      */         
/*  662 */         return new TemplatesImpl(arrayOfByte, transletClassName, null, this._indentNumber, this);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  667 */     XSLTC xsltc = new XSLTC();
/*  668 */     if (this._debug) xsltc.setDebug(true); 
/*  669 */     if (this._enableInlining) xsltc.setTemplateInlining(true); 
/*  670 */     xsltc.init();
/*      */ 
/*      */     
/*  673 */     if (this._uriResolver != null) {
/*  674 */       xsltc.setSourceLoader(this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  679 */     if (this._piParams != null && this._piParams.get(source) != null) {
/*      */       
/*  681 */       PIParamWrapper p = (PIParamWrapper)this._piParams.get(source);
/*      */       
/*  683 */       if (p != null) {
/*  684 */         xsltc.setPIParameters(p._media, p._title, p._charset);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  689 */     int outputType = 2;
/*  690 */     if (this._generateTranslet || this._autoTranslet) {
/*      */       
/*  692 */       xsltc.setClassName(getTransletBaseName(source));
/*      */       
/*  694 */       if (this._destinationDirectory != null) {
/*  695 */         xsltc.setDestDirectory(this._destinationDirectory);
/*      */       } else {
/*  697 */         String xslName = getStylesheetFileName(source);
/*  698 */         if (xslName != null) {
/*  699 */           File xslFile = new File(xslName);
/*  700 */           String xslDir = xslFile.getParent();
/*      */           
/*  702 */           if (xslDir != null) {
/*  703 */             xsltc.setDestDirectory(xslDir);
/*      */           }
/*      */         } 
/*      */       } 
/*  707 */       if (this._packageName != null) {
/*  708 */         xsltc.setPackageName(this._packageName);
/*      */       }
/*  710 */       if (this._jarFileName != null) {
/*  711 */         xsltc.setJarFileName(this._jarFileName);
/*  712 */         outputType = 5;
/*      */       } else {
/*      */         
/*  715 */         outputType = 4;
/*      */       } 
/*      */     } 
/*      */     
/*  719 */     InputSource input = Util.getInputSource(xsltc, source);
/*  720 */     byte[][] bytecodes = xsltc.compile(null, input, outputType);
/*  721 */     String transletName = xsltc.getClassName();
/*      */ 
/*      */     
/*  724 */     if ((this._generateTranslet || this._autoTranslet) && bytecodes != null && this._jarFileName != null) {
/*      */ 
/*      */       
/*  727 */       try { xsltc.outputToJar(); } catch (IOException iOException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  734 */     resetTransientAttributes();
/*      */ 
/*      */     
/*  737 */     if (this._errorListener != this) {
/*      */       
/*  739 */       try { passWarningsToListener(xsltc.getWarnings()); } catch (TransformerException e)
/*      */       
/*      */       { 
/*  742 */         throw new TransformerConfigurationException(e); }
/*      */     
/*      */     } else {
/*      */       
/*  746 */       xsltc.printWarnings();
/*      */     } 
/*      */ 
/*      */     
/*  750 */     if (bytecodes == null) {
/*      */       
/*  752 */       ErrorMsg err = new ErrorMsg("JAXP_COMPILE_ERR");
/*  753 */       TransformerConfigurationException exc = new TransformerConfigurationException(err.toString());
/*      */ 
/*      */       
/*  756 */       if (this._errorListener != null) {
/*  757 */         passErrorsToListener(xsltc.getErrors());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  763 */         try { this._errorListener.fatalError(exc); } catch (TransformerException transformerException) {}
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  769 */         xsltc.printErrors();
/*      */       } 
/*  771 */       throw exc;
/*      */     } 
/*      */     
/*  774 */     return new TemplatesImpl(bytecodes, transletName, xsltc.getOutputProperties(), this._indentNumber, this);
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
/*      */   public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
/*  789 */     TemplatesHandlerImpl handler = new TemplatesHandlerImpl(this._indentNumber, this);
/*      */     
/*  791 */     if (this._uriResolver != null) {
/*  792 */       handler.setURIResolver(this._uriResolver);
/*      */     }
/*  794 */     return handler;
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
/*      */   public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
/*  808 */     Transformer transformer = newTransformer();
/*  809 */     if (this._uriResolver != null) {
/*  810 */       transformer.setURIResolver(this._uriResolver);
/*      */     }
/*  812 */     return new TransformerHandlerImpl((TransformerImpl)transformer);
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
/*      */   public TransformerHandler newTransformerHandler(Source src) throws TransformerConfigurationException {
/*  828 */     Transformer transformer = newTransformer(src);
/*  829 */     if (this._uriResolver != null) {
/*  830 */       transformer.setURIResolver(this._uriResolver);
/*      */     }
/*  832 */     return new TransformerHandlerImpl((TransformerImpl)transformer);
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
/*      */   public TransformerHandler newTransformerHandler(Templates templates) throws TransformerConfigurationException {
/*  848 */     Transformer transformer = templates.newTransformer();
/*  849 */     TransformerImpl internal = (TransformerImpl)transformer;
/*  850 */     return new TransformerHandlerImpl(internal);
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
/*      */   public XMLFilter newXMLFilter(Source src) throws TransformerConfigurationException {
/*  865 */     Templates templates = newTemplates(src);
/*  866 */     if (templates == null) return null; 
/*  867 */     return newXMLFilter(templates);
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
/*      */   public XMLFilter newXMLFilter(Templates templates) throws TransformerConfigurationException {
/*      */     
/*  883 */     try { return new TrAXFilter(templates); } catch (TransformerConfigurationException e1)
/*      */     
/*      */     { 
/*  886 */       if (this._errorListener != null) {
/*      */         
/*  888 */         try { this._errorListener.fatalError(e1);
/*  889 */           return null; } catch (TransformerException e2)
/*      */         
/*      */         { 
/*  892 */           new TransformerConfigurationException(e2); }
/*      */       
/*      */       }
/*  895 */       throw e1; }
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
/*  913 */     Throwable wrapped = e.getException();
/*  914 */     if (wrapped != null) {
/*  915 */       System.err.println(new ErrorMsg("ERROR_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
/*      */     }
/*      */     else {
/*      */       
/*  919 */       System.err.println(new ErrorMsg("ERROR_MSG", e.getMessageAndLocation()));
/*      */     } 
/*      */     
/*  922 */     throw e;
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
/*  941 */     Throwable wrapped = e.getException();
/*  942 */     if (wrapped != null) {
/*  943 */       System.err.println(new ErrorMsg("FATAL_ERR_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
/*      */     }
/*      */     else {
/*      */       
/*  947 */       System.err.println(new ErrorMsg("FATAL_ERR_MSG", e.getMessageAndLocation()));
/*      */     } 
/*      */     
/*  950 */     throw e;
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
/*  969 */     Throwable wrapped = e.getException();
/*  970 */     if (wrapped != null) {
/*  971 */       System.err.println(new ErrorMsg("WARNING_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
/*      */     }
/*      */     else {
/*      */       
/*  975 */       System.err.println(new ErrorMsg("WARNING_MSG", e.getMessageAndLocation()));
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
/*      */   public InputSource loadSource(String href, String context, XSLTC xsltc) {
/*      */     
/*  991 */     try { if (this._uriResolver != null)
/*  992 */       { Source source = this._uriResolver.resolve(href, context);
/*  993 */         if (source != null)
/*  994 */           return Util.getInputSource(xsltc, source);  }  } catch (TransformerException transformerException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1001 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetTransientAttributes() {
/* 1008 */     this._transletName = DEFAULT_TRANSLET_NAME;
/* 1009 */     this._destinationDirectory = null;
/* 1010 */     this._packageName = null;
/* 1011 */     this._jarFileName = null;
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
/*      */   private byte[][] getBytecodesFromClasses(Source source, String fullClassName) {
/*      */     String str1;
/* 1024 */     if (fullClassName == null) {
/* 1025 */       return null;
/*      */     }
/* 1027 */     String xslFileName = getStylesheetFileName(source);
/* 1028 */     File xslFile = null;
/* 1029 */     if (xslFileName != null) {
/* 1030 */       xslFile = new File(xslFileName);
/*      */     }
/*      */ 
/*      */     
/* 1034 */     int lastDotIndex = fullClassName.lastIndexOf('.');
/* 1035 */     if (lastDotIndex > 0) {
/* 1036 */       str1 = fullClassName.substring(lastDotIndex + 1);
/*      */     } else {
/* 1038 */       str1 = fullClassName;
/*      */     } 
/*      */     
/* 1041 */     String transletPath = fullClassName.replace('.', '/');
/* 1042 */     if (this._destinationDirectory != null) {
/* 1043 */       transletPath = this._destinationDirectory + "/" + transletPath + ".class";
/*      */     
/*      */     }
/* 1046 */     else if (xslFile != null && xslFile.getParent() != null) {
/* 1047 */       transletPath = xslFile.getParent() + "/" + transletPath + ".class";
/*      */     } else {
/* 1049 */       transletPath = transletPath + ".class";
/*      */     } 
/*      */ 
/*      */     
/* 1053 */     File transletFile = new File(transletPath);
/* 1054 */     if (!transletFile.exists()) {
/* 1055 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1061 */     if (xslFile != null && xslFile.exists()) {
/* 1062 */       long xslTimestamp = xslFile.lastModified();
/* 1063 */       long transletTimestamp = transletFile.lastModified();
/* 1064 */       if (transletTimestamp < xslTimestamp) {
/* 1065 */         return null;
/*      */       }
/*      */     } 
/*      */     
/* 1069 */     Vector bytecodes = new Vector();
/* 1070 */     int fileLength = (int)transletFile.length();
/* 1071 */     if (fileLength > 0) {
/* 1072 */       FileInputStream input = null;
/*      */       
/* 1074 */       try { input = new FileInputStream(transletFile); } catch (FileNotFoundException e)
/*      */       
/*      */       { 
/* 1077 */         return null; }
/*      */ 
/*      */       
/* 1080 */       byte[] bytes = new byte[fileLength];
/*      */       
/* 1082 */       try { readFromInputStream(bytes, input, fileLength);
/* 1083 */         input.close(); } catch (IOException e)
/*      */       
/*      */       { 
/* 1086 */         return null; }
/*      */ 
/*      */       
/* 1089 */       bytecodes.addElement(bytes);
/*      */     } else {
/*      */       
/* 1092 */       return null;
/*      */     } 
/*      */     
/* 1095 */     String transletParentDir = transletFile.getParent();
/* 1096 */     if (transletParentDir == null) {
/* 1097 */       transletParentDir = System.getProperty("user.dir");
/*      */     }
/* 1099 */     File transletParentFile = new File(transletParentDir);
/*      */ 
/*      */     
/* 1102 */     String transletAuxPrefix = str1 + "$";
/* 1103 */     File[] auxfiles = transletParentFile.listFiles(new FilenameFilter(this, transletAuxPrefix) { private final String val$transletAuxPrefix; private final TransformerFactoryImpl this$0;
/*      */           
/*      */           public boolean accept(File dir, String name) {
/* 1106 */             return (name.endsWith(".class") && name.startsWith(this.val$transletAuxPrefix));
/*      */           } }
/*      */       );
/*      */ 
/*      */     
/* 1111 */     for (int i = 0; i < auxfiles.length; i++) {
/*      */       
/* 1113 */       File auxfile = auxfiles[i];
/* 1114 */       int auxlength = (int)auxfile.length();
/* 1115 */       if (auxlength > 0) {
/* 1116 */         FileInputStream auxinput = null;
/*      */         
/* 1118 */         try { auxinput = new FileInputStream(auxfile); } catch (FileNotFoundException e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1124 */         byte[] bytes = new byte[auxlength];
/*      */ 
/*      */         
/* 1127 */         try { readFromInputStream(bytes, auxinput, auxlength);
/* 1128 */           auxinput.close(); } catch (IOException e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1134 */         bytecodes.addElement(bytes);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1139 */     int count = bytecodes.size();
/* 1140 */     if (count > 0) {
/* 1141 */       byte[][] result = new byte[count][1];
/* 1142 */       for (int j = 0; j < count; j++) {
/* 1143 */         result[j] = bytecodes.elementAt(j);
/*      */       }
/*      */       
/* 1146 */       return result;
/*      */     } 
/*      */     
/* 1149 */     return null;
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
/*      */   private byte[][] getBytecodesFromJar(Source source, String fullClassName) {
/* 1161 */     String xslFileName = getStylesheetFileName(source);
/* 1162 */     File xslFile = null;
/* 1163 */     if (xslFileName != null) {
/* 1164 */       xslFile = new File(xslFileName);
/*      */     }
/*      */     
/* 1167 */     String jarPath = null;
/* 1168 */     if (this._destinationDirectory != null) {
/* 1169 */       jarPath = this._destinationDirectory + "/" + this._jarFileName;
/*      */     }
/* 1171 */     else if (xslFile != null && xslFile.getParent() != null) {
/* 1172 */       jarPath = xslFile.getParent() + "/" + this._jarFileName;
/*      */     } else {
/* 1174 */       jarPath = this._jarFileName;
/*      */     } 
/*      */ 
/*      */     
/* 1178 */     File file = new File(jarPath);
/* 1179 */     if (!file.exists()) {
/* 1180 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1184 */     if (xslFile != null && xslFile.exists()) {
/* 1185 */       long xslTimestamp = xslFile.lastModified();
/* 1186 */       long transletTimestamp = file.lastModified();
/* 1187 */       if (transletTimestamp < xslTimestamp) {
/* 1188 */         return null;
/*      */       }
/*      */     } 
/*      */     
/* 1192 */     ZipFile jarFile = null;
/*      */     
/* 1194 */     try { jarFile = new ZipFile(file); } catch (IOException e)
/*      */     
/*      */     { 
/* 1197 */       return null; }
/*      */ 
/*      */     
/* 1200 */     String transletPath = fullClassName.replace('.', '/');
/* 1201 */     String transletAuxPrefix = transletPath + "$";
/* 1202 */     String transletFullName = transletPath + ".class";
/*      */     
/* 1204 */     Vector bytecodes = new Vector();
/*      */ 
/*      */ 
/*      */     
/* 1208 */     Enumeration entries = jarFile.entries();
/* 1209 */     while (entries.hasMoreElements()) {
/*      */       
/* 1211 */       ZipEntry entry = entries.nextElement();
/* 1212 */       String entryName = entry.getName();
/* 1213 */       if (entry.getSize() > 0L && (entryName.equals(transletFullName) || (entryName.endsWith(".class") && entryName.startsWith(transletAuxPrefix)))) {
/*      */ 
/*      */         
/*      */         try { 
/*      */ 
/*      */           
/* 1219 */           InputStream input = jarFile.getInputStream(entry);
/* 1220 */           int size = (int)entry.getSize();
/* 1221 */           byte[] bytes = new byte[size];
/* 1222 */           readFromInputStream(bytes, input, size);
/* 1223 */           input.close();
/* 1224 */           bytecodes.addElement(bytes); } catch (IOException e)
/*      */         
/*      */         { 
/* 1227 */           return null; }
/*      */       
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1233 */     int count = bytecodes.size();
/* 1234 */     if (count > 0) {
/* 1235 */       byte[][] result = new byte[count][1];
/* 1236 */       for (int i = 0; i < count; i++) {
/* 1237 */         result[i] = bytecodes.elementAt(i);
/*      */       }
/*      */       
/* 1240 */       return result;
/*      */     } 
/*      */     
/* 1243 */     return null;
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
/*      */   private void readFromInputStream(byte[] bytes, InputStream input, int size) throws IOException {
/* 1256 */     int n = 0;
/* 1257 */     int offset = 0;
/* 1258 */     int length = size;
/* 1259 */     while (length > 0 && (n = input.read(bytes, offset, length)) > 0) {
/* 1260 */       offset += n;
/* 1261 */       length -= n;
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
/*      */   private String getTransletBaseName(Source source) {
/* 1278 */     String transletBaseName = null;
/* 1279 */     if (!this._transletName.equals(DEFAULT_TRANSLET_NAME)) {
/* 1280 */       return this._transletName;
/*      */     }
/* 1282 */     String systemId = source.getSystemId();
/* 1283 */     if (systemId != null) {
/* 1284 */       String baseName = Util.baseName(systemId);
/* 1285 */       if (baseName != null) {
/* 1286 */         baseName = Util.noExtName(baseName);
/* 1287 */         transletBaseName = Util.toJavaName(baseName);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1292 */     return (transletBaseName != null) ? transletBaseName : DEFAULT_TRANSLET_NAME;
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
/*      */   private String getStylesheetFileName(Source source) {
/* 1304 */     String systemId = source.getSystemId();
/* 1305 */     if (systemId != null) {
/* 1306 */       File file = new File(systemId);
/* 1307 */       if (file.exists()) {
/* 1308 */         return systemId;
/*      */       }
/* 1310 */       URL url = null;
/*      */       
/* 1312 */       try { url = new URL(systemId); } catch (MalformedURLException e)
/*      */       
/*      */       { 
/* 1315 */         return null; }
/*      */ 
/*      */       
/* 1318 */       if ("file".equals(url.getProtocol())) {
/* 1319 */         return url.getFile();
/*      */       }
/* 1321 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1325 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Class getDTMManagerClass() {
/* 1332 */     return this.m_DTMManagerClass;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/TransformerFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */