/*     */ package c.a.c;
/*     */ 
/*     */ import c.a.a;
/*     */ import c.a.d;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
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
/*     */ public class e
/*     */   extends d
/*     */ {
/*     */   public e(int nt, int nc, byte type) {
/*  77 */     super(nt, nc, type);
/*  78 */     if (type != 1) {
/*  79 */       throw new Error("Illegal use of class ProgressionSpec !");
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
/*     */   public e(int nt, int nc, int nl, a dls, byte type, J2KImageWriteParamJava wp, String values) {
/* 102 */     super(nt, nc, type);
/*     */     
/* 104 */     this.q = values;
/*     */     
/* 106 */     String param = values;
/*     */     
/* 108 */     int mode = -1;
/*     */     
/* 110 */     if (values == null) {
/* 111 */       if (wp.getROIs() == null) {
/* 112 */         mode = a("res");
/*     */       } else {
/*     */         
/* 115 */         mode = a("layer");
/*     */       } 
/*     */       
/* 118 */       if (mode == -1) {
/* 119 */         String str = "Unknown progression type : '" + param + "'";
/* 120 */         throw new IllegalArgumentException(str);
/*     */       } 
/* 122 */       d[] arrayOfD = new d[1];
/* 123 */       arrayOfD[0] = new d(mode, 0, nc, 0, dls.a() + 1, nl);
/* 124 */       a(arrayOfD);
/*     */       
/*     */       return;
/*     */     } 
/* 128 */     StringTokenizer stk = new StringTokenizer(param);
/* 129 */     byte curSpecType = 0;
/*     */     
/* 131 */     boolean[] tileSpec = null;
/* 132 */     String word = null;
/* 133 */     String errMsg = null;
/* 134 */     boolean needInteger = false;
/* 135 */     int intType = 0;
/*     */ 
/*     */ 
/*     */     
/* 139 */     Vector<d> progression = new Vector();
/* 140 */     int tmp = 0;
/* 141 */     d curProg = null;
/*     */     
/* 143 */     while (stk.hasMoreTokens()) {
/* 144 */       word = stk.nextToken();
/*     */       
/* 146 */       switch (word.charAt(0)) {
/*     */         
/*     */         case 't':
/* 149 */           if (progression.size() > 0) {
/*     */             
/* 151 */             curProg.c = nc;
/* 152 */             curProg.f = nl;
/* 153 */             curProg.e = dls.a() + 1;
/* 154 */             d[] arrayOfD = new d[progression.size()];
/* 155 */             progression.copyInto((Object[])arrayOfD);
/* 156 */             if (curSpecType == 0) {
/* 157 */               a(arrayOfD);
/*     */             }
/* 159 */             else if (curSpecType == 2) {
/* 160 */               for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 161 */                 if (tileSpec[i])
/* 162 */                   b(i, arrayOfD); 
/*     */               } 
/*     */             } 
/*     */           } 
/* 166 */           progression.removeAllElements();
/* 167 */           intType = -1;
/* 168 */           needInteger = false;
/*     */ 
/*     */           
/* 171 */           tileSpec = a(word, this.j);
/* 172 */           curSpecType = 2;
/*     */           continue;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 178 */       if (needInteger) {
/*     */         try {
/* 180 */           tmp = (new Integer(word)).intValue();
/*     */         }
/* 182 */         catch (NumberFormatException numberFormatException) {
/*     */           
/* 184 */           throw new IllegalArgumentException("Progression order specification has missing parameters: " + param);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 192 */         switch (intType) {
/*     */           case 0:
/* 194 */             if (tmp < 0 || tmp > dls.a() + 1) {
/* 195 */               throw new IllegalArgumentException("Invalid comp_start in '-Aptype' option");
/*     */             }
/*     */             
/* 198 */             curProg.b = tmp; break;
/*     */           case 1:
/* 200 */             if (tmp < 0 || tmp > nc) {
/* 201 */               throw new IllegalArgumentException("Invalid res_start in '-Aptype' option");
/*     */             }
/*     */ 
/*     */             
/* 205 */             curProg.d = tmp; break;
/*     */           case 2:
/* 207 */             if (tmp < 0) {
/* 208 */               throw new IllegalArgumentException("Invalid layer_end in '-Aptype' option");
/*     */             }
/*     */             
/* 211 */             if (tmp > nl) {
/* 212 */               tmp = nl;
/*     */             }
/* 214 */             curProg.f = tmp; break;
/*     */           case 3:
/* 216 */             if (tmp < 0) {
/* 217 */               throw new IllegalArgumentException("Invalid comp_end in '-Aptype' option");
/*     */             }
/*     */             
/* 220 */             if (tmp > dls.a() + 1) {
/* 221 */               tmp = dls.a() + 1;
/*     */             }
/* 223 */             curProg.c = tmp; break;
/*     */           case 4:
/* 225 */             if (tmp < 0) {
/* 226 */               throw new IllegalArgumentException("Invalid res_end in '-Aptype' option");
/*     */             }
/*     */             
/* 229 */             if (tmp > nc) {
/* 230 */               tmp = nc;
/*     */             }
/* 232 */             curProg.e = tmp;
/*     */             break;
/*     */         } 
/* 235 */         if (intType < 4) {
/* 236 */           intType++;
/* 237 */           needInteger = true;
/*     */           continue;
/*     */         } 
/* 240 */         if (intType == 4) {
/* 241 */           intType = 0;
/* 242 */           needInteger = false;
/*     */           
/*     */           continue;
/*     */         } 
/* 246 */         throw new Error("Error in usage of 'Aptype' option: " + param);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 251 */       if (!needInteger) {
/* 252 */         mode = a(word);
/* 253 */         if (mode == -1) {
/* 254 */           errMsg = "Unknown progression type : '" + word + "'";
/* 255 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/* 257 */         needInteger = true;
/* 258 */         intType = 0;
/* 259 */         if (progression.size() == 0) {
/* 260 */           curProg = new d(mode, 0, nc, 0, dls.a() + 1, nl);
/*     */         } else {
/*     */           
/* 263 */           curProg = new d(mode, 0, nc, 0, dls.a() + 1, nl);
/*     */         } 
/*     */         
/* 266 */         progression.addElement(curProg);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 271 */     if (progression.size() == 0) {
/*     */       
/* 273 */       if (wp.getROIs() == null) {
/* 274 */         mode = a("res");
/*     */       } else {
/*     */         
/* 277 */         mode = a("layer");
/*     */       } 
/*     */       
/* 280 */       if (mode == -1) {
/* 281 */         errMsg = "Unknown progression type : '" + param + "'";
/* 282 */         throw new IllegalArgumentException(errMsg);
/*     */       } 
/* 284 */       d[] arrayOfD = new d[1];
/* 285 */       arrayOfD[0] = new d(mode, 0, nc, 0, dls.a() + 1, nl);
/* 286 */       a(arrayOfD);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 291 */     curProg.c = nc;
/* 292 */     curProg.f = nl;
/* 293 */     curProg.e = dls.a() + 1;
/*     */ 
/*     */     
/* 296 */     d[] prog = new d[progression.size()];
/* 297 */     progression.copyInto((Object[])prog);
/*     */     
/* 299 */     if (curSpecType == 0) {
/* 300 */       a(prog);
/*     */     }
/* 302 */     else if (curSpecType == 2) {
/* 303 */       for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 304 */         if (tileSpec[i]) {
/* 305 */           b(i, prog);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 310 */     if (d() == null) {
/* 311 */       int ndefspec = 0;
/* 312 */       for (int t = nt - 1; t >= 0; t--) {
/* 313 */         for (int c = nc - 1; c >= 0; c--) {
/* 314 */           if (this.l[t][c] == 0) {
/* 315 */             ndefspec++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 322 */       if (ndefspec != 0) {
/* 323 */         if (wp.getROIs() == null) {
/* 324 */           mode = a("res");
/*     */         } else {
/* 326 */           mode = a("layer");
/*     */         } 
/* 328 */         if (mode == -1) {
/* 329 */           errMsg = "Unknown progression type : '" + param + "'";
/* 330 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/* 332 */         prog = new d[1];
/* 333 */         prog[0] = new d(mode, 0, nc, 0, dls.a() + 1, nl);
/* 334 */         a(prog);
/*     */       } else {
/*     */         int c, i;
/*     */ 
/*     */         
/* 339 */         a(a(0, 0));
/* 340 */         switch (this.l[0][0]) {
/*     */           case 2:
/* 342 */             for (c = nc - 1; c >= 0; c--) {
/* 343 */               if (this.l[0][c] == 2)
/* 344 */                 this.l[0][c] = 0; 
/*     */             } 
/* 346 */             this.o[0] = null;
/*     */             break;
/*     */           case 1:
/* 349 */             for (i = nt - 1; i >= 0; i--) {
/* 350 */               if (this.l[i][0] == 1)
/* 351 */                 this.l[i][0] = 0; 
/*     */             } 
/* 353 */             this.n[0] = null;
/*     */             break;
/*     */           case 3:
/* 356 */             this.l[0][0] = 0;
/* 357 */             this.p.put("t0c0", null);
/*     */             break;
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
/*     */   private int a(String mode) {
/* 376 */     if (mode.equals("res")) {
/* 377 */       return 1;
/*     */     }
/* 379 */     if (mode.equals("layer")) {
/* 380 */       return 0;
/*     */     }
/* 382 */     if (mode.equals("pos-comp")) {
/* 383 */       return 3;
/*     */     }
/* 385 */     if (mode.equals("comp-pos")) {
/* 386 */       return 4;
/*     */     }
/* 388 */     if (mode.equals("res-pos")) {
/* 389 */       return 2;
/*     */     }
/*     */ 
/*     */     
/* 393 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/e.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */