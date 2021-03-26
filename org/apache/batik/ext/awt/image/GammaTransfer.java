/*    */ package org.apache.batik.ext.awt.image;
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
/*    */ public class GammaTransfer
/*    */   implements TransferFunction
/*    */ {
/*    */   public byte[] lutData;
/*    */   public float amplitude;
/*    */   public float exponent;
/*    */   public float offset;
/*    */   
/*    */   public GammaTransfer(float amplitude, float exponent, float offset) {
/* 57 */     this.amplitude = amplitude;
/* 58 */     this.exponent = exponent;
/* 59 */     this.offset = offset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void buildLutData() {
/* 67 */     this.lutData = new byte[256];
/*    */     
/* 69 */     for (int j = 0; j <= 255; j++) {
/* 70 */       int v = (int)Math.round(255.0D * (this.amplitude * Math.pow((j / 255.0F), this.exponent) + this.offset));
/* 71 */       if (v > 255) {
/* 72 */         v = -1;
/*    */       }
/* 74 */       else if (v < 0) {
/* 75 */         v = 0;
/*    */       } 
/* 77 */       this.lutData[j] = (byte)(v & 0xFF);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getLookupTable() {
/* 87 */     buildLutData();
/* 88 */     return this.lutData;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/GammaTransfer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */