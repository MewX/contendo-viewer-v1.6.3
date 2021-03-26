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
/*    */ public final class FilterGenerator
/*    */   extends ClassGenerator
/*    */ {
/* 34 */   private static int TRANSLET_INDEX = 5;
/*    */ 
/*    */   
/*    */   private final Instruction _aloadTranslet;
/*    */ 
/*    */   
/*    */   public FilterGenerator(String className, String superClassName, String fileName, int accessFlags, String[] interfaces, Stylesheet stylesheet) {
/* 41 */     super(className, superClassName, fileName, accessFlags, interfaces, stylesheet);
/*    */ 
/*    */     
/* 44 */     this._aloadTranslet = (Instruction)new ALOAD(TRANSLET_INDEX);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final Instruction loadTranslet() {
/* 52 */     return this._aloadTranslet;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isExternal() {
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/FilterGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */