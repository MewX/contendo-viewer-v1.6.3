/*    */ package org.apache.pdfbox.pdmodel.font;
/*    */ 
/*    */ import org.apache.fontbox.FontBoxFont;
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
/*    */ public class FontMapping<T extends FontBoxFont>
/*    */ {
/*    */   private final T font;
/*    */   private final boolean isFallback;
/*    */   
/*    */   public FontMapping(T font, boolean isFallback) {
/* 34 */     this.font = font;
/* 35 */     this.isFallback = isFallback;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T getFont() {
/* 43 */     return this.font;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFallback() {
/* 52 */     return this.isFallback;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FontMapping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */