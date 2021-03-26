/*    */ package org.apache.batik.svggen.font.table;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
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
/*    */ public class LangSys
/*    */ {
/*    */   private int lookupOrder;
/*    */   private int reqFeatureIndex;
/*    */   private int featureCount;
/*    */   private int[] featureIndex;
/*    */   
/*    */   protected LangSys(RandomAccessFile raf) throws IOException {
/* 38 */     this.lookupOrder = raf.readUnsignedShort();
/* 39 */     this.reqFeatureIndex = raf.readUnsignedShort();
/* 40 */     this.featureCount = raf.readUnsignedShort();
/* 41 */     this.featureIndex = new int[this.featureCount];
/* 42 */     for (int i = 0; i < this.featureCount; i++) {
/* 43 */       this.featureIndex[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   protected boolean isFeatureIndexed(int n) {
/* 48 */     for (int i = 0; i < this.featureCount; i++) {
/* 49 */       if (this.featureIndex[i] == n) {
/* 50 */         return true;
/*    */       }
/*    */     } 
/* 53 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/LangSys.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */