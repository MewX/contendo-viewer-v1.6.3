/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.BranchInstruction;
/*    */ import org.apache.bcel.generic.CompoundInstruction;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.GOTO;
/*    */ import org.apache.bcel.generic.InstructionConstants;
/*    */ import org.apache.bcel.generic.InstructionHandle;
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
/*    */ final class BooleanExpr
/*    */   extends Expression
/*    */ {
/*    */   private boolean _value;
/*    */   
/*    */   public BooleanExpr(boolean value) {
/* 41 */     this._value = value;
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 45 */     this._type = Type.Boolean;
/* 46 */     return this._type;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     return this._value ? "true()" : "false()";
/*    */   }
/*    */   
/*    */   public boolean getValue() {
/* 54 */     return this._value;
/*    */   }
/*    */   
/*    */   public boolean contextDependent() {
/* 58 */     return false;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 62 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 63 */     InstructionList il = methodGen.getInstructionList();
/* 64 */     il.append((CompoundInstruction)new PUSH(cpg, this._value));
/*    */   }
/*    */ 
/*    */   
/*    */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/* 69 */     InstructionList il = methodGen.getInstructionList();
/* 70 */     if (this._value) {
/* 71 */       il.append(InstructionConstants.NOP);
/*    */     } else {
/*    */       
/* 74 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO(null)));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/BooleanExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */