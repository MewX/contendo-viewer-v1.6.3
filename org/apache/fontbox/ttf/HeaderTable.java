/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
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
/*     */ public class HeaderTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "head";
/*     */   public static final int MAC_STYLE_BOLD = 1;
/*     */   public static final int MAC_STYLE_ITALIC = 2;
/*     */   private float version;
/*     */   private float fontRevision;
/*     */   private long checkSumAdjustment;
/*     */   private long magicNumber;
/*     */   private int flags;
/*     */   private int unitsPerEm;
/*     */   private Calendar created;
/*     */   private Calendar modified;
/*     */   private short xMin;
/*     */   private short yMin;
/*     */   private short xMax;
/*     */   private short yMax;
/*     */   private int macStyle;
/*     */   private int lowestRecPPEM;
/*     */   private short fontDirectionHint;
/*     */   private short indexToLocFormat;
/*     */   private short glyphDataFormat;
/*     */   
/*     */   HeaderTable(TrueTypeFont font) {
/*  64 */     super(font);
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
/*  76 */     this.version = data.read32Fixed();
/*  77 */     this.fontRevision = data.read32Fixed();
/*  78 */     this.checkSumAdjustment = data.readUnsignedInt();
/*  79 */     this.magicNumber = data.readUnsignedInt();
/*  80 */     this.flags = data.readUnsignedShort();
/*  81 */     this.unitsPerEm = data.readUnsignedShort();
/*  82 */     this.created = data.readInternationalDate();
/*  83 */     this.modified = data.readInternationalDate();
/*  84 */     this.xMin = data.readSignedShort();
/*  85 */     this.yMin = data.readSignedShort();
/*  86 */     this.xMax = data.readSignedShort();
/*  87 */     this.yMax = data.readSignedShort();
/*  88 */     this.macStyle = data.readUnsignedShort();
/*  89 */     this.lowestRecPPEM = data.readUnsignedShort();
/*  90 */     this.fontDirectionHint = data.readSignedShort();
/*  91 */     this.indexToLocFormat = data.readSignedShort();
/*  92 */     this.glyphDataFormat = data.readSignedShort();
/*  93 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCheckSumAdjustment() {
/* 100 */     return this.checkSumAdjustment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCheckSumAdjustment(long checkSumAdjustmentValue) {
/* 107 */     this.checkSumAdjustment = checkSumAdjustmentValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getCreated() {
/* 114 */     return this.created;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreated(Calendar createdValue) {
/* 121 */     this.created = createdValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 128 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flagsValue) {
/* 135 */     this.flags = flagsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getFontDirectionHint() {
/* 142 */     return this.fontDirectionHint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontDirectionHint(short fontDirectionHintValue) {
/* 149 */     this.fontDirectionHint = fontDirectionHintValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFontRevision() {
/* 156 */     return this.fontRevision;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontRevision(float fontRevisionValue) {
/* 163 */     this.fontRevision = fontRevisionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getGlyphDataFormat() {
/* 170 */     return this.glyphDataFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphDataFormat(short glyphDataFormatValue) {
/* 177 */     this.glyphDataFormat = glyphDataFormatValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getIndexToLocFormat() {
/* 184 */     return this.indexToLocFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndexToLocFormat(short indexToLocFormatValue) {
/* 191 */     this.indexToLocFormat = indexToLocFormatValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLowestRecPPEM() {
/* 198 */     return this.lowestRecPPEM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLowestRecPPEM(int lowestRecPPEMValue) {
/* 205 */     this.lowestRecPPEM = lowestRecPPEMValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMacStyle() {
/* 212 */     return this.macStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMacStyle(int macStyleValue) {
/* 219 */     this.macStyle = macStyleValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMagicNumber() {
/* 226 */     return this.magicNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMagicNumber(long magicNumberValue) {
/* 233 */     this.magicNumber = magicNumberValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getModified() {
/* 240 */     return this.modified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModified(Calendar modifiedValue) {
/* 247 */     this.modified = modifiedValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUnitsPerEm() {
/* 254 */     return this.unitsPerEm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnitsPerEm(int unitsPerEmValue) {
/* 261 */     this.unitsPerEm = unitsPerEmValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVersion() {
/* 268 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(float versionValue) {
/* 275 */     this.version = versionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getXMax() {
/* 282 */     return this.xMax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMax(short maxValue) {
/* 289 */     this.xMax = maxValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getXMin() {
/* 296 */     return this.xMin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMin(short minValue) {
/* 303 */     this.xMin = minValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getYMax() {
/* 310 */     return this.yMax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYMax(short maxValue) {
/* 317 */     this.yMax = maxValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getYMin() {
/* 324 */     return this.yMin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYMin(short minValue) {
/* 331 */     this.yMin = minValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/HeaderTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */