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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */   extends d
/*     */ {
/*     */   private String a;
/*     */   
/*     */   public g(int nt, int nc, byte type) {
/*  71 */     super(nt, nc, type);
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
/*     */   public g(int nt, int nc, byte type, String defaultValue, String[] list, J2KImageWriteParamJava wp, String values) {
/*  99 */     super(nt, nc, type);
/* 100 */     this.a = values;
/*     */     
/* 102 */     boolean recognized = false;
/*     */     
/* 104 */     String param = values;
/*     */     
/* 106 */     if (values == null) {
/* 107 */       for (int i = list.length - 1; i >= 0; i--) {
/* 108 */         if (defaultValue.equalsIgnoreCase(list[i]))
/* 109 */           recognized = true; 
/* 110 */       }  if (!recognized) {
/* 111 */         throw new IllegalArgumentException("Default parameter of option - not recognized: " + defaultValue);
/*     */       }
/*     */       
/* 114 */       a(defaultValue);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 119 */     StringTokenizer stk = new StringTokenizer(this.a);
/*     */     
/* 121 */     byte curSpecType = 0;
/*     */     
/* 123 */     boolean[] tileSpec = null;
/*     */     
/* 125 */     boolean[] compSpec = null;
/*     */ 
/*     */     
/* 128 */     while (stk.hasMoreTokens()) {
/* 129 */       String word = stk.nextToken();
/*     */       
/* 131 */       if (word.matches("t[0-9]*")) {
/* 132 */         tileSpec = a(word, this.j);
/* 133 */         if (curSpecType == 1) {
/* 134 */           curSpecType = 3;
/*     */           continue;
/*     */         } 
/* 137 */         curSpecType = 2; continue;
/*     */       } 
/* 139 */       if (word.matches("c[0-9]*")) {
/* 140 */         compSpec = a(word, this.k);
/* 141 */         if (curSpecType == 2) {
/* 142 */           curSpecType = 3;
/*     */           continue;
/*     */         } 
/* 145 */         curSpecType = 1; continue;
/*     */       } 
/* 147 */       recognized = false;
/*     */       int i;
/* 149 */       for (i = list.length - 1; i >= 0; i--) {
/* 150 */         if (word.equalsIgnoreCase(list[i]))
/* 151 */           recognized = true; 
/* 152 */       }  if (!recognized) {
/* 153 */         throw new IllegalArgumentException("Default parameter of option not recognized: " + word);
/*     */       }
/*     */ 
/*     */       
/* 157 */       if (curSpecType == 0) {
/* 158 */         a(word);
/*     */       }
/* 160 */       else if (curSpecType == 2) {
/* 161 */         for (i = tileSpec.length - 1; i >= 0; i--) {
/* 162 */           if (tileSpec[i]) {
/* 163 */             b(i, word);
/*     */           }
/*     */         } 
/* 166 */       } else if (curSpecType == 1) {
/* 167 */         for (i = compSpec.length - 1; i >= 0; i--) {
/* 168 */           if (compSpec[i]) {
/* 169 */             a(i, word);
/*     */           }
/*     */         } 
/*     */       } else {
/* 173 */         for (i = tileSpec.length - 1; i >= 0; i--) {
/* 174 */           for (int j = compSpec.length - 1; j >= 0; j--) {
/* 175 */             if (tileSpec[i] && compSpec[j]) {
/* 176 */               a(i, j, word);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 183 */       curSpecType = 0;
/* 184 */       tileSpec = null;
/* 185 */       compSpec = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 190 */     if (d() == null) {
/* 191 */       int ndefspec = 0;
/* 192 */       for (int t = nt - 1; t >= 0; t--) {
/* 193 */         for (int c = nc - 1; c >= 0; c--) {
/* 194 */           if (this.l[t][c] == 0) {
/* 195 */             ndefspec++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 202 */       if (ndefspec != 0) {
/* 203 */         param = defaultValue;
/* 204 */         for (int i = list.length - 1; i >= 0; i--) {
/* 205 */           if (param.equalsIgnoreCase(list[i]))
/* 206 */             recognized = true; 
/* 207 */         }  if (!recognized) {
/* 208 */           throw new IllegalArgumentException("Default parameter of option not recognized: " + this.a);
/*     */         }
/*     */         
/* 211 */         a(param);
/*     */       } else {
/*     */         int c, i;
/*     */ 
/*     */         
/* 216 */         a(b(0, 0));
/* 217 */         switch (this.l[0][0]) {
/*     */           case 2:
/* 219 */             for (c = nc - 1; c >= 0; c--) {
/* 220 */               if (this.l[0][c] == 2)
/* 221 */                 this.l[0][c] = 0; 
/*     */             } 
/* 223 */             this.o[0] = null;
/*     */             break;
/*     */           case 1:
/* 226 */             for (i = nt - 1; i >= 0; i--) {
/* 227 */               if (this.l[i][0] == 1)
/* 228 */                 this.l[i][0] = 0; 
/*     */             } 
/* 230 */             this.n[0] = null;
/*     */             break;
/*     */           case 3:
/* 233 */             this.l[0][0] = 0;
/* 234 */             this.p.put("t0c0", null);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String a() {
/* 242 */     return this.a;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/g.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */