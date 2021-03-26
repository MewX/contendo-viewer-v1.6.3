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
/*    */ 
/*    */ public class RangeRecord
/*    */ {
/*    */   private int start;
/*    */   private int end;
/*    */   private int startCoverageIndex;
/*    */   
/*    */   public RangeRecord(RandomAccessFile raf) throws IOException {
/* 38 */     this.start = raf.readUnsignedShort();
/* 39 */     this.end = raf.readUnsignedShort();
/* 40 */     this.startCoverageIndex = raf.readUnsignedShort();
/*    */   }
/*    */   
/*    */   public boolean isInRange(int glyphId) {
/* 44 */     return (this.start <= glyphId && glyphId <= this.end);
/*    */   }
/*    */   
/*    */   public int getCoverageIndex(int glyphId) {
/* 48 */     if (isInRange(glyphId)) {
/* 49 */       return this.startCoverageIndex + glyphId - this.start;
/*    */     }
/* 51 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/RangeRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */