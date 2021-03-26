/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import org.apache.bcel.generic.ALOAD;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.xalan.xsltc.compiler.Stylesheet;
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
/*    */ public final class NodeSortRecordGenerator
/*    */   extends ClassGenerator
/*    */ {
/*    */   private static final int TRANSLET_INDEX = 4;
/*    */   private final Instruction _aloadTranslet;
/*    */   
/*    */   public NodeSortRecordGenerator(String className, String superClassName, String fileName, int accessFlags, String[] interfaces, Stylesheet stylesheet) {
/* 40 */     super(className, superClassName, fileName, accessFlags, interfaces, stylesheet);
/*    */     
/* 42 */     this._aloadTranslet = (Instruction)new ALOAD(4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Instruction loadTranslet() {
/* 50 */     return this._aloadTranslet;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isExternal() {
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/NodeSortRecordGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */