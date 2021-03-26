/*    */ package org.apache.batik.svggen.font.table;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
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
/*    */ public class LocaTable
/*    */   implements Table
/*    */ {
/* 31 */   private byte[] buf = null;
/* 32 */   private int[] offsets = null;
/* 33 */   private short factor = 0;
/*    */   
/*    */   protected LocaTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/* 36 */     raf.seek(de.getOffset());
/* 37 */     this.buf = new byte[de.getLength()];
/* 38 */     raf.read(this.buf);
/*    */   }
/*    */   
/*    */   public void init(int numGlyphs, boolean shortEntries) {
/* 42 */     if (this.buf == null) {
/*    */       return;
/*    */     }
/* 45 */     this.offsets = new int[numGlyphs + 1];
/* 46 */     ByteArrayInputStream bais = new ByteArrayInputStream(this.buf);
/* 47 */     if (shortEntries) {
/* 48 */       this.factor = 2;
/* 49 */       for (int i = 0; i <= numGlyphs; i++) {
/* 50 */         this.offsets[i] = bais.read() << 8 | bais.read();
/*    */       }
/*    */     } else {
/* 53 */       this.factor = 1;
/* 54 */       for (int i = 0; i <= numGlyphs; i++) {
/* 55 */         this.offsets[i] = bais.read() << 24 | bais.read() << 16 | bais.read() << 8 | bais.read();
/*    */       }
/*    */     } 
/*    */     
/* 59 */     this.buf = null;
/*    */   }
/*    */   
/*    */   public int getOffset(int i) {
/* 63 */     if (this.offsets == null) {
/* 64 */       return 0;
/*    */     }
/* 66 */     return this.offsets[i] * this.factor;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 70 */     return 1819239265;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/LocaTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */