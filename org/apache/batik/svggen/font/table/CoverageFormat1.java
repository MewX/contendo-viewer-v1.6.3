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
/*    */ public class CoverageFormat1
/*    */   extends Coverage
/*    */ {
/*    */   private int glyphCount;
/*    */   private int[] glyphIds;
/*    */   
/*    */   protected CoverageFormat1(RandomAccessFile raf) throws IOException {
/* 36 */     this.glyphCount = raf.readUnsignedShort();
/* 37 */     this.glyphIds = new int[this.glyphCount];
/* 38 */     for (int i = 0; i < this.glyphCount; i++) {
/* 39 */       this.glyphIds[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 44 */     return 1;
/*    */   }
/*    */   
/*    */   public int findGlyph(int glyphId) {
/* 48 */     for (int i = 0; i < this.glyphCount; i++) {
/* 49 */       if (this.glyphIds[i] == glyphId) {
/* 50 */         return i;
/*    */       }
/*    */     } 
/* 53 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CoverageFormat1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */