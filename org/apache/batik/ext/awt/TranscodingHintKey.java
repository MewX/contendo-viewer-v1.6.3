/*    */ package org.apache.batik.ext.awt;
/*    */ 
/*    */ import java.awt.RenderingHints;
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
/*    */ final class TranscodingHintKey
/*    */   extends RenderingHints.Key
/*    */ {
/*    */   TranscodingHintKey(int number) {
/* 32 */     super(number);
/*    */   }
/*    */   public boolean isCompatibleValue(Object val) {
/* 35 */     boolean isCompatible = true;
/* 36 */     if (val != null && !(val instanceof String)) {
/* 37 */       isCompatible = false;
/*    */     }
/* 39 */     return isCompatible;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/TranscodingHintKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */