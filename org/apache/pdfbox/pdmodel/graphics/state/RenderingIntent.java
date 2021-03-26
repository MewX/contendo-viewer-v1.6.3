/*    */ package org.apache.pdfbox.pdmodel.graphics.state;
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
/* 30 */   ABSOLUTE_COLORIMETRIC("AbsoluteColorimetric"),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   RELATIVE_COLORIMETRIC("RelativeColorimetric"),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   SATURATION("Saturation"),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   PERCEPTUAL("Perceptual");
/*    */   private final String value;
/*    */   
/*    */   public static RenderingIntent fromString(String value) {
/* 49 */     for (RenderingIntent instance : values()) {
/*    */       
/* 51 */       if (instance.value.equals(value))
/*    */       {
/* 53 */         return instance;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 58 */     return RELATIVE_COLORIMETRIC;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   RenderingIntent(String value) {
/* 65 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String stringValue() {
/* 73 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/state/RenderingIntent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */