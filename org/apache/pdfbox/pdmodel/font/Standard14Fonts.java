/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.fontbox.afm.AFMParser;
/*     */ import org.apache.fontbox.afm.FontMetrics;
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
/*     */ final class Standard14Fonts
/*     */ {
/*  42 */   private static final Set<String> STANDARD_14_NAMES = new HashSet<String>(34);
/*  43 */   private static final Map<String, String> STANDARD_14_MAPPING = new HashMap<String, String>(34);
/*  44 */   private static final Map<String, FontMetrics> STANDARD14_AFM_MAP = new HashMap<String, FontMetrics>(34);
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  49 */       addAFM("Courier-Bold");
/*  50 */       addAFM("Courier-BoldOblique");
/*  51 */       addAFM("Courier");
/*  52 */       addAFM("Courier-Oblique");
/*  53 */       addAFM("Helvetica");
/*  54 */       addAFM("Helvetica-Bold");
/*  55 */       addAFM("Helvetica-BoldOblique");
/*  56 */       addAFM("Helvetica-Oblique");
/*  57 */       addAFM("Symbol");
/*  58 */       addAFM("Times-Bold");
/*  59 */       addAFM("Times-BoldItalic");
/*  60 */       addAFM("Times-Italic");
/*  61 */       addAFM("Times-Roman");
/*  62 */       addAFM("ZapfDingbats");
/*     */ 
/*     */       
/*  65 */       addAFM("CourierCourierNew", "Courier");
/*  66 */       addAFM("CourierNew", "Courier");
/*  67 */       addAFM("CourierNew,Italic", "Courier-Oblique");
/*  68 */       addAFM("CourierNew,Bold", "Courier-Bold");
/*  69 */       addAFM("CourierNew,BoldItalic", "Courier-BoldOblique");
/*  70 */       addAFM("Arial", "Helvetica");
/*  71 */       addAFM("Arial,Italic", "Helvetica-Oblique");
/*  72 */       addAFM("Arial,Bold", "Helvetica-Bold");
/*  73 */       addAFM("Arial,BoldItalic", "Helvetica-BoldOblique");
/*  74 */       addAFM("TimesNewRoman", "Times-Roman");
/*  75 */       addAFM("TimesNewRoman,Italic", "Times-Italic");
/*  76 */       addAFM("TimesNewRoman,Bold", "Times-Bold");
/*  77 */       addAFM("TimesNewRoman,BoldItalic", "Times-BoldItalic");
/*     */ 
/*     */       
/*  80 */       addAFM("Symbol,Italic", "Symbol");
/*  81 */       addAFM("Symbol,Bold", "Symbol");
/*  82 */       addAFM("Symbol,BoldItalic", "Symbol");
/*  83 */       addAFM("Times", "Times-Roman");
/*  84 */       addAFM("Times,Italic", "Times-Italic");
/*  85 */       addAFM("Times,Bold", "Times-Bold");
/*  86 */       addAFM("Times,BoldItalic", "Times-BoldItalic");
/*     */ 
/*     */       
/*  89 */       addAFM("ArialMT", "Helvetica");
/*  90 */       addAFM("Arial-ItalicMT", "Helvetica-Oblique");
/*  91 */       addAFM("Arial-BoldMT", "Helvetica-Bold");
/*  92 */       addAFM("Arial-BoldItalicMT", "Helvetica-BoldOblique");
/*     */     }
/*  94 */     catch (IOException e) {
/*     */       
/*  96 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addAFM(String fontName) throws IOException {
/* 102 */     addAFM(fontName, fontName);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addAFM(String fontName, String afmName) throws IOException {
/* 107 */     STANDARD_14_NAMES.add(fontName);
/* 108 */     STANDARD_14_MAPPING.put(fontName, afmName);
/*     */     
/* 110 */     if (STANDARD14_AFM_MAP.containsKey(afmName))
/*     */     {
/* 112 */       STANDARD14_AFM_MAP.put(fontName, STANDARD14_AFM_MAP.get(afmName));
/*     */     }
/*     */     
/* 115 */     String resourceName = "/org/apache/pdfbox/resources/afm/" + afmName + ".afm";
/* 116 */     InputStream afmStream = PDType1Font.class.getResourceAsStream(resourceName);
/* 117 */     if (afmStream == null)
/*     */     {
/* 119 */       throw new IOException(resourceName + " not found");
/*     */     }
/*     */     
/*     */     try {
/* 123 */       AFMParser parser = new AFMParser(afmStream);
/* 124 */       FontMetrics metric = parser.parse(true);
/* 125 */       STANDARD14_AFM_MAP.put(fontName, metric);
/*     */     }
/*     */     finally {
/*     */       
/* 129 */       afmStream.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FontMetrics getAFM(String baseName) {
/* 139 */     return STANDARD14_AFM_MAP.get(baseName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsName(String baseName) {
/* 148 */     return STANDARD_14_NAMES.contains(baseName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set<String> getNames() {
/* 156 */     return Collections.unmodifiableSet(STANDARD_14_NAMES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMappedFontName(String baseName) {
/* 165 */     return STANDARD_14_MAPPING.get(baseName);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/Standard14Fonts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */