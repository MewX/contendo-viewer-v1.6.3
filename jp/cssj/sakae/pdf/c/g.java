/*     */ package jp.cssj.sakae.pdf.c;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jp.cssj.sakae.a.h;
/*     */ import jp.cssj.sakae.a.i;
/*     */ import jp.cssj.sakae.c.a.b;
/*     */ import jp.cssj.sakae.c.a.c;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.a.l;
/*     */ import jp.cssj.sakae.e.d;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c.d.a;
/*     */ import org.apache.commons.collections.map.LRUMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */   implements h
/*     */ {
/*  39 */   protected Map<String, Object> a = new HashMap<>();
/*     */   
/*  41 */   protected Map<String, c> b = new HashMap<>();
/*     */   
/*  43 */   protected Map<URI, File> c = new HashMap<>();
/*     */   
/*  45 */   protected Collection<jp.cssj.sakae.a.g> d = new ArrayList<>();
/*     */   
/*  47 */   protected transient Map<h, jp.cssj.sakae.a.g[]> e = null;
/*     */   
/*     */   protected final boolean f;
/*     */   
/*     */   public g(boolean strictMatchName) {
/*  52 */     this.f = strictMatchName;
/*     */   }
/*     */   
/*     */   public g() {
/*  56 */     this(false);
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/*  60 */     super.finalize();
/*  61 */     for (Iterator<File> i = this.c.values().iterator(); i.hasNext(); ) {
/*  62 */       File file = i.next();
/*  63 */       file.delete();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void a(jp.cssj.sakae.c.a.a face) throws IOException {
/*  68 */     List<jp.cssj.sakae.a.g> list = new ArrayList<>();
/*  69 */     if (face.c != null) {
/*  70 */       list.add(b.a(face, (byte)1, face.c, null));
/*  71 */       list.add(b.a(face, (byte)2, face.c, null));
/*     */     } else {
/*     */       File file;
/*  74 */       if (face.a.k()) {
/*  75 */         file = face.a.l();
/*     */       } else {
/*  77 */         file = this.c.get(face.a.d());
/*  78 */         if (file == null) {
/*  79 */           byte[] buff = new byte[8192];
/*  80 */           file = File.createTempFile("copper-font-face", ".font");
/*  81 */           file.deleteOnExit();
/*  82 */           try(InputStream in = face.a.h(); OutputStream out = new FileOutputStream(file)) {
/*  83 */             int len; for (len = in.read(buff); len != -1; len = in.read(buff)) {
/*  84 */               out.write(buff, 0, len);
/*     */             }
/*     */           } 
/*  87 */           this.c.put(face.a.d(), file);
/*     */         } 
/*     */       } 
/*  90 */       b.a(list, face, (byte)1, file, face.b, null);
/*  91 */       b.a(list, face, (byte)2, file, face.b, null);
/*     */     } 
/*     */     
/*  94 */     if (face.g != null && !face.g.b()) {
/*  95 */       for (int i = 0; i < list.size(); i++) {
/*  96 */         f source = (f)list.get(i);
/*  97 */         list.set(i, new a(source, face.g));
/*     */       } 
/*     */     }
/* 100 */     this.d.addAll(list);
/*     */     
/* 102 */     List<String> m = new ArrayList<>();
/* 103 */     if (face.d != null) {
/* 104 */       for (int i = 0; i < face.d.b(); i++) {
/* 105 */         b family = face.d.a(i);
/* 106 */         String name = family.c();
/* 107 */         if (family.a()) {
/*     */           
/* 109 */           c generics = this.b.get(name);
/* 110 */           if (generics == null) {
/* 111 */             this.b.put(name, new c(new b(name)));
/*     */           } else {
/* 113 */             boolean found = false;
/* 114 */             for (int j = 0; j < generics.b(); j++) {
/* 115 */               if (generics.a(j).c().equals(name)) {
/* 116 */                 found = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 120 */             if (!found) {
/* 121 */               b[] families = new b[generics.b() + 1];
/* 122 */               for (int k = 0; k < generics.b(); k++) {
/* 123 */                 families[k] = generics.a(k);
/*     */               }
/* 125 */               families[generics.b()] = new b(name);
/* 126 */               this.b.put(name, new c(families));
/*     */             } 
/*     */           } 
/*     */         } 
/* 130 */         name = jp.cssj.sakae.c.a.a.a.a(name);
/* 131 */         if (!m.contains(name)) {
/*     */ 
/*     */           
/* 134 */           for (int j = 0; j < list.size(); j++) {
/* 135 */             jp.cssj.sakae.a.g source = list.get(j);
/* 136 */             a.b(this.a, name, source);
/*     */           } 
/* 138 */           m.add(name);
/*     */         } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized jp.cssj.sakae.a.g[] a(h fontStyle) {
/* 167 */     if (fontStyle == null) {
/* 168 */       return this.d.<jp.cssj.sakae.a.g>toArray(new jp.cssj.sakae.a.g[this.d.size()]);
/*     */     }
/*     */ 
/*     */     
/* 172 */     if (this.e != null) {
/* 173 */       jp.cssj.sakae.a.g[] arrayOfG = this.e.get(fontStyle);
/* 174 */       if (arrayOfG != null) {
/* 175 */         return arrayOfG;
/*     */       }
/*     */     } 
/*     */     
/* 179 */     List<jp.cssj.sakae.a.g> fontList = new ArrayList<>();
/* 180 */     a(fontStyle, fontStyle.d(), fontList, false);
/* 181 */     jp.cssj.sakae.a.g[] fonts = fontList.<jp.cssj.sakae.a.g>toArray(new jp.cssj.sakae.a.g[fontList.size()]);
/* 182 */     if (this.e == null) {
/* 183 */       this.e = (Map<h, jp.cssj.sakae.a.g[]>)new LRUMap();
/*     */     }
/* 185 */     this.e.put(fontStyle, fonts);
/* 186 */     return fonts;
/*     */   }
/*     */   
/*     */   protected void a(h fontStyle, c family, List<jp.cssj.sakae.a.g> fontList, boolean recurse) {
/* 190 */     for (int i = 0; i < family.b(); i++) {
/*     */       
/* 192 */       b entry = family.a(i);
/* 193 */       String name = entry.c();
/*     */ 
/*     */       
/* 196 */       if (entry.a()) {
/* 197 */         if (recurse) {
/* 198 */           throw new IllegalStateException("一般フォントが一般フォントで定義されています");
/*     */         }
/* 200 */         c gfamily = this.b.get(name);
/* 201 */         if (gfamily != null) {
/* 202 */           a(fontStyle, gfamily, fontList, true);
/*     */         }
/*     */       } else {
/*     */         
/* 206 */         name = jp.cssj.sakae.c.a.a.a.a(name);
/* 207 */         jp.cssj.sakae.a.g[] fonts = a.a(this.a, name);
/* 208 */         if (fonts != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 214 */           jp.cssj.sakae.c.a.g policy = fontStyle.f();
/* 215 */           Object[][] orders = new Object[fonts.length][2]; int j;
/* 216 */           for (j = 0; j < fonts.length; j++) {
/* 217 */             jp.cssj.sakae.a.g font = fonts[j];
/* 218 */             int order = 0;
/*     */ 
/*     */             
/* 221 */             if (font instanceof f) {
/* 222 */               f pdfFont = (f)font;
/* 223 */               byte type = pdfFont.h_();
/* 224 */               for (int k = 0; k < policy.b(); k++)
/* 225 */               { byte policyCode = policy.a(k);
/* 226 */                 switch (policyCode)
/*     */                 
/*     */                 { case 0:
/* 229 */                     if (type != 1) {
/*     */                       break;
/*     */                     }
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
/* 262 */                     order = policy.b() - k + 1; break;case 1: if (type != 4) break;  order = policy.b() - k + 1; break;case 2: if (type != 3) break;  order = policy.b() - k + 1; break;case 3: if (type != 2) break;  order = policy.b() - k + 1; break;
/*     */                   case 4: break;
/*     */                   default:
/* 265 */                     throw new IllegalStateException(); }  }  if (order == 0) {
/*     */                 continue;
/*     */               }
/*     */             } else {
/*     */               
/* 270 */               order = 1;
/*     */             } 
/*     */ 
/*     */             
/* 274 */             byte direction = fontStyle.a();
/* 275 */             byte fsDirection = font.e();
/* 276 */             if (direction != 3 && fsDirection == 3) {
/*     */               continue;
/*     */             }
/*     */ 
/*     */             
/* 281 */             order <<= 4;
/* 282 */             String fontName = jp.cssj.sakae.c.a.a.a.a(font.d());
/* 283 */             if (fontName.equals(name)) {
/* 284 */               order |= 0x1;
/* 285 */             } else if (this.f) {
/*     */               continue;
/*     */             } 
/*     */ 
/*     */             
/* 290 */             order <<= 4;
/* 291 */             short style = (short)fontStyle.c();
/* 292 */             if (style == 2) {
/* 293 */               if (font.b()) {
/* 294 */                 order |= 0x1;
/*     */               }
/* 296 */             } else if (style == 1 && 
/* 297 */               !font.b()) {
/* 298 */               order |= 0x1;
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 303 */             order <<= 4;
/* 304 */             short weight = fontStyle.b();
/* 305 */             int delta = Math.abs(font.c() - weight);
/* 306 */             order |= 0xF & (1000 - delta) / 100;
/*     */ 
/*     */             
/* 309 */             order <<= 4;
/* 310 */             if (style == 3 && 
/* 311 */               font.b()) {
/* 312 */               order |= 0x1;
/*     */             }
/*     */             
/* 315 */             orders[j][0] = d.a(order);
/* 316 */             orders[j][1] = font; continue;
/*     */           } 
/* 318 */           Arrays.sort(orders, (Comparator)g);
/* 319 */           for (j = 0; j < fonts.length; j++) {
/* 320 */             Integer order = (Integer)orders[j][0];
/* 321 */             if (order != null) {
/* 322 */               jp.cssj.sakae.a.g font = (jp.cssj.sakae.a.g)orders[j][1];
/* 323 */               fontList.add(font);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 329 */   } private static final Comparator<Object[]> g = new Comparator<Object[]>() {
/*     */       public int a(Object[] f1, Object[] f2) {
/* 331 */         Integer i1 = (Integer)f1[0];
/* 332 */         Integer i2 = (Integer)f2[0];
/* 333 */         if ((((i1 == null) ? 1 : 0) & ((i2 == null) ? 1 : 0)) != 0) {
/* 334 */           return 0;
/*     */         }
/* 336 */         if ((((i1 == null) ? 1 : 0) & ((i2 != null) ? 1 : 0)) != 0) {
/* 337 */           return 1;
/*     */         }
/* 339 */         if ((((i1 != null) ? 1 : 0) & ((i2 == null) ? 1 : 0)) != 0) {
/* 340 */           return -1;
/*     */         }
/* 342 */         if (i1.intValue() < i2.intValue()) {
/* 343 */           return 1;
/*     */         }
/* 345 */         if (i1.intValue() > i2.intValue()) {
/* 346 */           return -1;
/*     */         }
/* 348 */         return 0;
/*     */       }
/*     */     };
/*     */   
/*     */   private static class a
/*     */     extends i implements f {
/*     */     private static final long i = 1L;
/*     */     protected final l f;
/*     */     
/*     */     public a(f source, l includes) {
/* 358 */       super(source);
/* 359 */       this.f = includes;
/* 360 */       if (!g && includes.b()) throw new AssertionError(); 
/*     */     }
/*     */     
/*     */     public boolean a(int c) {
/* 364 */       if (this.f.a(c)) {
/* 365 */         return this.W_.a(c);
/*     */       }
/* 367 */       return false;
/*     */     }
/*     */     
/*     */     public e a(String name, b fontRef) {
/* 371 */       return ((f)this.W_).a(name, fontRef);
/*     */     }
/*     */     
/*     */     public byte h_() {
/* 375 */       return ((f)this.W_).h_();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */