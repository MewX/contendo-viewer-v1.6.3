/*      */ package org.apache.xalan.transformer;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.StringWriter;
/*      */ import java.util.Enumeration;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Properties;
/*      */ import java.util.Stack;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.Result;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.URIResolver;
/*      */ import javax.xml.transform.dom.DOMResult;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.sax.SAXResult;
/*      */ import javax.xml.transform.sax.SAXSource;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import javax.xml.transform.stream.StreamSource;
/*      */ import org.apache.xalan.extensions.ExtensionsTable;
/*      */ import org.apache.xalan.processor.TransformerFactoryImpl;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xalan.templates.AVT;
/*      */ import org.apache.xalan.templates.ElemAttributeSet;
/*      */ import org.apache.xalan.templates.ElemForEach;
/*      */ import org.apache.xalan.templates.ElemSort;
/*      */ import org.apache.xalan.templates.ElemTemplate;
/*      */ import org.apache.xalan.templates.ElemTemplateElement;
/*      */ import org.apache.xalan.templates.ElemTextLiteral;
/*      */ import org.apache.xalan.templates.ElemVariable;
/*      */ import org.apache.xalan.templates.OutputProperties;
/*      */ import org.apache.xalan.templates.Stylesheet;
/*      */ import org.apache.xalan.templates.StylesheetComposed;
/*      */ import org.apache.xalan.templates.StylesheetRoot;
/*      */ import org.apache.xalan.templates.WhiteSpaceInfo;
/*      */ import org.apache.xalan.templates.XUnresolvedVariable;
/*      */ import org.apache.xalan.trace.GenerateEvent;
/*      */ import org.apache.xalan.trace.TraceManager;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMIterator;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.serializer.Serializer;
/*      */ import org.apache.xml.serializer.SerializerFactory;
/*      */ import org.apache.xml.serializer.SerializerTrace;
/*      */ import org.apache.xml.serializer.ToHTMLSAXHandler;
/*      */ import org.apache.xml.serializer.ToSAXHandler;
/*      */ import org.apache.xml.serializer.ToTextSAXHandler;
/*      */ import org.apache.xml.serializer.ToTextStream;
/*      */ import org.apache.xml.serializer.ToXMLSAXHandler;
/*      */ import org.apache.xml.utils.BoolStack;
/*      */ import org.apache.xml.utils.DOMBuilder;
/*      */ import org.apache.xml.utils.DOMHelper;
/*      */ import org.apache.xml.utils.DefaultErrorHandler;
/*      */ import org.apache.xml.utils.NodeVector;
/*      */ import org.apache.xml.utils.ObjectPool;
/*      */ import org.apache.xml.utils.ObjectStack;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xml.utils.QName;
/*      */ import org.apache.xml.utils.SAXSourceLocator;
/*      */ import org.apache.xml.utils.ThreadControllerWrapper;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ import org.apache.xpath.Arg;
/*      */ import org.apache.xpath.ExtensionsProvider;
/*      */ import org.apache.xpath.NodeSetDTM;
/*      */ import org.apache.xpath.VariableStack;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.apache.xpath.axes.SelfIteratorNoPredicate;
/*      */ import org.apache.xpath.functions.FuncExtFunction;
/*      */ import org.apache.xpath.objects.XObject;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentFragment;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXNotRecognizedException;
/*      */ import org.xml.sax.SAXNotSupportedException;
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
/*      */ public class TransformerImpl
/*      */   extends Transformer
/*      */   implements Runnable, DTMWSFilter, SerializerTrace, ExtensionsProvider
/*      */ {
/*  115 */   private Boolean m_reentryGuard = new Boolean(true);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   private FileOutputStream m_outputStream = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_parserEventsOnMain = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private Thread m_transformThread;
/*      */ 
/*      */ 
/*      */   
/*  132 */   private String m_urlOfSource = null;
/*      */ 
/*      */   
/*  135 */   private Result m_outputTarget = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private OutputProperties m_outputFormat;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ContentHandler m_inputContentHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  151 */   private ContentHandler m_outputContentHandler = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  161 */   DocumentBuilder m_docBuilder = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  167 */   private ObjectPool m_textResultHandlerObjectPool = new ObjectPool(ToTextStream.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  175 */   private ObjectPool m_stringWriterObjectPool = new ObjectPool(StringWriter.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  182 */   private OutputProperties m_textformat = new OutputProperties("text");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  203 */   ObjectStack m_currentTemplateElements = new ObjectStack(4096);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   Stack m_currentMatchTemplates = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  226 */   NodeVector m_currentMatchedNodes = new NodeVector();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  231 */   private StylesheetRoot m_stylesheetRoot = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_quietConflictWarnings = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private XPathContext m_xcontext;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StackGuard m_stackGuard;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SerializationHandler m_serializationHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  258 */   private KeyManager m_keyManager = new KeyManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  264 */   Stack m_attrSetStack = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  270 */   CountersTable m_countersTable = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  275 */   BoolStack m_currentTemplateRuleIsNull = new BoolStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  282 */   ObjectStack m_currentFuncResult = new ObjectStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MsgMgr m_msgMgr;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean S_DEBUG = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  299 */   private ErrorListener m_errorHandler = (ErrorListener)new DefaultErrorHandler();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  305 */   private TraceManager m_traceManager = new TraceManager(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  312 */   private Exception m_exceptionThrown = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Source m_xmlSource;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int m_doc;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_isTransformDone = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_hasBeenReset = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_shouldReset = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShouldReset(boolean shouldReset) {
/*  349 */     this.m_shouldReset = shouldReset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  355 */   private Stack m_modes = new Stack();
/*      */   
/*      */   private ExtensionsTable m_extensionsTable;
/*      */   private boolean m_hasTransformThreadErrorCatcher;
/*      */   Vector m_userParams;
/*      */   
/*      */   public ExtensionsTable getExtensionsTable() {
/*      */     return this.m_extensionsTable;
/*      */   }
/*      */   
/*      */   void setExtensionsTable(StylesheetRoot sroot) throws TransformerException {
/*      */     try {
/*      */       if (sroot.getExtensions() != null) {
/*      */         this.m_extensionsTable = new ExtensionsTable(sroot);
/*      */       }
/*      */     } catch (TransformerException te) {
/*      */       te.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean functionAvailable(String ns, String funcName) throws TransformerException {
/*      */     return getExtensionsTable().functionAvailable(ns, funcName);
/*      */   }
/*      */   
/*      */   public TransformerImpl(StylesheetRoot stylesheet) {
/*  380 */     this.m_extensionsTable = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  562 */     this.m_hasTransformThreadErrorCatcher = false;
/*      */     setStylesheet(stylesheet);
/*      */     setXPathContext(new XPathContext(this));
/*      */     getXPathContext().setNamespaceContext((PrefixResolver)stylesheet);
/*      */     this.m_stackGuard = new StackGuard(this);
/*      */   } public boolean elementAvailable(String ns, String elemName) throws TransformerException { return getExtensionsTable().elementAvailable(ns, elemName); } public Object extFunction(String ns, String funcName, Vector argVec, Object methodKey) throws TransformerException {
/*      */     return getExtensionsTable().extFunction(ns, funcName, argVec, methodKey, getXPathContext().getExpressionContext());
/*      */   } public Object extFunction(FuncExtFunction extFunction, Vector argVec) throws TransformerException {
/*      */     return getExtensionsTable().extFunction(extFunction, argVec, getXPathContext().getExpressionContext());
/*      */   } public boolean hasTransformThreadErrorCatcher() {
/*  572 */     return this.m_hasTransformThreadErrorCatcher;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transform(Source source) throws TransformerException {
/*  583 */     transform(source, true);
/*      */   }
/*      */   public void reset() { if (!this.m_hasBeenReset && this.m_shouldReset) {
/*      */       this.m_hasBeenReset = true; if (this.m_outputStream != null)
/*      */         try {
/*      */           this.m_outputStream.close();
/*      */         } catch (IOException iOException) {}  this.m_outputStream = null; this.m_countersTable = null; this.m_xcontext.reset(); this.m_xcontext.getVarStack().reset(); resetUserParameters(); this.m_currentTemplateElements.removeAllElements(); this.m_currentMatchTemplates.removeAllElements(); this.m_currentMatchedNodes.removeAllElements(); this.m_serializationHandler = null; this.m_outputTarget = null;
/*      */       this.m_keyManager = new KeyManager();
/*      */       this.m_attrSetStack = null;
/*      */       this.m_countersTable = null;
/*      */       this.m_currentTemplateRuleIsNull = new BoolStack();
/*      */       this.m_xmlSource = null;
/*      */       this.m_doc = -1;
/*      */       this.m_isTransformDone = false;
/*      */       this.m_transformThread = null;
/*      */       this.m_xcontext.getSourceTreeManager().reset();
/*      */     }  }
/*      */   public boolean getProperty(String property) { return false; }
/*      */   public void setProperty(String property, Object value) {}
/*      */   public boolean isParserEventsOnMain() { return this.m_parserEventsOnMain; } public Thread getTransformThread() { return this.m_transformThread; } public void setTransformThread(Thread t) { this.m_transformThread = t; } public void transform(Source source, boolean shouldRelease) throws TransformerException { 
/*  603 */     try { if (getXPathContext().getNamespaceContext() == null) {
/*  604 */         getXPathContext().setNamespaceContext((PrefixResolver)getStylesheet());
/*      */       }
/*  606 */       String base = source.getSystemId();
/*      */ 
/*      */       
/*  609 */       if (null == base)
/*      */       {
/*  611 */         base = this.m_stylesheetRoot.getBaseIdentifier();
/*      */       }
/*      */ 
/*      */       
/*  615 */       if (null == base) {
/*      */         
/*  617 */         String currentDir = "";
/*      */         
/*  619 */         try { currentDir = System.getProperty("user.dir"); } catch (SecurityException securityException) {}
/*      */ 
/*      */ 
/*      */         
/*  623 */         if (currentDir.startsWith(File.separator)) {
/*  624 */           base = "file://" + currentDir;
/*      */         } else {
/*  626 */           base = "file:///" + currentDir;
/*      */         } 
/*  628 */         base = base + File.separatorChar + source.getClass().getName();
/*      */       } 
/*      */       
/*  631 */       setBaseURLOfSource(base);
/*  632 */       DTMManager mgr = this.m_xcontext.getDTMManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  641 */       if ((source instanceof StreamSource && source.getSystemId() == null && ((StreamSource)source).getInputStream() == null && ((StreamSource)source).getReader() == null) || (source instanceof SAXSource && ((SAXSource)source).getInputSource() == null && ((SAXSource)source).getXMLReader() == null) || (source instanceof DOMSource && ((DOMSource)source).getNode() == null)) {
/*      */ 
/*      */         
/*      */         try { 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  649 */           DocumentBuilderFactory builderF = DocumentBuilderFactory.newInstance();
/*      */           
/*  651 */           DocumentBuilder builder = builderF.newDocumentBuilder();
/*  652 */           String systemID = source.getSystemId();
/*  653 */           source = new DOMSource(builder.newDocument());
/*      */ 
/*      */           
/*  656 */           if (systemID != null)
/*  657 */             source.setSystemId(systemID);  } catch (ParserConfigurationException parserConfigurationException)
/*      */         
/*      */         { 
/*  660 */           fatalError(parserConfigurationException); }
/*      */       
/*      */       }
/*  663 */       DTM dtm = mgr.getDTM(source, false, this, true, true);
/*  664 */       dtm.setDocumentBaseURI(base);
/*      */       
/*  666 */       boolean hardDelete = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  673 */         transformNode(dtm.getDocument());
/*      */       }
/*      */       finally {
/*      */         
/*  677 */         if (shouldRelease) {
/*  678 */           mgr.release(dtm, hardDelete);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  686 */       Exception e = getExceptionThrown();
/*      */       
/*  688 */       if (null != e)
/*      */       
/*  690 */       { if (e instanceof TransformerException)
/*      */         {
/*  692 */           throw (TransformerException)e;
/*      */         }
/*  694 */         if (e instanceof WrappedRuntimeException)
/*      */         {
/*  696 */           fatalError(((WrappedRuntimeException)e).getException());
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*  701 */           throw new TransformerException(e);
/*      */         }
/*      */          }
/*  704 */       else if (null != this.m_serializationHandler)
/*      */       
/*  706 */       { this.m_serializationHandler.endDocument(); }  } catch (WrappedRuntimeException wre)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  711 */       Throwable throwable = wre.getException();
/*      */ 
/*      */       
/*  714 */       while (throwable instanceof WrappedRuntimeException)
/*      */       {
/*  716 */         throwable = ((WrappedRuntimeException)throwable).getException();
/*      */       }
/*      */ 
/*      */       
/*  720 */       fatalError(throwable); } catch (SAXParseException spe)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/*  726 */       fatalError(spe); } catch (SAXException se)
/*      */     
/*      */     { 
/*      */       
/*  730 */       this.m_errorHandler.fatalError(new TransformerException(se)); }
/*      */     
/*      */     finally
/*      */     
/*  734 */     { this.m_hasTransformThreadErrorCatcher = false;
/*      */ 
/*      */       
/*  737 */       reset(); }
/*      */      }
/*      */ 
/*      */ 
/*      */   
/*      */   private void fatalError(Throwable throwable) throws TransformerException {
/*  743 */     if (throwable instanceof SAXParseException) {
/*  744 */       this.m_errorHandler.fatalError(new TransformerException(throwable.getMessage(), (SourceLocator)new SAXSourceLocator((SAXParseException)throwable)));
/*      */     } else {
/*  746 */       this.m_errorHandler.fatalError(new TransformerException(throwable));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBaseURLOfSource() {
/*  757 */     return this.m_urlOfSource;
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
/*      */   public void setBaseURLOfSource(String base) {
/*  769 */     this.m_urlOfSource = base;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Result getOutputTarget() {
/*  779 */     return this.m_outputTarget;
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
/*      */   public void setOutputTarget(Result outputTarget) {
/*  792 */     this.m_outputTarget = outputTarget;
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
/*      */   public String getOutputProperty(String qnameString) throws IllegalArgumentException {
/*  817 */     String value = null;
/*  818 */     OutputProperties props = getOutputFormat();
/*      */     
/*  820 */     value = props.getProperty(qnameString);
/*      */     
/*  822 */     if (null == value)
/*      */     {
/*  824 */       if (!OutputProperties.isLegalPropertyKey(qnameString)) {
/*  825 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { qnameString }));
/*      */       }
/*      */     }
/*      */     
/*  829 */     return value;
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
/*      */   public String getOutputPropertyNoDefault(String qnameString) throws IllegalArgumentException {
/*  850 */     String value = null;
/*  851 */     OutputProperties props = getOutputFormat();
/*      */     
/*  853 */     value = (String)props.getProperties().get(qnameString);
/*      */     
/*  855 */     if (null == value)
/*      */     {
/*  857 */       if (!OutputProperties.isLegalPropertyKey(qnameString)) {
/*  858 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { qnameString }));
/*      */       }
/*      */     }
/*      */     
/*  862 */     return value;
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
/*      */   public void setOutputProperty(String name, String value) throws IllegalArgumentException {
/*  880 */     synchronized (this.m_reentryGuard) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  885 */       if (null == this.m_outputFormat)
/*      */       {
/*  887 */         this.m_outputFormat = (OutputProperties)getStylesheet().getOutputComposed().clone();
/*      */       }
/*      */ 
/*      */       
/*  891 */       if (!OutputProperties.isLegalPropertyKey(name)) {
/*  892 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { name }));
/*      */       }
/*      */       
/*  895 */       this.m_outputFormat.setProperty(name, value);
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
/*      */   public void setOutputProperties(Properties oformat) throws IllegalArgumentException {
/*  921 */     synchronized (this.m_reentryGuard) {
/*      */       
/*  923 */       if (null != oformat) {
/*      */ 
/*      */ 
/*      */         
/*  927 */         String method = (String)oformat.get("method");
/*      */         
/*  929 */         if (null != method) {
/*  930 */           this.m_outputFormat = new OutputProperties(method);
/*  931 */         } else if (this.m_outputFormat == null) {
/*  932 */           this.m_outputFormat = new OutputProperties();
/*      */         } 
/*  934 */         this.m_outputFormat.copyFrom(oformat);
/*      */ 
/*      */ 
/*      */         
/*  938 */         this.m_outputFormat.copyFrom(this.m_stylesheetRoot.getOutputProperties());
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  943 */         this.m_outputFormat = null;
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
/*      */   public Properties getOutputProperties() {
/*  963 */     return (Properties)getOutputFormat().getProperties().clone();
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
/*      */   public SerializationHandler createSerializationHandler(Result outputTarget) throws TransformerException {
/*  981 */     SerializationHandler xoh = createSerializationHandler(outputTarget, getOutputFormat());
/*      */     
/*  983 */     return xoh;
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
/*      */   public SerializationHandler createSerializationHandler(Result outputTarget, OutputProperties format) throws TransformerException {
/*      */     SerializationHandler serializationHandler;
/* 1008 */     Node outputNode = null;
/*      */     
/* 1010 */     if (outputTarget instanceof DOMResult) {
/*      */       Document document; short s;
/* 1012 */       outputNode = ((DOMResult)outputTarget).getNode();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1017 */       if (null != outputNode) {
/*      */         
/* 1019 */         s = outputNode.getNodeType();
/* 1020 */         document = (9 == s) ? (Document)outputNode : outputNode.getOwnerDocument();
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1026 */         document = DOMHelper.createDocument();
/* 1027 */         outputNode = document;
/* 1028 */         s = outputNode.getNodeType();
/*      */         
/* 1030 */         ((DOMResult)outputTarget).setNode(outputNode);
/*      */       } 
/*      */       
/* 1033 */       DOMBuilder dOMBuilder = (11 == s) ? new DOMBuilder(document, (DocumentFragment)outputNode) : new DOMBuilder(document, outputNode);
/*      */ 
/*      */ 
/*      */       
/* 1037 */       String encoding = format.getProperty("encoding");
/* 1038 */       ToXMLSAXHandler toXMLSAXHandler = new ToXMLSAXHandler((ContentHandler)dOMBuilder, (LexicalHandler)dOMBuilder, encoding);
/*      */     }
/* 1040 */     else if (outputTarget instanceof SAXResult) {
/*      */       ToXMLSAXHandler toXMLSAXHandler; LexicalHandler lexicalHandler;
/* 1042 */       ContentHandler handler = ((SAXResult)outputTarget).getHandler();
/*      */       
/* 1044 */       if (null == handler) {
/* 1045 */         throw new IllegalArgumentException("handler can not be null for a SAXResult");
/*      */       }
/*      */ 
/*      */       
/* 1049 */       if (handler instanceof LexicalHandler) {
/* 1050 */         lexicalHandler = (LexicalHandler)handler;
/*      */       } else {
/* 1052 */         lexicalHandler = null;
/*      */       } 
/* 1054 */       String encoding = format.getProperty("encoding");
/* 1055 */       String method = format.getProperty("method");
/* 1056 */       if ("html".equals(method)) {
/*      */         
/* 1058 */         ToHTMLSAXHandler toHTMLSAXHandler = new ToHTMLSAXHandler(handler, lexicalHandler, encoding);
/*      */       }
/* 1060 */       else if ("text".equals(method)) {
/*      */         
/* 1062 */         ToTextSAXHandler toTextSAXHandler = new ToTextSAXHandler(handler, lexicalHandler, encoding);
/*      */       }
/*      */       else {
/*      */         
/* 1066 */         ToXMLSAXHandler toXMLSAXHandler1 = new ToXMLSAXHandler(handler, lexicalHandler, encoding);
/* 1067 */         toXMLSAXHandler1.setShouldOutputNSAttr(false);
/* 1068 */         toXMLSAXHandler = toXMLSAXHandler1;
/*      */       } 
/*      */ 
/*      */       
/* 1072 */       String publicID = format.getProperty("doctype-public");
/* 1073 */       String systemID = format.getProperty("doctype-system");
/* 1074 */       if (systemID != null)
/* 1075 */         toXMLSAXHandler.setDoctypeSystem(systemID); 
/* 1076 */       if (publicID != null) {
/* 1077 */         toXMLSAXHandler.setDoctypePublic(publicID);
/*      */       }
/* 1079 */       if (handler instanceof TransformerClient) {
/* 1080 */         XalanTransformState state = new XalanTransformState();
/* 1081 */         ((TransformerClient)handler).setTransformState(state);
/* 1082 */         ((ToSAXHandler)toXMLSAXHandler).setTransformState(state);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1090 */     else if (outputTarget instanceof StreamResult) {
/*      */       
/* 1092 */       StreamResult sresult = (StreamResult)outputTarget;
/* 1093 */       String method = format.getProperty("method");
/*      */ 
/*      */ 
/*      */       
/* 1097 */       try { SerializationHandler serializer = (SerializationHandler)SerializerFactory.getSerializer(format.getProperties());
/*      */ 
/*      */         
/* 1100 */         if (null != sresult.getWriter()) {
/* 1101 */           serializer.setWriter(sresult.getWriter());
/* 1102 */         } else if (null != sresult.getOutputStream()) {
/* 1103 */           serializer.setOutputStream(sresult.getOutputStream());
/* 1104 */         } else if (null != sresult.getSystemId()) {
/*      */           
/* 1106 */           String fileURL = sresult.getSystemId();
/*      */           
/* 1108 */           if (fileURL.startsWith("file:///"))
/*      */           {
/* 1110 */             if (fileURL.substring(8).indexOf(":") > 0) {
/* 1111 */               fileURL = fileURL.substring(8);
/*      */             } else {
/* 1113 */               fileURL = fileURL.substring(7);
/*      */             } 
/*      */           }
/* 1116 */           this.m_outputStream = new FileOutputStream(fileURL);
/*      */           
/* 1118 */           serializer.setOutputStream(this.m_outputStream);
/*      */           
/* 1120 */           SerializationHandler serializationHandler1 = serializer;
/*      */         } else {
/*      */           
/* 1123 */           throw new TransformerException(XSLMessages.createMessage("ER_NO_OUTPUT_SPECIFIED", null));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1129 */         serializationHandler = serializer; } catch (IOException ioe)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1137 */         throw new TransformerException(ioe); }
/*      */ 
/*      */     
/*      */     } else {
/*      */       
/* 1142 */       throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_TRANSFORM_TO_RESULT_TYPE", new Object[] { outputTarget.getClass().getName() }));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1149 */     serializationHandler.setTransformer(this);
/*      */     
/* 1151 */     StylesheetRoot stylesheetRoot = getStylesheet();
/* 1152 */     serializationHandler.setSourceLocator((SourceLocator)stylesheetRoot);
/*      */ 
/*      */     
/* 1155 */     return serializationHandler;
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
/*      */   public void transform(Source xmlSource, Result outputTarget) throws TransformerException {
/* 1170 */     transform(xmlSource, outputTarget, true);
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
/*      */   public void transform(Source xmlSource, Result outputTarget, boolean shouldRelease) throws TransformerException {
/* 1185 */     synchronized (this.m_reentryGuard) {
/*      */       
/* 1187 */       SerializationHandler xoh = createSerializationHandler(outputTarget);
/* 1188 */       setSerializationHandler(xoh);
/*      */       
/* 1190 */       this.m_outputTarget = outputTarget;
/*      */       
/* 1192 */       transform(xmlSource, shouldRelease);
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
/*      */   public void transformNode(int node, Result outputTarget) throws TransformerException {
/* 1211 */     SerializationHandler xoh = createSerializationHandler(outputTarget);
/* 1212 */     setSerializationHandler(xoh);
/*      */     
/* 1214 */     this.m_outputTarget = outputTarget;
/*      */     
/* 1216 */     transformNode(node);
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
/*      */   public void transformNode(int node) throws TransformerException {
/* 1232 */     setExtensionsTable(getStylesheet());
/*      */     
/* 1234 */     synchronized (this.m_serializationHandler) {
/*      */       
/* 1236 */       this.m_hasBeenReset = false;
/*      */       
/* 1238 */       XPathContext xctxt = getXPathContext();
/* 1239 */       DTM dtm = xctxt.getDTM(node);
/*      */ 
/*      */ 
/*      */       
/* 1243 */       try { pushGlobalVars(node);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1248 */         StylesheetRoot stylesheet = getStylesheet();
/* 1249 */         int n = stylesheet.getGlobalImportCount();
/*      */         
/* 1251 */         for (int i = 0; i < n; i++) {
/*      */           
/* 1253 */           StylesheetComposed imported = stylesheet.getGlobalImport(i);
/* 1254 */           int includedCount = imported.getIncludeCountComposed();
/*      */           
/* 1256 */           for (int j = -1; j < includedCount; j++) {
/*      */             
/* 1258 */             Stylesheet included = imported.getIncludeComposed(j);
/*      */             
/* 1260 */             included.runtimeInit(this);
/*      */             
/* 1262 */             ElemTemplateElement child = included.getFirstChildElem();
/* 1263 */             for (; child != null; child = child.getNextSiblingElem())
/*      */             {
/* 1265 */               child.runtimeInit(this);
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1271 */         SelfIteratorNoPredicate selfIteratorNoPredicate = new SelfIteratorNoPredicate();
/* 1272 */         selfIteratorNoPredicate.setRoot(node, xctxt);
/* 1273 */         xctxt.pushContextNodeList((DTMIterator)selfIteratorNoPredicate);
/*      */         
/*      */         try {
/* 1276 */           applyTemplateToNode(null, null, node);
/*      */         }
/*      */         finally {
/*      */           
/* 1280 */           xctxt.popContextNodeList();
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1285 */         if (null != this.m_serializationHandler)
/*      */         {
/* 1287 */           this.m_serializationHandler.endDocument(); }  } catch (Exception se)
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
/* 1300 */         while (se instanceof WrappedRuntimeException) {
/*      */           
/* 1302 */           Exception e = ((WrappedRuntimeException)se).getException();
/* 1303 */           if (null != e) {
/* 1304 */             se = e;
/*      */           }
/*      */         } 
/* 1307 */         if (null != this.m_serializationHandler) {
/*      */ 
/*      */           
/*      */           try { 
/* 1311 */             if (se instanceof SAXParseException)
/* 1312 */             { this.m_serializationHandler.fatalError((SAXParseException)se); }
/* 1313 */             else if (se instanceof TransformerException)
/*      */             
/* 1315 */             { TransformerException te = (TransformerException)se;
/* 1316 */               SAXSourceLocator sl = new SAXSourceLocator(te.getLocator());
/* 1317 */               this.m_serializationHandler.fatalError(new SAXParseException(te.getMessage(), (Locator)sl, te)); }
/*      */             
/*      */             else
/*      */             
/* 1321 */             { this.m_serializationHandler.fatalError(new SAXParseException(se.getMessage(), (Locator)new SAXSourceLocator(), se)); }  } catch (Exception exception) {}
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1327 */         if (se instanceof TransformerException)
/*      */         {
/* 1329 */           this.m_errorHandler.fatalError((TransformerException)se);
/*      */         }
/* 1331 */         else if (se instanceof SAXParseException)
/*      */         {
/* 1333 */           this.m_errorHandler.fatalError(new TransformerException(se.getMessage(), (SourceLocator)new SAXSourceLocator((SAXParseException)se), se));
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */           
/* 1339 */           this.m_errorHandler.fatalError(new TransformerException(se));
/*      */         }
/*      */          }
/*      */       
/*      */       finally
/*      */       
/* 1345 */       { reset(); }
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
/*      */   public ContentHandler getInputContentHandler() {
/* 1359 */     return getInputContentHandler(false);
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
/*      */   public ContentHandler getInputContentHandler(boolean doDocFrag) {
/* 1375 */     if (null == this.m_inputContentHandler)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1380 */       this.m_inputContentHandler = new TransformerHandlerImpl(this, doDocFrag, this.m_urlOfSource);
/*      */     }
/*      */ 
/*      */     
/* 1384 */     return this.m_inputContentHandler;
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
/*      */   public DeclHandler getInputDeclHandler() {
/* 1396 */     if (this.m_inputContentHandler instanceof DeclHandler) {
/* 1397 */       return (DeclHandler)this.m_inputContentHandler;
/*      */     }
/* 1399 */     return null;
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
/*      */   public LexicalHandler getInputLexicalHandler() {
/* 1411 */     if (this.m_inputContentHandler instanceof LexicalHandler) {
/* 1412 */       return (LexicalHandler)this.m_inputContentHandler;
/*      */     }
/* 1414 */     return null;
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
/*      */   public void setOutputFormat(OutputProperties oformat) {
/* 1427 */     this.m_outputFormat = oformat;
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
/*      */   public OutputProperties getOutputFormat() {
/* 1441 */     OutputProperties format = (null == this.m_outputFormat) ? getStylesheet().getOutputComposed() : this.m_outputFormat;
/*      */ 
/*      */ 
/*      */     
/* 1445 */     return format;
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
/*      */   public void setParameter(String name, String namespace, Object value) {
/* 1461 */     VariableStack varstack = getXPathContext().getVarStack();
/* 1462 */     QName qname = new QName(namespace, name);
/* 1463 */     XObject xobject = XObject.create(value, getXPathContext());
/*      */     
/* 1465 */     StylesheetRoot sroot = this.m_stylesheetRoot;
/* 1466 */     Vector vars = sroot.getVariablesAndParamsComposed();
/* 1467 */     int i = vars.size();
/* 1468 */     while (--i >= 0) {
/*      */       
/* 1470 */       ElemVariable variable = vars.elementAt(i);
/* 1471 */       if (variable.getXSLToken() == 41 && variable.getName().equals(qname))
/*      */       {
/*      */         
/* 1474 */         varstack.setGlobalVariable(i, xobject);
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
/*      */   public void setParameter(String name, Object value) {
/* 1495 */     if (value == null) {
/* 1496 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_SET_PARAM_VALUE", new Object[] { name }));
/*      */     }
/*      */     
/* 1499 */     StringTokenizer tokenizer = new StringTokenizer(name, "{}", false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1506 */     try { String s1 = tokenizer.nextToken();
/* 1507 */       String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
/*      */       
/* 1509 */       if (null == this.m_userParams) {
/* 1510 */         this.m_userParams = new Vector();
/*      */       }
/* 1512 */       if (null == s2)
/*      */       
/* 1514 */       { replaceOrPushUserParam(new QName(s1), XObject.create(value, getXPathContext()));
/* 1515 */         setParameter(s1, null, value); }
/*      */       
/*      */       else
/*      */       
/* 1519 */       { replaceOrPushUserParam(new QName(s1, s2), XObject.create(value, getXPathContext()));
/* 1520 */         setParameter(s2, s1, value); }  } catch (NoSuchElementException noSuchElementException) {}
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
/*      */   private void replaceOrPushUserParam(QName qname, XObject xval) {
/* 1540 */     int n = this.m_userParams.size();
/*      */     
/* 1542 */     for (int i = n - 1; i >= 0; i--) {
/*      */       
/* 1544 */       Arg arg = this.m_userParams.elementAt(i);
/*      */       
/* 1546 */       if (arg.getQName().equals(qname)) {
/*      */         
/* 1548 */         this.m_userParams.setElementAt(new Arg(qname, xval, true), i);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/* 1554 */     this.m_userParams.addElement(new Arg(qname, xval, true));
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
/*      */   public Object getParameter(String name) {
/*      */     
/* 1577 */     try { QName qname = QName.getQNameFromString(name);
/*      */       
/* 1579 */       if (null == this.m_userParams) {
/* 1580 */         return null;
/*      */       }
/* 1582 */       int n = this.m_userParams.size();
/*      */       
/* 1584 */       for (int i = n - 1; i >= 0; i--) {
/*      */         
/* 1586 */         Arg arg = this.m_userParams.elementAt(i);
/*      */         
/* 1588 */         if (arg.getQName().equals(qname))
/*      */         {
/* 1590 */           return arg.getVal().object();
/*      */         }
/*      */       } 
/*      */       
/* 1594 */       return null; } catch (NoSuchElementException nsee)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/* 1600 */       return null; }
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
/*      */   private void resetUserParameters() {
/*      */     try {
/* 1616 */       if (null == this.m_userParams) {
/*      */         return;
/*      */       }
/* 1619 */       int n = this.m_userParams.size();
/* 1620 */       for (int i = n - 1; i >= 0; i--) {
/*      */         
/* 1622 */         Arg arg = this.m_userParams.elementAt(i);
/* 1623 */         QName name = arg.getQName();
/*      */ 
/*      */         
/* 1626 */         String s1 = name.getNamespace();
/* 1627 */         String s2 = name.getLocalPart();
/*      */         
/* 1629 */         setParameter(s2, s1, arg.getVal().object());
/*      */       } 
/*      */     } catch (NoSuchElementException noSuchElementException) {}
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
/*      */   public void setParameters(Properties params) {
/* 1658 */     clearParameters();
/*      */     
/* 1660 */     Enumeration names = params.propertyNames();
/*      */     
/* 1662 */     while (names.hasMoreElements()) {
/*      */       
/* 1664 */       String name = params.getProperty((String)names.nextElement());
/* 1665 */       StringTokenizer tokenizer = new StringTokenizer(name, "{}", false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1672 */       try { String s1 = tokenizer.nextToken();
/* 1673 */         String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
/*      */         
/* 1675 */         if (null == s2) {
/* 1676 */           setParameter(s1, null, params.getProperty(name)); continue;
/*      */         } 
/* 1678 */         setParameter(s2, s1, params.getProperty(name)); } catch (NoSuchElementException noSuchElementException) {}
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
/*      */   public void clearParameters() {
/* 1694 */     synchronized (this.m_reentryGuard) {
/*      */       
/* 1696 */       VariableStack varstack = new VariableStack();
/*      */       
/* 1698 */       this.m_xcontext.setVarStack(varstack);
/*      */       
/* 1700 */       this.m_userParams = null;
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
/*      */   protected void pushGlobalVars(int contextNode) throws TransformerException {
/* 1727 */     XPathContext xctxt = this.m_xcontext;
/* 1728 */     VariableStack vs = xctxt.getVarStack();
/* 1729 */     StylesheetRoot sr = getStylesheet();
/* 1730 */     Vector vars = sr.getVariablesAndParamsComposed();
/*      */     
/* 1732 */     int i = vars.size();
/* 1733 */     vs.link(i);
/*      */     
/* 1735 */     while (--i >= 0) {
/*      */       
/* 1737 */       ElemVariable v = vars.elementAt(i);
/*      */ 
/*      */       
/* 1740 */       XUnresolvedVariable xUnresolvedVariable = new XUnresolvedVariable(v, contextNode, this, vs.getStackFrame(), 0, true);
/*      */ 
/*      */       
/* 1743 */       if (null == vs.elementAt(i)) {
/* 1744 */         vs.setGlobalVariable(i, (XObject)xUnresolvedVariable);
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
/*      */   public void setURIResolver(URIResolver resolver) {
/* 1758 */     synchronized (this.m_reentryGuard) {
/*      */       
/* 1760 */       this.m_xcontext.getSourceTreeManager().setURIResolver(resolver);
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
/*      */   public URIResolver getURIResolver() {
/* 1773 */     return this.m_xcontext.getSourceTreeManager().getURIResolver();
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
/*      */   public void setContentHandler(ContentHandler handler) {
/* 1791 */     if (handler == null)
/*      */     {
/* 1793 */       throw new NullPointerException(XSLMessages.createMessage("ER_NULL_CONTENT_HANDLER", null));
/*      */     }
/*      */ 
/*      */     
/* 1797 */     this.m_outputContentHandler = handler;
/*      */     
/* 1799 */     if (null == this.m_serializationHandler) {
/*      */       
/* 1801 */       ToXMLSAXHandler h = new ToXMLSAXHandler();
/* 1802 */       h.setContentHandler(handler);
/* 1803 */       h.setTransformer(this);
/*      */       
/* 1805 */       this.m_serializationHandler = (SerializationHandler)h;
/*      */     } else {
/*      */       
/* 1808 */       this.m_serializationHandler.setContentHandler(handler);
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
/*      */   public ContentHandler getContentHandler() {
/* 1820 */     return this.m_outputContentHandler;
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
/*      */   public int transformToRTF(ElemTemplateElement templateParent) throws TransformerException {
/* 1838 */     DTM dtmFrag = this.m_xcontext.getRTFDTM();
/* 1839 */     return transformToRTF(templateParent, dtmFrag);
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
/*      */   public int transformToGlobalRTF(ElemTemplateElement templateParent) throws TransformerException {
/* 1861 */     DTM dtmFrag = this.m_xcontext.getGlobalRTFDTM();
/* 1862 */     return transformToRTF(templateParent, dtmFrag);
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
/*      */   private int transformToRTF(ElemTemplateElement templateParent, DTM dtmFrag) throws TransformerException {
/*      */     int resultFragment;
/* 1879 */     XPathContext xctxt = this.m_xcontext;
/*      */     
/* 1881 */     ContentHandler rtfHandler = dtmFrag.getContentHandler();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1890 */     SerializationHandler savedRTreeHandler = this.m_serializationHandler;
/*      */ 
/*      */ 
/*      */     
/* 1894 */     ToXMLSAXHandler toXMLSAXHandler = new ToXMLSAXHandler();
/* 1895 */     toXMLSAXHandler.setContentHandler(rtfHandler);
/* 1896 */     toXMLSAXHandler.setTransformer(this);
/*      */ 
/*      */     
/* 1899 */     this.m_serializationHandler = (SerializationHandler)toXMLSAXHandler;
/*      */ 
/*      */     
/* 1902 */     SerializationHandler rth = this.m_serializationHandler;
/*      */ 
/*      */ 
/*      */     
/* 1906 */     try { rth.startDocument();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1911 */       rth.flushPending();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1917 */       try { executeChildTemplates(templateParent, true);
/*      */ 
/*      */         
/* 1920 */         rth.flushPending();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1926 */         resultFragment = dtmFrag.getDocument(); }
/*      */       
/*      */       finally
/*      */       
/* 1930 */       { rth.endDocument(); }  } catch (SAXException se)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/* 1935 */       throw new TransformerException(se);
/*      */        }
/*      */     
/*      */     finally
/*      */     
/*      */     { 
/* 1941 */       this.m_serializationHandler = savedRTreeHandler; }
/*      */ 
/*      */     
/* 1944 */     return resultFragment;
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
/*      */   public ObjectPool getStringWriterPool() {
/* 1956 */     return this.m_stringWriterObjectPool;
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
/*      */   public String transformToString(ElemTemplateElement elem) throws TransformerException {
/*      */     String result;
/* 1977 */     ElemTemplateElement firstChild = elem.getFirstChildElem();
/* 1978 */     if (null == firstChild)
/* 1979 */       return ""; 
/* 1980 */     if (elem.hasTextLitOnly() && TransformerFactoryImpl.m_optimize)
/*      */     {
/* 1982 */       return ((ElemTextLiteral)firstChild).getNodeValue();
/*      */     }
/*      */ 
/*      */     
/* 1986 */     SerializationHandler savedRTreeHandler = this.m_serializationHandler;
/*      */ 
/*      */ 
/*      */     
/* 1990 */     StringWriter sw = (StringWriter)this.m_stringWriterObjectPool.getInstance();
/*      */     
/* 1992 */     this.m_serializationHandler = (SerializationHandler)this.m_textResultHandlerObjectPool.getInstance();
/*      */ 
/*      */     
/* 1995 */     if (null == this.m_serializationHandler) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2000 */       Serializer serializer = SerializerFactory.getSerializer(this.m_textformat.getProperties());
/*      */       
/* 2002 */       this.m_serializationHandler = (SerializationHandler)serializer;
/*      */     } 
/*      */     
/* 2005 */     this.m_serializationHandler.setTransformer(this);
/* 2006 */     this.m_serializationHandler.setWriter(sw);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2019 */     try { executeChildTemplates(elem, true);
/* 2020 */       this.m_serializationHandler.endDocument();
/*      */       
/* 2022 */       result = sw.toString(); } catch (SAXException se)
/*      */     
/*      */     { 
/*      */       
/* 2026 */       throw new TransformerException(se); }
/*      */     
/*      */     finally
/*      */     
/* 2030 */     { sw.getBuffer().setLength(0);
/*      */ 
/*      */ 
/*      */       
/* 2034 */       try { sw.close(); } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */       
/* 2038 */       this.m_stringWriterObjectPool.freeInstance(sw);
/* 2039 */       this.m_serializationHandler.reset();
/* 2040 */       this.m_textResultHandlerObjectPool.freeInstance(this.m_serializationHandler);
/*      */ 
/*      */       
/* 2043 */       this.m_serializationHandler = savedRTreeHandler; }
/*      */ 
/*      */     
/* 2046 */     return result;
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
/*      */   public boolean applyTemplateToNode(ElemTemplateElement xslInstruction, ElemTemplate template, int child) throws TransformerException {
/* 2066 */     DTM dtm = this.m_xcontext.getDTM(child);
/* 2067 */     short nodeType = dtm.getNodeType(child);
/* 2068 */     boolean isDefaultTextRule = false;
/* 2069 */     boolean isApplyImports = false;
/*      */     
/* 2071 */     if (null == template) {
/*      */       byte b;
/* 2073 */       int endImportLevel = 0;
/* 2074 */       isApplyImports = (xslInstruction == null) ? false : ((xslInstruction.getXSLToken() == 72));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2079 */       if (isApplyImports) {
/*      */         
/* 2081 */         b = xslInstruction.getStylesheetComposed().getImportCountComposed() - 1;
/*      */         
/* 2083 */         endImportLevel = xslInstruction.getStylesheetComposed().getEndImportCountComposed();
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2088 */         b = -1;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2097 */       if (isApplyImports && b == -1) {
/*      */         
/* 2099 */         template = null;
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 2106 */         XPathContext xctxt = this.m_xcontext;
/*      */ 
/*      */         
/*      */         try {
/* 2110 */           xctxt.pushNamespaceContext((PrefixResolver)xslInstruction);
/*      */           
/* 2112 */           QName mode = getMode();
/*      */           
/* 2114 */           if (isApplyImports) {
/* 2115 */             template = this.m_stylesheetRoot.getTemplateComposed(xctxt, child, mode, b, endImportLevel, this.m_quietConflictWarnings, dtm);
/*      */           } else {
/*      */             
/* 2118 */             template = this.m_stylesheetRoot.getTemplateComposed(xctxt, child, mode, this.m_quietConflictWarnings, dtm);
/*      */           }
/*      */         
/*      */         }
/*      */         finally {
/*      */           
/* 2124 */           xctxt.popNamespaceContext();
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2130 */       if (null == template)
/*      */       {
/* 2132 */         switch (nodeType) {
/*      */           
/*      */           case 1:
/*      */           case 11:
/* 2136 */             template = this.m_stylesheetRoot.getDefaultRule();
/*      */             break;
/*      */           case 2:
/*      */           case 3:
/*      */           case 4:
/* 2141 */             template = this.m_stylesheetRoot.getDefaultTextRule();
/* 2142 */             isDefaultTextRule = true;
/*      */             break;
/*      */           case 9:
/* 2145 */             template = this.m_stylesheetRoot.getDefaultRootRule();
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 2150 */             return false;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */     } 
/*      */     
/* 2159 */     try { pushElemTemplateElement((ElemTemplateElement)template);
/* 2160 */       this.m_xcontext.pushCurrentNode(child);
/* 2161 */       pushPairCurrentMatched((ElemTemplateElement)template, child);
/*      */ 
/*      */       
/* 2164 */       if (!isApplyImports) {
/* 2165 */         NodeSetDTM nodeSetDTM = new NodeSetDTM(child, this.m_xcontext.getDTMManager());
/* 2166 */         this.m_xcontext.pushContextNodeList((DTMIterator)nodeSetDTM);
/*      */       } 
/*      */       
/* 2169 */       if (isDefaultTextRule)
/*      */       
/* 2171 */       { switch (nodeType) {
/*      */           
/*      */           case 3:
/*      */           case 4:
/* 2175 */             ClonerToResultTree.cloneToResultTree(child, nodeType, dtm, getResultTreeHandler(), false);
/*      */             break;
/*      */           
/*      */           case 2:
/* 2179 */             dtm.dispatchCharactersEvents(child, (ContentHandler)getResultTreeHandler(), false);
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */          }
/*      */       else
/* 2188 */       { if (S_DEBUG) {
/* 2189 */           getTraceManager().fireTraceEvent((ElemTemplateElement)template);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2199 */         this.m_xcontext.setSAXLocator((SourceLocator)template);
/*      */         
/* 2201 */         this.m_xcontext.getVarStack().link(template.m_frameSize);
/* 2202 */         executeChildTemplates((ElemTemplateElement)template, true);
/*      */         
/* 2204 */         if (S_DEBUG)
/* 2205 */           getTraceManager().fireTraceEndEvent((ElemTemplateElement)template);  }  } catch (SAXException se)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/* 2210 */       throw new TransformerException(se); }
/*      */     
/*      */     finally
/*      */     
/* 2214 */     { if (!isDefaultTextRule)
/* 2215 */         this.m_xcontext.getVarStack().unlink(); 
/* 2216 */       this.m_xcontext.popCurrentNode();
/* 2217 */       if (!isApplyImports) {
/* 2218 */         this.m_xcontext.popContextNodeList();
/* 2219 */         popCurrentMatched();
/*      */       } 
/* 2221 */       popElemTemplateElement(); }
/*      */ 
/*      */     
/* 2224 */     return true;
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
/*      */   public void executeChildTemplates(ElemTemplateElement elem, Node context, QName mode, ContentHandler handler) throws TransformerException {
/* 2248 */     XPathContext xctxt = this.m_xcontext;
/*      */ 
/*      */     
/*      */     try {
/* 2252 */       if (null != mode)
/* 2253 */         pushMode(mode); 
/* 2254 */       xctxt.pushCurrentNode(xctxt.getDTMHandleFromNode(context));
/* 2255 */       executeChildTemplates(elem, handler);
/*      */     }
/*      */     finally {
/*      */       
/* 2259 */       xctxt.popCurrentNode();
/*      */ 
/*      */ 
/*      */       
/* 2263 */       if (null != mode) {
/* 2264 */         popMode();
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
/*      */   public void executeChildTemplates(ElemTemplateElement elem, boolean shouldAddAttrs) throws TransformerException {
/* 2288 */     ElemTemplateElement t = elem.getFirstChildElem();
/*      */     
/* 2290 */     if (null == t) {
/*      */       return;
/*      */     }
/* 2293 */     if (elem.hasTextLitOnly() && TransformerFactoryImpl.m_optimize) {
/*      */       
/* 2295 */       char[] chars = ((ElemTextLiteral)t).getChars();
/*      */ 
/*      */ 
/*      */       
/* 2299 */       try { pushElemTemplateElement(t);
/* 2300 */         this.m_serializationHandler.characters(chars, 0, chars.length); } catch (SAXException se)
/*      */       
/*      */       { 
/*      */         
/* 2304 */         throw new TransformerException(se); }
/*      */       
/*      */       finally
/*      */       
/* 2308 */       { popElemTemplateElement(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2319 */     XPathContext xctxt = this.m_xcontext;
/* 2320 */     xctxt.pushSAXLocatorNull();
/* 2321 */     int currentTemplateElementsTop = this.m_currentTemplateElements.size();
/* 2322 */     this.m_currentTemplateElements.push(null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2328 */       for (; t != null; t = t.getNextSiblingElem()) {
/*      */         
/* 2330 */         if (shouldAddAttrs || t.getXSLToken() != 48) {
/*      */ 
/*      */ 
/*      */           
/* 2334 */           xctxt.setSAXLocator((SourceLocator)t);
/* 2335 */           this.m_currentTemplateElements.setElementAt(t, currentTemplateElementsTop);
/* 2336 */           t.execute(this);
/*      */         } 
/*      */       } 
/*      */     } catch (RuntimeException re) {
/*      */       
/* 2341 */       TransformerException te = new TransformerException(re);
/* 2342 */       te.setLocator((SourceLocator)t);
/* 2343 */       throw te;
/*      */     }
/*      */     finally {
/*      */       
/* 2347 */       this.m_currentTemplateElements.pop();
/* 2348 */       xctxt.popSAXLocator();
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
/*      */   public void executeChildTemplates(ElemTemplateElement elem, ContentHandler handler) throws TransformerException {
/* 2371 */     SerializationHandler xoh = getSerializationHandler();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2376 */     SerializationHandler savedHandler = xoh;
/*      */ 
/*      */ 
/*      */     
/* 2380 */     try { xoh.flushPending();
/*      */ 
/*      */       
/* 2383 */       LexicalHandler lex = null;
/* 2384 */       if (handler instanceof LexicalHandler) {
/* 2385 */         lex = (LexicalHandler)handler;
/*      */       }
/* 2387 */       this.m_serializationHandler = (SerializationHandler)new ToXMLSAXHandler(handler, lex, savedHandler.getEncoding());
/* 2388 */       this.m_serializationHandler.setTransformer(this);
/* 2389 */       executeChildTemplates(elem, true); } catch (TransformerException e)
/*      */     
/*      */     { 
/*      */       
/* 2393 */       throw e; } catch (SAXException se)
/*      */     
/*      */     { 
/* 2396 */       throw new TransformerException(se); }
/*      */     
/*      */     finally
/*      */     
/* 2400 */     { this.m_serializationHandler = savedHandler; }
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
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector processSortKeys(ElemForEach foreach, int sourceNodeContext) throws TransformerException {
/* 2421 */     Vector keys = null;
/* 2422 */     XPathContext xctxt = this.m_xcontext;
/* 2423 */     int nElems = foreach.getSortElemCount();
/*      */     
/* 2425 */     if (nElems > 0) {
/* 2426 */       keys = new Vector();
/*      */     }
/*      */     
/* 2429 */     for (int i = 0; i < nElems; i++) {
/*      */       boolean bool;
/* 2431 */       ElemSort sort = foreach.getSortElem(i);
/*      */       
/* 2433 */       if (S_DEBUG) {
/* 2434 */         getTraceManager().fireTraceEvent((ElemTemplateElement)sort);
/*      */       }
/* 2436 */       String langString = (null != sort.getLang()) ? sort.getLang().evaluate(xctxt, sourceNodeContext, (PrefixResolver)foreach) : null;
/*      */ 
/*      */       
/* 2439 */       String dataTypeString = sort.getDataType().evaluate(xctxt, sourceNodeContext, (PrefixResolver)foreach);
/*      */ 
/*      */       
/* 2442 */       if (dataTypeString.indexOf(":") >= 0) {
/* 2443 */         System.out.println("TODO: Need to write the hooks for QNAME sort data type");
/*      */       }
/* 2445 */       else if (!dataTypeString.equalsIgnoreCase("text") && !dataTypeString.equalsIgnoreCase("number")) {
/*      */ 
/*      */         
/* 2448 */         foreach.error("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "data-type", dataTypeString });
/*      */       } 
/*      */ 
/*      */       
/* 2452 */       boolean treatAsNumbers = (null != dataTypeString && dataTypeString.equals("number"));
/*      */ 
/*      */       
/* 2455 */       String orderString = sort.getOrder().evaluate(xctxt, sourceNodeContext, (PrefixResolver)foreach);
/*      */ 
/*      */       
/* 2458 */       if (!orderString.equalsIgnoreCase("ascending") && !orderString.equalsIgnoreCase("descending"))
/*      */       {
/*      */         
/* 2461 */         foreach.error("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "order", orderString });
/*      */       }
/*      */ 
/*      */       
/* 2465 */       boolean descending = (null != orderString && orderString.equals("descending"));
/*      */ 
/*      */       
/* 2468 */       AVT caseOrder = sort.getCaseOrder();
/*      */ 
/*      */       
/* 2471 */       if (null != caseOrder) {
/*      */         
/* 2473 */         String caseOrderString = caseOrder.evaluate(xctxt, sourceNodeContext, (PrefixResolver)foreach);
/*      */ 
/*      */         
/* 2476 */         if (!caseOrderString.equalsIgnoreCase("upper-first") && !caseOrderString.equalsIgnoreCase("lower-first"))
/*      */         {
/*      */           
/* 2479 */           foreach.error("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "case-order", caseOrderString });
/*      */         }
/*      */ 
/*      */         
/* 2483 */         bool = (null != caseOrderString && caseOrderString.equals("upper-first")) ? true : false;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 2489 */         bool = false;
/*      */       } 
/*      */       
/* 2492 */       keys.addElement(new NodeSortKey(this, sort.getSelect(), treatAsNumbers, descending, langString, bool, (PrefixResolver)foreach));
/*      */ 
/*      */       
/* 2495 */       if (S_DEBUG) {
/* 2496 */         getTraceManager().fireTraceEndEvent((ElemTemplateElement)sort);
/*      */       }
/*      */     } 
/* 2499 */     return keys;
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
/*      */   public Vector getElementCallstack() {
/* 2514 */     Vector elems = new Vector();
/* 2515 */     int nStackSize = this.m_currentTemplateElements.size();
/* 2516 */     for (int i = 0; i < nStackSize; i++) {
/*      */       
/* 2518 */       ElemTemplateElement elem = (ElemTemplateElement)this.m_currentTemplateElements.elementAt(i);
/* 2519 */       if (null != elem)
/*      */       {
/* 2521 */         elems.addElement(elem);
/*      */       }
/*      */     } 
/* 2524 */     return elems;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentTemplateElementsCount() {
/* 2535 */     return this.m_currentTemplateElements.size();
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
/*      */   public ObjectStack getCurrentTemplateElements() {
/* 2547 */     return this.m_currentTemplateElements;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pushElemTemplateElement(ElemTemplateElement elem) {
/* 2558 */     this.m_currentTemplateElements.push(elem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void popElemTemplateElement() {
/* 2566 */     this.m_currentTemplateElements.pop();
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
/*      */   public void setCurrentElement(ElemTemplateElement e) {
/* 2578 */     this.m_currentTemplateElements.setTop(e);
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
/*      */   public ElemTemplateElement getCurrentElement() {
/* 2590 */     return (this.m_currentTemplateElements.size() > 0) ? (ElemTemplateElement)this.m_currentTemplateElements.peek() : null;
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
/*      */   public int getCurrentNode() {
/* 2602 */     return this.m_xcontext.getCurrentNode();
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
/*      */   public Vector getTemplateCallstack() {
/* 2614 */     Vector elems = new Vector();
/* 2615 */     int nStackSize = this.m_currentTemplateElements.size();
/* 2616 */     for (int i = 0; i < nStackSize; i++) {
/*      */       
/* 2618 */       ElemTemplateElement elem = (ElemTemplateElement)this.m_currentTemplateElements.elementAt(i);
/* 2619 */       if (null != elem && elem.getXSLToken() != 19)
/*      */       {
/* 2621 */         elems.addElement(elem);
/*      */       }
/*      */     } 
/* 2624 */     return elems;
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
/*      */   public ElemTemplate getCurrentTemplate() {
/* 2642 */     ElemTemplateElement elem = getCurrentElement();
/*      */ 
/*      */     
/* 2645 */     while (null != elem && elem.getXSLToken() != 19)
/*      */     {
/* 2647 */       elem = elem.getParentElem();
/*      */     }
/*      */     
/* 2650 */     return (ElemTemplate)elem;
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
/*      */   public void pushPairCurrentMatched(ElemTemplateElement template, int child) {
/* 2663 */     this.m_currentMatchTemplates.push(template);
/* 2664 */     this.m_currentMatchedNodes.push(child);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void popCurrentMatched() {
/* 2672 */     this.m_currentMatchTemplates.pop();
/* 2673 */     this.m_currentMatchedNodes.pop();
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
/*      */   public ElemTemplate getMatchedTemplate() {
/* 2687 */     return this.m_currentMatchTemplates.peek();
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
/*      */   public int getMatchedNode() {
/* 2699 */     return this.m_currentMatchedNodes.peepTail();
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
/*      */   public DTMIterator getContextNodeList() {
/*      */     
/* 2712 */     try { DTMIterator cnl = this.m_xcontext.getContextNodeList();
/*      */       
/* 2714 */       return (cnl == null) ? null : cnl.cloneWithReset(); } catch (CloneNotSupportedException cnse)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/* 2720 */       return null; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transformer getTransformer() {
/* 2731 */     return this;
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
/*      */   public void setStylesheet(StylesheetRoot stylesheetRoot) {
/* 2750 */     this.m_stylesheetRoot = stylesheetRoot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final StylesheetRoot getStylesheet() {
/* 2761 */     return this.m_stylesheetRoot;
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
/*      */   public boolean getQuietConflictWarnings() {
/* 2774 */     return this.m_quietConflictWarnings;
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
/*      */   public void setQuietConflictWarnings(boolean b) {
/* 2788 */     this.m_quietConflictWarnings = b;
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
/*      */   public void setXPathContext(XPathContext xcontext) {
/* 2800 */     this.m_xcontext = xcontext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final XPathContext getXPathContext() {
/* 2810 */     return this.m_xcontext;
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
/*      */   public StackGuard getStackGuard() {
/* 2822 */     return this.m_stackGuard;
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
/*      */   public int getRecursionLimit() {
/* 2839 */     return this.m_stackGuard.getRecursionLimit();
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
/*      */   public void setRecursionLimit(int limit) {
/* 2857 */     this.m_stackGuard.setRecursionLimit(limit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SerializationHandler getResultTreeHandler() {
/* 2868 */     return this.m_serializationHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SerializationHandler getSerializationHandler() {
/* 2879 */     return this.m_serializationHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public KeyManager getKeyManager() {
/* 2890 */     return this.m_keyManager;
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
/*      */   public boolean isRecursiveAttrSet(ElemAttributeSet attrSet) {
/* 2903 */     if (null == this.m_attrSetStack)
/*      */     {
/* 2905 */       this.m_attrSetStack = new Stack();
/*      */     }
/*      */     
/* 2908 */     if (!this.m_attrSetStack.empty()) {
/*      */       
/* 2910 */       int loc = this.m_attrSetStack.search(attrSet);
/*      */       
/* 2912 */       if (loc > -1)
/*      */       {
/* 2914 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 2918 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pushElemAttributeSet(ElemAttributeSet attrSet) {
/* 2929 */     this.m_attrSetStack.push(attrSet);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void popElemAttributeSet() {
/* 2937 */     this.m_attrSetStack.pop();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CountersTable getCountersTable() {
/* 2948 */     if (null == this.m_countersTable) {
/* 2949 */       this.m_countersTable = new CountersTable();
/*      */     }
/* 2951 */     return this.m_countersTable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean currentTemplateRuleIsNull() {
/* 2962 */     return (!this.m_currentTemplateRuleIsNull.isEmpty() && this.m_currentTemplateRuleIsNull.peek() == true);
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
/*      */   public void pushCurrentTemplateRuleIsNull(boolean b) {
/* 2975 */     this.m_currentTemplateRuleIsNull.push(b);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void popCurrentTemplateRuleIsNull() {
/* 2984 */     this.m_currentTemplateRuleIsNull.pop();
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
/*      */   public void pushCurrentFuncResult(Object val) {
/* 2996 */     this.m_currentFuncResult.push(val);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object popCurrentFuncResult() {
/* 3005 */     return this.m_currentFuncResult.pop();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean currentFuncResultSeen() {
/* 3016 */     return (!this.m_currentFuncResult.empty() && this.m_currentFuncResult.peek() != null);
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
/*      */   public MsgMgr getMsgMgr() {
/* 3028 */     if (null == this.m_msgMgr) {
/* 3029 */       this.m_msgMgr = new MsgMgr(this);
/*      */     }
/* 3031 */     return this.m_msgMgr;
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
/*      */   public void setErrorListener(ErrorListener listener) throws IllegalArgumentException {
/* 3044 */     synchronized (this.m_reentryGuard) {
/*      */       
/* 3046 */       if (listener == null) {
/* 3047 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_NULL_ERROR_HANDLER", null));
/*      */       }
/* 3049 */       this.m_errorHandler = listener;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorListener getErrorListener() {
/* 3060 */     return this.m_errorHandler;
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
/*      */   public TraceManager getTraceManager() {
/* 3072 */     return this.m_traceManager;
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
/*      */   public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
/* 3111 */     if ("http://xml.org/trax/features/sax/input".equals(name))
/* 3112 */       return true; 
/* 3113 */     if ("http://xml.org/trax/features/dom/input".equals(name)) {
/* 3114 */       return true;
/*      */     }
/* 3116 */     throw new SAXNotRecognizedException(name);
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
/*      */   public QName getMode() {
/* 3129 */     return this.m_modes.isEmpty() ? null : this.m_modes.peek();
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
/*      */   public void pushMode(QName mode) {
/* 3142 */     this.m_modes.push(mode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void popMode() {
/* 3153 */     this.m_modes.pop();
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
/*      */   public void runTransformThread(int priority) {
/* 3166 */     Thread t = ThreadControllerWrapper.runThread(this, priority);
/* 3167 */     setTransformThread(t);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void runTransformThread() {
/* 3177 */     ThreadControllerWrapper.runThread(this, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void runTransformThread(Runnable runnable) {
/* 3188 */     ThreadControllerWrapper.runThread(runnable, -1);
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
/*      */   public void waitTransformThread() throws SAXException {
/* 3205 */     Thread transformThread = getTransformThread();
/*      */     
/* 3207 */     if (null != transformThread) {
/*      */ 
/*      */       
/*      */       try { 
/* 3211 */         ThreadControllerWrapper.waitThread(transformThread, this);
/*      */         
/* 3213 */         if (!hasTransformThreadErrorCatcher()) {
/*      */           
/* 3215 */           Exception e = getExceptionThrown();
/*      */           
/* 3217 */           if (null != e) {
/*      */             
/* 3219 */             e.printStackTrace();
/* 3220 */             throw new SAXException(e);
/*      */           } 
/*      */         } 
/*      */         
/* 3224 */         setTransformThread(null); } catch (InterruptedException interruptedException) {}
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
/*      */   public Exception getExceptionThrown() {
/* 3239 */     return this.m_exceptionThrown;
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
/*      */   public void setExceptionThrown(Exception e) {
/* 3251 */     this.m_exceptionThrown = e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSourceTreeDocForThread(int doc) {
/* 3262 */     this.m_doc = doc;
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
/*      */   public void setXMLSource(Source source) {
/* 3274 */     this.m_xmlSource = source;
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
/*      */   public boolean isTransformDone() {
/* 3286 */     synchronized (this) {
/*      */       
/* 3288 */       return this.m_isTransformDone;
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
/*      */   public void setIsTransformDone(boolean done) {
/* 3301 */     synchronized (this) {
/*      */       
/* 3303 */       this.m_isTransformDone = done;
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
/*      */   void postExceptionFromThread(Exception e) {
/* 3336 */     this.m_isTransformDone = true;
/* 3337 */     this.m_exceptionThrown = e;
/*      */ 
/*      */     
/* 3340 */     synchronized (this) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3347 */       notifyAll();
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
/*      */   public void run() {
/* 3365 */     this.m_hasBeenReset = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try { 
/* 3374 */       try { this.m_isTransformDone = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3383 */         transformNode(this.m_doc); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3391 */         if (null != this.m_transformThread) {
/* 3392 */           postExceptionFromThread(e);
/*      */         } else {
/* 3394 */           throw new RuntimeException(e.getMessage());
/*      */         }  }
/*      */       finally
/*      */       
/* 3398 */       { this.m_isTransformDone = true;
/*      */         
/* 3400 */         if (this.m_inputContentHandler instanceof TransformerHandlerImpl)
/*      */         {
/* 3402 */           ((TransformerHandlerImpl)this.m_inputContentHandler).clearCoRoutine(); }  }  } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3415 */       if (null != this.m_transformThread) {
/* 3416 */         postExceptionFromThread(e);
/*      */       } else {
/* 3418 */         throw new RuntimeException(e.getMessage());
/*      */       }  }
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
/*      */   public TransformSnapshot getSnapshot() {
/* 3433 */     return new TransformSnapshotImpl(this);
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
/*      */   public void executeFromSnapshot(TransformSnapshot ts) throws TransformerException {
/* 3450 */     ElemTemplate elemTemplate = getMatchedTemplate();
/* 3451 */     int child = getMatchedNode();
/*      */     
/* 3453 */     pushElemTemplateElement((ElemTemplateElement)elemTemplate);
/* 3454 */     this.m_xcontext.pushCurrentNode(child);
/* 3455 */     executeChildTemplates((ElemTemplateElement)elemTemplate, true);
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
/*      */   public void resetToStylesheet(TransformSnapshot ts) {
/* 3467 */     ((TransformSnapshotImpl)ts).apply(this);
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
/*      */   public void stopTransformation() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShouldStripSpace(int elementHandle, DTM dtm) {
/*      */     
/* 3491 */     try { WhiteSpaceInfo info = this.m_stylesheetRoot.getWhiteSpaceInfo(this.m_xcontext, elementHandle, dtm);
/*      */ 
/*      */       
/* 3494 */       if (null == info)
/*      */       {
/* 3496 */         return 3;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3502 */       return info.getShouldStripSpace() ? 2 : 1; } catch (TransformerException se)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/* 3508 */       return 3; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void init(ToXMLSAXHandler h, Transformer transformer, ContentHandler realHandler) {
/* 3519 */     h.setTransformer(transformer);
/* 3520 */     h.setContentHandler(realHandler);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSerializationHandler(SerializationHandler xoh) {
/* 3525 */     this.m_serializationHandler = xoh;
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
/*      */   public void fireGenerateEvent(int eventType, char[] ch, int start, int length) {
/* 3540 */     GenerateEvent ge = new GenerateEvent(this, eventType, ch, start, length);
/* 3541 */     this.m_traceManager.fireGenerateEvent(ge);
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
/*      */   public void fireGenerateEvent(int eventType, String name, Attributes atts) {
/* 3553 */     GenerateEvent ge = new GenerateEvent(this, eventType, name, atts);
/* 3554 */     this.m_traceManager.fireGenerateEvent(ge);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireGenerateEvent(int eventType, String name, String data) {
/* 3562 */     GenerateEvent ge = new GenerateEvent(this, eventType, name, data);
/* 3563 */     this.m_traceManager.fireGenerateEvent(ge);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireGenerateEvent(int eventType, String data) {
/* 3571 */     GenerateEvent ge = new GenerateEvent(this, eventType, data);
/* 3572 */     this.m_traceManager.fireGenerateEvent(ge);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireGenerateEvent(int eventType) {
/* 3580 */     GenerateEvent ge = new GenerateEvent(this, eventType);
/* 3581 */     this.m_traceManager.fireGenerateEvent(ge);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasTraceListeners() {
/* 3588 */     return this.m_traceManager.hasTraceListeners();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/TransformerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */