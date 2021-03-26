/*     */ package c.a.g;
/*     */ 
/*     */ import c.a.d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends d
/*     */ {
/*     */   public c(int nt, int nc, byte type) {
/*  81 */     super(nt, nc, type);
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
/*     */   public c(int nt, int nc, byte type, J2KImageWriteParamJava wp, String values) {
/*  98 */     super(nt, nc, type);
/*     */     
/* 100 */     if (values == null) {
/* 101 */       if (wp.getLossless()) {
/* 102 */         a("reversible");
/*     */       } else {
/* 104 */         a("expounded");
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     this.q = values;
/* 110 */     String param = values;
/*     */     
/* 112 */     StringTokenizer stk = new StringTokenizer(param);
/*     */     
/* 114 */     byte curSpecValType = 0;
/*     */     
/* 116 */     boolean[] tileSpec = null;
/* 117 */     boolean[] compSpec = null;
/*     */     
/* 119 */     while (stk.hasMoreTokens()) {
/* 120 */       String word = stk.nextToken().toLowerCase();
/*     */       
/* 122 */       switch (word.charAt(0)) {
/*     */         case 't':
/* 124 */           tileSpec = a(word, this.j);
/* 125 */           if (curSpecValType == 1) {
/* 126 */             curSpecValType = 3;
/*     */             continue;
/*     */           } 
/* 129 */           curSpecValType = 2;
/*     */           continue;
/*     */         
/*     */         case 'c':
/* 133 */           compSpec = a(word, this.k);
/* 134 */           if (curSpecValType == 2) {
/* 135 */             curSpecValType = 3;
/*     */             continue;
/*     */           } 
/* 138 */           curSpecValType = 1;
/*     */           continue;
/*     */         case 'd':
/*     */         case 'e':
/*     */         case 'r':
/* 143 */           if (!word.equalsIgnoreCase("reversible") && 
/* 144 */             !word.equalsIgnoreCase("derived") && 
/* 145 */             !word.equalsIgnoreCase("expounded")) {
/* 146 */             throw new IllegalArgumentException("Unknown parameter for '-Qtype' option: " + word);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 151 */           if (wp.getLossless() && (word
/* 152 */             .equalsIgnoreCase("derived") || word
/* 153 */             .equalsIgnoreCase("expounded"))) {
/* 154 */             throw new IllegalArgumentException("Cannot use non reversible quantization with '-lossless' option");
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 159 */           if (curSpecValType == 0) {
/* 160 */             a(word);
/*     */           }
/* 162 */           else if (curSpecValType == 2) {
/* 163 */             for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 164 */               if (tileSpec[i]) {
/* 165 */                 b(i, word);
/*     */               }
/*     */             } 
/* 168 */           } else if (curSpecValType == 1) {
/* 169 */             for (int i = compSpec.length - 1; i >= 0; i--) {
/* 170 */               if (compSpec[i]) {
/* 171 */                 a(i, word);
/*     */               }
/*     */             } 
/*     */           } else {
/* 175 */             for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 176 */               for (int j = compSpec.length - 1; j >= 0; j--) {
/* 177 */                 if (tileSpec[i] && compSpec[j]) {
/* 178 */                   a(i, j, word);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 185 */           curSpecValType = 0;
/* 186 */           tileSpec = null;
/* 187 */           compSpec = null;
/*     */           continue;
/*     */       } 
/*     */       
/* 191 */       throw new IllegalArgumentException("Unknown parameter for '-Qtype' option: " + word);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (d() == null) {
/* 198 */       int ndefspec = 0;
/* 199 */       for (int t = nt - 1; t >= 0; t--) {
/* 200 */         for (int i = nc - 1; i >= 0; i--) {
/* 201 */           if (this.l[t][i] == 0) {
/* 202 */             ndefspec++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 209 */       if (ndefspec != 0) {
/* 210 */         if (wp.getLossless()) {
/* 211 */           a("reversible");
/*     */         } else {
/* 213 */           a("expounded");
/*     */         } 
/*     */       } else {
/*     */         int j, i;
/*     */         
/* 218 */         a(a(0, 0));
/* 219 */         switch (this.l[0][0]) {
/*     */           case 2:
/* 221 */             for (j = nc - 1; j >= 0; j--) {
/* 222 */               if (this.l[0][j] == 2)
/* 223 */                 this.l[0][j] = 0; 
/*     */             } 
/* 225 */             this.o[0] = null;
/*     */             break;
/*     */           case 1:
/* 228 */             for (i = nt - 1; i >= 0; i--) {
/* 229 */               if (this.l[i][0] == 1)
/* 230 */                 this.l[i][0] = 0; 
/*     */             } 
/* 232 */             this.n[0] = null;
/*     */             break;
/*     */           case 3:
/* 235 */             this.l[0][0] = 0;
/* 236 */             this.p.put("t0c0", null);
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
/*     */   public boolean e(int t, int i) {
/* 254 */     if (((String)a(t, i)).equals("derived")) {
/* 255 */       return true;
/*     */     }
/* 257 */     return false;
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
/*     */   public boolean f(int t, int i) {
/* 270 */     if (((String)a(t, i)).equals("reversible")) {
/* 271 */       return true;
/*     */     }
/* 273 */     return false;
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
/*     */   public boolean a() {
/* 285 */     if (((String)d()).equals("reversible")) {
/* 286 */       for (int t = this.j - 1; t >= 0; t--) {
/* 287 */         for (int i = this.k - 1; i >= 0; i--)
/* 288 */         { if (this.l[t][i] != 0)
/* 289 */             return false;  } 
/* 290 */       }  return true;
/*     */     } 
/*     */     
/* 293 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 303 */     for (int t = this.j - 1; t >= 0; t--) {
/* 304 */       for (int i = this.k - 1; i >= 0; i--)
/* 305 */       { if (((String)b(t, i)).equals("reversible"))
/* 306 */           return false;  } 
/* 307 */     }  return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/g/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */