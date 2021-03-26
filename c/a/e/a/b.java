/*     */ package c.a.e.a;
/*     */ 
/*     */ import c.a.e.b;
/*     */ import c.a.j.a;
/*     */ import c.a.j.a.a;
/*     */ import c.a.j.a.f;
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
/*     */ public class b
/*     */   extends b
/*     */   implements a
/*     */ {
/*  61 */   private String a = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(int nt, int nc, byte type, f wfs, J2KImageWriteParamJava wp, String values) {
/*  86 */     super(nt, nc, type);
/*     */     
/*  88 */     String param = values;
/*  89 */     this.q = values;
/*  90 */     if (values == null) {
/*     */ 
/*     */       
/*  93 */       if (nc < 3) {
/*  94 */         a("none");
/*     */         
/*     */         return;
/*     */       } 
/*  98 */       if (wp.getLossless()) {
/*  99 */         a("rct");
/*     */         
/*     */         return;
/*     */       } 
/* 103 */       int[] filtType = new int[this.k];
/* 104 */       for (int c = 0; c < 3; c++) {
/* 105 */         a[][] anfilt = (a[][])wfs.e(c);
/* 106 */         filtType[c] = anfilt[0][0].n();
/*     */       } 
/*     */ 
/*     */       
/* 110 */       boolean reject = false; int j;
/* 111 */       for (j = 1; j < 3; j++) {
/* 112 */         if (filtType[j] != filtType[0]) reject = true;
/*     */       
/*     */       } 
/* 115 */       if (reject) {
/* 116 */         a("none");
/*     */       } else {
/* 118 */         a[][] anfilt = (a[][])wfs.e(0);
/* 119 */         if (anfilt[0][0].n() == 0) {
/* 120 */           a("ict");
/*     */         } else {
/* 122 */           a("rct");
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       for (int i = 0; i < nt; i++) {
/*     */         
/* 132 */         int[] arrayOfInt = new int[this.k];
/* 133 */         for (j = 0; j < 3; j++) {
/* 134 */           a[][] anfilt = (a[][])wfs.a(i, j);
/* 135 */           arrayOfInt[j] = anfilt[0][0].n();
/*     */         } 
/*     */ 
/*     */         
/* 139 */         boolean bool = false;
/* 140 */         for (int k = 1; k < this.k; k++) {
/* 141 */           if (arrayOfInt[k] != arrayOfInt[0]) {
/* 142 */             bool = true;
/*     */           }
/*     */         } 
/* 145 */         if (bool) {
/* 146 */           b(i, "none");
/*     */         } else {
/* 148 */           a[][] anfilt = (a[][])wfs.a(i, 0);
/* 149 */           if (anfilt[0][0].n() == 0) {
/* 150 */             b(i, "ict");
/*     */           } else {
/* 152 */             b(i, "rct");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 159 */     if (param.equalsIgnoreCase("true")) {
/* 160 */       param = "on";
/*     */     }
/* 162 */     StringTokenizer stk = new StringTokenizer(param);
/*     */     
/* 164 */     byte curSpecType = 0;
/*     */     
/* 166 */     boolean[] tileSpec = null;
/*     */ 
/*     */ 
/*     */     
/* 170 */     while (stk.hasMoreTokens()) {
/* 171 */       String word = stk.nextToken();
/*     */       
/* 173 */       switch (word.charAt(0)) {
/*     */         case 't':
/* 175 */           tileSpec = a(word, this.j);
/* 176 */           if (curSpecType == 1) {
/* 177 */             curSpecType = 3; continue;
/*     */           } 
/* 179 */           curSpecType = 2;
/*     */           continue;
/*     */         
/*     */         case 'c':
/* 183 */           throw new IllegalArgumentException("Component specific  parameters not allowed with '-Mct' option");
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 188 */       if (word.equals("off")) {
/* 189 */         if (curSpecType == 0) {
/* 190 */           a("none");
/* 191 */         } else if (curSpecType == 2) {
/* 192 */           for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 193 */             if (tileSpec[i])
/* 194 */               b(i, "none"); 
/*     */           } 
/*     */         } 
/* 197 */       } else if (word.equals("on")) {
/* 198 */         if (nc < 3) {
/* 199 */           a("none");
/*     */           
/*     */           continue;
/*     */         } 
/* 203 */         if (curSpecType == 0) {
/*     */ 
/*     */           
/* 206 */           a("rct");
/* 207 */         } else if (curSpecType == 2) {
/* 208 */           for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 209 */             if (tileSpec[i]) {
/* 210 */               if (a(i, wfs) == 1) {
/* 211 */                 b(i, "rct");
/*     */               } else {
/* 213 */                 b(i, "ict");
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } else {
/* 219 */         throw new IllegalArgumentException("Default parameter of option Mct not recognized: " + param);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 225 */       curSpecType = 0;
/* 226 */       tileSpec = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     if (d() == null) {
/*     */ 
/*     */ 
/*     */       
/* 236 */       a("none");
/*     */       
/* 238 */       for (int i = 0; i < nt; i++) {
/* 239 */         if (!h(i)) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 244 */           int[] filtType = new int[this.k];
/* 245 */           for (int c = 0; c < 3; c++) {
/* 246 */             a[][] anfilt = (a[][])wfs.a(i, c);
/* 247 */             filtType[c] = anfilt[0][0].n();
/*     */           } 
/*     */ 
/*     */           
/* 251 */           boolean reject = false;
/* 252 */           for (int j = 1; j < this.k; j++) {
/* 253 */             if (filtType[j] != filtType[0]) {
/* 254 */               reject = true;
/*     */             }
/*     */           } 
/* 257 */           if (reject) {
/* 258 */             b(i, "none");
/*     */           } else {
/* 260 */             a[][] anfilt = (a[][])wfs.a(i, 0);
/* 261 */             if (anfilt[0][0].n() == 0) {
/* 262 */               b(i, "ict");
/*     */             } else {
/* 264 */               b(i, "rct");
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 272 */     for (int t = nt - 1; t >= 0; t--) {
/*     */       
/* 274 */       if (!((String)f(t)).equals("none"))
/*     */       {
/*     */ 
/*     */         
/* 278 */         if (((String)f(t)).equals("rct")) {
/*     */           
/* 280 */           int filterType = a(t, wfs);
/* 281 */           switch (filterType) {
/*     */             case 1:
/*     */               break;
/*     */             case 0:
/* 285 */               if (h(t))
/*     */               {
/* 287 */                 throw new IllegalArgumentException("Cannot use RCT with 9x7 filter in tile " + t);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 292 */               b(t, "ict");
/*     */               break;
/*     */             
/*     */             default:
/* 296 */               throw new IllegalArgumentException("Default filter is not JPEG 2000 part I compliant");
/*     */           } 
/*     */ 
/*     */         
/*     */         } else {
/* 301 */           int filterType = a(t, wfs);
/* 302 */           switch (filterType) {
/*     */             case 1:
/* 304 */               if (h(t))
/*     */               {
/* 306 */                 throw new IllegalArgumentException("Cannot use ICT with filter 5x3 in tile " + t);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 311 */               b(t, "rct");
/*     */               break;
/*     */             
/*     */             case 0:
/*     */               break;
/*     */             default:
/* 317 */               throw new IllegalArgumentException("Default filter is not JPEG 2000 part I compliant");
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
/*     */   private int a(int t, f wfs) {
/* 339 */     int[] filtType = new int[this.k];
/* 340 */     for (int c = 0; c < this.k; c++) {
/* 341 */       a[][] anfilt; if (t == -1) {
/* 342 */         anfilt = (a[][])wfs.e(c);
/*     */       } else {
/* 344 */         anfilt = (a[][])wfs.a(t, c);
/* 345 */       }  filtType[c] = anfilt[0][0].n();
/*     */     } 
/*     */ 
/*     */     
/* 349 */     boolean reject = false;
/* 350 */     for (int i = 1; i < this.k; i++) {
/* 351 */       if (filtType[i] != filtType[0])
/* 352 */         reject = true; 
/*     */     } 
/* 354 */     if (reject) {
/* 355 */       throw new IllegalArgumentException("Can not use component transformation when components do not use the same filters");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 360 */     return filtType[0];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/a/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */