/*    */ package org.apache.pdfbox.text;
/*    */ 
/*    */ import java.util.Comparator;
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
/*    */ public class TextPositionComparator
/*    */   implements Comparator<TextPosition>
/*    */ {
/*    */   public int compare(TextPosition pos1, TextPosition pos2) {
/* 35 */     int cmp1 = Float.compare(pos1.getDir(), pos2.getDir());
/* 36 */     if (cmp1 != 0)
/*    */     {
/* 38 */       return cmp1;
/*    */     }
/*    */ 
/*    */     
/* 42 */     float x1 = pos1.getXDirAdj();
/* 43 */     float x2 = pos2.getXDirAdj();
/*    */     
/* 45 */     float pos1YBottom = pos1.getYDirAdj();
/* 46 */     float pos2YBottom = pos2.getYDirAdj();
/*    */ 
/*    */     
/* 49 */     float pos1YTop = pos1YBottom - pos1.getHeightDir();
/* 50 */     float pos2YTop = pos2YBottom - pos2.getHeightDir();
/*    */     
/* 52 */     float yDifference = Math.abs(pos1YBottom - pos2YBottom);
/*    */ 
/*    */     
/* 55 */     if (yDifference < 0.1D || (pos2YBottom >= pos1YTop && pos2YBottom <= pos1YBottom) || (pos1YBottom >= pos2YTop && pos1YBottom <= pos2YBottom))
/*    */     {
/*    */ 
/*    */       
/* 59 */       return Float.compare(x1, x2);
/*    */     }
/* 61 */     if (pos1YBottom < pos2YBottom)
/*    */     {
/* 63 */       return -1;
/*    */     }
/*    */ 
/*    */     
/* 67 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/text/TextPositionComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */