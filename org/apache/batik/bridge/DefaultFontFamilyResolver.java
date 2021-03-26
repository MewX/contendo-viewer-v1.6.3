/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.gvt.font.AWTFontFamily;
/*     */ import org.apache.batik.gvt.font.AWTGVTFont;
/*     */ import org.apache.batik.gvt.font.GVTFontFace;
/*     */ import org.apache.batik.gvt.font.GVTFontFamily;
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
/*     */ public final class DefaultFontFamilyResolver
/*     */   implements FontFamilyResolver
/*     */ {
/*  44 */   public static final DefaultFontFamilyResolver SINGLETON = new DefaultFontFamilyResolver();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private static final AWTFontFamily DEFAULT_FONT_FAMILY = new AWTFontFamily("SansSerif");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected static final Map fonts = new HashMap<Object, Object>();
/*     */   
/*  62 */   protected static final List awtFontFamilies = new ArrayList();
/*     */   
/*  64 */   protected static final List awtFonts = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  70 */     fonts.put("sans-serif", "SansSerif");
/*  71 */     fonts.put("serif", "Serif");
/*  72 */     fonts.put("times", "Serif");
/*  73 */     fonts.put("times new roman", "Serif");
/*  74 */     fonts.put("cursive", "Dialog");
/*  75 */     fonts.put("fantasy", "Symbol");
/*  76 */     fonts.put("monospace", "Monospaced");
/*  77 */     fonts.put("monospaced", "Monospaced");
/*  78 */     fonts.put("courier", "Monospaced");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*  86 */     String[] fontNames = env.getAvailableFontFamilyNames();
/*     */     
/*  88 */     int nFonts = (fontNames != null) ? fontNames.length : 0;
/*  89 */     for (int i = 0; i < nFonts; i++) {
/*  90 */       fonts.put(fontNames[i].toLowerCase(), fontNames[i]);
/*     */ 
/*     */       
/*  93 */       StringTokenizer st = new StringTokenizer(fontNames[i]);
/*  94 */       String fontNameWithoutSpaces = "";
/*  95 */       while (st.hasMoreTokens()) {
/*  96 */         fontNameWithoutSpaces = fontNameWithoutSpaces + st.nextToken();
/*     */       }
/*  98 */       fonts.put(fontNameWithoutSpaces.toLowerCase(), fontNames[i]);
/*     */ 
/*     */       
/* 101 */       String fontNameWithDashes = fontNames[i].replace(' ', '-');
/* 102 */       if (!fontNameWithDashes.equals(fontNames[i])) {
/* 103 */         fonts.put(fontNameWithDashes.toLowerCase(), fontNames[i]);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     Font[] allFonts = env.getAllFonts();
/* 111 */     for (Font f : allFonts) {
/* 112 */       fonts.put(f.getFontName().toLowerCase(), f.getFontName());
/*     */     }
/*     */ 
/*     */     
/* 116 */     awtFontFamilies.add(DEFAULT_FONT_FAMILY);
/* 117 */     awtFonts.add(new AWTGVTFont(DEFAULT_FONT_FAMILY.getFamilyName(), 0, 12));
/*     */     
/* 119 */     Collection fontValues = fonts.values();
/* 120 */     for (Object fontValue : fontValues) {
/* 121 */       String fontFamily = (String)fontValue;
/* 122 */       AWTFontFamily awtFontFamily = new AWTFontFamily(fontFamily);
/* 123 */       awtFontFamilies.add(awtFontFamily);
/* 124 */       AWTGVTFont font = new AWTGVTFont(fontFamily, 0, 12);
/* 125 */       awtFonts.add(font);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   protected static final Map resolvedFontFamilies = new HashMap<Object, Object>();
/*     */   
/*     */   public AWTFontFamily resolve(String familyName, FontFace fontFace) {
/* 137 */     String fontName = (String)fonts.get(fontFace.getFamilyName().toLowerCase());
/* 138 */     if (fontName == null) {
/* 139 */       return null;
/*     */     }
/* 141 */     GVTFontFace face = FontFace.createFontFace(fontName, fontFace);
/* 142 */     return new AWTFontFamily(fontFace);
/*     */   }
/*     */ 
/*     */   
/*     */   public GVTFontFamily loadFont(InputStream in, FontFace ff) throws Exception {
/* 147 */     Font font = Font.createFont(0, in);
/* 148 */     return (GVTFontFamily)new AWTFontFamily(ff, font);
/*     */   }
/*     */ 
/*     */   
/*     */   public GVTFontFamily resolve(String familyName) {
/*     */     AWTFontFamily aWTFontFamily;
/* 154 */     familyName = familyName.toLowerCase();
/*     */ 
/*     */     
/* 157 */     GVTFontFamily resolvedFF = (GVTFontFamily)resolvedFontFamilies.get(familyName);
/*     */ 
/*     */     
/* 160 */     if (resolvedFF == null) {
/*     */ 
/*     */       
/* 163 */       String awtFamilyName = (String)fonts.get(familyName);
/* 164 */       if (awtFamilyName != null) {
/* 165 */         aWTFontFamily = new AWTFontFamily(awtFamilyName);
/*     */       }
/*     */       
/* 168 */       resolvedFontFamilies.put(familyName, aWTFontFamily);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     return (GVTFontFamily)aWTFontFamily;
/*     */   }
/*     */ 
/*     */   
/*     */   public GVTFontFamily getFamilyThatCanDisplay(char c) {
/* 183 */     for (int i = 0; i < awtFontFamilies.size(); i++) {
/* 184 */       AWTFontFamily fontFamily = awtFontFamilies.get(i);
/* 185 */       AWTGVTFont font = awtFonts.get(i);
/* 186 */       if (font.canDisplay(c) && fontFamily.getFamilyName().indexOf("Song") == -1)
/*     */       {
/* 188 */         return (GVTFontFamily)fontFamily;
/*     */       }
/*     */     } 
/*     */     
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public GVTFontFamily getDefault() {
/* 197 */     return (GVTFontFamily)DEFAULT_FONT_FAMILY;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/DefaultFontFamilyResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */