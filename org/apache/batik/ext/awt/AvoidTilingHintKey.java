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
/*    */ public class AvoidTilingHintKey
/*    */   extends RenderingHints.Key
/*    */ {
/*    */   AvoidTilingHintKey(int number) {
/* 31 */     super(number);
/*    */   }
/*    */   public boolean isCompatibleValue(Object v) {
/* 34 */     if (v == null) return false; 
/* 35 */     return (v == RenderingHintsKeyExt.VALUE_AVOID_TILE_PAINTING_ON || v == RenderingHintsKeyExt.VALUE_AVOID_TILE_PAINTING_OFF || v == RenderingHintsKeyExt.VALUE_AVOID_TILE_PAINTING_DEFAULT);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/AvoidTilingHintKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */