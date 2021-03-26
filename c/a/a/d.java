/*     */ package c.a.a;
/*     */ 
/*     */ import c.a.j.a;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   implements e, h, a, Cloneable
/*     */ {
/*     */   public i a;
/*     */   
/*     */   public class i
/*     */     implements Cloneable
/*     */   {
/*     */     public int a;
/*     */     public int b;
/*     */     public int c;
/*     */     public int d;
/*     */     public int e;
/*     */     public int f;
/*     */     public int g;
/*     */     public int h;
/*     */     public int i;
/*     */     public int j;
/*     */     public int k;
/*     */     public int[] l;
/*     */     public int[] m;
/*     */     public int[] n;
/*  77 */     private int[] p = null;
/*     */     
/*  79 */     private int q = -1;
/*     */     
/*  81 */     private int[] r = null;
/*     */     
/*  83 */     private int s = -1;
/*     */ 
/*     */ 
/*     */     
/*     */     public i(d this$0) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public int a(int c) {
/*  92 */       if (this.p == null) {
/*  93 */         this.p = new int[this.k];
/*  94 */         for (int cc = 0; cc < this.k; cc++) {
/*  95 */           this.p[cc] = 
/*     */             
/*  97 */             (int)(Math.ceil(this.c / this.m[cc]) - Math.ceil(this.e / this.m[cc]));
/*     */         }
/*     */       } 
/* 100 */       return this.p[c];
/*     */     }
/*     */     public int a() {
/* 103 */       if (this.p == null) {
/* 104 */         this.p = new int[this.k];
/* 105 */         for (int cc = 0; cc < this.k; cc++) {
/* 106 */           this.p[cc] = 
/*     */             
/* 108 */             (int)(Math.ceil(this.c / this.m[cc]) - Math.ceil(this.e / this.m[cc]));
/*     */         }
/*     */       } 
/* 111 */       if (this.q == -1) {
/* 112 */         for (int c = 0; c < this.k; c++) {
/* 113 */           if (this.p[c] > this.q) {
/* 114 */             this.q = this.p[c];
/*     */           }
/*     */         } 
/*     */       }
/* 118 */       return this.q;
/*     */     }
/*     */     public int b(int c) {
/* 121 */       if (this.r == null) {
/* 122 */         this.r = new int[this.k];
/* 123 */         for (int cc = 0; cc < this.k; cc++) {
/* 124 */           this.r[cc] = 
/*     */             
/* 126 */             (int)(Math.ceil(this.d / this.n[cc]) - Math.ceil(this.f / this.n[cc]));
/*     */         }
/*     */       } 
/* 129 */       return this.r[c];
/*     */     }
/*     */     public int b() {
/* 132 */       if (this.r == null) {
/* 133 */         this.r = new int[this.k];
/* 134 */         for (int cc = 0; cc < this.k; cc++) {
/* 135 */           this.r[cc] = 
/*     */             
/* 137 */             (int)(Math.ceil(this.d / this.n[cc]) - Math.ceil(this.f / this.n[cc]));
/*     */         }
/*     */       } 
/* 140 */       if (this.s == -1) {
/* 141 */         for (int c = 0; c < this.k; c++) {
/* 142 */           if (this.r[c] != this.s) {
/* 143 */             this.s = this.r[c];
/*     */           }
/*     */         } 
/*     */       }
/* 147 */       return this.s;
/*     */     }
/* 149 */     private int t = -1;
/*     */     public int c() {
/* 151 */       if (this.t == -1) {
/* 152 */         this.t = (this.c - this.i + this.g - 1) / this.g * (this.d - this.j + this.h - 1) / this.h;
/*     */       }
/*     */       
/* 155 */       return this.t;
/*     */     }
/* 157 */     private boolean[] u = null;
/*     */     public boolean c(int c) {
/* 159 */       if (this.u == null) {
/* 160 */         this.u = new boolean[this.k];
/* 161 */         for (int cc = 0; cc < this.k; cc++) {
/* 162 */           this.u[cc] = (this.l[cc] >>> 7 == 1);
/*     */         }
/*     */       } 
/* 165 */       return this.u[c];
/*     */     }
/* 167 */     private int[] v = null;
/*     */     public int d(int c) {
/* 169 */       if (this.v == null) {
/* 170 */         this.v = new int[this.k];
/* 171 */         for (int cc = 0; cc < this.k; cc++) {
/* 172 */           this.v[cc] = (this.l[cc] & 0x7F) + 1;
/*     */         }
/*     */       } 
/* 175 */       return this.v[c];
/*     */     }
/*     */     public i d() {
/* 178 */       i ms = null;
/*     */       try {
/* 180 */         ms = (i)clone();
/* 181 */       } catch (CloneNotSupportedException e) {
/* 182 */         throw new Error("Cannot clone SIZ marker segment");
/*     */       } 
/* 184 */       return ms;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 189 */       String str = "\n --- SIZ (" + this.a + " bytes) ---\n";
/* 190 */       str = str + " Capabilities : " + this.b + "\n";
/* 191 */       str = str + " Image dim.   : " + (this.c - this.e) + "x" + (this.d - this.f) + ", (off=" + this.e + "," + this.f + ")\n";
/*     */       
/* 193 */       str = str + " Tile dim.    : " + this.g + "x" + this.h + ", (off=" + this.i + "," + this.j + ")\n";
/*     */       
/* 195 */       str = str + " Component(s) : " + this.k + "\n";
/* 196 */       str = str + " Orig. depth  : "; int j;
/* 197 */       for (j = 0; j < this.k; ) { str = str + d(j) + " "; j++; }
/* 198 */        str = str + "\n";
/* 199 */       str = str + " Orig. signed : ";
/* 200 */       for (j = 0; j < this.k; ) { str = str + c(j) + " "; j++; }
/* 201 */        str = str + "\n";
/* 202 */       str = str + " Subs. factor : ";
/* 203 */       for (j = 0; j < this.k; ) { str = str + this.m[j] + "," + this.n[j] + " "; j++; }
/* 204 */        str = str + "\n";
/* 205 */       return str;
/*     */     } }
/*     */   
/*     */   public i a() {
/* 209 */     return new i(this);
/*     */   }
/*     */   
/*     */   public class j { public int a;
/*     */     public int b;
/*     */     public int c;
/*     */     public int d;
/*     */     public int e;
/*     */     
/*     */     public j(d this$0) {}
/*     */     
/*     */     public String toString() {
/* 221 */       String str = "\n --- SOT (" + this.a + " bytes) ---\n";
/* 222 */       str = str + "Tile index         : " + this.b + "\n";
/* 223 */       str = str + "Tile-part length   : " + this.c + " bytes\n";
/* 224 */       str = str + "Tile-part index    : " + this.d + "\n";
/* 225 */       str = str + "Num. of tile-parts : " + this.e + "\n";
/* 226 */       str = str + "\n";
/* 227 */       return str;
/*     */     } }
/*     */   
/*     */   public j b() {
/* 231 */     return new j(this);
/*     */   }
/*     */   
/*     */   public class b implements Cloneable {
/*     */     public int a;
/*     */     public int b;
/*     */     public int c;
/*     */     public int d;
/*     */     public int e;
/*     */     public int f;
/*     */     public int g;
/*     */     public int h;
/*     */     public int i;
/* 244 */     public int[] j = new int[1]; public int[] k;
/*     */     public b(d this$0) {}
/*     */     
/*     */     public b a() {
/* 248 */       b ms = null;
/*     */       try {
/* 250 */         ms = (b)clone();
/* 251 */       } catch (CloneNotSupportedException e) {
/* 252 */         throw new Error("Cannot clone SIZ marker segment");
/*     */       } 
/* 254 */       return ms;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 258 */       String str = "\n --- COD (" + this.a + " bytes) ---\n";
/* 259 */       str = str + " Coding style   : ";
/* 260 */       if (this.b == 0) {
/* 261 */         str = str + "Default";
/*     */       } else {
/* 263 */         if ((this.b & 0x1) != 0) str = str + "Precints "; 
/* 264 */         if ((this.b & 0x2) != 0) str = str + "SOP "; 
/* 265 */         if ((this.b & 0x4) != 0) str = str + "EPH "; 
/* 266 */         int cb0x = ((this.b & 0x8) != 0) ? 1 : 0;
/* 267 */         int cb0y = ((this.b & 0x10) != 0) ? 1 : 0;
/* 268 */         if (cb0x != 0 || cb0y != 0) {
/* 269 */           str = str + "Code-blocks offset";
/* 270 */           str = str + "\n Cblk partition : " + cb0x + "," + cb0y;
/*     */         } 
/*     */       } 
/* 273 */       str = str + "\n";
/* 274 */       str = str + " Cblk style     : ";
/* 275 */       if (this.i == 0) {
/* 276 */         str = str + "Default";
/*     */       } else {
/* 278 */         if ((this.i & 0x1) != 0) str = str + "Bypass "; 
/* 279 */         if ((this.i & 0x2) != 0) str = str + "Reset "; 
/* 280 */         if ((this.i & 0x4) != 0) str = str + "Terminate "; 
/* 281 */         if ((this.i & 0x8) != 0) str = str + "Vert_causal "; 
/* 282 */         if ((this.i & 0x10) != 0) str = str + "Predict "; 
/* 283 */         if ((this.i & 0x20) != 0) str = str + "Seg_symb "; 
/*     */       } 
/* 285 */       str = str + "\n";
/* 286 */       str = str + " Num. of levels : " + this.f + "\n";
/* 287 */       switch (this.c) {
/*     */         case 0:
/* 289 */           str = str + " Progress. type : LY_RES_COMP_POS_PROG\n";
/*     */           break;
/*     */         case 1:
/* 292 */           str = str + " Progress. type : RES_LY_COMP_POS_PROG\n";
/*     */           break;
/*     */         case 2:
/* 295 */           str = str + " Progress. type : RES_POS_COMP_LY_PROG\n";
/*     */           break;
/*     */         case 3:
/* 298 */           str = str + " Progress. type : POS_COMP_RES_LY_PROG\n";
/*     */           break;
/*     */         case 4:
/* 301 */           str = str + " Progress. type : COMP_POS_RES_LY_PROG\n";
/*     */           break;
/*     */       } 
/* 304 */       str = str + " Num. of layers : " + this.d + "\n";
/* 305 */       str = str + " Cblk dimension : " + (1 << this.g + 2) + "x" + (1 << this.h + 2) + "\n";
/*     */       
/* 307 */       switch (this.j[0]) {
/*     */         case 0:
/* 309 */           str = str + " Filter         : 9-7 irreversible\n";
/*     */           break;
/*     */         case 1:
/* 312 */           str = str + " Filter         : 5-3 reversible\n";
/*     */           break;
/*     */       } 
/* 315 */       str = str + " Multi comp tr. : " + ((this.e == 1) ? 1 : 0) + "\n";
/* 316 */       if (this.k != null) {
/* 317 */         str = str + " Precincts      : ";
/* 318 */         for (int i = 0; i < this.k.length; i++) {
/* 319 */           str = str + (1 << (this.k[i] & 0xF)) + "x" + (1 << (this.k[i] & 0xF0) >> 4) + " ";
/*     */         }
/*     */       } 
/*     */       
/* 323 */       str = str + "\n";
/* 324 */       return str;
/*     */     } }
/*     */   
/*     */   public b c() {
/* 328 */     return new b(this);
/*     */   }
/*     */   
/*     */   public class a {
/*     */     public int a;
/*     */     public int b;
/*     */     public int c;
/*     */     public int d;
/*     */     public int e;
/*     */     public int f;
/*     */     public int g;
/* 339 */     public int[] h = new int[1]; public int[] i;
/*     */     public a(d this$0) {}
/*     */     
/*     */     public String toString() {
/* 343 */       String str = "\n --- COC (" + this.a + " bytes) ---\n";
/* 344 */       str = str + " Component      : " + this.b + "\n";
/* 345 */       str = str + " Coding style   : ";
/* 346 */       if (this.c == 0) {
/* 347 */         str = str + "Default";
/*     */       } else {
/* 349 */         if ((this.c & 0x1) != 0) str = str + "Precints "; 
/* 350 */         if ((this.c & 0x2) != 0) str = str + "SOP "; 
/* 351 */         if ((this.c & 0x4) != 0) str = str + "EPH "; 
/*     */       } 
/* 353 */       str = str + "\n";
/* 354 */       str = str + " Cblk style     : ";
/* 355 */       if (this.g == 0) {
/* 356 */         str = str + "Default";
/*     */       } else {
/* 358 */         if ((this.g & 0x1) != 0) str = str + "Bypass "; 
/* 359 */         if ((this.g & 0x2) != 0) str = str + "Reset "; 
/* 360 */         if ((this.g & 0x4) != 0) str = str + "Terminate "; 
/* 361 */         if ((this.g & 0x8) != 0) str = str + "Vert_causal "; 
/* 362 */         if ((this.g & 0x10) != 0) str = str + "Predict "; 
/* 363 */         if ((this.g & 0x20) != 0) str = str + "Seg_symb "; 
/*     */       } 
/* 365 */       str = str + "\n";
/* 366 */       str = str + " Num. of levels : " + this.d + "\n";
/* 367 */       str = str + " Cblk dimension : " + (1 << this.e + 2) + "x" + (1 << this.f + 2) + "\n";
/*     */       
/* 369 */       switch (this.h[0]) {
/*     */         case 0:
/* 371 */           str = str + " Filter         : 9-7 irreversible\n";
/*     */           break;
/*     */         case 1:
/* 374 */           str = str + " Filter         : 5-3 reversible\n";
/*     */           break;
/*     */       } 
/* 377 */       if (this.i != null) {
/* 378 */         str = str + " Precincts      : ";
/* 379 */         for (int i = 0; i < this.i.length; i++) {
/* 380 */           str = str + (1 << (this.i[i] & 0xF)) + "x" + (1 << (this.i[i] & 0xF0) >> 4) + " ";
/*     */         }
/*     */       } 
/*     */       
/* 384 */       str = str + "\n";
/* 385 */       return str;
/*     */     } }
/*     */   
/*     */   public a d() {
/* 389 */     return new a(this);
/*     */   }
/*     */   public class h { public int a;
/*     */     public int b;
/*     */     public int c;
/*     */     public int d;
/*     */     
/*     */     public h(d this$0) {}
/*     */     
/*     */     public String toString() {
/* 399 */       String str = "\n --- RGN (" + this.a + " bytes) ---\n";
/* 400 */       str = str + " Component : " + this.b + "\n";
/* 401 */       if (this.c == 0) {
/* 402 */         str = str + " ROI style : Implicit\n";
/*     */       } else {
/* 404 */         str = str + " ROI style : Unsupported\n";
/*     */       } 
/* 406 */       str = str + " ROI shift : " + this.d + "\n";
/* 407 */       str = str + "\n";
/* 408 */       return str;
/*     */     } }
/*     */   
/*     */   public h e() {
/* 412 */     return new h(this);
/*     */   }
/*     */   public class g { public int a;
/*     */     public int b;
/*     */     public int[][] c;
/*     */     
/*     */     public g(d this$0) {}
/*     */     
/* 420 */     private int e = -1;
/*     */     public int a() {
/* 422 */       if (this.e == -1) {
/* 423 */         this.e = this.b & 0xFFFFFF1F;
/*     */       }
/* 425 */       return this.e;
/*     */     }
/* 427 */     private int f = -1;
/*     */     public int b() {
/* 429 */       if (this.f == -1) {
/* 430 */         this.f = this.b >> 5 & 0x7;
/*     */       }
/* 432 */       return this.f;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 437 */       String str = "\n --- QCD (" + this.a + " bytes) ---\n";
/* 438 */       str = str + " Quant. type    : ";
/* 439 */       int qt = a();
/* 440 */       if (qt == 0) { str = str + "No quantization \n"; }
/* 441 */       else if (qt == 1) { str = str + "Scalar derived\n"; }
/* 442 */       else if (qt == 2) { str = str + "Scalar expounded\n"; }
/* 443 */        str = str + " Guard bits     : " + b() + "\n";
/* 444 */       if (qt == 0) {
/* 445 */         str = str + " Exponents   :\n";
/*     */         
/* 447 */         for (int i = 0; i < this.c.length; i++) {
/* 448 */           for (int j = 0; j < (this.c[i]).length; j++) {
/* 449 */             if (i == 0 && j == 0) {
/* 450 */               int exp = this.c[0][0] >> 3 & 0x1F;
/* 451 */               str = str + "\tr=0 : " + exp + "\n";
/* 452 */             } else if (i != 0 && j > 0) {
/* 453 */               int exp = this.c[i][j] >> 3 & 0x1F;
/* 454 */               str = str + "\tr=" + i + ",s=" + j + " : " + exp + "\n";
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 459 */         str = str + " Exp / Mantissa : \n";
/*     */ 
/*     */         
/* 462 */         for (int i = 0; i < this.c.length; i++) {
/* 463 */           for (int j = 0; j < (this.c[i]).length; j++) {
/* 464 */             if (i == 0 && j == 0) {
/* 465 */               int exp = this.c[0][0] >> 11 & 0x1F;
/* 466 */               double mantissa = ((-1.0F - (this.c[0][0] & 0x7FF) / 2048.0F) / (-1 << exp));
/*     */               
/* 468 */               str = str + "\tr=0 : " + exp + " / " + mantissa + "\n";
/* 469 */             } else if (i != 0 && j > 0) {
/* 470 */               int exp = this.c[i][j] >> 11 & 0x1F;
/* 471 */               double mantissa = ((-1.0F - (this.c[i][j] & 0x7FF) / 2048.0F) / (-1 << exp));
/*     */               
/* 473 */               str = str + "\tr=" + i + ",s=" + j + " : " + exp + " / " + mantissa + "\n";
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 479 */       str = str + "\n";
/* 480 */       return str;
/*     */     } }
/*     */   
/*     */   public g f() {
/* 484 */     return new g(this);
/*     */   }
/*     */   
/*     */   public class f
/*     */   {
/*     */     public int a;
/*     */     public int b;
/*     */     public int c;
/*     */     public int[][] d;
/* 493 */     private int f = -1; public f(d this$0) {}
/*     */     public int a() {
/* 495 */       if (this.f == -1) {
/* 496 */         this.f = this.c & 0xFFFFFF1F;
/*     */       }
/* 498 */       return this.f;
/*     */     }
/* 500 */     private int g = -1;
/*     */     public int b() {
/* 502 */       if (this.g == -1) {
/* 503 */         this.g = this.c >> 5 & 0x7;
/*     */       }
/* 505 */       return this.g;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 510 */       String str = "\n --- QCC (" + this.a + " bytes) ---\n";
/* 511 */       str = str + " Component      : " + this.b + "\n";
/* 512 */       str = str + " Quant. type    : ";
/* 513 */       int qt = a();
/* 514 */       if (qt == 0) { str = str + "No quantization \n"; }
/* 515 */       else if (qt == 1) { str = str + "Scalar derived\n"; }
/* 516 */       else if (qt == 2) { str = str + "Scalar expounded\n"; }
/* 517 */        str = str + " Guard bits     : " + b() + "\n";
/* 518 */       if (qt == 0) {
/* 519 */         str = str + " Exponents   :\n";
/*     */         
/* 521 */         for (int i = 0; i < this.d.length; i++) {
/* 522 */           for (int j = 0; j < (this.d[i]).length; j++) {
/* 523 */             if (i == 0 && j == 0) {
/* 524 */               int exp = this.d[0][0] >> 3 & 0x1F;
/* 525 */               str = str + "\tr=0 : " + exp + "\n";
/* 526 */             } else if (i != 0 && j > 0) {
/* 527 */               int exp = this.d[i][j] >> 3 & 0x1F;
/* 528 */               str = str + "\tr=" + i + ",s=" + j + " : " + exp + "\n";
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 533 */         str = str + " Exp / Mantissa : \n";
/*     */ 
/*     */         
/* 536 */         for (int i = 0; i < this.d.length; i++) {
/* 537 */           for (int j = 0; j < (this.d[i]).length; j++) {
/* 538 */             if (i == 0 && j == 0) {
/* 539 */               int exp = this.d[0][0] >> 11 & 0x1F;
/* 540 */               double mantissa = ((-1.0F - (this.d[0][0] & 0x7FF) / 2048.0F) / (-1 << exp));
/*     */               
/* 542 */               str = str + "\tr=0 : " + exp + " / " + mantissa + "\n";
/* 543 */             } else if (i != 0 && j > 0) {
/* 544 */               int exp = this.d[i][j] >> 11 & 0x1F;
/* 545 */               double mantissa = ((-1.0F - (this.d[i][j] & 0x7FF) / 2048.0F) / (-1 << exp));
/*     */               
/* 547 */               str = str + "\tr=" + i + ",s=" + j + " : " + exp + " / " + mantissa + "\n";
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 553 */       str = str + "\n";
/* 554 */       return str;
/*     */     } }
/*     */   
/*     */   public f g() {
/* 558 */     return new f(this);
/*     */   }
/*     */   public class e { public int a;
/*     */     public int[] b;
/*     */     public int[] c;
/*     */     public int[] d;
/*     */     public int[] e;
/*     */     public int[] f;
/*     */     public int[] g;
/*     */     
/*     */     public e(d this$0) {}
/*     */     
/*     */     public String toString() {
/* 571 */       String str = "\n --- POC (" + this.a + " bytes) ---\n";
/* 572 */       str = str + " Chg_idx RSpoc CSpoc LYEpoc REpoc CEpoc Ppoc\n";
/* 573 */       for (int chg = 0; chg < this.b.length; chg++) {
/* 574 */         str = str + "   " + chg + "      " + this.b[chg] + "     " + this.c[chg] + "     " + this.d[chg] + "      " + this.e[chg] + "     " + this.f[chg];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 580 */         switch (this.g[chg]) {
/*     */           case 0:
/* 582 */             str = str + "  LY_RES_COMP_POS_PROG\n";
/*     */             break;
/*     */           case 1:
/* 585 */             str = str + "  RES_LY_COMP_POS_PROG\n";
/*     */             break;
/*     */           case 2:
/* 588 */             str = str + "  RES_POS_COMP_LY_PROG\n";
/*     */             break;
/*     */           case 3:
/* 591 */             str = str + "  POS_COMP_RES_LY_PROG\n";
/*     */             break;
/*     */           case 4:
/* 594 */             str = str + "  COMP_POS_RES_LY_PROG\n";
/*     */             break;
/*     */         } 
/*     */       } 
/* 598 */       str = str + "\n";
/* 599 */       return str;
/*     */     } }
/*     */   
/*     */   public e h() {
/* 603 */     return new e(this);
/*     */   }
/*     */   public class d { public int a;
/*     */     public int[] b;
/*     */     public int[] c;
/*     */     
/*     */     public d(d this$0) {}
/*     */     
/*     */     public String toString() {
/* 612 */       String str = "\n --- CRG (" + this.a + " bytes) ---\n";
/* 613 */       for (int c = 0; c < this.b.length; c++) {
/* 614 */         str = str + " Component " + c + " offset : " + this.b[c] + "," + this.c[c] + "\n";
/*     */       }
/* 616 */       str = str + "\n";
/* 617 */       return str;
/*     */     } }
/*     */   
/*     */   public d i() {
/* 621 */     return new d(this);
/*     */   }
/*     */   public class c { public int a;
/*     */     public int b;
/*     */     public byte[] c;
/*     */     
/*     */     public c(d this$0) {}
/*     */     
/*     */     public String toString() {
/* 630 */       String str = "\n --- COM (" + this.a + " bytes) ---\n";
/* 631 */       if (this.b == 0) {
/* 632 */         str = str + " Registration : General use (binary values)\n";
/* 633 */       } else if (this.b == 1) {
/* 634 */         str = str + " Registration : General use (IS 8859-15:1999 (Latin) values)\n";
/*     */         
/* 636 */         str = str + " Text         : " + new String(this.c) + "\n";
/*     */       } else {
/* 638 */         str = str + " Registration : Unknown\n";
/*     */       } 
/* 640 */       str = str + "\n";
/* 641 */       return str;
/*     */     } }
/*     */   
/*     */   public c j() {
/* 645 */     this.ae++; return new c(this);
/*     */   }
/*     */   public int k() {
/* 648 */     return this.ae;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 655 */   public Hashtable b = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/* 659 */   public Hashtable c = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 664 */   public Hashtable d = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 669 */   public Hashtable e = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/* 673 */   public Hashtable f = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 678 */   public Hashtable g = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/* 682 */   public Hashtable h = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*     */   public d i;
/*     */ 
/*     */   
/* 689 */   public Hashtable j = new Hashtable<Object, Object>();
/*     */ 
/*     */   
/* 692 */   private int ae = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public String l() {
/* 697 */     int nc = this.a.k;
/*     */     
/* 699 */     String str = "" + this.a;
/*     */     
/* 701 */     if (this.c.get("main") != null) {
/* 702 */       str = str + "" + (b)this.c.get("main");
/*     */     }
/*     */     int c;
/* 705 */     for (c = 0; c < nc; c++) {
/* 706 */       if (this.d.get("main_c" + c) != null) {
/* 707 */         str = str + "" + (a)this.d.get("main_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 711 */     if (this.f.get("main") != null) {
/* 712 */       str = str + "" + (g)this.f.get("main");
/*     */     }
/*     */     
/* 715 */     for (c = 0; c < nc; c++) {
/* 716 */       if (this.g.get("main_c" + c) != null) {
/* 717 */         str = str + "" + (f)this.g.get("main_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 721 */     for (c = 0; c < nc; c++) {
/* 722 */       if (this.e.get("main_c" + c) != null) {
/* 723 */         str = str + "" + (h)this.e.get("main_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 727 */     if (this.h.get("main") != null) {
/* 728 */       str = str + "" + (e)this.h.get("main");
/*     */     }
/*     */     
/* 731 */     if (this.i != null) {
/* 732 */       str = str + "" + this.i;
/*     */     }
/*     */     
/* 735 */     for (int j = 0; j < this.ae; j++) {
/* 736 */       if (this.j.get("main_" + j) != null) {
/* 737 */         str = str + "" + (c)this.j.get("main_" + j);
/*     */       }
/*     */     } 
/* 740 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String a(int t, int ntp) {
/* 751 */     int nc = this.a.k;
/* 752 */     String str = "";
/*     */     
/* 754 */     for (int j = 0; j < ntp; j++) {
/* 755 */       str = str + "Tile-part " + j + ", tile " + t + ":\n";
/* 756 */       str = str + "" + (j)this.b.get("t" + t + "_tp" + j);
/*     */     } 
/*     */     
/* 759 */     if (this.c.get("t" + t) != null) {
/* 760 */       str = str + "" + (b)this.c.get("t" + t);
/*     */     }
/*     */     int c;
/* 763 */     for (c = 0; c < nc; c++) {
/* 764 */       if (this.d.get("t" + t + "_c" + c) != null) {
/* 765 */         str = str + "" + (a)this.d.get("t" + t + "_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 769 */     if (this.f.get("t" + t) != null) {
/* 770 */       str = str + "" + (g)this.f.get("t" + t);
/*     */     }
/*     */     
/* 773 */     for (c = 0; c < nc; c++) {
/* 774 */       if (this.g.get("t" + t + "_c" + c) != null) {
/* 775 */         str = str + "" + (f)this.g.get("t" + t + "_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 779 */     for (c = 0; c < nc; c++) {
/* 780 */       if (this.e.get("t" + t + "_c" + c) != null) {
/* 781 */         str = str + "" + (h)this.e.get("t" + t + "_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 785 */     if (this.h.get("t" + t) != null) {
/* 786 */       str = str + "" + (e)this.h.get("t" + t);
/*     */     }
/* 788 */     return str;
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
/*     */   public String b(int t, int ntp) {
/* 800 */     int nc = this.a.k;
/* 801 */     String str = "";
/*     */     
/* 803 */     if (this.c.get("t" + t) != null) {
/* 804 */       str = str + "" + (b)this.c.get("t" + t);
/*     */     }
/*     */     int c;
/* 807 */     for (c = 0; c < nc; c++) {
/* 808 */       if (this.d.get("t" + t + "_c" + c) != null) {
/* 809 */         str = str + "" + (a)this.d.get("t" + t + "_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 813 */     if (this.f.get("t" + t) != null) {
/* 814 */       str = str + "" + (g)this.f.get("t" + t);
/*     */     }
/*     */     
/* 817 */     for (c = 0; c < nc; c++) {
/* 818 */       if (this.g.get("t" + t + "_c" + c) != null) {
/* 819 */         str = str + "" + (f)this.g.get("t" + t + "_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 823 */     for (c = 0; c < nc; c++) {
/* 824 */       if (this.e.get("t" + t + "_c" + c) != null) {
/* 825 */         str = str + "" + (h)this.e.get("t" + t + "_c" + c);
/*     */       }
/*     */     } 
/*     */     
/* 829 */     if (this.h.get("t" + t) != null) {
/* 830 */       str = str + "" + (e)this.h.get("t" + t);
/*     */     }
/* 832 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public d a(int nt) {
/* 837 */     d nhi = null;
/*     */     
/*     */     try {
/* 840 */       nhi = (d)clone();
/* 841 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/* 842 */       throw new Error("Cannot clone HeaderInfo instance");
/*     */     } 
/* 844 */     nhi.a = this.a.d();
/*     */     
/* 846 */     if (this.c.get("main") != null) {
/* 847 */       b ms = (b)this.c.get("main");
/* 848 */       nhi.c.put("main", ms.a());
/*     */     } 
/* 850 */     for (int t = 0; t < nt; t++) {
/* 851 */       if (this.c.get("t" + t) != null) {
/* 852 */         b ms = (b)this.c.get("t" + t);
/* 853 */         nhi.c.put("t" + t, ms.a());
/*     */       } 
/*     */     } 
/* 856 */     return nhi;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */