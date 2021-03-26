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
/*    */ public class LigatureSubstFormat1
/*    */   extends LigatureSubst
/*    */ {
/*    */   private int coverageOffset;
/*    */   private int ligSetCount;
/*    */   private int[] ligatureSetOffsets;
/*    */   private Coverage coverage;
/*    */   private LigatureSet[] ligatureSets;
/*    */   
/*    */   protected LigatureSubstFormat1(RandomAccessFile raf, int offset) throws IOException {
/* 39 */     this.coverageOffset = raf.readUnsignedShort();
/* 40 */     this.ligSetCount = raf.readUnsignedShort();
/* 41 */     this.ligatureSetOffsets = new int[this.ligSetCount];
/* 42 */     this.ligatureSets = new LigatureSet[this.ligSetCount]; int i;
/* 43 */     for (i = 0; i < this.ligSetCount; i++) {
/* 44 */       this.ligatureSetOffsets[i] = raf.readUnsignedShort();
/*    */     }
/* 46 */     raf.seek((offset + this.coverageOffset));
/* 47 */     this.coverage = Coverage.read(raf);
/* 48 */     for (i = 0; i < this.ligSetCount; i++) {
/* 49 */       this.ligatureSets[i] = new LigatureSet(raf, offset + this.ligatureSetOffsets[i]);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 54 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/LigatureSubstFormat1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */