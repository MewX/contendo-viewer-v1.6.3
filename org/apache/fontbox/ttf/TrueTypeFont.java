/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.util.BoundingBox;
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
/*     */ public class TrueTypeFont
/*     */   implements Closeable, FontBoxFont
/*     */ {
/*     */   private float version;
/*  41 */   private int numberOfGlyphs = -1;
/*  42 */   private int unitsPerEm = -1;
/*  43 */   protected Map<String, TTFTable> tables = new HashMap<String, TTFTable>();
/*     */   private final TTFDataStream data;
/*     */   private Map<String, Integer> postScriptNames;
/*  46 */   private final List<String> enabledGsubFeatures = new ArrayList<String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TrueTypeFont(TTFDataStream fontData) {
/*  55 */     this.data = fontData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  61 */     this.data.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVersion() {
/*  69 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setVersion(float versionValue) {
/*  78 */     this.version = versionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addTable(TTFTable table) {
/*  88 */     this.tables.put(table.getTag(), table);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TTFTable> getTables() {
/*  98 */     return this.tables.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, TTFTable> getTableMap() {
/* 108 */     return this.tables;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized byte[] getTableBytes(TTFTable table) throws IOException {
/* 119 */     long currentPosition = this.data.getCurrentPosition();
/* 120 */     this.data.seek(table.getOffset());
/*     */ 
/*     */     
/* 123 */     byte[] bytes = this.data.read((int)table.getLength());
/*     */ 
/*     */     
/* 126 */     this.data.seek(currentPosition);
/* 127 */     return bytes;
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
/*     */   protected synchronized TTFTable getTable(String tag) throws IOException {
/* 139 */     TTFTable ttfTable = this.tables.get(tag);
/* 140 */     if (ttfTable != null && !ttfTable.getInitialized())
/*     */     {
/* 142 */       readTable(ttfTable);
/*     */     }
/* 144 */     return ttfTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamingTable getNaming() throws IOException {
/* 155 */     return (NamingTable)getTable("name");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PostScriptTable getPostScript() throws IOException {
/* 166 */     return (PostScriptTable)getTable("post");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OS2WindowsMetricsTable getOS2Windows() throws IOException {
/* 177 */     return (OS2WindowsMetricsTable)getTable("OS/2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MaximumProfileTable getMaximumProfile() throws IOException {
/* 188 */     return (MaximumProfileTable)getTable("maxp");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderTable getHeader() throws IOException {
/* 199 */     return (HeaderTable)getTable("head");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HorizontalHeaderTable getHorizontalHeader() throws IOException {
/* 210 */     return (HorizontalHeaderTable)getTable("hhea");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HorizontalMetricsTable getHorizontalMetrics() throws IOException {
/* 221 */     return (HorizontalMetricsTable)getTable("hmtx");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndexToLocationTable getIndexToLocation() throws IOException {
/* 232 */     return (IndexToLocationTable)getTable("loca");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphTable getGlyph() throws IOException {
/* 243 */     return (GlyphTable)getTable("glyf");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CmapTable getCmap() throws IOException {
/* 254 */     return (CmapTable)getTable("cmap");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerticalHeaderTable getVerticalHeader() throws IOException {
/* 265 */     return (VerticalHeaderTable)getTable("vhea");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerticalMetricsTable getVerticalMetrics() throws IOException {
/* 276 */     return (VerticalMetricsTable)getTable("vmtx");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerticalOriginTable getVerticalOrigin() throws IOException {
/* 287 */     return (VerticalOriginTable)getTable("VORG");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KerningTable getKerning() throws IOException {
/* 298 */     return (KerningTable)getTable("kern");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphSubstitutionTable getGsub() throws IOException {
/* 309 */     return (GlyphSubstitutionTable)getTable("GSUB");
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
/*     */   public InputStream getOriginalData() throws IOException {
/* 323 */     return this.data.getOriginalData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getOriginalDataSize() {
/* 334 */     return this.data.getOriginalDataSize();
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
/*     */   void readTable(TTFTable table) throws IOException {
/* 348 */     synchronized (this.data) {
/*     */ 
/*     */       
/* 351 */       long currentPosition = this.data.getCurrentPosition();
/* 352 */       this.data.seek(table.getOffset());
/* 353 */       table.read(this, this.data);
/*     */       
/* 355 */       this.data.seek(currentPosition);
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
/*     */   public int getNumberOfGlyphs() throws IOException {
/* 367 */     if (this.numberOfGlyphs == -1) {
/*     */       
/* 369 */       MaximumProfileTable maximumProfile = getMaximumProfile();
/* 370 */       if (maximumProfile != null) {
/*     */         
/* 372 */         this.numberOfGlyphs = maximumProfile.getNumGlyphs();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 377 */         this.numberOfGlyphs = 0;
/*     */       } 
/*     */     } 
/* 380 */     return this.numberOfGlyphs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUnitsPerEm() throws IOException {
/* 391 */     if (this.unitsPerEm == -1) {
/*     */       
/* 393 */       HeaderTable header = getHeader();
/* 394 */       if (header != null) {
/*     */         
/* 396 */         this.unitsPerEm = header.getUnitsPerEm();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 401 */         this.unitsPerEm = 0;
/*     */       } 
/*     */     } 
/* 404 */     return this.unitsPerEm;
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
/*     */   public int getAdvanceWidth(int gid) throws IOException {
/* 416 */     HorizontalMetricsTable hmtx = getHorizontalMetrics();
/* 417 */     if (hmtx != null)
/*     */     {
/* 419 */       return hmtx.getAdvanceWidth(gid);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 424 */     return 250;
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
/*     */   public int getAdvanceHeight(int gid) throws IOException {
/* 437 */     VerticalMetricsTable vmtx = getVerticalMetrics();
/* 438 */     if (vmtx != null)
/*     */     {
/* 440 */       return vmtx.getAdvanceHeight(gid);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 445 */     return 250;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() throws IOException {
/* 452 */     if (getNaming() != null)
/*     */     {
/* 454 */       return getNaming().getPostScriptName();
/*     */     }
/*     */ 
/*     */     
/* 458 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void readPostScriptNames() throws IOException {
/* 464 */     if (this.postScriptNames == null && getPostScript() != null) {
/*     */       
/* 466 */       String[] names = getPostScript().getGlyphNames();
/* 467 */       if (names != null) {
/*     */         
/* 469 */         this.postScriptNames = new HashMap<String, Integer>(names.length);
/* 470 */         for (int i = 0; i < names.length; i++)
/*     */         {
/* 472 */           this.postScriptNames.put(names[i], Integer.valueOf(i));
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 477 */         this.postScriptNames = new HashMap<String, Integer>();
/*     */       } 
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
/*     */   @Deprecated
/*     */   public CmapSubtable getUnicodeCmap() throws IOException {
/* 492 */     return getUnicodeCmap(true);
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
/*     */   @Deprecated
/*     */   public CmapSubtable getUnicodeCmap(boolean isStrict) throws IOException {
/* 506 */     return getUnicodeCmapImpl(isStrict);
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
/*     */   public CmapLookup getUnicodeCmapLookup() throws IOException {
/* 519 */     return getUnicodeCmapLookup(true);
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
/*     */   public CmapLookup getUnicodeCmapLookup(boolean isStrict) throws IOException {
/* 533 */     CmapSubtable cmap = getUnicodeCmapImpl(isStrict);
/* 534 */     if (!this.enabledGsubFeatures.isEmpty()) {
/*     */       
/* 536 */       GlyphSubstitutionTable table = getGsub();
/* 537 */       if (table != null)
/*     */       {
/* 539 */         return new SubstitutingCmapLookup(cmap, table, 
/* 540 */             Collections.unmodifiableList(this.enabledGsubFeatures));
/*     */       }
/*     */     } 
/* 543 */     return cmap;
/*     */   }
/*     */ 
/*     */   
/*     */   private CmapSubtable getUnicodeCmapImpl(boolean isStrict) throws IOException {
/* 548 */     CmapTable cmapTable = getCmap();
/* 549 */     if (cmapTable == null) {
/*     */       
/* 551 */       if (isStrict)
/*     */       {
/* 553 */         throw new IOException("The TrueType font " + getName() + " does not contain a 'cmap' table");
/*     */       }
/*     */ 
/*     */       
/* 557 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 561 */     CmapSubtable cmap = cmapTable.getSubtable(0, 4);
/*     */     
/* 563 */     if (cmap == null)
/*     */     {
/* 565 */       cmap = cmapTable.getSubtable(3, 10);
/*     */     }
/*     */     
/* 568 */     if (cmap == null)
/*     */     {
/* 570 */       cmap = cmapTable.getSubtable(0, 3);
/*     */     }
/*     */     
/* 573 */     if (cmap == null)
/*     */     {
/* 575 */       cmap = cmapTable.getSubtable(3, 1);
/*     */     }
/*     */     
/* 578 */     if (cmap == null)
/*     */     {
/*     */ 
/*     */       
/* 582 */       cmap = cmapTable.getSubtable(3, 0);
/*     */     }
/*     */     
/* 585 */     if (cmap == null) {
/*     */       
/* 587 */       if (isStrict)
/*     */       {
/* 589 */         throw new IOException("The TrueType font does not contain a Unicode cmap");
/*     */       }
/* 591 */       if ((cmapTable.getCmaps()).length > 0)
/*     */       {
/*     */         
/* 594 */         cmap = cmapTable.getCmaps()[0];
/*     */       }
/*     */     } 
/* 597 */     return cmap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nameToGID(String name) throws IOException {
/* 607 */     readPostScriptNames();
/* 608 */     if (this.postScriptNames != null) {
/*     */       
/* 610 */       Integer gid = this.postScriptNames.get(name);
/* 611 */       if (gid != null && gid.intValue() > 0 && gid.intValue() < getMaximumProfile().getNumGlyphs())
/*     */       {
/* 613 */         return gid.intValue();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 618 */     int uni = parseUniName(name);
/* 619 */     if (uni > -1) {
/*     */       
/* 621 */       CmapLookup cmap = getUnicodeCmapLookup(false);
/* 622 */       return cmap.getGlyphId(uni);
/*     */     } 
/*     */     
/* 625 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int parseUniName(String name) throws IOException {
/* 633 */     if (name.startsWith("uni") && name.length() == 7) {
/*     */       
/* 635 */       int nameLength = name.length();
/* 636 */       StringBuilder uniStr = new StringBuilder();
/*     */       
/*     */       try {
/* 639 */         for (int chPos = 3; chPos + 4 <= nameLength; chPos += 4) {
/*     */           
/* 641 */           int codePoint = Integer.parseInt(name.substring(chPos, chPos + 4), 16);
/* 642 */           if (codePoint <= 55295 || codePoint >= 57344)
/*     */           {
/* 644 */             uniStr.append((char)codePoint);
/*     */           }
/*     */         } 
/* 647 */         String unicode = uniStr.toString();
/* 648 */         if (unicode.length() == 0)
/*     */         {
/* 650 */           return -1;
/*     */         }
/* 652 */         return unicode.codePointAt(0);
/*     */       }
/* 654 */       catch (NumberFormatException e) {
/*     */         
/* 656 */         return -1;
/*     */       } 
/*     */     } 
/* 659 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String name) throws IOException {
/* 665 */     int gid = nameToGID(name);
/*     */ 
/*     */     
/* 668 */     GlyphData glyph = getGlyph().getGlyph(gid);
/* 669 */     if (glyph == null)
/*     */     {
/* 671 */       return new GeneralPath();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 676 */     return glyph.getPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth(String name) throws IOException {
/* 683 */     Integer gid = Integer.valueOf(nameToGID(name));
/* 684 */     return getAdvanceWidth(gid.intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String name) throws IOException {
/* 690 */     return (nameToGID(name) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getFontBBox() throws IOException {
/* 696 */     short xMin = getHeader().getXMin();
/* 697 */     short xMax = getHeader().getXMax();
/* 698 */     short yMin = getHeader().getYMin();
/* 699 */     short yMax = getHeader().getYMax();
/* 700 */     float scale = 1000.0F / getUnitsPerEm();
/* 701 */     return new BoundingBox(xMin * scale, yMin * scale, xMax * scale, yMax * scale);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getFontMatrix() throws IOException {
/* 707 */     float scale = 1000.0F / getUnitsPerEm();
/* 708 */     return Arrays.asList(new Number[] { Float.valueOf(0.001F * scale), Integer.valueOf(0), Integer.valueOf(0), Float.valueOf(0.001F * scale), Integer.valueOf(0), Integer.valueOf(0) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableGsubFeature(String featureTag) {
/* 719 */     this.enabledGsubFeatures.add(featureTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disableGsubFeature(String featureTag) {
/* 729 */     this.enabledGsubFeatures.remove(featureTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableVerticalSubstitutions() {
/* 737 */     enableGsubFeature("vrt2");
/* 738 */     enableGsubFeature("vert");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     try {
/* 746 */       if (getNaming() != null)
/*     */       {
/* 748 */         return getNaming().getPostScriptName();
/*     */       }
/*     */ 
/*     */       
/* 752 */       return "(null)";
/*     */     
/*     */     }
/* 755 */     catch (IOException e) {
/*     */       
/* 757 */       return "(null - " + e.getMessage() + ")";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/TrueTypeFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */