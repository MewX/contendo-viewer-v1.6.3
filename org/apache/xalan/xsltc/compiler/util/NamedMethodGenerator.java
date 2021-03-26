/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import org.apache.bcel.generic.ALOAD;
/*    */ import org.apache.bcel.generic.ASTORE;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionList;
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
/*    */ 
/*    */ 
/*    */ public final class NamedMethodGenerator
/*    */   extends MethodGenerator
/*    */ {
/* 36 */   protected static int CURRENT_INDEX = 4;
/*    */ 
/*    */ 
/*    */   
/*    */   private static final int PARAM_START_INDEX = 5;
/*    */ 
/*    */ 
/*    */   
/*    */   public NamedMethodGenerator(int access_flags, Type return_type, Type[] arg_types, String[] arg_names, String method_name, String class_name, InstructionList il, ConstantPoolGen cp) {
/* 45 */     super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLocalIndex(String name) {
/* 50 */     if (name.equals("current")) {
/* 51 */       return CURRENT_INDEX;
/*    */     }
/* 53 */     return super.getLocalIndex(name);
/*    */   }
/*    */   
/*    */   public Instruction loadParameter(int index) {
/* 57 */     return (Instruction)new ALOAD(index + 5);
/*    */   }
/*    */   
/*    */   public Instruction storeParameter(int index) {
/* 61 */     return (Instruction)new ASTORE(index + 5);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/NamedMethodGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */