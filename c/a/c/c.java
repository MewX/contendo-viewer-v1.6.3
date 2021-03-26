/*     */ package c.a.c;
/*     */ 
/*     */ import c.a.a;
/*     */ import c.a.d;
/*     */ import c.a.e.a;
/*     */ import c.a.i.e;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends d
/*     */ {
/*     */   private static final String a = "Cpp";
/*     */   private a r;
/*     */   
/*     */   public c(int nt, int nc, byte type, a dls) {
/*  91 */     super(nt, nc, type);
/*  92 */     this.r = dls;
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
/*     */   public c(int nt, int nc, byte type, a imgsrc, a dls, J2KImageWriteParamJava wp, String values) {
/* 112 */     super(nt, nc, type);
/*     */     
/* 114 */     this.r = dls;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     boolean wasReadingPrecinctSize = false;
/*     */     
/* 132 */     String param = values;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     Vector[] tmpv = new Vector[2];
/* 140 */     tmpv[0] = new Vector();
/* 141 */     tmpv[0].addElement(new Integer(65535));
/* 142 */     tmpv[1] = new Vector();
/* 143 */     tmpv[1].addElement(new Integer(65535));
/* 144 */     a(tmpv);
/*     */     
/* 146 */     if (param == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     StringTokenizer stk = new StringTokenizer(param);
/* 154 */     byte curSpecType = 0;
/*     */     
/* 156 */     boolean[] tileSpec = null;
/* 157 */     boolean[] compSpec = null;
/*     */ 
/*     */     
/* 160 */     boolean endOfParamList = false;
/* 161 */     String word = null;
/*     */     
/* 163 */     String errMsg = null;
/*     */     
/* 165 */     while ((stk.hasMoreTokens() || wasReadingPrecinctSize) && !endOfParamList) {
/*     */ 
/*     */       
/* 168 */       Vector[] v = new Vector[2];
/*     */ 
/*     */ 
/*     */       
/* 172 */       if (!wasReadingPrecinctSize) {
/* 173 */         word = stk.nextToken();
/*     */       }
/*     */       
/* 176 */       wasReadingPrecinctSize = false;
/*     */       
/* 178 */       switch (word.charAt(0)) {
/*     */         
/*     */         case 't':
/* 181 */           tileSpec = a(word, this.j);
/* 182 */           if (curSpecType == 1) {
/* 183 */             curSpecType = 3;
/*     */             continue;
/*     */           } 
/* 186 */           curSpecType = 2;
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 'c':
/* 191 */           compSpec = a(word, this.k);
/* 192 */           if (curSpecType == 2) {
/* 193 */             curSpecType = 3;
/*     */             continue;
/*     */           } 
/* 196 */           curSpecType = 1;
/*     */           continue;
/*     */       } 
/*     */ 
/*     */       
/* 201 */       if (!Character.isDigit(word.charAt(0))) {
/* 202 */         errMsg = "Bad construction for parameter: " + word;
/* 203 */         throw new IllegalArgumentException(errMsg);
/*     */       } 
/*     */ 
/*     */       
/* 207 */       v[0] = new Vector();
/* 208 */       v[1] = new Vector();
/*     */ 
/*     */       
/*     */       while (true) {
/*     */         Integer w, h;
/*     */         
/*     */         try {
/* 215 */           w = new Integer(word);
/*     */ 
/*     */           
/*     */           try {
/* 219 */             word = stk.nextToken();
/*     */           }
/* 221 */           catch (NoSuchElementException e) {
/* 222 */             errMsg = "'Cpp' option : could not parse the precinct's width";
/*     */             
/* 224 */             throw new IllegalArgumentException(errMsg);
/*     */           } 
/*     */ 
/*     */           
/* 228 */           h = new Integer(word);
/* 229 */           if (w.intValue() != 1 << e.a(w.intValue()) || h
/* 230 */             .intValue() != 1 << 
/* 231 */             e.a(h.intValue())) {
/* 232 */             errMsg = "Precinct dimensions must be powers of 2";
/* 233 */             throw new IllegalArgumentException(errMsg);
/*     */           }
/*     */         
/* 236 */         } catch (NumberFormatException e) {
/* 237 */           errMsg = "'Cpp' option : the argument '" + word + "' could not be parsed.";
/*     */           
/* 239 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/*     */         
/* 242 */         v[0].addElement(w);
/* 243 */         v[1].addElement(h);
/*     */ 
/*     */         
/* 246 */         if (stk.hasMoreTokens()) {
/* 247 */           word = stk.nextToken();
/* 248 */           if (!Character.isDigit(word.charAt(0))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 254 */             wasReadingPrecinctSize = true;
/*     */             
/* 256 */             if (curSpecType == 0) {
/* 257 */               a(v); continue;
/*     */             } 
/* 259 */             if (curSpecType == 2) {
/* 260 */               for (int ti = tileSpec.length - 1; ti >= 0; ti--) {
/* 261 */                 if (tileSpec[ti]) {
/* 262 */                   b(ti, v);
/*     */                 }
/*     */               }
/*     */             
/* 266 */             } else if (curSpecType == 1) {
/* 267 */               for (int ci = compSpec.length - 1; ci >= 0; ci--) {
/* 268 */                 if (compSpec[ci]) {
/* 269 */                   a(ci, v);
/*     */                 }
/*     */               } 
/*     */             } else {
/*     */               
/* 274 */               for (int ti = tileSpec.length - 1; ti >= 0; ti--) {
/* 275 */                 for (int ci = compSpec.length - 1; ci >= 0; ci--) {
/* 276 */                   if (tileSpec[ti] && compSpec[ci]) {
/* 277 */                     a(ti, ci, v);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 283 */             curSpecType = 0;
/* 284 */             tileSpec = null;
/* 285 */             compSpec = null;
/*     */           } 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */ 
/*     */       
/* 297 */       if (curSpecType == 0) {
/* 298 */         a(v);
/*     */       }
/* 300 */       else if (curSpecType == 2) {
/* 301 */         for (int ti = tileSpec.length - 1; ti >= 0; ti--) {
/* 302 */           if (tileSpec[ti]) {
/* 303 */             b(ti, v);
/*     */           }
/*     */         }
/*     */       
/* 307 */       } else if (curSpecType == 1) {
/* 308 */         for (int ci = compSpec.length - 1; ci >= 0; ci--) {
/* 309 */           if (compSpec[ci]) {
/* 310 */             a(ci, v);
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 315 */         for (int ti = tileSpec.length - 1; ti >= 0; ti--) {
/* 316 */           for (int ci = compSpec.length - 1; ci >= 0; ci--) {
/* 317 */             if (tileSpec[ti] && compSpec[ci]) {
/* 318 */               a(ti, ci, v);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 323 */       endOfParamList = true;
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
/*     */   public int a(int t, int i, int rl) {
/*     */     int mrl;
/* 351 */     Vector[] v = null;
/* 352 */     boolean tileSpecified = (t != -1);
/* 353 */     boolean compSpecified = (i != -1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     if (tileSpecified && compSpecified) {
/* 359 */       mrl = ((Integer)this.r.a(t, i)).intValue();
/* 360 */       v = (Vector[])a(t, i);
/*     */     }
/* 362 */     else if (tileSpecified && !compSpecified) {
/* 363 */       mrl = ((Integer)this.r.f(t)).intValue();
/* 364 */       v = (Vector[])f(t);
/*     */     }
/* 366 */     else if (!tileSpecified && compSpecified) {
/* 367 */       mrl = ((Integer)this.r.e(i)).intValue();
/* 368 */       v = (Vector[])e(i);
/*     */     } else {
/*     */       
/* 371 */       mrl = ((Integer)this.r.d()).intValue();
/* 372 */       v = (Vector[])d();
/*     */     } 
/* 374 */     int idx = mrl - rl;
/* 375 */     if (v[0].size() > idx) {
/* 376 */       return ((Integer)v[0].elementAt(idx)).intValue();
/*     */     }
/*     */     
/* 379 */     return ((Integer)v[0].elementAt(v[0].size() - 1)).intValue();
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
/*     */   public int b(int t, int i, int rl) {
/*     */     int mrl;
/* 402 */     Vector[] v = null;
/* 403 */     boolean tileSpecified = (t != -1);
/* 404 */     boolean compSpecified = (i != -1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 409 */     if (tileSpecified && compSpecified) {
/* 410 */       mrl = ((Integer)this.r.a(t, i)).intValue();
/* 411 */       v = (Vector[])a(t, i);
/*     */     }
/* 413 */     else if (tileSpecified && !compSpecified) {
/* 414 */       mrl = ((Integer)this.r.f(t)).intValue();
/* 415 */       v = (Vector[])f(t);
/*     */     }
/* 417 */     else if (!tileSpecified && compSpecified) {
/* 418 */       mrl = ((Integer)this.r.e(i)).intValue();
/* 419 */       v = (Vector[])e(i);
/*     */     } else {
/*     */       
/* 422 */       mrl = ((Integer)this.r.d()).intValue();
/* 423 */       v = (Vector[])d();
/*     */     } 
/* 425 */     int idx = mrl - rl;
/* 426 */     if (v[1].size() > idx) {
/* 427 */       return ((Integer)v[1].elementAt(idx)).intValue();
/*     */     }
/*     */     
/* 430 */     return ((Integer)v[1].elementAt(v[1].size() - 1)).intValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */