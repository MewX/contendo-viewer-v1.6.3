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
/*     */ public class GlyphTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "glyf";
/*     */   private GlyphData[] glyphs;
/*     */   private TTFDataStream data;
/*     */   private IndexToLocationTable loca;
/*     */   private int numGlyphs;
/*  40 */   private int cached = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int MAX_CACHE_SIZE = 5000;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int MAX_CACHED_GLYPHS = 100;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GlyphTable(TrueTypeFont font) {
/*  54 */     super(font);
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
/*  67 */     this.loca = ttf.getIndexToLocation();
/*  68 */     this.numGlyphs = ttf.getNumberOfGlyphs();
/*     */     
/*  70 */     if (this.numGlyphs < 5000)
/*     */     {
/*     */       
/*  73 */       this.glyphs = new GlyphData[this.numGlyphs];
/*     */     }
/*     */ 
/*     */     
/*  77 */     this.data = data;
/*  78 */     this.initialized = true;
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
/*     */   public GlyphData[] getGlyphs() throws IOException {
/*  90 */     synchronized (this.data) {
/*     */ 
/*     */       
/*  93 */       long[] offsets = this.loca.getOffsets();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  99 */       long endOfGlyphs = offsets[this.numGlyphs];
/* 100 */       long offset = getOffset();
/* 101 */       if (this.glyphs == null)
/*     */       {
/* 103 */         this.glyphs = new GlyphData[this.numGlyphs];
/*     */       }
/*     */       
/* 106 */       for (int gid = 0; gid < this.numGlyphs; gid++) {
/*     */ 
/*     */         
/* 109 */         if (endOfGlyphs != 0L && endOfGlyphs == offsets[gid]) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 115 */         if (offsets[gid + 1] > offsets[gid])
/*     */         {
/*     */ 
/*     */           
/* 119 */           if (this.glyphs[gid] == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 125 */             this.data.seek(offset + offsets[gid]);
/*     */             
/* 127 */             if (this.glyphs[gid] == null)
/*     */             {
/* 129 */               this.cached++;
/*     */             }
/* 131 */             this.glyphs[gid] = getGlyphData(gid);
/*     */           }  } 
/* 133 */       }  this.initialized = true;
/* 134 */       return this.glyphs;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphs(GlyphData[] glyphsValue) {
/* 143 */     this.glyphs = glyphsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphData getGlyph(int gid) throws IOException {
/* 154 */     if (gid < 0 || gid >= this.numGlyphs)
/*     */     {
/* 156 */       return null;
/*     */     }
/*     */     
/* 159 */     if (this.glyphs != null && this.glyphs[gid] != null)
/*     */     {
/* 161 */       return this.glyphs[gid];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 166 */     synchronized (this.data) {
/*     */ 
/*     */       
/* 169 */       long[] offsets = this.loca.getOffsets();
/*     */       
/* 171 */       if (offsets[gid] == offsets[gid + 1])
/*     */       {
/*     */         
/* 174 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 178 */       long currentPosition = this.data.getCurrentPosition();
/*     */       
/* 180 */       this.data.seek(getOffset() + offsets[gid]);
/*     */       
/* 182 */       GlyphData glyph = getGlyphData(gid);
/*     */ 
/*     */       
/* 185 */       this.data.seek(currentPosition);
/*     */       
/* 187 */       if (this.glyphs != null && this.glyphs[gid] == null && this.cached < 100) {
/*     */         
/* 189 */         this.glyphs[gid] = glyph;
/* 190 */         this.cached++;
/*     */       } 
/*     */       
/* 193 */       return glyph;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private GlyphData getGlyphData(int gid) throws IOException {
/* 199 */     GlyphData glyph = new GlyphData();
/* 200 */     HorizontalMetricsTable hmt = this.font.getHorizontalMetrics();
/* 201 */     int leftSideBearing = (hmt == null) ? 0 : hmt.getLeftSideBearing(gid);
/* 202 */     glyph.initData(this, this.data, leftSideBearing);
/*     */     
/* 204 */     if (glyph.getDescription().isComposite())
/*     */     {
/* 206 */       glyph.getDescription().resolve();
/*     */     }
/* 208 */     return glyph;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyphTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */