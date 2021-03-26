/*      */ package org.apache.xalan.xsltc.compiler;
/*      */ 
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import org.apache.bcel.generic.ANEWARRAY;
/*      */ import org.apache.bcel.generic.CompoundInstruction;
/*      */ import org.apache.bcel.generic.ConstantPoolGen;
/*      */ import org.apache.bcel.generic.FieldGen;
/*      */ import org.apache.bcel.generic.GETFIELD;
/*      */ import org.apache.bcel.generic.GETSTATIC;
/*      */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*      */ import org.apache.bcel.generic.INVOKESPECIAL;
/*      */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*      */ import org.apache.bcel.generic.ISTORE;
/*      */ import org.apache.bcel.generic.Instruction;
/*      */ import org.apache.bcel.generic.InstructionConstants;
/*      */ import org.apache.bcel.generic.InstructionHandle;
/*      */ import org.apache.bcel.generic.InstructionList;
/*      */ import org.apache.bcel.generic.LocalVariableGen;
/*      */ import org.apache.bcel.generic.NEW;
/*      */ import org.apache.bcel.generic.NEWARRAY;
/*      */ import org.apache.bcel.generic.PUSH;
/*      */ import org.apache.bcel.generic.PUTFIELD;
/*      */ import org.apache.bcel.generic.PUTSTATIC;
/*      */ import org.apache.bcel.generic.TargetLostException;
/*      */ import org.apache.bcel.generic.Type;
/*      */ import org.apache.bcel.util.InstructionFinder;
/*      */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*      */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*      */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*      */ import org.apache.xalan.xsltc.compiler.util.Type;
/*      */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*      */ import org.apache.xalan.xsltc.compiler.util.Util;
/*      */ import org.apache.xml.utils.SystemIDResolver;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Stylesheet
/*      */   extends SyntaxTreeNode
/*      */ {
/*      */   private String _version;
/*      */   private QName _name;
/*      */   private String _systemId;
/*      */   private Stylesheet _parentStylesheet;
/*   92 */   private Vector _globals = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   97 */   private Boolean _hasLocalParams = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String _className;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   private final Vector _templates = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  113 */   private Vector _allValidTemplates = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  118 */   private int _nextModeSerial = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  123 */   private final Hashtable _modes = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Mode _defaultMode;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   private final Hashtable _extensions = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   public Stylesheet _importedFrom = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  145 */   public Stylesheet _includedFrom = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  150 */   private Vector _includedStylesheets = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  155 */   private int _importPrecedence = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  160 */   private Hashtable _keys = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  166 */   private SourceLoader _loader = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _numberFormattingUsed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _simplified = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _multiDocument = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _callsNodeset = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _hasIdCall = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean _templateInlining = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  202 */   private Output _lastOutputElement = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  207 */   private Properties _outputProperties = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  213 */   private int _outputMethod = 0;
/*      */   
/*      */   public static final int UNKNOWN_OUTPUT = 0;
/*      */   
/*      */   public static final int XML_OUTPUT = 1;
/*      */   
/*      */   public static final int HTML_OUTPUT = 2;
/*      */   
/*      */   public static final int TEXT_OUTPUT = 3;
/*      */ 
/*      */   
/*      */   public int getOutputMethod() {
/*  225 */     return this._outputMethod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkOutputMethod() {
/*  232 */     if (this._lastOutputElement != null) {
/*  233 */       String method = this._lastOutputElement.getOutputMethod();
/*  234 */       if (method != null)
/*  235 */         if (method.equals("xml")) {
/*  236 */           this._outputMethod = 1;
/*  237 */         } else if (method.equals("html")) {
/*  238 */           this._outputMethod = 2;
/*  239 */         } else if (method.equals("text")) {
/*  240 */           this._outputMethod = 3;
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getTemplateInlining() {
/*  246 */     return this._templateInlining;
/*      */   }
/*      */   
/*      */   public void setTemplateInlining(boolean flag) {
/*  250 */     this._templateInlining = flag;
/*      */   }
/*      */   
/*      */   public boolean isSimplified() {
/*  254 */     return this._simplified;
/*      */   }
/*      */   
/*      */   public void setSimplified() {
/*  258 */     this._simplified = true;
/*      */   }
/*      */   
/*      */   public void setHasIdCall(boolean flag) {
/*  262 */     this._hasIdCall = flag;
/*      */   }
/*      */   
/*      */   public void setOutputProperty(String key, String value) {
/*  266 */     if (this._outputProperties == null) {
/*  267 */       this._outputProperties = new Properties();
/*      */     }
/*  269 */     this._outputProperties.setProperty(key, value);
/*      */   }
/*      */   
/*      */   public void setOutputProperties(Properties props) {
/*  273 */     this._outputProperties = props;
/*      */   }
/*      */   
/*      */   public Properties getOutputProperties() {
/*  277 */     return this._outputProperties;
/*      */   }
/*      */   
/*      */   public Output getLastOutputElement() {
/*  281 */     return this._lastOutputElement;
/*      */   }
/*      */   
/*      */   public void setMultiDocument(boolean flag) {
/*  285 */     this._multiDocument = flag;
/*      */   }
/*      */   
/*      */   public boolean isMultiDocument() {
/*  289 */     return this._multiDocument;
/*      */   }
/*      */   
/*      */   public void setCallsNodeset(boolean flag) {
/*  293 */     if (flag) setMultiDocument(flag); 
/*  294 */     this._callsNodeset = flag;
/*      */   }
/*      */   
/*      */   public boolean callsNodeset() {
/*  298 */     return this._callsNodeset;
/*      */   }
/*      */   
/*      */   public void numberFormattingUsed() {
/*  302 */     this._numberFormattingUsed = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  309 */     Stylesheet parent = getParentStylesheet();
/*  310 */     if (null != parent) parent.numberFormattingUsed();
/*      */   
/*      */   }
/*      */   
/*      */   public void setImportPrecedence(int precedence) {
/*  315 */     this._importPrecedence = precedence;
/*      */ 
/*      */     
/*  318 */     Enumeration elements = elements();
/*  319 */     while (elements.hasMoreElements()) {
/*  320 */       SyntaxTreeNode child = elements.nextElement();
/*  321 */       if (child instanceof Include) {
/*  322 */         Stylesheet included = ((Include)child).getIncludedStylesheet();
/*  323 */         if (included != null && included._includedFrom == this) {
/*  324 */           included.setImportPrecedence(precedence);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  330 */     if (this._importedFrom != null) {
/*  331 */       if (this._importedFrom.getImportPrecedence() < precedence) {
/*  332 */         Parser parser = getParser();
/*  333 */         int nextPrecedence = parser.getNextImportPrecedence();
/*  334 */         this._importedFrom.setImportPrecedence(nextPrecedence);
/*      */       }
/*      */     
/*      */     }
/*  338 */     else if (this._includedFrom != null && 
/*  339 */       this._includedFrom.getImportPrecedence() != precedence) {
/*  340 */       this._includedFrom.setImportPrecedence(precedence);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getImportPrecedence() {
/*  345 */     return this._importPrecedence;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean checkForLoop(String systemId) {
/*  350 */     if (this._systemId != null && this._systemId.equals(systemId)) {
/*  351 */       return true;
/*      */     }
/*      */     
/*  354 */     if (this._parentStylesheet != null) {
/*  355 */       return this._parentStylesheet.checkForLoop(systemId);
/*      */     }
/*  357 */     return false;
/*      */   }
/*      */   
/*      */   public void setParser(Parser parser) {
/*  361 */     super.setParser(parser);
/*  362 */     this._name = makeStylesheetName("__stylesheet_");
/*      */   }
/*      */   
/*      */   public void setParentStylesheet(Stylesheet parent) {
/*  366 */     this._parentStylesheet = parent;
/*      */   }
/*      */   
/*      */   public Stylesheet getParentStylesheet() {
/*  370 */     return this._parentStylesheet;
/*      */   }
/*      */   
/*      */   public void setImportingStylesheet(Stylesheet parent) {
/*  374 */     this._importedFrom = parent;
/*  375 */     parent.addIncludedStylesheet(this);
/*      */   }
/*      */   
/*      */   public void setIncludingStylesheet(Stylesheet parent) {
/*  379 */     this._includedFrom = parent;
/*  380 */     parent.addIncludedStylesheet(this);
/*      */   }
/*      */   
/*      */   public void addIncludedStylesheet(Stylesheet child) {
/*  384 */     if (this._includedStylesheets == null) {
/*  385 */       this._includedStylesheets = new Vector();
/*      */     }
/*  387 */     this._includedStylesheets.addElement(child);
/*      */   }
/*      */   
/*      */   public void setSystemId(String systemId) {
/*  391 */     if (systemId != null) {
/*  392 */       this._systemId = SystemIDResolver.getAbsoluteURI(systemId);
/*      */     }
/*      */   }
/*      */   
/*      */   public String getSystemId() {
/*  397 */     return this._systemId;
/*      */   }
/*      */   
/*      */   public void setSourceLoader(SourceLoader loader) {
/*  401 */     this._loader = loader;
/*      */   }
/*      */   
/*      */   public SourceLoader getSourceLoader() {
/*  405 */     return this._loader;
/*      */   }
/*      */   
/*      */   private QName makeStylesheetName(String prefix) {
/*  409 */     return getParser().getQName(prefix + getXSLTC().nextStylesheetSerial());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasGlobals() {
/*  416 */     return (this._globals.size() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasLocalParams() {
/*  425 */     if (this._hasLocalParams == null) {
/*  426 */       Vector templates = getAllValidTemplates();
/*  427 */       int n = templates.size();
/*  428 */       for (int i = 0; i < n; i++) {
/*  429 */         Template template = templates.elementAt(i);
/*  430 */         if (template.hasParams()) {
/*  431 */           this._hasLocalParams = new Boolean(true);
/*  432 */           return true;
/*      */         } 
/*      */       } 
/*  435 */       this._hasLocalParams = new Boolean(false);
/*  436 */       return false;
/*      */     } 
/*      */     
/*  439 */     return this._hasLocalParams.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addPrefixMapping(String prefix, String uri) {
/*  449 */     if (prefix.equals("") && uri.equals("http://www.w3.org/1999/xhtml"))
/*  450 */       return;  super.addPrefixMapping(prefix, uri);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void extensionURI(String prefixes, SymbolTable stable) {
/*  457 */     if (prefixes != null) {
/*  458 */       StringTokenizer tokens = new StringTokenizer(prefixes);
/*  459 */       while (tokens.hasMoreTokens()) {
/*  460 */         String prefix = tokens.nextToken();
/*  461 */         String uri = lookupNamespace(prefix);
/*  462 */         if (uri != null) {
/*  463 */           this._extensions.put(uri, prefix);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isExtension(String uri) {
/*  470 */     return (this._extensions.get(uri) != null);
/*      */   }
/*      */   
/*      */   public void excludeExtensionPrefixes(Parser parser) {
/*  474 */     SymbolTable stable = parser.getSymbolTable();
/*  475 */     String excludePrefixes = getAttribute("exclude-result-prefixes");
/*  476 */     String extensionPrefixes = getAttribute("extension-element-prefixes");
/*      */ 
/*      */     
/*  479 */     stable.excludeURI("http://www.w3.org/1999/XSL/Transform");
/*  480 */     stable.excludeNamespaces(excludePrefixes);
/*  481 */     stable.excludeNamespaces(extensionPrefixes);
/*  482 */     extensionURI(extensionPrefixes, stable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseContents(Parser parser) {
/*  491 */     SymbolTable stable = parser.getSymbolTable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  505 */     addPrefixMapping("xml", "http://www.w3.org/XML/1998/namespace");
/*      */ 
/*      */     
/*  508 */     Stylesheet sheet = stable.addStylesheet(this._name, this);
/*  509 */     if (sheet != null) {
/*      */       
/*  511 */       ErrorMsg err = new ErrorMsg("MULTIPLE_STYLESHEET_ERR", this);
/*  512 */       parser.reportError(3, err);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  520 */     if (this._simplified) {
/*  521 */       stable.excludeURI("http://www.w3.org/1999/XSL/Transform");
/*  522 */       Template template = new Template();
/*  523 */       template.parseSimplified(this, parser);
/*      */     }
/*      */     else {
/*      */       
/*  527 */       parseOwnChildren(parser);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void parseOwnChildren(Parser parser) {
/*  535 */     Vector contents = getContents();
/*  536 */     int count = contents.size();
/*      */ 
/*      */ 
/*      */     
/*  540 */     for (int i = 0; i < count; i++) {
/*  541 */       SyntaxTreeNode child = contents.elementAt(i);
/*  542 */       if (child instanceof VariableBase || child instanceof NamespaceAlias) {
/*      */         
/*  544 */         parser.getSymbolTable().setCurrentNode(child);
/*  545 */         child.parseContents(parser);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  550 */     for (int j = 0; j < count; j++) {
/*  551 */       SyntaxTreeNode child = contents.elementAt(j);
/*  552 */       if (!(child instanceof VariableBase) && !(child instanceof NamespaceAlias)) {
/*      */         
/*  554 */         parser.getSymbolTable().setCurrentNode(child);
/*  555 */         child.parseContents(parser);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  560 */       if (!this._templateInlining && child instanceof Template) {
/*  561 */         Template template = (Template)child;
/*  562 */         String name = "template$dot$" + template.getPosition();
/*  563 */         template.setName(parser.getQName(name));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void processModes() {
/*  569 */     if (this._defaultMode == null)
/*  570 */       this._defaultMode = new Mode(null, this, ""); 
/*  571 */     this._defaultMode.processPatterns(this._keys);
/*  572 */     Enumeration modes = this._modes.elements();
/*  573 */     while (modes.hasMoreElements()) {
/*  574 */       Mode mode = modes.nextElement();
/*  575 */       mode.processPatterns(this._keys);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compileModes(ClassGenerator classGen) {
/*  580 */     this._defaultMode.compileApplyTemplates(classGen);
/*  581 */     Enumeration modes = this._modes.elements();
/*  582 */     while (modes.hasMoreElements()) {
/*  583 */       Mode mode = modes.nextElement();
/*  584 */       mode.compileApplyTemplates(classGen);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Mode getMode(QName modeName) {
/*  589 */     if (modeName == null) {
/*  590 */       if (this._defaultMode == null) {
/*  591 */         this._defaultMode = new Mode(null, this, "");
/*      */       }
/*  593 */       return this._defaultMode;
/*      */     } 
/*      */     
/*  596 */     Mode mode = (Mode)this._modes.get(modeName);
/*  597 */     if (mode == null) {
/*  598 */       String suffix = Integer.toString(this._nextModeSerial++);
/*  599 */       this._modes.put(modeName, mode = new Mode(modeName, this, suffix));
/*      */     } 
/*  601 */     return mode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  609 */     int count = this._globals.size();
/*  610 */     for (int i = 0; i < count; i++) {
/*  611 */       VariableBase var = this._globals.elementAt(i);
/*  612 */       var.typeCheck(stable);
/*      */     } 
/*  614 */     return typeCheckContents(stable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  621 */     translate();
/*      */   }
/*      */   
/*      */   private void addDOMField(ClassGenerator classGen) {
/*  625 */     FieldGen fgen = new FieldGen(1, Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), "_dom", classGen.getConstantPool());
/*      */ 
/*      */ 
/*      */     
/*  629 */     classGen.addField(fgen.getField());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addStaticField(ClassGenerator classGen, String type, String name) {
/*  638 */     FieldGen fgen = new FieldGen(12, Util.getJCRefType(type), name, classGen.getConstantPool());
/*      */ 
/*      */ 
/*      */     
/*  642 */     classGen.addField(fgen.getField());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void translate() {
/*  650 */     this._className = getXSLTC().getClassName();
/*      */ 
/*      */     
/*  653 */     ClassGenerator classGen = new ClassGenerator(this._className, "org.apache.xalan.xsltc.runtime.AbstractTranslet", "", 33, null, this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  660 */     addDOMField(classGen);
/*      */ 
/*      */ 
/*      */     
/*  664 */     compileTransform(classGen);
/*      */ 
/*      */     
/*  667 */     Enumeration elements = elements();
/*  668 */     while (elements.hasMoreElements()) {
/*  669 */       Object element = elements.nextElement();
/*      */       
/*  671 */       if (element instanceof Template) {
/*      */         
/*  673 */         Template template = (Template)element;
/*      */         
/*  675 */         getMode(template.getModeName()).addTemplate(template);
/*      */         continue;
/*      */       } 
/*  678 */       if (element instanceof AttributeSet) {
/*  679 */         ((AttributeSet)element).translate(classGen, null); continue;
/*      */       } 
/*  681 */       if (element instanceof Output) {
/*      */         
/*  683 */         Output output = (Output)element;
/*  684 */         if (output.enabled()) this._lastOutputElement = output;
/*      */       
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  693 */     checkOutputMethod();
/*  694 */     processModes();
/*  695 */     compileModes(classGen);
/*  696 */     compileStaticInitializer(classGen);
/*  697 */     compileConstructor(classGen, this._lastOutputElement);
/*      */     
/*  699 */     if (!getParser().errorsFound()) {
/*  700 */       getXSLTC().dumpClass(classGen.getJavaClass());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void compileStaticInitializer(ClassGenerator classGen) {
/*  711 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  712 */     InstructionList il = new InstructionList();
/*      */     
/*  714 */     MethodGenerator staticConst = new MethodGenerator(9, (Type)Type.VOID, null, null, "<clinit>", this._className, il, cpg);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  720 */     addStaticField(classGen, "[Ljava/lang/String;", "_sNamesArray");
/*  721 */     addStaticField(classGen, "[Ljava/lang/String;", "_sUrisArray");
/*  722 */     addStaticField(classGen, "[I", "_sTypesArray");
/*  723 */     addStaticField(classGen, "[Ljava/lang/String;", "_sNamespaceArray");
/*      */ 
/*      */     
/*  726 */     int charDataFieldCount = getXSLTC().getCharacterDataCount();
/*  727 */     for (int i = 0; i < charDataFieldCount; i++) {
/*  728 */       addStaticField(classGen, "[C", "_scharData" + i);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  733 */     Vector namesIndex = getXSLTC().getNamesIndex();
/*  734 */     int size = namesIndex.size();
/*  735 */     String[] namesArray = new String[size];
/*  736 */     String[] urisArray = new String[size];
/*  737 */     int[] typesArray = new int[size];
/*      */ 
/*      */     
/*  740 */     for (int j = 0; j < size; j++) {
/*  741 */       String encodedName = namesIndex.elementAt(j); int index;
/*  742 */       if ((index = encodedName.lastIndexOf(':')) > -1) {
/*  743 */         urisArray[j] = encodedName.substring(0, index);
/*      */       }
/*      */       
/*  746 */       index++;
/*  747 */       if (encodedName.charAt(index) == '@') {
/*  748 */         typesArray[j] = 2;
/*  749 */         index++;
/*  750 */       } else if (encodedName.charAt(index) == '?') {
/*  751 */         typesArray[j] = 13;
/*  752 */         index++;
/*      */       } else {
/*  754 */         typesArray[j] = 1;
/*      */       } 
/*      */       
/*  757 */       if (index == 0) {
/*  758 */         namesArray[j] = encodedName;
/*      */       } else {
/*      */         
/*  761 */         namesArray[j] = encodedName.substring(index);
/*      */       } 
/*      */     } 
/*      */     
/*  765 */     il.append((CompoundInstruction)new PUSH(cpg, size));
/*  766 */     il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/*      */     
/*  768 */     for (int k = 0; k < size; k++) {
/*  769 */       String name = namesArray[k];
/*  770 */       il.append((Instruction)InstructionConstants.DUP);
/*  771 */       il.append((CompoundInstruction)new PUSH(cpg, k));
/*  772 */       il.append((CompoundInstruction)new PUSH(cpg, name));
/*  773 */       il.append((Instruction)InstructionConstants.AASTORE);
/*      */     } 
/*  775 */     il.append((Instruction)new PUTSTATIC(cpg.addFieldref(this._className, "_sNamesArray", "[Ljava/lang/String;")));
/*      */ 
/*      */ 
/*      */     
/*  779 */     il.append((CompoundInstruction)new PUSH(cpg, size));
/*  780 */     il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/*      */     
/*  782 */     for (int m = 0; m < size; m++) {
/*  783 */       String uri = urisArray[m];
/*  784 */       il.append((Instruction)InstructionConstants.DUP);
/*  785 */       il.append((CompoundInstruction)new PUSH(cpg, m));
/*  786 */       il.append((CompoundInstruction)new PUSH(cpg, uri));
/*  787 */       il.append((Instruction)InstructionConstants.AASTORE);
/*      */     } 
/*  789 */     il.append((Instruction)new PUTSTATIC(cpg.addFieldref(this._className, "_sUrisArray", "[Ljava/lang/String;")));
/*      */ 
/*      */ 
/*      */     
/*  793 */     il.append((CompoundInstruction)new PUSH(cpg, size));
/*  794 */     il.append((Instruction)new NEWARRAY(Type.INT));
/*      */     
/*  796 */     for (int n = 0; n < size; n++) {
/*  797 */       int nodeType = typesArray[n];
/*  798 */       il.append((Instruction)InstructionConstants.DUP);
/*  799 */       il.append((CompoundInstruction)new PUSH(cpg, n));
/*  800 */       il.append((CompoundInstruction)new PUSH(cpg, nodeType));
/*  801 */       il.append((Instruction)InstructionConstants.IASTORE);
/*      */     } 
/*  803 */     il.append((Instruction)new PUTSTATIC(cpg.addFieldref(this._className, "_sTypesArray", "[I")));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  808 */     Vector namespaces = getXSLTC().getNamespaceIndex();
/*  809 */     il.append((CompoundInstruction)new PUSH(cpg, namespaces.size()));
/*  810 */     il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/*      */     
/*  812 */     for (int i1 = 0; i1 < namespaces.size(); i1++) {
/*  813 */       String ns = namespaces.elementAt(i1);
/*  814 */       il.append((Instruction)InstructionConstants.DUP);
/*  815 */       il.append((CompoundInstruction)new PUSH(cpg, i1));
/*  816 */       il.append((CompoundInstruction)new PUSH(cpg, ns));
/*  817 */       il.append((Instruction)InstructionConstants.AASTORE);
/*      */     } 
/*  819 */     il.append((Instruction)new PUTSTATIC(cpg.addFieldref(this._className, "_sNamespaceArray", "[Ljava/lang/String;")));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  824 */     int charDataCount = getXSLTC().getCharacterDataCount();
/*  825 */     int toCharArray = cpg.addMethodref("java.lang.String", "toCharArray", "()[C");
/*  826 */     for (int i2 = 0; i2 < charDataCount; i2++) {
/*  827 */       il.append((CompoundInstruction)new PUSH(cpg, getXSLTC().getCharacterData(i2)));
/*  828 */       il.append((Instruction)new INVOKEVIRTUAL(toCharArray));
/*  829 */       il.append((Instruction)new PUTSTATIC(cpg.addFieldref(this._className, "_scharData" + i2, "[C")));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  834 */     il.append((Instruction)InstructionConstants.RETURN);
/*      */     
/*  836 */     staticConst.stripAttributes(true);
/*  837 */     staticConst.setMaxLocals();
/*  838 */     staticConst.setMaxStack();
/*  839 */     classGen.addMethod(staticConst.getMethod());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void compileConstructor(ClassGenerator classGen, Output output) {
/*  848 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  849 */     InstructionList il = new InstructionList();
/*      */     
/*  851 */     MethodGenerator constructor = new MethodGenerator(1, (Type)Type.VOID, null, null, "<init>", this._className, il, cpg);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  858 */     il.append(classGen.loadTranslet());
/*  859 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "<init>", "()V")));
/*      */ 
/*      */     
/*  862 */     il.append(classGen.loadTranslet());
/*  863 */     il.append((Instruction)new GETSTATIC(cpg.addFieldref(this._className, "_sNamesArray", "[Ljava/lang/String;")));
/*      */ 
/*      */     
/*  866 */     il.append((Instruction)new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namesArray", "[Ljava/lang/String;")));
/*      */ 
/*      */ 
/*      */     
/*  870 */     il.append(classGen.loadTranslet());
/*  871 */     il.append((Instruction)new GETSTATIC(cpg.addFieldref(this._className, "_sUrisArray", "[Ljava/lang/String;")));
/*      */ 
/*      */     
/*  874 */     il.append((Instruction)new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "urisArray", "[Ljava/lang/String;")));
/*      */ 
/*      */ 
/*      */     
/*  878 */     il.append(classGen.loadTranslet());
/*  879 */     il.append((Instruction)new GETSTATIC(cpg.addFieldref(this._className, "_sTypesArray", "[I")));
/*      */ 
/*      */     
/*  882 */     il.append((Instruction)new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "typesArray", "[I")));
/*      */ 
/*      */ 
/*      */     
/*  886 */     il.append(classGen.loadTranslet());
/*  887 */     il.append((Instruction)new GETSTATIC(cpg.addFieldref(this._className, "_sNamespaceArray", "[Ljava/lang/String;")));
/*      */ 
/*      */     
/*  890 */     il.append((Instruction)new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namespaceArray", "[Ljava/lang/String;")));
/*      */ 
/*      */ 
/*      */     
/*  894 */     il.append(classGen.loadTranslet());
/*  895 */     il.append((CompoundInstruction)new PUSH(cpg, 101));
/*  896 */     il.append((Instruction)new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "transletVersion", "I")));
/*      */ 
/*      */ 
/*      */     
/*  900 */     if (this._hasIdCall) {
/*  901 */       il.append(classGen.loadTranslet());
/*  902 */       il.append((CompoundInstruction)new PUSH(cpg, Boolean.TRUE));
/*  903 */       il.append((Instruction)new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_hasIdCall", "Z")));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  909 */     if (output != null)
/*      */     {
/*  911 */       output.translate(classGen, constructor);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  916 */     if (this._numberFormattingUsed) {
/*  917 */       DecimalFormatting.translateDefaultDFS(classGen, constructor);
/*      */     }
/*  919 */     il.append((Instruction)InstructionConstants.RETURN);
/*      */     
/*  921 */     constructor.stripAttributes(true);
/*  922 */     constructor.setMaxLocals();
/*  923 */     constructor.setMaxStack();
/*  924 */     classGen.addMethod(constructor.getMethod());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String compileTopLevel(ClassGenerator classGen, Enumeration elements) {
/*  935 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*      */     
/*  937 */     Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;") };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  943 */     String[] argNames = { "document", "iterator", "handler" };
/*      */ 
/*      */ 
/*      */     
/*  947 */     InstructionList il = new InstructionList();
/*      */     
/*  949 */     MethodGenerator toplevel = new MethodGenerator(1, (Type)Type.VOID, argTypes, argNames, "topLevel", this._className, il, classGen.getConstantPool());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  956 */     toplevel.addException("org.apache.xalan.xsltc.TransletException");
/*      */ 
/*      */     
/*  959 */     LocalVariableGen current = toplevel.addLocalVariable("current", (Type)Type.INT, il.getEnd(), null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  964 */     int setFilter = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "setFilter", "(Lorg/apache/xalan/xsltc/StripFilter;)V");
/*      */ 
/*      */ 
/*      */     
/*  968 */     il.append((CompoundInstruction)new PUSH(cpg, 0));
/*  969 */     il.append((Instruction)new ISTORE(current.getIndex()));
/*      */ 
/*      */     
/*  972 */     this._globals = resolveReferences(this._globals);
/*  973 */     int count = this._globals.size();
/*  974 */     for (int i = 0; i < count; i++) {
/*  975 */       VariableBase var = this._globals.elementAt(i);
/*  976 */       var.translate(classGen, toplevel);
/*      */     } 
/*      */ 
/*      */     
/*  980 */     Vector whitespaceRules = new Vector();
/*  981 */     while (elements.hasMoreElements()) {
/*  982 */       Object element = elements.nextElement();
/*      */       
/*  984 */       if (element instanceof DecimalFormatting) {
/*  985 */         ((DecimalFormatting)element).translate(classGen, toplevel);
/*      */         continue;
/*      */       } 
/*  988 */       if (element instanceof Whitespace) {
/*  989 */         whitespaceRules.addAll(((Whitespace)element).getRules());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  994 */     if (whitespaceRules.size() > 0) {
/*  995 */       Whitespace.translateRules(whitespaceRules, classGen);
/*      */     }
/*      */     
/*  998 */     if (classGen.containsMethod("stripSpace", "(Lorg/apache/xalan/xsltc/DOM;II)Z") != null) {
/*  999 */       il.append(toplevel.loadDOM());
/* 1000 */       il.append(classGen.loadTranslet());
/* 1001 */       il.append((Instruction)new INVOKEINTERFACE(setFilter, 2));
/*      */     } 
/*      */     
/* 1004 */     il.append((Instruction)InstructionConstants.RETURN);
/*      */ 
/*      */     
/* 1007 */     toplevel.stripAttributes(true);
/* 1008 */     toplevel.setMaxLocals();
/* 1009 */     toplevel.setMaxStack();
/* 1010 */     toplevel.removeNOPs();
/*      */     
/* 1012 */     classGen.addMethod(toplevel.getMethod());
/*      */     
/* 1014 */     return "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V";
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
/*      */   private Vector resolveReferences(Vector input) {
/* 1028 */     for (int i = 0; i < input.size(); i++) {
/* 1029 */       VariableBase var = input.elementAt(i);
/* 1030 */       Vector dep = var.getDependencies();
/* 1031 */       int depSize = (dep != null) ? dep.size() : 0;
/*      */       
/* 1033 */       for (int j = 0; j < depSize; j++) {
/* 1034 */         VariableBase depVar = dep.elementAt(j);
/* 1035 */         if (!input.contains(depVar)) {
/* 1036 */           input.addElement(depVar);
/*      */         }
/*      */       } 
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
/* 1049 */     Vector result = new Vector();
/* 1050 */     while (input.size() > 0) {
/* 1051 */       boolean changed = false;
/* 1052 */       for (int j = 0; j < input.size(); ) {
/* 1053 */         VariableBase var = input.elementAt(j);
/* 1054 */         Vector dep = var.getDependencies();
/* 1055 */         if (dep == null || result.containsAll(dep)) {
/* 1056 */           result.addElement(var);
/* 1057 */           input.remove(j);
/* 1058 */           changed = true;
/*      */           continue;
/*      */         } 
/* 1061 */         j++;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1066 */       if (!changed) {
/* 1067 */         ErrorMsg err = new ErrorMsg("CIRCULAR_VARIABLE_ERR", input.toString(), this);
/*      */         
/* 1069 */         getParser().reportError(3, err);
/* 1070 */         return result;
/*      */       } 
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
/* 1082 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String compileBuildKeys(ClassGenerator classGen) {
/* 1091 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*      */     
/* 1093 */     Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;"), (Type)Type.INT };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1100 */     String[] argNames = { "document", "iterator", "handler", "current" };
/*      */ 
/*      */ 
/*      */     
/* 1104 */     InstructionList il = new InstructionList();
/*      */     
/* 1106 */     MethodGenerator buildKeys = new MethodGenerator(1, (Type)Type.VOID, argTypes, argNames, "buildKeys", this._className, il, classGen.getConstantPool());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1113 */     buildKeys.addException("org.apache.xalan.xsltc.TransletException");
/*      */     
/* 1115 */     Enumeration elements = elements();
/*      */     
/* 1117 */     while (elements.hasMoreElements()) {
/*      */       
/* 1119 */       Object element = elements.nextElement();
/* 1120 */       if (element instanceof Key) {
/* 1121 */         Key key = (Key)element;
/* 1122 */         key.translate(classGen, buildKeys);
/* 1123 */         this._keys.put(key.getName(), key);
/*      */       } 
/*      */     } 
/*      */     
/* 1127 */     il.append((Instruction)InstructionConstants.RETURN);
/*      */ 
/*      */     
/* 1130 */     buildKeys.stripAttributes(true);
/* 1131 */     buildKeys.setMaxLocals();
/* 1132 */     buildKeys.setMaxStack();
/* 1133 */     buildKeys.removeNOPs();
/*      */     
/* 1135 */     classGen.addMethod(buildKeys.getMethod());
/*      */     
/* 1137 */     return "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;I)V";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void compileTransform(ClassGenerator classGen) {
/* 1146 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1152 */     Type[] argTypes = new Type[3];
/*      */     
/* 1154 */     argTypes[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
/* 1155 */     argTypes[1] = Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;");
/* 1156 */     argTypes[2] = Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;");
/*      */     
/* 1158 */     String[] argNames = new String[3];
/* 1159 */     argNames[0] = "document";
/* 1160 */     argNames[1] = "iterator";
/* 1161 */     argNames[2] = "handler";
/*      */     
/* 1163 */     InstructionList il = new InstructionList();
/* 1164 */     MethodGenerator transf = new MethodGenerator(1, (Type)Type.VOID, argTypes, argNames, "transform", this._className, il, classGen.getConstantPool());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1172 */     transf.addException("org.apache.xalan.xsltc.TransletException");
/*      */ 
/*      */     
/* 1175 */     LocalVariableGen current = transf.addLocalVariable("current", (Type)Type.INT, il.getEnd(), null);
/*      */ 
/*      */ 
/*      */     
/* 1179 */     String applyTemplatesSig = classGen.getApplyTemplatesSig();
/* 1180 */     int applyTemplates = cpg.addMethodref(getClassName(), "applyTemplates", applyTemplatesSig);
/*      */ 
/*      */     
/* 1183 */     int domField = cpg.addFieldref(getClassName(), "_dom", "Lorg/apache/xalan/xsltc/DOM;");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1188 */     il.append(classGen.loadTranslet());
/*      */ 
/*      */     
/* 1191 */     if (isMultiDocument()) {
/* 1192 */       il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.MultiDOM")));
/* 1193 */       il.append((Instruction)InstructionConstants.DUP);
/*      */     } 
/*      */     
/* 1196 */     il.append(classGen.loadTranslet());
/* 1197 */     il.append(transf.loadDOM());
/* 1198 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "makeDOMAdapter", "(Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xalan/xsltc/dom/DOMAdapter;")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1204 */     if (isMultiDocument()) {
/* 1205 */       int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.MultiDOM", "<init>", "(Lorg/apache/xalan/xsltc/DOM;)V");
/*      */ 
/*      */       
/* 1208 */       il.append((Instruction)new INVOKESPECIAL(init));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1213 */     il.append((Instruction)new PUTFIELD(domField));
/*      */ 
/*      */     
/* 1216 */     il.append((CompoundInstruction)new PUSH(cpg, 0));
/* 1217 */     il.append((Instruction)new ISTORE(current.getIndex()));
/*      */ 
/*      */     
/* 1220 */     il.append(classGen.loadTranslet());
/* 1221 */     il.append(transf.loadHandler());
/* 1222 */     int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "transferOutputSettings", "(Lorg/apache/xml/serializer/SerializationHandler;)V");
/*      */ 
/*      */     
/* 1225 */     il.append((Instruction)new INVOKEVIRTUAL(index));
/*      */ 
/*      */ 
/*      */     
/* 1229 */     String keySig = compileBuildKeys(classGen);
/* 1230 */     int keyIdx = cpg.addMethodref(getClassName(), "buildKeys", keySig);
/*      */     
/* 1232 */     il.append(classGen.loadTranslet());
/* 1233 */     il.append(classGen.loadTranslet());
/* 1234 */     il.append((Instruction)new GETFIELD(domField));
/* 1235 */     il.append(transf.loadIterator());
/* 1236 */     il.append(transf.loadHandler());
/* 1237 */     il.append((CompoundInstruction)new PUSH(cpg, 0));
/* 1238 */     il.append((Instruction)new INVOKEVIRTUAL(keyIdx));
/*      */ 
/*      */ 
/*      */     
/* 1242 */     Enumeration toplevel = elements();
/* 1243 */     if (this._globals.size() > 0 || toplevel.hasMoreElements()) {
/*      */       
/* 1245 */       String topLevelSig = compileTopLevel(classGen, toplevel);
/*      */       
/* 1247 */       int topLevelIdx = cpg.addMethodref(getClassName(), "topLevel", topLevelSig);
/*      */ 
/*      */ 
/*      */       
/* 1251 */       il.append(classGen.loadTranslet());
/* 1252 */       il.append(classGen.loadTranslet());
/* 1253 */       il.append((Instruction)new GETFIELD(domField));
/* 1254 */       il.append(transf.loadIterator());
/* 1255 */       il.append(transf.loadHandler());
/* 1256 */       il.append((Instruction)new INVOKEVIRTUAL(topLevelIdx));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1263 */     il.append(transf.loadHandler());
/* 1264 */     il.append(transf.startDocument());
/*      */ 
/*      */     
/* 1267 */     il.append(classGen.loadTranslet());
/*      */     
/* 1269 */     il.append(classGen.loadTranslet());
/* 1270 */     il.append((Instruction)new GETFIELD(domField));
/*      */     
/* 1272 */     il.append(transf.loadIterator());
/* 1273 */     il.append(transf.loadHandler());
/* 1274 */     il.append((Instruction)new INVOKEVIRTUAL(applyTemplates));
/*      */     
/* 1276 */     il.append(transf.loadHandler());
/* 1277 */     il.append(transf.endDocument());
/*      */     
/* 1279 */     il.append((Instruction)InstructionConstants.RETURN);
/*      */ 
/*      */     
/* 1282 */     transf.stripAttributes(true);
/* 1283 */     transf.setMaxLocals();
/* 1284 */     transf.setMaxStack();
/* 1285 */     transf.removeNOPs();
/*      */     
/* 1287 */     classGen.addMethod(transf.getMethod());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void peepHoleOptimization(MethodGenerator methodGen) {
/* 1294 */     String pattern = "`ALOAD'`POP'`Instruction'";
/* 1295 */     InstructionList il = methodGen.getInstructionList();
/* 1296 */     InstructionFinder find = new InstructionFinder(il);
/* 1297 */     for (Iterator iter = find.search("`ALOAD'`POP'`Instruction'"); iter.hasNext(); ) {
/* 1298 */       InstructionHandle[] match = iter.next();
/*      */       
/* 1300 */       try { il.delete(match[0], match[1]); } catch (TargetLostException targetLostException) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int addParam(Param param) {
/* 1309 */     this._globals.addElement(param);
/* 1310 */     return this._globals.size() - 1;
/*      */   }
/*      */   
/*      */   public int addVariable(Variable global) {
/* 1314 */     this._globals.addElement(global);
/* 1315 */     return this._globals.size() - 1;
/*      */   }
/*      */   
/*      */   public void display(int indent) {
/* 1319 */     indent(indent);
/* 1320 */     Util.println("Stylesheet");
/* 1321 */     displayContents(indent + 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespace(String prefix) {
/* 1326 */     return lookupNamespace(prefix);
/*      */   }
/*      */   
/*      */   public String getClassName() {
/* 1330 */     return this._className;
/*      */   }
/*      */   
/*      */   public Vector getTemplates() {
/* 1334 */     return this._templates;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vector getAllValidTemplates() {
/* 1339 */     if (this._includedStylesheets == null) {
/* 1340 */       return this._templates;
/*      */     }
/*      */ 
/*      */     
/* 1344 */     if (this._allValidTemplates == null) {
/* 1345 */       Vector templates = new Vector();
/* 1346 */       int size = this._includedStylesheets.size();
/* 1347 */       for (int i = 0; i < size; i++) {
/* 1348 */         Stylesheet included = this._includedStylesheets.elementAt(i);
/* 1349 */         templates.addAll(included.getAllValidTemplates());
/*      */       } 
/* 1351 */       templates.addAll(this._templates);
/*      */ 
/*      */       
/* 1354 */       if (this._parentStylesheet != null) {
/* 1355 */         return templates;
/*      */       }
/* 1357 */       this._allValidTemplates = templates;
/*      */     } 
/*      */     
/* 1360 */     return this._allValidTemplates;
/*      */   }
/*      */   
/*      */   protected void addTemplate(Template template) {
/* 1364 */     this._templates.addElement(template);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Stylesheet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */