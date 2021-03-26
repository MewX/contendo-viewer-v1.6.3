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
/*    */ public class CmapFormat2
/*    */   extends CmapFormat
/*    */ {
/* 30 */   private short[] subHeaderKeys = new short[256];
/*    */   private int[] subHeaders1;
/*    */   private int[] subHeaders2;
/*    */   private short[] glyphIndexArray;
/*    */   
/*    */   protected CmapFormat2(RandomAccessFile raf) throws IOException {
/* 36 */     super(raf);
/* 37 */     this.format = 2;
/*    */   }
/*    */   
/* 40 */   public int getFirst() { return 0; } public int getLast() {
/* 41 */     return 0;
/*    */   }
/*    */   public int mapCharCode(int charCode) {
/* 44 */     return 0;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CmapFormat2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */