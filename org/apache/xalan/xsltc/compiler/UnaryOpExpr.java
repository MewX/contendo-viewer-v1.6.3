/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodType;
/*    */ import org.apache.xalan.xsltc.compiler.util.Type;
/*    */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class UnaryOpExpr
/*    */   extends Expression
/*    */ {
/*    */   private Expression _left;
/*    */   
/*    */   public UnaryOpExpr(Expression left) {
/* 37 */     (this._left = left).setParent(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasPositionCall() {
/* 45 */     return this._left.hasPositionCall();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasLastCall() {
/* 52 */     return this._left.hasLastCall();
/*    */   }
/*    */   
/*    */   public void setParser(Parser parser) {
/* 56 */     super.setParser(parser);
/* 57 */     this._left.setParser(parser);
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 61 */     Type tleft = this._left.typeCheck(stable);
/* 62 */     MethodType ptype = lookupPrimop(stable, "u-", new MethodType(Type.Void, tleft));
/*    */ 
/*    */ 
/*    */     
/* 66 */     if (ptype != null) {
/* 67 */       Type arg1 = ptype.argsType().elementAt(0);
/* 68 */       if (!arg1.identicalTo(tleft)) {
/* 69 */         this._left = new CastExpr(this._left, arg1);
/*    */       }
/* 71 */       return this._type = ptype.resultType();
/*    */     } 
/*    */     
/* 74 */     throw new TypeCheckError(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 78 */     return "u-(" + this._left + ')';
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 82 */     InstructionList il = methodGen.getInstructionList();
/* 83 */     this._left.translate(classGen, methodGen);
/* 84 */     il.append(this._type.NEG());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/UnaryOpExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */