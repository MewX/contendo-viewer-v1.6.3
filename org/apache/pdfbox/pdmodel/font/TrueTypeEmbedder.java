/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.fontbox.ttf.CmapLookup;
/*     */ import org.apache.fontbox.ttf.CmapSubtable;
/*     */ import org.apache.fontbox.ttf.HeaderTable;
/*     */ import org.apache.fontbox.ttf.HorizontalHeaderTable;
/*     */ import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
/*     */ import org.apache.fontbox.ttf.PostScriptTable;
/*     */ import org.apache.fontbox.ttf.TTFParser;
/*     */ import org.apache.fontbox.ttf.TTFSubsetter;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*     */ abstract class TrueTypeEmbedder
/*     */   implements Subsetter
/*     */ {
/*     */   private static final int ITALIC = 1;
/*     */   private static final int OBLIQUE = 512;
/*     */   private static final String BASE25 = "BCDEFGHIJKLMNOPQRSTUVWXYZ";
/*     */   private final PDDocument document;
/*     */   protected TrueTypeFont ttf;
/*     */   protected PDFontDescriptor fontDescriptor;
/*     */   @Deprecated
/*     */   protected final CmapSubtable cmap;
/*     */   protected final CmapLookup cmapLookup;
/*  71 */   private final Set<Integer> subsetCodePoints = new HashSet<Integer>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean embedSubset;
/*     */ 
/*     */ 
/*     */   
/*     */   TrueTypeEmbedder(PDDocument document, COSDictionary dict, TrueTypeFont ttf, boolean embedSubset) throws IOException {
/*  80 */     this.document = document;
/*  81 */     this.embedSubset = embedSubset;
/*  82 */     this.ttf = ttf;
/*  83 */     this.fontDescriptor = createFontDescriptor(ttf);
/*     */     
/*  85 */     if (!isEmbeddingPermitted(ttf))
/*     */     {
/*  87 */       throw new IOException("This font does not permit embedding");
/*     */     }
/*     */     
/*  90 */     if (!embedSubset) {
/*     */ 
/*     */       
/*  93 */       PDStream stream = new PDStream(document, ttf.getOriginalData(), COSName.FLATE_DECODE);
/*  94 */       stream.getCOSObject().setLong(COSName.LENGTH1, ttf.getOriginalDataSize());
/*  95 */       this.fontDescriptor.setFontFile2(stream);
/*     */     } 
/*     */     
/*  98 */     dict.setName(COSName.BASE_FONT, ttf.getName());
/*     */ 
/*     */     
/* 101 */     this.cmap = ttf.getUnicodeCmap();
/* 102 */     this.cmapLookup = ttf.getUnicodeCmapLookup();
/*     */   }
/*     */   
/*     */   public void buildFontFile2(InputStream ttfStream) throws IOException {
/*     */     COSInputStream cOSInputStream;
/* 107 */     PDStream stream = new PDStream(this.document, ttfStream, COSName.FLATE_DECODE);
/*     */ 
/*     */     
/* 110 */     InputStream input = null;
/*     */     
/*     */     try {
/* 113 */       cOSInputStream = stream.createInputStream();
/* 114 */       this.ttf = (new TTFParser()).parseEmbedded((InputStream)cOSInputStream);
/* 115 */       if (!isEmbeddingPermitted(this.ttf))
/*     */       {
/* 117 */         throw new IOException("This font does not permit embedding");
/*     */       }
/* 119 */       if (this.fontDescriptor == null)
/*     */       {
/* 121 */         this.fontDescriptor = createFontDescriptor(this.ttf);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 126 */       IOUtils.closeQuietly((Closeable)cOSInputStream);
/*     */     } 
/* 128 */     stream.getCOSObject().setLong(COSName.LENGTH1, this.ttf.getOriginalDataSize());
/* 129 */     this.fontDescriptor.setFontFile2(stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isEmbeddingPermitted(TrueTypeFont ttf) throws IOException {
/* 137 */     if (ttf.getOS2Windows() != null) {
/*     */       
/* 139 */       int fsType = ttf.getOS2Windows().getFsType();
/* 140 */       int exclusive = fsType & 0x8;
/*     */       
/* 142 */       if ((exclusive & 0x1) == 1)
/*     */       {
/*     */ 
/*     */         
/* 146 */         return false;
/*     */       }
/* 148 */       if ((exclusive & 0x200) == 512)
/*     */       {
/*     */ 
/*     */         
/* 152 */         return false;
/*     */       }
/*     */     } 
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSubsettingPermitted(TrueTypeFont ttf) throws IOException {
/* 163 */     if (ttf.getOS2Windows() != null) {
/*     */       
/* 165 */       int fsType = ttf.getOS2Windows().getFsType();
/* 166 */       if ((fsType & 0x100) == 256)
/*     */       {
/*     */         
/* 169 */         return false;
/*     */       }
/*     */     } 
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDFontDescriptor createFontDescriptor(TrueTypeFont ttf) throws IOException {
/* 180 */     PDFontDescriptor fd = new PDFontDescriptor();
/* 181 */     fd.setFontName(ttf.getName());
/*     */     
/* 183 */     OS2WindowsMetricsTable os2 = ttf.getOS2Windows();
/* 184 */     PostScriptTable post = ttf.getPostScript();
/*     */ 
/*     */     
/* 187 */     fd.setFixedPitch((post.getIsFixedPitch() > 0L || ttf
/* 188 */         .getHorizontalHeader().getNumberOfHMetrics() == 1));
/*     */     
/* 190 */     int fsSelection = os2.getFsSelection();
/* 191 */     fd.setItalic(((fsSelection & 0x201) != 0));
/*     */     
/* 193 */     switch (os2.getFamilyClass()) {
/*     */       
/*     */       case 1:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 7:
/* 200 */         fd.setSerif(true);
/*     */         break;
/*     */       case 10:
/* 203 */         fd.setScript(true);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 209 */     fd.setFontWeight(os2.getWeightClass());
/*     */     
/* 211 */     fd.setSymbolic(true);
/* 212 */     fd.setNonSymbolic(false);
/*     */ 
/*     */     
/* 215 */     fd.setItalicAngle(post.getItalicAngle());
/*     */ 
/*     */     
/* 218 */     HeaderTable header = ttf.getHeader();
/* 219 */     PDRectangle rect = new PDRectangle();
/* 220 */     float scaling = 1000.0F / header.getUnitsPerEm();
/* 221 */     rect.setLowerLeftX(header.getXMin() * scaling);
/* 222 */     rect.setLowerLeftY(header.getYMin() * scaling);
/* 223 */     rect.setUpperRightX(header.getXMax() * scaling);
/* 224 */     rect.setUpperRightY(header.getYMax() * scaling);
/* 225 */     fd.setFontBoundingBox(rect);
/*     */ 
/*     */     
/* 228 */     HorizontalHeaderTable hHeader = ttf.getHorizontalHeader();
/* 229 */     fd.setAscent(hHeader.getAscender() * scaling);
/* 230 */     fd.setDescent(hHeader.getDescender() * scaling);
/*     */ 
/*     */     
/* 233 */     if (os2.getVersion() >= 1.2D) {
/*     */       
/* 235 */       fd.setCapHeight(os2.getCapHeight() * scaling);
/* 236 */       fd.setXHeight(os2.getHeight() * scaling);
/*     */     }
/*     */     else {
/*     */       
/* 240 */       GeneralPath capHPath = ttf.getPath("H");
/* 241 */       if (capHPath != null) {
/*     */         
/* 243 */         fd.setCapHeight((float)Math.round(capHPath.getBounds2D().getMaxY()) * scaling);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 248 */         fd.setCapHeight((os2.getTypoAscender() + os2.getTypoDescender()) * scaling);
/*     */       } 
/* 250 */       GeneralPath xPath = ttf.getPath("x");
/* 251 */       if (xPath != null) {
/*     */         
/* 253 */         fd.setXHeight((float)Math.round(xPath.getBounds2D().getMaxY()) * scaling);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 258 */         fd.setXHeight(os2.getTypoAscender() / 2.0F * scaling);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 263 */     fd.setStemV(fd.getFontBoundingBox().getWidth() * 0.13F);
/*     */     
/* 265 */     return fd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public TrueTypeFont getTrueTypeFont() {
/* 276 */     return this.ttf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontDescriptor getFontDescriptor() {
/* 284 */     return this.fontDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToSubset(int codePoint) {
/* 290 */     this.subsetCodePoints.add(Integer.valueOf(codePoint));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void subset() throws IOException {
/* 296 */     if (!isSubsettingPermitted(this.ttf))
/*     */     {
/* 298 */       throw new IOException("This font does not permit subsetting");
/*     */     }
/*     */     
/* 301 */     if (!this.embedSubset)
/*     */     {
/* 303 */       throw new IllegalStateException("Subsetting is disabled");
/*     */     }
/*     */ 
/*     */     
/* 307 */     List<String> tables = new ArrayList<String>();
/* 308 */     tables.add("head");
/* 309 */     tables.add("hhea");
/* 310 */     tables.add("loca");
/* 311 */     tables.add("maxp");
/* 312 */     tables.add("cvt ");
/* 313 */     tables.add("prep");
/* 314 */     tables.add("glyf");
/* 315 */     tables.add("hmtx");
/* 316 */     tables.add("fpgm");
/*     */     
/* 318 */     tables.add("gasp");
/*     */ 
/*     */     
/* 321 */     TTFSubsetter subsetter = new TTFSubsetter(this.ttf, tables);
/* 322 */     subsetter.addAll(this.subsetCodePoints);
/*     */ 
/*     */     
/* 325 */     Map<Integer, Integer> gidToCid = subsetter.getGIDMap();
/* 326 */     String tag = getTag(gidToCid);
/* 327 */     subsetter.setPrefix(tag);
/*     */ 
/*     */     
/* 330 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 331 */     subsetter.writeToStream(out);
/*     */ 
/*     */     
/* 334 */     buildSubset(new ByteArrayInputStream(out.toByteArray()), tag, gidToCid);
/* 335 */     this.ttf.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean needsSubset() {
/* 343 */     return this.embedSubset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void buildSubset(InputStream paramInputStream, String paramString, Map<Integer, Integer> paramMap) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTag(Map<Integer, Integer> gidToCid) {
/* 358 */     long num = gidToCid.hashCode();
/*     */ 
/*     */     
/* 361 */     StringBuilder sb = new StringBuilder();
/*     */     
/*     */     do {
/* 364 */       long div = num / 25L;
/* 365 */       int mod = (int)(num % 25L);
/* 366 */       sb.append("BCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(mod));
/* 367 */       num = div;
/* 368 */     } while (num != 0L && sb.length() < 6);
/*     */ 
/*     */     
/* 371 */     while (sb.length() < 6)
/*     */     {
/* 373 */       sb.insert(0, 'A');
/*     */     }
/*     */     
/* 376 */     sb.append('+');
/* 377 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/TrueTypeEmbedder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */