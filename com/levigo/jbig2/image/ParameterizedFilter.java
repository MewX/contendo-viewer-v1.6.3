/*    */ package com.levigo.jbig2.image;
/*    */ 
/*    */ import com.levigo.jbig2.util.Utils;
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
/*    */ class ParameterizedFilter
/*    */ {
/*    */   final Filter filter;
/*    */   final double scale;
/*    */   final double support;
/*    */   final int width;
/*    */   
/*    */   public ParameterizedFilter(Filter paramFilter, double paramDouble) {
/* 24 */     this.filter = paramFilter;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.scale = paramFilter.blur * Math.max(1.0D, 1.0D / paramDouble);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     this.support = Math.max(0.5D, this.scale * paramFilter.support);
/* 37 */     this.width = (int)Math.ceil(2.0D * this.support);
/*    */   }
/*    */   
/*    */   public ParameterizedFilter(Filter paramFilter, double paramDouble1, double paramDouble2, int paramInt) {
/* 41 */     this.filter = paramFilter;
/* 42 */     this.scale = paramDouble1;
/* 43 */     this.support = paramDouble2;
/* 44 */     this.width = paramInt;
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
/*    */   
/*    */   public double eval(double paramDouble, int paramInt) {
/* 59 */     return this.filter.fWindowed((paramInt + 0.5D - paramDouble) / this.scale);
/*    */   }
/*    */   
/*    */   public int minIndex(double paramDouble) {
/* 63 */     return Utils.floor(paramDouble - this.support);
/*    */   }
/*    */   
/*    */   public int maxIndex(double paramDouble) {
/* 67 */     return Utils.ceil(paramDouble + this.support);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/ParameterizedFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */