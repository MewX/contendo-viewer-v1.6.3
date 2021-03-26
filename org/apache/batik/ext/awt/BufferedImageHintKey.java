/*    */ package org.apache.batik.ext.awt;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.lang.ref.Reference;
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
/*    */ final class BufferedImageHintKey
/*    */   extends RenderingHints.Key
/*    */ {
/*    */   BufferedImageHintKey(int number) {
/* 33 */     super(number);
/*    */   }
/*    */   public boolean isCompatibleValue(Object val) {
/* 36 */     if (val == null) {
/* 37 */       return true;
/*    */     }
/* 39 */     if (!(val instanceof Reference))
/* 40 */       return false; 
/* 41 */     Reference ref = (Reference)val;
/* 42 */     val = ref.get();
/* 43 */     if (val == null)
/* 44 */       return true; 
/* 45 */     if (val instanceof java.awt.image.BufferedImage) {
/* 46 */       return true;
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/BufferedImageHintKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */