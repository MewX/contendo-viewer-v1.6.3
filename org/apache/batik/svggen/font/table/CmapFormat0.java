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
/*    */ public class CmapFormat0
/*    */   extends CmapFormat
/*    */ {
/* 32 */   private int[] glyphIdArray = new int[256]; private int first;
/*    */   private int last;
/*    */   
/*    */   protected CmapFormat0(RandomAccessFile raf) throws IOException {
/* 36 */     super(raf);
/* 37 */     this.format = 0;
/* 38 */     this.first = -1;
/* 39 */     for (int i = 0; i < 256; i++) {
/* 40 */       this.glyphIdArray[i] = raf.readUnsignedByte();
/* 41 */       if (this.glyphIdArray[i] > 0) {
/* 42 */         if (this.first == -1) this.first = i; 
/* 43 */         this.last = i;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/* 48 */   public int getFirst() { return this.first; } public int getLast() {
/* 49 */     return this.last;
/*    */   }
/*    */   public int mapCharCode(int charCode) {
/* 52 */     if (0 <= charCode && charCode < 256) {
/* 53 */       return this.glyphIdArray[charCode];
/*    */     }
/* 55 */     return 0;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CmapFormat0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */