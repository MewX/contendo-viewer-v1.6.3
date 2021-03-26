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
/*    */ public class LigatureSet
/*    */ {
/*    */   private int ligatureCount;
/*    */   private int[] ligatureOffsets;
/*    */   private Ligature[] ligatures;
/*    */   
/*    */   public LigatureSet(RandomAccessFile raf, int offset) throws IOException {
/* 37 */     raf.seek(offset);
/* 38 */     this.ligatureCount = raf.readUnsignedShort();
/* 39 */     this.ligatureOffsets = new int[this.ligatureCount];
/* 40 */     this.ligatures = new Ligature[this.ligatureCount]; int i;
/* 41 */     for (i = 0; i < this.ligatureCount; i++) {
/* 42 */       this.ligatureOffsets[i] = raf.readUnsignedShort();
/*    */     }
/* 44 */     for (i = 0; i < this.ligatureCount; i++) {
/* 45 */       raf.seek((offset + this.ligatureOffsets[i]));
/* 46 */       this.ligatures[i] = new Ligature(raf);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/LigatureSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */