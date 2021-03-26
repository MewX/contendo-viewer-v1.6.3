/*    */ package com.levigo.jbig2;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Rectangle;
/*    */ import javax.imageio.ImageReadParam;
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
/*    */ public class JBIG2ReadParam
/*    */   extends ImageReadParam
/*    */ {
/*    */   public JBIG2ReadParam() {
/* 32 */     this(1, 1, 0, 0, (Rectangle)null, (Dimension)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public JBIG2ReadParam(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Rectangle paramRectangle, Dimension paramDimension) {
/* 37 */     this.canSetSourceRenderSize = true;
/* 38 */     this.sourceRegion = paramRectangle;
/* 39 */     this.sourceRenderSize = paramDimension;
/*    */     
/* 41 */     if (paramInt1 < 1 || paramInt2 < 1) {
/* 42 */       throw new IllegalArgumentException("Illegal subsampling factor: shall be 1 or greater; but was  sourceXSubsampling=" + paramInt1 + ", sourceYSubsampling=" + paramInt2);
/*    */     }
/*    */ 
/*    */     
/* 46 */     setSourceSubsampling(paramInt1, paramInt2, paramInt3, paramInt4);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2ReadParam.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */