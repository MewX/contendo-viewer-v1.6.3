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
/*    */ public class CmapFormat6
/*    */   extends CmapFormat
/*    */ {
/*    */   private short format;
/*    */   private short length;
/*    */   private short version;
/*    */   private short firstCode;
/*    */   private short entryCount;
/*    */   private short[] glyphIdArray;
/*    */   
/*    */   protected CmapFormat6(RandomAccessFile raf) throws IOException {
/* 38 */     super(raf);
/* 39 */     this.format = 6;
/*    */   }
/*    */   
/* 42 */   public int getFirst() { return 0; } public int getLast() {
/* 43 */     return 0;
/*    */   }
/*    */   public int mapCharCode(int charCode) {
/* 46 */     return 0;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CmapFormat6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */