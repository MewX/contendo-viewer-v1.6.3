/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
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
/*     */ public class HheaTable
/*     */   implements Table
/*     */ {
/*     */   private int version;
/*     */   private short ascender;
/*     */   private short descender;
/*     */   private short lineGap;
/*     */   private short advanceWidthMax;
/*     */   private short minLeftSideBearing;
/*     */   private short minRightSideBearing;
/*     */   private short xMaxExtent;
/*     */   private short caretSlopeRise;
/*     */   private short caretSlopeRun;
/*     */   private short metricDataFormat;
/*     */   private int numberOfHMetrics;
/*     */   
/*     */   protected HheaTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  44 */     raf.seek(de.getOffset());
/*  45 */     this.version = raf.readInt();
/*  46 */     this.ascender = raf.readShort();
/*  47 */     this.descender = raf.readShort();
/*  48 */     this.lineGap = raf.readShort();
/*  49 */     this.advanceWidthMax = raf.readShort();
/*  50 */     this.minLeftSideBearing = raf.readShort();
/*  51 */     this.minRightSideBearing = raf.readShort();
/*  52 */     this.xMaxExtent = raf.readShort();
/*  53 */     this.caretSlopeRise = raf.readShort();
/*  54 */     this.caretSlopeRun = raf.readShort();
/*  55 */     for (int i = 0; i < 5; i++) {
/*  56 */       raf.readShort();
/*     */     }
/*  58 */     this.metricDataFormat = raf.readShort();
/*  59 */     this.numberOfHMetrics = raf.readUnsignedShort();
/*     */   }
/*     */   
/*     */   public short getAdvanceWidthMax() {
/*  63 */     return this.advanceWidthMax;
/*     */   }
/*     */   
/*     */   public short getAscender() {
/*  67 */     return this.ascender;
/*     */   }
/*     */   
/*     */   public short getCaretSlopeRise() {
/*  71 */     return this.caretSlopeRise;
/*     */   }
/*     */   
/*     */   public short getCaretSlopeRun() {
/*  75 */     return this.caretSlopeRun;
/*     */   }
/*     */   
/*     */   public short getDescender() {
/*  79 */     return this.descender;
/*     */   }
/*     */   
/*     */   public short getLineGap() {
/*  83 */     return this.lineGap;
/*     */   }
/*     */   
/*     */   public short getMetricDataFormat() {
/*  87 */     return this.metricDataFormat;
/*     */   }
/*     */   
/*     */   public short getMinLeftSideBearing() {
/*  91 */     return this.minLeftSideBearing;
/*     */   }
/*     */   
/*     */   public short getMinRightSideBearing() {
/*  95 */     return this.minRightSideBearing;
/*     */   }
/*     */   
/*     */   public int getNumberOfHMetrics() {
/*  99 */     return this.numberOfHMetrics;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 103 */     return 1751672161;
/*     */   }
/*     */   
/*     */   public short getXMaxExtent() {
/* 107 */     return this.xMaxExtent;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/HheaTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */