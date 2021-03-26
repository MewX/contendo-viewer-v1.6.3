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
/*     */ public class b
/*     */   extends d
/*     */ {
/*  63 */   private String a = "0.0078125";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(int nt, int nc, byte type) {
/*  77 */     super(nt, nc, type);
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
/*     */   public b(int nt, int nc, byte type, J2KImageWriteParamJava wp, String values) {
/*  92 */     super(nt, nc, type);
/*     */     
/*  94 */     if (values == null)
/*     */     {
/*  96 */       a(new Float(this.a));
/*     */     }
/*     */     
/*  99 */     this.q = values;
/*     */ 
/*     */     
/* 102 */     String param = this.q;
/* 103 */     if (param == null) {
/* 104 */       param = this.a;
/*     */     }
/*     */     
/* 107 */     StringTokenizer stk = new StringTokenizer(param);
/*     */     
/* 109 */     byte curSpecType = 0;
/*     */     
/* 111 */     boolean[] tileSpec = null;
/* 112 */     boolean[] compSpec = null;
/*     */ 
/*     */     
/* 115 */     while (stk.hasMoreTokens()) {
/* 116 */       Float value; String word = stk.nextToken().toLowerCase();
/*     */       
/* 118 */       switch (word.charAt(0)) {
/*     */         case 't':
/* 120 */           tileSpec = a(word, this.j);
/* 121 */           if (curSpecType == 1) {
/* 122 */             curSpecType = 3; continue;
/*     */           } 
/* 124 */           curSpecType = 2;
/*     */           continue;
/*     */         case 'c':
/* 127 */           compSpec = a(word, this.k);
/* 128 */           if (curSpecType == 2) {
/* 129 */             curSpecType = 3; continue;
/*     */           } 
/* 131 */           curSpecType = 1;
/*     */           continue;
/*     */       } 
/*     */       try {
/* 135 */         value = new Float(word);
/*     */       }
/* 137 */       catch (NumberFormatException e) {
/* 138 */         throw new IllegalArgumentException("Bad parameter for -Qstep option : " + word);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 143 */       if (value.floatValue() <= 0.0F) {
/* 144 */         throw new IllegalArgumentException("Normalized base step must be positive : " + value);
/*     */       }
/*     */ 
/*     */ 
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
/* 197 */         a(new Float(this.a));
/*     */       } else {
/*     */         int c, i;
/*     */ 
/*     */         
/* 202 */         a(a(0, 0));
/* 203 */         switch (this.l[0][0]) {
/*     */           case 2:
/* 205 */             for (c = nc - 1; c >= 0; c--) {
/* 206 */               if (this.l[0][c] == 2)
/* 207 */                 this.l[0][c] = 0; 
/*     */             } 
/* 209 */             this.o[0] = null;
/*     */             break;
/*     */           case 1:
/* 212 */             for (i = nt - 1; i >= 0; i--) {
/* 213 */               if (this.l[i][0] == 1)
/* 214 */                 this.l[i][0] = 0; 
/*     */             } 
/* 216 */             this.n[0] = null;
/*     */             break;
/*     */           case 3:
/* 219 */             this.l[0][0] = 0;
/* 220 */             this.p.put("t0c0", null);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/g/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */