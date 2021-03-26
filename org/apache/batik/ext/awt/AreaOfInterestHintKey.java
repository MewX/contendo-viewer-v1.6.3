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
/*    */ 
/*    */ final class AreaOfInterestHintKey
/*    */   extends RenderingHints.Key
/*    */ {
/*    */   AreaOfInterestHintKey(int number) {
/* 33 */     super(number);
/*    */   }
/*    */   public boolean isCompatibleValue(Object val) {
/* 36 */     boolean isCompatible = true;
/* 37 */     if (val != null && !(val instanceof java.awt.Shape)) {
/* 38 */       isCompatible = false;
/*    */     }
/* 40 */     return isCompatible;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/AreaOfInterestHintKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */