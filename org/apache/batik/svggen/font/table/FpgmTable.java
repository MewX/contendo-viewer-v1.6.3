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
/*    */ public class FpgmTable
/*    */   extends Program
/*    */   implements Table
/*    */ {
/*    */   protected FpgmTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 31 */     raf.seek(de.getOffset());
/* 32 */     readInstructions(raf, de.getLength());
/*    */   }
/*    */   
/*    */   public int getType() {
/* 36 */     return 1718642541;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/FpgmTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */