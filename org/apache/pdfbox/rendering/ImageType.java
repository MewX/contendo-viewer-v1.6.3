/*    */ package org.apache.pdfbox.rendering;
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
/*    */ public enum ImageType
/*    */ {
/* 27 */   BINARY
/*    */   {
/*    */     
/*    */     int toBufferedImageType()
/*    */     {
/* 32 */       return 12;
/*    */     }
/*    */   },
/*    */ 
/*    */   
/* 37 */   GRAY
/*    */   {
/*    */     
/*    */     int toBufferedImageType()
/*    */     {
/* 42 */       return 10;
/*    */     }
/*    */   },
/*    */ 
/*    */   
/* 47 */   RGB
/*    */   {
/*    */     
/*    */     int toBufferedImageType()
/*    */     {
/* 52 */       return 1;
/*    */     }
/*    */   },
/*    */ 
/*    */   
/* 57 */   ARGB
/*    */   {
/*    */     
/*    */     int toBufferedImageType()
/*    */     {
/* 62 */       return 2;
/*    */     }
/*    */   };
/*    */   
/*    */   abstract int toBufferedImageType();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/ImageType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */