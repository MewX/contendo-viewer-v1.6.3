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
/*    */ public class Feature
/*    */ {
/*    */   private int featureParams;
/*    */   private int lookupCount;
/*    */   private int[] lookupListIndex;
/*    */   
/*    */   protected Feature(RandomAccessFile raf, int offset) throws IOException {
/* 37 */     raf.seek(offset);
/* 38 */     this.featureParams = raf.readUnsignedShort();
/* 39 */     this.lookupCount = raf.readUnsignedShort();
/* 40 */     this.lookupListIndex = new int[this.lookupCount];
/* 41 */     for (int i = 0; i < this.lookupCount; i++) {
/* 42 */       this.lookupListIndex[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getLookupCount() {
/* 47 */     return this.lookupCount;
/*    */   }
/*    */   
/*    */   public int getLookupListIndex(int i) {
/* 51 */     return this.lookupListIndex[i];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Feature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */