/*    */ package com.levigo.jbig2.util;
/*    */ 
/*    */ import com.levigo.jbig2.Bitmap;
/*    */ import com.levigo.jbig2.TestImage;
/*    */ import com.levigo.jbig2.image.Bitmaps;
/*    */ import java.util.List;
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
/*    */ class DictionaryViewer
/*    */ {
/*    */   public static void show(Bitmap paramBitmap) {
/* 33 */     new TestImage(Bitmaps.asBufferedImage(paramBitmap));
/*    */   }
/*    */   
/*    */   public static void show(List<Bitmap> paramList) {
/* 37 */     int i = 0;
/* 38 */     int j = 0;
/*    */     
/* 40 */     for (Bitmap bitmap1 : paramList) {
/* 41 */       i += bitmap1.getWidth();
/*    */       
/* 43 */       if (bitmap1.getHeight() > j) {
/* 44 */         j = bitmap1.getHeight();
/*    */       }
/*    */     } 
/*    */     
/* 48 */     Bitmap bitmap = new Bitmap(i, j);
/*    */     
/* 50 */     int k = 0;
/*    */     
/* 52 */     for (Bitmap bitmap1 : paramList) {
/* 53 */       Bitmaps.blit(bitmap1, bitmap, k, 0, CombinationOperator.REPLACE);
/* 54 */       k += bitmap1.getWidth();
/*    */     } 
/*    */     
/* 57 */     new TestImage(Bitmaps.asBufferedImage(bitmap));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/DictionaryViewer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */