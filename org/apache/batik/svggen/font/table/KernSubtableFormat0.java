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
/*    */ public class KernSubtableFormat0
/*    */   extends KernSubtable
/*    */ {
/*    */   private int nPairs;
/*    */   private int searchRange;
/*    */   private int entrySelector;
/*    */   private int rangeShift;
/*    */   private KerningPair[] kerningPairs;
/*    */   
/*    */   protected KernSubtableFormat0(RandomAccessFile raf) throws IOException {
/* 39 */     this.nPairs = raf.readUnsignedShort();
/* 40 */     this.searchRange = raf.readUnsignedShort();
/* 41 */     this.entrySelector = raf.readUnsignedShort();
/* 42 */     this.rangeShift = raf.readUnsignedShort();
/* 43 */     this.kerningPairs = new KerningPair[this.nPairs];
/* 44 */     for (int i = 0; i < this.nPairs; i++) {
/* 45 */       this.kerningPairs[i] = new KerningPair(raf);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getKerningPairCount() {
/* 50 */     return this.nPairs;
/*    */   }
/*    */   
/*    */   public KerningPair getKerningPair(int i) {
/* 54 */     return this.kerningPairs[i];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/KernSubtableFormat0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */