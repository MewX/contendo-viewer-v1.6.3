/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.classfile.Method;
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ANEWARRAY;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.NOP;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.PUTFIELD;
/*     */ import org.apache.bcel.generic.TABLESWITCH;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.CompareGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.NodeSortRecordFactGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.NodeSortRecordGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
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
/*     */ final class Sort
/*     */   extends Instruction
/*     */   implements Closure
/*     */ {
/*     */   private Expression _select;
/*     */   private AttributeValue _order;
/*     */   private AttributeValue _caseOrder;
/*     */   private AttributeValue _dataType;
/*     */   private String _lang;
/*  75 */   private String _data = null;
/*     */ 
/*     */   
/*  78 */   private String _className = null;
/*  79 */   private ArrayList _closureVars = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _needsSortRecordFactory = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inInnerClass() {
/*  89 */     return (this._className != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getParentClosure() {
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInnerClassName() {
/* 104 */     return this._className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVariable(VariableRefBase variableRef) {
/* 111 */     if (this._closureVars == null) {
/* 112 */       this._closureVars = new ArrayList();
/*     */     }
/*     */ 
/*     */     
/* 116 */     if (!this._closureVars.contains(variableRef)) {
/* 117 */       this._closureVars.add(variableRef);
/* 118 */       this._needsSortRecordFactory = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setInnerClassName(String className) {
/* 125 */     this._className = className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 133 */     SyntaxTreeNode parent = getParent();
/* 134 */     if (!(parent instanceof ApplyTemplates) && !(parent instanceof ForEach)) {
/*     */       
/* 136 */       reportError(this, parser, "STRAY_SORT_ERR", null);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 141 */     this._select = parser.parseExpression(this, "select", "string(.)");
/*     */ 
/*     */     
/* 144 */     String val = getAttribute("order");
/* 145 */     if (val.length() == 0) val = "ascending"; 
/* 146 */     this._order = AttributeValue.create(this, val, parser);
/*     */ 
/*     */     
/* 149 */     val = getAttribute("data-type");
/* 150 */     if (val.length() == 0) {
/*     */       
/* 152 */       try { Type type = this._select.typeCheck(parser.getSymbolTable());
/* 153 */         if (type instanceof org.apache.xalan.xsltc.compiler.util.IntType)
/* 154 */         { val = "number"; }
/*     */         else
/* 156 */         { val = "text"; }  } catch (TypeCheckError e)
/*     */       
/*     */       { 
/* 159 */         val = "text"; }
/*     */     
/*     */     }
/* 162 */     this._dataType = AttributeValue.create(this, val, parser);
/*     */     
/* 164 */     this._lang = getAttribute("lang");
/*     */ 
/*     */ 
/*     */     
/* 168 */     val = getAttribute("case-order");
/* 169 */     this._caseOrder = AttributeValue.create(this, val, parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 178 */     Type tselect = this._select.typeCheck(stable);
/*     */ 
/*     */ 
/*     */     
/* 182 */     if (!(tselect instanceof org.apache.xalan.xsltc.compiler.util.StringType)) {
/* 183 */       this._select = new CastExpr(this._select, Type.String);
/*     */     }
/*     */     
/* 186 */     this._order.typeCheck(stable);
/* 187 */     this._caseOrder.typeCheck(stable);
/* 188 */     this._dataType.typeCheck(stable);
/* 189 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateSortType(ClassGenerator classGen, MethodGenerator methodGen) {
/* 198 */     this._dataType.translate(classGen, methodGen);
/*     */   }
/*     */ 
/*     */   
/*     */   public void translateSortOrder(ClassGenerator classGen, MethodGenerator methodGen) {
/* 203 */     this._order.translate(classGen, methodGen);
/*     */   }
/*     */ 
/*     */   
/*     */   public void translateCaseOrder(ClassGenerator classGen, MethodGenerator methodGen) {
/* 208 */     this._caseOrder.translate(classGen, methodGen);
/*     */   }
/*     */ 
/*     */   
/*     */   public void translateLang(ClassGenerator classGen, MethodGenerator methodGen) {
/* 213 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 214 */     InstructionList il = methodGen.getInstructionList();
/* 215 */     il.append((CompoundInstruction)new PUSH(cpg, this._lang));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateSelect(ClassGenerator classGen, MethodGenerator methodGen) {
/* 225 */     this._select.translate(classGen, methodGen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void translateSortIterator(ClassGenerator classGen, MethodGenerator methodGen, Expression nodeSet, Vector sortObjects) {
/* 245 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 246 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 249 */     int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SortingIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xalan/xsltc/dom/NodeSortRecordFactory;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SortingIterator")));
/* 256 */     il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */     
/* 259 */     if (nodeSet == null) {
/* 260 */       int children = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getAxisIterator", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */       
/* 264 */       il.append(methodGen.loadDOM());
/* 265 */       il.append((CompoundInstruction)new PUSH(cpg, 3));
/* 266 */       il.append((Instruction)new INVOKEINTERFACE(children, 2));
/*     */     } else {
/*     */       
/* 269 */       nodeSet.translate(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 274 */     compileSortRecordFactory(sortObjects, classGen, methodGen);
/* 275 */     il.append((Instruction)new INVOKESPECIAL(init));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void compileSortRecordFactory(Vector sortObjects, ClassGenerator classGen, MethodGenerator methodGen) {
/* 286 */     String sortRecordClass = compileSortRecord(sortObjects, classGen, methodGen);
/*     */ 
/*     */     
/* 289 */     boolean needsSortRecordFactory = false;
/* 290 */     int nsorts = sortObjects.size();
/* 291 */     for (int i = 0; i < nsorts; i++) {
/* 292 */       Sort sort = sortObjects.elementAt(i);
/* 293 */       needsSortRecordFactory |= sort._needsSortRecordFactory;
/*     */     } 
/*     */     
/* 296 */     String sortRecordFactoryClass = "org/apache/xalan/xsltc/dom/NodeSortRecordFactory";
/* 297 */     if (needsSortRecordFactory) {
/* 298 */       sortRecordFactoryClass = compileSortRecordFactory(sortObjects, classGen, methodGen, sortRecordClass);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 303 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 304 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 306 */     il.append((Instruction)new NEW(cpg.addClass(sortRecordFactoryClass)));
/* 307 */     il.append((Instruction)InstructionConstants.DUP);
/* 308 */     il.append(methodGen.loadDOM());
/* 309 */     il.append((CompoundInstruction)new PUSH(cpg, sortRecordClass));
/* 310 */     il.append(classGen.loadTranslet());
/*     */ 
/*     */     
/* 313 */     il.append((CompoundInstruction)new PUSH(cpg, nsorts));
/* 314 */     il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/* 315 */     for (int level = 0; level < nsorts; level++) {
/* 316 */       Sort sort = sortObjects.elementAt(level);
/* 317 */       il.append((Instruction)InstructionConstants.DUP);
/* 318 */       il.append((CompoundInstruction)new PUSH(cpg, level));
/* 319 */       sort.translateSortOrder(classGen, methodGen);
/* 320 */       il.append((Instruction)InstructionConstants.AASTORE);
/*     */     } 
/*     */     
/* 323 */     il.append((CompoundInstruction)new PUSH(cpg, nsorts));
/* 324 */     il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/* 325 */     for (int k = 0; k < nsorts; k++) {
/* 326 */       Sort sort = sortObjects.elementAt(k);
/* 327 */       il.append((Instruction)InstructionConstants.DUP);
/* 328 */       il.append((CompoundInstruction)new PUSH(cpg, k));
/* 329 */       sort.translateSortType(classGen, methodGen);
/* 330 */       il.append((Instruction)InstructionConstants.AASTORE);
/*     */     } 
/*     */     
/* 333 */     il.append((CompoundInstruction)new PUSH(cpg, nsorts));
/* 334 */     il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/* 335 */     for (int m = 0; m < nsorts; m++) {
/* 336 */       Sort sort = sortObjects.elementAt(m);
/* 337 */       il.append((Instruction)InstructionConstants.DUP);
/* 338 */       il.append((CompoundInstruction)new PUSH(cpg, m));
/* 339 */       sort.translateLang(classGen, methodGen);
/* 340 */       il.append((Instruction)InstructionConstants.AASTORE);
/*     */     } 
/*     */     
/* 343 */     il.append((CompoundInstruction)new PUSH(cpg, nsorts));
/* 344 */     il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.String")));
/* 345 */     for (int n = 0; n < nsorts; n++) {
/* 346 */       Sort sort = sortObjects.elementAt(n);
/* 347 */       il.append((Instruction)InstructionConstants.DUP);
/* 348 */       il.append((CompoundInstruction)new PUSH(cpg, n));
/* 349 */       sort.translateCaseOrder(classGen, methodGen);
/* 350 */       il.append((Instruction)InstructionConstants.AASTORE);
/*     */     } 
/*     */     
/* 353 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref(sortRecordFactoryClass, "<init>", "(Lorg/apache/xalan/xsltc/DOM;Ljava/lang/String;Lorg/apache/xalan/xsltc/Translet;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V")));
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
/* 364 */     ArrayList dups = new ArrayList();
/*     */     
/* 366 */     for (int j = 0; j < nsorts; j++) {
/* 367 */       Sort sort = sortObjects.get(j);
/* 368 */       int length = (sort._closureVars == null) ? 0 : sort._closureVars.size();
/*     */ 
/*     */       
/* 371 */       for (int i1 = 0; i1 < length; i1++) {
/* 372 */         VariableRefBase varRef = sort._closureVars.get(i1);
/*     */ 
/*     */         
/* 375 */         if (!dups.contains(varRef)) {
/*     */           
/* 377 */           VariableBase var = varRef.getVariable();
/*     */ 
/*     */           
/* 380 */           il.append((Instruction)InstructionConstants.DUP);
/* 381 */           il.append(var.loadInstruction());
/* 382 */           il.append((Instruction)new PUTFIELD(cpg.addFieldref(sortRecordFactoryClass, var.getEscapedName(), var.getType().toSignature())));
/*     */ 
/*     */           
/* 385 */           dups.add(varRef);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String compileSortRecordFactory(Vector sortObjects, ClassGenerator classGen, MethodGenerator methodGen, String sortRecordClass) {
/* 394 */     XSLTC xsltc = ((Sort)sortObjects.firstElement()).getXSLTC();
/* 395 */     String className = xsltc.getHelperClassName();
/*     */     
/* 397 */     NodeSortRecordFactGenerator sortRecordFactory = new NodeSortRecordFactGenerator(className, "org/apache/xalan/xsltc/dom/NodeSortRecordFactory", className + ".java", 49, new String[0], classGen.getStylesheet());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 405 */     ConstantPoolGen cpg = sortRecordFactory.getConstantPool();
/*     */ 
/*     */     
/* 408 */     int nsorts = sortObjects.size();
/* 409 */     ArrayList dups = new ArrayList();
/*     */     
/* 411 */     for (int j = 0; j < nsorts; j++) {
/* 412 */       Sort sort = sortObjects.get(j);
/* 413 */       int length = (sort._closureVars == null) ? 0 : sort._closureVars.size();
/*     */ 
/*     */       
/* 416 */       for (int k = 0; k < length; k++) {
/* 417 */         VariableRefBase varRef = sort._closureVars.get(k);
/*     */ 
/*     */         
/* 420 */         if (!dups.contains(varRef)) {
/*     */           
/* 422 */           VariableBase var = varRef.getVariable();
/* 423 */           sortRecordFactory.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
/*     */ 
/*     */ 
/*     */           
/* 427 */           dups.add(varRef);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 432 */     Type[] argTypes = new Type[7];
/*     */     
/* 434 */     argTypes[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
/* 435 */     argTypes[1] = Util.getJCRefType("Ljava/lang/String;");
/* 436 */     argTypes[2] = Util.getJCRefType("Lorg/apache/xalan/xsltc/Translet;");
/* 437 */     argTypes[3] = Util.getJCRefType("[Ljava/lang/String;");
/* 438 */     argTypes[4] = Util.getJCRefType("[Ljava/lang/String;");
/* 439 */     argTypes[5] = Util.getJCRefType("[Ljava/lang/String;");
/* 440 */     argTypes[6] = Util.getJCRefType("[Ljava/lang/String;");
/*     */     
/* 442 */     String[] argNames = new String[7];
/* 443 */     argNames[0] = "document";
/* 444 */     argNames[1] = "className";
/* 445 */     argNames[2] = "translet";
/* 446 */     argNames[3] = "order";
/* 447 */     argNames[4] = "type";
/* 448 */     argNames[5] = "lang";
/* 449 */     argNames[6] = "case_order";
/*     */ 
/*     */     
/* 452 */     InstructionList il = new InstructionList();
/* 453 */     MethodGenerator constructor = new MethodGenerator(1, (Type)Type.VOID, argTypes, argNames, "<init>", className, il, cpg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 460 */     il.append((Instruction)InstructionConstants.ALOAD_0);
/* 461 */     il.append((Instruction)InstructionConstants.ALOAD_1);
/* 462 */     il.append((Instruction)InstructionConstants.ALOAD_2);
/* 463 */     il.append((Instruction)new ALOAD(3));
/* 464 */     il.append((Instruction)new ALOAD(4));
/* 465 */     il.append((Instruction)new ALOAD(5));
/* 466 */     il.append((Instruction)new ALOAD(6));
/* 467 */     il.append((Instruction)new ALOAD(7));
/* 468 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("org/apache/xalan/xsltc/dom/NodeSortRecordFactory", "<init>", "(Lorg/apache/xalan/xsltc/DOM;Ljava/lang/String;Lorg/apache/xalan/xsltc/Translet;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 477 */     il.append((Instruction)InstructionConstants.RETURN);
/*     */ 
/*     */     
/* 480 */     il = new InstructionList();
/* 481 */     MethodGenerator makeNodeSortRecord = new MethodGenerator(1, Util.getJCRefType("Lorg/apache/xalan/xsltc/dom/NodeSortRecord;"), new Type[] { (Type)Type.INT, (Type)Type.INT }, new String[] { "node", "last" }, "makeNodeSortRecord", className, il, cpg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 490 */     il.append((Instruction)InstructionConstants.ALOAD_0);
/* 491 */     il.append((Instruction)InstructionConstants.ILOAD_1);
/* 492 */     il.append((Instruction)InstructionConstants.ILOAD_2);
/* 493 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("org/apache/xalan/xsltc/dom/NodeSortRecordFactory", "makeNodeSortRecord", "(II)Lorg/apache/xalan/xsltc/dom/NodeSortRecord;")));
/*     */     
/* 495 */     il.append((Instruction)InstructionConstants.DUP);
/* 496 */     il.append((Instruction)new CHECKCAST(cpg.addClass(sortRecordClass)));
/*     */ 
/*     */     
/* 499 */     int ndups = dups.size();
/* 500 */     for (int i = 0; i < ndups; i++) {
/* 501 */       VariableRefBase varRef = dups.get(i);
/* 502 */       VariableBase var = varRef.getVariable();
/* 503 */       Type varType = var.getType();
/*     */       
/* 505 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 508 */       il.append((Instruction)InstructionConstants.ALOAD_0);
/* 509 */       il.append((Instruction)new GETFIELD(cpg.addFieldref(className, var.getEscapedName(), varType.toSignature())));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 514 */       il.append((Instruction)new PUTFIELD(cpg.addFieldref(sortRecordClass, var.getEscapedName(), varType.toSignature())));
/*     */     } 
/*     */ 
/*     */     
/* 518 */     il.append((Instruction)InstructionConstants.POP);
/* 519 */     il.append((Instruction)InstructionConstants.ARETURN);
/*     */     
/* 521 */     constructor.setMaxLocals();
/* 522 */     constructor.setMaxStack();
/* 523 */     sortRecordFactory.addMethod(constructor.getMethod());
/* 524 */     makeNodeSortRecord.setMaxLocals();
/* 525 */     makeNodeSortRecord.setMaxStack();
/* 526 */     sortRecordFactory.addMethod(makeNodeSortRecord.getMethod());
/* 527 */     xsltc.dumpClass(sortRecordFactory.getJavaClass());
/*     */     
/* 529 */     return className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String compileSortRecord(Vector sortObjects, ClassGenerator classGen, MethodGenerator methodGen) {
/* 538 */     XSLTC xsltc = ((Sort)sortObjects.firstElement()).getXSLTC();
/* 539 */     String className = xsltc.getHelperClassName();
/*     */ 
/*     */     
/* 542 */     NodeSortRecordGenerator sortRecord = new NodeSortRecordGenerator(className, "org.apache.xalan.xsltc.dom.NodeSortRecord", "sort$0.java", 49, new String[0], classGen.getStylesheet());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 550 */     ConstantPoolGen cpg = sortRecord.getConstantPool();
/*     */ 
/*     */     
/* 553 */     int nsorts = sortObjects.size();
/* 554 */     ArrayList dups = new ArrayList();
/*     */     
/* 556 */     for (int j = 0; j < nsorts; j++) {
/* 557 */       Sort sort = sortObjects.get(j);
/*     */ 
/*     */       
/* 560 */       sort.setInnerClassName(className);
/*     */       
/* 562 */       int length = (sort._closureVars == null) ? 0 : sort._closureVars.size();
/*     */       
/* 564 */       for (int i = 0; i < length; i++) {
/* 565 */         VariableRefBase varRef = sort._closureVars.get(i);
/*     */ 
/*     */         
/* 568 */         if (!dups.contains(varRef)) {
/*     */           
/* 570 */           VariableBase var = varRef.getVariable();
/* 571 */           sortRecord.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
/*     */ 
/*     */ 
/*     */           
/* 575 */           dups.add(varRef);
/*     */         } 
/*     */       } 
/*     */     } 
/* 579 */     Method init = compileInit(sortObjects, sortRecord, cpg, className);
/*     */     
/* 581 */     Method extract = compileExtract(sortObjects, sortRecord, cpg, className);
/*     */     
/* 583 */     sortRecord.addMethod(init);
/* 584 */     sortRecord.addMethod(extract);
/*     */     
/* 586 */     xsltc.dumpClass(sortRecord.getJavaClass());
/* 587 */     return className;
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
/*     */   private static Method compileInit(Vector sortObjects, NodeSortRecordGenerator sortRecord, ConstantPoolGen cpg, String className) {
/* 600 */     InstructionList il = new InstructionList();
/* 601 */     MethodGenerator init = new MethodGenerator(1, (Type)Type.VOID, null, null, "<init>", className, il, cpg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 608 */     il.append((Instruction)InstructionConstants.ALOAD_0);
/* 609 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeSortRecord", "<init>", "()V")));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 614 */     il.append((Instruction)InstructionConstants.RETURN);
/*     */     
/* 616 */     init.stripAttributes(true);
/* 617 */     init.setMaxLocals();
/* 618 */     init.setMaxStack();
/*     */     
/* 620 */     return init.getMethod();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Method compileExtract(Vector sortObjects, NodeSortRecordGenerator sortRecord, ConstantPoolGen cpg, String className) {
/* 631 */     InstructionList il = new InstructionList();
/*     */ 
/*     */     
/* 634 */     CompareGenerator extractMethod = new CompareGenerator(17, (Type)Type.STRING, new Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), (Type)Type.INT, (Type)Type.INT, Util.getJCRefType("Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;"), (Type)Type.INT }, new String[] { "dom", "current", "level", "translet", "last" }, "extractValueFromDOM", className, il, cpg);
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
/* 653 */     int levels = sortObjects.size();
/* 654 */     int[] match = new int[levels];
/* 655 */     InstructionHandle[] target = new InstructionHandle[levels];
/* 656 */     InstructionHandle tblswitch = null;
/*     */ 
/*     */     
/* 659 */     if (levels > 1) {
/*     */       
/* 661 */       il.append((Instruction)new ILOAD(extractMethod.getLocalIndex("level")));
/*     */       
/* 663 */       tblswitch = il.append((Instruction)new NOP());
/*     */     } 
/*     */ 
/*     */     
/* 667 */     for (int level = 0; level < levels; level++) {
/* 668 */       match[level] = level;
/* 669 */       Sort sort = sortObjects.elementAt(level);
/* 670 */       target[level] = il.append(InstructionConstants.NOP);
/* 671 */       sort.translateSelect((ClassGenerator)sortRecord, (MethodGenerator)extractMethod);
/* 672 */       il.append((Instruction)InstructionConstants.ARETURN);
/*     */     } 
/*     */ 
/*     */     
/* 676 */     if (levels > 1) {
/*     */       
/* 678 */       InstructionHandle defaultTarget = il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */       
/* 680 */       il.insert(tblswitch, (BranchInstruction)new TABLESWITCH(match, target, defaultTarget));
/* 681 */       il.append((Instruction)InstructionConstants.ARETURN);
/*     */     } 
/*     */     
/* 684 */     extractMethod.stripAttributes(true);
/* 685 */     extractMethod.setMaxLocals();
/* 686 */     extractMethod.setMaxStack();
/* 687 */     extractMethod.removeNOPs();
/*     */     
/* 689 */     return extractMethod.getMethod();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Sort.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */