/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ANEWARRAY;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.DUP_X1;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.ICONST;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.NEWARRAY;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.xml.sax.Attributes;
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
/*     */ public abstract class SyntaxTreeNode
/*     */   implements Constants
/*     */ {
/*     */   private Parser _parser;
/*     */   protected SyntaxTreeNode _parent;
/*     */   private Stylesheet _stylesheet;
/*     */   private Template _template;
/*  67 */   private final Vector _contents = new Vector(2);
/*     */   
/*     */   protected QName _qname;
/*     */   
/*     */   private int _line;
/*  72 */   protected Attributes _attributes = null;
/*  73 */   private Hashtable _prefixMapping = null;
/*     */ 
/*     */   
/*  76 */   protected static final SyntaxTreeNode Dummy = new AbsolutePathPattern(null);
/*     */   
/*     */   protected static final int IndentIncrement = 4;
/*     */   
/*  80 */   private static final char[] _spaces = "                                                       ".toCharArray();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SyntaxTreeNode() {
/*  88 */     this._line = 0;
/*  89 */     this._qname = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SyntaxTreeNode(int line) {
/*  97 */     this._line = line;
/*  98 */     this._qname = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SyntaxTreeNode(String uri, String prefix, String local) {
/* 108 */     this._line = 0;
/* 109 */     setQName(uri, prefix, local);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setLineNumber(int line) {
/* 117 */     this._line = line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getLineNumber() {
/* 125 */     return this._line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setQName(QName qname) {
/* 133 */     this._qname = qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setQName(String uri, String prefix, String localname) {
/* 143 */     this._qname = new QName(uri, prefix, localname);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected QName getQName() {
/* 151 */     return this._qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setAttributes(Attributes attributes) {
/* 160 */     this._attributes = attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getAttribute(String qname) {
/* 169 */     if (this._attributes == null) {
/* 170 */       return "";
/*     */     }
/* 172 */     String value = this._attributes.getValue(qname);
/* 173 */     return (value == null || value.equals("")) ? "" : value;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasAttribute(String qname) {
/* 178 */     return (this._attributes != null && this._attributes.getValue(qname) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Attributes getAttributes() {
/* 187 */     return this._attributes;
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
/*     */   protected void setPrefixMapping(Hashtable mapping) {
/* 199 */     this._prefixMapping = mapping;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Hashtable getPrefixMapping() {
/* 210 */     return this._prefixMapping;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addPrefixMapping(String prefix, String uri) {
/* 219 */     if (this._prefixMapping == null)
/* 220 */       this._prefixMapping = new Hashtable(); 
/* 221 */     this._prefixMapping.put(prefix, uri);
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
/*     */   protected String lookupNamespace(String prefix) {
/* 234 */     String uri = null;
/*     */ 
/*     */     
/* 237 */     if (this._prefixMapping != null) {
/* 238 */       uri = (String)this._prefixMapping.get(prefix);
/*     */     }
/* 240 */     if (uri == null && this._parent != null) {
/* 241 */       uri = this._parent.lookupNamespace(prefix);
/* 242 */       if (prefix == "" && uri == null) {
/* 243 */         uri = "";
/*     */       }
/*     */     } 
/* 246 */     return uri;
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
/*     */   protected String lookupPrefix(String uri) {
/* 261 */     String prefix = null;
/*     */ 
/*     */     
/* 264 */     if (this._prefixMapping != null && this._prefixMapping.contains(uri)) {
/*     */       
/* 266 */       Enumeration prefixes = this._prefixMapping.keys();
/* 267 */       while (prefixes.hasMoreElements()) {
/* 268 */         prefix = prefixes.nextElement();
/* 269 */         String mapsTo = (String)this._prefixMapping.get(prefix);
/* 270 */         if (mapsTo.equals(uri)) return prefix;
/*     */       
/*     */       }
/*     */     
/* 274 */     } else if (this._parent != null) {
/* 275 */       prefix = this._parent.lookupPrefix(uri);
/* 276 */       if (uri == "" && prefix == null)
/* 277 */         prefix = ""; 
/*     */     } 
/* 279 */     return prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParser(Parser parser) {
/* 288 */     this._parser = parser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Parser getParser() {
/* 296 */     return this._parser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParent(SyntaxTreeNode parent) {
/* 304 */     if (this._parent == null) {
/* 305 */       this._parent = parent;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final SyntaxTreeNode getParent() {
/* 313 */     return this._parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final boolean isDummy() {
/* 321 */     return (this == Dummy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getImportPrecedence() {
/* 330 */     Stylesheet stylesheet = getStylesheet();
/* 331 */     if (stylesheet == null) return Integer.MIN_VALUE; 
/* 332 */     return stylesheet.getImportPrecedence();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stylesheet getStylesheet() {
/* 341 */     if (this._stylesheet == null) {
/* 342 */       SyntaxTreeNode parent = this;
/* 343 */       while (parent != null) {
/* 344 */         if (parent instanceof Stylesheet)
/* 345 */           return (Stylesheet)parent; 
/* 346 */         parent = parent.getParent();
/*     */       } 
/* 348 */       this._stylesheet = (Stylesheet)parent;
/*     */     } 
/* 350 */     return this._stylesheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Template getTemplate() {
/* 360 */     if (this._template == null) {
/* 361 */       SyntaxTreeNode parent = this;
/* 362 */       while (parent != null && !(parent instanceof Template))
/* 363 */         parent = parent.getParent(); 
/* 364 */       this._template = (Template)parent;
/*     */     } 
/* 366 */     return this._template;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final XSLTC getXSLTC() {
/* 374 */     return this._parser.getXSLTC();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final SymbolTable getSymbolTable() {
/* 382 */     return (this._parser == null) ? null : this._parser.getSymbolTable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 393 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void parseChildren(Parser parser) {
/* 403 */     Vector locals = null;
/*     */     
/* 405 */     int count = this._contents.size();
/* 406 */     for (int i = 0; i < count; i++) {
/* 407 */       SyntaxTreeNode child = this._contents.elementAt(i);
/* 408 */       parser.getSymbolTable().setCurrentNode(child);
/* 409 */       child.parseContents(parser);
/*     */       
/* 411 */       QName varOrParamName = updateScope(parser, child);
/* 412 */       if (varOrParamName != null) {
/* 413 */         if (locals == null) {
/* 414 */           locals = new Vector(2);
/*     */         }
/* 416 */         locals.addElement(varOrParamName);
/*     */       } 
/*     */     } 
/*     */     
/* 420 */     parser.getSymbolTable().setCurrentNode(this);
/*     */ 
/*     */     
/* 423 */     if (locals != null) {
/* 424 */       int nLocals = locals.size();
/* 425 */       for (int j = 0; j < nLocals; j++) {
/* 426 */         parser.removeVariable(locals.elementAt(j));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected QName updateScope(Parser parser, SyntaxTreeNode node) {
/* 436 */     if (node instanceof Variable) {
/* 437 */       Variable var = (Variable)node;
/* 438 */       parser.addVariable(var);
/* 439 */       return var.getName();
/*     */     } 
/* 441 */     if (node instanceof Param) {
/* 442 */       Param param = (Param)node;
/* 443 */       parser.addParameter(param);
/* 444 */       return param.getName();
/*     */     } 
/*     */     
/* 447 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Type typeCheck(SymbolTable paramSymbolTable) throws TypeCheckError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Type typeCheckContents(SymbolTable stable) throws TypeCheckError {
/* 463 */     int n = elementCount();
/* 464 */     for (int i = 0; i < n; i++) {
/* 465 */       SyntaxTreeNode item = this._contents.elementAt(i);
/* 466 */       item.typeCheck(stable);
/*     */     } 
/* 468 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void translate(ClassGenerator paramClassGenerator, MethodGenerator paramMethodGenerator);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void translateContents(ClassGenerator classGen, MethodGenerator methodGen) {
/* 487 */     int n = elementCount();
/* 488 */     for (int i = 0; i < n; i++) {
/* 489 */       SyntaxTreeNode item = this._contents.elementAt(i);
/* 490 */       item.translate(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 498 */     for (int j = 0; j < n; j++) {
/* 499 */       if (this._contents.elementAt(j) instanceof VariableBase) {
/* 500 */         VariableBase var = this._contents.elementAt(j);
/* 501 */         var.unmapRegister(methodGen);
/*     */       } 
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
/*     */   private boolean isSimpleRTF(SyntaxTreeNode node) {
/* 516 */     Vector contents = node.getContents();
/* 517 */     for (int i = 0; i < contents.size(); i++) {
/* 518 */       SyntaxTreeNode item = contents.elementAt(i);
/* 519 */       if (!isTextElement(item, false)) {
/* 520 */         return false;
/*     */       }
/*     */     } 
/* 523 */     return true;
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
/*     */   private boolean isAdaptiveRTF(SyntaxTreeNode node) {
/* 537 */     Vector contents = node.getContents();
/* 538 */     for (int i = 0; i < contents.size(); i++) {
/* 539 */       SyntaxTreeNode item = contents.elementAt(i);
/* 540 */       if (!isTextElement(item, true)) {
/* 541 */         return false;
/*     */       }
/*     */     } 
/* 544 */     return true;
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
/*     */   private boolean isTextElement(SyntaxTreeNode node, boolean doExtendedCheck) {
/* 564 */     if (node instanceof ValueOf || node instanceof Number || node instanceof Text)
/*     */     {
/*     */       
/* 567 */       return true;
/*     */     }
/* 569 */     if (node instanceof If) {
/* 570 */       return doExtendedCheck ? isAdaptiveRTF(node) : isSimpleRTF(node);
/*     */     }
/* 572 */     if (node instanceof Choose) {
/* 573 */       Vector contents = node.getContents();
/* 574 */       for (int i = 0; i < contents.size(); ) {
/* 575 */         SyntaxTreeNode item = contents.elementAt(i);
/* 576 */         if (item instanceof Text || ((item instanceof When || item instanceof Otherwise) && ((doExtendedCheck && isAdaptiveRTF(item)) || (!doExtendedCheck && isSimpleRTF(item))))) {
/*     */           i++;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 582 */         return false;
/*     */       } 
/* 584 */       return true;
/*     */     } 
/* 586 */     if (doExtendedCheck && (node instanceof CallTemplate || node instanceof ApplyTemplates))
/*     */     {
/*     */       
/* 589 */       return true;
/*     */     }
/* 591 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void compileResultTree(ClassGenerator classGen, MethodGenerator methodGen) {
/* 602 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 603 */     InstructionList il = methodGen.getInstructionList();
/* 604 */     Stylesheet stylesheet = classGen.getStylesheet();
/*     */     
/* 606 */     boolean isSimple = isSimpleRTF(this);
/* 607 */     boolean isAdaptive = false;
/* 608 */     if (!isSimple) {
/* 609 */       isAdaptive = isAdaptiveRTF(this);
/*     */     }
/*     */     
/* 612 */     int rtfType = isSimple ? 0 : (isAdaptive ? 1 : 2);
/*     */ 
/*     */ 
/*     */     
/* 616 */     il.append(methodGen.loadHandler());
/*     */     
/* 618 */     String DOM_CLASS = classGen.getDOMClass();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 624 */     il.append(methodGen.loadDOM());
/* 625 */     int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getResultTreeFrag", "(IIZ)Lorg/apache/xalan/xsltc/DOM;");
/*     */ 
/*     */     
/* 628 */     il.append((CompoundInstruction)new PUSH(cpg, 32));
/* 629 */     il.append((CompoundInstruction)new PUSH(cpg, rtfType));
/* 630 */     il.append((CompoundInstruction)new PUSH(cpg, stylesheet.callsNodeset()));
/* 631 */     il.append((Instruction)new INVOKEINTERFACE(index, 4));
/*     */     
/* 633 */     il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */     
/* 636 */     index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getOutputDomBuilder", "()Lorg/apache/xml/serializer/SerializationHandler;");
/*     */ 
/*     */ 
/*     */     
/* 640 */     il.append((Instruction)new INVOKEINTERFACE(index, 1));
/* 641 */     il.append((Instruction)InstructionConstants.DUP);
/* 642 */     il.append(methodGen.storeHandler());
/*     */ 
/*     */     
/* 645 */     il.append(methodGen.startDocument());
/*     */ 
/*     */     
/* 648 */     translateContents(classGen, methodGen);
/*     */ 
/*     */     
/* 651 */     il.append(methodGen.loadHandler());
/* 652 */     il.append(methodGen.endDocument());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 657 */     if (stylesheet.callsNodeset() && !DOM_CLASS.equals("org/apache/xalan/xsltc/DOM")) {
/*     */ 
/*     */       
/* 660 */       index = cpg.addMethodref("org/apache/xalan/xsltc/dom/DOMAdapter", "<init>", "(Lorg/apache/xalan/xsltc/DOM;[Ljava/lang/String;[Ljava/lang/String;[I[Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 667 */       il.append((Instruction)new NEW(cpg.addClass("org/apache/xalan/xsltc/dom/DOMAdapter")));
/* 668 */       il.append((Instruction)new DUP_X1());
/* 669 */       il.append((Instruction)InstructionConstants.SWAP);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 675 */       if (!stylesheet.callsNodeset()) {
/* 676 */         il.append((Instruction)new ICONST(0));
/* 677 */         il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/* 678 */         il.append((Instruction)InstructionConstants.DUP);
/* 679 */         il.append((Instruction)InstructionConstants.DUP);
/* 680 */         il.append((Instruction)new ICONST(0));
/* 681 */         il.append((Instruction)new NEWARRAY(Type.INT));
/* 682 */         il.append((Instruction)InstructionConstants.SWAP);
/* 683 */         il.append((Instruction)new INVOKESPECIAL(index));
/*     */       }
/*     */       else {
/*     */         
/* 687 */         il.append((Instruction)InstructionConstants.ALOAD_0);
/* 688 */         il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namesArray", "[Ljava/lang/String;")));
/*     */ 
/*     */         
/* 691 */         il.append((Instruction)InstructionConstants.ALOAD_0);
/* 692 */         il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "urisArray", "[Ljava/lang/String;")));
/*     */ 
/*     */         
/* 695 */         il.append((Instruction)InstructionConstants.ALOAD_0);
/* 696 */         il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "typesArray", "[I")));
/*     */ 
/*     */         
/* 699 */         il.append((Instruction)InstructionConstants.ALOAD_0);
/* 700 */         il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namespaceArray", "[Ljava/lang/String;")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 705 */         il.append((Instruction)new INVOKESPECIAL(index));
/*     */ 
/*     */         
/* 708 */         il.append((Instruction)InstructionConstants.DUP);
/* 709 */         il.append(methodGen.loadDOM());
/* 710 */         il.append((Instruction)new CHECKCAST(cpg.addClass(classGen.getDOMClass())));
/* 711 */         il.append((Instruction)InstructionConstants.SWAP);
/* 712 */         index = cpg.addMethodref("org.apache.xalan.xsltc.dom.MultiDOM", "addDOMAdapter", "(Lorg/apache/xalan/xsltc/dom/DOMAdapter;)I");
/*     */ 
/*     */         
/* 715 */         il.append((Instruction)new INVOKEVIRTUAL(index));
/* 716 */         il.append((Instruction)InstructionConstants.POP);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 721 */     il.append((Instruction)InstructionConstants.SWAP);
/* 722 */     il.append(methodGen.storeHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean contextDependent() {
/* 733 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean dependentContents() {
/* 742 */     int n = elementCount();
/* 743 */     for (int i = 0; i < n; i++) {
/* 744 */       SyntaxTreeNode item = this._contents.elementAt(i);
/* 745 */       if (item.contextDependent()) {
/* 746 */         return true;
/*     */       }
/*     */     } 
/* 749 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void addElement(SyntaxTreeNode element) {
/* 757 */     this._contents.addElement(element);
/* 758 */     element.setParent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setFirstElement(SyntaxTreeNode element) {
/* 767 */     this._contents.insertElementAt(element, 0);
/* 768 */     element.setParent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void removeElement(SyntaxTreeNode element) {
/* 776 */     this._contents.remove(element);
/* 777 */     element.setParent(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Vector getContents() {
/* 785 */     return this._contents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final boolean hasContents() {
/* 793 */     return (elementCount() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int elementCount() {
/* 801 */     return this._contents.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Enumeration elements() {
/* 809 */     return this._contents.elements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Object elementAt(int pos) {
/* 818 */     return this._contents.elementAt(pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final SyntaxTreeNode lastChild() {
/* 826 */     if (this._contents.size() == 0) return null; 
/* 827 */     return this._contents.lastElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void display(int indent) {
/* 837 */     displayContents(indent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void displayContents(int indent) {
/* 846 */     int n = elementCount();
/* 847 */     for (int i = 0; i < n; i++) {
/* 848 */       SyntaxTreeNode item = this._contents.elementAt(i);
/* 849 */       item.display(indent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void indent(int indent) {
/* 858 */     System.out.print(new String(_spaces, 0, indent));
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
/*     */   protected void reportError(SyntaxTreeNode element, Parser parser, String errorCode, String message) {
/* 871 */     ErrorMsg error = new ErrorMsg(errorCode, message, element);
/* 872 */     parser.reportError(3, error);
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
/*     */   protected void reportWarning(SyntaxTreeNode element, Parser parser, String errorCode, String message) {
/* 885 */     ErrorMsg error = new ErrorMsg(errorCode, message, element);
/* 886 */     parser.reportError(4, error);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/SyntaxTreeNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */