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
/*    */ public class PrepTable
/*    */   extends Program
/*    */   implements Table
/*    */ {
/*    */   public PrepTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 31 */     raf.seek(de.getOffset());
/* 32 */     readInstructions(raf, de.getLength());
/*    */   }
/*    */   
/*    */   public int getType() {
/* 36 */     return 1886545264;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/PrepTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */