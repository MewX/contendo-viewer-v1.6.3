/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodType;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
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
/*     */ final class LogicalExpr
/*     */   extends Expression
/*     */ {
/*     */   public static final int OR = 0;
/*     */   public static final int AND = 1;
/*     */   private final int _op;
/*     */   private Expression _left;
/*     */   private Expression _right;
/*  45 */   private static final String[] Ops = new String[] { "or", "and" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LogicalExpr(int op, Expression left, Expression right) {
/*  54 */     this._op = op;
/*  55 */     (this._left = left).setParent(this);
/*  56 */     (this._right = right).setParent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPositionCall() {
/*  64 */     return (this._left.hasPositionCall() || this._right.hasPositionCall());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasLastCall() {
/*  71 */     return (this._left.hasLastCall() || this._right.hasLastCall());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object evaluateAtCompileTime() {
/*  80 */     Object leftb = this._left.evaluateAtCompileTime();
/*  81 */     Object rightb = this._right.evaluateAtCompileTime();
/*     */ 
/*     */     
/*  84 */     if (leftb == null || rightb == null) {
/*  85 */       return null;
/*     */     }
/*     */     
/*  88 */     if (this._op == 1) {
/*  89 */       return (leftb == Boolean.TRUE && rightb == Boolean.TRUE) ? Boolean.TRUE : Boolean.FALSE;
/*     */     }
/*     */ 
/*     */     
/*  93 */     return (leftb == Boolean.TRUE || rightb == Boolean.TRUE) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOp() {
/* 103 */     return this._op;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParser(Parser parser) {
/* 111 */     super.setParser(parser);
/* 112 */     this._left.setParser(parser);
/* 113 */     this._right.setParser(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 120 */     return Ops[this._op] + '(' + this._left + ", " + this._right + ')';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 128 */     Type tleft = this._left.typeCheck(stable);
/* 129 */     Type tright = this._right.typeCheck(stable);
/*     */ 
/*     */     
/* 132 */     MethodType wantType = new MethodType(Type.Void, tleft, tright);
/* 133 */     MethodType haveType = lookupPrimop(stable, Ops[this._op], wantType);
/*     */ 
/*     */     
/* 136 */     if (haveType != null) {
/*     */       
/* 138 */       Type arg1 = haveType.argsType().elementAt(0);
/* 139 */       if (!arg1.identicalTo(tleft)) {
/* 140 */         this._left = new CastExpr(this._left, arg1);
/*     */       }
/* 142 */       Type arg2 = haveType.argsType().elementAt(1);
/* 143 */       if (!arg2.identicalTo(tright)) {
/* 144 */         this._right = new CastExpr(this._right, arg1);
/*     */       }
/* 146 */       return this._type = haveType.resultType();
/*     */     } 
/* 148 */     throw new TypeCheckError(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 155 */     translateDesynthesized(classGen, methodGen);
/* 156 */     synthesize(classGen, methodGen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/* 165 */     InstructionList il = methodGen.getInstructionList();
/* 166 */     SyntaxTreeNode parent = getParent();
/*     */ 
/*     */     
/* 169 */     if (this._op == 1) {
/*     */ 
/*     */       
/* 172 */       this._left.translateDesynthesized(classGen, methodGen);
/*     */ 
/*     */       
/* 175 */       InstructionHandle middle = il.append(InstructionConstants.NOP);
/*     */ 
/*     */       
/* 178 */       this._right.translateDesynthesized(classGen, methodGen);
/*     */ 
/*     */       
/* 181 */       InstructionHandle after = il.append(InstructionConstants.NOP);
/*     */ 
/*     */       
/* 184 */       this._falseList.append(this._right._falseList.append(this._left._falseList));
/*     */ 
/*     */ 
/*     */       
/* 188 */       if (this._left instanceof LogicalExpr && ((LogicalExpr)this._left).getOp() == 0) {
/*     */         
/* 190 */         this._left.backPatchTrueList(middle);
/*     */       }
/* 192 */       else if (this._left instanceof NotCall) {
/* 193 */         this._left.backPatchTrueList(middle);
/*     */       } else {
/*     */         
/* 196 */         this._trueList.append(this._left._trueList);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 201 */       if (this._right instanceof LogicalExpr && ((LogicalExpr)this._right).getOp() == 0) {
/*     */         
/* 203 */         this._right.backPatchTrueList(after);
/*     */       }
/* 205 */       else if (this._right instanceof NotCall) {
/* 206 */         this._right.backPatchTrueList(after);
/*     */       } else {
/*     */         
/* 209 */         this._trueList.append(this._right._trueList);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 215 */       this._left.translateDesynthesized(classGen, methodGen);
/*     */ 
/*     */ 
/*     */       
/* 219 */       BranchHandle branchHandle = il.append((BranchInstruction)new GOTO(null));
/*     */ 
/*     */       
/* 222 */       this._right.translateDesynthesized(classGen, methodGen);
/*     */       
/* 224 */       this._left._trueList.backPatch((InstructionHandle)branchHandle);
/* 225 */       this._left._falseList.backPatch(branchHandle.getNext());
/*     */       
/* 227 */       this._falseList.append(this._right._falseList);
/* 228 */       this._trueList.add((InstructionHandle)branchHandle).append(this._right._trueList);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LogicalExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */