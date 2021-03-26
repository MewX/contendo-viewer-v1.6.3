/*    */ package com.levigo.jbig2.util;
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
/*    */ public enum CombinationOperator
/*    */ {
/* 24 */   OR, AND, XOR, XNOR, REPLACE;
/*    */   
/*    */   public static CombinationOperator translateOperatorCodeToEnum(short paramShort) {
/* 27 */     switch (paramShort) {
/*    */       case 0:
/* 29 */         return OR;
/*    */       case 1:
/* 31 */         return AND;
/*    */       case 2:
/* 33 */         return XOR;
/*    */       case 3:
/* 35 */         return XNOR;
/*    */     } 
/* 37 */     return REPLACE;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/CombinationOperator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */