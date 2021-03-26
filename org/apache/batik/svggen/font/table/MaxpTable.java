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
/*     */ public class MaxpTable
/*     */   implements Table
/*     */ {
/*     */   private int versionNumber;
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
/*     */   protected MaxpTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  47 */     raf.seek(de.getOffset());
/*  48 */     this.versionNumber = raf.readInt();
/*  49 */     this.numGlyphs = raf.readUnsignedShort();
/*  50 */     this.maxPoints = raf.readUnsignedShort();
/*  51 */     this.maxContours = raf.readUnsignedShort();
/*  52 */     this.maxCompositePoints = raf.readUnsignedShort();
/*  53 */     this.maxCompositeContours = raf.readUnsignedShort();
/*  54 */     this.maxZones = raf.readUnsignedShort();
/*  55 */     this.maxTwilightPoints = raf.readUnsignedShort();
/*  56 */     this.maxStorage = raf.readUnsignedShort();
/*  57 */     this.maxFunctionDefs = raf.readUnsignedShort();
/*  58 */     this.maxInstructionDefs = raf.readUnsignedShort();
/*  59 */     this.maxStackElements = raf.readUnsignedShort();
/*  60 */     this.maxSizeOfInstructions = raf.readUnsignedShort();
/*  61 */     this.maxComponentElements = raf.readUnsignedShort();
/*  62 */     this.maxComponentDepth = raf.readUnsignedShort();
/*     */   }
/*     */   
/*     */   public int getMaxComponentDepth() {
/*  66 */     return this.maxComponentDepth;
/*     */   }
/*     */   
/*     */   public int getMaxComponentElements() {
/*  70 */     return this.maxComponentElements;
/*     */   }
/*     */   
/*     */   public int getMaxCompositeContours() {
/*  74 */     return this.maxCompositeContours;
/*     */   }
/*     */   
/*     */   public int getMaxCompositePoints() {
/*  78 */     return this.maxCompositePoints;
/*     */   }
/*     */   
/*     */   public int getMaxContours() {
/*  82 */     return this.maxContours;
/*     */   }
/*     */   
/*     */   public int getMaxFunctionDefs() {
/*  86 */     return this.maxFunctionDefs;
/*     */   }
/*     */   
/*     */   public int getMaxInstructionDefs() {
/*  90 */     return this.maxInstructionDefs;
/*     */   }
/*     */   
/*     */   public int getMaxPoints() {
/*  94 */     return this.maxPoints;
/*     */   }
/*     */   
/*     */   public int getMaxSizeOfInstructions() {
/*  98 */     return this.maxSizeOfInstructions;
/*     */   }
/*     */   
/*     */   public int getMaxStackElements() {
/* 102 */     return this.maxStackElements;
/*     */   }
/*     */   
/*     */   public int getMaxStorage() {
/* 106 */     return this.maxStorage;
/*     */   }
/*     */   
/*     */   public int getMaxTwilightPoints() {
/* 110 */     return this.maxTwilightPoints;
/*     */   }
/*     */   
/*     */   public int getMaxZones() {
/* 114 */     return this.maxZones;
/*     */   }
/*     */   
/*     */   public int getNumGlyphs() {
/* 118 */     return this.numGlyphs;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 122 */     return 1835104368;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/MaxpTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */