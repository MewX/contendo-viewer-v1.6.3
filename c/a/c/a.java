/*     */ package c.a.c;
/*     */ 
/*     */ import c.a.d;
/*     */ import c.a.i.e;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class a
/*     */   extends d
/*     */ {
/*  60 */   private String a = "64 64";
/*     */ 
/*     */   
/*     */   private static final String r = "Cblksiz";
/*     */ 
/*     */   
/*  66 */   private int s = 0;
/*     */ 
/*     */   
/*  69 */   private int t = 0;
/*     */ 
/*     */ 
/*     */ 
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
/*  83 */     super(nt, nc, type);
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
/*     */   public a(int nt, int nc, byte type, J2KImageWriteParamJava wp, String values) {
/* 102 */     super(nt, nc, type);
/*     */     
/* 104 */     boolean firstVal = true;
/* 105 */     this.q = values;
/*     */     
/* 107 */     String param = values;
/* 108 */     if (param == null) {
/* 109 */       param = this.a;
/*     */     }
/*     */ 
/*     */     
/* 113 */     StringTokenizer stk = new StringTokenizer(param);
/* 114 */     byte curSpecType = 0;
/*     */     
/* 116 */     boolean[] tileSpec = null;
/* 117 */     boolean[] compSpec = null;
/*     */     
/* 119 */     String word = null;
/* 120 */     String errMsg = null;
/*     */     
/* 122 */     while (stk.hasMoreTokens()) {
/* 123 */       int ci; word = stk.nextToken();
/*     */       
/* 125 */       switch (word.charAt(0)) {
/*     */         
/*     */         case 't':
/* 128 */           tileSpec = a(word, this.j);
/* 129 */           if (curSpecType == 1) {
/* 130 */             curSpecType = 3;
/*     */             continue;
/*     */           } 
/* 133 */           curSpecType = 2;
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 'c':
/* 138 */           compSpec = a(word, this.k);
/* 139 */           if (curSpecType == 2) {
/* 140 */             curSpecType = 3;
/*     */             continue;
/*     */           } 
/* 143 */           curSpecType = 1;
/*     */           continue;
/*     */       } 
/*     */ 
/*     */       
/* 148 */       if (!Character.isDigit(word.charAt(0))) {
/* 149 */         errMsg = "Bad construction for parameter: " + word;
/* 150 */         throw new IllegalArgumentException(errMsg);
/*     */       } 
/* 152 */       Integer[] dim = new Integer[2];
/*     */       
/*     */       try {
/* 155 */         dim[0] = new Integer(word);
/*     */ 
/*     */         
/* 158 */         if (dim[0].intValue() > 1024) {
/* 159 */           errMsg = "'Cblksiz' option : the code-block's width cannot be greater than 1024";
/*     */ 
/*     */           
/* 162 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/*     */ 
/*     */         
/* 166 */         if (dim[0].intValue() < 4) {
/* 167 */           errMsg = "'Cblksiz' option : the code-block's width cannot be less than 4";
/*     */ 
/*     */           
/* 170 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/*     */         
/* 173 */         if (dim[0].intValue() != 1 << 
/* 174 */           e.a(dim[0].intValue())) {
/* 175 */           errMsg = "'Cblksiz' option : the code-block's width must be a power of 2";
/*     */           
/* 177 */           throw new IllegalArgumentException(errMsg);
/*     */         }
/*     */       
/* 180 */       } catch (NumberFormatException e) {
/* 181 */         errMsg = "'Cblksiz' option : the code-block's width could not be parsed.";
/*     */         
/* 183 */         throw new IllegalArgumentException(errMsg);
/*     */       } 
/*     */       
/*     */       try {
/* 187 */         word = stk.nextToken();
/*     */       }
/* 189 */       catch (NoSuchElementException e) {
/* 190 */         errMsg = "'Cblksiz' option : could not parse the code-block's height";
/*     */         
/* 192 */         throw new IllegalArgumentException(errMsg);
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 197 */         dim[1] = new Integer(word);
/*     */ 
/*     */         
/* 200 */         if (dim[1].intValue() > 1024) {
/* 201 */           errMsg = "'Cblksiz' option : the code-block's height cannot be greater than 1024";
/*     */ 
/*     */           
/* 204 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/*     */ 
/*     */         
/* 208 */         if (dim[1].intValue() < 4) {
/* 209 */           errMsg = "'Cblksiz' option : the code-block's height cannot be less than 4";
/*     */ 
/*     */           
/* 212 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/*     */         
/* 215 */         if (dim[1].intValue() != 1 << 
/* 216 */           e.a(dim[1].intValue())) {
/* 217 */           errMsg = "'Cblksiz' option : the code-block's height must be a power of 2";
/*     */           
/* 219 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/*     */ 
/*     */         
/* 223 */         if (dim[0].intValue() * dim[1].intValue() > 4096)
/*     */         {
/*     */           
/* 226 */           errMsg = "'Cblksiz' option : The code-block's area (i.e. width*height) cannot be greater than 4096";
/*     */ 
/*     */ 
/*     */           
/* 230 */           throw new IllegalArgumentException(errMsg);
/*     */         }
/*     */       
/* 233 */       } catch (NumberFormatException e) {
/* 234 */         errMsg = "'Cblksiz' option : the code-block's height could not be parsed.";
/*     */         
/* 236 */         throw new IllegalArgumentException(errMsg);
/*     */       } 
/*     */ 
/*     */       
/* 240 */       if (dim[0].intValue() > this.s) {
/* 241 */         this.s = dim[0].intValue();
/*     */       }
/*     */       
/* 244 */       if (dim[1].intValue() > this.t) {
/* 245 */         this.t = dim[1].intValue();
/*     */       }
/*     */       
/* 248 */       if (firstVal) {
/*     */ 
/*     */         
/* 251 */         a(dim);
/* 252 */         firstVal = false;
/*     */       } 
/*     */       
/* 255 */       switch (curSpecType) {
/*     */         case 0:
/* 257 */           a(dim);
/*     */           continue;
/*     */         case 2:
/* 260 */           for (ti = tileSpec.length - 1; ti >= 0; ti--) {
/* 261 */             if (tileSpec[ti]) {
/* 262 */               b(ti, dim);
/*     */             }
/*     */           } 
/*     */           continue;
/*     */         case 1:
/* 267 */           for (ci = compSpec.length - 1; ci >= 0; ci--) {
/* 268 */             if (compSpec[ci]) {
/* 269 */               a(ci, dim);
/*     */             }
/*     */           } 
/*     */           continue;
/*     */       } 
/* 274 */       for (int ti = tileSpec.length - 1; ti >= 0; ti--) {
/* 275 */         for (ci = compSpec.length - 1; ci >= 0; ci--) {
/* 276 */           if (tileSpec[ti] && compSpec[ci]) {
/* 277 */             a(ti, ci, dim);
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
/*     */   public int a() {
/* 292 */     return this.s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 300 */     return this.t;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte type, int t, int c) {
/* 335 */     Integer[] dim = null;
/* 336 */     switch (type) {
/*     */       case 0:
/* 338 */         dim = (Integer[])d();
/*     */         break;
/*     */       case 1:
/* 341 */         dim = (Integer[])e(c);
/*     */         break;
/*     */       case 2:
/* 344 */         dim = (Integer[])f(t);
/*     */         break;
/*     */       case 3:
/* 347 */         dim = (Integer[])a(t, c); break;
/*     */     } 
/* 349 */     return dim[0].intValue();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(byte type, int t, int c) {
/* 384 */     Integer[] dim = null;
/* 385 */     switch (type) {
/*     */       case 0:
/* 387 */         dim = (Integer[])d();
/*     */         break;
/*     */       case 1:
/* 390 */         dim = (Integer[])e(c);
/*     */         break;
/*     */       case 2:
/* 393 */         dim = (Integer[])f(t);
/*     */         break;
/*     */       case 3:
/* 396 */         dim = (Integer[])a(t, c); break;
/*     */     } 
/* 398 */     return dim[1].intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Object value) {
/* 407 */     super.a(value);
/*     */ 
/*     */     
/* 410 */     a((Integer[])value);
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
/*     */   public void b(int t, Object value) {
/* 422 */     super.b(t, value);
/*     */ 
/*     */     
/* 425 */     a((Integer[])value);
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
/*     */   public void a(int c, Object value) {
/* 437 */     super.a(c, value);
/*     */ 
/*     */     
/* 440 */     a((Integer[])value);
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
/*     */   public void a(int t, int c, Object value) {
/* 453 */     super.a(t, c, value);
/*     */ 
/*     */     
/* 456 */     a((Integer[])value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(Integer[] dim) {
/* 467 */     if (dim[0].intValue() > this.s) {
/* 468 */       this.s = dim[0].intValue();
/*     */     }
/* 470 */     if (dim[1].intValue() > this.t)
/* 471 */       this.t = dim[1].intValue(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */