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
/*     */ public class MaximumProfileTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "maxp";
/*     */   private float version;
/*     */   private int numGlyphs;
/*     */   private int maxPoints;
/*     */   private int maxContours;
/*     */   private int maxCompositePoints;
/*     */   private int maxCompositeContours;
/*     */   private int maxZones;
/*     */   private int maxTwilightPoints;
/*     */   private int maxStorage;
/*     */   private int maxFunctionDefs;
/*     */   private int maxInstructionDefs;
/*     */   private int maxStackElements;
/*     */   private int maxSizeOfInstructions;
/*     */   private int maxComponentElements;
/*     */   private int maxComponentDepth;
/*     */   
/*     */   MaximumProfileTable(TrueTypeFont font) {
/*  51 */     super(font);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxComponentDepth() {
/*  59 */     return this.maxComponentDepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxComponentDepth(int maxComponentDepthValue) {
/*  66 */     this.maxComponentDepth = maxComponentDepthValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxComponentElements() {
/*  73 */     return this.maxComponentElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxComponentElements(int maxComponentElementsValue) {
/*  80 */     this.maxComponentElements = maxComponentElementsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxCompositeContours() {
/*  87 */     return this.maxCompositeContours;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxCompositeContours(int maxCompositeContoursValue) {
/*  94 */     this.maxCompositeContours = maxCompositeContoursValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxCompositePoints() {
/* 101 */     return this.maxCompositePoints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxCompositePoints(int maxCompositePointsValue) {
/* 108 */     this.maxCompositePoints = maxCompositePointsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxContours() {
/* 115 */     return this.maxContours;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxContours(int maxContoursValue) {
/* 122 */     this.maxContours = maxContoursValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxFunctionDefs() {
/* 129 */     return this.maxFunctionDefs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxFunctionDefs(int maxFunctionDefsValue) {
/* 136 */     this.maxFunctionDefs = maxFunctionDefsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxInstructionDefs() {
/* 143 */     return this.maxInstructionDefs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxInstructionDefs(int maxInstructionDefsValue) {
/* 150 */     this.maxInstructionDefs = maxInstructionDefsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxPoints() {
/* 157 */     return this.maxPoints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxPoints(int maxPointsValue) {
/* 164 */     this.maxPoints = maxPointsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxSizeOfInstructions() {
/* 171 */     return this.maxSizeOfInstructions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxSizeOfInstructions(int maxSizeOfInstructionsValue) {
/* 178 */     this.maxSizeOfInstructions = maxSizeOfInstructionsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStackElements() {
/* 185 */     return this.maxStackElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxStackElements(int maxStackElementsValue) {
/* 192 */     this.maxStackElements = maxStackElementsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStorage() {
/* 199 */     return this.maxStorage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxStorage(int maxStorageValue) {
/* 206 */     this.maxStorage = maxStorageValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxTwilightPoints() {
/* 213 */     return this.maxTwilightPoints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxTwilightPoints(int maxTwilightPointsValue) {
/* 220 */     this.maxTwilightPoints = maxTwilightPointsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxZones() {
/* 227 */     return this.maxZones;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxZones(int maxZonesValue) {
/* 234 */     this.maxZones = maxZonesValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumGlyphs() {
/* 241 */     return this.numGlyphs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumGlyphs(int numGlyphsValue) {
/* 248 */     this.numGlyphs = numGlyphsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVersion() {
/* 255 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(float versionValue) {
/* 262 */     this.version = versionValue;
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
/* 274 */     this.version = data.read32Fixed();
/* 275 */     this.numGlyphs = data.readUnsignedShort();
/* 276 */     this.maxPoints = data.readUnsignedShort();
/* 277 */     this.maxContours = data.readUnsignedShort();
/* 278 */     this.maxCompositePoints = data.readUnsignedShort();
/* 279 */     this.maxCompositeContours = data.readUnsignedShort();
/* 280 */     this.maxZones = data.readUnsignedShort();
/* 281 */     this.maxTwilightPoints = data.readUnsignedShort();
/* 282 */     this.maxStorage = data.readUnsignedShort();
/* 283 */     this.maxFunctionDefs = data.readUnsignedShort();
/* 284 */     this.maxInstructionDefs = data.readUnsignedShort();
/* 285 */     this.maxStackElements = data.readUnsignedShort();
/* 286 */     this.maxSizeOfInstructions = data.readUnsignedShort();
/* 287 */     this.maxComponentElements = data.readUnsignedShort();
/* 288 */     this.maxComponentDepth = data.readUnsignedShort();
/* 289 */     this.initialized = true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/MaximumProfileTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */