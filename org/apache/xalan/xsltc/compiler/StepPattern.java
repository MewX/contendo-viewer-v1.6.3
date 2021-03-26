/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.GOTO_W;
/*     */ import org.apache.bcel.generic.IFLT;
/*     */ import org.apache.bcel.generic.IFNE;
/*     */ import org.apache.bcel.generic.IFNONNULL;
/*     */ import org.apache.bcel.generic.IF_ICMPEQ;
/*     */ import org.apache.bcel.generic.IF_ICMPLT;
/*     */ import org.apache.bcel.generic.IF_ICMPNE;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.PUTFIELD;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ import org.apache.xml.dtm.Axis;
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
/*     */ class StepPattern
/*     */   extends RelativePathPattern
/*     */ {
/*     */   private static final int NO_CONTEXT = 0;
/*     */   private static final int SIMPLE_CONTEXT = 1;
/*     */   private static final int GENERAL_CONTEXT = 2;
/*     */   protected final int _axis;
/*     */   protected final int _nodeType;
/*     */   protected Vector _predicates;
/*  71 */   private Step _step = null;
/*     */   
/*     */   private boolean _isEpsilon = false;
/*     */   private int _contextCase;
/*  75 */   private double _priority = Double.MAX_VALUE;
/*     */   
/*     */   public StepPattern(int axis, int nodeType, Vector predicates) {
/*  78 */     this._axis = axis;
/*  79 */     this._nodeType = nodeType;
/*  80 */     this._predicates = predicates;
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  84 */     super.setParser(parser);
/*  85 */     if (this._predicates != null) {
/*  86 */       int n = this._predicates.size();
/*  87 */       for (int i = 0; i < n; i++) {
/*  88 */         Predicate exp = this._predicates.elementAt(i);
/*  89 */         exp.setParser(parser);
/*  90 */         exp.setParent(this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getNodeType() {
/*  96 */     return this._nodeType;
/*     */   }
/*     */   
/*     */   public void setPriority(double priority) {
/* 100 */     this._priority = priority;
/*     */   }
/*     */   
/*     */   public StepPattern getKernelPattern() {
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isWildcard() {
/* 108 */     return (this._isEpsilon && !hasPredicates());
/*     */   }
/*     */   
/*     */   public StepPattern setPredicates(Vector predicates) {
/* 112 */     this._predicates = predicates;
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   protected boolean hasPredicates() {
/* 117 */     return (this._predicates != null && this._predicates.size() > 0);
/*     */   }
/*     */   
/*     */   public double getDefaultPriority() {
/* 121 */     if (this._priority != Double.MAX_VALUE) {
/* 122 */       return this._priority;
/*     */     }
/*     */     
/* 125 */     if (hasPredicates()) {
/* 126 */       return 0.5D;
/*     */     }
/*     */     
/* 129 */     switch (this._nodeType) {
/*     */       case -1:
/* 131 */         return -0.5D;
/*     */       case 0:
/* 133 */         return 0.0D;
/*     */     } 
/* 135 */     return (this._nodeType >= 14) ? 0.0D : -0.5D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAxis() {
/* 141 */     return this._axis;
/*     */   }
/*     */   
/*     */   public void reduceKernelPattern() {
/* 145 */     this._isEpsilon = true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 149 */     StringBuffer buffer = new StringBuffer("stepPattern(\"");
/* 150 */     buffer.append(Axis.names[this._axis]).append("\", ").append(this._isEpsilon ? ("epsilon{" + Integer.toString(this._nodeType) + "}") : Integer.toString(this._nodeType));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (this._predicates != null)
/* 156 */       buffer.append(", ").append(this._predicates.toString()); 
/* 157 */     return buffer.append(')').toString();
/*     */   }
/*     */   
/*     */   private int analyzeCases() {
/* 161 */     boolean noContext = true;
/* 162 */     int n = this._predicates.size();
/*     */     
/* 164 */     for (int i = 0; i < n && noContext; i++) {
/* 165 */       Predicate pred = this._predicates.elementAt(i);
/* 166 */       if (pred.isNthPositionFilter() || pred.hasPositionCall() || pred.hasLastCall())
/*     */       {
/*     */ 
/*     */         
/* 170 */         noContext = false;
/*     */       }
/*     */     } 
/*     */     
/* 174 */     if (noContext) {
/* 175 */       return 0;
/*     */     }
/* 177 */     if (n == 1) {
/* 178 */       return 1;
/*     */     }
/* 180 */     return 2;
/*     */   }
/*     */   
/*     */   private String getNextFieldName() {
/* 184 */     return "__step_pattern_iter_" + getXSLTC().nextStepPatternSerial();
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 188 */     if (hasPredicates()) {
/*     */       
/* 190 */       int n = this._predicates.size();
/* 191 */       for (int i = 0; i < n; i++) {
/* 192 */         Predicate pred = this._predicates.elementAt(i);
/* 193 */         pred.typeCheck(stable);
/*     */       } 
/*     */ 
/*     */       
/* 197 */       this._contextCase = analyzeCases();
/*     */       
/* 199 */       Step step = null;
/*     */ 
/*     */       
/* 202 */       if (this._contextCase == 1) {
/* 203 */         Predicate pred = this._predicates.elementAt(0);
/* 204 */         if (pred.isNthPositionFilter()) {
/* 205 */           this._contextCase = 2;
/* 206 */           step = new Step(this._axis, this._nodeType, this._predicates);
/*     */         } else {
/* 208 */           step = new Step(this._axis, this._nodeType, null);
/*     */         } 
/* 210 */       } else if (this._contextCase == 2) {
/* 211 */         int len = this._predicates.size();
/* 212 */         for (int j = 0; j < len; j++) {
/* 213 */           ((Predicate)this._predicates.elementAt(j)).dontOptimize();
/*     */         }
/*     */         
/* 216 */         step = new Step(this._axis, this._nodeType, this._predicates);
/*     */       } 
/*     */       
/* 219 */       if (step != null) {
/* 220 */         step.setParser(getParser());
/* 221 */         step.typeCheck(stable);
/* 222 */         this._step = step;
/*     */       } 
/*     */     } 
/* 225 */     return (this._axis == 3) ? Type.Element : Type.Attribute;
/*     */   }
/*     */ 
/*     */   
/*     */   private void translateKernel(ClassGenerator classGen, MethodGenerator methodGen) {
/* 230 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 231 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 233 */     if (this._nodeType == 1) {
/* 234 */       int check = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "isElement", "(I)Z");
/*     */       
/* 236 */       il.append(methodGen.loadDOM());
/* 237 */       il.append((Instruction)InstructionConstants.SWAP);
/* 238 */       il.append((Instruction)new INVOKEINTERFACE(check, 2));
/*     */ 
/*     */       
/* 241 */       BranchHandle icmp = il.append((BranchInstruction)new IFNE(null));
/* 242 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO_W(null)));
/* 243 */       icmp.setTarget(il.append(InstructionConstants.NOP));
/*     */     }
/* 245 */     else if (this._nodeType == 2) {
/* 246 */       int check = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "isAttribute", "(I)Z");
/*     */       
/* 248 */       il.append(methodGen.loadDOM());
/* 249 */       il.append((Instruction)InstructionConstants.SWAP);
/* 250 */       il.append((Instruction)new INVOKEINTERFACE(check, 2));
/*     */ 
/*     */       
/* 253 */       BranchHandle icmp = il.append((BranchInstruction)new IFNE(null));
/* 254 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO_W(null)));
/* 255 */       icmp.setTarget(il.append(InstructionConstants.NOP));
/*     */     }
/*     */     else {
/*     */       
/* 259 */       int getEType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
/*     */ 
/*     */       
/* 262 */       il.append(methodGen.loadDOM());
/* 263 */       il.append((Instruction)InstructionConstants.SWAP);
/* 264 */       il.append((Instruction)new INVOKEINTERFACE(getEType, 2));
/* 265 */       il.append((CompoundInstruction)new PUSH(cpg, this._nodeType));
/*     */ 
/*     */       
/* 268 */       BranchHandle icmp = il.append((BranchInstruction)new IF_ICMPEQ(null));
/* 269 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO_W(null)));
/* 270 */       icmp.setTarget(il.append(InstructionConstants.NOP));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void translateNoContext(ClassGenerator classGen, MethodGenerator methodGen) {
/* 276 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 277 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 280 */     il.append(methodGen.loadCurrentNode());
/* 281 */     il.append((Instruction)InstructionConstants.SWAP);
/*     */ 
/*     */     
/* 284 */     il.append(methodGen.storeCurrentNode());
/*     */ 
/*     */     
/* 287 */     if (!this._isEpsilon) {
/* 288 */       il.append(methodGen.loadCurrentNode());
/* 289 */       translateKernel(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */     
/* 293 */     int n = this._predicates.size();
/* 294 */     for (int i = 0; i < n; i++) {
/* 295 */       Predicate pred = this._predicates.elementAt(i);
/* 296 */       Expression exp = pred.getExpr();
/* 297 */       exp.translateDesynthesized(classGen, methodGen);
/* 298 */       this._trueList.append(exp._trueList);
/* 299 */       this._falseList.append(exp._falseList);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 304 */     InstructionHandle restore = il.append(methodGen.storeCurrentNode());
/* 305 */     backPatchTrueList(restore);
/* 306 */     BranchHandle skipFalse = il.append((BranchInstruction)new GOTO(null));
/*     */ 
/*     */     
/* 309 */     restore = il.append(methodGen.storeCurrentNode());
/* 310 */     backPatchFalseList(restore);
/* 311 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO(null)));
/*     */ 
/*     */     
/* 314 */     skipFalse.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void translateSimpleContext(ClassGenerator classGen, MethodGenerator methodGen) {
/* 320 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 321 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */ 
/*     */     
/* 325 */     LocalVariableGen match = methodGen.addLocalVariable("step_pattern_tmp1", Util.getJCRefType("I"), il.getEnd(), null);
/*     */ 
/*     */     
/* 328 */     il.append((Instruction)new ISTORE(match.getIndex()));
/*     */ 
/*     */     
/* 331 */     if (!this._isEpsilon) {
/* 332 */       il.append((Instruction)new ILOAD(match.getIndex()));
/* 333 */       translateKernel(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */     
/* 337 */     il.append(methodGen.loadCurrentNode());
/* 338 */     il.append(methodGen.loadIterator());
/*     */ 
/*     */     
/* 341 */     int index = cpg.addMethodref("org.apache.xalan.xsltc.dom.MatchingIterator", "<init>", "(ILorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */     
/* 343 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.MatchingIterator")));
/* 344 */     il.append((Instruction)InstructionConstants.DUP);
/* 345 */     il.append((Instruction)new ILOAD(match.getIndex()));
/* 346 */     this._step.translate(classGen, methodGen);
/* 347 */     il.append((Instruction)new INVOKESPECIAL(index));
/*     */ 
/*     */     
/* 350 */     il.append(methodGen.loadDOM());
/* 351 */     il.append((Instruction)new ILOAD(match.getIndex()));
/* 352 */     index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
/* 353 */     il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */ 
/*     */     
/* 356 */     il.append(methodGen.setStartNode());
/*     */ 
/*     */     
/* 359 */     il.append(methodGen.storeIterator());
/* 360 */     il.append((Instruction)new ILOAD(match.getIndex()));
/* 361 */     il.append(methodGen.storeCurrentNode());
/*     */ 
/*     */     
/* 364 */     Predicate pred = this._predicates.elementAt(0);
/* 365 */     Expression exp = pred.getExpr();
/* 366 */     exp.translateDesynthesized(classGen, methodGen);
/*     */ 
/*     */     
/* 369 */     InstructionHandle restore = il.append(methodGen.storeIterator());
/* 370 */     il.append(methodGen.storeCurrentNode());
/* 371 */     exp.backPatchTrueList(restore);
/* 372 */     BranchHandle skipFalse = il.append((BranchInstruction)new GOTO(null));
/*     */ 
/*     */     
/* 375 */     restore = il.append(methodGen.storeIterator());
/* 376 */     il.append(methodGen.storeCurrentNode());
/* 377 */     exp.backPatchFalseList(restore);
/* 378 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO(null)));
/*     */ 
/*     */     
/* 381 */     skipFalse.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */   
/*     */   private void translateGeneralContext(ClassGenerator classGen, MethodGenerator methodGen) {
/* 386 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 387 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 389 */     int iteratorIndex = 0;
/* 390 */     BranchHandle ifBlock = null;
/*     */     
/* 392 */     String iteratorName = getNextFieldName();
/*     */ 
/*     */     
/* 395 */     LocalVariableGen node = methodGen.addLocalVariable("step_pattern_tmp1", Util.getJCRefType("I"), il.getEnd(), null);
/*     */ 
/*     */     
/* 398 */     il.append((Instruction)new ISTORE(node.getIndex()));
/*     */ 
/*     */     
/* 401 */     LocalVariableGen iter = methodGen.addLocalVariable("step_pattern_tmp2", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 406 */     if (!classGen.isExternal()) {
/* 407 */       Field iterator = new Field(2, cpg.addUtf8(iteratorName), cpg.addUtf8("Lorg/apache/xml/dtm/DTMAxisIterator;"), null, cpg.getConstantPool());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 412 */       classGen.addField(iterator);
/* 413 */       iteratorIndex = cpg.addFieldref(classGen.getClassName(), iteratorName, "Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */       
/* 417 */       il.append(classGen.loadTranslet());
/* 418 */       il.append((Instruction)new GETFIELD(iteratorIndex));
/* 419 */       il.append((Instruction)InstructionConstants.DUP);
/* 420 */       il.append((Instruction)new ASTORE(iter.getIndex()));
/* 421 */       ifBlock = il.append((BranchInstruction)new IFNONNULL(null));
/* 422 */       il.append(classGen.loadTranslet());
/*     */     } 
/*     */ 
/*     */     
/* 426 */     this._step.translate(classGen, methodGen);
/* 427 */     il.append((Instruction)new ASTORE(iter.getIndex()));
/*     */ 
/*     */     
/* 430 */     if (!classGen.isExternal()) {
/* 431 */       il.append((Instruction)new ALOAD(iter.getIndex()));
/* 432 */       il.append((Instruction)new PUTFIELD(iteratorIndex));
/* 433 */       ifBlock.setTarget(il.append(InstructionConstants.NOP));
/*     */     } 
/*     */ 
/*     */     
/* 437 */     il.append(methodGen.loadDOM());
/* 438 */     il.append((Instruction)new ILOAD(node.getIndex()));
/* 439 */     int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
/*     */     
/* 441 */     il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */ 
/*     */     
/* 444 */     il.append((Instruction)new ALOAD(iter.getIndex()));
/* 445 */     il.append((Instruction)InstructionConstants.SWAP);
/* 446 */     il.append(methodGen.setStartNode());
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
/* 458 */     LocalVariableGen node2 = methodGen.addLocalVariable("step_pattern_tmp3", Util.getJCRefType("I"), il.getEnd(), null);
/*     */ 
/*     */ 
/*     */     
/* 462 */     BranchHandle skipNext = il.append((BranchInstruction)new GOTO(null));
/* 463 */     InstructionHandle next = il.append((Instruction)new ALOAD(iter.getIndex()));
/* 464 */     InstructionHandle begin = il.append(methodGen.nextNode());
/* 465 */     il.append((Instruction)InstructionConstants.DUP);
/* 466 */     il.append((Instruction)new ISTORE(node2.getIndex()));
/* 467 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IFLT(null)));
/*     */     
/* 469 */     il.append((Instruction)new ILOAD(node2.getIndex()));
/* 470 */     il.append((Instruction)new ILOAD(node.getIndex()));
/* 471 */     il.append((BranchInstruction)new IF_ICMPLT(next));
/*     */     
/* 473 */     il.append((Instruction)new ILOAD(node2.getIndex()));
/* 474 */     il.append((Instruction)new ILOAD(node.getIndex()));
/* 475 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IF_ICMPNE(null)));
/*     */     
/* 477 */     skipNext.setTarget(begin);
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 481 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 482 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 484 */     if (hasPredicates()) {
/* 485 */       switch (this._contextCase) {
/*     */         case 0:
/* 487 */           translateNoContext(classGen, methodGen);
/*     */           return;
/*     */         
/*     */         case 1:
/* 491 */           translateSimpleContext(classGen, methodGen);
/*     */           return;
/*     */       } 
/*     */       
/* 495 */       translateGeneralContext(classGen, methodGen);
/*     */ 
/*     */     
/*     */     }
/* 499 */     else if (isWildcard()) {
/* 500 */       il.append((Instruction)InstructionConstants.POP);
/*     */     } else {
/*     */       
/* 503 */       translateKernel(classGen, methodGen);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/StepPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */