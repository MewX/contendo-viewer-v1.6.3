/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.security.AccessControlException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.cff.CFFCIDFont;
/*     */ import org.apache.fontbox.cff.CFFFont;
/*     */ import org.apache.fontbox.ttf.NamingTable;
/*     */ import org.apache.fontbox.ttf.OTFParser;
/*     */ import org.apache.fontbox.ttf.OpenTypeFont;
/*     */ import org.apache.fontbox.ttf.TTFParser;
/*     */ import org.apache.fontbox.ttf.TTFTable;
/*     */ import org.apache.fontbox.ttf.TrueTypeCollection;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.fontbox.type1.Type1Font;
/*     */ import org.apache.fontbox.util.autodetect.FontFileFinder;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.util.Charsets;
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
/*     */ final class FileSystemFontProvider
/*     */   extends FontProvider
/*     */ {
/*  57 */   private static final Log LOG = LogFactory.getLog(FileSystemFontProvider.class);
/*     */   
/*  59 */   private final List<FSFontInfo> fontInfoList = new ArrayList<FSFontInfo>();
/*     */   
/*     */   private final FontCache cache;
/*     */ 
/*     */   
/*     */   private static class FSFontInfo
/*     */     extends FontInfo
/*     */   {
/*     */     private final String postScriptName;
/*     */     
/*     */     private final FontFormat format;
/*     */     private final CIDSystemInfo cidSystemInfo;
/*     */     private final int usWeightClass;
/*     */     private final int sFamilyClass;
/*     */     private final int ulCodePageRange1;
/*     */     private final int ulCodePageRange2;
/*     */     private final int macStyle;
/*     */     private final PDPanoseClassification panose;
/*     */     private final File file;
/*     */     private transient FileSystemFontProvider parent;
/*     */     
/*     */     private FSFontInfo(File file, FontFormat format, String postScriptName, CIDSystemInfo cidSystemInfo, int usWeightClass, int sFamilyClass, int ulCodePageRange1, int ulCodePageRange2, int macStyle, byte[] panose, FileSystemFontProvider parent) {
/*  81 */       this.file = file;
/*  82 */       this.format = format;
/*  83 */       this.postScriptName = postScriptName;
/*  84 */       this.cidSystemInfo = cidSystemInfo;
/*  85 */       this.usWeightClass = usWeightClass;
/*  86 */       this.sFamilyClass = sFamilyClass;
/*  87 */       this.ulCodePageRange1 = ulCodePageRange1;
/*  88 */       this.ulCodePageRange2 = ulCodePageRange2;
/*  89 */       this.macStyle = macStyle;
/*  90 */       this.panose = (panose != null) ? new PDPanoseClassification(panose) : null;
/*  91 */       this.parent = parent;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getPostScriptName() {
/*  97 */       return this.postScriptName;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public FontFormat getFormat() {
/* 103 */       return this.format;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public CIDSystemInfo getCIDSystemInfo() {
/* 109 */       return this.cidSystemInfo;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FontBoxFont getFont() {
/*     */       Type1Font type1Font;
/*     */       TrueTypeFont trueTypeFont;
/*     */       OpenTypeFont openTypeFont;
/* 121 */       FontBoxFont cached = this.parent.cache.getFont(this);
/* 122 */       if (cached != null)
/*     */       {
/* 124 */         return cached;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 129 */       switch (this.format) {
/*     */         case PFB:
/* 131 */           type1Font = this.parent.getType1Font(this.postScriptName, this.file); break;
/* 132 */         case TTF: trueTypeFont = this.parent.getTrueTypeFont(this.postScriptName, this.file); break;
/* 133 */         case OTF: openTypeFont = this.parent.getOTFFont(this.postScriptName, this.file); break;
/* 134 */         default: throw new RuntimeException("can't happen");
/*     */       } 
/* 136 */       if (openTypeFont != null)
/*     */       {
/* 138 */         this.parent.cache.addFont(this, (FontBoxFont)openTypeFont);
/*     */       }
/* 140 */       return (FontBoxFont)openTypeFont;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getFamilyClass() {
/* 147 */       return this.sFamilyClass;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getWeightClass() {
/* 153 */       return this.usWeightClass;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCodePageRange1() {
/* 159 */       return this.ulCodePageRange1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCodePageRange2() {
/* 165 */       return this.ulCodePageRange2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getMacStyle() {
/* 171 */       return this.macStyle;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public PDPanoseClassification getPanose() {
/* 177 */       return this.panose;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 183 */       return super.toString() + " " + this.file;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class FSIgnored
/*     */     extends FSFontInfo
/*     */   {
/*     */     private FSIgnored(File file, FontFormat format, String postScriptName) {
/* 194 */       super(file, format, postScriptName, null, 0, 0, 0, 0, 0, null, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   FileSystemFontProvider(FontCache cache) {
/* 203 */     this.cache = cache;
/*     */     
/*     */     try {
/* 206 */       if (LOG.isTraceEnabled())
/*     */       {
/* 208 */         LOG.trace("Will search the local system for fonts");
/*     */       }
/*     */ 
/*     */       
/* 212 */       List<File> files = new ArrayList<File>();
/* 213 */       FontFileFinder fontFileFinder = new FontFileFinder();
/* 214 */       List<URI> fonts = fontFileFinder.find();
/* 215 */       for (URI font : fonts)
/*     */       {
/* 217 */         files.add(new File(font));
/*     */       }
/*     */       
/* 220 */       if (LOG.isTraceEnabled())
/*     */       {
/* 222 */         LOG.trace("Found " + files.size() + " fonts on the local system");
/*     */       }
/*     */ 
/*     */       
/* 226 */       List<FSFontInfo> cachedInfos = loadDiskCache(files);
/* 227 */       if (cachedInfos != null && !cachedInfos.isEmpty())
/*     */       {
/* 229 */         this.fontInfoList.addAll(cachedInfos);
/*     */       }
/*     */       else
/*     */       {
/* 233 */         LOG.warn("Building on-disk font cache, this may take a while");
/* 234 */         scanFonts(files);
/* 235 */         saveDiskCache();
/* 236 */         LOG.warn("Finished building on-disk font cache, found " + this.fontInfoList
/* 237 */             .size() + " fonts");
/*     */       }
/*     */     
/* 240 */     } catch (AccessControlException e) {
/*     */       
/* 242 */       LOG.error("Error accessing the file system", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void scanFonts(List<File> files) {
/* 248 */     for (File file : files) {
/*     */ 
/*     */       
/*     */       try {
/* 252 */         if (file.getPath().toLowerCase().endsWith(".ttf") || file
/* 253 */           .getPath().toLowerCase().endsWith(".otf")) {
/*     */           
/* 255 */           addTrueTypeFont(file); continue;
/*     */         } 
/* 257 */         if (file.getPath().toLowerCase().endsWith(".ttc") || file
/* 258 */           .getPath().toLowerCase().endsWith(".otc")) {
/*     */           
/* 260 */           addTrueTypeCollection(file); continue;
/*     */         } 
/* 262 */         if (file.getPath().toLowerCase().endsWith(".pfb"))
/*     */         {
/* 264 */           addType1Font(file);
/*     */         }
/*     */       }
/* 267 */       catch (IOException e) {
/*     */         
/* 269 */         LOG.error("Error parsing font " + file.getPath(), e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private File getDiskCacheFile() {
/* 276 */     String path = System.getProperty("pdfbox.fontcache");
/* 277 */     if (path == null || !(new File(path)).isDirectory() || !(new File(path)).canWrite()) {
/*     */       
/* 279 */       path = System.getProperty("user.home");
/* 280 */       if (path == null || !(new File(path)).isDirectory() || !(new File(path)).canWrite())
/*     */       {
/* 282 */         path = System.getProperty("java.io.tmpdir");
/*     */       }
/*     */     } 
/* 285 */     return new File(path, ".pdfbox.cache");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveDiskCache() {
/* 293 */     BufferedWriter writer = null;
/* 294 */     File file = null;
/*     */ 
/*     */     
/*     */     try {
/*     */       try {
/* 299 */         file = getDiskCacheFile();
/* 300 */         writer = new BufferedWriter(new FileWriter(file));
/*     */       }
/* 302 */       catch (SecurityException e) {
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 307 */       for (FSFontInfo fontInfo : this.fontInfoList)
/*     */       {
/* 309 */         writer.write(fontInfo.postScriptName.trim());
/* 310 */         writer.write("|");
/* 311 */         writer.write(fontInfo.format.toString());
/* 312 */         writer.write("|");
/* 313 */         if (fontInfo.cidSystemInfo != null)
/*     */         {
/* 315 */           writer.write(fontInfo.cidSystemInfo.getRegistry() + '-' + fontInfo
/* 316 */               .cidSystemInfo.getOrdering() + '-' + fontInfo
/* 317 */               .cidSystemInfo.getSupplement());
/*     */         }
/* 319 */         writer.write("|");
/* 320 */         if (fontInfo.usWeightClass > -1)
/*     */         {
/* 322 */           writer.write(Integer.toHexString(fontInfo.usWeightClass));
/*     */         }
/* 324 */         writer.write("|");
/* 325 */         if (fontInfo.sFamilyClass > -1)
/*     */         {
/* 327 */           writer.write(Integer.toHexString(fontInfo.sFamilyClass));
/*     */         }
/* 329 */         writer.write("|");
/* 330 */         writer.write(Integer.toHexString(fontInfo.ulCodePageRange1));
/* 331 */         writer.write("|");
/* 332 */         writer.write(Integer.toHexString(fontInfo.ulCodePageRange2));
/* 333 */         writer.write("|");
/* 334 */         if (fontInfo.macStyle > -1)
/*     */         {
/* 336 */           writer.write(Integer.toHexString(fontInfo.macStyle));
/*     */         }
/* 338 */         writer.write("|");
/* 339 */         if (fontInfo.panose != null) {
/*     */           
/* 341 */           byte[] bytes = fontInfo.panose.getBytes();
/* 342 */           for (int i = 0; i < 10; i++) {
/*     */             
/* 344 */             String str = Integer.toHexString(bytes[i]);
/* 345 */             if (str.length() == 1)
/*     */             {
/* 347 */               writer.write(48);
/*     */             }
/* 349 */             writer.write(str);
/*     */           } 
/*     */         } 
/* 352 */         writer.write("|");
/* 353 */         writer.write(fontInfo.file.getAbsolutePath());
/* 354 */         writer.newLine();
/*     */       }
/*     */     
/* 357 */     } catch (IOException e) {
/*     */       
/* 359 */       LOG.warn("Could not write to font cache", e);
/* 360 */       LOG.warn("Installed fonts information will have to be reloaded for each start");
/* 361 */       LOG.warn("You can assign a directory to the 'pdfbox.fontcache' property");
/*     */     }
/*     */     finally {
/*     */       
/* 365 */       IOUtils.closeQuietly(writer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<FSFontInfo> loadDiskCache(List<File> files) {
/* 374 */     Set<String> pending = new HashSet<String>();
/* 375 */     for (File file1 : files)
/*     */     {
/* 377 */       pending.add(file1.getAbsolutePath());
/*     */     }
/*     */     
/* 380 */     List<FSFontInfo> results = new ArrayList<FSFontInfo>();
/*     */ 
/*     */     
/* 383 */     File file = null;
/* 384 */     boolean fileExists = false;
/*     */     
/*     */     try {
/* 387 */       file = getDiskCacheFile();
/* 388 */       fileExists = file.exists();
/*     */     }
/* 390 */     catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */     
/* 394 */     if (fileExists) {
/*     */       
/* 396 */       BufferedReader reader = null;
/*     */       
/*     */       try {
/* 399 */         reader = new BufferedReader(new FileReader(file));
/*     */         String line;
/* 401 */         while ((line = reader.readLine()) != null)
/*     */         {
/* 403 */           String[] parts = line.split("\\|", 10);
/* 404 */           if (parts.length < 10) {
/*     */             
/* 406 */             LOG.error("Incorrect line '" + line + "' in font disk cache is skipped");
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 412 */           CIDSystemInfo cidSystemInfo = null;
/* 413 */           int usWeightClass = -1;
/* 414 */           int sFamilyClass = -1;
/*     */ 
/*     */           
/* 417 */           int macStyle = -1;
/* 418 */           byte[] panose = null;
/*     */ 
/*     */           
/* 421 */           String postScriptName = parts[0];
/* 422 */           FontFormat format = FontFormat.valueOf(parts[1]);
/* 423 */           if (parts[2].length() > 0) {
/*     */             
/* 425 */             String[] ros = parts[2].split("-");
/* 426 */             cidSystemInfo = new CIDSystemInfo(ros[0], ros[1], Integer.parseInt(ros[2]));
/*     */           } 
/* 428 */           if (parts[3].length() > 0)
/*     */           {
/* 430 */             usWeightClass = (int)Long.parseLong(parts[3], 16);
/*     */           }
/* 432 */           if (parts[4].length() > 0)
/*     */           {
/* 434 */             sFamilyClass = (int)Long.parseLong(parts[4], 16);
/*     */           }
/* 436 */           int ulCodePageRange1 = (int)Long.parseLong(parts[5], 16);
/* 437 */           int ulCodePageRange2 = (int)Long.parseLong(parts[6], 16);
/* 438 */           if (parts[7].length() > 0)
/*     */           {
/* 440 */             macStyle = (int)Long.parseLong(parts[7], 16);
/*     */           }
/* 442 */           if (parts[8].length() > 0) {
/*     */             
/* 444 */             panose = new byte[10];
/* 445 */             for (int i = 0; i < 10; i++) {
/*     */               
/* 447 */               String str = parts[8].substring(i * 2, i * 2 + 2);
/* 448 */               int b = Integer.parseInt(str, 16);
/* 449 */               panose[i] = (byte)(b & 0xFF);
/*     */             } 
/*     */           } 
/* 452 */           File fontFile = new File(parts[9]);
/* 453 */           if (fontFile.exists()) {
/*     */             
/* 455 */             FSFontInfo info = new FSFontInfo(fontFile, format, postScriptName, cidSystemInfo, usWeightClass, sFamilyClass, ulCodePageRange1, ulCodePageRange2, macStyle, panose, this);
/*     */ 
/*     */             
/* 458 */             results.add(info);
/*     */           }
/*     */           else {
/*     */             
/* 462 */             LOG.debug("Font file " + fontFile.getAbsolutePath() + " not found, skipped");
/*     */           } 
/* 464 */           pending.remove(fontFile.getAbsolutePath());
/*     */         }
/*     */       
/* 467 */       } catch (IOException e) {
/*     */         
/* 469 */         LOG.error("Error loading font cache, will be re-built", e);
/* 470 */         return null;
/*     */       }
/*     */       finally {
/*     */         
/* 474 */         IOUtils.closeQuietly(reader);
/*     */       } 
/*     */     } 
/*     */     
/* 478 */     if (!pending.isEmpty()) {
/*     */ 
/*     */       
/* 481 */       LOG.warn("New fonts found, font cache will be re-built");
/* 482 */       return null;
/*     */     } 
/*     */     
/* 485 */     return results;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTrueTypeCollection(final File ttcFile) throws IOException {
/* 493 */     TrueTypeCollection ttc = null;
/*     */     
/*     */     try {
/* 496 */       ttc = new TrueTypeCollection(ttcFile);
/* 497 */       ttc.processAllFonts(new TrueTypeCollection.TrueTypeFontProcessor()
/*     */           {
/*     */             
/*     */             public void process(TrueTypeFont ttf) throws IOException
/*     */             {
/* 502 */               FileSystemFontProvider.this.addTrueTypeFontImpl(ttf, ttcFile);
/*     */             }
/*     */           });
/*     */     }
/* 506 */     catch (NullPointerException e) {
/*     */       
/* 508 */       LOG.error("Could not load font file: " + ttcFile, e);
/*     */     }
/* 510 */     catch (IOException e) {
/*     */       
/* 512 */       LOG.error("Could not load font file: " + ttcFile, e);
/*     */     }
/*     */     finally {
/*     */       
/* 516 */       if (ttc != null)
/*     */       {
/* 518 */         ttc.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTrueTypeFont(File ttfFile) throws IOException {
/*     */     try {
/* 530 */       if (ttfFile.getPath().endsWith(".otf"))
/*     */       {
/* 532 */         OTFParser parser = new OTFParser(false, true);
/* 533 */         OpenTypeFont otf = parser.parse(ttfFile);
/* 534 */         addTrueTypeFontImpl((TrueTypeFont)otf, ttfFile);
/*     */       }
/*     */       else
/*     */       {
/* 538 */         TTFParser parser = new TTFParser(false, true);
/* 539 */         TrueTypeFont ttf = parser.parse(ttfFile);
/* 540 */         addTrueTypeFontImpl(ttf, ttfFile);
/*     */       }
/*     */     
/* 543 */     } catch (NullPointerException e) {
/*     */       
/* 545 */       LOG.error("Could not load font file: " + ttfFile, e);
/*     */     }
/* 547 */     catch (IOException e) {
/*     */       
/* 549 */       LOG.error("Could not load font file: " + ttfFile, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTrueTypeFontImpl(TrueTypeFont ttf, File file) throws IOException {
/*     */     try {
/* 561 */       if (ttf.getName() != null && ttf.getName().contains("|")) {
/*     */         
/* 563 */         this.fontInfoList.add(new FSIgnored(file, FontFormat.TTF, "*skippipeinname*"));
/* 564 */         LOG.warn("Skipping font with '|' in name " + ttf.getName() + " in file " + file);
/*     */       }
/* 566 */       else if (ttf.getName() != null) {
/*     */         String format;
/*     */         
/* 569 */         if (ttf.getHeader() == null) {
/*     */           
/* 571 */           this.fontInfoList.add(new FSIgnored(file, FontFormat.TTF, ttf.getName()));
/*     */           return;
/*     */         } 
/* 574 */         int macStyle = ttf.getHeader().getMacStyle();
/*     */         
/* 576 */         int sFamilyClass = -1;
/* 577 */         int usWeightClass = -1;
/* 578 */         int ulCodePageRange1 = 0;
/* 579 */         int ulCodePageRange2 = 0;
/* 580 */         byte[] panose = null;
/*     */         
/* 582 */         if (ttf.getOS2Windows() != null) {
/*     */           
/* 584 */           sFamilyClass = ttf.getOS2Windows().getFamilyClass();
/* 585 */           usWeightClass = ttf.getOS2Windows().getWeightClass();
/* 586 */           ulCodePageRange1 = (int)ttf.getOS2Windows().getCodePageRange1();
/* 587 */           ulCodePageRange2 = (int)ttf.getOS2Windows().getCodePageRange2();
/* 588 */           panose = ttf.getOS2Windows().getPanose();
/*     */         } 
/*     */ 
/*     */         
/* 592 */         if (ttf instanceof OpenTypeFont && ((OpenTypeFont)ttf).isPostScript()) {
/*     */           
/* 594 */           format = "OTF";
/* 595 */           CFFFont cff = ((OpenTypeFont)ttf).getCFF().getFont();
/* 596 */           CIDSystemInfo ros = null;
/* 597 */           if (cff instanceof CFFCIDFont) {
/*     */             
/* 599 */             CFFCIDFont cidFont = (CFFCIDFont)cff;
/* 600 */             String registry = cidFont.getRegistry();
/* 601 */             String ordering = cidFont.getOrdering();
/* 602 */             int supplement = cidFont.getSupplement();
/* 603 */             ros = new CIDSystemInfo(registry, ordering, supplement);
/*     */           } 
/* 605 */           this.fontInfoList.add(new FSFontInfo(file, FontFormat.OTF, ttf.getName(), ros, usWeightClass, sFamilyClass, ulCodePageRange1, ulCodePageRange2, macStyle, panose, this));
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 611 */           CIDSystemInfo ros = null;
/* 612 */           if (ttf.getTableMap().containsKey("gcid")) {
/*     */ 
/*     */             
/* 615 */             byte[] bytes = ttf.getTableBytes((TTFTable)ttf.getTableMap().get("gcid"));
/* 616 */             String reg = new String(bytes, 10, 64, Charsets.US_ASCII);
/* 617 */             String registryName = reg.substring(0, reg.indexOf(false));
/* 618 */             String ord = new String(bytes, 76, 64, Charsets.US_ASCII);
/* 619 */             String orderName = ord.substring(0, ord.indexOf(false));
/* 620 */             int supplementVersion = bytes[140] << 8 & bytes[141];
/* 621 */             ros = new CIDSystemInfo(registryName, orderName, supplementVersion);
/*     */           } 
/*     */           
/* 624 */           format = "TTF";
/* 625 */           this.fontInfoList.add(new FSFontInfo(file, FontFormat.TTF, ttf.getName(), ros, usWeightClass, sFamilyClass, ulCodePageRange1, ulCodePageRange2, macStyle, panose, this));
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 630 */         if (LOG.isTraceEnabled())
/*     */         {
/* 632 */           NamingTable name = ttf.getNaming();
/* 633 */           if (name != null)
/*     */           {
/* 635 */             LOG.trace(format + ": '" + name.getPostScriptName() + "' / '" + name
/* 636 */                 .getFontFamily() + "' / '" + name
/* 637 */                 .getFontSubFamily() + "'");
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 643 */         this.fontInfoList.add(new FSIgnored(file, FontFormat.TTF, "*skipnoname*"));
/* 644 */         LOG.warn("Missing 'name' entry for PostScript name in font " + file);
/*     */       }
/*     */     
/* 647 */     } catch (IOException e) {
/*     */       
/* 649 */       this.fontInfoList.add(new FSIgnored(file, FontFormat.TTF, "*skipexception*"));
/* 650 */       LOG.error("Could not load font file: " + file, e);
/*     */     }
/*     */     finally {
/*     */       
/* 654 */       ttf.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addType1Font(File pfbFile) throws IOException {
/* 663 */     InputStream input = new FileInputStream(pfbFile);
/*     */     
/*     */     try {
/* 666 */       Type1Font type1 = Type1Font.createWithPFB(input);
/* 667 */       if (type1.getName() != null && type1.getName().contains("|")) {
/*     */         
/* 669 */         this.fontInfoList.add(new FSIgnored(pfbFile, FontFormat.PFB, "*skippipeinname*"));
/* 670 */         LOG.warn("Skipping font with '|' in name " + type1.getName() + " in file " + pfbFile);
/*     */         return;
/*     */       } 
/* 673 */       this.fontInfoList.add(new FSFontInfo(pfbFile, FontFormat.PFB, type1.getName(), null, -1, -1, 0, 0, -1, null, this));
/*     */ 
/*     */       
/* 676 */       if (LOG.isTraceEnabled())
/*     */       {
/* 678 */         LOG.trace("PFB: '" + type1.getName() + "' / '" + type1.getFamilyName() + "' / '" + type1
/* 679 */             .getWeight() + "'");
/*     */       }
/*     */     }
/* 682 */     catch (IOException e) {
/*     */       
/* 684 */       LOG.error("Could not load font file: " + pfbFile, e);
/*     */     }
/*     */     finally {
/*     */       
/* 688 */       input.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private TrueTypeFont getTrueTypeFont(String postScriptName, File file) {
/*     */     try {
/* 696 */       TrueTypeFont ttf = readTrueTypeFont(postScriptName, file);
/*     */       
/* 698 */       if (LOG.isDebugEnabled())
/*     */       {
/* 700 */         LOG.debug("Loaded " + postScriptName + " from " + file);
/*     */       }
/* 702 */       return ttf;
/*     */     }
/* 704 */     catch (NullPointerException e) {
/*     */       
/* 706 */       LOG.error("Could not load font file: " + file, e);
/*     */     }
/* 708 */     catch (IOException e) {
/*     */       
/* 710 */       LOG.error("Could not load font file: " + file, e);
/*     */     } 
/* 712 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private TrueTypeFont readTrueTypeFont(String postScriptName, File file) throws IOException {
/* 717 */     if (file.getName().toLowerCase().endsWith(".ttc")) {
/*     */       
/* 719 */       TrueTypeCollection ttc = new TrueTypeCollection(file);
/* 720 */       TrueTypeFont ttf = ttc.getFontByName(postScriptName);
/* 721 */       if (ttf == null) {
/*     */         
/* 723 */         ttc.close();
/* 724 */         throw new IOException("Font " + postScriptName + " not found in " + file);
/*     */       } 
/* 726 */       return ttf;
/*     */     } 
/*     */ 
/*     */     
/* 730 */     TTFParser ttfParser = new TTFParser(false, true);
/* 731 */     return ttfParser.parse(file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private OpenTypeFont getOTFFont(String postScriptName, File file) {
/*     */     try {
/* 740 */       OTFParser parser = new OTFParser(false, true);
/* 741 */       OpenTypeFont otf = parser.parse(file);
/*     */       
/* 743 */       if (LOG.isDebugEnabled())
/*     */       {
/* 745 */         LOG.debug("Loaded " + postScriptName + " from " + file);
/*     */       }
/* 747 */       return otf;
/*     */     }
/* 749 */     catch (IOException e) {
/*     */       
/* 751 */       LOG.error("Could not load font file: " + file, e);
/*     */       
/* 753 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Type1Font getType1Font(String postScriptName, File file) {
/* 758 */     InputStream input = null;
/*     */     
/*     */     try {
/* 761 */       input = new FileInputStream(file);
/* 762 */       Type1Font type1 = Type1Font.createWithPFB(input);
/*     */       
/* 764 */       if (LOG.isDebugEnabled())
/*     */       {
/* 766 */         LOG.debug("Loaded " + postScriptName + " from " + file);
/*     */       }
/* 768 */       return type1;
/*     */     }
/* 770 */     catch (IOException e) {
/*     */       
/* 772 */       LOG.error("Could not load font file: " + file, e);
/*     */     }
/*     */     finally {
/*     */       
/* 776 */       IOUtils.closeQuietly(input);
/*     */     } 
/* 778 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toDebugString() {
/* 784 */     StringBuilder sb = new StringBuilder();
/* 785 */     for (FSFontInfo info : this.fontInfoList) {
/*     */       
/* 787 */       sb.append(info.getFormat());
/* 788 */       sb.append(": ");
/* 789 */       sb.append(info.getPostScriptName());
/* 790 */       sb.append(": ");
/* 791 */       sb.append(info.file.getPath());
/* 792 */       sb.append('\n');
/*     */     } 
/* 794 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<? extends FontInfo> getFontInfo() {
/* 800 */     return (List)this.fontInfoList;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FileSystemFontProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */