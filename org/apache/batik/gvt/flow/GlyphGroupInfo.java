/*     */ package org.apache.batik.gvt.flow;
/*     */ 
/*     */ import org.apache.batik.gvt.font.GVTGlyphVector;
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
/*     */ public class GlyphGroupInfo
/*     */ {
/*     */   int start;
/*     */   int end;
/*     */   int glyphCount;
/*     */   int lastGlyphCount;
/*     */   boolean hideLast;
/*     */   float advance;
/*     */   float lastAdvance;
/*     */   int range;
/*     */   GVTGlyphVector gv;
/*     */   boolean[] hide;
/*     */   
/*     */   public GlyphGroupInfo(GVTGlyphVector gv, int start, int end, boolean[] glyphHide, boolean glyphGroupHideLast, float[] glyphPos, float[] advAdj, float[] lastAdvAdj, boolean[] space) {
/*  50 */     this.gv = gv;
/*  51 */     this.start = start;
/*  52 */     this.end = end;
/*  53 */     this.hide = new boolean[this.end - this.start + 1];
/*  54 */     this.hideLast = glyphGroupHideLast;
/*  55 */     System.arraycopy(glyphHide, this.start, this.hide, 0, this.hide.length);
/*     */ 
/*     */     
/*  58 */     float adv = glyphPos[2 * end + 2] - glyphPos[2 * start];
/*  59 */     float ladv = adv;
/*  60 */     adv += advAdj[end];
/*  61 */     int glyphCount = end - start + 1;
/*  62 */     for (int g = start; g < end; g++) {
/*  63 */       if (glyphHide[g]) glyphCount--; 
/*     */     } 
/*  65 */     int lastGlyphCount = glyphCount;
/*  66 */     for (int i = end; i >= start; i--) {
/*  67 */       ladv += lastAdvAdj[i];
/*  68 */       if (!space[i])
/*  69 */         break;  lastGlyphCount--;
/*     */     } 
/*  71 */     if (this.hideLast) lastGlyphCount--;
/*     */     
/*  73 */     this.glyphCount = glyphCount;
/*  74 */     this.lastGlyphCount = lastGlyphCount;
/*  75 */     this.advance = adv;
/*  76 */     this.lastAdvance = ladv;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphVector getGlyphVector() {
/*  82 */     return this.gv;
/*     */   }
/*     */   public int getStart() {
/*  85 */     return this.start;
/*     */   } public int getEnd() {
/*  87 */     return this.end;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGlyphCount() {
/*  92 */     return this.glyphCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLastGlyphCount() {
/*  98 */     return this.lastGlyphCount;
/*     */   } public boolean[] getHide() {
/* 100 */     return this.hide;
/*     */   }
/*     */   
/*     */   public boolean getHideLast() {
/* 104 */     return this.hideLast;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdvance() {
/* 109 */     return this.advance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLastAdvance() {
/* 117 */     return this.lastAdvance;
/*     */   }
/* 119 */   public void setRange(int range) { this.range = range; } public int getRange() {
/* 120 */     return this.range;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/flow/GlyphGroupInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */