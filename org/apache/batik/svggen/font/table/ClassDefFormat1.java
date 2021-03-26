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
/*    */ public class ClassDefFormat1
/*    */   extends ClassDef
/*    */ {
/*    */   private int startGlyph;
/*    */   private int glyphCount;
/*    */   private int[] classValues;
/*    */   
/*    */   public ClassDefFormat1(RandomAccessFile raf) throws IOException {
/* 37 */     this.startGlyph = raf.readUnsignedShort();
/* 38 */     this.glyphCount = raf.readUnsignedShort();
/* 39 */     this.classValues = new int[this.glyphCount];
/* 40 */     for (int i = 0; i < this.glyphCount; i++) {
/* 41 */       this.classValues[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 46 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/ClassDefFormat1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */