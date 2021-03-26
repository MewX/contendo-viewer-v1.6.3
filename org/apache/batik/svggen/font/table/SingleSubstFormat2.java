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
/*    */ public class SingleSubstFormat2
/*    */   extends SingleSubst
/*    */ {
/*    */   private int coverageOffset;
/*    */   private int glyphCount;
/*    */   private int[] substitutes;
/*    */   private Coverage coverage;
/*    */   
/*    */   protected SingleSubstFormat2(RandomAccessFile raf, int offset) throws IOException {
/* 38 */     this.coverageOffset = raf.readUnsignedShort();
/* 39 */     this.glyphCount = raf.readUnsignedShort();
/* 40 */     this.substitutes = new int[this.glyphCount];
/* 41 */     for (int i = 0; i < this.glyphCount; i++) {
/* 42 */       this.substitutes[i] = raf.readUnsignedShort();
/*    */     }
/* 44 */     raf.seek((offset + this.coverageOffset));
/* 45 */     this.coverage = Coverage.read(raf);
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 49 */     return 2;
/*    */   }
/*    */   
/*    */   public int substitute(int glyphId) {
/* 53 */     int i = this.coverage.findGlyph(glyphId);
/* 54 */     if (i > -1) {
/* 55 */       return this.substitutes[i];
/*    */     }
/* 57 */     return glyphId;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/SingleSubstFormat2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */