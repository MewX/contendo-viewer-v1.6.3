/*    */ package org.apache.xmlgraphics.java2d.color;
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
/*    */ public enum RenderingIntent
/*    */ {
/* 30 */   PERCEPTUAL(0),
/*    */   
/* 32 */   RELATIVE_COLORIMETRIC(1),
/*    */   
/* 34 */   ABSOLUTE_COLORIMETRIC(3),
/*    */   
/* 36 */   SATURATION(2),
/*    */   
/* 38 */   AUTO(4);
/*    */   
/*    */   private int intValue;
/*    */   
/*    */   RenderingIntent(int value) {
/* 43 */     this.intValue = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIntegerValue() {
/* 52 */     return this.intValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static RenderingIntent fromICCValue(int value) {
/* 62 */     switch (value) { case 0:
/* 63 */         return PERCEPTUAL;
/* 64 */       case 1: return RELATIVE_COLORIMETRIC;
/* 65 */       case 3: return ABSOLUTE_COLORIMETRIC;
/* 66 */       case 2: return SATURATION; }
/*    */     
/* 68 */     throw new IllegalArgumentException("Invalid value for rendering intent: " + value);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/RenderingIntent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */