/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class VerticalHeaderTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "vhea";
/*     */   private float version;
/*     */   private short ascender;
/*     */   private short descender;
/*     */   private short lineGap;
/*     */   private int advanceHeightMax;
/*     */   private short minTopSideBearing;
/*     */   private short minBottomSideBearing;
/*     */   private short yMaxExtent;
/*     */   private short caretSlopeRise;
/*     */   private short caretSlopeRun;
/*     */   private short caretOffset;
/*     */   private short reserved1;
/*     */   private short reserved2;
/*     */   private short reserved3;
/*     */   private short reserved4;
/*     */   private short metricDataFormat;
/*     */   private int numberOfVMetrics;
/*     */   
/*     */   VerticalHeaderTable(TrueTypeFont font) {
/*  63 */     super(font);
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
/*     */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  76 */     this.version = data.read32Fixed();
/*  77 */     this.ascender = data.readSignedShort();
/*  78 */     this.descender = data.readSignedShort();
/*  79 */     this.lineGap = data.readSignedShort();
/*  80 */     this.advanceHeightMax = data.readUnsignedShort();
/*  81 */     this.minTopSideBearing = data.readSignedShort();
/*  82 */     this.minBottomSideBearing = data.readSignedShort();
/*  83 */     this.yMaxExtent = data.readSignedShort();
/*  84 */     this.caretSlopeRise = data.readSignedShort();
/*  85 */     this.caretSlopeRun = data.readSignedShort();
/*  86 */     this.caretOffset = data.readSignedShort();
/*  87 */     this.reserved1 = data.readSignedShort();
/*  88 */     this.reserved2 = data.readSignedShort();
/*  89 */     this.reserved3 = data.readSignedShort();
/*  90 */     this.reserved4 = data.readSignedShort();
/*  91 */     this.metricDataFormat = data.readSignedShort();
/*  92 */     this.numberOfVMetrics = data.readUnsignedShort();
/*  93 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAdvanceHeightMax() {
/* 101 */     return this.advanceHeightMax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getAscender() {
/* 108 */     return this.ascender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCaretSlopeRise() {
/* 115 */     return this.caretSlopeRise;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCaretSlopeRun() {
/* 122 */     return this.caretSlopeRun;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCaretOffset() {
/* 129 */     return this.caretOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getDescender() {
/* 136 */     return this.descender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getLineGap() {
/* 143 */     return this.lineGap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMetricDataFormat() {
/* 150 */     return this.metricDataFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMinTopSideBearing() {
/* 157 */     return this.minTopSideBearing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMinBottomSideBearing() {
/* 164 */     return this.minBottomSideBearing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfVMetrics() {
/* 171 */     return this.numberOfVMetrics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved1() {
/* 178 */     return this.reserved1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved2() {
/* 185 */     return this.reserved2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved3() {
/* 192 */     return this.reserved3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved4() {
/* 199 */     return this.reserved4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVersion() {
/* 206 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getYMaxExtent() {
/* 213 */     return this.yMaxExtent;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/VerticalHeaderTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */