/*     */ package jp.cssj.homare.impl.a.c.a;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.a.c.w;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.a;
/*     */ import jp.cssj.homare.css.c.e;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.b;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.impl.a.c.b.b;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class H
/*     */   extends a
/*     */ {
/*  28 */   public static final j a = (j)new H();
/*     */   
/*  30 */   public static final j b = (j)new H();
/*     */   
/*  32 */   private static final j[] c = new j[] { a, b }; public static w c(c style) { M m1, m3; ad ad1; M m2, m5;
/*     */     ad x;
/*     */     M y, m4;
/*  35 */     ad xValue = style.a(a);
/*  36 */     ad yValue = style.a(b);
/*  37 */     switch (b.c(style)) {
/*     */       
/*     */       case 2:
/*  40 */         switch (c.c(style)) {
/*     */           case 2:
/*  42 */             switch (yValue.a()) {
/*     */               case 23:
/*  44 */                 m5 = (M)yValue;
/*  45 */                 m3 = M.a(100.0D - m5.b());
/*     */                 break;
/*     */             } 
/*     */           case 3:
/*  49 */             x = xValue;
/*  50 */             m1 = m3;
/*  51 */             ad1 = x;
/*     */             break;
/*     */         } 
/*     */       
/*     */       
/*     */ 
/*     */       
/*     */       case 3:
/*  59 */         switch (c.c(style)) {
/*     */           case 1:
/*  61 */             switch (ad1.a()) {
/*     */               case 23:
/*  63 */                 y = (M)ad1;
/*  64 */                 m2 = M.a(100.0D - y.b());
/*     */                 break;
/*     */             } 
/*  67 */             m4 = m1;
/*  68 */             m1 = m2;
/*  69 */             m2 = m4;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  78 */     return b.b((ad)m1, (ad)m2); }
/*     */ 
/*     */   
/*     */   protected H() {
/*  82 */     super("-cssj-transform-origin");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  86 */     return (ad)M.b;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   protected j[] a() {
/*  94 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ad a(ad value, c style) {
/* 101 */     return l.a(value, style);
/*     */   }
/*     */   protected e.a[] a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     E e1, e2;
/* 105 */     if (lu.getLexicalUnitType() == 12) {
/* 106 */       return new e.a[] { new e.a(a, (ad)C.a), new e.a(b, (ad)C.a) };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (lu.getLexicalUnitType() == 35) {
/* 112 */       M m2; String kw1 = lu.getStringValue().toLowerCase();
/* 113 */       if (!kw1.equals("top") && !kw1.equals("bottom") && !kw1.equals("center") && !kw1.equals("left") && 
/* 114 */         !kw1.equals("right")) {
/* 115 */         throw new l();
/*     */       }
/*     */       
/* 118 */       lu = lu.getNextLexicalUnit();
/* 119 */       if (lu == null || lu.getLexicalUnitType() == 35) {
/* 120 */         M m4; String kw2; if (lu == null) {
/* 121 */           kw2 = null;
/*     */         } else {
/* 123 */           kw2 = lu.getStringValue().toLowerCase();
/* 124 */           if (!kw2.equals("top") && !kw2.equals("bottom") && !kw2.equals("center") && !kw2.equals("left") && 
/* 125 */             !kw2.equals("right")) {
/* 126 */             throw new l();
/*     */           }
/*     */         } 
/*     */         
/* 130 */         if (("top".equals(kw1) && "left".equals(kw2)) || ("left".equals(kw1) && "top".equals(kw2))) {
/* 131 */           m2 = m4 = M.a;
/* 132 */         } else if (("top".equals(kw1) && kw2 == null) || ("top".equals(kw1) && "center".equals(kw2)) || ("center"
/* 133 */           .equals(kw1) && "top".equals(kw2))) {
/* 134 */           m2 = M.b;
/* 135 */           m4 = M.a;
/* 136 */         } else if (("right".equals(kw1) && "top".equals(kw2)) || ("top".equals(kw1) && "right".equals(kw2))) {
/* 137 */           m2 = M.c;
/* 138 */           m4 = M.a;
/* 139 */         } else if (("left".equals(kw1) && kw2 == null) || ("left".equals(kw1) && "center".equals(kw2)) || ("center"
/* 140 */           .equals(kw1) && "left".equals(kw2))) {
/* 141 */           m2 = M.a;
/* 142 */           m4 = M.b;
/* 143 */         } else if (("center".equals(kw1) && kw2 == null) || ("center".equals(kw1) && "center".equals(kw2))) {
/* 144 */           m2 = m4 = M.b;
/* 145 */         } else if (("right".equals(kw1) && kw2 == null) || ("right".equals(kw1) && "center".equals(kw2)) || ("center"
/* 146 */           .equals(kw1) && "right".equals(kw2))) {
/* 147 */           m2 = M.c;
/* 148 */           m4 = M.b;
/* 149 */         } else if (("left".equals(kw1) && "bottom".equals(kw2)) || ("bottom"
/* 150 */           .equals(kw1) && "left".equals(kw2))) {
/* 151 */           m2 = M.a;
/* 152 */           m4 = M.c;
/* 153 */         } else if (("bottom".equals(kw1) && kw2 == null) || ("bottom".equals(kw1) && "center".equals(kw2)) || ("center"
/* 154 */           .equals(kw1) && "bottom".equals(kw2))) {
/* 155 */           m2 = M.b;
/* 156 */           m4 = M.c;
/* 157 */         } else if (("bottom".equals(kw1) && "right".equals(kw2)) || ("right"
/* 158 */           .equals(kw1) && "bottom".equals(kw2))) {
/* 159 */           m2 = m4 = M.c;
/*     */         } else {
/* 161 */           throw new l();
/*     */         } 
/*     */         
/* 164 */         return new e.a[] { new e.a(a, (ad)m2), new e.a(b, (ad)m4) };
/*     */       } 
/*     */       
/* 167 */       M m3 = l.d(lu);
/* 168 */       if (m3 == null) {
/* 169 */         e2 = l.a(ua, lu);
/*     */       }
/* 171 */       if (e2 == null) {
/* 172 */         throw new l();
/*     */       }
/* 174 */       if (kw1.equals("left")) {
/* 175 */         m2 = M.a;
/* 176 */       } else if (kw1.equals("center")) {
/* 177 */         m2 = M.b;
/* 178 */       } else if (kw1.equals("right")) {
/* 179 */         m2 = M.c;
/*     */       } else {
/* 181 */         throw new l();
/*     */       } 
/*     */       
/* 184 */       return new e.a[] { new e.a(a, (ad)m2), new e.a(b, (ad)e2) };
/*     */     } 
/*     */     
/* 187 */     M m1 = l.d(lu);
/* 188 */     if (m1 == null) {
/* 189 */       e1 = l.a(ua, lu);
/*     */     }
/* 191 */     if (e1 == null) {
/* 192 */       throw new l();
/*     */     }
/*     */     
/* 195 */     lu = lu.getNextLexicalUnit();
/* 196 */     if (lu == null) {
/* 197 */       e2 = e1;
/* 198 */       return new e.a[] { new e.a(a, (ad)e1), new e.a(b, (ad)e2) };
/*     */     } 
/*     */ 
/*     */     
/* 202 */     if (lu.getLexicalUnitType() == 35) {
/* 203 */       String kw2 = lu.getStringValue().toLowerCase();
/* 204 */       if (kw2.equals("top")) {
/* 205 */         M m2 = M.a;
/* 206 */       } else if (kw2.equals("center")) {
/* 207 */         M m2 = M.b;
/* 208 */       } else if (kw2.equals("bottom")) {
/* 209 */         M m2 = M.c;
/*     */       } else {
/* 211 */         throw new l();
/*     */       } 
/*     */     } else {
/* 214 */       M m2 = l.d(lu);
/* 215 */       if (m2 == null) {
/* 216 */         e2 = l.a(ua, lu);
/*     */       }
/* 218 */       if (e2 == null) {
/* 219 */         throw new l();
/*     */       }
/*     */     } 
/* 222 */     return new e.a[] { new e.a(a, (ad)e1), new e.a(b, (ad)e2) };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/H.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */