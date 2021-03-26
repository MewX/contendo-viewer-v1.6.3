/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Arrays;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.stream.JsonLocation;
/*     */ import javax.json.stream.JsonParser;
/*     */ import javax.json.stream.JsonParsingException;
/*     */ import org.a.a.a.a;
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
/*     */ final class v
/*     */   implements Closeable
/*     */ {
/*     */   static {
/*  62 */     b = new int[128];
/*     */     
/*  64 */     Arrays.fill(b, -1); int i;
/*  65 */     for (i = 48; i <= 57; i++) {
/*  66 */       b[i] = i - 48;
/*     */     }
/*  68 */     for (i = 65; i <= 70; i++) {
/*  69 */       b[i] = 10 + i - 65;
/*     */     }
/*  71 */     for (i = 97; i <= 102; i++) {
/*  72 */       b[i] = 10 + i - 97;
/*     */     }
/*     */     
/*  75 */     c = b.length;
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
/*  97 */   private long k = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private long l = 0L; private static final int[] b; private static final int c; private final a d; private final Reader e;
/*     */   private char[] f;
/*     */   private int g;
/* 109 */   private long m = 0L; private int h;
/*     */   private int i;
/*     */   private int j;
/*     */   private boolean n;
/*     */   private boolean o;
/*     */   private BigDecimal p;
/*     */   
/* 116 */   enum a { a((String)JsonParser.Event.START_OBJECT, false),
/* 117 */     b((String)JsonParser.Event.START_ARRAY, false),
/* 118 */     c(null, false),
/* 119 */     d(null, false),
/* 120 */     e((String)JsonParser.Event.VALUE_STRING, true),
/* 121 */     f((String)JsonParser.Event.VALUE_NUMBER, true),
/* 122 */     g((String)JsonParser.Event.VALUE_TRUE, true),
/* 123 */     h((String)JsonParser.Event.VALUE_FALSE, true),
/* 124 */     i((String)JsonParser.Event.VALUE_NULL, true),
/* 125 */     j((String)JsonParser.Event.END_OBJECT, false),
/* 126 */     k((String)JsonParser.Event.END_ARRAY, false),
/* 127 */     l(null, false); public static a[] a() {
/*     */       return (a[])o.clone();
/*     */     } public static a a(String name) {
/*     */       return Enum.<a>valueOf(a.class, name);
/*     */     }
/*     */     a(JsonParser.Event event, boolean value) {
/* 133 */       this.m = event;
/* 134 */       this.n = value;
/*     */     }
/*     */     private final JsonParser.Event m; private final boolean n;
/*     */     JsonParser.Event b() {
/* 138 */       return this.m;
/*     */     }
/*     */     
/*     */     boolean c() {
/* 142 */       return this.n;
/*     */     } }
/*     */ 
/*     */   
/*     */   v(Reader reader, a bufferPool) {
/* 147 */     this.e = reader;
/* 148 */     this.d = bufferPool;
/* 149 */     this.f = bufferPool.a();
/*     */   }
/*     */   
/*     */   private void l() {
/*     */     int ch;
/* 154 */     boolean inPlace = true;
/* 155 */     this.i = this.j = this.g;
/*     */ 
/*     */     
/*     */     while (true) {
/* 159 */       if (inPlace) {
/*     */         int i;
/* 161 */         while (this.g < this.h && (i = this.f[this.g]) >= 32 && i != 92) {
/* 162 */           if (i == 34) {
/* 163 */             this.j = this.g++;
/*     */             return;
/*     */           } 
/* 166 */           this.g++;
/*     */         } 
/* 168 */         this.j = this.g;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 173 */       ch = s();
/* 174 */       if (ch >= 32 && ch != 34 && ch != 92) {
/* 175 */         if (!inPlace) {
/* 176 */           this.f[this.j] = (char)ch;
/*     */         }
/* 178 */         this.j++;
/*     */         continue;
/*     */       } 
/* 181 */       switch (ch) {
/*     */         case 92:
/* 183 */           inPlace = false;
/* 184 */           m(); continue;
/*     */         case 34:
/*     */           return;
/*     */       }  break;
/*     */     } 
/* 189 */     throw b(ch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void m() {
/* 195 */     int unicode, i, ch = s();
/* 196 */     switch (ch) {
/*     */       case 98:
/* 198 */         this.f[this.j++] = '\b';
/*     */         return;
/*     */       case 116:
/* 201 */         this.f[this.j++] = '\t';
/*     */         return;
/*     */       case 110:
/* 204 */         this.f[this.j++] = '\n';
/*     */         return;
/*     */       case 102:
/* 207 */         this.f[this.j++] = '\f';
/*     */         return;
/*     */       case 114:
/* 210 */         this.f[this.j++] = '\r';
/*     */         return;
/*     */       case 34:
/*     */       case 47:
/*     */       case 92:
/* 215 */         this.f[this.j++] = (char)ch;
/*     */         return;
/*     */       case 117:
/* 218 */         unicode = 0;
/* 219 */         for (i = 0; i < 4; i++) {
/* 220 */           int ch3 = s();
/* 221 */           int digit = (ch3 >= 0 && ch3 < c) ? b[ch3] : -1;
/* 222 */           if (digit < 0) {
/* 223 */             throw b(ch3);
/*     */           }
/* 225 */           unicode = unicode << 4 | digit;
/*     */         } 
/* 227 */         this.f[this.j++] = (char)unicode;
/*     */         return;
/*     */     } 
/*     */     
/* 231 */     throw b(ch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int n() {
/* 239 */     if (this.g < this.h) {
/* 240 */       return this.f[this.g++];
/*     */     }
/* 242 */     this.j = this.g;
/* 243 */     return s();
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(int ch) {
/* 248 */     this.i = this.j = this.g - 1;
/*     */     
/* 250 */     if (ch == 45) {
/* 251 */       this.n = true;
/* 252 */       ch = n();
/* 253 */       if (ch < 48 || ch > 57) {
/* 254 */         throw b(ch);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 259 */     if (ch == 48) {
/* 260 */       ch = n();
/*     */     } else {
/*     */       do {
/* 263 */         ch = n();
/* 264 */       } while (ch >= 48 && ch <= 57);
/*     */     } 
/*     */ 
/*     */     
/* 268 */     if (ch == 46) {
/* 269 */       this.o = true;
/* 270 */       int count = 0;
/*     */       do {
/* 272 */         ch = n();
/* 273 */         count++;
/* 274 */       } while (ch >= 48 && ch <= 57);
/* 275 */       if (count == 1) {
/* 276 */         throw b(ch);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 281 */     if (ch == 101 || ch == 69) {
/* 282 */       this.o = true;
/* 283 */       ch = n();
/* 284 */       if (ch == 43 || ch == 45) {
/* 285 */         ch = n();
/*     */       }
/*     */       int count;
/* 288 */       for (count = 0; ch >= 48 && ch <= 57; count++) {
/* 289 */         ch = n();
/*     */       }
/* 291 */       if (count == 0) {
/* 292 */         throw b(ch);
/*     */       }
/*     */     } 
/* 295 */     if (ch != -1)
/*     */     {
/*     */       
/* 298 */       this.j = --this.g;
/*     */     }
/*     */   }
/*     */   
/*     */   private void o() {
/* 303 */     int ch1 = s();
/* 304 */     if (ch1 != 114) {
/* 305 */       throw a(ch1, 'r');
/*     */     }
/* 307 */     int ch2 = s();
/* 308 */     if (ch2 != 117) {
/* 309 */       throw a(ch2, 'u');
/*     */     }
/* 311 */     int ch3 = s();
/* 312 */     if (ch3 != 101) {
/* 313 */       throw a(ch3, 'e');
/*     */     }
/*     */   }
/*     */   
/*     */   private void p() {
/* 318 */     int ch1 = s();
/* 319 */     if (ch1 != 97) {
/* 320 */       throw a(ch1, 'a');
/*     */     }
/* 322 */     int ch2 = s();
/* 323 */     if (ch2 != 108) {
/* 324 */       throw a(ch2, 'l');
/*     */     }
/* 326 */     int ch3 = s();
/* 327 */     if (ch3 != 115) {
/* 328 */       throw a(ch3, 's');
/*     */     }
/* 330 */     int ch4 = s();
/* 331 */     if (ch4 != 101) {
/* 332 */       throw a(ch4, 'e');
/*     */     }
/*     */   }
/*     */   
/*     */   private void q() {
/* 337 */     int ch1 = s();
/* 338 */     if (ch1 != 117) {
/* 339 */       throw a(ch1, 'u');
/*     */     }
/* 341 */     int ch2 = s();
/* 342 */     if (ch2 != 108) {
/* 343 */       throw a(ch2, 'l');
/*     */     }
/* 345 */     int ch3 = s();
/* 346 */     if (ch3 != 108) {
/* 347 */       throw a(ch3, 'l');
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   a a() {
/* 356 */     u();
/* 357 */     int ch = s();
/*     */ 
/*     */     
/* 360 */     while (ch == 32 || ch == 9 || ch == 10 || ch == 13) {
/* 361 */       if (ch == 13) {
/* 362 */         this.k++;
/* 363 */         ch = s();
/* 364 */         if (ch == 10) {
/* 365 */           this.l = this.m + this.g;
/*     */         } else {
/* 367 */           this.l = this.m + this.g - 1L;
/*     */           continue;
/*     */         } 
/* 370 */       } else if (ch == 10) {
/* 371 */         this.k++;
/* 372 */         this.l = this.m + this.g;
/*     */       } 
/* 374 */       ch = s();
/*     */     } 
/*     */     
/* 377 */     switch (ch) {
/*     */       case 34:
/* 379 */         l();
/* 380 */         return a.e;
/*     */       case 123:
/* 382 */         return a.a;
/*     */       case 91:
/* 384 */         return a.b;
/*     */       case 58:
/* 386 */         return a.c;
/*     */       case 44:
/* 388 */         return a.d;
/*     */       case 116:
/* 390 */         o();
/* 391 */         return a.g;
/*     */       case 102:
/* 393 */         p();
/* 394 */         return a.h;
/*     */       case 110:
/* 396 */         q();
/* 397 */         return a.i;
/*     */       case 93:
/* 399 */         return a.k;
/*     */       case 125:
/* 401 */         return a.j;
/*     */       case 45:
/*     */       case 48:
/*     */       case 49:
/*     */       case 50:
/*     */       case 51:
/*     */       case 52:
/*     */       case 53:
/*     */       case 54:
/*     */       case 55:
/*     */       case 56:
/*     */       case 57:
/* 413 */         a(ch);
/* 414 */         return a.f;
/*     */       case -1:
/* 416 */         return a.l;
/*     */     } 
/* 418 */     throw b(ch);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean b() {
/* 423 */     u();
/* 424 */     int ch = r();
/*     */ 
/*     */     
/* 427 */     while (ch == 32 || ch == 9 || ch == 10 || ch == 13) {
/* 428 */       if (ch == 13) {
/* 429 */         this.k++;
/* 430 */         this.g++;
/* 431 */         ch = r();
/* 432 */         if (ch == 10) {
/* 433 */           this.l = this.m + this.g + 1L;
/*     */         } else {
/* 435 */           this.l = this.m + this.g;
/*     */           continue;
/*     */         } 
/* 438 */       } else if (ch == 10) {
/* 439 */         this.k++;
/* 440 */         this.l = this.m + this.g + 1L;
/*     */       } 
/* 442 */       this.g++;
/* 443 */       ch = r();
/*     */     } 
/* 445 */     return (ch != -1);
/*     */   }
/*     */   
/*     */   private int r() {
/*     */     try {
/* 450 */       if (this.g == this.h) {
/* 451 */         int len = t();
/* 452 */         if (len == -1) {
/* 453 */           return -1;
/*     */         }
/* 455 */         if (!a && len == 0) throw new AssertionError(); 
/* 456 */         this.g = this.j;
/* 457 */         this.h = this.g + len;
/*     */       } 
/* 459 */       return this.f[this.g];
/* 460 */     } catch (IOException ioe) {
/* 461 */       throw new JsonException(h.b(), ioe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JsonLocation c() {
/* 469 */     return new f(this.k, this.m + this.g - this.l, this.m + this.g - 1L);
/*     */   }
/*     */ 
/*     */   
/*     */   JsonLocation d() {
/* 474 */     return new f(this.k, this.m + this.g - this.l + 1L, this.m + this.g);
/*     */   }
/*     */   
/*     */   private int s() {
/*     */     try {
/* 479 */       if (this.g == this.h) {
/* 480 */         int len = t();
/* 481 */         if (len == -1) {
/* 482 */           return -1;
/*     */         }
/* 484 */         if (!a && len == 0) throw new AssertionError(); 
/* 485 */         this.g = this.j;
/* 486 */         this.h = this.g + len;
/*     */       } 
/* 488 */       return this.f[this.g++];
/* 489 */     } catch (IOException ioe) {
/* 490 */       throw new JsonException(h.b(), ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int t() throws IOException {
/* 495 */     if (this.j != 0) {
/* 496 */       int storeLen = this.j - this.i;
/* 497 */       if (storeLen > 0) {
/*     */         
/* 499 */         if (storeLen == this.f.length) {
/*     */           
/* 501 */           char[] doubleBuf = Arrays.copyOf(this.f, 2 * this.f.length);
/* 502 */           this.d.a(this.f);
/* 503 */           this.f = doubleBuf;
/*     */         } else {
/*     */           
/* 506 */           System.arraycopy(this.f, this.i, this.f, 0, storeLen);
/* 507 */           this.j = storeLen;
/* 508 */           this.i = 0;
/* 509 */           this.m += (this.g - this.j);
/*     */         } 
/*     */       } else {
/* 512 */         this.i = this.j = 0;
/* 513 */         this.m += this.g;
/*     */       } 
/*     */     } else {
/* 516 */       this.m += this.g;
/*     */     } 
/*     */     
/* 519 */     return this.e.read(this.f, this.j, this.f.length - this.j);
/*     */   }
/*     */ 
/*     */   
/*     */   private void u() {
/* 524 */     if (this.j != 0) {
/* 525 */       this.i = 0;
/* 526 */       this.j = 0;
/* 527 */       this.p = null;
/* 528 */       this.n = false;
/* 529 */       this.o = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   String e() {
/* 534 */     return new String(this.f, this.i, this.j - this.i);
/*     */   }
/*     */   
/*     */   BigDecimal f() {
/* 538 */     if (this.p == null) {
/* 539 */       this.p = new BigDecimal(this.f, this.i, this.j - this.i);
/*     */     }
/* 541 */     return this.p;
/*     */   }
/*     */ 
/*     */   
/*     */   int g() {
/* 546 */     int storeLen = this.j - this.i;
/* 547 */     if (!this.o && (storeLen <= 9 || (this.n && storeLen <= 10))) {
/* 548 */       int num = 0;
/* 549 */       int i = this.n ? 1 : 0;
/* 550 */       for (; i < storeLen; i++) {
/* 551 */         num = num * 10 + this.f[this.i + i] - 48;
/*     */       }
/* 553 */       return this.n ? -num : num;
/*     */     } 
/* 555 */     return f().intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   long h() {
/* 561 */     int storeLen = this.j - this.i;
/* 562 */     if (!this.o && (storeLen <= 18 || (this.n && storeLen <= 19))) {
/* 563 */       long num = 0L;
/* 564 */       int i = this.n ? 1 : 0;
/* 565 */       for (; i < storeLen; i++) {
/* 566 */         num = num * 10L + (this.f[this.i + i] - 48);
/*     */       }
/* 568 */       return this.n ? -num : num;
/*     */     } 
/* 570 */     return f().longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean i() {
/* 577 */     int storeLen = this.j - this.i;
/* 578 */     return (!this.o && (storeLen <= 9 || (this.n && storeLen <= 10)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean j() {
/* 584 */     int storeLen = this.j - this.i;
/* 585 */     return (!this.o && (storeLen <= 18 || (this.n && storeLen <= 19)));
/*     */   }
/*     */   
/*     */   boolean k() {
/* 589 */     return (!this.o || f().scale() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 594 */     this.e.close();
/* 595 */     this.d.a(this.f);
/*     */   }
/*     */   
/*     */   private JsonParsingException b(int ch) {
/* 599 */     JsonLocation location = c();
/* 600 */     return new JsonParsingException(
/* 601 */         h.a(ch, location), location);
/*     */   }
/*     */   
/*     */   private JsonParsingException a(int unexpected, char expected) {
/* 605 */     JsonLocation location = c();
/* 606 */     return new JsonParsingException(
/* 607 */         h.a(unexpected, location, expected), location);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/v.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */