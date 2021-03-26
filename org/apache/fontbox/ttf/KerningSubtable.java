/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class KerningSubtable
/*     */ {
/*  33 */   private static final Log LOG = LogFactory.getLog(KerningSubtable.class);
/*     */ 
/*     */   
/*     */   private static final int COVERAGE_HORIZONTAL = 1;
/*     */ 
/*     */   
/*     */   private static final int COVERAGE_MINIMUMS = 2;
/*     */ 
/*     */   
/*     */   private static final int COVERAGE_CROSS_STREAM = 4;
/*     */ 
/*     */   
/*     */   private static final int COVERAGE_FORMAT = 65280;
/*     */ 
/*     */   
/*     */   private static final int COVERAGE_HORIZONTAL_SHIFT = 0;
/*     */ 
/*     */   
/*     */   private static final int COVERAGE_MINIMUMS_SHIFT = 1;
/*     */ 
/*     */   
/*     */   private static final int COVERAGE_CROSS_STREAM_SHIFT = 2;
/*     */   
/*     */   private static final int COVERAGE_FORMAT_SHIFT = 8;
/*     */   
/*     */   private boolean horizontal;
/*     */   
/*     */   private boolean minimums;
/*     */   
/*     */   private boolean crossStream;
/*     */   
/*     */   private PairData pairs;
/*     */ 
/*     */   
/*     */   public void read(TTFDataStream data, int version) throws IOException {
/*  68 */     if (version == 0) {
/*     */       
/*  70 */       readSubtable0(data);
/*     */     }
/*  72 */     else if (version == 1) {
/*     */       
/*  74 */       readSubtable1(data);
/*     */     }
/*     */     else {
/*     */       
/*  78 */       throw new IllegalStateException();
/*     */     } 
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
/*     */   public boolean isHorizontalKerning() {
/*  91 */     return isHorizontalKerning(false);
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
/*     */   
/*     */   public boolean isHorizontalKerning(boolean cross) {
/* 105 */     if (!this.horizontal)
/*     */     {
/* 107 */       return false;
/*     */     }
/* 109 */     if (this.minimums)
/*     */     {
/* 111 */       return false;
/*     */     }
/* 113 */     if (cross)
/*     */     {
/* 115 */       return this.crossStream;
/*     */     }
/*     */ 
/*     */     
/* 119 */     return !this.crossStream;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getKerning(int[] glyphs) {
/* 135 */     int[] kerning = null;
/* 136 */     if (this.pairs != null) {
/*     */       
/* 138 */       int ng = glyphs.length;
/* 139 */       kerning = new int[ng];
/* 140 */       for (int i = 0; i < ng; i++)
/*     */       {
/* 142 */         int l = glyphs[i];
/* 143 */         int r = -1;
/* 144 */         for (int k = i + 1; k < ng; k++) {
/*     */           
/* 146 */           int g = glyphs[k];
/* 147 */           if (g >= 0) {
/*     */             
/* 149 */             r = g;
/*     */             break;
/*     */           } 
/*     */         } 
/* 153 */         kerning[i] = getKerning(l, r);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 158 */       LOG.warn("No kerning subtable data available due to an unsupported kerning subtable version");
/*     */     } 
/* 160 */     return kerning;
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
/*     */   public int getKerning(int l, int r) {
/* 172 */     if (this.pairs == null) {
/*     */       
/* 174 */       LOG.warn("No kerning subtable data available due to an unsupported kerning subtable version");
/* 175 */       return 0;
/*     */     } 
/* 177 */     return this.pairs.getKerning(l, r);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readSubtable0(TTFDataStream data) throws IOException {
/* 182 */     int version = data.readUnsignedShort();
/* 183 */     if (version != 0) {
/*     */       
/* 185 */       LOG.info("Unsupported kerning sub-table version: " + version);
/*     */       return;
/*     */     } 
/* 188 */     int length = data.readUnsignedShort();
/* 189 */     if (length < 6)
/*     */     {
/* 191 */       throw new IOException("Kerning sub-table too short, got " + length + " bytes, expect 6 or more.");
/*     */     }
/*     */     
/* 194 */     int coverage = data.readUnsignedShort();
/* 195 */     if (isBitsSet(coverage, 1, 0))
/*     */     {
/* 197 */       this.horizontal = true;
/*     */     }
/* 199 */     if (isBitsSet(coverage, 2, 1))
/*     */     {
/* 201 */       this.minimums = true;
/*     */     }
/* 203 */     if (isBitsSet(coverage, 4, 2))
/*     */     {
/* 205 */       this.crossStream = true;
/*     */     }
/* 207 */     int format = getBits(coverage, 65280, 8);
/* 208 */     if (format == 0) {
/*     */       
/* 210 */       readSubtable0Format0(data);
/*     */     }
/* 212 */     else if (format == 2) {
/*     */       
/* 214 */       readSubtable0Format2(data);
/*     */     }
/*     */     else {
/*     */       
/* 218 */       LOG.debug("Skipped kerning subtable due to an unsupported kerning subtable version: " + format);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void readSubtable0Format0(TTFDataStream data) throws IOException {
/* 224 */     this.pairs = new PairData0Format0();
/* 225 */     this.pairs.read(data);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readSubtable0Format2(TTFDataStream data) throws IOException {
/* 230 */     LOG.info("Kerning subtable format 2 not yet supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   private void readSubtable1(TTFDataStream data) throws IOException {
/* 235 */     LOG.info("Kerning subtable format 1 not yet supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isBitsSet(int bits, int mask, int shift) {
/* 240 */     return (getBits(bits, mask, shift) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getBits(int bits, int mask, int shift) {
/* 245 */     return (bits & mask) >> shift;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class PairData0Format0
/*     */     implements Comparator<int[]>, PairData
/*     */   {
/*     */     private int searchRange;
/*     */ 
/*     */     
/*     */     private int[][] pairs;
/*     */ 
/*     */     
/*     */     private PairData0Format0() {}
/*     */ 
/*     */     
/*     */     public void read(TTFDataStream data) throws IOException {
/* 263 */       int numPairs = data.readUnsignedShort();
/* 264 */       this.searchRange = data.readUnsignedShort() / 6;
/* 265 */       int entrySelector = data.readUnsignedShort();
/* 266 */       int rangeShift = data.readUnsignedShort();
/* 267 */       this.pairs = new int[numPairs][3];
/* 268 */       for (int i = 0; i < numPairs; i++) {
/*     */         
/* 270 */         int left = data.readUnsignedShort();
/* 271 */         int right = data.readUnsignedShort();
/* 272 */         int value = data.readSignedShort();
/* 273 */         this.pairs[i][0] = left;
/* 274 */         this.pairs[i][1] = right;
/* 275 */         this.pairs[i][2] = value;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getKerning(int l, int r) {
/* 282 */       int[] key = { l, r, 0 };
/* 283 */       int index = Arrays.binarySearch(this.pairs, key, this);
/* 284 */       if (index >= 0)
/*     */       {
/* 286 */         return this.pairs[index][2];
/*     */       }
/* 288 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(int[] p1, int[] p2) {
/* 294 */       assert p1 != null;
/* 295 */       assert p1.length >= 2;
/* 296 */       assert p2 != null;
/* 297 */       assert p2.length >= 2;
/* 298 */       int l1 = p1[0];
/* 299 */       int l2 = p2[0];
/* 300 */       if (l1 < l2)
/*     */       {
/* 302 */         return -1;
/*     */       }
/* 304 */       if (l1 > l2)
/*     */       {
/* 306 */         return 1;
/*     */       }
/*     */ 
/*     */       
/* 310 */       int r1 = p1[1];
/* 311 */       int r2 = p2[1];
/* 312 */       if (r1 < r2)
/*     */       {
/* 314 */         return -1;
/*     */       }
/* 316 */       if (r1 > r2)
/*     */       {
/* 318 */         return 1;
/*     */       }
/*     */ 
/*     */       
/* 322 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   private static interface PairData {
/*     */     void read(TTFDataStream param1TTFDataStream) throws IOException;
/*     */     
/*     */     int getKerning(int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/KerningSubtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */