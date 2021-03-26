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
/*     */ public class HorizontalHeaderTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "hhea";
/*     */   private float version;
/*     */   private short ascender;
/*     */   private short descender;
/*     */   private short lineGap;
/*     */   private int advanceWidthMax;
/*     */   private short minLeftSideBearing;
/*     */   private short minRightSideBearing;
/*     */   private short xMaxExtent;
/*     */   private short caretSlopeRise;
/*     */   private short caretSlopeRun;
/*     */   private short reserved1;
/*     */   private short reserved2;
/*     */   private short reserved3;
/*     */   private short reserved4;
/*     */   private short reserved5;
/*     */   private short metricDataFormat;
/*     */   private int numberOfHMetrics;
/*     */   
/*     */   HorizontalHeaderTable(TrueTypeFont font) {
/*  53 */     super(font);
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
/*     */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  65 */     this.version = data.read32Fixed();
/*  66 */     this.ascender = data.readSignedShort();
/*  67 */     this.descender = data.readSignedShort();
/*  68 */     this.lineGap = data.readSignedShort();
/*  69 */     this.advanceWidthMax = data.readUnsignedShort();
/*  70 */     this.minLeftSideBearing = data.readSignedShort();
/*  71 */     this.minRightSideBearing = data.readSignedShort();
/*  72 */     this.xMaxExtent = data.readSignedShort();
/*  73 */     this.caretSlopeRise = data.readSignedShort();
/*  74 */     this.caretSlopeRun = data.readSignedShort();
/*  75 */     this.reserved1 = data.readSignedShort();
/*  76 */     this.reserved2 = data.readSignedShort();
/*  77 */     this.reserved3 = data.readSignedShort();
/*  78 */     this.reserved4 = data.readSignedShort();
/*  79 */     this.reserved5 = data.readSignedShort();
/*  80 */     this.metricDataFormat = data.readSignedShort();
/*  81 */     this.numberOfHMetrics = data.readUnsignedShort();
/*  82 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAdvanceWidthMax() {
/*  90 */     return this.advanceWidthMax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAdvanceWidthMax(int advanceWidthMaxValue) {
/*  97 */     this.advanceWidthMax = advanceWidthMaxValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getAscender() {
/* 104 */     return this.ascender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAscender(short ascenderValue) {
/* 111 */     this.ascender = ascenderValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCaretSlopeRise() {
/* 118 */     return this.caretSlopeRise;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaretSlopeRise(short caretSlopeRiseValue) {
/* 125 */     this.caretSlopeRise = caretSlopeRiseValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCaretSlopeRun() {
/* 132 */     return this.caretSlopeRun;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaretSlopeRun(short caretSlopeRunValue) {
/* 139 */     this.caretSlopeRun = caretSlopeRunValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getDescender() {
/* 146 */     return this.descender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescender(short descenderValue) {
/* 153 */     this.descender = descenderValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getLineGap() {
/* 160 */     return this.lineGap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineGap(short lineGapValue) {
/* 167 */     this.lineGap = lineGapValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMetricDataFormat() {
/* 174 */     return this.metricDataFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetricDataFormat(short metricDataFormatValue) {
/* 181 */     this.metricDataFormat = metricDataFormatValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMinLeftSideBearing() {
/* 188 */     return this.minLeftSideBearing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinLeftSideBearing(short minLeftSideBearingValue) {
/* 195 */     this.minLeftSideBearing = minLeftSideBearingValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMinRightSideBearing() {
/* 202 */     return this.minRightSideBearing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinRightSideBearing(short minRightSideBearingValue) {
/* 209 */     this.minRightSideBearing = minRightSideBearingValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfHMetrics() {
/* 216 */     return this.numberOfHMetrics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberOfHMetrics(int numberOfHMetricsValue) {
/* 223 */     this.numberOfHMetrics = numberOfHMetricsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved1() {
/* 230 */     return this.reserved1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved1(short reserved1Value) {
/* 237 */     this.reserved1 = reserved1Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved2() {
/* 244 */     return this.reserved2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved2(short reserved2Value) {
/* 251 */     this.reserved2 = reserved2Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved3() {
/* 258 */     return this.reserved3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved3(short reserved3Value) {
/* 265 */     this.reserved3 = reserved3Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved4() {
/* 272 */     return this.reserved4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved4(short reserved4Value) {
/* 279 */     this.reserved4 = reserved4Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getReserved5() {
/* 286 */     return this.reserved5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved5(short reserved5Value) {
/* 293 */     this.reserved5 = reserved5Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVersion() {
/* 300 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(float versionValue) {
/* 307 */     this.version = versionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getXMaxExtent() {
/* 314 */     return this.xMaxExtent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMaxExtent(short maxExtentValue) {
/* 321 */     this.xMaxExtent = maxExtentValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/HorizontalHeaderTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */