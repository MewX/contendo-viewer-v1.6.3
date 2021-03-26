/*     */ package org.apache.pdfbox.rendering;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
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
/*     */ final class Type1Glyph2D
/*     */   implements Glyph2D
/*     */ {
/*  33 */   private static final Log LOG = LogFactory.getLog(Type1Glyph2D.class);
/*     */   
/*  35 */   private final Map<Integer, GeneralPath> cache = new HashMap<Integer, GeneralPath>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final PDSimpleFont font;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Type1Glyph2D(PDSimpleFont font) {
/*  45 */     this.font = font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPathForCharacterCode(int code) {
/*  52 */     GeneralPath path = this.cache.get(Integer.valueOf(code));
/*  53 */     if (path == null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/*  58 */         String name = this.font.getEncoding().getName(code);
/*  59 */         if (!this.font.hasGlyph(name)) {
/*     */           
/*  61 */           LOG.warn("No glyph for code " + code + " (" + name + ") in font " + this.font.getName());
/*  62 */           if (code == 10 && this.font.isStandard14()) {
/*     */ 
/*     */             
/*  65 */             path = new GeneralPath();
/*  66 */             this.cache.put(Integer.valueOf(code), path);
/*  67 */             return path;
/*     */           } 
/*     */ 
/*     */           
/*  71 */           String unicodes = this.font.getGlyphList().toUnicode(name);
/*  72 */           if (unicodes != null && unicodes.length() == 1) {
/*     */             
/*  74 */             String uniName = getUniNameOfCodePoint(unicodes.codePointAt(0));
/*  75 */             if (this.font.hasGlyph(uniName))
/*     */             {
/*  77 */               name = uniName;
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/*  83 */         path = this.font.getPath(name);
/*  84 */         if (path == null)
/*     */         {
/*  86 */           path = this.font.getPath(".notdef");
/*     */         }
/*     */         
/*  89 */         this.cache.put(Integer.valueOf(code), path);
/*  90 */         return path;
/*     */       }
/*  92 */       catch (IOException e) {
/*     */ 
/*     */         
/*  95 */         LOG.error("Glyph rendering failed", e);
/*  96 */         path = new GeneralPath();
/*     */       } 
/*     */     }
/*  99 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 105 */     this.cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getUniNameOfCodePoint(int codePoint) {
/* 111 */     String hex = Integer.toString(codePoint, 16).toUpperCase(Locale.US);
/* 112 */     switch (hex.length()) {
/*     */       
/*     */       case 1:
/* 115 */         return "uni000" + hex;
/*     */       case 2:
/* 117 */         return "uni00" + hex;
/*     */       case 3:
/* 119 */         return "uni0" + hex;
/*     */     } 
/* 121 */     return "uni" + hex;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/Type1Glyph2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */