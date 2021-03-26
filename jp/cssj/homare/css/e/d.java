/*     */ package jp.cssj.homare.css.e;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.f.b;
/*     */ import jp.cssj.homare.css.f.A;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.U;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.a.h;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.u;
/*     */ import jp.cssj.homare.css.f.w;
/*     */ import jp.cssj.homare.css.f.x;
/*     */ import jp.cssj.homare.css.f.y;
/*     */ import jp.cssj.homare.css.f.z;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.b;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
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
/*     */ public final class d
/*     */ {
/*     */   public static x a(m ua, LexicalUnit lu) {
/*  42 */     List<b> list = new ArrayList<>(); while (true) {
/*     */       String ident;
/*  44 */       switch (lu.getLexicalUnitType()) {
/*     */         case 35:
/*  46 */           ident = lu.getStringValue().toLowerCase();
/*  47 */           if (ident.equalsIgnoreCase("cursive")) {
/*  48 */             list.add(b.h); break;
/*     */           } 
/*  50 */           if (ident.equalsIgnoreCase("fantasy")) {
/*  51 */             list.add(b.i); break;
/*     */           } 
/*  53 */           if (ident.equalsIgnoreCase("monospace")) {
/*  54 */             list.add(b.j); break;
/*     */           } 
/*  56 */           if (ident.equalsIgnoreCase("sans-serif")) {
/*  57 */             list.add(b.g); break;
/*     */           } 
/*  59 */           if (ident.equalsIgnoreCase("serif")) {
/*  60 */             list.add(b.f);
/*     */             break;
/*     */           } 
/*     */         
/*     */         case 36:
/*  65 */           list.add(new b(lu.getStringValue()));
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  71 */       while ((lu = lu.getNextLexicalUnit()) != null && lu
/*  72 */         .getLexicalUnitType() == 0);
/*     */ 
/*     */       
/*  75 */       if (lu == null) {
/*  76 */         if (list.isEmpty()) {
/*  77 */           return null;
/*     */         }
/*  79 */         x defaultFamily = ua.i();
/*  80 */         for (int i = 0; i < defaultFamily.b(); i++) {
/*  81 */           list.add(defaultFamily.a(i));
/*     */         }
/*  83 */         return new x(list.<b>toArray(new b[list.size()]));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static y a(LexicalUnit lu) {
/*  93 */     short luType = lu.getLexicalUnitType();
/*  94 */     if (luType != 35) {
/*  95 */       return null;
/*     */     }
/*  97 */     String ident = lu.getStringValue().toLowerCase();
/*  98 */     if (ident.equals("normal"))
/*  99 */       return y.a; 
/* 100 */     if (ident.equals("italic"))
/* 101 */       return y.b; 
/* 102 */     if (ident.equals("oblique")) {
/* 103 */       return y.c;
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static jp.cssj.homare.css.f.b.d b(LexicalUnit lu) {
/* 115 */     short luType = lu.getLexicalUnitType();
/* 116 */     if (luType != 35) {
/* 117 */       return null;
/*     */     }
/* 119 */     if (lu.getNextLexicalUnit() == null) {
/* 120 */       String ident = lu.getStringValue().toLowerCase();
/* 121 */       if (ident.equals("generic") || ident.equals("cid-keyed"))
/* 122 */         return jp.cssj.homare.css.f.b.d.a; 
/* 123 */       if (ident.equals("external") || ident.equals("cid-identity"))
/* 124 */         return jp.cssj.homare.css.f.b.d.b; 
/* 125 */       if (ident.equals("embed") || ident.equals("embedded"))
/* 126 */         return jp.cssj.homare.css.f.b.d.c; 
/* 127 */       if (ident.equals("outlines")) {
/* 128 */         return jp.cssj.homare.css.f.b.d.d;
/*     */       }
/* 130 */       return null;
/*     */     } 
/*     */     
/* 133 */     b codes = new b();
/* 134 */     codes.a((byte)0);
/*     */     do {
/* 136 */       String ident = lu.getStringValue().toLowerCase();
/* 137 */       if (ident.equals("generic") || ident.equals("cid-keyed")) {
/* 138 */         codes.a((byte)1);
/* 139 */       } else if (ident.equals("external") || ident.equals("cid-identity")) {
/* 140 */         codes.a((byte)2);
/* 141 */       } else if (ident.equals("embed") || ident.equals("embedded")) {
/* 142 */         codes.a((byte)3);
/* 143 */       } else if (ident.equals("-core")) {
/* 144 */         codes.b(0);
/* 145 */       } else if (ident.equals("core")) {
/* 146 */         codes.a((byte)0);
/* 147 */       } else if (ident.equals("outlines")) {
/* 148 */         codes.a((byte)4);
/*     */       } else {
/* 150 */         return null;
/*     */       } 
/* 152 */       lu = lu.getNextLexicalUnit();
/* 153 */       if (luType != 35) {
/* 154 */         return null;
/*     */       }
/* 156 */     } while (lu != null);
/*     */ 
/*     */ 
/*     */     
/* 160 */     return new jp.cssj.homare.css.f.b.d(codes.a());
/*     */   }
/*     */   
/*     */   public static jp.cssj.homare.css.f.b.d a(String str) {
/* 164 */     String[] types = str.split("[\\s]+");
/* 165 */     if (types.length == 1) {
/* 166 */       String ident = types[0].toLowerCase();
/* 167 */       if (ident.equals("generic") || ident.equals("cid-keyed"))
/* 168 */         return jp.cssj.homare.css.f.b.d.a; 
/* 169 */       if (ident.equals("external") || ident.equals("cid-identity"))
/* 170 */         return jp.cssj.homare.css.f.b.d.b; 
/* 171 */       if (ident.equals("embed") || ident.equals("embedded"))
/* 172 */         return jp.cssj.homare.css.f.b.d.c; 
/* 173 */       if (ident.equals("outlines")) {
/* 174 */         return jp.cssj.homare.css.f.b.d.d;
/*     */       }
/* 176 */       return null;
/*     */     } 
/*     */     
/* 179 */     b codes = new b();
/* 180 */     codes.a((byte)0);
/* 181 */     for (int i = 0; i < types.length; i++) {
/* 182 */       String ident = types[i].toLowerCase();
/* 183 */       if (ident.equals("generic") || ident.equals("cid-keyed")) {
/* 184 */         codes.a((byte)1);
/* 185 */       } else if (ident.equals("external") || ident.equals("cid-identity")) {
/* 186 */         codes.a((byte)2);
/* 187 */       } else if (ident.equals("embed") || ident.equals("embedded")) {
/* 188 */         codes.a((byte)3);
/* 189 */       } else if (ident.equals("-core")) {
/* 190 */         codes.b(0);
/* 191 */       } else if (ident.equals("core")) {
/* 192 */         codes.a((byte)0);
/* 193 */       } else if (ident.equals("outlines")) {
/* 194 */         codes.a((byte)4);
/*     */       } else {
/* 196 */         return null;
/*     */       } 
/*     */     } 
/* 199 */     jp.cssj.homare.css.f.b.d result = new jp.cssj.homare.css.f.b.d(codes.a());
/*     */     
/* 201 */     return result;
/*     */   }
/*     */   
/*     */   public static jp.cssj.homare.css.f.b.d b(String str) {
/* 205 */     String[] types = str.split("[\\s]+");
/* 206 */     if (types.length == 1) {
/* 207 */       String ident = types[0].toLowerCase();
/* 208 */       if (ident.equals("embed") || ident.equals("embedded"))
/* 209 */         return jp.cssj.homare.css.f.b.d.e; 
/* 210 */       if (ident.equals("outlines")) {
/* 211 */         return jp.cssj.homare.css.f.b.d.d;
/*     */       }
/* 213 */       return null;
/*     */     } 
/*     */     
/* 216 */     b codes = new b();
/* 217 */     codes.a((byte)0);
/* 218 */     for (int i = 0; i < types.length; i++) {
/* 219 */       String ident = types[i].toLowerCase();
/* 220 */       if (ident.equals("embed") || ident.equals("embedded")) {
/* 221 */         codes.a((byte)3);
/* 222 */       } else if (ident.equals("outlines")) {
/* 223 */         codes.a((byte)4);
/*     */       } else {
/* 225 */         return null;
/*     */       } 
/*     */     } 
/* 228 */     jp.cssj.homare.css.f.b.d result = new jp.cssj.homare.css.f.b.d(codes.a());
/*     */     
/* 230 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static z c(LexicalUnit lu) {
/* 240 */     short luType = lu.getLexicalUnitType();
/* 241 */     if (luType != 35) {
/* 242 */       return null;
/*     */     }
/* 244 */     String ident = lu.getStringValue().toLowerCase();
/* 245 */     if (ident.equals("normal"))
/* 246 */       return z.c; 
/* 247 */     if (ident.equals("small-caps")) {
/* 248 */       return z.d;
/*     */     }
/* 250 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static A d(LexicalUnit lu) {
/*     */     String ident;
/*     */     int a;
/* 260 */     short luType = lu.getLexicalUnitType();
/* 261 */     switch (luType) {
/*     */       case 35:
/* 263 */         ident = lu.getStringValue().toLowerCase();
/* 264 */         if (ident.equals("normal"))
/* 265 */           return A.n; 
/* 266 */         if (ident.equals("bold"))
/* 267 */           return A.o; 
/* 268 */         if (ident.equals("bolder"))
/* 269 */           return A.p; 
/* 270 */         if (ident.equals("lighter")) {
/* 271 */           return A.q;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 13:
/* 276 */         a = lu.getIntegerValue();
/*     */         try {
/* 278 */           return A.a(a);
/* 279 */         } catch (IllegalArgumentException e) {
/* 280 */           return null;
/*     */         } 
/*     */     } 
/* 283 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ad b(m ua, LexicalUnit lu) {
/*     */     String ident;
/*     */     M per;
/*     */     int i;
/*     */     double val;
/* 293 */     switch (lu.getLexicalUnitType()) {
/*     */       case 35:
/* 295 */         ident = lu.getStringValue().toLowerCase();
/* 296 */         if (ident.equals("larger"))
/* 297 */           return (ad)U.c; 
/* 298 */         if (ident.equals("smaller"))
/* 299 */           return (ad)U.d; 
/* 300 */         if (ident.equals("xx-small"))
/* 301 */           return (ad)a.a(ua, ua.d((byte)1)); 
/* 302 */         if (ident.equals("x-small"))
/* 303 */           return (ad)a.a(ua, ua.d((byte)2)); 
/* 304 */         if (ident.equals("small"))
/* 305 */           return (ad)a.a(ua, ua.d((byte)3)); 
/* 306 */         if (ident.equals("medium"))
/* 307 */           return (ad)a.a(ua, ua.d((byte)4)); 
/* 308 */         if (ident.equals("large"))
/* 309 */           return (ad)a.a(ua, ua.d((byte)5)); 
/* 310 */         if (ident.equals("x-large"))
/* 311 */           return (ad)a.a(ua, ua.d((byte)6)); 
/* 312 */         if (ident.equals("xx-large")) {
/* 313 */           return (ad)a.a(ua, ua.d((byte)7));
/*     */         }
/* 315 */         return null;
/*     */       
/*     */       case 23:
/* 318 */         per = l.d(lu);
/* 319 */         if (per != null && per.d()) {
/* 320 */           return null;
/*     */         }
/* 322 */         return (ad)per;
/*     */     } 
/*     */     
/* 325 */     short luType = lu.getLexicalUnitType();
/* 326 */     switch (luType) {
/*     */       case 15:
/* 328 */         return (ad)u.a(lu.getFloatValue());
/*     */       
/*     */       case 16:
/* 331 */         return (ad)w.a(lu.getFloatValue());
/*     */       
/*     */       case 43:
/* 334 */         return (ad)h.a(lu.getFloatValue());
/*     */       
/*     */       case 44:
/* 337 */         return (ad)jp.cssj.homare.css.f.a.d.a(lu.getFloatValue());
/*     */       
/*     */       case 17:
/*     */       case 18:
/*     */       case 19:
/*     */       case 20:
/*     */       case 21:
/*     */       case 22:
/* 345 */         return (ad)a.a(ua, lu.getFloatValue() * ua.k(), luType);
/*     */       
/*     */       case 13:
/* 348 */         i = lu.getIntegerValue();
/* 349 */         if (i == 0) {
/* 350 */           return (ad)a.a(ua, i * ua.k(), (short)17);
/*     */         }
/* 352 */         return null;
/*     */ 
/*     */       
/*     */       case 14:
/* 356 */         val = lu.getFloatValue();
/* 357 */         if (val == 0.0D) {
/* 358 */           return (ad)a.a(ua, val * ua.k(), (short)17);
/*     */         }
/* 360 */         return null;
/*     */     } 
/*     */ 
/*     */     
/* 364 */     return null;
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
/*     */   public static x c(String str) {
/* 376 */     List<b> list = new ArrayList<>();
/* 377 */     int state = 0;
/* 378 */     StringBuffer buff = new StringBuffer();
/* 379 */     for (int i = 0; i <= str.length(); i++) {
/*     */       
/* 381 */       if (i < str.length())
/* 382 */       { String ident; b family; char c = str.charAt(i);
/* 383 */         switch (state)
/*     */         { case 0:
/* 385 */             if (Character.isWhitespace(c)) {
/*     */               break;
/*     */             }
/* 388 */             if (c == '\'') {
/* 389 */               state = 1;
/*     */               break;
/*     */             } 
/* 392 */             if (c == '"') {
/* 393 */               state = 2;
/*     */               break;
/*     */             } 
/* 396 */             buff.append(c);
/* 397 */             state = 3;
/*     */             break;
/*     */           case 1:
/* 400 */             if (c == '\'') {
/* 401 */               ident = buff.toString().trim();
/* 402 */               buff = new StringBuffer();
/* 403 */               state = 0;
/*     */             } else {
/*     */               
/* 406 */               buff.append(c);
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
/*     */               break;
/*     */             } 
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
/* 432 */             if (ident.length() > 0)
/* 433 */             { family = b.a(ident);
/* 434 */               list.add(family); } case 2: if (family == 34) { ident = buff.toString().trim(); buff = new StringBuffer(); state = 0; } else { buff.append(family); break; }  if (ident.length() > 0) { family = b.a(ident); list.add(family); } case 3: if (Character.isWhitespace(family)) { ident = buff.toString().trim(); buff = new StringBuffer(); state = 0; } else { buff.append(family); break; }  if (ident.length() > 0) { family = b.a(ident); list.add(family); } default: throw new IllegalStateException(); }  } else { String ident = buff.toString().trim(); if (ident.length() > 0) { b b = b.a(ident); list.add(b); }
/*     */          }
/*     */     
/* 437 */     }  b[] families = list.<b>toArray(new b[list.size()]);
/* 438 */     return new x(families);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */