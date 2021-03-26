/*     */ package jp.cssj.homare.css.e;
/*     */ 
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.S;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.e.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class f
/*     */ {
/*     */   public static double a(M percentage, double srcLength) {
/*  29 */     double ratio = percentage.c();
/*  30 */     return srcLength * ratio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double a(S real, double srcLength) {
/*  41 */     double ratio = real.b();
/*  42 */     return srcLength * ratio;
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
/*     */   public static double a(m ua, double length, short fromUnit, short toUnit) {
/*  55 */     switch (toUnit) {
/*     */       case 18:
/*  57 */         switch (fromUnit) {
/*     */           case 18:
/*  59 */             return length;
/*     */           
/*     */           case 19:
/*  62 */             return length / 2.54D;
/*     */           
/*     */           case 20:
/*  65 */             return length / 25.4D;
/*     */           
/*     */           case 21:
/*  68 */             return length / 72.0D;
/*     */           
/*     */           case 22:
/*  71 */             return length / 6.0D;
/*     */           
/*     */           case 17:
/*  74 */             return length / ua.n();
/*     */         } 
/*     */         
/*  77 */         throw new IllegalArgumentException();
/*     */ 
/*     */       
/*     */       case 19:
/*  81 */         switch (fromUnit) {
/*     */           case 18:
/*  83 */             return length * 2.54D;
/*     */           
/*     */           case 19:
/*  86 */             return length;
/*     */           
/*     */           case 20:
/*  89 */             return length / 10.0D;
/*     */           
/*     */           case 21:
/*  92 */             return length * 2.54D / 72.0D;
/*     */           
/*     */           case 22:
/*  95 */             return length * 2.54D / 6.0D;
/*     */           
/*     */           case 17:
/*  98 */             return length * 2.54D / ua.n();
/*     */         } 
/*     */         
/* 101 */         throw new IllegalArgumentException();
/*     */ 
/*     */       
/*     */       case 20:
/* 105 */         switch (fromUnit) {
/*     */           case 18:
/* 107 */             return length * 25.4D;
/*     */           
/*     */           case 19:
/* 110 */             return length * 10.0D;
/*     */           
/*     */           case 20:
/* 113 */             return length;
/*     */           
/*     */           case 21:
/* 116 */             return length * 25.4D / 72.0D;
/*     */           
/*     */           case 22:
/* 119 */             return length * 25.4D / 6.0D;
/*     */           
/*     */           case 17:
/* 122 */             return length * 25.4D / ua.n();
/*     */         } 
/*     */         
/* 125 */         throw new IllegalArgumentException();
/*     */ 
/*     */       
/*     */       case 21:
/* 129 */         switch (fromUnit) {
/*     */           case 18:
/* 131 */             return length * 72.0D;
/*     */           
/*     */           case 19:
/* 134 */             return length * 72.0D / 2.54D;
/*     */           
/*     */           case 20:
/* 137 */             return length * 72.0D / 25.4D;
/*     */           
/*     */           case 21:
/* 140 */             return length;
/*     */           
/*     */           case 22:
/* 143 */             return length * 12.0D;
/*     */           
/*     */           case 17:
/* 146 */             return length * 72.0D / ua.n();
/*     */         } 
/*     */         
/* 149 */         throw new IllegalArgumentException();
/*     */ 
/*     */       
/*     */       case 22:
/* 153 */         switch (fromUnit) {
/*     */           case 18:
/* 155 */             return length * 6.0D;
/*     */           
/*     */           case 19:
/* 158 */             return length * 6.0D / 2.54D;
/*     */           
/*     */           case 20:
/* 161 */             return length * 6.0D / 25.4D;
/*     */           
/*     */           case 21:
/* 164 */             return length / 6.0D;
/*     */           
/*     */           case 22:
/* 167 */             return length;
/*     */           
/*     */           case 17:
/* 170 */             return length * 6.0D / ua.n();
/*     */         } 
/*     */         
/* 173 */         throw new IllegalArgumentException();
/*     */ 
/*     */       
/*     */       case 17:
/* 177 */         switch (fromUnit) {
/*     */           case 18:
/* 179 */             return length * ua.n();
/*     */           
/*     */           case 19:
/* 182 */             return length * ua.n() / 2.54D;
/*     */           
/*     */           case 20:
/* 185 */             return length * ua.n() / 25.4D;
/*     */           
/*     */           case 21:
/* 188 */             return length * ua.n() / 72.0D;
/*     */           
/*     */           case 22:
/* 191 */             return length * ua.n() / 6.0D;
/*     */           
/*     */           case 17:
/* 194 */             return length;
/*     */         } 
/*     */         
/* 197 */         throw new IllegalArgumentException();
/*     */     } 
/*     */ 
/*     */     
/* 201 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static a a(m ua, String s) throws NumberFormatException, IllegalArgumentException {
/* 207 */     s = s.toLowerCase().trim();
/* 208 */     double len = d.a(s.substring(0, s.length() - 2));
/* 209 */     if (s.endsWith("mm"))
/* 210 */       return a.a(ua, len, (short)20); 
/* 211 */     if (s.endsWith("cm"))
/* 212 */       return a.a(ua, len, (short)19); 
/* 213 */     if (s.endsWith("pt"))
/* 214 */       return a.a(ua, len, (short)21); 
/* 215 */     if (s.endsWith("px"))
/* 216 */       return a.a(ua, len, (short)17); 
/* 217 */     if (s.endsWith("pc"))
/* 218 */       return a.a(ua, len, (short)21); 
/* 219 */     if (s.endsWith("in")) {
/* 220 */       return a.a(ua, len, (short)18);
/*     */     }
/* 222 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */