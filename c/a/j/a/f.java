/*     */ package c.a.j.a;
/*     */ 
/*     */ import c.a.d;
/*     */ import c.a.g.c;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class f
/*     */   extends d
/*     */ {
/*     */   private static final String a = "w5x3";
/*     */   private static final String r = "w9x7";
/*     */   
/*     */   public f(int nt, int nc, byte type, c qts, J2KImageWriteParamJava wp, String values) {
/*  82 */     super(nt, nc, type);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.q = values;
/*  89 */     String param = this.q;
/*  90 */     boolean isFilterSpecified = true;
/*     */ 
/*     */     
/*  93 */     if (values == null) {
/*  94 */       isFilterSpecified = false;
/*     */       
/*  96 */       if (wp.getLossless()) {
/*  97 */         a(a("w5x3"));
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 104 */       for (int i = nt - 1; i >= 0; i--) {
/* 105 */         for (int j = nc - 1; j >= 0; j--) {
/* 106 */           switch (qts.c(i, j)) {
/*     */             case 0:
/* 108 */               if (d() == null) {
/* 109 */                 if (wp.getLossless())
/* 110 */                   a(a("w5x3")); 
/* 111 */                 if (((String)qts.d())
/* 112 */                   .equals("reversible")) {
/* 113 */                   a(a("w5x3"));
/*     */                 } else {
/*     */                   
/* 116 */                   a(a("w9x7"));
/*     */                 } 
/*     */               } 
/* 119 */               this.l[i][j] = 0;
/*     */               break;
/*     */             case 1:
/* 122 */               if (!g(j)) {
/* 123 */                 if (((String)qts.e(j))
/* 124 */                   .equals("reversible")) {
/* 125 */                   a(j, a("w5x3"));
/*     */                 } else {
/*     */                   
/* 128 */                   a(j, a("w9x7"));
/*     */                 } 
/*     */               }
/* 131 */               this.l[i][j] = 1;
/*     */               break;
/*     */             case 2:
/* 134 */               if (!h(i)) {
/* 135 */                 if (((String)qts.f(i))
/* 136 */                   .equals("reversible")) {
/* 137 */                   b(i, a("w5x3"));
/*     */                 } else {
/*     */                   
/* 140 */                   b(i, a("w9x7"));
/*     */                 } 
/*     */               }
/* 143 */               this.l[i][j] = 2;
/*     */               break;
/*     */             case 3:
/* 146 */               if (!d(i, j)) {
/* 147 */                 if (((String)qts.a(i, j))
/* 148 */                   .equals("reversible")) {
/* 149 */                   a(i, j, a("w5x3"));
/*     */                 } else {
/*     */                   
/* 152 */                   a(i, j, 
/* 153 */                       a("w9x7"));
/*     */                 } 
/*     */               }
/* 156 */               this.l[i][j] = 3;
/*     */               break;
/*     */             default:
/* 159 */               throw new IllegalArgumentException("Unsupported specification type");
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } 
/* 169 */     StringTokenizer stk = new StringTokenizer(param);
/*     */     
/* 171 */     byte curSpecType = 0;
/*     */     
/* 173 */     boolean[] tileSpec = null;
/* 174 */     boolean[] compSpec = null;
/*     */ 
/*     */     
/* 177 */     while (stk.hasMoreTokens()) {
/* 178 */       a[][] filter; String word = stk.nextToken();
/*     */       
/* 180 */       switch (word.charAt(0)) {
/*     */         case 'T':
/*     */         case 't':
/* 183 */           tileSpec = a(word, this.j);
/* 184 */           if (curSpecType == 1) {
/* 185 */             curSpecType = 3; continue;
/*     */           } 
/* 187 */           curSpecType = 2;
/*     */           continue;
/*     */         case 'C':
/*     */         case 'c':
/* 191 */           compSpec = a(word, this.k);
/* 192 */           if (curSpecType == 2) {
/* 193 */             curSpecType = 3; continue;
/*     */           } 
/* 195 */           curSpecType = 1;
/*     */           continue;
/*     */         case 'W':
/*     */         case 'w':
/* 199 */           if (wp.getLossless() && word
/* 200 */             .equalsIgnoreCase("w9x7")) {
/* 201 */             throw new IllegalArgumentException("Cannot use non reversible wavelet transform with '-lossless' option");
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 208 */           filter = a(word);
/* 209 */           if (curSpecType == 0) {
/* 210 */             a(filter);
/*     */           }
/* 212 */           else if (curSpecType == 2) {
/* 213 */             for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 214 */               if (tileSpec[i]) {
/* 215 */                 b(i, filter);
/*     */               }
/*     */             } 
/* 218 */           } else if (curSpecType == 1) {
/* 219 */             for (int i = compSpec.length - 1; i >= 0; i--) {
/* 220 */               if (compSpec[i]) {
/* 221 */                 a(i, filter);
/*     */               }
/*     */             } 
/*     */           } else {
/* 225 */             for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 226 */               for (int j = compSpec.length - 1; j >= 0; j--) {
/* 227 */                 if (tileSpec[i] && compSpec[j]) {
/* 228 */                   a(i, j, filter);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 235 */           curSpecType = 0;
/* 236 */           tileSpec = null;
/* 237 */           compSpec = null;
/*     */           continue;
/*     */       } 
/*     */       
/* 241 */       throw new IllegalArgumentException("Bad construction for parameter: " + word);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     if (d() == null) {
/* 248 */       int ndefspec = 0;
/* 249 */       for (int i = nt - 1; i >= 0; i--) {
/* 250 */         for (int j = nc - 1; j >= 0; j--) {
/* 251 */           if (this.l[i][j] == 0) {
/* 252 */             ndefspec++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 259 */       if (ndefspec != 0) {
/* 260 */         if (((String)qts.d()).equals("reversible")) {
/* 261 */           a(a("w5x3"));
/*     */         } else {
/* 263 */           a(a("w9x7"));
/*     */         } 
/*     */       } else {
/*     */         int k, j;
/*     */         
/* 268 */         a(a(0, 0));
/* 269 */         switch (this.l[0][0]) {
/*     */           case 2:
/* 271 */             for (k = nc - 1; k >= 0; k--) {
/* 272 */               if (this.l[0][k] == 2)
/* 273 */                 this.l[0][k] = 0; 
/*     */             } 
/* 275 */             this.o[0] = null;
/*     */             break;
/*     */           case 1:
/* 278 */             for (j = nt - 1; j >= 0; j--) {
/* 279 */               if (this.l[j][0] == 1)
/* 280 */                 this.l[j][0] = 0; 
/*     */             } 
/* 282 */             this.n[0] = null;
/*     */             break;
/*     */           case 3:
/* 285 */             this.l[0][0] = 0;
/* 286 */             this.p.put("t0c0", null);
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 294 */     for (int t = nt - 1; t >= 0; t--) {
/* 295 */       for (int i = nc - 1; i >= 0; i--) {
/*     */         
/* 297 */         if (((String)qts.a(t, i)).equals("reversible")) {
/*     */           
/* 299 */           if (!h(t, i))
/*     */           {
/*     */             
/* 302 */             if (!isFilterSpecified) {
/* 303 */               a(t, i, a("w5x3"));
/*     */             }
/*     */             else {
/*     */               
/* 307 */               throw new IllegalArgumentException("Filter of tile-component (" + t + "," + i + ") does" + " not allow " + "reversible " + "quantization. " + "Specify '-Qtype " + "expounded' or " + "'-Qtype derived'" + "in " + "the command line.");
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 322 */         else if (h(t, i)) {
/*     */ 
/*     */ 
/*     */           
/* 326 */           if (!isFilterSpecified) {
/* 327 */             a(t, i, a("w9x7"));
/*     */           }
/*     */           else {
/*     */             
/* 331 */             throw new IllegalArgumentException("Filter of tile-component (" + t + "," + i + ") does" + " not allow " + "non-reversible " + "quantization. " + "Specify '-Qtype " + "reversible' in " + "the command line");
/*     */           } 
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
/*     */   private a[][] a(String word) {
/* 355 */     a[][] filt = new a[2][1];
/* 356 */     if (word.equalsIgnoreCase("w5x3")) {
/* 357 */       filt[0][0] = new e();
/* 358 */       filt[1][0] = new e();
/* 359 */       return filt;
/*     */     } 
/* 361 */     if (word.equalsIgnoreCase("w9x7")) {
/* 362 */       filt[0][0] = new c();
/* 363 */       filt[1][0] = new c();
/* 364 */       return filt;
/*     */     } 
/*     */     
/* 367 */     throw new IllegalArgumentException("Non JPEG 2000 part I filter: " + word);
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
/*     */   public int e(int t, int c) {
/* 386 */     a[][] an = (a[][])b(t, c);
/* 387 */     return an[0][0].j();
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
/*     */   public a[] f(int t, int c) {
/* 409 */     a[][] an = (a[][])b(t, c);
/* 410 */     return an[0];
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
/*     */   public a[] g(int t, int c) {
/* 432 */     a[][] an = (a[][])b(t, c);
/* 433 */     return an[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 438 */     String str = "";
/*     */ 
/*     */     
/* 441 */     str = str + "nTiles=" + this.j + "\nnComp=" + this.k + "\n\n";
/*     */     
/* 443 */     for (int t = 0; t < this.j; t++) {
/* 444 */       for (int c = 0; c < this.k; c++) {
/* 445 */         a[][] an = (a[][])b(t, c);
/*     */         
/* 447 */         str = str + "(t:" + t + ",c:" + c + ")\n";
/*     */ 
/*     */         
/* 450 */         str = str + "\tH:"; int i;
/* 451 */         for (i = 0; i < (an[0]).length; i++) {
/* 452 */           str = str + " " + an[0][i];
/*     */         }
/* 454 */         str = str + "\n\tV:";
/* 455 */         for (i = 0; i < (an[1]).length; i++)
/* 456 */           str = str + " " + an[1][i]; 
/* 457 */         str = str + "\n";
/*     */       } 
/*     */     } 
/*     */     
/* 461 */     return str;
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
/*     */   public boolean h(int t, int c) {
/* 476 */     a[] hfilter = f(t, c);
/* 477 */     a[] vfilter = g(t, c);
/*     */ 
/*     */     
/* 480 */     for (int i = hfilter.length - 1; i >= 0; i--) {
/* 481 */       if (!hfilter[i].k() || !vfilter[i].k())
/* 482 */         return false; 
/* 483 */     }  return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/a/f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */