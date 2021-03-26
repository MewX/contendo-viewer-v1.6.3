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
/*     */ public class VerticalMetricsTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "vmtx";
/*     */   private int[] advanceHeight;
/*     */   private short[] topSideBearing;
/*     */   private short[] additionalTopSideBearing;
/*     */   private int numVMetrics;
/*     */   
/*     */   VerticalMetricsTable(TrueTypeFont font) {
/*  46 */     super(font);
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
/*  59 */     VerticalHeaderTable vHeader = ttf.getVerticalHeader();
/*  60 */     this.numVMetrics = vHeader.getNumberOfVMetrics();
/*  61 */     int numGlyphs = ttf.getNumberOfGlyphs();
/*     */     
/*  63 */     int bytesRead = 0;
/*  64 */     this.advanceHeight = new int[this.numVMetrics];
/*  65 */     this.topSideBearing = new short[this.numVMetrics];
/*  66 */     for (int i = 0; i < this.numVMetrics; i++) {
/*     */       
/*  68 */       this.advanceHeight[i] = data.readUnsignedShort();
/*  69 */       this.topSideBearing[i] = data.readSignedShort();
/*  70 */       bytesRead += 4;
/*     */     } 
/*     */     
/*  73 */     if (bytesRead < getLength()) {
/*     */       
/*  75 */       int numberNonVertical = numGlyphs - this.numVMetrics;
/*     */ 
/*     */       
/*  78 */       if (numberNonVertical < 0)
/*     */       {
/*  80 */         numberNonVertical = numGlyphs;
/*     */       }
/*     */       
/*  83 */       this.additionalTopSideBearing = new short[numberNonVertical];
/*  84 */       for (int j = 0; j < numberNonVertical; j++) {
/*     */         
/*  86 */         if (bytesRead < getLength()) {
/*     */           
/*  88 */           this.additionalTopSideBearing[j] = data.readSignedShort();
/*  89 */           bytesRead += 2;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTopSideBearing(int gid) {
/* 104 */     if (gid < this.numVMetrics)
/*     */     {
/* 106 */       return this.topSideBearing[gid];
/*     */     }
/*     */ 
/*     */     
/* 110 */     return this.additionalTopSideBearing[gid - this.numVMetrics];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAdvanceHeight(int gid) {
/* 121 */     if (gid < this.numVMetrics)
/*     */     {
/* 123 */       return this.advanceHeight[gid];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     return this.advanceHeight[this.advanceHeight.length - 1];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/VerticalMetricsTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */