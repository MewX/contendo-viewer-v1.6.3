/*     */ package com.a.a.d;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */ {
/*     */   public static final int a = 0;
/*     */   public static final int b = 1;
/*     */   public static final int c = 2;
/*     */   public static final int d = 4;
/*     */   public static final int e = 8;
/*     */   public static final int f = 16;
/*     */   
/*     */   static abstract class a
/*     */   {
/*     */     public byte[] a;
/*     */     public int b;
/*     */     
/*     */     public abstract boolean a(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2, boolean param1Boolean);
/*     */     
/*     */     public abstract int a(int param1Int);
/*     */   }
/*     */   
/*     */   public static byte[] a(String str, int flags) {
/* 117 */     return a(str.getBytes(), flags);
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
/*     */   public static byte[] a(byte[] input, int flags) {
/* 135 */     return a(input, 0, input.length, flags);
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
/*     */   public static byte[] a(byte[] input, int offset, int len, int flags) {
/* 157 */     b decoder = new b(flags, new byte[len * 3 / 4]);
/*     */     
/* 159 */     if (!decoder.a(input, offset, len, true)) {
/* 160 */       throw new IllegalArgumentException("bad base-64");
/*     */     }
/*     */ 
/*     */     
/* 164 */     if (decoder.b == decoder.a.length) {
/* 165 */       return decoder.a;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 170 */     byte[] temp = new byte[decoder.b];
/* 171 */     System.arraycopy(decoder.a, 0, temp, 0, decoder.b);
/* 172 */     return temp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class b
/*     */     extends a
/*     */   {
/* 180 */     private static final int[] c = new int[] { 
/* 181 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 182 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 183 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 
/* 184 */         52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, 
/* 185 */         -1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
/* 186 */         15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, 
/* 187 */         -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 
/* 188 */         41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, 
/* 189 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 190 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 191 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 192 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 193 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 194 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 195 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 196 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     private static final int[] d = new int[] { 
/* 204 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 205 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 206 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 
/* 207 */         52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, 
/* 208 */         -1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
/* 209 */         15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, 
/* 210 */         -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 
/* 211 */         41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, 
/* 212 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 213 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 214 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 215 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 216 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 217 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 218 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/* 219 */         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*     */ 
/*     */ 
/*     */     
/*     */     private static final int e = -1;
/*     */ 
/*     */ 
/*     */     
/*     */     private static final int f = -2;
/*     */ 
/*     */     
/*     */     private int g;
/*     */ 
/*     */     
/*     */     private int h;
/*     */ 
/*     */     
/*     */     private final int[] i;
/*     */ 
/*     */ 
/*     */     
/*     */     public b(int flags, byte[] output) {
/* 241 */       this.a = output;
/*     */       
/* 243 */       this.i = ((flags & 0x8) == 0) ? c : d;
/* 244 */       this.g = 0;
/* 245 */       this.h = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int a(int len) {
/* 253 */       return len * 3 / 4 + 10;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a(byte[] input, int offset, int len, boolean finish) {
/* 263 */       if (this.g == 6) return false;
/*     */       
/* 265 */       int p = offset;
/* 266 */       len += offset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 273 */       int state = this.g;
/* 274 */       int value = this.h;
/* 275 */       int op = 0;
/* 276 */       byte[] output = this.a;
/* 277 */       int[] alphabet = this.i;
/*     */       
/* 279 */       while (p < len) {
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
/* 294 */         if (state == 0) {
/* 295 */           while (p + 4 <= len) {
/* 296 */             if ((value = alphabet[input[p] & 0xFF] << 18 | 
/* 297 */               alphabet[input[p + 1] & 0xFF] << 12 | 
/* 298 */               alphabet[input[p + 2] & 0xFF] << 6 | 
/* 299 */               alphabet[input[p + 3] & 0xFF]) < 0)
/* 300 */               break;  output[op + 2] = (byte)value;
/* 301 */             output[op + 1] = (byte)(value >> 8);
/* 302 */             output[op] = (byte)(value >> 16);
/* 303 */             op += 3;
/* 304 */             p += 4;
/*     */           } 
/* 306 */           if (p >= len) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 314 */         int d = alphabet[input[p++] & 0xFF];
/*     */         
/* 316 */         switch (state) {
/*     */           case 0:
/* 318 */             if (d >= 0) {
/* 319 */               value = d;
/* 320 */               state++; continue;
/* 321 */             }  if (d != -1) {
/* 322 */               this.g = 6;
/* 323 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 1:
/* 328 */             if (d >= 0) {
/* 329 */               value = value << 6 | d;
/* 330 */               state++; continue;
/* 331 */             }  if (d != -1) {
/* 332 */               this.g = 6;
/* 333 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 2:
/* 338 */             if (d >= 0) {
/* 339 */               value = value << 6 | d;
/* 340 */               state++; continue;
/* 341 */             }  if (d == -2) {
/*     */ 
/*     */               
/* 344 */               output[op++] = (byte)(value >> 4);
/* 345 */               state = 4; continue;
/* 346 */             }  if (d != -1) {
/* 347 */               this.g = 6;
/* 348 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 3:
/* 353 */             if (d >= 0) {
/*     */               
/* 355 */               value = value << 6 | d;
/* 356 */               output[op + 2] = (byte)value;
/* 357 */               output[op + 1] = (byte)(value >> 8);
/* 358 */               output[op] = (byte)(value >> 16);
/* 359 */               op += 3;
/* 360 */               state = 0; continue;
/* 361 */             }  if (d == -2) {
/*     */ 
/*     */               
/* 364 */               output[op + 1] = (byte)(value >> 2);
/* 365 */               output[op] = (byte)(value >> 10);
/* 366 */               op += 2;
/* 367 */               state = 5; continue;
/* 368 */             }  if (d != -1) {
/* 369 */               this.g = 6;
/* 370 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 4:
/* 375 */             if (d == -2) {
/* 376 */               state++; continue;
/* 377 */             }  if (d != -1) {
/* 378 */               this.g = 6;
/* 379 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 5:
/* 384 */             if (d != -1) {
/* 385 */               this.g = 6;
/* 386 */               return false;
/*     */             } 
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 392 */       if (!finish) {
/*     */ 
/*     */         
/* 395 */         this.g = state;
/* 396 */         this.h = value;
/* 397 */         this.b = op;
/* 398 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 404 */       switch (state) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 411 */           this.g = 6;
/* 412 */           return false;
/*     */ 
/*     */         
/*     */         case 2:
/* 416 */           output[op++] = (byte)(value >> 4);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 421 */           output[op++] = (byte)(value >> 10);
/* 422 */           output[op++] = (byte)(value >> 2);
/*     */           break;
/*     */         
/*     */         case 4:
/* 426 */           this.g = 6;
/* 427 */           return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 434 */       this.g = state;
/* 435 */       this.b = op;
/* 436 */       return true;
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
/*     */   public static String b(byte[] input, int flags) {
/*     */     try {
/* 455 */       return new String(c(input, flags), "US-ASCII");
/* 456 */     } catch (UnsupportedEncodingException e) {
/*     */       
/* 458 */       throw new AssertionError(e);
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
/*     */   public static String b(byte[] input, int offset, int len, int flags) {
/*     */     try {
/* 476 */       return new String(c(input, offset, len, flags), "US-ASCII");
/* 477 */     } catch (UnsupportedEncodingException e) {
/*     */       
/* 479 */       throw new AssertionError(e);
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
/*     */   public static byte[] c(byte[] input, int flags) {
/* 493 */     return c(input, 0, input.length, flags);
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
/*     */   public static byte[] c(byte[] input, int offset, int len, int flags) {
/* 509 */     c encoder = new c(flags, null);
/*     */ 
/*     */     
/* 512 */     int output_len = len / 3 * 4;
/*     */ 
/*     */     
/* 515 */     if (encoder.e) {
/* 516 */       if (len % 3 > 0) {
/* 517 */         output_len += 4;
/*     */       }
/*     */     } else {
/* 520 */       switch (len % 3)
/*     */       { case 1:
/* 522 */           output_len += 2; break;
/* 523 */         case 2: output_len += 3; break; } 
/* 524 */     }  if (encoder
/*     */ 
/*     */ 
/*     */       
/* 528 */       .f && len > 0) {
/* 529 */       output_len += ((len - 1) / 57 + 1) * (
/* 530 */         encoder.g ? 2 : 1);
/*     */     }
/*     */     
/* 533 */     encoder.a = new byte[output_len];
/* 534 */     encoder.a(input, offset, len, true);
/*     */     
/* 536 */     if (!g && encoder.b != output_len) throw new AssertionError();
/*     */     
/* 538 */     return encoder.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class c
/*     */     extends a
/*     */   {
/*     */     public static final int c = 19;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 553 */     private static final byte[] i = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
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
/* 564 */     private static final byte[] j = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
/*     */     
/*     */     private final byte[] k;
/*     */     
/*     */     int d;
/*     */     
/*     */     private int l;
/*     */     public final boolean e;
/*     */     public final boolean f;
/*     */     public final boolean g;
/*     */     private final byte[] m;
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     public c(int flags, byte[] output) {
/* 581 */       this.a = output;
/*     */       
/* 583 */       this.e = ((flags & 0x1) == 0);
/* 584 */       this.f = ((flags & 0x2) == 0);
/* 585 */       this.g = ((flags & 0x4) != 0);
/* 586 */       this.m = ((flags & 0x8) == 0) ? i : j;
/*     */       
/* 588 */       this.k = new byte[2];
/* 589 */       this.d = 0;
/*     */       
/* 591 */       this.l = this.f ? 19 : -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int a(int len) {
/* 599 */       return len * 8 / 5 + 10;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(byte[] input, int offset, int len, boolean finish) {
/* 604 */       byte[] alphabet = this.m;
/* 605 */       byte[] output = this.a;
/* 606 */       int op = 0;
/* 607 */       int count = this.l;
/*     */       
/* 609 */       int p = offset;
/* 610 */       len += offset;
/* 611 */       int v = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 617 */       switch (this.d) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 623 */           if (p + 2 <= len) {
/*     */ 
/*     */             
/* 626 */             v = (this.k[0] & 0xFF) << 16 | (
/* 627 */               input[p++] & 0xFF) << 8 | 
/* 628 */               input[p++] & 0xFF;
/* 629 */             this.d = 0;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 2:
/* 634 */           if (p + 1 <= len) {
/*     */             
/* 636 */             v = (this.k[0] & 0xFF) << 16 | (
/* 637 */               this.k[1] & 0xFF) << 8 | 
/* 638 */               input[p++] & 0xFF;
/* 639 */             this.d = 0;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */       
/* 644 */       if (v != -1) {
/* 645 */         output[op++] = alphabet[v >> 18 & 0x3F];
/* 646 */         output[op++] = alphabet[v >> 12 & 0x3F];
/* 647 */         output[op++] = alphabet[v >> 6 & 0x3F];
/* 648 */         output[op++] = alphabet[v & 0x3F];
/* 649 */         if (--count == 0) {
/* 650 */           if (this.g) output[op++] = 13; 
/* 651 */           output[op++] = 10;
/* 652 */           count = 19;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 661 */       while (p + 3 <= len) {
/* 662 */         v = (input[p] & 0xFF) << 16 | (
/* 663 */           input[p + 1] & 0xFF) << 8 | 
/* 664 */           input[p + 2] & 0xFF;
/* 665 */         output[op] = alphabet[v >> 18 & 0x3F];
/* 666 */         output[op + 1] = alphabet[v >> 12 & 0x3F];
/* 667 */         output[op + 2] = alphabet[v >> 6 & 0x3F];
/* 668 */         output[op + 3] = alphabet[v & 0x3F];
/* 669 */         p += 3;
/* 670 */         op += 4;
/* 671 */         if (--count == 0) {
/* 672 */           if (this.g) output[op++] = 13; 
/* 673 */           output[op++] = 10;
/* 674 */           count = 19;
/*     */         } 
/*     */       } 
/*     */       
/* 678 */       if (finish) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 684 */         if (p - this.d == len - 1) {
/* 685 */           int t = 0;
/* 686 */           v = (((this.d > 0) ? this.k[t++] : input[p++]) & 0xFF) << 4;
/* 687 */           this.d -= t;
/* 688 */           output[op++] = alphabet[v >> 6 & 0x3F];
/* 689 */           output[op++] = alphabet[v & 0x3F];
/* 690 */           if (this.e) {
/* 691 */             output[op++] = 61;
/* 692 */             output[op++] = 61;
/*     */           } 
/* 694 */           if (this.f) {
/* 695 */             if (this.g) output[op++] = 13; 
/* 696 */             output[op++] = 10;
/*     */           } 
/* 698 */         } else if (p - this.d == len - 2) {
/* 699 */           int t = 0;
/* 700 */           v = (((this.d > 1) ? this.k[t++] : input[p++]) & 0xFF) << 10 | ((
/* 701 */             (this.d > 0) ? this.k[t++] : input[p++]) & 0xFF) << 2;
/* 702 */           this.d -= t;
/* 703 */           output[op++] = alphabet[v >> 12 & 0x3F];
/* 704 */           output[op++] = alphabet[v >> 6 & 0x3F];
/* 705 */           output[op++] = alphabet[v & 0x3F];
/* 706 */           if (this.e) {
/* 707 */             output[op++] = 61;
/*     */           }
/* 709 */           if (this.f) {
/* 710 */             if (this.g) output[op++] = 13; 
/* 711 */             output[op++] = 10;
/*     */           } 
/* 713 */         } else if (this.f && op > 0 && count != 19) {
/* 714 */           if (this.g) output[op++] = 13; 
/* 715 */           output[op++] = 10;
/*     */         } 
/*     */         
/* 718 */         if (!h && this.d != 0) throw new AssertionError(); 
/* 719 */         if (!h && p != len) throw new AssertionError();
/*     */         
/*     */ 
/*     */       
/*     */       }
/* 724 */       else if (p == len - 1) {
/* 725 */         this.k[this.d++] = input[p];
/* 726 */       } else if (p == len - 2) {
/* 727 */         this.k[this.d++] = input[p];
/* 728 */         this.k[this.d++] = input[p + 1];
/*     */       } 
/*     */ 
/*     */       
/* 732 */       this.b = op;
/* 733 */       this.l = count;
/*     */       
/* 735 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/d/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */