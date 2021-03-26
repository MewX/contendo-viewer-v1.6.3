/*    */ package com.levigo.jbig2.image;
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
/*    */ public enum FilterType
/*    */ {
/*    */   private static FilterType defaultFilter;
/* 25 */   Bessel,
/* 26 */   Blackman,
/* 27 */   Box,
/* 28 */   Catrom,
/* 29 */   Cubic,
/* 30 */   Gaussian,
/* 31 */   Hamming,
/* 32 */   Hanning,
/* 33 */   Hermite,
/* 34 */   Lanczos,
/* 35 */   Mitchell,
/* 36 */   Point,
/* 37 */   Quadratic,
/* 38 */   Sinc,
/* 39 */   Triangle;
/*    */   static {
/* 41 */     defaultFilter = Triangle;
/*    */   }
/*    */   public static void setDefaultFilterType(FilterType paramFilterType) {
/* 44 */     defaultFilter = paramFilterType;
/*    */   }
/*    */   
/*    */   public static FilterType getDefaultFilterType() {
/* 48 */     return defaultFilter;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/FilterType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */