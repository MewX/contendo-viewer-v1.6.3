/*     */ package jp.cssj.sakae.pdf.c;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.sakae.a.a;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.c.a.a;
/*     */ import jp.cssj.sakae.c.a.a.a;
/*     */ import jp.cssj.sakae.c.a.c;
/*     */ import jp.cssj.sakae.c.a.j;
/*     */ import jp.cssj.sakae.e.d;
/*     */ import jp.cssj.sakae.pdf.c.a.a.b;
/*     */ import jp.cssj.sakae.pdf.c.a.a.d;
/*     */ import jp.cssj.sakae.pdf.c.a.b.b;
/*     */ import jp.cssj.sakae.pdf.c.a.b.d;
/*     */ import jp.cssj.sakae.pdf.c.a.c.b;
/*     */ import jp.cssj.sakae.pdf.c.a.c.c;
/*     */ import jp.cssj.sakae.pdf.c.a.c.d;
/*     */ import jp.cssj.sakae.pdf.c.a.f;
/*     */ import jp.cssj.sakae.pdf.c.a.l;
/*     */ import jp.cssj.sakae.pdf.c.a.m;
/*     */ import jp.cssj.sakae.pdf.c.b.a;
/*     */ import jp.cssj.sakae.pdf.c.b.b;
/*     */ import jp.cssj.sakae.pdf.c.b.d;
/*     */ import jp.cssj.sakae.pdf.c.b.f;
/*     */ import jp.cssj.sakae.pdf.c.b.g;
/*     */ import jp.cssj.sakae.pdf.c.b.h;
/*     */ import jp.cssj.sakae.pdf.c.d.a;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class b
/*     */ {
/*  51 */   private static final Logger d = Logger.getLogger(b.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte a = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte b = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte c = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(List<g> list, a face, byte type, File ttfFile, int index, Map<String, f> nameToCMap) throws IOException {
/*     */     int i;
/*     */     f cmapObj, vcmapObj;
/*     */     int j;
/*  76 */     String fileName = ttfFile.getName();
/*  77 */     if (fileName.endsWith(".pfa") || fileName.endsWith(".PFA") || fileName.endsWith(".pfb") || fileName
/*  78 */       .endsWith(".PFB") || fileName.endsWith(".f3b") || fileName.endsWith(".F3B")) {
/*     */       
/*     */       try {
/*  81 */         Method createFont = Font.class.getMethod("createFont", new Class[] { int.class, File.class });
/*     */         
/*  83 */         Font awtFont = (Font)createFont.invoke(null, new Object[] {
/*  84 */               d.a(1), ttfFile });
/*  85 */         list.add(a(face, type, awtFont, nameToCMap));
/*     */         return;
/*  87 */       } catch (Exception e) {
/*  88 */         d.log(Level.WARNING, "TYPE1フォントを読み込もうとして失敗しました。", e);
/*     */       } 
/*     */     }
/*     */     
/*  92 */     d.fine("TrueTypeフォント: " + face.d);
/*     */     
/*  94 */     switch (type) {
/*     */       case 1:
/*  96 */         for (i = 0; i < 2; i++) {
/*  97 */           b tefont = new b(ttfFile, index, (i == 0) ? 1 : 3);
/*     */           
/*  99 */           if (face.h != null) {
/* 100 */             tefont.a(face.h);
/*     */           }
/* 102 */           if (face.d != null) {
/* 103 */             tefont.a(face.d.a(0).c());
/*     */           }
/* 105 */           if (face.f != -1) {
/* 106 */             tefont.a((face.f == 2));
/*     */           }
/* 108 */           if (face.e != -1) {
/* 109 */             tefont.a(face.e);
/*     */           }
/* 111 */           if (i == 0) {
/* 112 */             list.add(tefont);
/* 113 */             if (tefont.o().a(1196643650) == null) {
/*     */               break;
/*     */             }
/*     */           } else {
/* 117 */             list.add(list.size() - 1, tefont);
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       case 2:
/* 122 */         for (i = 0; i < 2; i++) {
/* 123 */           b tifont = new b(ttfFile, index, (i == 0) ? 1 : 3);
/*     */           
/* 125 */           if (face.h != null) {
/* 126 */             tifont.a(face.h);
/*     */           }
/* 128 */           if (face.d != null) {
/* 129 */             tifont.a(face.d.a(0).c());
/*     */           }
/* 131 */           if (face.f != -1) {
/* 132 */             tifont.a((face.f == 2));
/*     */           }
/* 134 */           if (face.e != -1) {
/* 135 */             tifont.a(face.e);
/*     */           }
/* 137 */           if (i == 0) {
/* 138 */             list.add(tifont);
/* 139 */             if (tifont.o().a(1196643650) == null) {
/*     */               break;
/*     */             }
/*     */           } else {
/* 143 */             list.add(list.size() - 1, tifont);
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       case 3:
/* 148 */         cmapObj = nameToCMap.get(face.i);
/* 149 */         vcmapObj = (face.j == null) ? null : nameToCMap.get(face.j);
/* 150 */         for (j = 0; j < 2; j++) {
/* 151 */           c ckfont = new c(cmapObj, (j == 0) ? vcmapObj : null, ttfFile, index);
/*     */           
/* 153 */           if (face.h != null) {
/* 154 */             ckfont.a(face.h);
/*     */           }
/* 156 */           if (face.d != null) {
/* 157 */             ckfont.a(face.d.a(0).c());
/*     */           }
/* 159 */           if (face.f != -1) {
/* 160 */             ckfont.a((face.f == 2));
/*     */           }
/* 162 */           if (face.e != -1) {
/* 163 */             ckfont.a(face.e);
/*     */           }
/* 165 */           list.add(ckfont);
/* 166 */           if (vcmapObj == null) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         return;
/*     */     } 
/* 172 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static f a(a face, byte type, Font awtFont, Map<String, f> nameToCMap) throws IOException {
/*     */     d d3;
/*     */     d d2;
/*     */     d d1;
/*     */     d sefont;
/*     */     d scfont;
/*     */     f cmapObj, vcmapObj;
/*     */     d ckfont;
/* 188 */     switch (type) {
/*     */       case 1:
/* 190 */         sefont = new d(awtFont);
/* 191 */         d3 = sefont;
/* 192 */         if (face.h != null) {
/* 193 */           sefont.a(face.h);
/*     */         }
/* 195 */         if (face.d != null) {
/* 196 */           sefont.a(face.d.a(0).c());
/*     */         }
/*     */         break;
/*     */       case 2:
/* 200 */         scfont = new d(awtFont);
/* 201 */         d2 = scfont;
/* 202 */         if (face.h != null) {
/* 203 */           scfont.a(face.h);
/*     */         }
/* 205 */         if (face.d != null) {
/* 206 */           scfont.a(face.d.a(0).c());
/*     */         }
/*     */         break;
/*     */       case 3:
/* 210 */         cmapObj = nameToCMap.get(face.i);
/* 211 */         vcmapObj = (face.j == null) ? null : nameToCMap.get(face.j);
/* 212 */         ckfont = new d(cmapObj, vcmapObj, awtFont);
/* 213 */         d1 = ckfont;
/* 214 */         if (face.h != null) {
/* 215 */           ckfont.a(face.h);
/*     */         }
/* 217 */         if (face.d != null) {
/* 218 */           ckfont.a(face.d.a(0).c());
/*     */         }
/*     */         break;
/*     */       default:
/* 222 */         throw new IllegalArgumentException();
/*     */     } 
/*     */     
/* 225 */     if (face.f != -1) {
/* 226 */       ((a)d1).a((face.f == 2));
/*     */     }
/* 228 */     if (face.e != -1) {
/* 229 */       ((a)d1).a(face.e);
/*     */     }
/*     */     
/* 232 */     d.fine("システムフォント: " + d1);
/* 233 */     return (f)d1;
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
/*     */   public static f[] a(jp.cssj.e.b warrayData, a face, Map<String, f> nameToCMap) throws IOException {
/* 247 */     f cmapObj = nameToCMap.get(face.i);
/* 248 */     f vcmapObj = (face.j == null) ? null : nameToCMap.get(face.j);
/* 249 */     f[] result = new f[(vcmapObj == null) ? 1 : 2];
/*     */     
/* 251 */     for (int k = 0; k < result.length; k++) {
/* 252 */       b source = new b(cmapObj, (k == 0) ? vcmapObj : null);
/* 253 */       source.a(face.d.a(0).c());
/*     */       
/* 255 */       if (face.h != null) {
/* 256 */         source.a(face.h);
/*     */       }
/*     */ 
/*     */       
/* 260 */       try (BufferedReader in = new BufferedReader(new InputStreamReader(warrayData
/* 261 */               .h(), "ISO-8859-1"))) {
/* 262 */         for (String line = in.readLine(); line != null; line = in.readLine()) {
/* 263 */           if (line.length() <= 0 || line.charAt(0) != '#') {
/*     */ 
/*     */             
/* 266 */             int colon = line.indexOf(':');
/* 267 */             if (colon != -1) {
/*     */ 
/*     */               
/* 270 */               String name = line.substring(0, colon).trim();
/* 271 */               String value = line.substring(colon + 1).trim();
/* 272 */               if (name.equalsIgnoreCase("bbox")) {
/* 273 */                 String[] values = value.split(" ");
/*     */                 
/* 275 */                 jp.cssj.sakae.a.b bbox = new jp.cssj.sakae.a.b(Short.parseShort(values[0]), Short.parseShort(values[1]), Short.parseShort(values[2]), Short.parseShort(values[3]));
/* 276 */                 source.a(bbox);
/* 277 */               } else if (name.equalsIgnoreCase("ascent")) {
/* 278 */                 source.b(Short.parseShort(value));
/* 279 */               } else if (name.equalsIgnoreCase("descent")) {
/* 280 */                 source.c(Short.parseShort(value));
/* 281 */               } else if (name.equalsIgnoreCase("capHeight")) {
/* 282 */                 source.d(Short.parseShort(value));
/* 283 */               } else if (name.equalsIgnoreCase("xHeight")) {
/* 284 */                 source.e(Short.parseShort(value));
/* 285 */               } else if (name.equalsIgnoreCase("StemH")) {
/* 286 */                 source.f(Short.parseShort(value));
/* 287 */               } else if (name.equalsIgnoreCase("StemV")) {
/* 288 */                 source.g(Short.parseShort(value));
/* 289 */               } else if (name.equalsIgnoreCase("warray")) {
/* 290 */                 int count = Integer.parseInt(in.readLine());
/* 291 */                 short defaultWidth = Short.parseShort(in.readLine());
/* 292 */                 m[] widths = new m[count];
/* 293 */                 for (int i = 0; i < count; i++) {
/* 294 */                   String wline = in.readLine();
/* 295 */                   String[] values = wline.split(" ");
/* 296 */                   short[] w = new short[values.length - 2];
/* 297 */                   for (int j = 0; j < w.length; j++) {
/* 298 */                     w[j] = Short.parseShort(values[j + 2]);
/*     */                   }
/* 300 */                   widths[i] = new m(Short.parseShort(values[0]), Short.parseShort(values[1]), w);
/*     */                 } 
/* 302 */                 l warrayObj = new l(defaultWidth, widths);
/* 303 */                 source.a(warrayObj);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 308 */       }  if (face.f != -1) {
/* 309 */         source.a((face.f == 2));
/*     */       }
/* 311 */       if (face.e != -1) {
/* 312 */         source.a(face.e);
/*     */       }
/*     */       
/* 315 */       d.fine("CID-Keyedフォント: " + source);
/* 316 */       result[k] = (f)source;
/*     */     } 
/* 318 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(a face, List<g> list, String types, Font font, Map<String, f> nameToCMap) throws IOException {
/* 323 */     if (types.indexOf("cid-keyed") != -1) {
/* 324 */       list.add(a(face, (byte)3, font, nameToCMap));
/*     */     }
/* 326 */     if (types.indexOf("cid-identity") != -1) {
/* 327 */       list.add(a(face, (byte)2, font, nameToCMap));
/*     */     }
/* 329 */     if (types.indexOf("embedded") != -1) {
/* 330 */       list.add(a(face, (byte)1, font, nameToCMap));
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
/*     */   private static a a(InputStream in) throws ParseException, IOException {
/* 343 */     b afm = new b();
/* 344 */     a font = afm.a(new BufferedInputStream(in));
/* 345 */     return font;
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
/*     */   public static f a(f unicodeEncoding, d pdfEncoding, InputStream in) throws ParseException, IOException {
/* 360 */     a font = a(in);
/* 361 */     g source = new g(unicodeEncoding, pdfEncoding, font);
/* 362 */     d.fine("Core AFMフォント: " + source);
/* 363 */     return (f)source;
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
/*     */   public static f a(InputStream in, jp.cssj.e.b toUnicodeFile) throws ParseException, IOException {
/* 377 */     a font = a(in);
/* 378 */     h source = new h(font, toUnicodeFile);
/* 379 */     d.fine("Core Symbolフォント: " + source);
/* 380 */     return (f)source;
/*     */   }
/*     */   
/*     */   private static boolean a(String italic) {
/* 384 */     return "true".equals(italic);
/*     */   }
/*     */   
/*     */   private static short b(String weight) {
/* 388 */     if (weight == null)
/* 389 */       return 400; 
/* 390 */     if (weight.equals("100"))
/* 391 */       return 100; 
/* 392 */     if (weight.equals("200"))
/* 393 */       return 200; 
/* 394 */     if (weight.equals("300"))
/* 395 */       return 300; 
/* 396 */     if (weight.equals("400"))
/* 397 */       return 400; 
/* 398 */     if (weight.equals("500"))
/* 399 */       return 500; 
/* 400 */     if (weight.equals("600"))
/* 401 */       return 600; 
/* 402 */     if (weight.equals("700"))
/* 403 */       return 700; 
/* 404 */     if (weight.equals("800"))
/* 405 */       return 800; 
/* 406 */     if (weight.equals("900")) {
/* 407 */       return 900;
/*     */     }
/* 409 */     return 400;
/*     */   }
/*     */   
/*     */   private static j c(String panoseStr) {
/* 413 */     String[] codes = panoseStr.split(" ");
/* 414 */     short cFamilyClass = 0;
/* 415 */     cFamilyClass = (short)(cFamilyClass | Byte.parseByte(codes[0].trim()) << 8);
/* 416 */     cFamilyClass = (short)(cFamilyClass | Byte.parseByte(codes[1].trim()));
/* 417 */     byte[] bytes = new byte[10];
/* 418 */     for (int i = 0; i < bytes.length; i++) {
/* 419 */       bytes[i] = Byte.parseByte(codes[i + 2].trim());
/*     */     }
/* 421 */     j panose = new j(cFamilyClass, bytes);
/* 422 */     return panose;
/*     */   }
/*     */   
/*     */   public static a a(Attributes atts) {
/* 426 */     a face = new a();
/* 427 */     face.i = atts.getValue("cmap");
/* 428 */     face.j = atts.getValue("vcmap");
/* 429 */     String italic = atts.getValue("italic");
/* 430 */     if (italic != null) {
/* 431 */       face.f = a(italic) ? 2 : 1;
/*     */     }
/* 433 */     String weight = atts.getValue("weight");
/* 434 */     if (weight != null) {
/* 435 */       face.e = b(weight);
/*     */     }
/* 437 */     String panose = atts.getValue("panose");
/* 438 */     if (panose != null) {
/* 439 */       face.h = c(panose);
/*     */     }
/* 441 */     String fontName = atts.getValue("name");
/* 442 */     if (fontName != null) {
/* 443 */       face.d = new c(jp.cssj.sakae.c.a.b.a(fontName));
/*     */     }
/*     */     
/* 446 */     return face;
/*     */   }
/*     */   
/*     */   public static void a(g source, Map<String, Object> nameToFonts) {
/* 450 */     List<String> m = new ArrayList<>();
/* 451 */     if (source.d() != null) {
/* 452 */       String name = a.a(source.d());
/* 453 */       m.add(name);
/* 454 */       a.a(nameToFonts, name, source);
/*     */     } 
/* 456 */     String[] aliases = source.a();
/* 457 */     for (int i = 0; i < aliases.length; i++) {
/* 458 */       String aliase = aliases[i];
/* 459 */       String name = a.a(aliase);
/* 460 */       if (!m.contains(name)) {
/*     */ 
/*     */         
/* 463 */         m.add(name);
/* 464 */         a.a(nameToFonts, name, source);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */