/*     */ package net.a.a.f;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.FontFormatException;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.concurrent.ThreadSafe;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.util.ClasspathResource;
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
/*     */ @ThreadSafe
/*     */ public class a
/*     */   extends b
/*     */ {
/*     */   private static final int b = 12;
/*     */   private static final int c = 4;
/*  59 */   private static final Log d = LogFactory.getLog(a.class);
/*     */   
/*  61 */   private final Map<String, Font[]> e = (Map)new HashMap<String, Font>();
/*     */   
/*     */   a() {
/*  64 */     c();
/*  65 */     d();
/*     */   }
/*     */ 
/*     */   
/*     */   private void c() {
/*  70 */     String[] arrayOfString = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
/*  71 */     for (String str : arrayOfString) {
/*  72 */       for (byte b1 = 0; b1 < 4; b1++) {
/*  73 */         Font font = new Font(str, b1, 12);
/*     */         
/*  75 */         a(font);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void d() {
/*  83 */     List list = ClasspathResource.getInstance().listResourcesOfMimeType("application/x-font");
/*  84 */     for (URL uRL : list) {
/*     */       try {
/*     */         try {
/*  87 */           a(Font.createFont(0, uRL
/*  88 */                 .openStream()));
/*  89 */         } catch (FontFormatException fontFormatException) {
/*     */           try {
/*  91 */             a(Font.createFont(1, uRL
/*  92 */                   .openStream()));
/*  93 */           } catch (FontFormatException fontFormatException1) {
/*  94 */             d.warn(fontFormatException.getMessage());
/*     */           } 
/*     */         } 
/*  97 */       } catch (IOException iOException) {
/*  98 */         d.warn(iOException.getMessage());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font a(String paramString, int paramInt, float paramFloat) {
/*     */     Font font;
/* 120 */     synchronized (this.e) {
/* 121 */       Font[] arrayOfFont = this.e.get(paramString
/* 122 */           .toLowerCase(Locale.ENGLISH));
/* 123 */       if (arrayOfFont == null) {
/*     */ 
/*     */ 
/*     */         
/* 127 */         font = a(new Font(paramString, 0, 12)).deriveFont(paramInt, paramFloat);
/*     */       } else {
/* 129 */         font = arrayOfFont[paramInt];
/* 130 */         if (font == null) {
/* 131 */           font = arrayOfFont[0].deriveFont(paramInt, paramFloat);
/* 132 */           arrayOfFont[paramInt] = font;
/*     */         } else {
/* 134 */           font = font.deriveFont(paramFloat);
/*     */         } 
/*     */       } 
/*     */     } 
/* 138 */     return font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font a(List<String> paramList, int paramInt1, int paramInt2, float paramFloat) {
/* 145 */     Font font = a(paramList, paramInt1, paramInt2, paramFloat);
/*     */     
/* 147 */     if (font == null) {
/* 148 */       font = a(this.e.keySet(), paramInt1, paramInt2, paramFloat);
/*     */     }
/*     */     
/* 151 */     return font;
/*     */   }
/*     */ 
/*     */   
/*     */   private Font a(Collection<String> paramCollection, int paramInt1, int paramInt2, float paramFloat) {
/* 156 */     for (String str1 : paramCollection) {
/* 157 */       Font font = a(str1, paramInt2, paramFloat);
/* 158 */       String str2 = str1.trim();
/* 159 */       if ((font.getFamily().equalsIgnoreCase(str2) || font
/* 160 */         .getFontName().equalsIgnoreCase(str2)) && font
/* 161 */         .canDisplay(paramInt1)) {
/* 162 */         return font;
/*     */       }
/*     */     } 
/* 165 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font a(int paramInt, File paramFile) throws IOException, FontFormatException {
/* 189 */     return a(Font.createFont(paramInt, paramFile));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font a(int paramInt, InputStream paramInputStream) throws IOException, FontFormatException {
/* 213 */     return a(Font.createFont(paramInt, paramInputStream));
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
/*     */   private Font a(Font paramFont) {
/* 225 */     String str1 = paramFont.getFamily().trim().toLowerCase(Locale.ENGLISH);
/*     */     
/* 227 */     String str2 = paramFont.getName().trim().toLowerCase(Locale.ENGLISH);
/*     */     
/* 229 */     int i = paramFont.getStyle();
/* 230 */     if (str2.contains("italic")) {
/* 231 */       i |= 0x2;
/*     */     }
/* 233 */     if (str2.contains("oblique")) {
/* 234 */       i |= 0x2;
/*     */     }
/* 236 */     if (str2.contains("bold")) {
/* 237 */       i |= 0x1;
/*     */     }
/* 239 */     a(paramFont, str1, i);
/* 240 */     a(paramFont, str2, i);
/* 241 */     return paramFont;
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Font paramFont, String paramString, int paramInt) {
/* 246 */     synchronized (this.e) {
/* 247 */       Font[] arrayOfFont = this.e.get(paramString);
/* 248 */       if (arrayOfFont == null) {
/* 249 */         arrayOfFont = new Font[4];
/* 250 */         this.e.put(paramString, arrayOfFont);
/* 251 */         arrayOfFont[0] = paramFont;
/*     */       } 
/* 253 */       arrayOfFont[paramInt] = paramFont;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> a() {
/*     */     HashSet<String> hashSet;
/* 261 */     synchronized (this.e) {
/* 262 */       hashSet = new HashSet(this.e.keySet());
/*     */     } 
/* 264 */     return hashSet;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/f/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */