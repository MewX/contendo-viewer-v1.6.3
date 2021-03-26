/*     */ package jp.cssj.sakae.pdf.c.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.c.a.j;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.c.c.a;
/*     */ import jp.cssj.sakae.pdf.c.d;
/*     */ import jp.cssj.sakae.pdf.h;
/*     */ import jp.cssj.sakae.pdf.k;
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
/*     */ public final class e
/*     */ {
/*     */   public static final String a = "Identity-H";
/*     */   public static final String b = "Identity-V";
/*     */   public static final String c = "Adobe";
/*     */   public static final String d = "Identity";
/*     */   public static final int e = 0;
/*     */   public static final int f = 1;
/*     */   public static final int g = 2;
/*     */   public static final int h = 4;
/*     */   public static final int i = 8;
/*     */   public static final int j = 64;
/*     */   public static final int k = 65536;
/*     */   public static final int l = 131072;
/*     */   public static final int m = 262144;
/*     */   public static final int n = 880;
/*     */   public static final int o = 500;
/*     */   
/*     */   public static void a(h out, b source) throws IOException {
/*  52 */     int flags = 4;
/*  53 */     j panose = source.p();
/*  54 */     if (panose != null) {
/*  55 */       if (panose.f >= 8) {
/*  56 */         flags |= 0x1;
/*     */       }
/*  58 */       if (panose.d <= 3) {
/*  59 */         flags |= 0x2;
/*     */       }
/*  61 */       if (panose.c == 3) {
/*  62 */         flags |= 0x8;
/*     */       }
/*  64 */       if (panose.j >= 9) {
/*  65 */         flags |= 0x40;
/*     */       }
/*  67 */       if (panose.c == 4 && panose.l == 4) {
/*  68 */         flags |= 0x10000;
/*     */       }
/*  70 */       if (panose.c == 4 && panose.l == 5) {
/*  71 */         flags |= 0x20000;
/*     */       }
/*  73 */       if (panose.e >= 8) {
/*  74 */         flags |= 0x40000;
/*     */       }
/*     */     } else {
/*  77 */       if (source.b()) {
/*  78 */         flags |= 0x40;
/*     */       }
/*  80 */       if (source.c() >= 500) {
/*  81 */         flags |= 0x40000;
/*     */       }
/*     */     } 
/*     */     
/*  85 */     out.a("Flags");
/*  86 */     out.a(flags);
/*  87 */     out.k();
/*     */     
/*  89 */     if (panose != null) {
/*  90 */       byte[] bytes = new byte[12];
/*  91 */       bytes[0] = panose.a;
/*  92 */       bytes[1] = panose.b;
/*  93 */       bytes[2] = panose.c;
/*  94 */       bytes[3] = panose.d;
/*  95 */       bytes[4] = panose.e;
/*  96 */       bytes[5] = panose.f;
/*  97 */       bytes[6] = panose.g;
/*  98 */       bytes[7] = panose.h;
/*  99 */       bytes[8] = panose.i;
/* 100 */       bytes[9] = panose.j;
/* 101 */       bytes[10] = panose.k;
/* 102 */       bytes[11] = panose.l;
/* 103 */       out.a("Style");
/* 104 */       out.g();
/* 105 */       out.a("Panose");
/* 106 */       out.a(bytes, 0, bytes.length);
/* 107 */       out.h();
/* 108 */       out.k();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(h out, l warray) throws IOException {
/* 116 */     out.a("DW");
/* 117 */     out.a(warray.a());
/* 118 */     out.k();
/* 119 */     m[] widths = warray.b();
/* 120 */     if (widths.length > 0) {
/* 121 */       out.a("W");
/* 122 */       out.i();
/* 123 */       out.k();
/* 124 */       for (int i = 0; i < widths.length; i++) {
/* 125 */         m w = widths[i];
/* 126 */         short[] shorts = w.c();
/* 127 */         if (shorts.length == 1) {
/* 128 */           out.a(w.a());
/* 129 */           out.a(w.b());
/* 130 */           out.a(shorts[0]);
/*     */         }
/* 132 */         else if (shorts.length <= w.b() - w.a()) {
/* 133 */           out.a(w.a());
/* 134 */           out.i();
/* 135 */           for (int j = 0; j < shorts.length - 1; j++) {
/* 136 */             out.a(shorts[j]);
/*     */           }
/* 138 */           out.j();
/* 139 */           out.a(w.a() + shorts.length - 1);
/* 140 */           out.a(w.b());
/* 141 */           out.a(shorts[shorts.length - 1]);
/*     */         } else {
/* 143 */           out.a(w.a());
/* 144 */           out.i();
/* 145 */           for (int j = 0; j < shorts.length; j++) {
/* 146 */             out.a(shorts[j]);
/*     */           }
/* 148 */           out.j();
/*     */         } 
/*     */         
/* 151 */         out.k();
/*     */       } 
/* 153 */       out.j();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(h out, l warray) throws IOException {
/* 161 */     out.a("DW2");
/* 162 */     out.i();
/* 163 */     out.a(880);
/* 164 */     out.a(-warray.a());
/* 165 */     out.j();
/* 166 */     out.k();
/* 167 */     m[] widths = warray.b();
/* 168 */     if (widths.length > 0) {
/* 169 */       out.a("W2");
/* 170 */       out.i();
/* 171 */       out.k();
/* 172 */       for (int i = 0; i < widths.length; i++) {
/* 173 */         m w = widths[i];
/* 174 */         short[] shorts = w.c();
/* 175 */         if (shorts.length == 1) {
/* 176 */           out.a(w.a());
/* 177 */           out.a(w.b());
/* 178 */           out.a(-shorts[0]);
/* 179 */           out.a(500);
/* 180 */           out.a(880);
/*     */         }
/* 182 */         else if (shorts.length <= w.b() - w.a()) {
/* 183 */           out.a(w.a());
/* 184 */           out.i();
/* 185 */           for (int j = 0; j < shorts.length - 1; j++) {
/* 186 */             out.a(-shorts[j]);
/* 187 */             out.a(500);
/* 188 */             out.a(880);
/*     */           } 
/* 190 */           out.j();
/* 191 */           out.a(w.a() + shorts.length - 1);
/* 192 */           out.a(w.b());
/* 193 */           out.a(-shorts[shorts.length - 1]);
/* 194 */           out.a(500);
/* 195 */           out.a(880);
/*     */         } else {
/* 197 */           out.a(w.a());
/* 198 */           out.i();
/* 199 */           for (int j = 0; j < shorts.length; j++) {
/* 200 */             out.a(-shorts[j]);
/* 201 */             out.a(500);
/* 202 */             out.a(880);
/*     */           } 
/* 204 */           out.j();
/*     */         } 
/*     */         
/* 207 */         out.k();
/*     */       } 
/* 209 */       out.j();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(c out, k xref, b source, b fontRef, short[] w, short[] w2, int[] unicodeArray) throws IOException {
/* 216 */     String fontName = source.d();
/* 217 */     out.a(fontRef);
/* 218 */     out.g();
/* 219 */     out.a("Type");
/* 220 */     out.a("Font");
/* 221 */     out.k();
/* 222 */     out.a("Subtype");
/* 223 */     out.a("Type0");
/* 224 */     out.k();
/* 225 */     out.a("BaseFont");
/* 226 */     out.a(fontName);
/* 227 */     out.k();
/* 228 */     out.a("DescendantFonts");
/* 229 */     out.i();
/* 230 */     b xfontRef = xref.a();
/* 231 */     out.b(xfontRef);
/* 232 */     out.j();
/* 233 */     out.k();
/* 234 */     out.a("Encoding");
/* 235 */     out.a((w2 != null) ? "Identity-V" : "Identity-H");
/*     */ 
/*     */     
/* 238 */     b toUnicodeRef = xref.a();
/* 239 */     out.k();
/* 240 */     out.a("ToUnicode");
/* 241 */     out.b(toUnicodeRef);
/* 242 */     out.h();
/* 243 */     out.a();
/*     */     
/* 245 */     out.a(toUnicodeRef);
/* 246 */     h pout = new h(out.a((short)2), "ISO-8859-1");
/* 247 */     a(pout, unicodeArray);
/* 248 */     out.a();
/*     */ 
/*     */     
/* 251 */     out.a(xfontRef);
/* 252 */     out.g();
/* 253 */     out.a("Type");
/* 254 */     out.a("Font");
/* 255 */     out.k();
/* 256 */     out.a("Subtype");
/* 257 */     out.a("CIDFontType2");
/* 258 */     out.k();
/* 259 */     out.a("BaseFont");
/* 260 */     out.a(fontName);
/* 261 */     out.k();
/* 262 */     out.a("FontDescriptor");
/* 263 */     b fontDescRef = xref.a();
/* 264 */     out.b(fontDescRef);
/* 265 */     out.k();
/* 266 */     out.a("CIDSystemInfo");
/* 267 */     out.g();
/* 268 */     out.a("Registry");
/* 269 */     out.c("Adobe");
/* 270 */     out.a("Ordering");
/* 271 */     out.c("Identity");
/* 272 */     out.a("Supplement");
/* 273 */     out.a(0);
/* 274 */     out.k();
/* 275 */     out.a("CIDToGIDMap");
/* 276 */     out.a("Identity");
/* 277 */     out.k();
/* 278 */     out.h();
/*     */ 
/*     */ 
/*     */     
/* 282 */     l warray = l.a(w);
/* 283 */     a((h)out, warray);
/*     */     
/* 285 */     if (w2 != null && w2.length > 0) {
/* 286 */       warray = l.a(w2);
/* 287 */       b((h)out, warray);
/*     */     } 
/*     */     
/* 290 */     out.h();
/* 291 */     out.a();
/*     */ 
/*     */     
/* 294 */     out.a(fontDescRef);
/* 295 */     out.g();
/* 296 */     out.a("Type");
/* 297 */     out.a("FontDescriptor");
/* 298 */     out.k();
/* 299 */     out.a("FontName");
/* 300 */     out.a(fontName);
/* 301 */     out.k();
/* 302 */     a((h)out, source);
/* 303 */     out.a("FontBBox");
/* 304 */     b bbox = source.f();
/* 305 */     out.i();
/* 306 */     out.a(bbox.a);
/* 307 */     out.a(bbox.b);
/* 308 */     out.a(bbox.c);
/* 309 */     out.a(bbox.d);
/* 310 */     out.j();
/* 311 */     out.k();
/* 312 */     out.a("StemV");
/* 313 */     out.a(source.k());
/* 314 */     out.k();
/* 315 */     out.a("ItalicAngle");
/* 316 */     out.a(0);
/* 317 */     out.k();
/* 318 */     out.a("CapHeight");
/* 319 */     out.a(source.h());
/* 320 */     out.k();
/* 321 */     out.a("XHeight");
/* 322 */     out.a(source.l());
/* 323 */     out.k();
/* 324 */     out.a("Ascent");
/* 325 */     out.a(source.g());
/* 326 */     out.k();
/* 327 */     out.a("Descent");
/* 328 */     out.a(-source.i());
/* 329 */     out.k();
/* 330 */     out.h();
/* 331 */     out.a();
/*     */   }
/*     */   
/*     */   private static void a(h pout, int[] unicodeArray) throws IOException {
/* 335 */     k toUnicode = k.a(unicodeArray);
/*     */     
/* 337 */     pout.a("CIDInit");
/* 338 */     pout.a("ProcSet");
/* 339 */     pout.b("findresource");
/* 340 */     pout.b("begin");
/* 341 */     pout.k();
/*     */     
/* 343 */     pout.a(12);
/* 344 */     pout.b("dict");
/* 345 */     pout.b("begin");
/* 346 */     pout.k();
/*     */     
/* 348 */     pout.b("begincmap");
/* 349 */     pout.k();
/*     */     
/* 351 */     pout.a("CIDSystemInfo");
/* 352 */     pout.k();
/*     */     
/* 354 */     pout.g();
/*     */     
/* 356 */     pout.a("Registry");
/* 357 */     pout.c("Adobe");
/* 358 */     pout.k();
/*     */     
/* 360 */     pout.a("Ordering");
/* 361 */     pout.c("UCS");
/* 362 */     pout.k();
/*     */     
/* 364 */     pout.a("Supplement");
/* 365 */     pout.a(0);
/* 366 */     pout.k();
/*     */     
/* 368 */     pout.h();
/* 369 */     pout.b("def");
/* 370 */     pout.k();
/*     */     
/* 372 */     pout.a("CMapName");
/* 373 */     pout.a("Adobe-Identity-UCS");
/* 374 */     pout.b("def");
/* 375 */     pout.k();
/*     */     
/* 377 */     pout.a("CMapType");
/* 378 */     pout.a(2);
/* 379 */     pout.b("def");
/* 380 */     pout.k();
/*     */     
/* 382 */     pout.a(1);
/* 383 */     pout.b("begincodespacerange");
/* 384 */     pout.k();
/*     */     
/* 386 */     pout.b(0);
/* 387 */     pout.b(65535);
/* 388 */     pout.k();
/*     */     
/* 390 */     pout.b("endcodespacerange");
/* 391 */     pout.k();
/*     */     
/* 393 */     k.a[] unicodes = toUnicode.a();
/* 394 */     pout.a(unicodes.length);
/* 395 */     pout.b("beginbfrange");
/* 396 */     pout.k();
/*     */     
/* 398 */     for (int i = 0; i < unicodes.length; i++) {
/* 399 */       k.a u = unicodes[i];
/* 400 */       int[] chars = u.c();
/* 401 */       if (chars.length == 1) {
/* 402 */         pout.b(u.a());
/* 403 */         pout.b(u.b());
/* 404 */         pout.b(chars[0]);
/*     */       } else {
/* 406 */         pout.b(u.a());
/* 407 */         pout.b(u.b());
/* 408 */         pout.i();
/* 409 */         for (int j = 0; j < chars.length; j++) {
/* 410 */           pout.b(chars[j]);
/*     */         }
/* 412 */         pout.j();
/*     */       } 
/* 414 */       pout.k();
/*     */     } 
/*     */     
/* 417 */     pout.b("endbfrange");
/* 418 */     pout.k();
/*     */     
/* 420 */     pout.b("endcmap");
/* 421 */     pout.k();
/*     */     
/* 423 */     pout.b("CMapName");
/* 424 */     pout.b("currentdict");
/* 425 */     pout.a("CMap");
/* 426 */     pout.b("defineresource");
/* 427 */     pout.b("pop");
/* 428 */     pout.k();
/*     */     
/* 430 */     pout.b("end");
/* 431 */     pout.b("end");
/* 432 */     pout.k();
/*     */     
/* 434 */     pout.close();
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
/*     */   public static void a(c out, k xref, b source, d font, b fontRef, short[] w, short[] w2, int[] unicodeArray) throws IOException {
/* 456 */     int on = fontRef.a;
/* 457 */     char a = (char)(65 + (on & 0xF));
/* 458 */     char c1 = (char)(65 + (on >> 4 & 0xF));
/* 459 */     char c2 = (char)(65 + (on >> 8 & 0xF));
/* 460 */     char c3 = (char)(65 + (on >> 12 & 0xF));
/* 461 */     char c4 = (char)(65 + (on >> 16 & 0xF));
/* 462 */     char f = (char)(65 + (on >> 20 & 0xF));
/* 463 */     String subsetName = "" + a + c1 + c2 + c3 + c4 + f + '+' + font.b();
/*     */ 
/*     */ 
/*     */     
/* 467 */     out.a(fontRef);
/* 468 */     out.g();
/* 469 */     out.a("Type");
/* 470 */     out.a("Font");
/* 471 */     out.k();
/* 472 */     out.a("Subtype");
/* 473 */     out.a("Type0");
/* 474 */     out.k();
/* 475 */     out.a("BaseFont");
/* 476 */     out.a(subsetName);
/* 477 */     out.k();
/* 478 */     out.a("DescendantFonts");
/* 479 */     out.i();
/* 480 */     b xfontRef = xref.a();
/* 481 */     out.b(xfontRef);
/* 482 */     out.j();
/* 483 */     out.k();
/* 484 */     out.a("Encoding");
/* 485 */     out.a((w2 != null) ? "Identity-V" : "Identity-H");
/*     */ 
/*     */     
/* 488 */     b toUnicodeRef = xref.a();
/* 489 */     out.k();
/* 490 */     out.a("ToUnicode");
/* 491 */     out.b(toUnicodeRef);
/* 492 */     out.h();
/* 493 */     out.a();
/*     */     
/* 495 */     out.a(toUnicodeRef);
/* 496 */     h pout = new h(out.a((short)2), "ISO-8859-1");
/* 497 */     a(pout, unicodeArray);
/* 498 */     out.a();
/*     */ 
/*     */     
/* 501 */     out.a(xfontRef);
/* 502 */     out.g();
/* 503 */     out.a("Type");
/* 504 */     out.a("Font");
/* 505 */     out.k();
/* 506 */     out.a("Subtype");
/* 507 */     out.a("CIDFontType0");
/* 508 */     out.k();
/* 509 */     out.a("BaseFont");
/* 510 */     out.a(subsetName);
/* 511 */     out.k();
/* 512 */     out.a("FontDescriptor");
/* 513 */     b fontDescRef = xref.a();
/* 514 */     out.b(fontDescRef);
/* 515 */     out.k();
/* 516 */     out.a("CIDSystemInfo");
/* 517 */     out.g();
/* 518 */     out.a("Registry");
/* 519 */     out.c("Adobe");
/* 520 */     out.a("Ordering");
/* 521 */     out.c("Identity");
/* 522 */     out.a("Supplement");
/* 523 */     out.a(0);
/* 524 */     out.h();
/*     */ 
/*     */ 
/*     */     
/* 528 */     l warray = l.a(w);
/* 529 */     a((h)out, warray);
/*     */     
/* 531 */     if (w2 != null && w2.length > 0) {
/* 532 */       warray = l.a(w2);
/* 533 */       b((h)out, warray);
/*     */     } 
/*     */     
/* 536 */     out.h();
/* 537 */     out.a();
/*     */ 
/*     */     
/* 540 */     out.a(fontDescRef);
/* 541 */     out.g();
/* 542 */     out.a("Type");
/* 543 */     out.a("FontDescriptor");
/* 544 */     out.k();
/* 545 */     out.a("FontName");
/* 546 */     out.a(subsetName);
/* 547 */     out.k();
/* 548 */     a((h)out, source);
/* 549 */     out.a("FontBBox");
/* 550 */     b bbox = source.f();
/* 551 */     out.i();
/* 552 */     out.a(bbox.a);
/* 553 */     out.a(bbox.b);
/* 554 */     out.a(bbox.c);
/* 555 */     out.a(bbox.d);
/* 556 */     out.j();
/* 557 */     out.k();
/* 558 */     out.a("StemV");
/* 559 */     out.a(source.k());
/* 560 */     out.k();
/* 561 */     out.a("ItalicAngle");
/* 562 */     out.a(0);
/* 563 */     out.k();
/* 564 */     out.a("CapHeight");
/* 565 */     out.a(source.h());
/* 566 */     out.k();
/* 567 */     out.a("XHeight");
/* 568 */     out.a(source.l());
/* 569 */     out.k();
/* 570 */     out.a("Ascent");
/* 571 */     out.a(source.g());
/* 572 */     out.k();
/* 573 */     out.a("Descent");
/* 574 */     out.a(-source.i());
/* 575 */     out.k();
/* 576 */     b cidSetRef = xref.a();
/* 577 */     out.a("CIDSet");
/* 578 */     out.b(cidSetRef);
/* 579 */     out.k();
/* 580 */     b fontFile3Ref = xref.a();
/* 581 */     out.a("FontFile3");
/* 582 */     out.b(fontFile3Ref);
/* 583 */     out.h();
/* 584 */     out.a();
/*     */ 
/*     */ 
/*     */     
/* 588 */     out.a(cidSetRef);
/* 589 */     out.g();
/* 590 */     try (OutputStream sout = out.b((short)1)) {
/* 591 */       int bytes = (int)Math.ceil(unicodeArray.length / 8.0D);
/* 592 */       for (int i = 0; i < bytes; i++) {
/* 593 */         int start = i * 8;
/* 594 */         int end = start + 8;
/* 595 */         int m = 0;
/* 596 */         for (int j = start; j < end; j++) {
/* 597 */           if (j < unicodeArray.length) {
/* 598 */             m |= 1 << end - j - 1;
/*     */           }
/*     */         } 
/* 601 */         sout.write(m);
/*     */       } 
/*     */     } 
/* 604 */     out.a();
/*     */ 
/*     */     
/* 607 */     out.a(fontFile3Ref);
/* 608 */     out.g();
/* 609 */     out.a("Subtype");
/* 610 */     out.a("CIDFontType0C");
/* 611 */     out.k();
/*     */     
/* 613 */     try (OutputStream cout = out.b((short)1)) {
/* 614 */       a cff = new a();
/* 615 */       cff.a(subsetName);
/* 616 */       cff.a(font);
/* 617 */       cff.a(cout);
/*     */     } 
/*     */     
/* 620 */     out.a();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */