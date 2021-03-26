/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public final class OTFParser
/*     */   extends TTFParser
/*     */ {
/*     */   public OTFParser() {}
/*     */   
/*     */   public OTFParser(boolean isEmbedded) {
/*  44 */     this(isEmbedded, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OTFParser(boolean isEmbedded, boolean parseOnDemand) {
/*  55 */     super(isEmbedded, parseOnDemand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OpenTypeFont parse(String file) throws IOException {
/*  61 */     return (OpenTypeFont)super.parse(file);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OpenTypeFont parse(File file) throws IOException {
/*  67 */     return (OpenTypeFont)super.parse(file);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OpenTypeFont parse(InputStream data) throws IOException {
/*  73 */     return (OpenTypeFont)super.parse(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   OpenTypeFont parse(TTFDataStream raf) throws IOException {
/*  79 */     return (OpenTypeFont)super.parse(raf);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   OpenTypeFont newFont(TTFDataStream raf) {
/*  85 */     return new OpenTypeFont(raf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TTFTable readTable(TrueTypeFont font, String tag) {
/*  93 */     if (tag.equals("BASE") || tag.equals("GDEF") || tag.equals("GPOS") || tag
/*  94 */       .equals("GSUB") || tag.equals("JSTF"))
/*     */     {
/*  96 */       return new OTLTable(font);
/*     */     }
/*  98 */     if (tag.equals("CFF "))
/*     */     {
/* 100 */       return new CFFTable(font);
/*     */     }
/*     */ 
/*     */     
/* 104 */     return super.readTable(font, tag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean allowCFF() {
/* 111 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/OTFParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */