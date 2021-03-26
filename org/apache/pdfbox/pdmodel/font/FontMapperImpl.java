/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.PriorityQueue;
/*     */ import java.util.Set;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.ttf.OpenTypeFont;
/*     */ import org.apache.fontbox.ttf.TTFParser;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.fontbox.type1.Type1Font;
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
/*     */ final class FontMapperImpl
/*     */   implements FontMapper
/*     */ {
/*  44 */   private static final FontCache fontCache = new FontCache();
/*     */   
/*     */   private FontProvider fontProvider;
/*     */   
/*     */   private Map<String, FontInfo> fontInfoByName;
/*     */   private final TrueTypeFont lastResortFont;
/*  50 */   private final Map<String, List<String>> substitutes = new HashMap<String, List<String>>();
/*     */ 
/*     */ 
/*     */   
/*     */   FontMapperImpl() {
/*  55 */     this.substitutes.put("Courier", 
/*  56 */         Arrays.asList(new String[] { "CourierNew", "CourierNewPSMT", "LiberationMono", "NimbusMonL-Regu" }));
/*  57 */     this.substitutes.put("Courier-Bold", 
/*  58 */         Arrays.asList(new String[] { "CourierNewPS-BoldMT", "CourierNew-Bold", "LiberationMono-Bold", "NimbusMonL-Bold" }));
/*     */     
/*  60 */     this.substitutes.put("Courier-Oblique", 
/*  61 */         Arrays.asList(new String[] { "CourierNewPS-ItalicMT", "CourierNew-Italic", "LiberationMono-Italic", "NimbusMonL-ReguObli" }));
/*     */     
/*  63 */     this.substitutes.put("Courier-BoldOblique", 
/*  64 */         Arrays.asList(new String[] { "CourierNewPS-BoldItalicMT", "CourierNew-BoldItalic", "LiberationMono-BoldItalic", "NimbusMonL-BoldObli" }));
/*     */     
/*  66 */     this.substitutes.put("Helvetica", 
/*  67 */         Arrays.asList(new String[] { "ArialMT", "Arial", "LiberationSans", "NimbusSanL-Regu" }));
/*  68 */     this.substitutes.put("Helvetica-Bold", 
/*  69 */         Arrays.asList(new String[] { "Arial-BoldMT", "Arial-Bold", "LiberationSans-Bold", "NimbusSanL-Bold" }));
/*     */     
/*  71 */     this.substitutes.put("Helvetica-Oblique", 
/*  72 */         Arrays.asList(new String[] { "Arial-ItalicMT", "Arial-Italic", "Helvetica-Italic", "LiberationSans-Italic", "NimbusSanL-ReguItal" }));
/*     */     
/*  74 */     this.substitutes.put("Helvetica-BoldOblique", 
/*  75 */         Arrays.asList(new String[] { "Arial-BoldItalicMT", "Helvetica-BoldItalic", "LiberationSans-BoldItalic", "NimbusSanL-BoldItal" }));
/*     */     
/*  77 */     this.substitutes.put("Times-Roman", 
/*  78 */         Arrays.asList(new String[] { "TimesNewRomanPSMT", "TimesNewRoman", "TimesNewRomanPS", "LiberationSerif", "NimbusRomNo9L-Regu" }));
/*     */     
/*  80 */     this.substitutes.put("Times-Bold", 
/*  81 */         Arrays.asList(new String[] { "TimesNewRomanPS-BoldMT", "TimesNewRomanPS-Bold", "TimesNewRoman-Bold", "LiberationSerif-Bold", "NimbusRomNo9L-Medi" }));
/*     */ 
/*     */     
/*  84 */     this.substitutes.put("Times-Italic", 
/*  85 */         Arrays.asList(new String[] { "TimesNewRomanPS-ItalicMT", "TimesNewRomanPS-Italic", "TimesNewRoman-Italic", "LiberationSerif-Italic", "NimbusRomNo9L-ReguItal" }));
/*     */ 
/*     */     
/*  88 */     this.substitutes.put("Times-BoldItalic", 
/*  89 */         Arrays.asList(new String[] { "TimesNewRomanPS-BoldItalicMT", "TimesNewRomanPS-BoldItalic", "TimesNewRoman-BoldItalic", "LiberationSerif-BoldItalic", "NimbusRomNo9L-MediItal" }));
/*     */ 
/*     */     
/*  92 */     this.substitutes.put("Symbol", Arrays.asList(new String[] { "Symbol", "SymbolMT", "StandardSymL" }));
/*  93 */     this.substitutes.put("ZapfDingbats", Arrays.asList(new String[] { "ZapfDingbatsITC", "Dingbats", "MS-Gothic" }));
/*     */ 
/*     */ 
/*     */     
/*  97 */     for (String baseName : Standard14Fonts.getNames()) {
/*     */       
/*  99 */       if (!this.substitutes.containsKey(baseName)) {
/*     */         
/* 101 */         String mappedName = Standard14Fonts.getMappedFontName(baseName);
/* 102 */         this.substitutes.put(baseName, copySubstitutes(mappedName));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 110 */       String ttfName = "/org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf";
/* 111 */       InputStream ttfStream = FontMapper.class.getResourceAsStream(ttfName);
/* 112 */       if (ttfStream == null)
/*     */       {
/* 114 */         throw new IOException("Error loading resource: " + ttfName);
/*     */       }
/* 116 */       TTFParser ttfParser = new TTFParser();
/* 117 */       this.lastResortFont = ttfParser.parse(ttfStream);
/*     */     }
/* 119 */     catch (IOException e) {
/*     */       
/* 121 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static class DefaultFontProvider
/*     */   {
/* 128 */     private static final FontProvider INSTANCE = new FileSystemFontProvider(FontMapperImpl.fontCache);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setProvider(FontProvider fontProvider) {
/* 136 */     this.fontInfoByName = createFontInfoByName(fontProvider.getFontInfo());
/* 137 */     this.fontProvider = fontProvider;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized FontProvider getProvider() {
/* 145 */     if (this.fontProvider == null)
/*     */     {
/* 147 */       setProvider(DefaultFontProvider.INSTANCE);
/*     */     }
/* 149 */     return this.fontProvider;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontCache getFontCache() {
/* 158 */     return fontCache;
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<String, FontInfo> createFontInfoByName(List<? extends FontInfo> fontInfoList) {
/* 163 */     Map<String, FontInfo> map = new LinkedHashMap<String, FontInfo>();
/* 164 */     for (FontInfo info : fontInfoList) {
/*     */       
/* 166 */       for (String name : getPostScriptNames(info.getPostScriptName()))
/*     */       {
/* 168 */         map.put(name, info);
/*     */       }
/*     */     } 
/* 171 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Set<String> getPostScriptNames(String postScriptName) {
/* 179 */     Set<String> names = new HashSet<String>();
/*     */ 
/*     */     
/* 182 */     names.add(postScriptName);
/*     */ 
/*     */     
/* 185 */     names.add(postScriptName.replaceAll("-", ""));
/*     */     
/* 187 */     return names;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> copySubstitutes(String postScriptName) {
/* 195 */     return new ArrayList<String>(this.substitutes.get(postScriptName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubstitute(String match, String replace) {
/* 206 */     if (!this.substitutes.containsKey(match))
/*     */     {
/* 208 */       this.substitutes.put(match, new ArrayList<String>());
/*     */     }
/* 210 */     ((List<String>)this.substitutes.get(match)).add(replace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> getSubstitutes(String postScriptName) {
/* 218 */     List<String> subs = this.substitutes.get(postScriptName.replaceAll(" ", ""));
/* 219 */     if (subs != null)
/*     */     {
/* 221 */       return subs;
/*     */     }
/*     */ 
/*     */     
/* 225 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getFallbackFontName(PDFontDescriptor fontDescriptor) {
/*     */     String fontName;
/* 235 */     if (fontDescriptor != null) {
/*     */ 
/*     */       
/* 238 */       boolean isBold = false;
/* 239 */       String name = fontDescriptor.getFontName();
/* 240 */       if (name != null) {
/*     */         
/* 242 */         String lower = fontDescriptor.getFontName().toLowerCase();
/*     */ 
/*     */         
/* 245 */         isBold = (lower.contains("bold") || lower.contains("black") || lower.contains("heavy"));
/*     */       } 
/*     */ 
/*     */       
/* 249 */       if (fontDescriptor.isFixedPitch()) {
/*     */         
/* 251 */         fontName = "Courier";
/* 252 */         if (isBold && fontDescriptor.isItalic())
/*     */         {
/* 254 */           fontName = fontName + "-BoldOblique";
/*     */         }
/* 256 */         else if (isBold)
/*     */         {
/* 258 */           fontName = fontName + "-Bold";
/*     */         }
/* 260 */         else if (fontDescriptor.isItalic())
/*     */         {
/* 262 */           fontName = fontName + "-Oblique";
/*     */         }
/*     */       
/* 265 */       } else if (fontDescriptor.isSerif()) {
/*     */         
/* 267 */         fontName = "Times";
/* 268 */         if (isBold && fontDescriptor.isItalic())
/*     */         {
/* 270 */           fontName = fontName + "-BoldItalic";
/*     */         }
/* 272 */         else if (isBold)
/*     */         {
/* 274 */           fontName = fontName + "-Bold";
/*     */         }
/* 276 */         else if (fontDescriptor.isItalic())
/*     */         {
/* 278 */           fontName = fontName + "-Italic";
/*     */         }
/*     */         else
/*     */         {
/* 282 */           fontName = fontName + "-Roman";
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 287 */         fontName = "Helvetica";
/* 288 */         if (isBold && fontDescriptor.isItalic())
/*     */         {
/* 290 */           fontName = fontName + "-BoldOblique";
/*     */         }
/* 292 */         else if (isBold)
/*     */         {
/* 294 */           fontName = fontName + "-Bold";
/*     */         }
/* 296 */         else if (fontDescriptor.isItalic())
/*     */         {
/* 298 */           fontName = fontName + "-Oblique";
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 305 */       fontName = "Times-Roman";
/*     */     } 
/* 307 */     return fontName;
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
/*     */   public FontMapping<TrueTypeFont> getTrueTypeFont(String baseFont, PDFontDescriptor fontDescriptor) {
/* 319 */     TrueTypeFont ttf = (TrueTypeFont)findFont(FontFormat.TTF, baseFont);
/* 320 */     if (ttf != null)
/*     */     {
/* 322 */       return new FontMapping<TrueTypeFont>(ttf, false);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 327 */     String fontName = getFallbackFontName(fontDescriptor);
/* 328 */     ttf = (TrueTypeFont)findFont(FontFormat.TTF, fontName);
/* 329 */     if (ttf == null)
/*     */     {
/*     */       
/* 332 */       ttf = this.lastResortFont;
/*     */     }
/* 334 */     return new FontMapping<TrueTypeFont>(ttf, true);
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
/*     */   public FontMapping<FontBoxFont> getFontBoxFont(String baseFont, PDFontDescriptor fontDescriptor) {
/*     */     TrueTypeFont trueTypeFont;
/* 348 */     FontBoxFont font = findFontBoxFont(baseFont);
/* 349 */     if (font != null)
/*     */     {
/* 351 */       return new FontMapping<FontBoxFont>(font, false);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 356 */     String fallbackName = getFallbackFontName(fontDescriptor);
/* 357 */     font = findFontBoxFont(fallbackName);
/* 358 */     if (font == null)
/*     */     {
/*     */       
/* 361 */       trueTypeFont = this.lastResortFont;
/*     */     }
/* 363 */     return new FontMapping(trueTypeFont, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FontBoxFont findFontBoxFont(String postScriptName) {
/* 374 */     Type1Font t1 = (Type1Font)findFont(FontFormat.PFB, postScriptName);
/* 375 */     if (t1 != null)
/*     */     {
/* 377 */       return (FontBoxFont)t1;
/*     */     }
/*     */     
/* 380 */     TrueTypeFont ttf = (TrueTypeFont)findFont(FontFormat.TTF, postScriptName);
/* 381 */     if (ttf != null)
/*     */     {
/* 383 */       return (FontBoxFont)ttf;
/*     */     }
/*     */     
/* 386 */     OpenTypeFont otf = (OpenTypeFont)findFont(FontFormat.OTF, postScriptName);
/* 387 */     if (otf != null)
/*     */     {
/* 389 */       return (FontBoxFont)otf;
/*     */     }
/*     */     
/* 392 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FontBoxFont findFont(FontFormat format, String postScriptName) {
/* 403 */     if (postScriptName == null)
/*     */     {
/* 405 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 409 */     if (this.fontProvider == null)
/*     */     {
/* 411 */       getProvider();
/*     */     }
/*     */ 
/*     */     
/* 415 */     FontInfo info = getFont(format, postScriptName);
/* 416 */     if (info != null)
/*     */     {
/* 418 */       return info.getFont();
/*     */     }
/*     */ 
/*     */     
/* 422 */     info = getFont(format, postScriptName.replaceAll("-", ""));
/* 423 */     if (info != null)
/*     */     {
/* 425 */       return info.getFont();
/*     */     }
/*     */ 
/*     */     
/* 429 */     for (String substituteName : getSubstitutes(postScriptName)) {
/*     */       
/* 431 */       info = getFont(format, substituteName);
/* 432 */       if (info != null)
/*     */       {
/* 434 */         return info.getFont();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 439 */     info = getFont(format, postScriptName.replaceAll(",", "-"));
/* 440 */     if (info != null)
/*     */     {
/* 442 */       return info.getFont();
/*     */     }
/*     */ 
/*     */     
/* 446 */     info = getFont(format, postScriptName + "-Regular");
/* 447 */     if (info != null)
/*     */     {
/* 449 */       return info.getFont();
/*     */     }
/*     */     
/* 452 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FontInfo getFont(FontFormat format, String postScriptName) {
/* 461 */     if (postScriptName.contains("+"))
/*     */     {
/* 463 */       postScriptName = postScriptName.substring(postScriptName.indexOf('+') + 1);
/*     */     }
/*     */ 
/*     */     
/* 467 */     FontInfo info = this.fontInfoByName.get(postScriptName);
/* 468 */     if (info != null && info.getFormat() == format)
/*     */     {
/* 470 */       return info;
/*     */     }
/* 472 */     return null;
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
/*     */   public CIDFontMapping getCIDFont(String baseFont, PDFontDescriptor fontDescriptor, PDCIDSystemInfo cidSystemInfo) {
/* 487 */     OpenTypeFont otf1 = (OpenTypeFont)findFont(FontFormat.OTF, baseFont);
/* 488 */     if (otf1 != null)
/*     */     {
/* 490 */       return new CIDFontMapping(otf1, null, false);
/*     */     }
/*     */ 
/*     */     
/* 494 */     TrueTypeFont ttf = (TrueTypeFont)findFont(FontFormat.TTF, baseFont);
/* 495 */     if (ttf != null)
/*     */     {
/* 497 */       return new CIDFontMapping(null, (FontBoxFont)ttf, false);
/*     */     }
/*     */     
/* 500 */     if (cidSystemInfo != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 506 */       String collection = cidSystemInfo.getRegistry() + "-" + cidSystemInfo.getOrdering();
/*     */       
/* 508 */       if (collection.equals("Adobe-GB1") || collection.equals("Adobe-CNS1") || collection
/* 509 */         .equals("Adobe-Japan1") || collection.equals("Adobe-Korea1")) {
/*     */ 
/*     */         
/* 512 */         PriorityQueue<FontMatch> queue = getFontMatches(fontDescriptor, cidSystemInfo);
/* 513 */         FontMatch bestMatch = queue.poll();
/* 514 */         if (bestMatch != null) {
/*     */           
/* 516 */           FontBoxFont font = bestMatch.info.getFont();
/* 517 */           if (font instanceof OpenTypeFont)
/*     */           {
/* 519 */             return new CIDFontMapping((OpenTypeFont)font, null, true);
/*     */           }
/* 521 */           if (font != null)
/*     */           {
/* 523 */             return new CIDFontMapping(null, font, true);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 530 */     return new CIDFontMapping(null, (FontBoxFont)this.lastResortFont, true);
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
/*     */   private PriorityQueue<FontMatch> getFontMatches(PDFontDescriptor fontDescriptor, PDCIDSystemInfo cidSystemInfo) {
/* 543 */     PriorityQueue<FontMatch> queue = new PriorityQueue<FontMatch>(20);
/*     */     
/* 545 */     for (FontInfo info : this.fontInfoByName.values()) {
/*     */ 
/*     */       
/* 548 */       if (cidSystemInfo != null && !isCharSetMatch(cidSystemInfo, info)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 553 */       FontMatch match = new FontMatch(info);
/*     */ 
/*     */       
/* 556 */       if (fontDescriptor.getPanose() != null && info.getPanose() != null) {
/*     */         
/* 558 */         PDPanoseClassification panose = fontDescriptor.getPanose().getPanose();
/* 559 */         if (panose.getFamilyKind() == info.getPanose().getFamilyKind())
/*     */         {
/* 561 */           if (panose.getFamilyKind() == 0 && (info
/* 562 */             .getPostScriptName().toLowerCase().contains("barcode") || info
/* 563 */             .getPostScriptName().startsWith("Code")) && 
/* 564 */             !probablyBarcodeFont(fontDescriptor)) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 570 */           if (panose.getSerifStyle() == info.getPanose().getSerifStyle()) {
/*     */ 
/*     */             
/* 573 */             match.score += 2.0D;
/*     */           }
/* 575 */           else if (panose.getSerifStyle() >= 2 && panose.getSerifStyle() <= 5 && info
/* 576 */             .getPanose().getSerifStyle() >= 2 && info
/* 577 */             .getPanose().getSerifStyle() <= 5) {
/*     */ 
/*     */             
/* 580 */             match.score++;
/*     */           }
/* 582 */           else if (panose.getSerifStyle() >= 11 && panose.getSerifStyle() <= 13 && info
/* 583 */             .getPanose().getSerifStyle() >= 11 && info
/* 584 */             .getPanose().getSerifStyle() <= 13) {
/*     */ 
/*     */             
/* 587 */             match.score++;
/*     */           }
/* 589 */           else if (panose.getSerifStyle() != 0 && info.getPanose().getSerifStyle() != 0) {
/*     */ 
/*     */             
/* 592 */             match.score--;
/*     */           } 
/*     */ 
/*     */           
/* 596 */           int weight = info.getPanose().getWeight();
/* 597 */           int weightClass = info.getWeightClassAsPanose();
/* 598 */           if (Math.abs(weight - weightClass) > 2)
/*     */           {
/*     */             
/* 601 */             weight = weightClass;
/*     */           }
/*     */           
/* 604 */           if (panose.getWeight() == weight)
/*     */           {
/*     */             
/* 607 */             match.score += 2.0D;
/*     */           }
/* 609 */           else if (panose.getWeight() > 1 && weight > 1)
/*     */           {
/* 611 */             float dist = Math.abs(panose.getWeight() - weight);
/* 612 */             match.score += 1.0D - dist * 0.5D;
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 619 */       else if (fontDescriptor.getFontWeight() > 0.0F && info.getWeightClass() > 0) {
/*     */ 
/*     */         
/* 622 */         float dist = Math.abs(fontDescriptor.getFontWeight() - info.getWeightClass());
/* 623 */         match.score += 1.0D - (dist / 100.0F) * 0.5D;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 628 */       queue.add(match);
/*     */     } 
/* 630 */     return queue;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean probablyBarcodeFont(PDFontDescriptor fontDescriptor) {
/* 635 */     String ff = fontDescriptor.getFontFamily();
/* 636 */     if (ff == null)
/*     */     {
/* 638 */       ff = "";
/*     */     }
/* 640 */     String fn = fontDescriptor.getFontName();
/* 641 */     if (fn == null)
/*     */     {
/* 643 */       fn = "";
/*     */     }
/* 645 */     return (ff.startsWith("Code") || ff.toLowerCase().contains("barcode") || fn
/* 646 */       .startsWith("Code") || fn.toLowerCase().contains("barcode"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isCharSetMatch(PDCIDSystemInfo cidSystemInfo, FontInfo info) {
/* 655 */     if (info.getCIDSystemInfo() != null)
/*     */     {
/* 657 */       return (info.getCIDSystemInfo().getRegistry().equals(cidSystemInfo.getRegistry()) && info
/* 658 */         .getCIDSystemInfo().getOrdering().equals(cidSystemInfo.getOrdering()));
/*     */     }
/*     */ 
/*     */     
/* 662 */     long codePageRange = info.getCodePageRange();
/*     */     
/* 664 */     long JIS_JAPAN = 131072L;
/* 665 */     long CHINESE_SIMPLIFIED = 262144L;
/* 666 */     long KOREAN_WANSUNG = 524288L;
/* 667 */     long CHINESE_TRADITIONAL = 1048576L;
/* 668 */     long KOREAN_JOHAB = 2097152L;
/*     */     
/* 670 */     if (cidSystemInfo.getOrdering().equals("GB1") && (codePageRange & CHINESE_SIMPLIFIED) == CHINESE_SIMPLIFIED)
/*     */     {
/*     */       
/* 673 */       return true;
/*     */     }
/* 675 */     if (cidSystemInfo.getOrdering().equals("CNS1") && (codePageRange & CHINESE_TRADITIONAL) == CHINESE_TRADITIONAL)
/*     */     {
/*     */       
/* 678 */       return true;
/*     */     }
/* 680 */     if (cidSystemInfo.getOrdering().equals("Japan1") && (codePageRange & JIS_JAPAN) == JIS_JAPAN)
/*     */     {
/*     */       
/* 683 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 687 */     return ((cidSystemInfo.getOrdering().equals("Korea1") && (codePageRange & KOREAN_WANSUNG) == KOREAN_WANSUNG) || (codePageRange & KOREAN_JOHAB) == KOREAN_JOHAB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class FontMatch
/*     */     implements Comparable<FontMatch>
/*     */   {
/*     */     double score;
/*     */ 
/*     */     
/*     */     final FontInfo info;
/*     */ 
/*     */ 
/*     */     
/*     */     FontMatch(FontInfo info) {
/* 704 */       this.info = info;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int compareTo(FontMatch match) {
/* 710 */       return Double.compare(match.score, this.score);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FontMatch printMatches(PriorityQueue<FontMatch> queue) {
/* 719 */     FontMatch bestMatch = queue.peek();
/* 720 */     System.out.println("-------");
/* 721 */     while (!queue.isEmpty()) {
/*     */       
/* 723 */       FontMatch match = queue.poll();
/* 724 */       FontInfo info = match.info;
/* 725 */       System.out.println(match.score + " | " + info.getMacStyle() + " " + info
/* 726 */           .getFamilyClass() + " " + info.getPanose() + " " + info
/* 727 */           .getCIDSystemInfo() + " " + info.getPostScriptName() + " " + info
/* 728 */           .getFormat());
/*     */     } 
/* 730 */     System.out.println("-------");
/* 731 */     return bestMatch;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FontMapperImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */