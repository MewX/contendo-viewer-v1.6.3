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
/*    */ public class FeatureRecord
/*    */ {
/*    */   private int tag;
/*    */   private int offset;
/*    */   
/*    */   public FeatureRecord(RandomAccessFile raf) throws IOException {
/* 36 */     this.tag = raf.readInt();
/* 37 */     this.offset = raf.readUnsignedShort();
/*    */   }
/*    */   
/*    */   public int getTag() {
/* 41 */     return this.tag;
/*    */   }
/*    */   
/*    */   public int getOffset() {
/* 45 */     return this.offset;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/FeatureRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */