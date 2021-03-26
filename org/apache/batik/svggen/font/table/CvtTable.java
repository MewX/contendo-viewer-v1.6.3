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
/*    */ public class CvtTable
/*    */   implements Table
/*    */ {
/*    */   private short[] values;
/*    */   
/*    */   protected CvtTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 33 */     raf.seek(de.getOffset());
/* 34 */     int len = de.getLength() / 2;
/* 35 */     this.values = new short[len];
/* 36 */     for (int i = 0; i < len; i++) {
/* 37 */       this.values[i] = raf.readShort();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getType() {
/* 42 */     return 1668707360;
/*    */   }
/*    */   
/*    */   public short[] getValues() {
/* 46 */     return this.values;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CvtTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */