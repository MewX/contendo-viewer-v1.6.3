/*      */ package org.apache.xalan.xsltc.compiler;
/*      */ 
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Vector;
/*      */ import org.apache.bcel.generic.BranchHandle;
/*      */ import org.apache.bcel.generic.BranchInstruction;
/*      */ import org.apache.bcel.generic.CompoundInstruction;
/*      */ import org.apache.bcel.generic.ConstantPoolGen;
/*      */ import org.apache.bcel.generic.DUP;
/*      */ import org.apache.bcel.generic.GOTO_W;
/*      */ import org.apache.bcel.generic.IFLT;
/*      */ import org.apache.bcel.generic.ILOAD;
/*      */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*      */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*      */ import org.apache.bcel.generic.ISTORE;
/*      */ import org.apache.bcel.generic.Instruction;
/*      */ import org.apache.bcel.generic.InstructionConstants;
/*      */ import org.apache.bcel.generic.InstructionHandle;
/*      */ import org.apache.bcel.generic.InstructionList;
/*      */ import org.apache.bcel.generic.LocalVariableGen;
/*      */ import org.apache.bcel.generic.SWITCH;
/*      */ import org.apache.bcel.generic.TargetLostException;
/*      */ import org.apache.bcel.generic.Type;
/*      */ import org.apache.bcel.util.InstructionFinder;
/*      */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*      */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*      */ import org.apache.xalan.xsltc.compiler.util.NamedMethodGenerator;
/*      */ import org.apache.xalan.xsltc.compiler.util.Util;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ final class Mode
/*      */   implements Constants
/*      */ {
/*      */   private final QName _name;
/*      */   private final Stylesheet _stylesheet;
/*      */   private final String _methodName;
/*      */   private Vector _templates;
/*   85 */   private Vector _childNodeGroup = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   90 */   private TestSeq _childNodeTestSeq = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   private Vector _attribNodeGroup = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  100 */   private TestSeq _attribNodeTestSeq = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   private Vector _idxGroup = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   private TestSeq _idxTestSeq = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector[] _patternGroups;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private TestSeq[] _testSeq;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   private Hashtable _preCompiled = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   private Hashtable _neededTemplates = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  138 */   private Hashtable _namedTemplates = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  143 */   private Hashtable _templateIHs = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  148 */   private Hashtable _templateILs = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  153 */   private LocationPathPattern _rootPattern = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  159 */   private Hashtable _importLevels = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  164 */   private Hashtable _keys = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int _currentIndex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Mode(QName name, Stylesheet stylesheet, String suffix) {
/*  180 */     this._name = name;
/*  181 */     this._stylesheet = stylesheet;
/*  182 */     this._methodName = "applyTemplates" + suffix;
/*  183 */     this._templates = new Vector();
/*  184 */     this._patternGroups = new Vector[32];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String functionName() {
/*  195 */     return this._methodName;
/*      */   }
/*      */   
/*      */   public String functionName(int min, int max) {
/*  199 */     if (this._importLevels == null) {
/*  200 */       this._importLevels = new Hashtable();
/*      */     }
/*  202 */     this._importLevels.put(new Integer(max), new Integer(min));
/*  203 */     return this._methodName + '_' + max;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addInstructionList(Pattern pattern, InstructionList ilist) {
/*  212 */     this._preCompiled.put(pattern, ilist);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionList getInstructionList(Pattern pattern) {
/*  220 */     return (InstructionList)this._preCompiled.get(pattern);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getClassName() {
/*  227 */     return this._stylesheet.getClassName();
/*      */   }
/*      */   
/*      */   public Stylesheet getStylesheet() {
/*  231 */     return this._stylesheet;
/*      */   }
/*      */   
/*      */   public void addTemplate(Template template) {
/*  235 */     this._templates.addElement(template);
/*      */   }
/*      */   
/*      */   private Vector quicksort(Vector templates, int p, int r) {
/*  239 */     if (p < r) {
/*  240 */       int q = partition(templates, p, r);
/*  241 */       quicksort(templates, p, q);
/*  242 */       quicksort(templates, q + 1, r);
/*      */     } 
/*  244 */     return templates;
/*      */   }
/*      */   
/*      */   private int partition(Vector templates, int p, int r) {
/*  248 */     Template x = templates.elementAt(p);
/*  249 */     int i = p - 1;
/*  250 */     int j = r + 1;
/*      */     while (true) {
/*  252 */       if (x.compareTo(templates.elementAt(--j)) <= 0) { do {  }
/*  253 */         while (x.compareTo(templates.elementAt(++i)) < 0);
/*  254 */         if (i < j) {
/*  255 */           templates.set(j, templates.set(i, templates.elementAt(j))); continue;
/*      */         }  break; }
/*      */     
/*  258 */     }  return j;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processPatterns(Hashtable keys) {
/*  267 */     this._keys = keys;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  279 */     this._templates = quicksort(this._templates, 0, this._templates.size() - 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  292 */     Enumeration templates = this._templates.elements();
/*  293 */     while (templates.hasMoreElements()) {
/*      */       
/*  295 */       Template template = templates.nextElement();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  302 */       if (template.isNamed() && !template.disabled()) {
/*  303 */         this._namedTemplates.put(template, this);
/*      */       }
/*      */ 
/*      */       
/*  307 */       Pattern pattern = template.getPattern();
/*  308 */       if (pattern != null) {
/*  309 */         flattenAlternative(pattern, template, keys);
/*      */       }
/*      */     } 
/*  312 */     prepareTestSequences();
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
/*      */   private void flattenAlternative(Pattern pattern, Template template, Hashtable keys) {
/*  326 */     if (pattern instanceof IdKeyPattern) {
/*  327 */       IdKeyPattern idkey = (IdKeyPattern)pattern;
/*  328 */       idkey.setTemplate(template);
/*  329 */       if (this._idxGroup == null) this._idxGroup = new Vector(); 
/*  330 */       this._idxGroup.add(pattern);
/*      */     
/*      */     }
/*  333 */     else if (pattern instanceof AlternativePattern) {
/*  334 */       AlternativePattern alt = (AlternativePattern)pattern;
/*  335 */       flattenAlternative(alt.getLeft(), template, keys);
/*  336 */       flattenAlternative(alt.getRight(), template, keys);
/*      */     
/*      */     }
/*  339 */     else if (pattern instanceof LocationPathPattern) {
/*  340 */       LocationPathPattern lpp = (LocationPathPattern)pattern;
/*  341 */       lpp.setTemplate(template);
/*  342 */       addPatternToGroup(lpp);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addPatternToGroup(LocationPathPattern lpp) {
/*  352 */     if (lpp instanceof IdKeyPattern) {
/*  353 */       addPattern(-1, lpp);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  358 */       StepPattern kernel = lpp.getKernelPattern();
/*  359 */       if (kernel != null) {
/*  360 */         addPattern(kernel.getNodeType(), lpp);
/*      */       }
/*  362 */       else if (this._rootPattern == null || lpp.noSmallerThan(this._rootPattern)) {
/*      */         
/*  364 */         this._rootPattern = lpp;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addPattern(int kernelType, LocationPathPattern pattern) {
/*      */     Vector vector;
/*  374 */     int oldLength = this._patternGroups.length;
/*  375 */     if (kernelType >= oldLength) {
/*  376 */       Vector[] newGroups = new Vector[kernelType * 2];
/*  377 */       System.arraycopy(this._patternGroups, 0, newGroups, 0, oldLength);
/*  378 */       this._patternGroups = newGroups;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  384 */     if (kernelType == -1) {
/*  385 */       if (pattern.getAxis() == 2) {
/*  386 */         vector = (this._attribNodeGroup == null) ? (this._attribNodeGroup = new Vector(2)) : this._attribNodeGroup;
/*      */       }
/*      */       else {
/*      */         
/*  390 */         vector = (this._childNodeGroup == null) ? (this._childNodeGroup = new Vector(2)) : this._childNodeGroup;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  395 */       vector = (this._patternGroups[kernelType] == null) ? (this._patternGroups[kernelType] = new Vector(2)) : this._patternGroups[kernelType];
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  400 */     if (vector.size() == 0) {
/*  401 */       vector.addElement(pattern);
/*      */     } else {
/*      */       
/*  404 */       boolean inserted = false;
/*  405 */       for (int i = 0; i < vector.size(); i++) {
/*  406 */         LocationPathPattern lppToCompare = vector.elementAt(i);
/*      */ 
/*      */         
/*  409 */         if (pattern.noSmallerThan(lppToCompare)) {
/*  410 */           inserted = true;
/*  411 */           vector.insertElementAt(pattern, i);
/*      */           break;
/*      */         } 
/*      */       } 
/*  415 */       if (!inserted) {
/*  416 */         vector.addElement(pattern);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void completeTestSequences(int nodeType, Vector patterns) {
/*  426 */     if (patterns != null) {
/*  427 */       if (this._patternGroups[nodeType] == null) {
/*  428 */         this._patternGroups[nodeType] = patterns;
/*      */       } else {
/*      */         
/*  431 */         int m = patterns.size();
/*  432 */         for (int j = 0; j < m; j++) {
/*  433 */           addPattern(nodeType, patterns.elementAt(j));
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
/*      */   private void prepareTestSequences() {
/*  446 */     Vector starGroup = this._patternGroups[1];
/*  447 */     Vector atStarGroup = this._patternGroups[2];
/*      */ 
/*      */     
/*  450 */     completeTestSequences(3, this._childNodeGroup);
/*      */ 
/*      */     
/*  453 */     completeTestSequences(1, this._childNodeGroup);
/*      */ 
/*      */     
/*  456 */     completeTestSequences(7, this._childNodeGroup);
/*      */ 
/*      */     
/*  459 */     completeTestSequences(8, this._childNodeGroup);
/*      */ 
/*      */     
/*  462 */     completeTestSequences(2, this._attribNodeGroup);
/*      */     
/*  464 */     Vector names = this._stylesheet.getXSLTC().getNamesIndex();
/*  465 */     if (starGroup != null || atStarGroup != null || this._childNodeGroup != null || this._attribNodeGroup != null) {
/*      */ 
/*      */       
/*  468 */       int j = this._patternGroups.length;
/*      */ 
/*      */       
/*  471 */       for (int k = 14; k < j; k++) {
/*  472 */         if (this._patternGroups[k] != null) {
/*      */           
/*  474 */           String name = names.elementAt(k - 14);
/*      */           
/*  476 */           if (isAttributeName(name)) {
/*      */             
/*  478 */             completeTestSequences(k, atStarGroup);
/*      */ 
/*      */             
/*  481 */             completeTestSequences(k, this._attribNodeGroup);
/*      */           }
/*      */           else {
/*      */             
/*  485 */             completeTestSequences(k, starGroup);
/*      */ 
/*      */             
/*  488 */             completeTestSequences(k, this._childNodeGroup);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  493 */     this._testSeq = new TestSeq[14 + names.size()];
/*      */     
/*  495 */     int n = this._patternGroups.length;
/*  496 */     for (int i = 0; i < n; i++) {
/*  497 */       Vector patterns = this._patternGroups[i];
/*  498 */       if (patterns != null) {
/*  499 */         TestSeq testSeq = new TestSeq(patterns, i, this);
/*      */         
/*  501 */         testSeq.reduce();
/*  502 */         this._testSeq[i] = testSeq;
/*  503 */         testSeq.findTemplates(this._neededTemplates);
/*      */       } 
/*      */     } 
/*      */     
/*  507 */     if (this._childNodeGroup != null && this._childNodeGroup.size() > 0) {
/*  508 */       this._childNodeTestSeq = new TestSeq(this._childNodeGroup, -1, this);
/*  509 */       this._childNodeTestSeq.reduce();
/*  510 */       this._childNodeTestSeq.findTemplates(this._neededTemplates);
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
/*  521 */     if (this._idxGroup != null && this._idxGroup.size() > 0) {
/*  522 */       this._idxTestSeq = new TestSeq(this._idxGroup, this);
/*  523 */       this._idxTestSeq.reduce();
/*  524 */       this._idxTestSeq.findTemplates(this._neededTemplates);
/*      */     } 
/*      */     
/*  527 */     if (this._rootPattern != null)
/*      */     {
/*  529 */       this._neededTemplates.put(this._rootPattern.getTemplate(), this);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void compileNamedTemplate(Template template, ClassGenerator classGen) {
/*  535 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  536 */     InstructionList il = new InstructionList();
/*  537 */     String methodName = Util.escape(template.getName().toString());
/*      */     
/*  539 */     int numParams = 0;
/*  540 */     if (template.isSimpleNamedTemplate()) {
/*  541 */       Vector parameters = template.getParameters();
/*  542 */       numParams = parameters.size();
/*      */     } 
/*      */ 
/*      */     
/*  546 */     Type[] types = new Type[4 + numParams];
/*      */     
/*  548 */     String[] names = new String[4 + numParams];
/*  549 */     types[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
/*  550 */     types[1] = Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;");
/*  551 */     types[2] = Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;");
/*  552 */     types[3] = (Type)Type.INT;
/*  553 */     names[0] = "document";
/*  554 */     names[1] = "iterator";
/*  555 */     names[2] = "handler";
/*  556 */     names[3] = "node";
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  561 */     for (int i = 4; i < 4 + numParams; i++) {
/*  562 */       types[i] = Util.getJCRefType("Ljava/lang/Object;");
/*  563 */       names[i] = "param" + String.valueOf(i - 4);
/*      */     } 
/*      */     
/*  566 */     NamedMethodGenerator methodGen = new NamedMethodGenerator(1, (Type)Type.VOID, types, names, methodName, getClassName(), il, cpg);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  572 */     il.append(template.compile(classGen, (MethodGenerator)methodGen));
/*  573 */     il.append((Instruction)InstructionConstants.RETURN);
/*      */     
/*  575 */     methodGen.stripAttributes(true);
/*  576 */     methodGen.setMaxLocals();
/*  577 */     methodGen.setMaxStack();
/*  578 */     methodGen.removeNOPs();
/*  579 */     classGen.addMethod(methodGen.getMethod());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void compileTemplates(ClassGenerator classGen, MethodGenerator methodGen, InstructionHandle next) {
/*  586 */     Enumeration templates = this._namedTemplates.keys();
/*  587 */     while (templates.hasMoreElements()) {
/*  588 */       Template template = templates.nextElement();
/*  589 */       compileNamedTemplate(template, classGen);
/*      */     } 
/*      */     
/*  592 */     templates = this._neededTemplates.keys();
/*  593 */     while (templates.hasMoreElements()) {
/*  594 */       Template template = templates.nextElement();
/*  595 */       if (template.hasContents()) {
/*      */         
/*  597 */         InstructionList til = template.compile(classGen, methodGen);
/*  598 */         til.append((BranchInstruction)new GOTO_W(next));
/*  599 */         this._templateILs.put(template, til);
/*  600 */         this._templateIHs.put(template, til.getStart());
/*      */         
/*      */         continue;
/*      */       } 
/*  604 */       this._templateIHs.put(template, next);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void appendTemplateCode(InstructionList body) {
/*  610 */     Enumeration templates = this._neededTemplates.keys();
/*  611 */     while (templates.hasMoreElements()) {
/*  612 */       Object iList = this._templateILs.get(templates.nextElement());
/*      */       
/*  614 */       if (iList != null) {
/*  615 */         body.append((InstructionList)iList);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void appendTestSequences(InstructionList body) {
/*  621 */     int n = this._testSeq.length;
/*  622 */     for (int i = 0; i < n; i++) {
/*  623 */       TestSeq testSeq = this._testSeq[i];
/*  624 */       if (testSeq != null) {
/*  625 */         InstructionList il = testSeq.getInstructionList();
/*  626 */         if (il != null) {
/*  627 */           body.append(il);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void compileGetChildren(ClassGenerator classGen, MethodGenerator methodGen, int node) {
/*  636 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  637 */     InstructionList il = methodGen.getInstructionList();
/*  638 */     int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getChildren", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*      */ 
/*      */     
/*  641 */     il.append(methodGen.loadDOM());
/*  642 */     il.append((Instruction)new ILOAD(node));
/*  643 */     il.append((Instruction)new INVOKEINTERFACE(git, 2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InstructionList compileDefaultRecursion(ClassGenerator classGen, MethodGenerator methodGen, InstructionHandle next) {
/*  652 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  653 */     InstructionList il = new InstructionList();
/*  654 */     String applyTemplatesSig = classGen.getApplyTemplatesSig();
/*  655 */     int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getChildren", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*      */ 
/*      */     
/*  658 */     int applyTemplates = cpg.addMethodref(getClassName(), functionName(), applyTemplatesSig);
/*      */ 
/*      */     
/*  661 */     il.append(classGen.loadTranslet());
/*  662 */     il.append(methodGen.loadDOM());
/*      */     
/*  664 */     il.append(methodGen.loadDOM());
/*  665 */     il.append((Instruction)new ILOAD(this._currentIndex));
/*  666 */     il.append((Instruction)new INVOKEINTERFACE(git, 2));
/*  667 */     il.append(methodGen.loadHandler());
/*  668 */     il.append((Instruction)new INVOKEVIRTUAL(applyTemplates));
/*  669 */     il.append((BranchInstruction)new GOTO_W(next));
/*  670 */     return il;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InstructionList compileDefaultText(ClassGenerator classGen, MethodGenerator methodGen, InstructionHandle next) {
/*  680 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  681 */     InstructionList il = new InstructionList();
/*      */     
/*  683 */     int chars = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "characters", "(ILorg/apache/xml/serializer/SerializationHandler;)V");
/*      */ 
/*      */     
/*  686 */     il.append(methodGen.loadDOM());
/*  687 */     il.append((Instruction)new ILOAD(this._currentIndex));
/*  688 */     il.append(methodGen.loadHandler());
/*  689 */     il.append((Instruction)new INVOKEINTERFACE(chars, 3));
/*  690 */     il.append((BranchInstruction)new GOTO_W(next));
/*  691 */     return il;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InstructionList compileNamespaces(ClassGenerator classGen, MethodGenerator methodGen, boolean[] isNamespace, boolean[] isAttribute, boolean attrFlag, InstructionHandle defaultTarget) {
/*  700 */     XSLTC xsltc = classGen.getParser().getXSLTC();
/*  701 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*      */ 
/*      */     
/*  704 */     Vector namespaces = xsltc.getNamespaceIndex();
/*  705 */     Vector names = xsltc.getNamesIndex();
/*  706 */     int namespaceCount = namespaces.size() + 1;
/*  707 */     int namesCount = names.size();
/*      */     
/*  709 */     InstructionList il = new InstructionList();
/*  710 */     int[] types = new int[namespaceCount];
/*  711 */     InstructionHandle[] targets = new InstructionHandle[types.length];
/*      */     
/*  713 */     if (namespaceCount > 0) {
/*  714 */       boolean compiled = false;
/*      */ 
/*      */       
/*  717 */       for (int i = 0; i < namespaceCount; i++) {
/*  718 */         targets[i] = defaultTarget;
/*  719 */         types[i] = i;
/*      */       } 
/*      */ 
/*      */       
/*  723 */       for (int j = 14; j < 14 + namesCount; j++) {
/*  724 */         if (isNamespace[j] && isAttribute[j] == attrFlag) {
/*  725 */           String name = names.elementAt(j - 14);
/*  726 */           String namespace = name.substring(0, name.lastIndexOf(':'));
/*  727 */           int type = xsltc.registerNamespace(namespace);
/*      */           
/*  729 */           if (j < this._testSeq.length && this._testSeq[j] != null) {
/*      */             
/*  731 */             targets[type] = this._testSeq[j].compile(classGen, methodGen, defaultTarget);
/*      */ 
/*      */ 
/*      */             
/*  735 */             compiled = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  741 */       if (!compiled) return null;
/*      */ 
/*      */       
/*  744 */       int getNS = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceType", "(I)I");
/*      */ 
/*      */       
/*  747 */       il.append(methodGen.loadDOM());
/*  748 */       il.append((Instruction)new ILOAD(this._currentIndex));
/*  749 */       il.append((Instruction)new INVOKEINTERFACE(getNS, 2));
/*  750 */       il.append((CompoundInstruction)new SWITCH(types, targets, defaultTarget));
/*  751 */       return il;
/*      */     } 
/*      */     
/*  754 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void compileApplyTemplates(ClassGenerator classGen) {
/*  763 */     XSLTC xsltc = classGen.getParser().getXSLTC();
/*  764 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  765 */     Vector names = xsltc.getNamesIndex();
/*      */ 
/*      */     
/*  768 */     Type[] argTypes = new Type[3];
/*      */     
/*  770 */     argTypes[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
/*  771 */     argTypes[1] = Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;");
/*  772 */     argTypes[2] = Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;");
/*      */     
/*  774 */     String[] argNames = new String[3];
/*  775 */     argNames[0] = "document";
/*  776 */     argNames[1] = "iterator";
/*  777 */     argNames[2] = "handler";
/*      */     
/*  779 */     InstructionList mainIL = new InstructionList();
/*  780 */     MethodGenerator methodGen = new MethodGenerator(17, (Type)Type.VOID, argTypes, argNames, functionName(), getClassName(), mainIL, classGen.getConstantPool());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  786 */     methodGen.addException("org.apache.xalan.xsltc.TransletException");
/*      */ 
/*      */ 
/*      */     
/*  790 */     LocalVariableGen current = methodGen.addLocalVariable2("current", (Type)Type.INT, mainIL.getEnd());
/*      */ 
/*      */     
/*  793 */     this._currentIndex = current.getIndex();
/*      */ 
/*      */ 
/*      */     
/*  797 */     InstructionList body = new InstructionList();
/*  798 */     body.append(InstructionConstants.NOP);
/*      */ 
/*      */ 
/*      */     
/*  802 */     InstructionList ilLoop = new InstructionList();
/*  803 */     ilLoop.append(methodGen.loadIterator());
/*  804 */     ilLoop.append(methodGen.nextNode());
/*  805 */     ilLoop.append((Instruction)InstructionConstants.DUP);
/*  806 */     ilLoop.append((Instruction)new ISTORE(this._currentIndex));
/*      */ 
/*      */ 
/*      */     
/*  810 */     BranchHandle ifeq = ilLoop.append((BranchInstruction)new IFLT(null));
/*  811 */     BranchHandle loop = ilLoop.append((BranchInstruction)new GOTO_W(null));
/*  812 */     ifeq.setTarget(ilLoop.append((Instruction)InstructionConstants.RETURN));
/*  813 */     InstructionHandle ihLoop = ilLoop.getStart();
/*      */ 
/*      */     
/*  816 */     InstructionList ilRecurse = compileDefaultRecursion(classGen, methodGen, ihLoop);
/*      */     
/*  818 */     InstructionHandle ihRecurse = ilRecurse.getStart();
/*      */ 
/*      */     
/*  821 */     InstructionList ilText = compileDefaultText(classGen, methodGen, ihLoop);
/*      */     
/*  823 */     InstructionHandle ihText = ilText.getStart();
/*      */ 
/*      */     
/*  826 */     int[] types = new int[14 + names.size()];
/*  827 */     for (int i = 0; i < types.length; i++) {
/*  828 */       types[i] = i;
/*      */     }
/*      */ 
/*      */     
/*  832 */     boolean[] isAttribute = new boolean[types.length];
/*  833 */     boolean[] isNamespace = new boolean[types.length];
/*  834 */     for (int j = 0; j < names.size(); j++) {
/*  835 */       String name = names.elementAt(j);
/*  836 */       isAttribute[j + 14] = isAttributeName(name);
/*  837 */       isNamespace[j + 14] = isNamespaceName(name);
/*      */     } 
/*      */ 
/*      */     
/*  841 */     compileTemplates(classGen, methodGen, ihLoop);
/*      */ 
/*      */     
/*  844 */     TestSeq elemTest = this._testSeq[1];
/*  845 */     InstructionHandle ihElem = ihRecurse;
/*  846 */     if (elemTest != null) {
/*  847 */       ihElem = elemTest.compile(classGen, methodGen, ihRecurse);
/*      */     }
/*      */     
/*  850 */     TestSeq attrTest = this._testSeq[2];
/*  851 */     InstructionHandle ihAttr = ihText;
/*  852 */     if (attrTest != null) {
/*  853 */       ihAttr = attrTest.compile(classGen, methodGen, ihAttr);
/*      */     }
/*      */     
/*  856 */     InstructionList ilKey = null;
/*  857 */     if (this._idxTestSeq != null) {
/*  858 */       loop.setTarget(this._idxTestSeq.compile(classGen, methodGen, body.getStart()));
/*  859 */       ilKey = this._idxTestSeq.getInstructionList();
/*      */     } else {
/*      */       
/*  862 */       loop.setTarget(body.getStart());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  867 */     if (this._childNodeTestSeq != null) {
/*      */       
/*  869 */       double nodePrio = this._childNodeTestSeq.getPriority();
/*  870 */       int nodePos = this._childNodeTestSeq.getPosition();
/*  871 */       double elemPrio = -1.7976931348623157E308D;
/*  872 */       int elemPos = Integer.MIN_VALUE;
/*      */       
/*  874 */       if (elemTest != null) {
/*  875 */         elemPrio = elemTest.getPriority();
/*  876 */         elemPos = elemTest.getPosition();
/*      */       } 
/*  878 */       if (elemPrio == Double.NaN || elemPrio < nodePrio || (elemPrio == nodePrio && elemPos < nodePos))
/*      */       {
/*      */         
/*  881 */         ihElem = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
/*      */       }
/*      */ 
/*      */       
/*  885 */       TestSeq textTest = this._testSeq[3];
/*  886 */       double textPrio = -1.7976931348623157E308D;
/*  887 */       int textPos = Integer.MIN_VALUE;
/*      */       
/*  889 */       if (textTest != null) {
/*  890 */         textPrio = textTest.getPriority();
/*  891 */         textPos = textTest.getPosition();
/*      */       } 
/*  893 */       if (textPrio == Double.NaN || textPrio < nodePrio || (textPrio == nodePrio && textPos < nodePos)) {
/*      */ 
/*      */         
/*  896 */         ihText = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
/*  897 */         this._testSeq[3] = this._childNodeTestSeq;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  902 */     InstructionHandle elemNamespaceHandle = ihElem;
/*  903 */     InstructionList nsElem = compileNamespaces(classGen, methodGen, isNamespace, isAttribute, false, ihElem);
/*      */ 
/*      */     
/*  906 */     if (nsElem != null) elemNamespaceHandle = nsElem.getStart();
/*      */ 
/*      */     
/*  909 */     InstructionHandle attrNamespaceHandle = ihAttr;
/*  910 */     InstructionList nsAttr = compileNamespaces(classGen, methodGen, isNamespace, isAttribute, true, ihAttr);
/*      */ 
/*      */     
/*  913 */     if (nsAttr != null) attrNamespaceHandle = nsAttr.getStart();
/*      */ 
/*      */     
/*  916 */     InstructionHandle[] targets = new InstructionHandle[types.length];
/*  917 */     for (int k = 14; k < targets.length; k++) {
/*  918 */       TestSeq testSeq = this._testSeq[k];
/*      */       
/*  920 */       if (isNamespace[k]) {
/*  921 */         if (isAttribute[k]) {
/*  922 */           targets[k] = attrNamespaceHandle;
/*      */         } else {
/*  924 */           targets[k] = elemNamespaceHandle;
/*      */         }
/*      */       
/*  927 */       } else if (testSeq != null) {
/*  928 */         if (isAttribute[k]) {
/*  929 */           targets[k] = testSeq.compile(classGen, methodGen, attrNamespaceHandle);
/*      */         } else {
/*      */           
/*  932 */           targets[k] = testSeq.compile(classGen, methodGen, elemNamespaceHandle);
/*      */         } 
/*      */       } else {
/*      */         
/*  936 */         targets[k] = ihLoop;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  942 */     targets[0] = (this._rootPattern != null) ? getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  947 */     targets[9] = (this._rootPattern != null) ? getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  952 */     targets[3] = (this._testSeq[3] != null) ? this._testSeq[3].compile(classGen, methodGen, ihText) : ihText;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  957 */     targets[13] = ihLoop;
/*      */ 
/*      */     
/*  960 */     targets[1] = elemNamespaceHandle;
/*      */ 
/*      */     
/*  963 */     targets[2] = attrNamespaceHandle;
/*      */ 
/*      */     
/*  966 */     InstructionHandle ihPI = ihLoop;
/*  967 */     if (this._childNodeTestSeq != null) ihPI = ihElem; 
/*  968 */     if (this._testSeq[7] != null) {
/*  969 */       targets[7] = this._testSeq[7].compile(classGen, methodGen, ihPI);
/*      */     }
/*      */     else {
/*      */       
/*  973 */       targets[7] = ihPI;
/*      */     } 
/*      */     
/*  976 */     InstructionHandle ihComment = ihLoop;
/*  977 */     if (this._childNodeTestSeq != null) ihComment = ihElem; 
/*  978 */     targets[8] = (this._testSeq[8] != null) ? this._testSeq[8].compile(classGen, methodGen, ihComment) : ihComment;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  983 */     targets[4] = ihLoop;
/*      */ 
/*      */     
/*  986 */     targets[11] = ihLoop;
/*      */ 
/*      */     
/*  989 */     targets[10] = ihLoop;
/*      */ 
/*      */     
/*  992 */     targets[6] = ihLoop;
/*      */ 
/*      */     
/*  995 */     targets[5] = ihLoop;
/*      */ 
/*      */     
/*  998 */     targets[12] = ihLoop;
/*      */ 
/*      */ 
/*      */     
/* 1002 */     for (int m = 14; m < targets.length; m++) {
/* 1003 */       TestSeq testSeq = this._testSeq[m];
/*      */       
/* 1005 */       if (testSeq == null || isNamespace[m]) {
/* 1006 */         if (isAttribute[m]) {
/* 1007 */           targets[m] = attrNamespaceHandle;
/*      */         } else {
/* 1009 */           targets[m] = elemNamespaceHandle;
/*      */         }
/*      */       
/*      */       }
/* 1013 */       else if (isAttribute[m]) {
/* 1014 */         targets[m] = testSeq.compile(classGen, methodGen, attrNamespaceHandle);
/*      */       } else {
/*      */         
/* 1017 */         targets[m] = testSeq.compile(classGen, methodGen, elemNamespaceHandle);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1022 */     if (ilKey != null) body.insert(ilKey);
/*      */ 
/*      */     
/* 1025 */     int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
/*      */ 
/*      */     
/* 1028 */     body.append(methodGen.loadDOM());
/* 1029 */     body.append((Instruction)new ILOAD(this._currentIndex));
/* 1030 */     body.append((Instruction)new INVOKEINTERFACE(getType, 2));
/*      */ 
/*      */     
/* 1033 */     InstructionHandle disp = body.append((CompoundInstruction)new SWITCH(types, targets, ihLoop));
/*      */ 
/*      */     
/* 1036 */     appendTestSequences(body);
/*      */     
/* 1038 */     appendTemplateCode(body);
/*      */ 
/*      */     
/* 1041 */     if (nsElem != null) body.append(nsElem);
/*      */     
/* 1043 */     if (nsAttr != null) body.append(nsAttr);
/*      */ 
/*      */     
/* 1046 */     body.append(ilRecurse);
/*      */     
/* 1048 */     body.append(ilText);
/*      */ 
/*      */     
/* 1051 */     mainIL.append((BranchInstruction)new GOTO_W(ihLoop));
/* 1052 */     mainIL.append(body);
/*      */     
/* 1054 */     mainIL.append(ilLoop);
/*      */     
/* 1056 */     peepHoleOptimization(methodGen);
/* 1057 */     methodGen.stripAttributes(true);
/*      */     
/* 1059 */     methodGen.setMaxLocals();
/* 1060 */     methodGen.setMaxStack();
/* 1061 */     methodGen.removeNOPs();
/* 1062 */     classGen.addMethod(methodGen.getMethod());
/*      */ 
/*      */     
/* 1065 */     if (this._importLevels != null) {
/* 1066 */       Enumeration levels = this._importLevels.keys();
/* 1067 */       while (levels.hasMoreElements()) {
/* 1068 */         Integer max = levels.nextElement();
/* 1069 */         Integer min = (Integer)this._importLevels.get(max);
/* 1070 */         compileApplyImports(classGen, min.intValue(), max.intValue());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void compileTemplateCalls(ClassGenerator classGen, MethodGenerator methodGen, InstructionHandle next, int min, int max) {
/* 1078 */     Enumeration templates = this._neededTemplates.keys();
/* 1079 */     while (templates.hasMoreElements()) {
/* 1080 */       Template template = templates.nextElement();
/* 1081 */       int prec = template.getImportPrecedence();
/* 1082 */       if (prec >= min && prec < max) {
/* 1083 */         if (template.hasContents()) {
/* 1084 */           InstructionList til = template.compile(classGen, methodGen);
/* 1085 */           til.append((BranchInstruction)new GOTO_W(next));
/* 1086 */           this._templateILs.put(template, til);
/* 1087 */           this._templateIHs.put(template, til.getStart());
/*      */           
/*      */           continue;
/*      */         } 
/* 1091 */         this._templateIHs.put(template, next);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void compileApplyImports(ClassGenerator classGen, int min, int max) {
/* 1099 */     XSLTC xsltc = classGen.getParser().getXSLTC();
/* 1100 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 1101 */     Vector names = xsltc.getNamesIndex();
/*      */ 
/*      */     
/* 1104 */     this._namedTemplates = new Hashtable();
/* 1105 */     this._neededTemplates = new Hashtable();
/* 1106 */     this._templateIHs = new Hashtable();
/* 1107 */     this._templateILs = new Hashtable();
/* 1108 */     this._patternGroups = new Vector[32];
/* 1109 */     this._rootPattern = null;
/*      */ 
/*      */     
/* 1112 */     Vector oldTemplates = this._templates;
/*      */ 
/*      */     
/* 1115 */     this._templates = new Vector();
/* 1116 */     Enumeration templates = oldTemplates.elements();
/* 1117 */     while (templates.hasMoreElements()) {
/* 1118 */       Template template = templates.nextElement();
/* 1119 */       int prec = template.getImportPrecedence();
/* 1120 */       if (prec >= min && prec < max) addTemplate(template);
/*      */     
/*      */     } 
/*      */     
/* 1124 */     processPatterns(this._keys);
/*      */ 
/*      */     
/* 1127 */     Type[] argTypes = new Type[3];
/*      */     
/* 1129 */     argTypes[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
/* 1130 */     argTypes[1] = Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;");
/* 1131 */     argTypes[2] = Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;");
/*      */     
/* 1133 */     String[] argNames = new String[3];
/* 1134 */     argNames[0] = "document";
/* 1135 */     argNames[1] = "iterator";
/* 1136 */     argNames[2] = "handler";
/*      */     
/* 1138 */     InstructionList mainIL = new InstructionList();
/* 1139 */     MethodGenerator methodGen = new MethodGenerator(17, (Type)Type.VOID, argTypes, argNames, functionName() + '_' + max, getClassName(), mainIL, classGen.getConstantPool());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1145 */     methodGen.addException("org.apache.xalan.xsltc.TransletException");
/*      */ 
/*      */ 
/*      */     
/* 1149 */     LocalVariableGen current = methodGen.addLocalVariable2("current", (Type)Type.INT, mainIL.getEnd());
/*      */ 
/*      */     
/* 1152 */     this._currentIndex = current.getIndex();
/*      */ 
/*      */ 
/*      */     
/* 1156 */     InstructionList body = new InstructionList();
/* 1157 */     body.append(InstructionConstants.NOP);
/*      */ 
/*      */ 
/*      */     
/* 1161 */     InstructionList ilLoop = new InstructionList();
/* 1162 */     ilLoop.append(methodGen.loadIterator());
/* 1163 */     ilLoop.append(methodGen.nextNode());
/* 1164 */     ilLoop.append((Instruction)InstructionConstants.DUP);
/* 1165 */     ilLoop.append((Instruction)new ISTORE(this._currentIndex));
/*      */ 
/*      */ 
/*      */     
/* 1169 */     BranchHandle ifeq = ilLoop.append((BranchInstruction)new IFLT(null));
/* 1170 */     BranchHandle loop = ilLoop.append((BranchInstruction)new GOTO_W(null));
/* 1171 */     ifeq.setTarget(ilLoop.append((Instruction)InstructionConstants.RETURN));
/* 1172 */     InstructionHandle ihLoop = ilLoop.getStart();
/*      */ 
/*      */     
/* 1175 */     InstructionList ilRecurse = compileDefaultRecursion(classGen, methodGen, ihLoop);
/*      */     
/* 1177 */     InstructionHandle ihRecurse = ilRecurse.getStart();
/*      */ 
/*      */     
/* 1180 */     InstructionList ilText = compileDefaultText(classGen, methodGen, ihLoop);
/*      */     
/* 1182 */     InstructionHandle ihText = ilText.getStart();
/*      */ 
/*      */     
/* 1185 */     int[] types = new int[14 + names.size()];
/* 1186 */     for (int i = 0; i < types.length; i++) {
/* 1187 */       types[i] = i;
/*      */     }
/*      */     
/* 1190 */     boolean[] isAttribute = new boolean[types.length];
/* 1191 */     boolean[] isNamespace = new boolean[types.length];
/* 1192 */     for (int j = 0; j < names.size(); j++) {
/* 1193 */       String name = names.elementAt(j);
/* 1194 */       isAttribute[j + 14] = isAttributeName(name);
/* 1195 */       isNamespace[j + 14] = isNamespaceName(name);
/*      */     } 
/*      */ 
/*      */     
/* 1199 */     compileTemplateCalls(classGen, methodGen, ihLoop, min, max);
/*      */ 
/*      */     
/* 1202 */     TestSeq elemTest = this._testSeq[1];
/* 1203 */     InstructionHandle ihElem = ihRecurse;
/* 1204 */     if (elemTest != null) {
/* 1205 */       ihElem = elemTest.compile(classGen, methodGen, ihLoop);
/*      */     }
/*      */ 
/*      */     
/* 1209 */     TestSeq attrTest = this._testSeq[2];
/* 1210 */     InstructionHandle ihAttr = ihLoop;
/* 1211 */     if (attrTest != null) {
/* 1212 */       ihAttr = attrTest.compile(classGen, methodGen, ihAttr);
/*      */     }
/*      */ 
/*      */     
/* 1216 */     InstructionList ilKey = null;
/* 1217 */     if (this._idxTestSeq != null) {
/* 1218 */       loop.setTarget(this._idxTestSeq.compile(classGen, methodGen, body.getStart()));
/* 1219 */       ilKey = this._idxTestSeq.getInstructionList();
/*      */     } else {
/*      */       
/* 1222 */       loop.setTarget(body.getStart());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1227 */     if (this._childNodeTestSeq != null) {
/*      */       
/* 1229 */       double nodePrio = this._childNodeTestSeq.getPriority();
/* 1230 */       int nodePos = this._childNodeTestSeq.getPosition();
/* 1231 */       double elemPrio = -1.7976931348623157E308D;
/* 1232 */       int elemPos = Integer.MIN_VALUE;
/*      */       
/* 1234 */       if (elemTest != null) {
/* 1235 */         elemPrio = elemTest.getPriority();
/* 1236 */         elemPos = elemTest.getPosition();
/*      */       } 
/*      */       
/* 1239 */       if (elemPrio == Double.NaN || elemPrio < nodePrio || (elemPrio == nodePrio && elemPos < nodePos))
/*      */       {
/*      */         
/* 1242 */         ihElem = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
/*      */       }
/*      */ 
/*      */       
/* 1246 */       TestSeq textTest = this._testSeq[3];
/* 1247 */       double textPrio = -1.7976931348623157E308D;
/* 1248 */       int textPos = Integer.MIN_VALUE;
/*      */       
/* 1250 */       if (textTest != null) {
/* 1251 */         textPrio = textTest.getPriority();
/* 1252 */         textPos = textTest.getPosition();
/*      */       } 
/*      */       
/* 1255 */       if (textPrio == Double.NaN || textPrio < nodePrio || (textPrio == nodePrio && textPos < nodePos)) {
/*      */ 
/*      */         
/* 1258 */         ihText = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
/* 1259 */         this._testSeq[3] = this._childNodeTestSeq;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1264 */     InstructionHandle elemNamespaceHandle = ihElem;
/* 1265 */     InstructionList nsElem = compileNamespaces(classGen, methodGen, isNamespace, isAttribute, false, ihElem);
/*      */ 
/*      */     
/* 1268 */     if (nsElem != null) elemNamespaceHandle = nsElem.getStart();
/*      */ 
/*      */     
/* 1271 */     InstructionList nsAttr = compileNamespaces(classGen, methodGen, isNamespace, isAttribute, true, ihAttr);
/*      */ 
/*      */     
/* 1274 */     InstructionHandle attrNamespaceHandle = ihAttr;
/* 1275 */     if (nsAttr != null) attrNamespaceHandle = nsAttr.getStart();
/*      */ 
/*      */     
/* 1278 */     InstructionHandle[] targets = new InstructionHandle[types.length];
/* 1279 */     for (int k = 14; k < targets.length; k++) {
/* 1280 */       TestSeq testSeq = this._testSeq[k];
/*      */       
/* 1282 */       if (isNamespace[k]) {
/* 1283 */         if (isAttribute[k]) {
/* 1284 */           targets[k] = attrNamespaceHandle;
/*      */         } else {
/* 1286 */           targets[k] = elemNamespaceHandle;
/*      */         }
/*      */       
/* 1289 */       } else if (testSeq != null) {
/* 1290 */         if (isAttribute[k]) {
/* 1291 */           targets[k] = testSeq.compile(classGen, methodGen, attrNamespaceHandle);
/*      */         } else {
/*      */           
/* 1294 */           targets[k] = testSeq.compile(classGen, methodGen, elemNamespaceHandle);
/*      */         } 
/*      */       } else {
/*      */         
/* 1298 */         targets[k] = ihLoop;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1303 */     targets[0] = (this._rootPattern != null) ? getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse;
/*      */ 
/*      */ 
/*      */     
/* 1307 */     targets[9] = (this._rootPattern != null) ? getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1312 */     targets[3] = (this._testSeq[3] != null) ? this._testSeq[3].compile(classGen, methodGen, ihText) : ihText;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1317 */     targets[13] = ihLoop;
/*      */ 
/*      */     
/* 1320 */     targets[1] = elemNamespaceHandle;
/*      */ 
/*      */     
/* 1323 */     targets[2] = attrNamespaceHandle;
/*      */ 
/*      */     
/* 1326 */     InstructionHandle ihPI = ihLoop;
/* 1327 */     if (this._childNodeTestSeq != null) ihPI = ihElem; 
/* 1328 */     if (this._testSeq[7] != null) {
/* 1329 */       targets[7] = this._testSeq[7].compile(classGen, methodGen, ihPI);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1334 */       targets[7] = ihPI;
/*      */     } 
/*      */ 
/*      */     
/* 1338 */     InstructionHandle ihComment = ihLoop;
/* 1339 */     if (this._childNodeTestSeq != null) ihComment = ihElem; 
/* 1340 */     targets[8] = (this._testSeq[8] != null) ? this._testSeq[8].compile(classGen, methodGen, ihComment) : ihComment;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1345 */     targets[4] = ihLoop;
/*      */ 
/*      */     
/* 1348 */     targets[11] = ihLoop;
/*      */ 
/*      */     
/* 1351 */     targets[10] = ihLoop;
/*      */ 
/*      */     
/* 1354 */     targets[6] = ihLoop;
/*      */ 
/*      */     
/* 1357 */     targets[5] = ihLoop;
/*      */ 
/*      */     
/* 1360 */     targets[12] = ihLoop;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1365 */     for (int m = 14; m < targets.length; m++) {
/* 1366 */       TestSeq testSeq = this._testSeq[m];
/*      */       
/* 1368 */       if (testSeq == null || isNamespace[m]) {
/* 1369 */         if (isAttribute[m]) {
/* 1370 */           targets[m] = attrNamespaceHandle;
/*      */         } else {
/* 1372 */           targets[m] = elemNamespaceHandle;
/*      */         }
/*      */       
/*      */       }
/* 1376 */       else if (isAttribute[m]) {
/* 1377 */         targets[m] = testSeq.compile(classGen, methodGen, attrNamespaceHandle);
/*      */       } else {
/*      */         
/* 1380 */         targets[m] = testSeq.compile(classGen, methodGen, elemNamespaceHandle);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1385 */     if (ilKey != null) body.insert(ilKey);
/*      */ 
/*      */     
/* 1388 */     int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
/*      */ 
/*      */     
/* 1391 */     body.append(methodGen.loadDOM());
/* 1392 */     body.append((Instruction)new ILOAD(this._currentIndex));
/* 1393 */     body.append((Instruction)new INVOKEINTERFACE(getType, 2));
/*      */ 
/*      */     
/* 1396 */     InstructionHandle disp = body.append((CompoundInstruction)new SWITCH(types, targets, ihLoop));
/*      */ 
/*      */     
/* 1399 */     appendTestSequences(body);
/*      */     
/* 1401 */     appendTemplateCode(body);
/*      */ 
/*      */     
/* 1404 */     if (nsElem != null) body.append(nsElem);
/*      */     
/* 1406 */     if (nsAttr != null) body.append(nsAttr);
/*      */ 
/*      */     
/* 1409 */     body.append(ilRecurse);
/*      */     
/* 1411 */     body.append(ilText);
/*      */ 
/*      */     
/* 1414 */     mainIL.append((BranchInstruction)new GOTO_W(ihLoop));
/* 1415 */     mainIL.append(body);
/*      */     
/* 1417 */     mainIL.append(ilLoop);
/*      */     
/* 1419 */     peepHoleOptimization(methodGen);
/* 1420 */     methodGen.stripAttributes(true);
/*      */     
/* 1422 */     methodGen.setMaxLocals();
/* 1423 */     methodGen.setMaxStack();
/* 1424 */     methodGen.removeNOPs();
/* 1425 */     classGen.addMethod(methodGen.getMethod());
/*      */ 
/*      */     
/* 1428 */     this._templates = oldTemplates;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void peepHoleOptimization(MethodGenerator methodGen) {
/* 1435 */     InstructionList il = methodGen.getInstructionList();
/* 1436 */     InstructionFinder find = new InstructionFinder(il);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1441 */     String pattern = "`ALOAD'`POP'`Instruction'";
/* 1442 */     for (Iterator iter = find.search(pattern); iter.hasNext(); ) {
/* 1443 */       InstructionHandle[] match = iter.next();
/*      */       
/* 1445 */       try { if (!match[0].hasTargeters() && !match[1].hasTargeters())
/* 1446 */           il.delete(match[0], match[1]);  } catch (TargetLostException targetLostException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1454 */     pattern = "`ILOAD'`ALOAD'`SWAP'`Instruction'";
/* 1455 */     for (Iterator iterator1 = find.search(pattern); iterator1.hasNext(); ) {
/* 1456 */       InstructionHandle[] match = iterator1.next();
/*      */ 
/*      */ 
/*      */       
/* 1460 */       try { if (!match[0].hasTargeters() && !match[1].hasTargeters() && !match[2].hasTargeters())
/*      */         
/*      */         { 
/* 1463 */           Instruction iload = match[0].getInstruction();
/* 1464 */           Instruction aload = match[1].getInstruction();
/* 1465 */           il.insert(match[0], aload);
/* 1466 */           il.insert(match[0], iload);
/* 1467 */           il.delete(match[0], match[2]); }  } catch (TargetLostException targetLostException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1476 */     pattern = "`ALOAD_1'`ALOAD_1'`Instruction'";
/* 1477 */     for (Iterator iterator2 = find.search(pattern); iterator2.hasNext(); ) {
/* 1478 */       InstructionHandle[] match = iterator2.next();
/*      */ 
/*      */ 
/*      */       
/* 1482 */       try { if (!match[0].hasTargeters() && !match[1].hasTargeters())
/* 1483 */         { il.insert(match[1], (Instruction)new DUP());
/* 1484 */           il.delete(match[1]); }  } catch (TargetLostException targetLostException) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle getTemplateInstructionHandle(Template template) {
/* 1495 */     return (InstructionHandle)this._templateIHs.get(template);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAttributeName(String qname) {
/* 1502 */     int col = qname.lastIndexOf(':') + 1;
/* 1503 */     return (qname.charAt(col) == '@');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isNamespaceName(String qname) {
/* 1511 */     int col = qname.lastIndexOf(':');
/* 1512 */     return (col > -1 && qname.charAt(qname.length() - 1) == '*');
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Mode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */