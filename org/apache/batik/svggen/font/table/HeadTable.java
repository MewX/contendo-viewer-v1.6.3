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
/*     */ public class HeadTable
/*     */   implements Table
/*     */ {
/*     */   private int versionNumber;
/*     */   private int fontRevision;
/*     */   private int checkSumAdjustment;
/*     */   private int magicNumber;
/*     */   private short flags;
/*     */   private short unitsPerEm;
/*     */   private long created;
/*     */   private long modified;
/*     */   private short xMin;
/*     */   private short yMin;
/*     */   private short xMax;
/*     */   private short yMax;
/*     */   private short macStyle;
/*     */   private short lowestRecPPEM;
/*     */   private short fontDirectionHint;
/*     */   private short indexToLocFormat;
/*     */   private short glyphDataFormat;
/*     */   
/*     */   protected HeadTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  49 */     raf.seek(de.getOffset());
/*  50 */     this.versionNumber = raf.readInt();
/*  51 */     this.fontRevision = raf.readInt();
/*  52 */     this.checkSumAdjustment = raf.readInt();
/*  53 */     this.magicNumber = raf.readInt();
/*  54 */     this.flags = raf.readShort();
/*  55 */     this.unitsPerEm = raf.readShort();
/*  56 */     this.created = raf.readLong();
/*  57 */     this.modified = raf.readLong();
/*  58 */     this.xMin = raf.readShort();
/*  59 */     this.yMin = raf.readShort();
/*  60 */     this.xMax = raf.readShort();
/*  61 */     this.yMax = raf.readShort();
/*  62 */     this.macStyle = raf.readShort();
/*  63 */     this.lowestRecPPEM = raf.readShort();
/*  64 */     this.fontDirectionHint = raf.readShort();
/*  65 */     this.indexToLocFormat = raf.readShort();
/*  66 */     this.glyphDataFormat = raf.readShort();
/*     */   }
/*     */   
/*     */   public int getCheckSumAdjustment() {
/*  70 */     return this.checkSumAdjustment;
/*     */   }
/*     */   
/*     */   public long getCreated() {
/*  74 */     return this.created;
/*     */   }
/*     */   
/*     */   public short getFlags() {
/*  78 */     return this.flags;
/*     */   }
/*     */   
/*     */   public short getFontDirectionHint() {
/*  82 */     return this.fontDirectionHint;
/*     */   }
/*     */   
/*     */   public int getFontRevision() {
/*  86 */     return this.fontRevision;
/*     */   }
/*     */   
/*     */   public short getGlyphDataFormat() {
/*  90 */     return this.glyphDataFormat;
/*     */   }
/*     */   
/*     */   public short getIndexToLocFormat() {
/*  94 */     return this.indexToLocFormat;
/*     */   }
/*     */   
/*     */   public short getLowestRecPPEM() {
/*  98 */     return this.lowestRecPPEM;
/*     */   }
/*     */   
/*     */   public short getMacStyle() {
/* 102 */     return this.macStyle;
/*     */   }
/*     */   
/*     */   public long getModified() {
/* 106 */     return this.modified;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 110 */     return 1751474532;
/*     */   }
/*     */   
/*     */   public short getUnitsPerEm() {
/* 114 */     return this.unitsPerEm;
/*     */   }
/*     */   
/*     */   public int getVersionNumber() {
/* 118 */     return this.versionNumber;
/*     */   }
/*     */   
/*     */   public short getXMax() {
/* 122 */     return this.xMax;
/*     */   }
/*     */   
/*     */   public short getXMin() {
/* 126 */     return this.xMin;
/*     */   }
/*     */   
/*     */   public short getYMax() {
/* 130 */     return this.yMax;
/*     */   }
/*     */   
/*     */   public short getYMin() {
/* 134 */     return this.yMin;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 138 */     return "head\n\tversionNumber: " + this.versionNumber + "\n\tfontRevision: " + this.fontRevision + "\n\tcheckSumAdjustment: " + this.checkSumAdjustment + "\n\tmagicNumber: " + this.magicNumber + "\n\tflags: " + this.flags + "\n\tunitsPerEm: " + this.unitsPerEm + "\n\tcreated: " + this.created + "\n\tmodified: " + this.modified + "\n\txMin: " + this.xMin + ", yMin: " + this.yMin + "\n\txMax: " + this.xMax + ", yMax: " + this.yMax + "\n\tmacStyle: " + this.macStyle + "\n\tlowestRecPPEM: " + this.lowestRecPPEM + "\n\tfontDirectionHint: " + this.fontDirectionHint + "\n\tindexToLocFormat: " + this.indexToLocFormat + "\n\tglyphDataFormat: " + this.glyphDataFormat;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/HeadTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */