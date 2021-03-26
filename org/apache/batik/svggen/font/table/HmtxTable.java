/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HmtxTable
/*     */   implements Table
/*     */ {
/*  31 */   private byte[] buf = null;
/*  32 */   private int[] hMetrics = null;
/*  33 */   private short[] leftSideBearing = null;
/*     */   
/*     */   protected HmtxTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  36 */     raf.seek(de.getOffset());
/*  37 */     this.buf = new byte[de.getLength()];
/*  38 */     raf.read(this.buf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int numberOfHMetrics, int lsbCount) {
/*  57 */     if (this.buf == null) {
/*     */       return;
/*     */     }
/*  60 */     this.hMetrics = new int[numberOfHMetrics];
/*  61 */     ByteArrayInputStream bais = new ByteArrayInputStream(this.buf); int i;
/*  62 */     for (i = 0; i < numberOfHMetrics; i++)
/*     */     {
/*     */       
/*  65 */       this.hMetrics[i] = bais.read() << 24 | bais.read() << 16 | bais.read() << 8 | bais.read();
/*     */     }
/*     */     
/*  68 */     if (lsbCount > 0) {
/*  69 */       this.leftSideBearing = new short[lsbCount];
/*  70 */       for (i = 0; i < lsbCount; i++) {
/*  71 */         this.leftSideBearing[i] = (short)(bais.read() << 8 | bais.read());
/*     */       }
/*     */     } 
/*  74 */     this.buf = null;
/*     */   }
/*     */   
/*     */   public int getAdvanceWidth(int i) {
/*  78 */     if (this.hMetrics == null) {
/*  79 */       return 0;
/*     */     }
/*  81 */     if (i < this.hMetrics.length) {
/*  82 */       return this.hMetrics[i] >> 16;
/*     */     }
/*  84 */     return this.hMetrics[this.hMetrics.length - 1] >> 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getLeftSideBearing(int i) {
/*  89 */     if (this.hMetrics == null) {
/*  90 */       return 0;
/*     */     }
/*  92 */     if (i < this.hMetrics.length) {
/*  93 */       return (short)(this.hMetrics[i] & 0xFFFF);
/*     */     }
/*  95 */     return this.leftSideBearing[i - this.hMetrics.length];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 100 */     return 1752003704;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/HmtxTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */