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
/*     */ public class HorizontalMetricsTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "hmtx";
/*     */   private int[] advanceWidth;
/*     */   private short[] leftSideBearing;
/*     */   private short[] nonHorizontalLeftSideBearing;
/*     */   private int numHMetrics;
/*     */   
/*     */   HorizontalMetricsTable(TrueTypeFont font) {
/*  40 */     super(font);
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
/*  52 */     HorizontalHeaderTable hHeader = ttf.getHorizontalHeader();
/*  53 */     this.numHMetrics = hHeader.getNumberOfHMetrics();
/*  54 */     int numGlyphs = ttf.getNumberOfGlyphs();
/*     */     
/*  56 */     int bytesRead = 0;
/*  57 */     this.advanceWidth = new int[this.numHMetrics];
/*  58 */     this.leftSideBearing = new short[this.numHMetrics];
/*  59 */     for (int i = 0; i < this.numHMetrics; i++) {
/*     */       
/*  61 */       this.advanceWidth[i] = data.readUnsignedShort();
/*  62 */       this.leftSideBearing[i] = data.readSignedShort();
/*  63 */       bytesRead += 4;
/*     */     } 
/*     */     
/*  66 */     int numberNonHorizontal = numGlyphs - this.numHMetrics;
/*     */ 
/*     */     
/*  69 */     if (numberNonHorizontal < 0)
/*     */     {
/*  71 */       numberNonHorizontal = numGlyphs;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  76 */     this.nonHorizontalLeftSideBearing = new short[numberNonHorizontal];
/*     */     
/*  78 */     if (bytesRead < getLength())
/*     */     {
/*  80 */       for (int j = 0; j < numberNonHorizontal; j++) {
/*     */         
/*  82 */         if (bytesRead < getLength()) {
/*     */           
/*  84 */           this.nonHorizontalLeftSideBearing[j] = data.readSignedShort();
/*  85 */           bytesRead += 2;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  90 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAdvanceWidth(int gid) {
/* 100 */     if (gid < this.numHMetrics)
/*     */     {
/* 102 */       return this.advanceWidth[gid];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     return this.advanceWidth[this.advanceWidth.length - 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLeftSideBearing(int gid) {
/* 119 */     if (gid < this.numHMetrics)
/*     */     {
/* 121 */       return this.leftSideBearing[gid];
/*     */     }
/*     */ 
/*     */     
/* 125 */     return this.nonHorizontalLeftSideBearing[gid - this.numHMetrics];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/HorizontalMetricsTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */