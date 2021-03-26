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
/*    */ public class SingleSubstFormat1
/*    */   extends SingleSubst
/*    */ {
/*    */   private int coverageOffset;
/*    */   private short deltaGlyphID;
/*    */   private Coverage coverage;
/*    */   
/*    */   protected SingleSubstFormat1(RandomAccessFile raf, int offset) throws IOException {
/* 37 */     this.coverageOffset = raf.readUnsignedShort();
/* 38 */     this.deltaGlyphID = raf.readShort();
/* 39 */     raf.seek((offset + this.coverageOffset));
/* 40 */     this.coverage = Coverage.read(raf);
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 44 */     return 1;
/*    */   }
/*    */   
/*    */   public int substitute(int glyphId) {
/* 48 */     int i = this.coverage.findGlyph(glyphId);
/* 49 */     if (i > -1) {
/* 50 */       return glyphId + this.deltaGlyphID;
/*    */     }
/* 52 */     return glyphId;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/SingleSubstFormat1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */