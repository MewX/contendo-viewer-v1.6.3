/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import org.apache.bcel.generic.ALOAD;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.ILOAD;
/*    */ import org.apache.bcel.generic.ISTORE;
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
/*    */ public final class MatchGenerator
/*    */   extends MethodGenerator
/*    */ {
/* 35 */   private static int CURRENT_INDEX = 1;
/*    */   
/* 37 */   private int _iteratorIndex = -1;
/*    */   
/*    */   private final Instruction _iloadCurrent;
/*    */   
/*    */   private final Instruction _istoreCurrent;
/*    */   
/*    */   private Instruction _aloadDom;
/*    */ 
/*    */   
/*    */   public MatchGenerator(int access_flags, Type return_type, Type[] arg_types, String[] arg_names, String method_name, String class_name, InstructionList il, ConstantPoolGen cp) {
/* 47 */     super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
/*    */ 
/*    */     
/* 50 */     this._iloadCurrent = (Instruction)new ILOAD(CURRENT_INDEX);
/* 51 */     this._istoreCurrent = (Instruction)new ISTORE(CURRENT_INDEX);
/*    */   }
/*    */   
/*    */   public Instruction loadCurrentNode() {
/* 55 */     return this._iloadCurrent;
/*    */   }
/*    */   
/*    */   public Instruction storeCurrentNode() {
/* 59 */     return this._istoreCurrent;
/*    */   }
/*    */   
/*    */   public int getHandlerIndex() {
/* 63 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Instruction loadDOM() {
/* 70 */     return this._aloadDom;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDomIndex(int domIndex) {
/* 77 */     this._aloadDom = (Instruction)new ALOAD(domIndex);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIteratorIndex() {
/* 84 */     return this._iteratorIndex;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setIteratorIndex(int iteratorIndex) {
/* 91 */     this._iteratorIndex = iteratorIndex;
/*    */   }
/*    */   
/*    */   public int getLocalIndex(String name) {
/* 95 */     if (name.equals("current")) {
/* 96 */       return CURRENT_INDEX;
/*    */     }
/* 98 */     return super.getLocalIndex(name);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/MatchGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */