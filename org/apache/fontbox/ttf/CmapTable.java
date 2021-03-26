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
/*     */ public class CmapTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "cmap";
/*     */   public static final int PLATFORM_UNICODE = 0;
/*     */   public static final int PLATFORM_MACINTOSH = 1;
/*     */   public static final int PLATFORM_WINDOWS = 3;
/*     */   public static final int ENCODING_MAC_ROMAN = 0;
/*     */   public static final int ENCODING_WIN_SYMBOL = 0;
/*     */   public static final int ENCODING_WIN_UNICODE_BMP = 1;
/*     */   public static final int ENCODING_WIN_SHIFT_JIS = 2;
/*     */   public static final int ENCODING_WIN_BIG5 = 3;
/*     */   public static final int ENCODING_WIN_PRC = 4;
/*     */   public static final int ENCODING_WIN_WANSUNG = 5;
/*     */   public static final int ENCODING_WIN_JOHAB = 6;
/*     */   public static final int ENCODING_WIN_UNICODE_FULL = 10;
/*     */   public static final int ENCODING_UNICODE_1_0 = 0;
/*     */   public static final int ENCODING_UNICODE_1_1 = 1;
/*     */   public static final int ENCODING_UNICODE_2_0_BMP = 3;
/*     */   public static final int ENCODING_UNICODE_2_0_FULL = 4;
/*     */   private CmapSubtable[] cmaps;
/*     */   
/*     */   CmapTable(TrueTypeFont font) {
/*  61 */     super(font);
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
/*     */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  75 */     int version = data.readUnsignedShort();
/*  76 */     int numberOfTables = data.readUnsignedShort();
/*  77 */     this.cmaps = new CmapSubtable[numberOfTables]; int i;
/*  78 */     for (i = 0; i < numberOfTables; i++) {
/*     */       
/*  80 */       CmapSubtable cmap = new CmapSubtable();
/*  81 */       cmap.initData(data);
/*  82 */       this.cmaps[i] = cmap;
/*     */     } 
/*  84 */     for (i = 0; i < numberOfTables; i++)
/*     */     {
/*  86 */       this.cmaps[i].initSubtable(this, ttf.getNumberOfGlyphs(), data);
/*     */     }
/*  88 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CmapSubtable[] getCmaps() {
/*  96 */     return this.cmaps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCmaps(CmapSubtable[] cmapsValue) {
/* 104 */     this.cmaps = cmapsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CmapSubtable getSubtable(int platformId, int platformEncodingId) {
/* 112 */     for (CmapSubtable cmap : this.cmaps) {
/*     */       
/* 114 */       if (cmap.getPlatformId() == platformId && cmap
/* 115 */         .getPlatformEncodingId() == platformEncodingId)
/*     */       {
/* 117 */         return cmap;
/*     */       }
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/CmapTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */