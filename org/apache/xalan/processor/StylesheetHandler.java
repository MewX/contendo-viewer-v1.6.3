/*      */ package org.apache.xalan.processor;
/*      */ 
/*      */ import java.util.EmptyStackException;
/*      */ import java.util.Stack;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.Templates;
/*      */ import javax.xml.transform.TransformerConfigurationException;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.sax.TemplatesHandler;
/*      */ import org.apache.xalan.extensions.ExpressionVisitor;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xalan.templates.ElemForEach;
/*      */ import org.apache.xalan.templates.ElemTemplateElement;
/*      */ import org.apache.xalan.templates.FuncDocument;
/*      */ import org.apache.xalan.templates.FuncFormatNumb;
/*      */ import org.apache.xalan.templates.Stylesheet;
/*      */ import org.apache.xalan.templates.StylesheetRoot;
/*      */ import org.apache.xml.utils.BoolStack;
/*      */ import org.apache.xml.utils.NamespaceSupport2;
/*      */ import org.apache.xml.utils.NodeConsumer;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xml.utils.SAXSourceLocator;
/*      */ import org.apache.xml.utils.XMLCharacterRecognizer;
/*      */ import org.apache.xpath.Expression;
/*      */ import org.apache.xpath.ExpressionOwner;
/*      */ import org.apache.xpath.XPath;
/*      */ import org.apache.xpath.XPathVisitor;
/*      */ import org.apache.xpath.compiler.FunctionTable;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ import org.xml.sax.helpers.NamespaceSupport;
/*      */ 
/*      */ public class StylesheetHandler extends DefaultHandler implements TemplatesHandler, NodeConsumer, PrefixResolver {
/*      */   private int m_stylesheetLevel;
/*      */   private boolean m_parsingComplete;
/*      */   private Vector m_prefixMappings;
/*      */   private boolean m_shouldProcess;
/*      */   private String m_fragmentIDString;
/*      */   private int m_elementID;
/*      */   private int m_fragmentID;
/*      */   private TransformerFactoryImpl m_stylesheetProcessor;
/*      */   static final int STYPE_ROOT = 1;
/*      */   static final int STYPE_INCLUDE = 2;
/*      */   static final int STYPE_IMPORT = 3;
/*      */   private int m_stylesheetType;
/*      */   private Stack m_stylesheets;
/*      */   StylesheetRoot m_stylesheetRoot;
/*      */   Stylesheet m_lastPoppedStylesheet;
/*      */   private Stack m_processors;
/*      */   private XSLTSchema m_schema;
/*      */   private Stack m_elems;
/*      */   private int m_docOrderCount;
/*      */   Stack m_baseIdentifiers;
/*      */   private Stack m_stylesheetLocatorStack;
/*      */   private Stack m_importStack;
/*      */   private boolean warnedAboutOldXSLTNamespace;
/*      */   Stack m_nsSupportStack;
/*      */   private Node m_originatingNode;
/*      */   private BoolStack m_spacePreserveStack;
/*      */   
/*      */   static {
/*   69 */     FuncDocument funcDocument = new FuncDocument();
/*      */     
/*   71 */     FunctionTable.installFunction("document", (Expression)funcDocument);
/*      */ 
/*      */ 
/*      */     
/*   75 */     FuncFormatNumb funcFormatNumb = new FuncFormatNumb();
/*      */     
/*   77 */     FunctionTable.installFunction("format-number", (Expression)funcFormatNumb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void init(TransformerFactoryImpl processor) {
/*      */     this.m_stylesheetProcessor = processor;
/*      */     this.m_processors.push(this.m_schema.getElementProcessor());
/*      */     pushNewNamespaceSupport();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XPath createXPath(String str, ElemTemplateElement owningTemplate) throws TransformerException {
/*      */     ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
/*      */     XPath xpath = new XPath(str, (SourceLocator)owningTemplate, this, 0, handler);
/*      */     xpath.callVisitors((ExpressionOwner)xpath, (XPathVisitor)new ExpressionVisitor(getStylesheetRoot()));
/*      */     return xpath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   XPath createMatchPatternXPath(String str, ElemTemplateElement owningTemplate) throws TransformerException {
/*      */     ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
/*      */     XPath xpath = new XPath(str, (SourceLocator)owningTemplate, this, 1, handler);
/*      */     xpath.callVisitors((ExpressionOwner)xpath, (XPathVisitor)new ExpressionVisitor(getStylesheetRoot()));
/*      */     return xpath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceForPrefix(String prefix) {
/*      */     return getNamespaceSupport().getURI(prefix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceForPrefix(String prefix, Node context) {
/*      */     assertion(true, "can't process a context node in StylesheetHandler!");
/*      */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean stackContains(Stack stack, String url) {
/*      */     int n = stack.size();
/*      */     boolean contains = false;
/*      */     for (int i = 0; i < n; i++) {
/*      */       String url2 = stack.elementAt(i);
/*      */       if (url2.equals(url)) {
/*      */         contains = true;
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     return contains;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Templates getTemplates() {
/*      */     return (Templates)getStylesheetRoot();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSystemId(String baseID) {
/*      */     pushBaseIndentifier(baseID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSystemId() {
/*      */     return getBaseIdentifier();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/*      */     return getCurrentProcessor().resolveEntity(this, publicId, systemId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void notationDecl(String name, String publicId, String systemId) {
/*      */     getCurrentProcessor().notationDecl(this, name, publicId, systemId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) {
/*      */     getCurrentProcessor().unparsedEntityDecl(this, name, publicId, systemId, notationName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   XSLTElementProcessor getProcessorFor(String uri, String localName, String rawName) throws SAXException {
/*      */     XSLTElementProcessor currentProcessor = getCurrentProcessor();
/*      */     XSLTElementDef def = currentProcessor.getElemDef();
/*      */     XSLTElementProcessor elemProcessor = def.getProcessorFor(uri, localName);
/*      */     if (null == elemProcessor && !(currentProcessor instanceof ProcessorStylesheetDoc) && (null == getStylesheet() || Double.valueOf(getStylesheet().getVersion()).doubleValue() > 1.0D || (!uri.equals("http://www.w3.org/1999/XSL/Transform") && currentProcessor instanceof ProcessorStylesheetElement) || getElemVersion() > 1.0D)) {
/*      */       elemProcessor = def.getProcessorForUnknown(uri, localName);
/*      */     }
/*      */     if (null == elemProcessor) {
/*      */       error(XSLMessages.createMessage("ER_NOT_ALLOWED_IN_POSITION", new Object[] { rawName }), null);
/*      */     }
/*      */     return elemProcessor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {
/*      */     this.m_stylesheetLocatorStack.push(new SAXSourceLocator(locator));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() throws SAXException {
/*      */     this.m_stylesheetLevel++;
/*      */     pushSpaceHandling(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStylesheetParsingComplete() {
/*      */     return this.m_parsingComplete;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument() throws SAXException {
/*      */     try {
/*      */       if (null != getStylesheetRoot()) {
/*      */         if (0 == this.m_stylesheetLevel) {
/*      */           getStylesheetRoot().recompose();
/*      */         }
/*      */       } else {
/*      */         throw new TransformerException(XSLMessages.createMessage("ER_NO_STYLESHEETROOT", null));
/*      */       } 
/*      */       XSLTElementProcessor elemProcessor = getCurrentProcessor();
/*      */       if (null != elemProcessor) {
/*      */         elemProcessor.startNonText(this);
/*      */       }
/*      */       this.m_stylesheetLevel--;
/*      */       popSpaceHandling();
/*      */       this.m_parsingComplete = (this.m_stylesheetLevel < 0);
/*      */     } catch (TransformerException te) {
/*      */       throw new SAXException(te);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*      */     this.m_prefixMappings.addElement(prefix);
/*      */     this.m_prefixMappings.addElement(uri);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endPrefixMapping(String prefix) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void flushCharacters() throws SAXException {
/*      */     XSLTElementProcessor elemProcessor = getCurrentProcessor();
/*      */     if (null != elemProcessor) {
/*      */       elemProcessor.startNonText(this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*      */     NamespaceSupport nssupport = getNamespaceSupport();
/*      */     nssupport.pushContext();
/*      */     int n = this.m_prefixMappings.size();
/*      */     for (int i = 0; i < n; i++) {
/*      */       String prefix = this.m_prefixMappings.elementAt(i++);
/*      */       String nsURI = this.m_prefixMappings.elementAt(i);
/*      */       nssupport.declarePrefix(prefix, nsURI);
/*      */     } 
/*      */     this.m_prefixMappings.removeAllElements();
/*      */     this.m_elementID++;
/*      */     checkForFragmentID(attributes);
/*      */     if (!this.m_shouldProcess) {
/*      */       return;
/*      */     }
/*      */     flushCharacters();
/*      */     pushSpaceHandling(attributes);
/*      */     XSLTElementProcessor elemProcessor = getProcessorFor(uri, localName, rawName);
/*      */     if (null != elemProcessor) {
/*      */       pushProcessor(elemProcessor);
/*      */       elemProcessor.startElement(this, uri, localName, rawName, attributes);
/*      */     } else {
/*      */       this.m_shouldProcess = false;
/*      */       popSpaceHandling();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String uri, String localName, String rawName) throws SAXException {
/*      */     this.m_elementID--;
/*      */     if (!this.m_shouldProcess) {
/*      */       return;
/*      */     }
/*      */     if (this.m_elementID + 1 == this.m_fragmentID) {
/*      */       this.m_shouldProcess = false;
/*      */     }
/*      */     flushCharacters();
/*      */     popSpaceHandling();
/*      */     XSLTElementProcessor p = getCurrentProcessor();
/*      */     p.endElement(this, uri, localName, rawName);
/*      */     popProcessor();
/*      */     getNamespaceSupport().popContext();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] ch, int start, int length) throws SAXException {
/*      */     if (!this.m_shouldProcess) {
/*      */       return;
/*      */     }
/*      */     XSLTElementProcessor elemProcessor = getCurrentProcessor();
/*      */     XSLTElementDef def = elemProcessor.getElemDef();
/*      */     if (def.getType() != 2) {
/*      */       elemProcessor = def.getProcessorFor(null, "text()");
/*      */     }
/*      */     if (null == elemProcessor) {
/*      */       if (!XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
/*      */         error(XSLMessages.createMessage("ER_NONWHITESPACE_NOT_ALLOWED_IN_POSITION", null), null);
/*      */       }
/*      */     } else {
/*      */       elemProcessor.characters(this, ch, start, length);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*      */     if (!this.m_shouldProcess) {
/*      */       return;
/*      */     }
/*      */     getCurrentProcessor().ignorableWhitespace(this, ch, start, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StylesheetHandler(TransformerFactoryImpl processor) throws TransformerConfigurationException {
/*  391 */     this.m_stylesheetLevel = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  411 */     this.m_parsingComplete = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  470 */     this.m_prefixMappings = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1019 */     this.m_shouldProcess = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1036 */     this.m_elementID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1042 */     this.m_fragmentID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1113 */     this.m_stylesheetType = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1140 */     this.m_stylesheets = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1222 */     this.m_processors = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1259 */     this.m_schema = new XSLTSchema();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1275 */     this.m_elems = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1297 */     this.m_docOrderCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1337 */     this.m_baseIdentifiers = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1406 */     this.m_stylesheetLocatorStack = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1434 */     this.m_importStack = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1474 */     this.warnedAboutOldXSLTNamespace = false;
/*      */ 
/*      */     
/* 1477 */     this.m_nsSupportStack = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1540 */     this.m_spacePreserveStack = new BoolStack(); init(processor);
/*      */   }
/*      */   public void processingInstruction(String target, String data) throws SAXException { if (!this.m_shouldProcess) return;  String prefix = "", ns = "", localName = target; int colon = target.indexOf(':'); if (colon >= 0) { ns = getNamespaceForPrefix(prefix = target.substring(0, colon)); localName = target.substring(colon + 1); }  try { if ("xalan-doc-cache-off".equals(target) || "xalan:doc-cache-off".equals(target) || ("doc-cache-off".equals(localName) && ns.equals("org.apache.xalan.xslt.extensions.Redirect"))) { if (!(this.m_elems.peek() instanceof ElemForEach)) throw new TransformerException("xalan:doc-cache-off not allowed here!", getLocator());  ElemForEach elem = this.m_elems.peek(); elem.m_doc_cache_off = true; }  } catch (Exception exception) {} flushCharacters(); getCurrentProcessor().processingInstruction(this, target, data); }
/*      */   public void skippedEntity(String name) throws SAXException { if (!this.m_shouldProcess) return;  getCurrentProcessor().skippedEntity(this, name); }
/*      */   public void warn(String msg, Object[] args) throws SAXException { String formattedMsg = XSLMessages.createWarning(msg, args); SAXSourceLocator locator = getLocator(); ErrorListener handler = this.m_stylesheetProcessor.getErrorListener(); try { if (null != handler) handler.warning(new TransformerException(formattedMsg, (SourceLocator)locator));  } catch (TransformerException te) { throw new SAXException(te); }  }
/*      */   private void assertion(boolean condition, String msg) throws RuntimeException { if (!condition) throw new RuntimeException(msg);  }
/*      */   protected void error(String msg, Exception e) throws SAXException { TransformerException transformerException; SAXSourceLocator locator = getLocator(); ErrorListener handler = this.m_stylesheetProcessor.getErrorListener(); if (!(e instanceof TransformerException)) { transformerException = (null == e) ? new TransformerException(msg, (SourceLocator)locator) : new TransformerException(msg, (SourceLocator)locator, e); } else { transformerException = (TransformerException)e; }  if (null != handler) { try { handler.error(transformerException); } catch (TransformerException te) { throw new SAXException(te); }  } else { throw new SAXException(transformerException); }  }
/*      */   protected void error(String msg, Object[] args, Exception e) throws SAXException { String formattedMsg = XSLMessages.createMessage(msg, args); error(formattedMsg, e); }
/*      */   public void warning(SAXParseException e) throws SAXException { String formattedMsg = e.getMessage(); SAXSourceLocator locator = getLocator(); ErrorListener handler = this.m_stylesheetProcessor.getErrorListener(); try { handler.warning(new TransformerException(formattedMsg, (SourceLocator)locator)); } catch (TransformerException te) { throw new SAXException(te); }  }
/*      */   public void error(SAXParseException e) throws SAXException { String formattedMsg = e.getMessage(); SAXSourceLocator locator = getLocator(); ErrorListener handler = this.m_stylesheetProcessor.getErrorListener(); try { handler.error(new TransformerException(formattedMsg, (SourceLocator)locator)); } catch (TransformerException te) { throw new SAXException(te); }  }
/* 1550 */   public void fatalError(SAXParseException e) throws SAXException { String formattedMsg = e.getMessage(); SAXSourceLocator locator = getLocator(); ErrorListener handler = this.m_stylesheetProcessor.getErrorListener(); try { handler.fatalError(new TransformerException(formattedMsg, (SourceLocator)locator)); } catch (TransformerException te) { throw new SAXException(te); }  } private void checkForFragmentID(Attributes attributes) { if (!this.m_shouldProcess) if (null != attributes && null != this.m_fragmentIDString) { int n = attributes.getLength(); for (int i = 0; i < n; i++) { String name = attributes.getQName(i); if (name.equals("id")) { String val = attributes.getValue(i); if (val.equalsIgnoreCase(this.m_fragmentIDString)) { this.m_shouldProcess = true; this.m_fragmentID = this.m_elementID; }  }  }  }   } TransformerFactoryImpl getStylesheetProcessor() { return this.m_stylesheetProcessor; } int getStylesheetType() { return this.m_stylesheetType; } void setStylesheetType(int type) { this.m_stylesheetType = type; } Stylesheet getStylesheet() { return (this.m_stylesheets.size() == 0) ? null : this.m_stylesheets.peek(); } Stylesheet getLastPoppedStylesheet() { return this.m_lastPoppedStylesheet; } boolean isSpacePreserve() { return this.m_spacePreserveStack.peek(); }
/*      */   public StylesheetRoot getStylesheetRoot() { return this.m_stylesheetRoot; }
/*      */   public void pushStylesheet(Stylesheet s) { if (this.m_stylesheets.size() == 0) this.m_stylesheetRoot = (StylesheetRoot)s;  this.m_stylesheets.push(s); }
/*      */   Stylesheet popStylesheet() { if (!this.m_stylesheetLocatorStack.isEmpty()) this.m_stylesheetLocatorStack.pop();  if (!this.m_stylesheets.isEmpty())
/*      */       this.m_lastPoppedStylesheet = this.m_stylesheets.pop();  return this.m_lastPoppedStylesheet; }
/*      */   XSLTElementProcessor getCurrentProcessor() { return this.m_processors.peek(); }
/*      */   void pushProcessor(XSLTElementProcessor processor) { this.m_processors.push(processor); }
/*      */   XSLTElementProcessor popProcessor() { return this.m_processors.pop(); }
/* 1558 */   XSLTSchema getSchema() { return this.m_schema; } void popSpaceHandling() { this.m_spacePreserveStack.pop(); } ElemTemplateElement getElemTemplateElement() { try { return this.m_elems.peek(); } catch (EmptyStackException ese) { return null; }  } int nextUid() { return this.m_docOrderCount++; } void pushElemTemplateElement(ElemTemplateElement elem) { if (elem.getUid() == -1) elem.setUid(nextUid());  this.m_elems.push(elem); } ElemTemplateElement popElemTemplateElement() { return this.m_elems.pop(); } void pushBaseIndentifier(String baseID) { if (null != baseID) { int posOfHash = baseID.indexOf('#'); if (posOfHash > -1) { this.m_fragmentIDString = baseID.substring(posOfHash + 1); this.m_shouldProcess = false; } else { this.m_shouldProcess = true; }  } else { this.m_shouldProcess = true; }  this.m_baseIdentifiers.push(baseID); } String popBaseIndentifier() { return this.m_baseIdentifiers.pop(); }
/*      */   public String getBaseIdentifier() { String base = this.m_baseIdentifiers.isEmpty() ? null : this.m_baseIdentifiers.peek(); if (null == base) { SAXSourceLocator sAXSourceLocator = getLocator(); base = (null == sAXSourceLocator) ? "" : sAXSourceLocator.getSystemId(); }  return base; }
/*      */   public SAXSourceLocator getLocator() { if (this.m_stylesheetLocatorStack.isEmpty()) { SAXSourceLocator locator = new SAXSourceLocator(); locator.setSystemId(getStylesheetProcessor().getDOMsystemID()); return locator; }  return this.m_stylesheetLocatorStack.peek(); }
/*      */   void pushImportURL(String hrefUrl) { this.m_importStack.push(hrefUrl); }
/*      */   boolean importStackContains(String hrefUrl) { return stackContains(this.m_importStack, hrefUrl); }
/*      */   String popImportURL() { return this.m_importStack.pop(); }
/*      */   void pushNewNamespaceSupport() { this.m_nsSupportStack.push(new NamespaceSupport2()); }
/*      */   void popNamespaceSupport() { this.m_nsSupportStack.pop(); }
/*      */   NamespaceSupport getNamespaceSupport() { return this.m_nsSupportStack.peek(); }
/*      */   public void setOriginatingNode(Node n) { this.m_originatingNode = n; }
/*      */   public Node getOriginatingNode() { return this.m_originatingNode; }
/* 1569 */   void pushSpaceHandling(boolean b) throws SAXParseException { this.m_spacePreserveStack.push(b); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void pushSpaceHandling(Attributes attrs) throws SAXParseException {
/* 1581 */     String value = attrs.getValue("xml:space");
/* 1582 */     if (null == value) {
/*      */       
/* 1584 */       this.m_spacePreserveStack.push(this.m_spacePreserveStack.peekOrFalse());
/*      */     }
/* 1586 */     else if (value.equals("preserve")) {
/*      */       
/* 1588 */       this.m_spacePreserveStack.push(true);
/*      */     }
/* 1590 */     else if (value.equals("default")) {
/*      */       
/* 1592 */       this.m_spacePreserveStack.push(false);
/*      */     }
/*      */     else {
/*      */       
/* 1596 */       SAXSourceLocator locator = getLocator();
/* 1597 */       ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
/*      */ 
/*      */ 
/*      */       
/* 1601 */       try { handler.error(new TransformerException(XSLMessages.createMessage("ER_ILLEGAL_XMLSPACE_VALUE", null), (SourceLocator)locator)); } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/* 1605 */         throw new SAXParseException(te.getMessage(), locator, te); }
/*      */       
/* 1607 */       this.m_spacePreserveStack.push(this.m_spacePreserveStack.peek());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private double getElemVersion() {
/* 1613 */     ElemTemplateElement elem = getElemTemplateElement();
/* 1614 */     double version = -1.0D;
/* 1615 */     while ((version == -1.0D || version == 1.0D) && elem != null) {
/*      */ 
/*      */       
/* 1618 */       try { version = Double.valueOf(elem.getVersion()).doubleValue(); } catch (Exception ex)
/*      */       
/*      */       { 
/*      */         
/* 1622 */         version = -1.0D; }
/*      */       
/* 1624 */       elem = elem.getParentElem();
/*      */     } 
/* 1626 */     return (version == -1.0D) ? 1.0D : version;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean handlesNullPrefixes() {
/* 1632 */     return false;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/StylesheetHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */