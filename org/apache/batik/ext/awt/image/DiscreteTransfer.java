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
/*    */ public class DiscreteTransfer
/*    */   implements TransferFunction
/*    */ {
/*    */   public byte[] lutData;
/*    */   public int[] tableValues;
/*    */   private int n;
/*    */   
/*    */   public DiscreteTransfer(int[] tableValues) {
/* 51 */     this.tableValues = tableValues;
/* 52 */     this.n = tableValues.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void buildLutData() {
/* 60 */     this.lutData = new byte[256];
/*    */     
/* 62 */     for (int j = 0; j <= 255; j++) {
/* 63 */       int i = (int)Math.floor(((j * this.n) / 255.0F));
/* 64 */       if (i == this.n) {
/* 65 */         i = this.n - 1;
/*    */       }
/* 67 */       this.lutData[j] = (byte)(this.tableValues[i] & 0xFF);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getLookupTable() {
/* 76 */     buildLutData();
/* 77 */     return this.lutData;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/DiscreteTransfer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */