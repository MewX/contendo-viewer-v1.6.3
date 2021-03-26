/*     */ package net.zamasoft.a.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import net.zamasoft.a.b;
/*     */ import net.zamasoft.a.b.aa;
/*     */ import net.zamasoft.a.b.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements aa
/*     */ {
/*     */   public static final int a = 0;
/*     */   public static final int b = 1;
/*     */   public static final int c = 2;
/*     */   public static final int d = 3;
/*     */   public static final int e = 4;
/*     */   public static final int f = 5;
/*     */   public static final int g = 6;
/*     */   public static final int h = 7;
/*     */   public static final int i = 8;
/*     */   public static final int j = 9;
/*     */   public static final int k = 10;
/*     */   public static final int l = 11;
/*     */   public static final int m = 13;
/*     */   public static final int n = 14;
/*     */   public static final int o = 15;
/*     */   public static final int p = 16;
/*     */   public static final int q = 17;
/*     */   public static final int r = 18;
/*     */   public static final int s = 19;
/*     */   public static final int t = 20;
/*     */   public static final int u = 21;
/*     */   public static final int v = 3072;
/*     */   public static final int w = 3073;
/*     */   public static final int x = 3074;
/*     */   public static final int y = 3075;
/*     */   public static final int z = 3076;
/*     */   public static final int A = 3077;
/*     */   public static final int B = 3078;
/*     */   public static final int C = 1804;
/*     */   public static final int D = 3080;
/*     */   public static final int E = 3081;
/*     */   public static final int F = 3082;
/*     */   public static final int G = 3083;
/*     */   public static final int H = 3084;
/*     */   public static final int I = 3085;
/*     */   public static final int J = 3086;
/*     */   public static final int K = 3089;
/*     */   public static final int L = 4620;
/*     */   public static final int M = 4876;
/*     */   public static final int N = 5132;
/*     */   public static final int O = 5388;
/*     */   public static final int P = 5644;
/*     */   public static final int Q = 5900;
/*     */   public static final int R = 7692;
/*     */   public static final int S = 7948;
/*     */   public static final int T = 3104;
/*     */   public static final int U = 3105;
/*     */   public static final int V = 3106;
/*     */   public static final int W = 3107;
/*     */   public static final int X = 3108;
/*     */   public static final int Y = 3109;
/*     */   public static final int Z = 3110;
/*     */   public static final byte aa = 1;
/*     */   public static final byte ab = 2;
/*     */   public static final byte ac = 3;
/*     */   private static final boolean dg = false;
/*     */   private final RandomAccessFile dh;
/* 145 */   private final b di = new b();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] dj;
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] dk;
/*     */ 
/*     */ 
/*     */   
/*     */   private int[][] dl;
/*     */ 
/*     */ 
/*     */   
/* 162 */   private int dm = -1;
/*     */   
/* 164 */   private final StringBuffer dn = new StringBuffer();
/*     */   
/*     */   private d do;
/*     */   
/*     */   public c(i de, RandomAccessFile raf) throws IOException {
/* 169 */     this.dh = raf;
/* 170 */     synchronized (this.dh) {
/*     */ 
/*     */       
/* 173 */       this.dh.seek(de.c());
/* 174 */       byte[] header = c();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       int skip = header[2];
/* 180 */       this.dh.skipBytes(skip - 4);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       int count = e();
/*     */ 
/*     */ 
/*     */       
/* 189 */       if (count != 1) {
/* 190 */         throw new IOException("Name INDEXが1つではありません。:" + count);
/*     */       }
/* 192 */       int offSize = f();
/* 193 */       this.dh.skipBytes(offSize);
/* 194 */       int offset = a(offSize);
/* 195 */       this.dh.skipBytes(offset - 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       int j = e();
/*     */ 
/*     */ 
/*     */       
/* 205 */       if (j != 1) {
/* 206 */         throw new IOException("Top DICT INDEXが1つではありません。:" + j);
/*     */       }
/* 208 */       int k = f();
/* 209 */       this.dh.skipBytes(k);
/* 210 */       int m = a(k);
/* 211 */       int dictEnd = m - 1;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 216 */       dictEnd = (int)(dictEnd + this.dh.getFilePointer());
/*     */ 
/*     */       
/* 219 */       int charStringsOffset = -1;
/* 220 */       int privateOffset = -1, privateEnd = 0;
/* 221 */       int fontDictIndexOffset = -1;
/* 222 */       int fdSelectOffset = -1;
/* 223 */       while (this.dh.getFilePointer() < dictEnd) {
/* 224 */         int op = g();
/*     */ 
/*     */ 
/*     */         
/* 228 */         switch (op) {
/*     */           case 17:
/* 230 */             charStringsOffset = this.di.a(0).intValue();
/*     */             break;
/*     */           case 18:
/* 233 */             privateEnd = this.di.a(0).intValue();
/* 234 */             privateOffset = this.di.a(1).intValue();
/*     */             break;
/*     */           case 3108:
/* 237 */             fontDictIndexOffset = this.di.a(0).intValue();
/*     */             break;
/*     */           case 3109:
/* 240 */             fdSelectOffset = this.di.a(0).intValue();
/*     */             break;
/*     */         } 
/* 243 */         this.di.b();
/*     */       } 
/* 245 */       if (charStringsOffset == -1) {
/* 246 */         throw new IOException("DICTにCharStringsがありません。");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 251 */       int n = e();
/*     */ 
/*     */ 
/*     */       
/* 255 */       if (n > 0) {
/* 256 */         int i5 = f();
/* 257 */         int i6 = n * i5;
/* 258 */         this.dh.skipBytes(i6);
/* 259 */         int i7 = a(i5);
/* 260 */         this.dh.skipBytes(i7 - 1);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 266 */       n = e();
/*     */ 
/*     */ 
/*     */       
/* 270 */       int i1 = f();
/* 271 */       n++;
/* 272 */       this.dk = new int[n];
/* 273 */       for (int i3 = 0; i3 < n; i3++) {
/* 274 */         this.dk[i3] = a(i1);
/*     */       }
/* 276 */       int globalSubrsIndexOffset = (int)this.dh.getFilePointer() - 1; int i4;
/* 277 */       for (i4 = 0; i4 < n; i4++) {
/* 278 */         this.dk[i4] = this.dk[i4] + globalSubrsIndexOffset;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 283 */       this.dh.seek((de.c() + charStringsOffset));
/* 284 */       int charCount = e();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 289 */       i1 = f();
/* 290 */       this.dj = new int[charCount];
/* 291 */       for (int i2 = 0; i2 < charCount; i2++) {
/* 292 */         this.dj[i2] = a(i1);
/*     */       }
/* 294 */       int charStringsIndexOffset = (int)this.dh.getFilePointer() + i1 - 1;
/* 295 */       for (i4 = 0; i4 < charCount; i4++) {
/* 296 */         this.dj[i4] = this.dj[i4] + charStringsIndexOffset;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 305 */       if (privateEnd != 0) {
/* 306 */         this.dh.seek((de.c() + privateOffset));
/* 307 */         privateEnd = (int)(privateEnd + this.dh.getFilePointer());
/*     */         
/* 309 */         int localSubrsOffset = -1;
/* 310 */         while (this.dh.getFilePointer() < privateEnd) {
/* 311 */           int op = g();
/*     */ 
/*     */ 
/*     */           
/* 315 */           switch (op) {
/*     */             case 19:
/* 317 */               localSubrsOffset = this.di.a(0).intValue() + privateOffset;
/*     */               break;
/*     */           } 
/* 320 */           this.di.b();
/*     */         } 
/*     */         
/* 323 */         if (localSubrsOffset != -1) {
/*     */           
/* 325 */           this.dh.seek((de.c() + localSubrsOffset));
/* 326 */           int i5 = e();
/*     */ 
/*     */ 
/*     */           
/* 330 */           int i6 = f();
/* 331 */           i5++;
/* 332 */           int[] localSubrOffsets = new int[i5];
/* 333 */           for (int i7 = 0; i7 < i5; i7++) {
/* 334 */             localSubrOffsets[i7] = a(i6);
/*     */           }
/* 336 */           int localSubrsIndexOffset = (int)this.dh.getFilePointer() - 1; int i8;
/* 337 */           for (i8 = 0; i8 < i5; i8++) {
/* 338 */             localSubrOffsets[i8] = localSubrOffsets[i8] + localSubrsIndexOffset;
/*     */           }
/* 340 */           this.dl = new int[charCount][];
/* 341 */           for (i8 = 0; i8 < charCount; i8++) {
/* 342 */             this.dl[i8] = localSubrOffsets;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 347 */       if (fontDictIndexOffset != -1) {
/*     */         int i11, nRanges, first, i12;
/* 349 */         this.dh.seek((de.c() + fontDictIndexOffset));
/* 350 */         int i5 = e();
/*     */ 
/*     */ 
/*     */         
/* 354 */         i5++;
/* 355 */         int i6 = f();
/* 356 */         int[] offsets = new int[i5];
/* 357 */         for (int i7 = 0; i7 < i5; i7++) {
/* 358 */           offsets[i7] = a(i6);
/*     */         }
/* 360 */         int[] privateOffsets = new int[i5 - 1];
/* 361 */         int[] privateLengths = new int[i5 - 1];
/* 362 */         for (int i8 = 0; i8 < i5 - 1; i8++) {
/* 363 */           int end = (int)this.dh.getFilePointer() + offsets[i8 + 1] - offsets[i8];
/* 364 */           while (this.dh.getFilePointer() < end) {
/* 365 */             int op = g();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 370 */             switch (op) {
/*     */               case 18:
/* 372 */                 privateLengths[i8] = this.di.a(0).intValue();
/* 373 */                 privateOffsets[i8] = this.di.a(1).intValue();
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 380 */             this.di.b();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 385 */         int[] subrsOffsets = new int[i5 - 1];
/* 386 */         for (int i9 = 0; i9 < i5 - 1; i9++) {
/* 387 */           int end = privateLengths[i9];
/* 388 */           if (end > 0) {
/*     */ 
/*     */             
/* 391 */             int fdPrivateOffset = privateOffsets[i9];
/* 392 */             this.dh.seek((de.c() + fdPrivateOffset));
/* 393 */             end = (int)(end + this.dh.getFilePointer());
/* 394 */             while (this.dh.getFilePointer() < end) {
/* 395 */               int op = g();
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 400 */               switch (op) {
/*     */                 case 19:
/* 402 */                   subrsOffsets[i9] = fdPrivateOffset + this.di.a(0).intValue();
/*     */                   break;
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 409 */               this.di.b();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 414 */         int[][] fdLocalSubrOffsets = new int[i5 - 1][];
/* 415 */         for (int i10 = 0; i10 < i5 - 1; i10++) {
/* 416 */           if (subrsOffsets[i10] != 0) {
/*     */ 
/*     */             
/* 419 */             this.dh.seek((de.c() + subrsOffsets[i10]));
/* 420 */             int subrCount = e() + 1;
/*     */ 
/*     */ 
/*     */             
/* 424 */             int subrOffSize = f();
/* 425 */             if (subrOffSize != 0) {
/*     */ 
/*     */               
/* 428 */               int[] subrOffsets = new int[subrCount];
/* 429 */               for (int i13 = 0; i13 < subrCount; i13++) {
/* 430 */                 subrOffsets[i13] = a(subrOffSize);
/*     */               }
/* 432 */               int fdLocalSubrsIndexOffset = (int)this.dh.getFilePointer() - 1;
/* 433 */               for (int i14 = 0; i14 < subrCount; i14++) {
/* 434 */                 subrOffsets[i14] = subrOffsets[i14] + fdLocalSubrsIndexOffset;
/*     */               }
/* 436 */               fdLocalSubrOffsets[i10] = subrOffsets;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 441 */         this.dl = new int[charCount][];
/* 442 */         this.dh.seek((de.c() + fdSelectOffset));
/* 443 */         int format = d();
/*     */ 
/*     */ 
/*     */         
/* 447 */         switch (format) {
/*     */           case 0:
/* 449 */             for (i11 = 0; i11 < charCount; i11++) {
/* 450 */               int fdIx = d();
/* 451 */               this.dl[i11] = fdLocalSubrOffsets[fdIx];
/*     */             } 
/*     */             break;
/*     */           case 3:
/* 455 */             nRanges = e();
/* 456 */             first = e();
/* 457 */             for (i12 = 0; i12 < nRanges; i12++) {
/* 458 */               int fdIx = d();
/* 459 */               int last = e();
/* 460 */               for (int i13 = first; i13 < last; i13++) {
/* 461 */                 this.dl[i13] = fdLocalSubrOffsets[fdIx];
/*     */               }
/* 463 */               first = last;
/*     */             } 
/*     */             break;
/*     */           default:
/* 467 */             throw new IOException("Unsupported FD Select format: " + format);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/* 475 */     return 1128678944;
/*     */   }
/*     */   
/*     */   public void b() {
/* 479 */     this.do = new d(this.dh);
/*     */   }
/*     */   
/*     */   public b a(int ix, short upm) {
/* 483 */     int[] localSubrOffsets = (this.dl == null) ? null : this.dl[ix];
/* 484 */     return this.do.a(ix, this.dj[ix], upm, this.dk, localSubrOffsets);
/*     */   }
/*     */   
/*     */   private byte[] c() throws IOException {
/* 488 */     byte[] header = new byte[4];
/* 489 */     for (int i = 0; i < 4; i++) {
/* 490 */       header[i] = this.dh.readByte();
/*     */     }
/* 492 */     return header;
/*     */   }
/*     */   
/*     */   private int d() throws IOException {
/* 496 */     return this.dh.read();
/*     */   }
/*     */   
/*     */   private int e() throws IOException {
/* 500 */     return this.dh.readShort() & 0xFFFF;
/*     */   }
/*     */   
/*     */   private int f() throws IOException {
/* 504 */     byte offSize = this.dh.readByte();
/* 505 */     if (offSize < 1 || offSize > 4)
/*     */     {
/* 507 */       throw new IOException("OffSizeは1から4までです。:" + offSize);
/*     */     }
/* 509 */     return offSize;
/*     */   }
/*     */   
/*     */   private int a(int size) throws IOException {
/* 513 */     int offset = 0;
/* 514 */     for (int i = 0; i < size; i++) {
/* 515 */       offset <<= 8;
/* 516 */       offset |= this.dh.read();
/*     */     } 
/* 518 */     return offset;
/*     */   } private int g() throws IOException {
/*     */     while (true) {
/*     */       Number number;
/*     */       String real;
/* 523 */       byte type = h();
/*     */       
/* 525 */       switch (type) {
/*     */         case 1:
/* 527 */           return i();
/*     */         case 2:
/* 529 */           number = Integer.valueOf(j());
/*     */           break;
/*     */         case 3:
/* 532 */           real = k();
/*     */           try {
/* 534 */             number = Float.valueOf(Float.parseFloat(real));
/* 535 */           } catch (NumberFormatException e) {
/*     */ 
/*     */ 
/*     */             
/* 539 */             throw e;
/*     */           } 
/*     */           break;
/*     */         default:
/* 543 */           throw new IllegalStateException();
/*     */       } 
/* 545 */       this.di.a(number);
/*     */     } 
/*     */   }
/*     */   
/*     */   private byte h() throws IOException {
/* 550 */     this.dm = this.dh.read();
/* 551 */     if (this.dm <= 21) {
/* 552 */       return 1;
/*     */     }
/* 554 */     if (this.dm == 30) {
/* 555 */       return 3;
/*     */     }
/* 557 */     if (this.dm >= 28 && this.dm != 31 && this.dm != 255) {
/* 558 */       return 2;
/*     */     }
/* 560 */     throw new IOException("未知のオペランドです。");
/*     */   }
/*     */   
/*     */   private int i() throws IOException {
/* 564 */     if (this.dm == -1 && 
/* 565 */       h() != 1) {
/* 566 */       throw new IOException("Operatorではありません。");
/*     */     }
/*     */     
/* 569 */     int i = this.dm;
/* 570 */     this.dm = -1;
/* 571 */     if (i == 12) {
/* 572 */       i <<= 8;
/* 573 */       i |= this.dh.read();
/*     */     } 
/* 575 */     return i;
/*     */   }
/*     */   
/*     */   private int j() throws IOException {
/* 579 */     if (this.dm == -1 && 
/* 580 */       h() != 2) {
/* 581 */       throw new IOException("Integerではありません。");
/*     */     }
/*     */     
/* 584 */     int b0 = this.dm;
/* 585 */     this.dm = -1;
/* 586 */     if (b0 >= 32 && b0 <= 246) {
/* 587 */       return b0 - 139;
/*     */     }
/* 589 */     if (b0 >= 247 && b0 <= 250) {
/* 590 */       int b1 = this.dh.read();
/* 591 */       return (b0 - 247) * 256 + b1 + 108;
/*     */     } 
/* 593 */     if (b0 >= 251 && b0 <= 254) {
/* 594 */       int b1 = this.dh.read();
/* 595 */       return -(b0 - 251) * 256 - b1 - 108;
/*     */     } 
/* 597 */     if (b0 == 28) {
/* 598 */       short b1 = (short)this.dh.read();
/* 599 */       short b2 = (short)this.dh.read();
/* 600 */       return b1 << 8 | b2;
/*     */     } 
/* 602 */     if (b0 == 29) {
/* 603 */       int b1 = this.dh.read();
/* 604 */       int b2 = this.dh.read();
/* 605 */       int b3 = this.dh.read();
/* 606 */       int b4 = this.dh.read();
/* 607 */       return b1 << 24 | b2 << 16 | b3 << 8 | b4;
/*     */     } 
/* 609 */     throw new IOException("不正なIntegerです。:" + b0);
/*     */   }
/*     */   
/*     */   private String k() throws IOException {
/* 613 */     if (this.dm == -1 && 
/* 614 */       h() != 3) {
/* 615 */       throw new IOException("Realではありません。");
/*     */     }
/*     */     
/* 618 */     this.dn.setLength(0);
/* 619 */     this.dm = -1;
/*     */     while (true) {
/* 621 */       int j = this.dh.read();
/* 622 */       for (int i = 0; i < 2; i++) {
/* 623 */         int k = j >> 4 & 0xF;
/* 624 */         switch (k) {
/*     */           case 10:
/* 626 */             this.dn.append('.');
/*     */             break;
/*     */           case 11:
/* 629 */             this.dn.append('E');
/*     */             break;
/*     */           case 12:
/* 632 */             this.dn.append("E-");
/*     */             break;
/*     */           case 13:
/*     */             break;
/*     */           case 14:
/* 637 */             this.dn.append('-');
/*     */             break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 15:
/* 648 */             return this.dn.toString();
/*     */           default:
/*     */             this.dn.append(String.valueOf(k));
/*     */             break;
/*     */         } 
/*     */         j <<= 4;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */