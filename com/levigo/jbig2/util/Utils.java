/*    */ package com.levigo.jbig2.util;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.geom.Rectangle2D;
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
/*    */ public class Utils
/*    */ {
/*    */   private static final int BIG_ENOUGH_INT = 16384;
/*    */   private static final double BIG_ENOUGH_FLOOR = 16384.0D;
/*    */   private static final double BIG_ENOUGH_ROUND = 16384.5D;
/*    */   
/*    */   public static Rectangle enlargeRectToGrid(Rectangle2D paramRectangle2D) {
/* 36 */     int i = floor(paramRectangle2D.getMinX());
/* 37 */     int j = floor(paramRectangle2D.getMinY());
/* 38 */     int k = ceil(paramRectangle2D.getMaxX());
/* 39 */     int m = ceil(paramRectangle2D.getMaxY());
/* 40 */     return new Rectangle(i, j, k - i, m - j);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Rectangle2D dilateRect(Rectangle2D paramRectangle2D, double paramDouble1, double paramDouble2) {
/* 51 */     return new Rectangle2D.Double(paramRectangle2D.getX() - paramDouble1, paramRectangle2D.getY() - paramDouble2, paramRectangle2D.getWidth() + 2.0D * paramDouble1, paramRectangle2D.getHeight() + 2.0D * paramDouble2);
/*    */   }
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
/*    */   public static double clamp(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 64 */     return Math.min(paramDouble3, Math.max(paramDouble1, paramDouble2));
/*    */   }
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
/*    */   public static int floor(double paramDouble) {
/* 78 */     return (int)(paramDouble + 16384.0D) - 16384;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int round(double paramDouble) {
/* 88 */     return (int)(paramDouble + 16384.5D) - 16384;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int ceil(double paramDouble) {
/* 98 */     return 16384 - (int)(16384.0D - paramDouble);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */