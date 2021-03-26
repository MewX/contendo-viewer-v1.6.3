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
/*    */ public class TableTransfer
/*    */   implements TransferFunction
/*    */ {
/*    */   public byte[] lutData;
/*    */   public int[] tableValues;
/*    */   private int n;
/*    */   
/*    */   public TableTransfer(int[] tableValues) {
/* 53 */     this.tableValues = tableValues;
/* 54 */     this.n = tableValues.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void buildLutData() {
/* 63 */     this.lutData = new byte[256];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 72 */     for (int j = 0; j <= 255; j++) {
/* 73 */       float fi = (j * (this.n - 1)) / 255.0F;
/* 74 */       int ffi = (int)Math.floor(fi);
/* 75 */       int cfi = (ffi + 1 > this.n - 1) ? (this.n - 1) : (ffi + 1);
/* 76 */       float r = fi - ffi;
/* 77 */       this.lutData[j] = (byte)((int)(this.tableValues[ffi] + r * (this.tableValues[cfi] - this.tableValues[ffi])) & 0xFF);
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
/*    */   
/*    */   public byte[] getLookupTable() {
/* 94 */     buildLutData();
/* 95 */     return this.lutData;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/TableTransfer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */