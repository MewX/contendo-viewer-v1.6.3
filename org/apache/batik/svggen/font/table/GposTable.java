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
/*    */ public class GposTable
/*    */   implements Table
/*    */ {
/*    */   protected GposTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 32 */     raf.seek(de.getOffset());
/*    */ 
/*    */     
/* 35 */     raf.readInt();
/* 36 */     raf.readInt();
/* 37 */     raf.readInt();
/* 38 */     raf.readInt();
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
/*    */   public int getType() {
/* 72 */     return 1196445523;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 76 */     return "GPOS";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GposTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */