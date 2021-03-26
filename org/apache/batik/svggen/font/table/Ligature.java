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
/*    */ public class Ligature
/*    */ {
/*    */   private int ligGlyph;
/*    */   private int compCount;
/*    */   private int[] components;
/*    */   
/*    */   public Ligature(RandomAccessFile raf) throws IOException {
/* 37 */     this.ligGlyph = raf.readUnsignedShort();
/* 38 */     this.compCount = raf.readUnsignedShort();
/* 39 */     this.components = new int[this.compCount - 1];
/* 40 */     for (int i = 0; i < this.compCount - 1; i++) {
/* 41 */       this.components[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getGlyphCount() {
/* 46 */     return this.compCount;
/*    */   }
/*    */   
/*    */   public int getGlyphId(int i) {
/* 50 */     return (i == 0) ? this.ligGlyph : this.components[i - 1];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Ligature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */