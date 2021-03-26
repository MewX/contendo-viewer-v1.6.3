/*     */ package org.apache.batik.gvt.flow;
/*     */ 
/*     */ import java.awt.geom.Point2D;
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
/*     */ public class LineInfo
/*     */ {
/*     */   FlowRegions fr;
/*  35 */   double lineHeight = -1.0D;
/*  36 */   double ascent = -1.0D;
/*  37 */   double descent = -1.0D;
/*  38 */   double hLeading = -1.0D;
/*     */   double baseline;
/*     */   int numGlyphs;
/*  41 */   int words = 0;
/*     */   
/*  43 */   int size = 0;
/*  44 */   GlyphGroupInfo[] ggis = null;
/*  45 */   int newSize = 0;
/*  46 */   GlyphGroupInfo[] newGGIS = null;
/*     */   
/*     */   int numRanges;
/*     */   
/*     */   double[] ranges;
/*     */   double[] rangeAdv;
/*  52 */   BlockInfo bi = null;
/*     */   boolean paraStart;
/*     */   boolean paraEnd;
/*     */   protected static final int FULL_WORD = 0;
/*     */   protected static final int FULL_ADV = 1;
/*     */   static final float MAX_COMPRESS = 0.1F;
/*     */   static final float COMRESS_SCALE = 3.0F;
/*     */   
/*     */   public LineInfo(FlowRegions fr, BlockInfo bi, boolean paraStart) {
/*  61 */     this.fr = fr;
/*  62 */     this.bi = bi;
/*  63 */     this.lineHeight = bi.getLineHeight();
/*  64 */     this.ascent = bi.getAscent();
/*  65 */     this.descent = bi.getDescent();
/*  66 */     this.hLeading = (this.lineHeight - this.ascent + this.descent) / 2.0D;
/*  67 */     this.baseline = (float)(fr.getCurrentY() + this.hLeading + this.ascent);
/*  68 */     this.paraStart = paraStart;
/*  69 */     this.paraEnd = false;
/*  70 */     if (this.lineHeight > 0.0D) {
/*  71 */       fr.newLineHeight(this.lineHeight);
/*  72 */       updateRangeInfo();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParaEnd(boolean paraEnd) {
/*  78 */     this.paraEnd = paraEnd;
/*     */   }
/*     */   
/*     */   public boolean addWord(WordInfo wi) {
/*  82 */     double nlh = wi.getLineHeight();
/*  83 */     if (nlh <= this.lineHeight)
/*  84 */       return insertWord(wi); 
/*  85 */     this.fr.newLineHeight(nlh);
/*     */     
/*  87 */     if (!updateRangeInfo()) {
/*  88 */       if (this.lineHeight > 0.0D)
/*  89 */         this.fr.newLineHeight(this.lineHeight); 
/*  90 */       return false;
/*     */     } 
/*     */     
/*  93 */     if (!insertWord(wi)) {
/*  94 */       if (this.lineHeight > 0.0D)
/*  95 */         setLineHeight(this.lineHeight); 
/*  96 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 100 */     this.lineHeight = nlh;
/* 101 */     if (wi.getAscent() > this.ascent)
/* 102 */       this.ascent = wi.getAscent(); 
/* 103 */     if (wi.getDescent() > this.descent)
/* 104 */       this.descent = wi.getDescent(); 
/* 105 */     this.hLeading = (nlh - this.ascent + this.descent) / 2.0D;
/* 106 */     this.baseline = (float)(this.fr.getCurrentY() + this.hLeading + this.ascent);
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean insertWord(WordInfo wi) {
/* 114 */     mergeGlyphGroups(wi);
/*     */     
/* 116 */     if (!assignGlyphGroupRanges(this.newSize, this.newGGIS)) {
/* 117 */       return false;
/*     */     }
/*     */     
/* 120 */     swapGlyphGroupInfo();
/*     */     
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean assignGlyphGroupRanges(int ggSz, GlyphGroupInfo[] ggis) {
/* 128 */     int i = 0, r = 0;
/* 129 */     while (r < this.numRanges) {
/* 130 */       double range = this.ranges[2 * r + 1] - this.ranges[2 * r];
/* 131 */       float adv = 0.0F;
/* 132 */       float rangeAdvance = 0.0F;
/*     */       
/* 134 */       while (i < ggSz) {
/* 135 */         GlyphGroupInfo glyphGroupInfo = ggis[i];
/* 136 */         glyphGroupInfo.setRange(r);
/* 137 */         adv = glyphGroupInfo.getAdvance();
/* 138 */         double delta = range - (rangeAdvance + adv);
/* 139 */         if (delta < 0.0D)
/*     */           break; 
/* 141 */         i++;
/* 142 */         rangeAdvance += adv;
/*     */       } 
/*     */ 
/*     */       
/* 146 */       if (i == ggSz) {
/* 147 */         i--;
/* 148 */         rangeAdvance -= adv;
/*     */       } 
/*     */       
/* 151 */       GlyphGroupInfo ggi = ggis[i];
/* 152 */       float ladv = ggi.getLastAdvance();
/* 153 */       while ((rangeAdvance + ladv) > range) {
/*     */         
/* 155 */         i--;
/* 156 */         ladv = 0.0F;
/* 157 */         if (i < 0)
/* 158 */           break;  ggi = ggis[i];
/* 159 */         if (r != ggi.getRange()) {
/*     */           break;
/*     */         }
/* 162 */         rangeAdvance -= ggi.getAdvance();
/* 163 */         ladv = ggi.getLastAdvance();
/*     */       } 
/*     */       
/* 166 */       i++;
/* 167 */       this.rangeAdv[r] = (rangeAdvance + ladv);
/* 168 */       r++;
/* 169 */       if (i == ggSz) return true; 
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setLineHeight(double lh) {
/* 179 */     this.fr.newLineHeight(lh);
/*     */     
/* 181 */     if (updateRangeInfo()) {
/* 182 */       this.lineHeight = lh;
/* 183 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 187 */     if (this.lineHeight > 0.0D)
/* 188 */       this.fr.newLineHeight(this.lineHeight); 
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public double getCurrentY() {
/* 193 */     return this.fr.getCurrentY();
/*     */   }
/*     */   
/*     */   public boolean gotoY(double y) {
/* 197 */     if (this.fr.gotoY(y))
/* 198 */       return true; 
/* 199 */     if (this.lineHeight > 0.0D)
/* 200 */       updateRangeInfo(); 
/* 201 */     this.baseline = (float)(this.fr.getCurrentY() + this.hLeading + this.ascent);
/* 202 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean updateRangeInfo() {
/* 206 */     this.fr.resetRange();
/* 207 */     int nr = this.fr.getNumRangeOnLine();
/* 208 */     if (nr == 0) {
/* 209 */       return false;
/*     */     }
/* 211 */     this.numRanges = nr;
/*     */     
/* 213 */     if (this.ranges == null) {
/* 214 */       this.rangeAdv = new double[this.numRanges];
/* 215 */       this.ranges = new double[2 * this.numRanges];
/* 216 */     } else if (this.numRanges > this.rangeAdv.length) {
/* 217 */       int sz = 2 * this.rangeAdv.length;
/* 218 */       if (sz < this.numRanges) sz = this.numRanges; 
/* 219 */       this.rangeAdv = new double[sz];
/* 220 */       this.ranges = new double[2 * sz];
/*     */     } 
/*     */     
/* 223 */     for (int r = 0; r < this.numRanges; r++) {
/* 224 */       double[] rangeBounds = this.fr.nextRange();
/*     */ 
/*     */       
/* 227 */       double r0 = rangeBounds[0];
/* 228 */       if (r == 0) {
/* 229 */         double delta = this.bi.getLeftMargin();
/* 230 */         if (this.paraStart) {
/* 231 */           double indent = this.bi.getIndent();
/*     */           
/* 233 */           if (delta < -indent) { delta = 0.0D; }
/* 234 */           else { delta += indent; }
/*     */         
/* 236 */         }  r0 += delta;
/*     */       } 
/*     */       
/* 239 */       double r1 = rangeBounds[1];
/* 240 */       if (r == this.numRanges - 1)
/* 241 */         r1 -= this.bi.getRightMargin(); 
/* 242 */       this.ranges[2 * r] = r0;
/* 243 */       this.ranges[2 * r + 1] = r1;
/*     */     } 
/*     */     
/* 246 */     return true;
/*     */   }
/*     */   
/*     */   protected void swapGlyphGroupInfo() {
/* 250 */     GlyphGroupInfo[] tmp = this.ggis;
/* 251 */     this.ggis = this.newGGIS;
/* 252 */     this.newGGIS = tmp;
/*     */     
/* 254 */     this.size = this.newSize;
/* 255 */     this.newSize = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mergeGlyphGroups(WordInfo wi) {
/* 265 */     int numGG = wi.getNumGlyphGroups();
/* 266 */     this.newSize = 0;
/* 267 */     if (this.ggis == null) {
/*     */       
/* 269 */       this.newSize = numGG;
/* 270 */       this.newGGIS = new GlyphGroupInfo[numGG];
/* 271 */       for (int i = 0; i < numGG; i++) {
/* 272 */         this.newGGIS[i] = wi.getGlyphGroup(i);
/*     */       }
/*     */     } else {
/*     */       
/* 276 */       int s = 0;
/* 277 */       int i = 0;
/* 278 */       GlyphGroupInfo nggi = wi.getGlyphGroup(i);
/* 279 */       int nStart = nggi.getStart();
/*     */       
/* 281 */       GlyphGroupInfo oggi = this.ggis[this.size - 1];
/* 282 */       int oStart = oggi.getStart();
/*     */       
/* 284 */       this.newGGIS = assureSize(this.newGGIS, this.size + numGG);
/* 285 */       if (nStart < oStart) {
/* 286 */         oggi = this.ggis[s];
/* 287 */         oStart = oggi.getStart();
/* 288 */         while (s < this.size && i < numGG) {
/* 289 */           if (nStart < oStart) {
/* 290 */             this.newGGIS[this.newSize++] = nggi;
/* 291 */             i++;
/* 292 */             if (i < numGG) {
/* 293 */               nggi = wi.getGlyphGroup(i);
/* 294 */               nStart = nggi.getStart();
/*     */             }  continue;
/*     */           } 
/* 297 */           this.newGGIS[this.newSize++] = oggi;
/* 298 */           s++;
/* 299 */           if (s < this.size) {
/* 300 */             oggi = this.ggis[s];
/* 301 */             oStart = oggi.getStart();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 306 */       while (s < this.size) {
/* 307 */         this.newGGIS[this.newSize++] = this.ggis[s++];
/*     */       }
/* 309 */       while (i < numGG) {
/* 310 */         this.newGGIS[this.newSize++] = wi.getGlyphGroup(i++);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void layout() {
/* 321 */     if (this.size == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 328 */     assignGlyphGroupRanges(this.size, this.ggis);
/*     */     
/* 330 */     GVTGlyphVector gv = this.ggis[0].getGlyphVector();
/* 331 */     int justType = 0;
/* 332 */     double ggAdv = 0.0D;
/* 333 */     double gAdv = 0.0D;
/*     */ 
/*     */ 
/*     */     
/* 337 */     int[] rangeGG = new int[this.numRanges];
/* 338 */     int[] rangeG = new int[this.numRanges];
/* 339 */     GlyphGroupInfo[] rangeLastGGI = new GlyphGroupInfo[this.numRanges];
/* 340 */     GlyphGroupInfo ggi = this.ggis[0];
/* 341 */     int r = ggi.getRange();
/* 342 */     rangeGG[r] = rangeGG[r] + 1;
/* 343 */     rangeG[r] = rangeG[r] + ggi.getGlyphCount();
/* 344 */     for (int i = 1; i < this.size; i++) {
/* 345 */       ggi = this.ggis[i];
/* 346 */       r = ggi.getRange();
/* 347 */       if (rangeLastGGI[r] == null || !rangeLastGGI[r].getHideLast())
/* 348 */         rangeGG[r] = rangeGG[r] + 1; 
/* 349 */       rangeLastGGI[r] = ggi;
/*     */       
/* 351 */       rangeG[r] = rangeG[r] + ggi.getGlyphCount();
/*     */       
/* 353 */       GlyphGroupInfo pggi = this.ggis[i - 1];
/* 354 */       int pr = pggi.getRange();
/* 355 */       if (r != pr)
/* 356 */         rangeG[pr] = rangeG[pr] + pggi.getLastGlyphCount() - pggi.getGlyphCount(); 
/*     */     } 
/* 358 */     rangeG[r] = rangeG[r] + ggi.getLastGlyphCount() - ggi.getGlyphCount();
/*     */     
/* 360 */     int currRange = -1;
/* 361 */     double locX = 0.0D, range = 0.0D, rAdv = 0.0D;
/* 362 */     r = -1;
/* 363 */     ggi = null;
/* 364 */     for (int j = 0; j < this.size; j++) {
/* 365 */       GlyphGroupInfo pggi = ggi;
/* 366 */       int prevRange = currRange;
/*     */       
/* 368 */       ggi = this.ggis[j];
/* 369 */       currRange = ggi.getRange();
/*     */       
/* 371 */       if (currRange != prevRange) {
/* 372 */         double delta; int numSp; locX = this.ranges[2 * currRange];
/* 373 */         range = this.ranges[2 * currRange + 1] - locX;
/* 374 */         rAdv = this.rangeAdv[currRange];
/* 375 */         int textAlign = this.bi.getTextAlignment();
/* 376 */         if (this.paraEnd && textAlign == 3) {
/* 377 */           textAlign = 0;
/*     */         }
/* 379 */         switch (textAlign) {
/*     */           
/*     */           default:
/* 382 */             delta = range - rAdv;
/* 383 */             if (justType == 0) {
/* 384 */               int k = rangeGG[currRange] - 1;
/* 385 */               if (k >= 1)
/* 386 */                 ggAdv = delta / k;  break;
/*     */             } 
/* 388 */             numSp = rangeG[currRange] - 1;
/* 389 */             if (numSp >= 1) gAdv = delta / numSp;  break;
/*     */           case 0:
/*     */             break;
/*     */           case 1:
/* 393 */             locX += (range - rAdv) / 2.0D; break;
/* 394 */           case 2: locX += range - rAdv; break;
/*     */         } 
/* 396 */       } else if (pggi != null && pggi.getHideLast()) {
/*     */         
/* 398 */         gv.setGlyphVisible(pggi.getEnd(), false);
/*     */       } 
/*     */       
/* 401 */       int start = ggi.getStart();
/* 402 */       int end = ggi.getEnd();
/* 403 */       boolean[] hide = ggi.getHide();
/* 404 */       Point2D p2d = gv.getGlyphPosition(start);
/* 405 */       double deltaX = p2d.getX();
/* 406 */       double advAdj = 0.0D;
/* 407 */       for (int g = start; g <= end; g++) {
/* 408 */         Point2D np2d = gv.getGlyphPosition(g + 1);
/* 409 */         if (hide[g - start]) {
/* 410 */           gv.setGlyphVisible(g, false);
/* 411 */           advAdj += np2d.getX() - p2d.getX();
/*     */         } else {
/* 413 */           gv.setGlyphVisible(g, true);
/*     */         } 
/* 415 */         p2d.setLocation(p2d.getX() - deltaX - advAdj + locX, p2d.getY() + this.baseline);
/*     */         
/* 417 */         gv.setGlyphPosition(g, p2d);
/* 418 */         p2d = np2d;
/* 419 */         advAdj -= gAdv;
/*     */       } 
/* 421 */       if (ggi.getHideLast()) {
/* 422 */         locX += ggi.getAdvance() - advAdj;
/*     */       } else {
/* 424 */         locX += ggi.getAdvance() - advAdj + ggAdv;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static GlyphGroupInfo[] assureSize(GlyphGroupInfo[] ggis, int sz) {
/* 430 */     if (ggis == null) {
/* 431 */       if (sz < 10) sz = 10; 
/* 432 */       return new GlyphGroupInfo[sz];
/*     */     } 
/* 434 */     if (sz <= ggis.length) {
/* 435 */       return ggis;
/*     */     }
/* 437 */     int nsz = ggis.length * 2;
/* 438 */     if (nsz < sz) nsz = sz; 
/* 439 */     return new GlyphGroupInfo[nsz];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/flow/LineInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */