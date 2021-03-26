/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
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
/*     */ final class BinOpExpr
/*     */   extends Expression
/*     */ {
/*     */   public static final int PLUS = 0;
/*     */   public static final int MINUS = 1;
/*     */   public static final int TIMES = 2;
/*     */   public static final int DIV = 3;
/*     */   public static final int MOD = 4;
/*  41 */   private static final String[] Ops = new String[] { "+", "-", "*", "/", "%" };
/*     */   
/*     */   private int _op;
/*     */   
/*     */   private Expression _left;
/*     */   private Expression _right;
/*     */   
/*     */   public BinOpExpr(int op, Expression left, Expression right) {
/*  49 */     this._op = op;
/*  50 */     (this._left = left).setParent(this);
/*  51 */     (this._right = right).setParent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPositionCall() {
/*  59 */     if (this._left.hasPositionCall()) return true; 
/*  60 */     if (this._right.hasPositionCall()) return true; 
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasLastCall() {
/*  68 */     return (this._left.hasLastCall() || this._right.hasLastCall());
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  72 */     super.setParser(parser);
/*  73 */     this._left.setParser(parser);
/*  74 */     this._right.setParser(parser);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  78 */     Type tleft = this._left.typeCheck(stable);
/*  79 */     Type tright = this._right.typeCheck(stable);
/*  80 */     MethodType ptype = lookupPrimop(stable, Ops[this._op], new MethodType(Type.Void, tleft, tright));
/*     */ 
/*     */     
/*  83 */     if (ptype != null) {
/*  84 */       Type arg1 = ptype.argsType().elementAt(0);
/*  85 */       if (!arg1.identicalTo(tleft)) {
/*  86 */         this._left = new CastExpr(this._left, arg1);
/*     */       }
/*  88 */       Type arg2 = ptype.argsType().elementAt(1);
/*  89 */       if (!arg2.identicalTo(tright)) {
/*  90 */         this._right = new CastExpr(this._right, arg1);
/*     */       }
/*  92 */       return this._type = ptype.resultType();
/*     */     } 
/*  94 */     throw new TypeCheckError(this);
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  98 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 100 */     this._left.translate(classGen, methodGen);
/* 101 */     this._right.translate(classGen, methodGen);
/*     */     
/* 103 */     switch (this._op) {
/*     */       case 0:
/* 105 */         il.append(this._type.ADD());
/*     */         return;
/*     */       case 1:
/* 108 */         il.append(this._type.SUB());
/*     */         return;
/*     */       case 2:
/* 111 */         il.append(this._type.MUL());
/*     */         return;
/*     */       case 3:
/* 114 */         il.append(this._type.DIV());
/*     */         return;
/*     */       case 4:
/* 117 */         il.append(this._type.REM());
/*     */         return;
/*     */     } 
/* 120 */     ErrorMsg msg = new ErrorMsg("ILLEGAL_BINARY_OP_ERR", this);
/* 121 */     getParser().reportError(3, msg);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 126 */     return Ops[this._op] + '(' + this._left + ", " + this._right + ')';
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/BinOpExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */