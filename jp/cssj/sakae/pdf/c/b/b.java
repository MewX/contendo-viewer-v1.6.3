/*     */ package jp.cssj.sakae.pdf.c.b;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.text.ParseException;
/*     */ import java.util.HashMap;
/*     */ import jp.cssj.sakae.pdf.g.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */ {
/*     */   private a a;
/*     */   private Reader b;
/*     */   private int c;
/*  24 */   private int d = 1;
/*     */   
/*     */   public a a(InputStream in) throws ParseException, IOException {
/*  27 */     this.a = new a();
/*  28 */     this.b = new InputStreamReader(in, "ISO-8859-1");
/*     */     try {
/*  30 */       this.c = this.b.read();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  35 */       String s = c();
/*  36 */       if (!s.equals("StartFontMetrics")) {
/*  37 */         throw new ParseException("AFMファイルではありません", this.d);
/*     */       }
/*  39 */       s = g();
/*     */ 
/*     */       
/*     */       while (true) {
/*  43 */         d();
/*  44 */         s = c();
/*  45 */         switch (s.charAt(0)) {
/*     */           case 'A':
/*  47 */             if (s.equals("Ascender")) {
/*  48 */               this.a.d = h();
/*     */             }
/*     */ 
/*     */           
/*     */           case 'C':
/*  53 */             if (s.equals("CapHeight")) {
/*  54 */               this.a.f = h();
/*     */             }
/*     */ 
/*     */           
/*     */           case 'D':
/*  59 */             if (s.equals("Descender")) {
/*  60 */               this.a.e = (short)-h();
/*     */             }
/*     */ 
/*     */           
/*     */           case 'E':
/*  65 */             if (s.equals("EndFontMetrics")) {
/*     */               break;
/*     */             }
/*     */ 
/*     */           
/*     */           case 'F':
/*  71 */             if (s.equals("FontBBox")) {
/*  72 */               this.a
/*  73 */                 .l = new jp.cssj.sakae.a.b(h(), h(), h(), h()); continue;
/*  74 */             }  if (s.equals("FontName")) {
/*  75 */               this.a.a = g(); continue;
/*  76 */             }  if (s.equals("FullName")) {
/*  77 */               this.a.b = g(); continue;
/*  78 */             }  if (s.equals("FamilyName")) {
/*  79 */               this.a.c = g();
/*     */             }
/*     */ 
/*     */           
/*     */           case 'I':
/*  84 */             if (s.equals("ItalicAngle")) {
/*  85 */               double italicAngle = k();
/*  86 */               if (italicAngle != 0.0D) {
/*  87 */                 this.a.k = true;
/*     */               }
/*     */             } 
/*     */ 
/*     */           
/*     */           case 'S':
/*  93 */             if (s.equals("StdHW")) {
/*  94 */               this.a.h = h(); continue;
/*  95 */             }  if (s.equals("StdVW")) {
/*  96 */               this.a.g = h(); continue;
/*  97 */             }  if (s.equals("StartCharMetrics")) {
/*  98 */               a(); continue;
/*     */             } 
/* 100 */             if (s.equals("StartKernPairs")) {
/* 101 */               b();
/*     */             }
/*     */ 
/*     */           
/*     */           case 'W':
/* 106 */             if (s.equals("Weight")) {
/* 107 */               String weight = e().trim().toUpperCase();
/* 108 */               if (weight.equals("ULTRALIGHT")) {
/* 109 */                 this.a.j = 100; continue;
/* 110 */               }  if (weight.equals("THIN")) {
/* 111 */                 this.a.j = 200; continue;
/* 112 */               }  if (weight.equals("LIGHT") || weight.equals("EXTRALIGHT") || weight.equals("BOOK")) {
/* 113 */                 this.a.j = 300; continue;
/* 114 */               }  if (weight.equals("REGULAR") || weight.equals("PLAIN") || weight.equals("ROMAN") || weight
/* 115 */                 .equals("MEDIUM")) {
/* 116 */                 this.a.j = 400; continue;
/* 117 */               }  if (weight.equals("DEMI") || weight.equals("DEMIBOLD")) {
/* 118 */                 this.a.j = 500; continue;
/* 119 */               }  if (weight.equals("SEMIBOLD")) {
/* 120 */                 this.a.j = 600; continue;
/* 121 */               }  if (weight.equals("BOLD") || weight.equals("EXTRABOLD") || weight.equals("HERAVY") || weight
/* 122 */                 .equals("HEAVYFACE") || weight.equals("BLACK")) {
/* 123 */                 this.a.j = 700; continue;
/* 124 */               }  if (weight.equals("ULTRA") || weight.equals("ULTRABLACK") || weight.equals("FAT")) {
/* 125 */                 this.a.j = 800; continue;
/* 126 */               }  if (weight.equals("EXTRABLACK") || weight.equals("OBESE")) {
/* 127 */                 this.a.j = 900;
/*     */               }
/*     */             } 
/*     */         } 
/*     */       
/*     */       } 
/*     */     } finally {
/* 134 */       this.b.close();
/*     */     } 
/* 136 */     return this.a;
/*     */   }
/*     */   
/*     */   private void a() throws ParseException, IOException {
/* 140 */     this.a.m = new HashMap<>();
/*     */     while (true) {
/* 142 */       d();
/* 143 */       String s = c();
/* 144 */       if (s.equals("EndCharMetrics")) {
/*     */         break;
/*     */       }
/*     */       
/* 148 */       a.a gi = a(s);
/* 149 */       this.a.m.put(gi.b, gi);
/*     */     } 
/*     */   }
/*     */   
/*     */   private a.a a(String s) throws ParseException, IOException {
/* 154 */     a.a gi = new a.a();
/* 155 */     while (s != null) {
/* 156 */       switch (s.charAt(0)) {
/*     */         case 'C':
/* 158 */           if (s.equals("C")) {
/* 159 */             gi.a = i(); break;
/* 160 */           }  if (s.equals("CH")) {
/* 161 */             gi.a = j();
/*     */           }
/*     */           break;
/*     */         
/*     */         case 'L':
/* 166 */           if (s.equals("L")) {
/* 167 */             String sname = g();
/* 168 */             String lname = g();
/* 169 */             gi.a(sname, lname);
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 'N':
/* 174 */           if (s.equals("N")) {
/* 175 */             gi.b = g();
/*     */           }
/*     */           break;
/*     */         
/*     */         case 'W':
/* 180 */           if (s.equals("WX") || s.equals("W0X")) {
/* 181 */             gi.c = h();
/*     */           }
/*     */           break;
/*     */         
/*     */         default:
/*     */           while (true) {
/* 187 */             s = g();
/* 188 */             if (s.equals(";")) {
/* 189 */               s = g();
/*     */             }
/*     */           } 
/*     */       } 
/*     */       
/* 194 */       s = g();
/* 195 */       if (s.equals(";")) {
/* 196 */         s = g();
/*     */         continue;
/*     */       } 
/* 199 */       throw new ParseException("';'が見つかるはずでしたが'" + s + "'が見つかりました", this.d);
/*     */     } 
/* 201 */     return gi;
/*     */   }
/*     */   
/*     */   private void b() throws ParseException, IOException {
/*     */     while (true) {
/* 206 */       d();
/* 207 */       String s = c();
/* 208 */       if (s.equals("EndKernPairs")) {
/*     */         break;
/*     */       }
/*     */       
/* 212 */       switch (s.charAt(0)) {
/*     */         case 'K':
/* 214 */           if (s.equals("KPX")) {
/* 215 */             String pname = g();
/* 216 */             String sname = g();
/* 217 */             short kerning = h();
/* 218 */             a.a sci = this.a.m.get(sname);
/* 219 */             a.a pci = this.a.m.get(pname);
/* 220 */             if (sci != null && pci != null) {
/* 221 */               pci.a(sci.b, kerning);
/*     */             }
/*     */           } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String c() throws IOException {
/*     */     String title;
/*     */     while (true) {
/* 231 */       while (f()) {
/* 232 */         d();
/*     */       }
/* 234 */       StringBuffer buff = new StringBuffer();
/* 235 */       for (; this.c != -1 && !Character.isWhitespace((char)this.c); this.c = this.b.read()) {
/* 236 */         buff.append((char)this.c);
/*     */       }
/* 238 */       if (this.c == -1) {
/* 239 */         throw new EOFException();
/*     */       }
/* 241 */       title = buff.toString();
/* 242 */       if (title.equals("Comment")) {
/* 243 */         d(); continue;
/*     */       }  break;
/*     */     } 
/* 246 */     return title;
/*     */   }
/*     */ 
/*     */   
/*     */   private void d() throws IOException {
/* 251 */     boolean cr = false;
/* 252 */     for (; this.c != -1; this.c = this.b.read()) {
/* 253 */       switch (this.c) {
/*     */         case 10:
/*     */         case 13:
/* 256 */           cr = true;
/*     */           break;
/*     */         
/*     */         default:
/* 260 */           if (cr)
/*     */             break; 
/*     */           break;
/*     */       } 
/*     */     } 
/* 265 */     if (this.c == -1) {
/* 266 */       throw new EOFException();
/*     */     }
/* 268 */     this.d++;
/*     */   }
/*     */   
/*     */   private String e() throws IOException {
/* 272 */     StringBuffer buff = new StringBuffer();
/* 273 */     boolean cr = false;
/* 274 */     for (; this.c != -1; this.c = this.b.read()) {
/* 275 */       switch (this.c) {
/*     */         case 10:
/*     */           break;
/*     */         
/*     */         case 13:
/* 280 */           cr = true;
/*     */           break;
/*     */         
/*     */         default:
/* 284 */           if (cr) {
/*     */             break;
/*     */           }
/* 287 */           buff.append((char)this.c); break;
/*     */       } 
/*     */     } 
/* 290 */     if (this.c == -1) {
/* 291 */       throw new EOFException();
/*     */     }
/* 293 */     this.d++;
/* 294 */     return buff.toString();
/*     */   }
/*     */   
/*     */   private boolean f() throws IOException {
/* 298 */     for (; this.c != -1 && Character.isWhitespace((char)this.c); this.c = this.b.read()) {
/* 299 */       if (this.c == 10 || this.c == 13) {
/* 300 */         return true;
/*     */       }
/*     */     } 
/* 303 */     if (this.c == -1) {
/* 304 */       throw new EOFException();
/*     */     }
/* 306 */     return false;
/*     */   }
/*     */   
/*     */   private String g() throws ParseException, IOException {
/* 310 */     if (f()) {
/* 311 */       return null;
/*     */     }
/* 313 */     StringBuffer buff = new StringBuffer();
/* 314 */     for (; this.c != -1 && !Character.isWhitespace((char)this.c); this.c = this.b.read()) {
/* 315 */       buff.append((char)this.c);
/*     */     }
/* 317 */     if (this.c == -1) {
/* 318 */       throw new EOFException();
/*     */     }
/* 320 */     String s = buff.toString();
/* 321 */     return a.b(s, "MS932");
/*     */   }
/*     */   
/*     */   private short h() throws ParseException, IOException {
/* 325 */     String s = g();
/*     */     try {
/* 327 */       return Short.parseShort(s);
/* 328 */     } catch (NumberFormatException e) {
/* 329 */       throw new ParseException("整数が予想された場所です:" + s, this.d);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int i() throws ParseException, IOException {
/* 334 */     String s = g();
/*     */     try {
/* 336 */       return Integer.parseInt(s);
/* 337 */     } catch (NumberFormatException e) {
/* 338 */       throw new ParseException("整数が予想された場所です:" + s, this.d);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int j() throws ParseException, IOException {
/* 343 */     String s = g();
/*     */     try {
/* 345 */       return Integer.parseInt(s.substring(1, s.length() - 1), 16);
/* 346 */     } catch (NumberFormatException e) {
/* 347 */       throw new ParseException("数値が予想された場所に文字列がありました:" + s, this.d);
/*     */     } 
/*     */   }
/*     */   
/*     */   private double k() throws ParseException, IOException {
/* 352 */     String s = g();
/*     */     try {
/* 354 */       return Float.parseFloat(s);
/* 355 */     } catch (NumberFormatException e) {
/* 356 */       throw new ParseException("実数が予想された場所です:" + s, this.d);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */