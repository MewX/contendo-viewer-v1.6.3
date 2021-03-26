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
/*    */ public class LinearTransfer
/*    */   implements TransferFunction
/*    */ {
/*    */   public byte[] lutData;
/*    */   public float slope;
/*    */   public float intercept;
/*    */   
/*    */   public LinearTransfer(float slope, float intercept) {
/* 51 */     this.slope = slope;
/* 52 */     this.intercept = intercept;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void buildLutData() {
/* 60 */     this.lutData = new byte[256];
/*    */     
/* 62 */     float scaledInt = this.intercept * 255.0F + 0.5F;
/* 63 */     for (int j = 0; j <= 255; j++) {
/* 64 */       int value = (int)(this.slope * j + scaledInt);
/* 65 */       if (value < 0) {
/* 66 */         value = 0;
/*    */       }
/* 68 */       else if (value > 255) {
/* 69 */         value = 255;
/*    */       } 
/* 71 */       this.lutData[j] = (byte)(0xFF & value);
/*    */     } 
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
/*    */   public byte[] getLookupTable() {
/* 87 */     buildLutData();
/* 88 */     return this.lutData;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/LinearTransfer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */