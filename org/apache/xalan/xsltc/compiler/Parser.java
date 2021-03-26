/*      */ package org.apache.xalan.xsltc.compiler;
/*      */ 
/*      */ import b.a.b;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.StringReader;
/*      */ import java.util.Dictionary;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Properties;
/*      */ import java.util.Stack;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.parsers.SAXParser;
/*      */ import javax.xml.parsers.SAXParserFactory;
/*      */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*      */ import org.apache.xalan.xsltc.compiler.util.MethodType;
/*      */ import org.apache.xalan.xsltc.compiler.util.Type;
/*      */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*      */ import org.apache.xalan.xsltc.runtime.AttributeList;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.XMLReader;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Parser
/*      */   implements Constants, ContentHandler
/*      */ {
/*      */   private static final String XSL = "xsl";
/*      */   private static final String TRANSLET = "translet";
/*   63 */   private Locator _locator = null;
/*      */   
/*      */   private XSLTC _xsltc;
/*      */   
/*      */   private XPathParser _xpathParser;
/*      */   
/*      */   private Vector _errors;
/*      */   private Vector _warnings;
/*      */   private Hashtable _instructionClasses;
/*      */   private Hashtable _instructionAttrs;
/*      */   private Hashtable _qNames;
/*      */   private Hashtable _namespaces;
/*      */   private QName _useAttributeSets;
/*      */   private QName _excludeResultPrefixes;
/*      */   private QName _extensionElementPrefixes;
/*      */   private Hashtable _variableScope;
/*      */   private Stylesheet _currentStylesheet;
/*      */   private SymbolTable _symbolTable;
/*      */   private Output _output;
/*      */   private Template _template;
/*      */   private boolean _rootNamespaceDef;
/*      */   private SyntaxTreeNode _root;
/*      */   private String _target;
/*      */   private int _currentImportPrecedence;
/*      */   private String _PImedia;
/*      */   private String _PItitle;
/*      */   private String _PIcharset;
/*      */   private int _templateIndex;
/*      */   private boolean versionIsOne;
/*      */   private Stack _parentStack;
/*      */   private Hashtable _prefixMapping;
/*      */   
/*      */   public void init() {
/*   96 */     this._qNames = new Hashtable(512);
/*   97 */     this._namespaces = new Hashtable();
/*   98 */     this._instructionClasses = new Hashtable();
/*   99 */     this._instructionAttrs = new Hashtable();
/*  100 */     this._variableScope = new Hashtable();
/*  101 */     this._template = null;
/*  102 */     this._errors = new Vector();
/*  103 */     this._warnings = new Vector();
/*  104 */     this._symbolTable = new SymbolTable();
/*  105 */     this._xpathParser = new XPathParser(this);
/*  106 */     this._currentStylesheet = null;
/*  107 */     this._output = null;
/*  108 */     this._root = null;
/*  109 */     this._rootNamespaceDef = false;
/*  110 */     this._currentImportPrecedence = 1;
/*      */     
/*  112 */     initStdClasses();
/*  113 */     initInstructionAttrs();
/*  114 */     initExtClasses();
/*  115 */     initSymbolTable();
/*      */     
/*  117 */     this._useAttributeSets = getQName("http://www.w3.org/1999/XSL/Transform", "xsl", "use-attribute-sets");
/*      */     
/*  119 */     this._excludeResultPrefixes = getQName("http://www.w3.org/1999/XSL/Transform", "xsl", "exclude-result-prefixes");
/*      */     
/*  121 */     this._extensionElementPrefixes = getQName("http://www.w3.org/1999/XSL/Transform", "xsl", "extension-element-prefixes");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOutput(Output output) {
/*  126 */     if (this._output != null) {
/*  127 */       if (this._output.getImportPrecedence() <= output.getImportPrecedence()) {
/*  128 */         String cdata = this._output.getCdata();
/*  129 */         output.mergeCdata(cdata);
/*  130 */         this._output.disable();
/*  131 */         this._output = output;
/*      */       } else {
/*      */         
/*  134 */         output.disable();
/*      */       } 
/*      */     } else {
/*      */       
/*  138 */       this._output = output;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Output getOutput() {
/*  143 */     return this._output;
/*      */   }
/*      */   
/*      */   public Properties getOutputProperties() {
/*  147 */     return getTopLevelStylesheet().getOutputProperties();
/*      */   }
/*      */   
/*      */   public void addVariable(Variable var) {
/*  151 */     addVariableOrParam(var);
/*      */   }
/*      */   
/*      */   public void addParameter(Param param) {
/*  155 */     addVariableOrParam(param);
/*      */   }
/*      */   
/*      */   private void addVariableOrParam(VariableBase var) {
/*  159 */     Object existing = this._variableScope.get(var.getName());
/*  160 */     if (existing != null) {
/*  161 */       if (existing instanceof Stack) {
/*  162 */         Stack stack = (Stack)existing;
/*  163 */         stack.push(var);
/*      */       }
/*  165 */       else if (existing instanceof VariableBase) {
/*  166 */         Stack stack = new Stack();
/*  167 */         stack.push(existing);
/*  168 */         stack.push(var);
/*  169 */         this._variableScope.put(var.getName(), stack);
/*      */       } 
/*      */     } else {
/*      */       
/*  173 */       this._variableScope.put(var.getName(), var);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeVariable(QName name) {
/*  178 */     Object existing = this._variableScope.get(name);
/*  179 */     if (existing instanceof Stack) {
/*  180 */       Stack stack = (Stack)existing;
/*  181 */       if (!stack.isEmpty()) stack.pop(); 
/*  182 */       if (!stack.isEmpty())
/*      */         return; 
/*  184 */     }  this._variableScope.remove(name);
/*      */   }
/*      */   
/*      */   public VariableBase lookupVariable(QName name) {
/*  188 */     Object existing = this._variableScope.get(name);
/*  189 */     if (existing instanceof VariableBase) {
/*  190 */       return (VariableBase)existing;
/*      */     }
/*  192 */     if (existing instanceof Stack) {
/*  193 */       Stack stack = (Stack)existing;
/*  194 */       return stack.peek();
/*      */     } 
/*  196 */     return null;
/*      */   }
/*      */   
/*      */   public void setXSLTC(XSLTC xsltc) {
/*  200 */     this._xsltc = xsltc;
/*      */   }
/*      */   
/*      */   public XSLTC getXSLTC() {
/*  204 */     return this._xsltc;
/*      */   }
/*      */   
/*      */   public int getCurrentImportPrecedence() {
/*  208 */     return this._currentImportPrecedence;
/*      */   }
/*      */   
/*      */   public int getNextImportPrecedence() {
/*  212 */     return ++this._currentImportPrecedence;
/*      */   }
/*      */   
/*      */   public void setCurrentStylesheet(Stylesheet stylesheet) {
/*  216 */     this._currentStylesheet = stylesheet;
/*      */   }
/*      */   
/*      */   public Stylesheet getCurrentStylesheet() {
/*  220 */     return this._currentStylesheet;
/*      */   }
/*      */   
/*      */   public Stylesheet getTopLevelStylesheet() {
/*  224 */     return this._xsltc.getStylesheet();
/*      */   }
/*      */ 
/*      */   
/*      */   public QName getQNameSafe(String stringRep) {
/*  229 */     int colon = stringRep.lastIndexOf(':');
/*  230 */     if (colon != -1) {
/*  231 */       String prefix = stringRep.substring(0, colon);
/*  232 */       String localname = stringRep.substring(colon + 1);
/*  233 */       String namespace = null;
/*      */ 
/*      */       
/*  236 */       if (!prefix.equals("xmlns")) {
/*  237 */         namespace = this._symbolTable.lookupNamespace(prefix);
/*  238 */         if (namespace == null) namespace = ""; 
/*      */       } 
/*  240 */       return getQName(namespace, prefix, localname);
/*      */     } 
/*      */     
/*  243 */     String uri = stringRep.equals("xmlns") ? null : this._symbolTable.lookupNamespace("");
/*      */     
/*  245 */     return getQName(uri, (String)null, stringRep);
/*      */   }
/*      */ 
/*      */   
/*      */   public QName getQName(String stringRep) {
/*  250 */     return getQName(stringRep, true, false);
/*      */   }
/*      */   
/*      */   public QName getQNameIgnoreDefaultNs(String stringRep) {
/*  254 */     return getQName(stringRep, true, true);
/*      */   }
/*      */   
/*      */   public QName getQName(String stringRep, boolean reportError) {
/*  258 */     return getQName(stringRep, reportError, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private QName getQName(String stringRep, boolean reportError, boolean ignoreDefaultNs) {
/*  265 */     int colon = stringRep.lastIndexOf(':');
/*  266 */     if (colon != -1) {
/*  267 */       String prefix = stringRep.substring(0, colon);
/*  268 */       String localname = stringRep.substring(colon + 1);
/*  269 */       String namespace = null;
/*      */ 
/*      */       
/*  272 */       if (!prefix.equals("xmlns")) {
/*  273 */         namespace = this._symbolTable.lookupNamespace(prefix);
/*  274 */         if (namespace == null && reportError) {
/*  275 */           int line = this._locator.getLineNumber();
/*  276 */           ErrorMsg err = new ErrorMsg("NAMESPACE_UNDEF_ERR", line, prefix);
/*      */           
/*  278 */           reportError(3, err);
/*      */         } 
/*      */       } 
/*  281 */       return getQName(namespace, prefix, localname);
/*      */     } 
/*      */     
/*  284 */     if (stringRep.equals("xmlns")) {
/*  285 */       ignoreDefaultNs = true;
/*      */     }
/*  287 */     String defURI = ignoreDefaultNs ? null : this._symbolTable.lookupNamespace("");
/*      */     
/*  289 */     return getQName(defURI, (String)null, stringRep);
/*      */   }
/*      */ 
/*      */   
/*      */   public QName getQName(String namespace, String prefix, String localname) {
/*  294 */     if (namespace == null || namespace.equals("")) {
/*  295 */       QName qName = (QName)this._qNames.get(localname);
/*  296 */       if (qName == null) {
/*  297 */         qName = new QName(null, prefix, localname);
/*  298 */         this._qNames.put(localname, qName);
/*      */       } 
/*  300 */       return qName;
/*      */     } 
/*      */     
/*  303 */     Dictionary space = (Dictionary)this._namespaces.get(namespace);
/*  304 */     if (space == null) {
/*  305 */       QName qName = new QName(namespace, prefix, localname);
/*  306 */       this._namespaces.put(namespace, space = new Hashtable());
/*  307 */       space.put(localname, qName);
/*  308 */       return qName;
/*      */     } 
/*      */     
/*  311 */     QName name = (QName)space.get(localname);
/*  312 */     if (name == null) {
/*  313 */       name = new QName(namespace, prefix, localname);
/*  314 */       space.put(localname, name);
/*      */     } 
/*  316 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public QName getQName(String scope, String name) {
/*  322 */     return getQName(scope + name);
/*      */   }
/*      */   
/*      */   public QName getQName(QName scope, QName name) {
/*  326 */     return getQName(scope.toString() + name.toString());
/*      */   }
/*      */   
/*      */   public QName getUseAttributeSets() {
/*  330 */     return this._useAttributeSets;
/*      */   }
/*      */   
/*      */   public QName getExtensionElementPrefixes() {
/*  334 */     return this._extensionElementPrefixes;
/*      */   }
/*      */   
/*      */   public QName getExcludeResultPrefixes() {
/*  338 */     return this._excludeResultPrefixes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Stylesheet makeStylesheet(SyntaxTreeNode element) throws CompilerException {
/*      */     
/*      */     try { Stylesheet stylesheet;
/*  351 */       if (element instanceof Stylesheet) {
/*  352 */         stylesheet = (Stylesheet)element;
/*      */       } else {
/*      */         
/*  355 */         stylesheet = new Stylesheet();
/*  356 */         stylesheet.setSimplified();
/*  357 */         stylesheet.addElement(element);
/*  358 */         stylesheet.setAttributes(element.getAttributes());
/*      */ 
/*      */         
/*  361 */         if (element.lookupNamespace("") == null) {
/*  362 */           element.addPrefixMapping("", "");
/*      */         }
/*      */       } 
/*  365 */       stylesheet.setParser(this);
/*  366 */       return stylesheet; } catch (ClassCastException e)
/*      */     
/*      */     { 
/*  369 */       ErrorMsg err = new ErrorMsg("NOT_STYLESHEET_ERR", element);
/*  370 */       throw new CompilerException(err.toString()); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createAST(Stylesheet stylesheet) {
/*      */     
/*  379 */     try { if (stylesheet != null)
/*  380 */       { stylesheet.parseContents(this);
/*  381 */         int precedence = stylesheet.getImportPrecedence();
/*  382 */         Enumeration elements = stylesheet.elements();
/*  383 */         while (elements.hasMoreElements()) {
/*  384 */           Object child = elements.nextElement();
/*  385 */           if (child instanceof Text) {
/*  386 */             int l = this._locator.getLineNumber();
/*  387 */             ErrorMsg err = new ErrorMsg("ILLEGAL_TEXT_NODE_ERR", l, null);
/*      */             
/*  389 */             reportError(3, err);
/*      */           } 
/*      */         } 
/*  392 */         if (!errorsFound())
/*  393 */           stylesheet.typeCheck(this._symbolTable);  }  } catch (TypeCheckError e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  398 */       reportError(3, new ErrorMsg((Throwable)e)); }
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
/*      */   public SyntaxTreeNode parse(XMLReader reader, InputSource input) {
/*      */     
/*  411 */     try { reader.setContentHandler(this);
/*  412 */       reader.parse(input);
/*      */       
/*  414 */       return getStylesheet(this._root); } catch (IOException e)
/*      */     
/*      */     { 
/*  417 */       if (this._xsltc.debug()) e.printStackTrace(); 
/*  418 */       reportError(3, new ErrorMsg(e)); } catch (SAXException e)
/*      */     
/*      */     { 
/*  421 */       Throwable ex = e.getException();
/*  422 */       if (this._xsltc.debug()) {
/*  423 */         e.printStackTrace();
/*  424 */         if (ex != null) ex.printStackTrace(); 
/*      */       } 
/*  426 */       reportError(3, new ErrorMsg(e)); } catch (CompilerException e)
/*      */     
/*      */     { 
/*  429 */       if (this._xsltc.debug()) e.printStackTrace(); 
/*  430 */       reportError(3, new ErrorMsg(e)); } catch (Exception e)
/*      */     
/*      */     { 
/*  433 */       if (this._xsltc.debug()) e.printStackTrace(); 
/*  434 */       reportError(3, new ErrorMsg(e)); }
/*      */     
/*  436 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SyntaxTreeNode parse(InputSource input) {
/*      */     
/*  447 */     try { SAXParserFactory factory = SAXParserFactory.newInstance();
/*      */       
/*  449 */       try { factory.setFeature("http://xml.org/sax/features/namespaces", true); } catch (Exception e)
/*      */       
/*      */       { 
/*  452 */         factory.setNamespaceAware(true); }
/*      */       
/*  454 */       SAXParser parser = factory.newSAXParser();
/*  455 */       XMLReader reader = parser.getXMLReader();
/*  456 */       return parse(reader, input); } catch (ParserConfigurationException e)
/*      */     
/*      */     { 
/*  459 */       ErrorMsg err = new ErrorMsg("SAX_PARSER_CONFIG_ERR");
/*  460 */       reportError(3, err); } catch (SAXParseException e)
/*      */     
/*      */     { 
/*  463 */       reportError(3, new ErrorMsg(e.getMessage(), e.getLineNumber())); } catch (SAXException e)
/*      */     
/*      */     { 
/*  466 */       reportError(3, new ErrorMsg(e.getMessage())); }
/*      */     
/*  468 */     return null;
/*      */   }
/*      */   
/*      */   public SyntaxTreeNode getDocumentRoot() {
/*  472 */     return this._root;
/*      */   }
/*      */   protected void setPIParameters(String media, String title, String charset) { this._PImedia = media; this._PItitle = title; this._PIcharset = charset; }
/*  475 */   private SyntaxTreeNode getStylesheet(SyntaxTreeNode root) throws CompilerException { if (this._target == null) { if (!this._rootNamespaceDef) { ErrorMsg msg = new ErrorMsg("MISSING_XSLT_URI_ERR"); throw new CompilerException(msg.toString()); }  return root; }  if (this._target.charAt(0) == '#') { SyntaxTreeNode element = findStylesheet(root, this._target.substring(1)); if (element == null) { ErrorMsg msg = new ErrorMsg("MISSING_XSLT_TARGET_ERR", this._target, root); throw new CompilerException(msg.toString()); }  return element; }  return loadExternalStylesheet(this._target); } private SyntaxTreeNode findStylesheet(SyntaxTreeNode root, String href) { if (root == null) return null;  if (root instanceof Stylesheet) { String id = root.getAttribute("id"); if (id.equals(href)) return root;  }  Vector children = root.getContents(); if (children != null) { int count = children.size(); for (int i = 0; i < count; i++) { SyntaxTreeNode child = children.elementAt(i); SyntaxTreeNode node = findStylesheet(child, href); if (node != null) return node;  }  }  return null; } private SyntaxTreeNode loadExternalStylesheet(String location) throws CompilerException { InputSource inputSource; if ((new File(location)).exists()) { inputSource = new InputSource("file:" + location); } else { inputSource = new InputSource(location); }  SyntaxTreeNode external = parse(inputSource); return external; } private void initAttrTable(String elementName, String[] attrs) { this._instructionAttrs.put(getQName("http://www.w3.org/1999/XSL/Transform", "xsl", elementName), attrs); } private void initInstructionAttrs() { initAttrTable("template", new String[] { "match", "name", "priority", "mode" }); initAttrTable("stylesheet", new String[] { "id", "version", "extension-element-prefixes", "exclude-result-prefixes" }); initAttrTable("transform", new String[] { "id", "version", "extension-element-prefixes", "exclude-result-prefixes" }); initAttrTable("text", new String[] { "disable-output-escaping" }); initAttrTable("if", new String[] { "test" }); initAttrTable("choose", new String[0]); initAttrTable("when", new String[] { "test" }); initAttrTable("otherwise", new String[0]); initAttrTable("for-each", new String[] { "select" }); initAttrTable("message", new String[] { "terminate" }); initAttrTable("number", new String[] { "level", "count", "from", "value", "format", "lang", "letter-value", "grouping-separator", "grouping-size" }); initAttrTable("comment", new String[0]); initAttrTable("copy", new String[] { "use-attribute-sets" }); initAttrTable("copy-of", new String[] { "select" }); initAttrTable("param", new String[] { "name", "select" }); initAttrTable("with-param", new String[] { "name", "select" }); initAttrTable("variable", new String[] { "name", "select" }); initAttrTable("output", new String[] { "method", "version", "encoding", "omit-xml-declaration", "standalone", "doctype-public", "doctype-system", "cdata-section-elements", "indent", "media-type" }); initAttrTable("sort", new String[] { "select", "order", "case-order", "lang", "data-type" }); initAttrTable("key", new String[] { "name", "match", "use" }); initAttrTable("fallback", new String[0]); initAttrTable("attribute", new String[] { "name", "namespace" }); initAttrTable("attribute-set", new String[] { "name", "use-attribute-sets" }); initAttrTable("value-of", new String[] { "select", "disable-output-escaping" }); initAttrTable("element", new String[] { "name", "namespace", "use-attribute-sets" }); initAttrTable("call-template", new String[] { "name" }); initAttrTable("apply-templates", new String[] { "select", "mode" }); initAttrTable("apply-imports", new String[0]); initAttrTable("decimal-format", new String[] { "name", "decimal-separator", "grouping-separator", "infinity", "minus-sign", "NaN", "percent", "per-mille", "zero-digit", "digit", "pattern-separator" }); initAttrTable("import", new String[] { "href" }); initAttrTable("include", new String[] { "href" }); initAttrTable("strip-space", new String[] { "elements" }); initAttrTable("preserve-space", new String[] { "elements" }); initAttrTable("processing-instruction", new String[] { "name" }); initAttrTable("namespace-alias", new String[] { "stylesheet-prefix", "result-prefix" }); } private void initStdClasses() { initStdClass("template", "Template"); initStdClass("stylesheet", "Stylesheet"); initStdClass("transform", "Stylesheet"); initStdClass("text", "Text"); initStdClass("if", "If"); initStdClass("choose", "Choose"); initStdClass("when", "When"); initStdClass("otherwise", "Otherwise"); initStdClass("for-each", "ForEach"); initStdClass("message", "Message"); initStdClass("number", "Number"); initStdClass("comment", "Comment"); initStdClass("copy", "Copy"); initStdClass("copy-of", "CopyOf"); initStdClass("param", "Param"); initStdClass("with-param", "WithParam"); initStdClass("variable", "Variable"); initStdClass("output", "Output"); initStdClass("sort", "Sort"); initStdClass("key", "Key"); initStdClass("fallback", "Fallback"); initStdClass("attribute", "XslAttribute"); initStdClass("attribute-set", "AttributeSet"); initStdClass("value-of", "ValueOf"); initStdClass("element", "XslElement"); initStdClass("call-template", "CallTemplate"); initStdClass("apply-templates", "ApplyTemplates"); initStdClass("apply-imports", "ApplyImports"); initStdClass("decimal-format", "DecimalFormatting"); initStdClass("import", "Import"); initStdClass("include", "Include"); initStdClass("strip-space", "Whitespace"); initStdClass("preserve-space", "Whitespace"); initStdClass("processing-instruction", "ProcessingInstruction"); initStdClass("namespace-alias", "NamespaceAlias"); } private void initStdClass(String elementName, String className) { this._instructionClasses.put(getQName("http://www.w3.org/1999/XSL/Transform", "xsl", elementName), "org.apache.xalan.xsltc.compiler." + className); } public Parser(XSLTC xsltc) { this._PImedia = null;
/*  476 */     this._PItitle = null;
/*  477 */     this._PIcharset = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  879 */     this._templateIndex = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  897 */     this.versionIsOne = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1181 */     this._parentStack = null;
/* 1182 */     this._prefixMapping = null; this._xsltc = xsltc; }
/*      */   public boolean elementSupported(String namespace, String localName) { return (this._instructionClasses.get(getQName(namespace, "xsl", localName)) != null); }
/*      */   public boolean functionSupported(String fname) { return (this._symbolTable.lookupPrimop(fname) != null); }
/*      */   private void initExtClasses() { initExtClass("output", "TransletOutput"); initExtClass("http://xml.apache.org/xalan/redirect", "write", "TransletOutput"); }
/*      */   private void initExtClass(String elementName, String className) { this._instructionClasses.put(getQName("http://xml.apache.org/xalan/xsltc", "translet", elementName), "org.apache.xalan.xsltc.compiler." + className); }
/*      */   private void initExtClass(String namespace, String elementName, String className) { this._instructionClasses.put(getQName(namespace, "translet", elementName), "org.apache.xalan.xsltc.compiler." + className); }
/* 1188 */   private void initSymbolTable() { MethodType I_V = new MethodType(Type.Int, Type.Void); MethodType I_R = new MethodType(Type.Int, Type.Real); MethodType I_S = new MethodType(Type.Int, Type.String); MethodType I_D = new MethodType(Type.Int, Type.NodeSet); MethodType R_I = new MethodType(Type.Real, Type.Int); MethodType R_V = new MethodType(Type.Real, Type.Void); MethodType R_R = new MethodType(Type.Real, Type.Real); MethodType R_D = new MethodType(Type.Real, Type.NodeSet); MethodType R_O = new MethodType(Type.Real, Type.Reference); MethodType I_I = new MethodType(Type.Int, Type.Int); MethodType D_O = new MethodType(Type.NodeSet, Type.Reference); MethodType D_V = new MethodType(Type.NodeSet, Type.Void); MethodType D_S = new MethodType(Type.NodeSet, Type.String); MethodType D_D = new MethodType(Type.NodeSet, Type.NodeSet); MethodType A_V = new MethodType(Type.Node, Type.Void); MethodType S_V = new MethodType(Type.String, Type.Void); MethodType S_S = new MethodType(Type.String, Type.String); MethodType S_A = new MethodType(Type.String, Type.Node); MethodType S_D = new MethodType(Type.String, Type.NodeSet); MethodType S_O = new MethodType(Type.String, Type.Reference); MethodType B_O = new MethodType(Type.Boolean, Type.Reference); MethodType B_V = new MethodType(Type.Boolean, Type.Void); MethodType B_B = new MethodType(Type.Boolean, Type.Boolean); MethodType B_S = new MethodType(Type.Boolean, Type.String); MethodType D_X = new MethodType(Type.NodeSet, Type.Object); MethodType R_RR = new MethodType(Type.Real, Type.Real, Type.Real); MethodType I_II = new MethodType(Type.Int, Type.Int, Type.Int); MethodType B_RR = new MethodType(Type.Boolean, Type.Real, Type.Real); MethodType B_II = new MethodType(Type.Boolean, Type.Int, Type.Int); MethodType S_SS = new MethodType(Type.String, Type.String, Type.String); MethodType S_DS = new MethodType(Type.String, Type.Real, Type.String); MethodType S_SR = new MethodType(Type.String, Type.String, Type.Real); MethodType O_SO = new MethodType(Type.Reference, Type.String, Type.Reference); MethodType D_SS = new MethodType(Type.NodeSet, Type.String, Type.String); MethodType D_SD = new MethodType(Type.NodeSet, Type.String, Type.NodeSet); MethodType B_BB = new MethodType(Type.Boolean, Type.Boolean, Type.Boolean); MethodType B_SS = new MethodType(Type.Boolean, Type.String, Type.String); MethodType S_SD = new MethodType(Type.String, Type.String, Type.NodeSet); MethodType S_DSS = new MethodType(Type.String, Type.Real, Type.String, Type.String); MethodType S_SRR = new MethodType(Type.String, Type.String, Type.Real, Type.Real); MethodType S_SSS = new MethodType(Type.String, Type.String, Type.String, Type.String); this._symbolTable.addPrimop("current", A_V); this._symbolTable.addPrimop("last", I_V); this._symbolTable.addPrimop("position", I_V); this._symbolTable.addPrimop("true", B_V); this._symbolTable.addPrimop("false", B_V); this._symbolTable.addPrimop("not", B_B); this._symbolTable.addPrimop("name", S_V); this._symbolTable.addPrimop("name", S_A); this._symbolTable.addPrimop("generate-id", S_V); this._symbolTable.addPrimop("generate-id", S_A); this._symbolTable.addPrimop("ceiling", R_R); this._symbolTable.addPrimop("floor", R_R); this._symbolTable.addPrimop("round", R_R); this._symbolTable.addPrimop("contains", B_SS); this._symbolTable.addPrimop("number", R_O); this._symbolTable.addPrimop("number", R_V); this._symbolTable.addPrimop("boolean", B_O); this._symbolTable.addPrimop("string", S_O); this._symbolTable.addPrimop("string", S_V); this._symbolTable.addPrimop("translate", S_SSS); this._symbolTable.addPrimop("string-length", I_V); this._symbolTable.addPrimop("string-length", I_S); this._symbolTable.addPrimop("starts-with", B_SS); this._symbolTable.addPrimop("format-number", S_DS); this._symbolTable.addPrimop("format-number", S_DSS); this._symbolTable.addPrimop("unparsed-entity-uri", S_S); this._symbolTable.addPrimop("key", D_SS); this._symbolTable.addPrimop("key", D_SD); this._symbolTable.addPrimop("id", D_S); this._symbolTable.addPrimop("id", D_D); this._symbolTable.addPrimop("namespace-uri", S_V); this._symbolTable.addPrimop("function-available", B_S); this._symbolTable.addPrimop("element-available", B_S); this._symbolTable.addPrimop("document", D_S); this._symbolTable.addPrimop("document", D_V); this._symbolTable.addPrimop("count", I_D); this._symbolTable.addPrimop("sum", R_D); this._symbolTable.addPrimop("local-name", S_V); this._symbolTable.addPrimop("local-name", S_D); this._symbolTable.addPrimop("namespace-uri", S_V); this._symbolTable.addPrimop("namespace-uri", S_D); this._symbolTable.addPrimop("substring", S_SR); this._symbolTable.addPrimop("substring", S_SRR); this._symbolTable.addPrimop("substring-after", S_SS); this._symbolTable.addPrimop("substring-before", S_SS); this._symbolTable.addPrimop("normalize-space", S_V); this._symbolTable.addPrimop("normalize-space", S_S); this._symbolTable.addPrimop("system-property", S_S); this._symbolTable.addPrimop("nodeset", D_O); this._symbolTable.addPrimop("objectType", S_O); this._symbolTable.addPrimop("cast", O_SO); this._symbolTable.addPrimop("+", R_RR); this._symbolTable.addPrimop("-", R_RR); this._symbolTable.addPrimop("*", R_RR); this._symbolTable.addPrimop("/", R_RR); this._symbolTable.addPrimop("%", R_RR); this._symbolTable.addPrimop("+", I_II); this._symbolTable.addPrimop("-", I_II); this._symbolTable.addPrimop("*", I_II); this._symbolTable.addPrimop("<", B_RR); this._symbolTable.addPrimop("<=", B_RR); this._symbolTable.addPrimop(">", B_RR); this._symbolTable.addPrimop(">=", B_RR); this._symbolTable.addPrimop("<", B_II); this._symbolTable.addPrimop("<=", B_II); this._symbolTable.addPrimop(">", B_II); this._symbolTable.addPrimop(">=", B_II); this._symbolTable.addPrimop("<", B_BB); this._symbolTable.addPrimop("<=", B_BB); this._symbolTable.addPrimop(">", B_BB); this._symbolTable.addPrimop(">=", B_BB); this._symbolTable.addPrimop("or", B_BB); this._symbolTable.addPrimop("and", B_BB); this._symbolTable.addPrimop("u-", R_R); this._symbolTable.addPrimop("u-", I_I); } public SymbolTable getSymbolTable() { return this._symbolTable; } public void startDocument() { this._root = null;
/* 1189 */     this._target = null;
/* 1190 */     this._prefixMapping = null;
/* 1191 */     this._parentStack = new Stack(); } public Template getTemplate() { return this._template; } public void setTemplate(Template template) { this._template = template; } public int getTemplateIndex() { return this._templateIndex++; } public SyntaxTreeNode makeInstance(String uri, String prefix, String local, Attributes attributes) { SyntaxTreeNode node = null; QName qname = getQName(uri, prefix, local); String className = (String)this._instructionClasses.get(qname); if (className != null) { try { Class clazz = ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true); node = clazz.newInstance(); node.setQName(qname); node.setParser(this); if (this._locator != null) node.setLineNumber(this._locator.getLineNumber());  if (node instanceof Stylesheet) this._xsltc.setStylesheet((Stylesheet)node);  checkForSuperfluousAttributes(node, attributes); } catch (ClassNotFoundException e) { ErrorMsg err = new ErrorMsg("CLASS_NOT_FOUND_ERR", node); reportError(3, err); } catch (Exception e) { ErrorMsg err = new ErrorMsg("INTERNAL_ERR", e.getMessage(), node); reportError(2, err); }  } else { if (uri != null) if (uri.equals("http://www.w3.org/1999/XSL/Transform")) { node = new UnsupportedElement(uri, prefix, local, false); UnsupportedElement element = (UnsupportedElement)node; ErrorMsg msg = new ErrorMsg("UNSUPPORTED_XSL_ERR", this._locator.getLineNumber(), local); element.setErrorMessage(msg); if (this.versionIsOne) reportError(1, msg);  } else if (uri.equals("http://xml.apache.org/xalan/xsltc")) { node = new UnsupportedElement(uri, prefix, local, true); UnsupportedElement element = (UnsupportedElement)node; ErrorMsg msg = new ErrorMsg("UNSUPPORTED_EXT_ERR", this._locator.getLineNumber(), local); element.setErrorMessage(msg); } else { Stylesheet sheet = this._xsltc.getStylesheet(); if (sheet != null && sheet.isExtension(uri) && sheet != (SyntaxTreeNode)this._parentStack.peek()) { node = new UnsupportedElement(uri, prefix, local, true); UnsupportedElement elem = (UnsupportedElement)node; ErrorMsg msg = new ErrorMsg("UNSUPPORTED_EXT_ERR", this._locator.getLineNumber(), prefix + ":" + local); elem.setErrorMessage(msg); }  }   if (node == null) node = new LiteralElement();  }  if (node != null && node instanceof LiteralElement) ((LiteralElement)node).setQName(qname);  return node; }
/*      */   private void checkForSuperfluousAttributes(SyntaxTreeNode node, Attributes attrs) { QName qname = node.getQName(); boolean isStylesheet = node instanceof Stylesheet; String[] legal = (String[])this._instructionAttrs.get(qname); if (this.versionIsOne && legal != null) { int n = attrs.getLength(); for (int i = 0; i < n; i++) { String attrQName = attrs.getQName(i); if (isStylesheet && attrQName.equals("version")) this.versionIsOne = attrs.getValue(i).equals("1.0");  if (!attrQName.startsWith("xml") && attrQName.indexOf(':') <= 0) { int j; for (j = 0; j < legal.length && !attrQName.equalsIgnoreCase(legal[j]); j++); if (j == legal.length) { ErrorMsg err = new ErrorMsg("ILLEGAL_ATTRIBUTE_ERR", attrQName, node); reportError(4, err); }  }  }  }  }
/*      */   public Expression parseExpression(SyntaxTreeNode parent, String exp) { return (Expression)parseTopLevel(parent, "<EXPRESSION>" + exp, null); }
/*      */   public Expression parseExpression(SyntaxTreeNode parent, String attr, String def) { String exp = parent.getAttribute(attr); if (exp.length() == 0 && def != null) exp = def;  return (Expression)parseTopLevel(parent, "<EXPRESSION>" + exp, exp); }
/*      */   public Pattern parsePattern(SyntaxTreeNode parent, String pattern) { return (Pattern)parseTopLevel(parent, "<PATTERN>" + pattern, pattern); }
/*      */   public Pattern parsePattern(SyntaxTreeNode parent, String attr, String def) { String pattern = parent.getAttribute(attr); if (pattern.length() == 0 && def != null) pattern = def;  return (Pattern)parseTopLevel(parent, "<PATTERN>" + pattern, pattern); }
/*      */   private SyntaxTreeNode parseTopLevel(SyntaxTreeNode parent, String text, String expression) { int line = 0; if (this._locator != null) line = this._locator.getLineNumber();  try { this._xpathParser.setScanner(new XPathLexer(new StringReader(text))); b result = this._xpathParser.parse(expression, line); if (result != null) { SyntaxTreeNode node = (SyntaxTreeNode)result.f; if (node != null) { node.setParser(this); node.setParent(parent); node.setLineNumber(line); return node; }  }  reportError(3, new ErrorMsg("XPATH_PARSER_ERR", expression, parent)); } catch (Exception e) { if (this._xsltc.debug()) e.printStackTrace();  reportError(3, new ErrorMsg("XPATH_PARSER_ERR", expression, parent)); }  SyntaxTreeNode.Dummy.setParser(this); return SyntaxTreeNode.Dummy; }
/*      */   public boolean errorsFound() { return (this._errors.size() > 0); }
/*      */   public void printErrors() { int size = this._errors.size(); if (size > 0) { System.err.println(new ErrorMsg("COMPILER_ERROR_KEY")); for (int i = 0; i < size; i++) System.err.println("  " + this._errors.elementAt(i));  }  }
/*      */   public void printWarnings() { int size = this._warnings.size(); if (size > 0) { System.err.println(new ErrorMsg("COMPILER_WARNING_KEY")); for (int i = 0; i < size; i++) System.err.println("  " + this._warnings.elementAt(i));  }  }
/*      */   public void reportError(int category, ErrorMsg error) { switch (category) { case 0: this._errors.addElement(error); break;case 1: this._errors.addElement(error); break;case 2: this._errors.addElement(error); break;case 3: this._errors.addElement(error); break;case 4: this._warnings.addElement(error); break; }  }
/*      */   public Vector getErrors() { return this._errors; }
/*      */   public Vector getWarnings() { return this._warnings; }
/*      */   public void endDocument() {}
/* 1205 */   public void startPrefixMapping(String prefix, String uri) { if (this._prefixMapping == null) {
/* 1206 */       this._prefixMapping = new Hashtable();
/*      */     }
/* 1208 */     this._prefixMapping.put(prefix, uri); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endPrefixMapping(String prefix) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String localname, String qname, Attributes attributes) throws SAXException {
/* 1225 */     int col = qname.lastIndexOf(':');
/* 1226 */     String prefix = (col == -1) ? null : qname.substring(0, col);
/*      */     
/* 1228 */     SyntaxTreeNode element = makeInstance(uri, prefix, localname, attributes);
/*      */     
/* 1230 */     if (element == null) {
/* 1231 */       ErrorMsg err = new ErrorMsg("ELEMENT_PARSE_ERR", prefix + ':' + localname);
/*      */       
/* 1233 */       throw new SAXException(err.toString());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1238 */     if (this._root == null) {
/* 1239 */       if (this._prefixMapping == null || !this._prefixMapping.containsValue("http://www.w3.org/1999/XSL/Transform")) {
/*      */         
/* 1241 */         this._rootNamespaceDef = false;
/*      */       } else {
/* 1243 */         this._rootNamespaceDef = true;
/* 1244 */       }  this._root = element;
/*      */     } else {
/*      */       
/* 1247 */       SyntaxTreeNode parent = this._parentStack.peek();
/* 1248 */       parent.addElement(element);
/* 1249 */       element.setParent(parent);
/*      */     } 
/* 1251 */     element.setAttributes((Attributes)new AttributeList(attributes));
/* 1252 */     element.setPrefixMapping(this._prefixMapping);
/*      */     
/* 1254 */     if (element instanceof Stylesheet) {
/*      */ 
/*      */ 
/*      */       
/* 1258 */       getSymbolTable().setCurrentNode(element);
/* 1259 */       ((Stylesheet)element).excludeExtensionPrefixes(this);
/*      */     } 
/*      */     
/* 1262 */     this._prefixMapping = null;
/* 1263 */     this._parentStack.push(element);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String uri, String localname, String qname) {
/* 1270 */     this._parentStack.pop();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] ch, int start, int length) {
/* 1277 */     String string = new String(ch, start, length);
/* 1278 */     SyntaxTreeNode parent = this._parentStack.peek();
/*      */     
/* 1280 */     if (string.length() == 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1284 */     if (parent instanceof Text) {
/* 1285 */       ((Text)parent).setText(string);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1290 */     if (parent instanceof Stylesheet)
/*      */       return; 
/* 1292 */     SyntaxTreeNode bro = parent.lastChild();
/* 1293 */     if (bro != null && bro instanceof Text) {
/* 1294 */       Text text = (Text)bro;
/* 1295 */       if (!text.isTextElement() && (
/* 1296 */         length > 1 || ch[0] < 'Ā')) {
/* 1297 */         text.setText(string);
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/* 1304 */     parent.addElement(new Text(string));
/*      */   }
/*      */   
/*      */   private String getTokenValue(String token) {
/* 1308 */     int start = token.indexOf('"');
/* 1309 */     int stop = token.lastIndexOf('"');
/* 1310 */     return token.substring(start + 1, stop);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processingInstruction(String name, String value) {
/* 1319 */     if (this._target == null && name.equals("xml-stylesheet")) {
/*      */       
/* 1321 */       String href = null;
/* 1322 */       String media = null;
/* 1323 */       String title = null;
/* 1324 */       String charset = null;
/*      */ 
/*      */       
/* 1327 */       StringTokenizer tokens = new StringTokenizer(value);
/* 1328 */       while (tokens.hasMoreElements()) {
/* 1329 */         String token = (String)tokens.nextElement();
/* 1330 */         if (token.startsWith("href")) {
/* 1331 */           href = getTokenValue(token); continue;
/* 1332 */         }  if (token.startsWith("media")) {
/* 1333 */           media = getTokenValue(token); continue;
/* 1334 */         }  if (token.startsWith("title")) {
/* 1335 */           title = getTokenValue(token); continue;
/* 1336 */         }  if (token.startsWith("charset")) {
/* 1337 */           charset = getTokenValue(token);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1342 */       if ((this._PImedia == null || this._PImedia.equals(media)) && (this._PItitle == null || this._PImedia.equals(title)) && (this._PIcharset == null || this._PImedia.equals(charset)))
/*      */       {
/*      */         
/* 1345 */         this._target = href;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void skippedEntity(String name) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {
/* 1365 */     this._locator = locator;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Parser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */