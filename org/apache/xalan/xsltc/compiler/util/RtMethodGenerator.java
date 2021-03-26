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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RtMethodGenerator
/*    */   extends MethodGenerator
/*    */ {
/*    */   private static final int HANDLER_INDEX = 2;
/*    */   private final Instruction _astoreHandler;
/*    */   private final Instruction _aloadHandler;
/*    */   
/*    */   public RtMethodGenerator(int access_flags, Type return_type, Type[] arg_types, String[] arg_names, String method_name, String class_name, InstructionList il, ConstantPoolGen cp) {
/* 44 */     super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
/*    */ 
/*    */     
/* 47 */     this._astoreHandler = (Instruction)new ASTORE(2);
/* 48 */     this._aloadHandler = (Instruction)new ALOAD(2);
/*    */   }
/*    */   
/*    */   public int getIteratorIndex() {
/* 52 */     return -1;
/*    */   }
/*    */   
/*    */   public final Instruction storeHandler() {
/* 56 */     return this._astoreHandler;
/*    */   }
/*    */   
/*    */   public final Instruction loadHandler() {
/* 60 */     return this._aloadHandler;
/*    */   }
/*    */   
/*    */   public int getLocalIndex(String name) {
/* 64 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/RtMethodGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */