/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.CompoundInstruction;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.bcel.generic.PUSH;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
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
/*    */ final class RealExpr
/*    */   extends Expression
/*    */ {
/*    */   private double _value;
/*    */   
/*    */   public RealExpr(double value) {
/* 38 */     this._value = value;
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 42 */     return this._type = Type.Real;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 46 */     return "real-expr(" + this._value + ')';
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 50 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 51 */     InstructionList il = methodGen.getInstructionList();
/* 52 */     il.append((CompoundInstruction)new PUSH(cpg, this._value));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/RealExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */