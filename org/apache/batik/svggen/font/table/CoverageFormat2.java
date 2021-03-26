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
/*    */ public class CoverageFormat2
/*    */   extends Coverage
/*    */ {
/*    */   private int rangeCount;
/*    */   private RangeRecord[] rangeRecords;
/*    */   
/*    */   protected CoverageFormat2(RandomAccessFile raf) throws IOException {
/* 36 */     this.rangeCount = raf.readUnsignedShort();
/* 37 */     this.rangeRecords = new RangeRecord[this.rangeCount];
/* 38 */     for (int i = 0; i < this.rangeCount; i++) {
/* 39 */       this.rangeRecords[i] = new RangeRecord(raf);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 44 */     return 2;
/*    */   }
/*    */   
/*    */   public int findGlyph(int glyphId) {
/* 48 */     for (int i = 0; i < this.rangeCount; i++) {
/* 49 */       int n = this.rangeRecords[i].getCoverageIndex(glyphId);
/* 50 */       if (n > -1) {
/* 51 */         return n;
/*    */       }
/*    */     } 
/* 54 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CoverageFormat2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */