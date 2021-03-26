/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.PUTFIELD;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.FilterGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.TestGenerator;
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
/*     */ final class Predicate
/*     */   extends Expression
/*     */   implements Closure
/*     */ {
/*  58 */   private Expression _exp = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _canOptimize = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _nthPositionFilter = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _nthDescendant = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   int _ptype = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private String _className = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   private ArrayList _closureVars = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   private Closure _parentClosure = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   private Expression _value = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   private Step _step = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate(Expression exp) {
/* 113 */     this._exp = exp;
/* 114 */     this._exp.setParent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParser(Parser parser) {
/* 122 */     super.setParser(parser);
/* 123 */     this._exp.setParser(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNthPositionFilter() {
/* 131 */     return this._nthPositionFilter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNthDescendant() {
/* 139 */     return this._nthDescendant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dontOptimize() {
/* 146 */     this._canOptimize = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPositionCall() {
/* 154 */     return this._exp.hasPositionCall();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasLastCall() {
/* 162 */     return this._exp.hasLastCall();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inInnerClass() {
/* 172 */     return (this._className != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getParentClosure() {
/* 179 */     if (this._parentClosure == null) {
/* 180 */       SyntaxTreeNode node = getParent();
/*     */       do {
/* 182 */         if (node instanceof Closure) {
/* 183 */           this._parentClosure = (Closure)node;
/*     */           break;
/*     */         } 
/* 186 */         if (node instanceof TopLevelElement) {
/*     */           break;
/*     */         }
/* 189 */         node = node.getParent();
/* 190 */       } while (node != null);
/*     */     } 
/* 192 */     return this._parentClosure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInnerClassName() {
/* 200 */     return this._className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVariable(VariableRefBase variableRef) {
/* 207 */     if (this._closureVars == null) {
/* 208 */       this._closureVars = new ArrayList();
/*     */     }
/*     */ 
/*     */     
/* 212 */     if (!this._closureVars.contains(variableRef)) {
/* 213 */       this._closureVars.add(variableRef);
/*     */ 
/*     */       
/* 216 */       Closure parentClosure = getParentClosure();
/* 217 */       if (parentClosure != null) {
/* 218 */         parentClosure.addVariable(variableRef);
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
/*     */   public int getPosType() {
/* 230 */     if (this._ptype == -1) {
/* 231 */       SyntaxTreeNode parent = getParent();
/* 232 */       if (parent instanceof StepPattern) {
/* 233 */         this._ptype = ((StepPattern)parent).getNodeType();
/*     */       }
/* 235 */       else if (parent instanceof AbsoluteLocationPath) {
/* 236 */         AbsoluteLocationPath path = (AbsoluteLocationPath)parent;
/* 237 */         Expression exp = path.getPath();
/* 238 */         if (exp instanceof Step) {
/* 239 */           this._ptype = ((Step)exp).getNodeType();
/*     */         }
/*     */       }
/* 242 */       else if (parent instanceof VariableRefBase) {
/* 243 */         VariableRefBase ref = (VariableRefBase)parent;
/* 244 */         VariableBase var = ref.getVariable();
/* 245 */         Expression exp = var.getExpression();
/* 246 */         if (exp instanceof Step) {
/* 247 */           this._ptype = ((Step)exp).getNodeType();
/*     */         }
/*     */       }
/* 250 */       else if (parent instanceof Step) {
/* 251 */         this._ptype = ((Step)parent).getNodeType();
/*     */       } 
/*     */     } 
/* 254 */     return this._ptype;
/*     */   }
/*     */   
/*     */   public boolean parentIsPattern() {
/* 258 */     return getParent() instanceof Pattern;
/*     */   }
/*     */   
/*     */   public Expression getExpr() {
/* 262 */     return this._exp;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 266 */     return "pred(" + this._exp + ')';
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
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 281 */     Type texp = this._exp.typeCheck(stable);
/*     */ 
/*     */     
/* 284 */     if (texp instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/* 285 */       this._exp = new CastExpr(this._exp, texp = Type.Real);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     if (texp instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 292 */       this._exp = new CastExpr(this._exp, Type.Boolean);
/* 293 */       this._exp = new CastExpr(this._exp, Type.Real);
/* 294 */       texp = this._exp.typeCheck(stable);
/*     */     } 
/*     */ 
/*     */     
/* 298 */     if (texp instanceof org.apache.xalan.xsltc.compiler.util.NumberType) {
/*     */       
/* 300 */       if (!(texp instanceof org.apache.xalan.xsltc.compiler.util.IntType)) {
/* 301 */         this._exp = new CastExpr(this._exp, Type.Int);
/*     */       }
/*     */       
/* 304 */       if (this._canOptimize) {
/*     */         
/* 306 */         this._nthPositionFilter = (!this._exp.hasLastCall() && !this._exp.hasPositionCall());
/*     */ 
/*     */ 
/*     */         
/* 310 */         if (this._nthPositionFilter) {
/* 311 */           SyntaxTreeNode parent = getParent();
/* 312 */           this._nthDescendant = (parent instanceof Step && parent.getParent() instanceof AbsoluteLocationPath);
/*     */           
/* 314 */           return this._type = Type.NodeSet;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 319 */       this._nthPositionFilter = this._nthDescendant = false;
/*     */ 
/*     */       
/* 322 */       QName position = getParser().getQNameIgnoreDefaultNs("position");
/*     */       
/* 324 */       PositionCall positionCall = new PositionCall(position);
/*     */       
/* 326 */       positionCall.setParser(getParser());
/* 327 */       positionCall.setParent(this);
/*     */       
/* 329 */       this._exp = new EqualityExpr(0, positionCall, this._exp);
/*     */       
/* 331 */       if (this._exp.typeCheck(stable) != Type.Boolean) {
/* 332 */         this._exp = new CastExpr(this._exp, Type.Boolean);
/*     */       }
/* 334 */       return this._type = Type.Boolean;
/*     */     } 
/*     */ 
/*     */     
/* 338 */     if (!(texp instanceof org.apache.xalan.xsltc.compiler.util.BooleanType)) {
/* 339 */       this._exp = new CastExpr(this._exp, Type.Boolean);
/*     */     }
/* 341 */     return this._type = Type.Boolean;
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
/*     */   private void compileFilter(ClassGenerator classGen, MethodGenerator methodGen) {
/* 357 */     this._className = getXSLTC().getHelperClassName();
/* 358 */     FilterGenerator filterGen = new FilterGenerator(this._className, "java.lang.Object", toString(), 33, new String[] { "org.apache.xalan.xsltc.dom.CurrentNodeListFilter" }, classGen.getStylesheet());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 367 */     ConstantPoolGen cpg = filterGen.getConstantPool();
/* 368 */     int length = (this._closureVars == null) ? 0 : this._closureVars.size();
/*     */ 
/*     */     
/* 371 */     for (int i = 0; i < length; i++) {
/* 372 */       VariableBase var = ((VariableRefBase)this._closureVars.get(i)).getVariable();
/*     */       
/* 374 */       filterGen.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 380 */     InstructionList il = new InstructionList();
/* 381 */     TestGenerator testGen = new TestGenerator(17, (Type)Type.BOOLEAN, new Type[] { (Type)Type.INT, (Type)Type.INT, (Type)Type.INT, (Type)Type.INT, Util.getJCRefType("Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;") }, new String[] { "node", "position", "last", "current", "translet", "iterator" }, "test", this._className, il, cpg);
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
/* 402 */     LocalVariableGen local = testGen.addLocalVariable("document", Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), null, null);
/*     */ 
/*     */     
/* 405 */     String className = classGen.getClassName();
/* 406 */     il.append(filterGen.loadTranslet());
/* 407 */     il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/* 408 */     il.append((Instruction)new GETFIELD(cpg.addFieldref(className, "_dom", "Lorg/apache/xalan/xsltc/DOM;")));
/*     */     
/* 410 */     il.append((Instruction)new ASTORE(local.getIndex()));
/*     */ 
/*     */     
/* 413 */     testGen.setDomIndex(local.getIndex());
/*     */     
/* 415 */     this._exp.translate((ClassGenerator)filterGen, (MethodGenerator)testGen);
/* 416 */     il.append((Instruction)InstructionConstants.IRETURN);
/*     */     
/* 418 */     testGen.stripAttributes(true);
/* 419 */     testGen.setMaxLocals();
/* 420 */     testGen.setMaxStack();
/* 421 */     testGen.removeNOPs();
/* 422 */     filterGen.addEmptyConstructor(1);
/* 423 */     filterGen.addMethod(testGen.getMethod());
/*     */     
/* 425 */     getXSLTC().dumpClass(filterGen.getJavaClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBooleanTest() {
/* 434 */     return this._exp instanceof BooleanExpr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNodeValueTest() {
/* 443 */     if (!this._canOptimize) return false; 
/* 444 */     return (getStep() != null && getCompareValue() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Step getStep() {
/* 454 */     if (this._step != null) {
/* 455 */       return this._step;
/*     */     }
/*     */ 
/*     */     
/* 459 */     if (this._exp == null) {
/* 460 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 464 */     if (this._exp instanceof EqualityExpr) {
/* 465 */       EqualityExpr exp = (EqualityExpr)this._exp;
/* 466 */       Expression left = exp.getLeft();
/* 467 */       Expression right = exp.getRight();
/*     */ 
/*     */       
/* 470 */       if (left instanceof CastExpr) {
/* 471 */         left = ((CastExpr)left).getExpr();
/*     */       }
/* 473 */       if (left instanceof Step) {
/* 474 */         this._step = (Step)left;
/*     */       }
/*     */ 
/*     */       
/* 478 */       if (right instanceof CastExpr) {
/* 479 */         right = ((CastExpr)right).getExpr();
/*     */       }
/* 481 */       if (right instanceof Step) {
/* 482 */         this._step = (Step)right;
/*     */       }
/*     */     } 
/* 485 */     return this._step;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getCompareValue() {
/* 495 */     if (this._value != null) {
/* 496 */       return this._value;
/*     */     }
/*     */ 
/*     */     
/* 500 */     if (this._exp == null) {
/* 501 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 505 */     if (this._exp instanceof EqualityExpr) {
/* 506 */       EqualityExpr exp = (EqualityExpr)this._exp;
/* 507 */       Expression left = exp.getLeft();
/* 508 */       Expression right = exp.getRight();
/*     */ 
/*     */       
/* 511 */       if (left instanceof LiteralExpr) {
/* 512 */         this._value = left;
/* 513 */         return this._value;
/*     */       } 
/*     */       
/* 516 */       if (left instanceof VariableRefBase && left.getType() == Type.String) {
/*     */ 
/*     */         
/* 519 */         this._value = left;
/* 520 */         return this._value;
/*     */       } 
/*     */ 
/*     */       
/* 524 */       if (right instanceof LiteralExpr) {
/* 525 */         this._value = right;
/* 526 */         return this._value;
/*     */       } 
/*     */       
/* 529 */       if (right instanceof VariableRefBase && right.getType() == Type.String) {
/*     */ 
/*     */         
/* 532 */         this._value = right;
/* 533 */         return this._value;
/*     */       } 
/*     */     } 
/* 536 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateFilter(ClassGenerator classGen, MethodGenerator methodGen) {
/* 547 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 548 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 551 */     compileFilter(classGen, methodGen);
/*     */ 
/*     */     
/* 554 */     il.append((Instruction)new NEW(cpg.addClass(this._className)));
/* 555 */     il.append((Instruction)InstructionConstants.DUP);
/* 556 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref(this._className, "<init>", "()V")));
/*     */ 
/*     */ 
/*     */     
/* 560 */     int length = (this._closureVars == null) ? 0 : this._closureVars.size();
/*     */     
/* 562 */     for (int i = 0; i < length; i++) {
/* 563 */       VariableRefBase varRef = this._closureVars.get(i);
/* 564 */       VariableBase var = varRef.getVariable();
/* 565 */       Type varType = var.getType();
/*     */       
/* 567 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 570 */       Closure variableClosure = this._parentClosure;
/* 571 */       while (variableClosure != null && 
/* 572 */         !variableClosure.inInnerClass()) {
/* 573 */         variableClosure = variableClosure.getParentClosure();
/*     */       }
/*     */ 
/*     */       
/* 577 */       if (variableClosure != null) {
/* 578 */         il.append((Instruction)InstructionConstants.ALOAD_0);
/* 579 */         il.append((Instruction)new GETFIELD(cpg.addFieldref(variableClosure.getInnerClassName(), var.getEscapedName(), varType.toSignature())));
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 585 */         il.append(var.loadInstruction());
/*     */       } 
/*     */ 
/*     */       
/* 589 */       il.append((Instruction)new PUTFIELD(cpg.addFieldref(this._className, var.getEscapedName(), varType.toSignature())));
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
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 603 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 604 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 606 */     if (this._nthPositionFilter || this._nthDescendant) {
/* 607 */       this._exp.translate(classGen, methodGen);
/*     */     }
/* 609 */     else if (isNodeValueTest() && getParent() instanceof Step) {
/* 610 */       this._value.translate(classGen, methodGen);
/* 611 */       il.append((Instruction)new CHECKCAST(cpg.addClass("java.lang.String")));
/* 612 */       il.append((CompoundInstruction)new PUSH(cpg, ((EqualityExpr)this._exp).getOp()));
/*     */     } else {
/*     */       
/* 615 */       translateFilter(classGen, methodGen);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Predicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */