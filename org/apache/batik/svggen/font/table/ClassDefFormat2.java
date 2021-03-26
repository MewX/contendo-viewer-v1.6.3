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
/*    */ public class ClassDefFormat2
/*    */   extends ClassDef
/*    */ {
/*    */   private int classRangeCount;
/*    */   private RangeRecord[] classRangeRecords;
/*    */   
/*    */   public ClassDefFormat2(RandomAccessFile raf) throws IOException {
/* 36 */     this.classRangeCount = raf.readUnsignedShort();
/* 37 */     this.classRangeRecords = new RangeRecord[this.classRangeCount];
/* 38 */     for (int i = 0; i < this.classRangeCount; i++) {
/* 39 */       this.classRangeRecords[i] = new RangeRecord(raf);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 44 */     return 2;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/ClassDefFormat2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */