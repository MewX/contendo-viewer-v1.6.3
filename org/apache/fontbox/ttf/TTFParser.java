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
/*     */ public class TTFParser
/*     */ {
/*     */   private boolean isEmbedded = false;
/*     */   private boolean parseOnDemandOnly = false;
/*     */   
/*     */   public TTFParser() {
/*  38 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TTFParser(boolean isEmbedded) {
/*  48 */     this(isEmbedded, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TTFParser(boolean isEmbedded, boolean parseOnDemand) {
/*  59 */     this.isEmbedded = isEmbedded;
/*  60 */     this.parseOnDemandOnly = parseOnDemand;
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
/*     */   public TrueTypeFont parse(String ttfFile) throws IOException {
/*  72 */     return parse(new File(ttfFile));
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
/*     */   public TrueTypeFont parse(File ttfFile) throws IOException {
/*  84 */     RAFDataStream raf = new RAFDataStream(ttfFile, "r");
/*     */     
/*     */     try {
/*  87 */       return parse(raf);
/*     */     }
/*  89 */     catch (IOException ex) {
/*     */ 
/*     */       
/*  92 */       raf.close();
/*  93 */       throw ex;
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
/*     */   public TrueTypeFont parse(InputStream inputStream) throws IOException {
/* 106 */     return parse(new MemoryTTFDataStream(inputStream));
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
/*     */   public TrueTypeFont parseEmbedded(InputStream inputStream) throws IOException {
/* 118 */     this.isEmbedded = true;
/* 119 */     return parse(new MemoryTTFDataStream(inputStream));
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
/*     */   TrueTypeFont parse(TTFDataStream raf) throws IOException {
/* 131 */     TrueTypeFont font = newFont(raf);
/* 132 */     font.setVersion(raf.read32Fixed());
/* 133 */     int numberOfTables = raf.readUnsignedShort();
/* 134 */     int searchRange = raf.readUnsignedShort();
/* 135 */     int entrySelector = raf.readUnsignedShort();
/* 136 */     int rangeShift = raf.readUnsignedShort();
/* 137 */     for (int i = 0; i < numberOfTables; i++) {
/*     */       
/* 139 */       TTFTable table = readTableDirectory(font, raf);
/*     */ 
/*     */       
/* 142 */       if (table != null)
/*     */       {
/* 144 */         font.addTable(table);
/*     */       }
/*     */     } 
/*     */     
/* 148 */     if (!this.parseOnDemandOnly)
/*     */     {
/* 150 */       parseTables(font);
/*     */     }
/*     */     
/* 153 */     return font;
/*     */   }
/*     */ 
/*     */   
/*     */   TrueTypeFont newFont(TTFDataStream raf) {
/* 158 */     return new TrueTypeFont(raf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseTables(TrueTypeFont font) throws IOException {
/* 169 */     for (TTFTable table : font.getTables()) {
/*     */       
/* 171 */       if (!table.getInitialized())
/*     */       {
/* 173 */         font.readTable(table);
/*     */       }
/*     */     } 
/*     */     
/* 177 */     boolean isPostScript = (allowCFF() && font.tables.containsKey("CFF "));
/*     */     
/* 179 */     HeaderTable head = font.getHeader();
/* 180 */     if (head == null)
/*     */     {
/* 182 */       throw new IOException("head is mandatory");
/*     */     }
/*     */     
/* 185 */     HorizontalHeaderTable hh = font.getHorizontalHeader();
/* 186 */     if (hh == null)
/*     */     {
/* 188 */       throw new IOException("hhead is mandatory");
/*     */     }
/*     */     
/* 191 */     MaximumProfileTable maxp = font.getMaximumProfile();
/* 192 */     if (maxp == null)
/*     */     {
/* 194 */       throw new IOException("maxp is mandatory");
/*     */     }
/*     */     
/* 197 */     PostScriptTable post = font.getPostScript();
/* 198 */     if (post == null && !this.isEmbedded)
/*     */     {
/*     */       
/* 201 */       throw new IOException("post is mandatory");
/*     */     }
/*     */     
/* 204 */     if (!isPostScript) {
/*     */       
/* 206 */       IndexToLocationTable loc = font.getIndexToLocation();
/* 207 */       if (loc == null)
/*     */       {
/* 209 */         throw new IOException("loca is mandatory");
/*     */       }
/*     */       
/* 212 */       if (font.getGlyph() == null)
/*     */       {
/* 214 */         throw new IOException("glyf is mandatory");
/*     */       }
/*     */     } 
/*     */     
/* 218 */     if (font.getNaming() == null && !this.isEmbedded)
/*     */     {
/* 220 */       throw new IOException("name is mandatory");
/*     */     }
/*     */     
/* 223 */     if (font.getHorizontalMetrics() == null)
/*     */     {
/* 225 */       throw new IOException("hmtx is mandatory");
/*     */     }
/*     */     
/* 228 */     if (!this.isEmbedded && font.getCmap() == null)
/*     */     {
/* 230 */       throw new IOException("cmap is mandatory");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean allowCFF() {
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private TTFTable readTableDirectory(TrueTypeFont font, TTFDataStream raf) throws IOException {
/*     */     TTFTable table;
/* 242 */     String tag = raf.readString(4);
/* 243 */     if (tag.equals("cmap")) {
/*     */       
/* 245 */       table = new CmapTable(font);
/*     */     }
/* 247 */     else if (tag.equals("glyf")) {
/*     */       
/* 249 */       table = new GlyphTable(font);
/*     */     }
/* 251 */     else if (tag.equals("head")) {
/*     */       
/* 253 */       table = new HeaderTable(font);
/*     */     }
/* 255 */     else if (tag.equals("hhea")) {
/*     */       
/* 257 */       table = new HorizontalHeaderTable(font);
/*     */     }
/* 259 */     else if (tag.equals("hmtx")) {
/*     */       
/* 261 */       table = new HorizontalMetricsTable(font);
/*     */     }
/* 263 */     else if (tag.equals("loca")) {
/*     */       
/* 265 */       table = new IndexToLocationTable(font);
/*     */     }
/* 267 */     else if (tag.equals("maxp")) {
/*     */       
/* 269 */       table = new MaximumProfileTable(font);
/*     */     }
/* 271 */     else if (tag.equals("name")) {
/*     */       
/* 273 */       table = new NamingTable(font);
/*     */     }
/* 275 */     else if (tag.equals("OS/2")) {
/*     */       
/* 277 */       table = new OS2WindowsMetricsTable(font);
/*     */     }
/* 279 */     else if (tag.equals("post")) {
/*     */       
/* 281 */       table = new PostScriptTable(font);
/*     */     }
/* 283 */     else if (tag.equals("DSIG")) {
/*     */       
/* 285 */       table = new DigitalSignatureTable(font);
/*     */     }
/* 287 */     else if (tag.equals("kern")) {
/*     */       
/* 289 */       table = new KerningTable(font);
/*     */     }
/* 291 */     else if (tag.equals("vhea")) {
/*     */       
/* 293 */       table = new VerticalHeaderTable(font);
/*     */     }
/* 295 */     else if (tag.equals("vmtx")) {
/*     */       
/* 297 */       table = new VerticalMetricsTable(font);
/*     */     }
/* 299 */     else if (tag.equals("VORG")) {
/*     */       
/* 301 */       table = new VerticalOriginTable(font);
/*     */     }
/* 303 */     else if (tag.equals("GSUB")) {
/*     */       
/* 305 */       table = new GlyphSubstitutionTable(font);
/*     */     }
/*     */     else {
/*     */       
/* 309 */       table = readTable(font, tag);
/*     */     } 
/* 311 */     table.setTag(tag);
/* 312 */     table.setCheckSum(raf.readUnsignedInt());
/* 313 */     table.setOffset(raf.readUnsignedInt());
/* 314 */     table.setLength(raf.readUnsignedInt());
/*     */ 
/*     */     
/* 317 */     if (table.getLength() == 0L && !tag.equals("glyf"))
/*     */     {
/* 319 */       return null;
/*     */     }
/*     */     
/* 322 */     return table;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected TTFTable readTable(TrueTypeFont font, String tag) {
/* 328 */     return new TTFTable(font);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/TTFParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */