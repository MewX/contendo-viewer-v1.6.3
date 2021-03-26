/*     */ package jp.cssj.homare.impl.objects.barcode.b;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
/*     */ import org.krysalis.barcode4j.output.CanvasProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends AbstractBarcodeBean
/*     */ {
/*  16 */   private static final byte[] a = new byte[] { 1, 1, 4 };
/*  17 */   private static final byte[] b = new byte[] { 1, 3, 2 };
/*  18 */   private static final byte[] c = new byte[] { 3, 1, 2 };
/*  19 */   private static final byte[] d = new byte[] { 1, 2, 3 };
/*  20 */   private static final byte[] e = new byte[] { 1, 4, 1 };
/*  21 */   private static final byte[] f = new byte[] { 3, 2, 1 };
/*  22 */   private static final byte[] g = new byte[] { 2, 1, 3 };
/*  23 */   private static final byte[] h = new byte[] { 2, 3, 1 };
/*  24 */   private static final byte[] i = new byte[] { 4, 1, 1 };
/*  25 */   private static final byte[] j = new byte[] { 1, 4, 4 };
/*  26 */   private static final byte[] k = new byte[] { 4, 1, 4 };
/*  27 */   private static final byte[] l = new byte[] { 3, 2, 4 };
/*  28 */   private static final byte[] m = new byte[] { 3, 4, 2 };
/*  29 */   private static final byte[] n = new byte[] { 2, 3, 4 };
/*  30 */   private static final byte[] o = new byte[] { 4, 3, 2 };
/*  31 */   private static final byte[] p = new byte[] { 2, 4, 3 };
/*  32 */   private static final byte[] q = new byte[] { 4, 2, 3 };
/*  33 */   private static final byte[] r = new byte[] { 4, 4, 1 };
/*  34 */   private static final byte[] s = new byte[] { 1, 1, 1 };
/*  35 */   private static final byte[] t = new byte[] { 1, 3 };
/*  36 */   private static final byte[] u = new byte[] { 3, 1 };
/*     */   
/*     */   public void a(CanvasProvider canvas, String msg) {
/*  39 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*  40 */     msg = msg.toUpperCase();
/*     */     
/*     */     try {
/*  43 */       int ck = 0;
/*  44 */       out.write(t);
/*  45 */       int j = 0;
/*  46 */       for (int i = 0; i < msg.length(); ) {
/*  47 */         char c = msg.charAt(i);
/*  48 */         switch (c) {
/*     */           case '1':
/*  50 */             out.write(a);
/*  51 */             ck++;
/*  52 */             j++;
/*     */             break;
/*     */           case '2':
/*  55 */             out.write(b);
/*  56 */             ck += 2;
/*  57 */             j++;
/*     */             break;
/*     */           case '3':
/*  60 */             out.write(b.c);
/*  61 */             ck += 3;
/*  62 */             j++;
/*     */             break;
/*     */           case '4':
/*  65 */             out.write(d);
/*  66 */             ck += 4;
/*  67 */             j++;
/*     */             break;
/*     */           case '5':
/*  70 */             out.write(b.e);
/*  71 */             ck += 5;
/*  72 */             j++;
/*     */             break;
/*     */           case '6':
/*  75 */             out.write(f);
/*  76 */             ck += 6;
/*  77 */             j++;
/*     */             break;
/*     */           case '7':
/*  80 */             out.write(g);
/*  81 */             ck += 7;
/*  82 */             j++;
/*     */             break;
/*     */           case '8':
/*  85 */             out.write(h);
/*  86 */             ck += 8;
/*  87 */             j++;
/*     */             break;
/*     */           case '9':
/*  90 */             out.write(b.i);
/*  91 */             ck += 9;
/*  92 */             j++;
/*     */             break;
/*     */           case '0':
/*  95 */             out.write(b.j);
/*  96 */             ck += 0;
/*  97 */             j++;
/*     */             break;
/*     */           case '-':
/* 100 */             out.write(k);
/* 101 */             ck += 10;
/* 102 */             j++;
/*     */             break;
/*     */           case 'A':
/* 105 */             out.write(l);
/* 106 */             ck += 11;
/* 107 */             j++;
/* 108 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 111 */             out.write(b.j);
/* 112 */             ck += 0;
/* 113 */             j++;
/*     */             break;
/*     */           case 'B':
/* 116 */             out.write(l);
/* 117 */             ck += 11;
/* 118 */             j++;
/* 119 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 122 */             out.write(a);
/* 123 */             ck++;
/* 124 */             j++;
/*     */             break;
/*     */           case 'C':
/* 127 */             out.write(l);
/* 128 */             ck += 11;
/* 129 */             j++;
/* 130 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 133 */             out.write(b);
/* 134 */             ck += 2;
/* 135 */             j++;
/*     */             break;
/*     */           case 'D':
/* 138 */             out.write(l);
/* 139 */             ck += 11;
/* 140 */             j++;
/* 141 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 144 */             out.write(b.c);
/* 145 */             ck += 3;
/* 146 */             j++;
/*     */             break;
/*     */           case 'E':
/* 149 */             out.write(l);
/* 150 */             ck += 11;
/* 151 */             j++;
/* 152 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 155 */             out.write(d);
/* 156 */             ck += 4;
/* 157 */             j++;
/*     */             break;
/*     */           case 'F':
/* 160 */             out.write(l);
/* 161 */             ck += 11;
/* 162 */             j++;
/* 163 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 166 */             out.write(b.e);
/* 167 */             ck += 5;
/* 168 */             j++;
/*     */             break;
/*     */           case 'G':
/* 171 */             out.write(l);
/* 172 */             ck += 11;
/* 173 */             j++;
/* 174 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 177 */             out.write(f);
/* 178 */             ck += 6;
/* 179 */             j++;
/*     */             break;
/*     */           case 'H':
/* 182 */             out.write(l);
/* 183 */             ck += 11;
/* 184 */             j++;
/* 185 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 188 */             out.write(g);
/* 189 */             ck += 7;
/* 190 */             j++;
/*     */             break;
/*     */           case 'I':
/* 193 */             out.write(l);
/* 194 */             ck += 11;
/* 195 */             j++;
/* 196 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 199 */             out.write(h);
/* 200 */             ck += 8;
/* 201 */             j++;
/*     */             break;
/*     */           case 'J':
/* 204 */             out.write(l);
/* 205 */             ck += 11;
/* 206 */             j++;
/* 207 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 210 */             out.write(b.i);
/* 211 */             ck += 9;
/* 212 */             j++;
/*     */             break;
/*     */           case 'K':
/* 215 */             out.write(m);
/* 216 */             ck += 12;
/* 217 */             j++;
/* 218 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 221 */             out.write(b.j);
/* 222 */             ck += 0;
/* 223 */             j++;
/*     */             break;
/*     */           case 'L':
/* 226 */             out.write(m);
/* 227 */             ck += 12;
/* 228 */             j++;
/* 229 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 232 */             out.write(a);
/* 233 */             ck++;
/* 234 */             j++;
/*     */             break;
/*     */           case 'M':
/* 237 */             out.write(m);
/* 238 */             ck += 12;
/* 239 */             j++;
/* 240 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 243 */             out.write(b);
/* 244 */             ck += 2;
/* 245 */             j++;
/*     */             break;
/*     */           case 'N':
/* 248 */             out.write(m);
/* 249 */             ck += 12;
/* 250 */             j++;
/* 251 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 254 */             out.write(b.c);
/* 255 */             ck += 3;
/* 256 */             j++;
/*     */             break;
/*     */           case 'O':
/* 259 */             out.write(m);
/* 260 */             ck += 12;
/* 261 */             j++;
/* 262 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 265 */             out.write(d);
/* 266 */             ck += 4;
/* 267 */             j++;
/*     */             break;
/*     */           case 'P':
/* 270 */             out.write(m);
/* 271 */             ck += 12;
/* 272 */             j++;
/* 273 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 276 */             out.write(b.e);
/* 277 */             ck += 5;
/* 278 */             j++;
/*     */             break;
/*     */           case 'Q':
/* 281 */             out.write(m);
/* 282 */             ck += 12;
/* 283 */             j++;
/* 284 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 287 */             out.write(f);
/* 288 */             ck += 6;
/* 289 */             j++;
/*     */             break;
/*     */           case 'R':
/* 292 */             out.write(m);
/* 293 */             ck += 12;
/* 294 */             j++;
/* 295 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 298 */             out.write(g);
/* 299 */             ck += 7;
/* 300 */             j++;
/*     */             break;
/*     */           case 'S':
/* 303 */             out.write(m);
/* 304 */             ck += 12;
/* 305 */             j++;
/* 306 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 309 */             out.write(h);
/* 310 */             ck += 8;
/* 311 */             j++;
/*     */             break;
/*     */           case 'T':
/* 314 */             out.write(m);
/* 315 */             ck += 12;
/* 316 */             j++;
/* 317 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 320 */             out.write(b.i);
/* 321 */             ck += 9;
/* 322 */             j++;
/*     */             break;
/*     */           case 'U':
/* 325 */             out.write(n);
/* 326 */             ck += 13;
/* 327 */             j++;
/* 328 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 331 */             out.write(b.j);
/* 332 */             ck += 0;
/* 333 */             j++;
/*     */             break;
/*     */           case 'V':
/* 336 */             out.write(n);
/* 337 */             ck += 13;
/* 338 */             j++;
/* 339 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 342 */             out.write(a);
/* 343 */             ck++;
/* 344 */             j++;
/*     */             break;
/*     */           case 'W':
/* 347 */             out.write(n);
/* 348 */             ck += 13;
/* 349 */             j++;
/* 350 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 353 */             out.write(b);
/* 354 */             ck += 2;
/* 355 */             j++;
/*     */             break;
/*     */           case 'X':
/* 358 */             out.write(n);
/* 359 */             ck += 13;
/* 360 */             j++;
/* 361 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 364 */             out.write(b.c);
/* 365 */             ck += 3;
/* 366 */             j++;
/*     */             break;
/*     */           case 'Y':
/* 369 */             out.write(n);
/* 370 */             ck += 13;
/* 371 */             j++;
/* 372 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 375 */             out.write(d);
/* 376 */             ck += 4;
/* 377 */             j++;
/*     */             break;
/*     */           case 'Z':
/* 380 */             out.write(n);
/* 381 */             ck += 13;
/* 382 */             j++;
/* 383 */             if (j >= 20) {
/*     */               break;
/*     */             }
/* 386 */             out.write(b.e);
/* 387 */             ck += 5;
/* 388 */             j++; break;
/*     */           default:
/*     */             i++;
/*     */             continue;
/*     */         } 
/* 393 */         if (j >= 20) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 398 */       for (; j < 20; j++) {
/* 399 */         out.write(o);
/* 400 */         ck += 14;
/*     */       } 
/*     */       
/* 403 */       switch (19 - ck % 19) {
/*     */         case 19:
/* 405 */           out.write(b.j);
/*     */           break;
/*     */         case 1:
/* 408 */           out.write(a);
/*     */           break;
/*     */         case 2:
/* 411 */           out.write(b);
/*     */           break;
/*     */         case 3:
/* 414 */           out.write(b.c);
/*     */           break;
/*     */         case 4:
/* 417 */           out.write(d);
/*     */           break;
/*     */         case 5:
/* 420 */           out.write(b.e);
/*     */           break;
/*     */         case 6:
/* 423 */           out.write(f);
/*     */           break;
/*     */         case 7:
/* 426 */           out.write(g);
/*     */           break;
/*     */         case 8:
/* 429 */           out.write(h);
/*     */           break;
/*     */         case 9:
/* 432 */           out.write(b.i);
/*     */           break;
/*     */         case 10:
/* 435 */           out.write(k);
/*     */           break;
/*     */         case 11:
/* 438 */           out.write(l);
/*     */           break;
/*     */         case 12:
/* 441 */           out.write(m);
/*     */           break;
/*     */         case 13:
/* 444 */           out.write(n);
/*     */           break;
/*     */         case 14:
/* 447 */           out.write(o);
/*     */           break;
/*     */         case 15:
/* 450 */           out.write(p);
/*     */           break;
/*     */         case 16:
/* 453 */           out.write(q);
/*     */           break;
/*     */         case 17:
/* 456 */           out.write(r);
/*     */           break;
/*     */         case 18:
/* 459 */           out.write(s);
/*     */           break;
/*     */         default:
/* 462 */           throw new IllegalArgumentException("Unexpectred digit.");
/*     */       } 
/* 464 */       out.write(u);
/* 465 */     } catch (IOException e) {
/* 466 */       throw new IllegalStateException(e);
/*     */     } 
/*     */     
/* 469 */     byte[] code = out.toByteArray();
/* 470 */     for (int x = 0; x < code.length; x++) {
/* 471 */       a(canvas, (x * 2) * this.moduleWidth, code[x]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(CanvasProvider canvas, double x, byte state) {
/* 476 */     switch (state) {
/*     */       case 1:
/* 478 */         canvas.deviceFillRect(x, 0.0D * this.moduleWidth, this.moduleWidth, 6.0D * this.moduleWidth);
/*     */         return;
/*     */       
/*     */       case 2:
/* 482 */         canvas.deviceFillRect(x, 0.0D * this.moduleWidth, this.moduleWidth, 4.0D * this.moduleWidth);
/*     */         return;
/*     */       
/*     */       case 3:
/* 486 */         canvas.deviceFillRect(x, 2.0D * this.moduleWidth, this.moduleWidth, 4.0D * this.moduleWidth);
/*     */         return;
/*     */       
/*     */       case 4:
/* 490 */         canvas.deviceFillRect(x, 2.0D * this.moduleWidth, this.moduleWidth, 2.0D * this.moduleWidth);
/*     */         return;
/*     */     } 
/*     */     
/* 494 */     throw new IllegalStateException("Unexpected state." + state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double a(int arg0) {
/* 500 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */