/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import org.apache.bcel.generic.CompoundInstruction;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionConstants;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.bcel.generic.PUSH;
/*    */ import org.apache.bcel.generic.Type;
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
/*    */ public final class VoidType
/*    */   extends Type
/*    */ {
/*    */   public String toString() {
/* 35 */     return "void";
/*    */   }
/*    */   
/*    */   public boolean identicalTo(Type other) {
/* 39 */     return (this == other);
/*    */   }
/*    */   
/*    */   public String toSignature() {
/* 43 */     return "V";
/*    */   }
/*    */   
/*    */   public Type toJCType() {
/* 47 */     return null;
/*    */   }
/*    */   
/*    */   public Instruction POP() {
/* 51 */     return InstructionConstants.NOP;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/* 63 */     if (type == Type.String) {
/* 64 */       translateTo(classGen, methodGen, (StringType)type);
/*    */     } else {
/*    */       
/* 67 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*    */       
/* 69 */       classGen.getParser().reportError(2, err);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/* 80 */     InstructionList il = methodGen.getInstructionList();
/* 81 */     il.append((CompoundInstruction)new PUSH(classGen.getConstantPool(), ""));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 90 */     if (!clazz.getName().equals("void")) {
/* 91 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*    */       
/* 93 */       classGen.getParser().reportError(2, err);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/VoidType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */