/*    */ package org.apache.pdfbox.pdmodel.font;
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
/*    */ 
/*    */ public final class FontMappers
/*    */ {
/*    */   private static FontMapper instance;
/*    */   
/*    */   private static class DefaultFontMapper
/*    */   {
/* 36 */     private static final FontMapper INSTANCE = new FontMapperImpl();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static FontMapper instance() {
/* 44 */     if (instance == null)
/*    */     {
/* 46 */       instance = DefaultFontMapper.INSTANCE;
/*    */     }
/* 48 */     return instance;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void set(FontMapper fontMapper) {
/* 56 */     instance = fontMapper;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FontMappers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */