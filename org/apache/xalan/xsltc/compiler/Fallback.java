/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.InstructionList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Fallback
/*    */   extends Instruction
/*    */ {
/*    */   private boolean _active = false;
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 40 */     if (this._active) {
/* 41 */       return typeCheckContents(stable);
/*    */     }
/*    */     
/* 44 */     return Type.Void;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void activate() {
/* 52 */     this._active = true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 56 */     return "fallback";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void parseContents(Parser parser) {
/* 64 */     if (this._active) parseChildren(parser);
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 72 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 73 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 75 */     if (this._active) translateContents(classGen, methodGen); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Fallback.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */