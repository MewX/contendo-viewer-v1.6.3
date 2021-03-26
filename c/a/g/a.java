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
/*     */ public class a
/*     */   extends d
/*     */ {
/*  60 */   private String a = "2";
/*     */ 
/*     */ 
/*     */ 
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
/*  74 */     super(nt, nc, type);
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
/*     */   public a(int nt, int nc, byte type, J2KImageWriteParamJava wp, String values) {
/*  89 */     super(nt, nc, type);
/*     */ 
/*     */     
/*  92 */     if (values == null) {
/*  93 */       a(new Integer(this.a));
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  99 */     String param = values;
/*     */     
/* 101 */     StringTokenizer stk = new StringTokenizer(param);
/*     */     
/* 103 */     byte curSpecType = 0;
/*     */     
/* 105 */     boolean[] tileSpec = null;
/* 106 */     boolean[] compSpec = null;
/*     */ 
/*     */     
/* 109 */     while (stk.hasMoreTokens()) {
/* 110 */       Integer value; String word = stk.nextToken().toLowerCase();
/*     */       
/* 112 */       switch (word.charAt(0)) {
/*     */         case 't':
/* 114 */           tileSpec = a(word, this.j);
/* 115 */           if (curSpecType == 1) {
/* 116 */             curSpecType = 3; continue;
/*     */           } 
/* 118 */           curSpecType = 2;
/*     */           continue;
/*     */         case 'c':
/* 121 */           compSpec = a(word, this.k);
/* 122 */           if (curSpecType == 2) {
/* 123 */             curSpecType = 3; continue;
/*     */           } 
/* 125 */           curSpecType = 1;
/*     */           continue;
/*     */       } 
/*     */       try {
/* 129 */         value = new Integer(word);
/*     */       }
/* 131 */       catch (NumberFormatException e) {
/* 132 */         throw new IllegalArgumentException("Bad parameter for -Qguard_bits option : " + word);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 137 */       if (value.floatValue() <= 0.0F) {
/* 138 */         throw new IllegalArgumentException("Guard bits value must be positive : " + value);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 144 */       if (curSpecType == 0) {
/* 145 */         a(value);
/*     */       }
/* 147 */       else if (curSpecType == 2) {
/* 148 */         for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 149 */           if (tileSpec[i]) {
/* 150 */             b(i, value);
/*     */           }
/*     */         } 
/* 153 */       } else if (curSpecType == 1) {
/* 154 */         for (int i = compSpec.length - 1; i >= 0; i--) {
/* 155 */           if (compSpec[i]) {
/* 156 */             a(i, value);
/*     */           }
/*     */         } 
/*     */       } else {
/* 160 */         for (int i = tileSpec.length - 1; i >= 0; i--) {
/* 161 */           for (int j = compSpec.length - 1; j >= 0; j--) {
/* 162 */             if (tileSpec[i] && compSpec[j]) {
/* 163 */               a(i, j, value);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 170 */       curSpecType = 0;
/* 171 */       tileSpec = null;
/* 172 */       compSpec = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (d() == null) {
/* 179 */       int ndefspec = 0;
/* 180 */       for (int t = nt - 1; t >= 0; t--) {
/* 181 */         for (int c = nc - 1; c >= 0; c--) {
/* 182 */           if (this.l[t][c] == 0) {
/* 183 */             ndefspec++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 190 */       if (ndefspec != 0) {
/* 191 */         a(new Integer(this.a));
/*     */       } else {
/*     */         int c, i;
/*     */ 
/*     */         
/* 196 */         a(a(0, 0));
/* 197 */         switch (this.l[0][0]) {
/*     */           case 2:
/* 199 */             for (c = nc - 1; c >= 0; c--) {
/* 200 */               if (this.l[0][c] == 2)
/* 201 */                 this.l[0][c] = 0; 
/*     */             } 
/* 203 */             this.o[0] = null;
/*     */             break;
/*     */           case 1:
/* 206 */             for (i = nt - 1; i >= 0; i--) {
/* 207 */               if (this.l[i][0] == 1)
/* 208 */                 this.l[i][0] = 0; 
/*     */             } 
/* 210 */             this.n[0] = null;
/*     */             break;
/*     */           case 3:
/* 213 */             this.l[0][0] = 0;
/* 214 */             this.p.put("t0c0", null);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/g/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */