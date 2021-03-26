/*     */ package c.a;
/*     */ 
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
/*     */ public class a
/*     */   extends d
/*     */ {
/*  63 */   protected static int a = Integer.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(int nt, int nc, byte type) {
/*  79 */     super(nt, nc, type);
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
/*     */   public a(int nt, int nc, byte type, J2KImageWriteParamJava wp, String values, String defaultValue) {
/*  99 */     super(nt, nc, type);
/*     */     
/* 101 */     if (values == null) {
/*     */       try {
/* 103 */         a(new Integer(defaultValue));
/*     */       }
/* 105 */       catch (NumberFormatException e) {
/* 106 */         throw new IllegalArgumentException("Non recognized value for option -: " + defaultValue);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 116 */     StringTokenizer stk = new StringTokenizer(values);
/*     */     
/* 118 */     byte curSpecType = 0;
/*     */     
/* 120 */     boolean[] tileSpec = null;
/* 121 */     boolean[] compSpec = null;
/*     */     
/* 123 */     while (stk.hasMoreTokens()) {
/* 124 */       Integer value; String word = stk.nextToken();
/*     */       
/* 126 */       switch (word.charAt(0)) {
/*     */         case 't':
/* 128 */           tileSpec = a(word, this.j);
/* 129 */           if (curSpecType == 1) {
/* 130 */             curSpecType = 3; continue;
/*     */           } 
/* 132 */           curSpecType = 2;
/*     */           continue;
/*     */         case 'c':
/* 135 */           compSpec = a(word, this.k);
/* 136 */           if (curSpecType == 2) {
/* 137 */             curSpecType = 3; continue;
/*     */           } 
/* 139 */           curSpecType = 1;
/*     */           continue;
/*     */       } 
/*     */       try {
/* 143 */         value = new Integer(word);
/*     */       }
/* 145 */       catch (NumberFormatException e) {
/* 146 */         throw new IllegalArgumentException("Non recognized value for option -: " + word);
/*     */       } 
/*     */ 
/*     */       
/* 150 */       if (curSpecType == 0) {
/* 151 */         a(value);
/*     */       }
/* 153 */       else if (curSpecType == 2) {
/* 154 */         for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 155 */           if (tileSpec[i]) {
/* 156 */             b(i, value);
/*     */           }
/*     */         } 
/* 159 */       } else if (curSpecType == 1) {
/* 160 */         for (int i = compSpec.length - 1; i >= 0; i--) {
/* 161 */           if (compSpec[i]) {
/* 162 */             a(i, value);
/*     */           }
/*     */         } 
/*     */       } else {
/* 166 */         for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 167 */           for (int j = compSpec.length - 1; j >= 0; j--) {
/* 168 */             if (tileSpec[i] && compSpec[j]) {
/* 169 */               a(i, j, value);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 176 */       curSpecType = 0;
/* 177 */       tileSpec = null;
/* 178 */       compSpec = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     if (d() == null) {
/* 185 */       int ndefspec = 0;
/* 186 */       for (int t = nt - 1; t >= 0; t--) {
/* 187 */         for (int c = nc - 1; c >= 0; c--) {
/* 188 */           if (this.l[t][c] == 0) {
/* 189 */             ndefspec++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 196 */       if (ndefspec != 0) {
/*     */         try {
/* 198 */           a(new Integer(defaultValue));
/*     */         }
/* 200 */         catch (NumberFormatException e) {
/* 201 */           throw new IllegalArgumentException("Non recognized value for option - : " + defaultValue);
/*     */         } 
/*     */       } else {
/*     */         int c, i;
/*     */ 
/*     */ 
/*     */         
/* 208 */         a(a(0, 0));
/* 209 */         switch (this.l[0][0]) {
/*     */           case 2:
/* 211 */             for (c = nc - 1; c >= 0; c--) {
/* 212 */               if (this.l[0][c] == 2)
/* 213 */                 this.l[0][c] = 0; 
/*     */             } 
/* 215 */             this.o[0] = null;
/*     */             break;
/*     */           case 1:
/* 218 */             for (i = nt - 1; i >= 0; i--) {
/* 219 */               if (this.l[i][0] == 1)
/* 220 */                 this.l[i][0] = 0; 
/*     */             } 
/* 222 */             this.n[0] = null;
/*     */             break;
/*     */           case 3:
/* 225 */             this.l[0][0] = 0;
/* 226 */             this.p.put("t0c0", null);
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
/*     */   public int a() {
/* 240 */     int max = ((Integer)this.R_).intValue();
/*     */ 
/*     */     
/* 243 */     for (int t = 0; t < this.j; t++) {
/* 244 */       for (int c = 0; c < this.k; c++) {
/* 245 */         int tmp = ((Integer)b(t, c)).intValue();
/* 246 */         if (max < tmp) {
/* 247 */           max = tmp;
/*     */         }
/*     */       } 
/*     */     } 
/* 251 */     return max;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 261 */     int min = ((Integer)this.R_).intValue();
/*     */ 
/*     */     
/* 264 */     for (int t = 0; t < this.j; t++) {
/* 265 */       for (int c = 0; c < this.k; c++) {
/* 266 */         int tmp = ((Integer)b(t, c)).intValue();
/* 267 */         if (min > tmp) {
/* 268 */           min = tmp;
/*     */         }
/*     */       } 
/*     */     } 
/* 272 */     return min;
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
/*     */   public int a(int c) {
/* 284 */     int max = 0;
/*     */ 
/*     */     
/* 287 */     for (int t = 0; t < this.j; t++) {
/* 288 */       int tmp = ((Integer)b(t, c)).intValue();
/* 289 */       if (max < tmp) {
/* 290 */         max = tmp;
/*     */       }
/*     */     } 
/* 293 */     return max;
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
/*     */   public int b(int c) {
/* 305 */     int min = a;
/*     */ 
/*     */     
/* 308 */     for (int t = 0; t < this.j; t++) {
/* 309 */       int tmp = ((Integer)b(t, c)).intValue();
/* 310 */       if (min > tmp) {
/* 311 */         min = tmp;
/*     */       }
/*     */     } 
/* 314 */     return min;
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
/*     */   public int c(int t) {
/* 326 */     int max = 0;
/*     */ 
/*     */     
/* 329 */     for (int c = 0; c < this.k; c++) {
/* 330 */       int tmp = ((Integer)b(t, c)).intValue();
/* 331 */       if (max < tmp) {
/* 332 */         max = tmp;
/*     */       }
/*     */     } 
/* 335 */     return max;
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
/*     */   public int d(int t) {
/* 347 */     int min = a;
/*     */ 
/*     */     
/* 350 */     for (int c = 0; c < this.k; c++) {
/* 351 */       int tmp = ((Integer)b(t, c)).intValue();
/* 352 */       if (min > tmp) {
/* 353 */         min = tmp;
/*     */       }
/*     */     } 
/* 356 */     return min;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */