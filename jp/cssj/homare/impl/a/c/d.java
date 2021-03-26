/*     */ package jp.cssj.homare.impl.a.c;
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
/*     */ import jp.cssj.homare.impl.a.c.a.c;
/*     */ import jp.cssj.homare.impl.a.c.b.b;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends a
/*     */ {
/*  31 */   public static final j a = (j)new d();
/*     */   
/*  33 */   public static final j b = (j)new d();
/*     */   
/*  35 */   private static final j[] c = new j[] { a, b }; public static w c(c style) { M m1; ad ad1; M m3; ad ad2; M m2, y;
/*     */     ad ad3;
/*     */     M x, m4;
/*  38 */     ad xValue = style.a(a);
/*  39 */     ad yValue = style.a(b);
/*  40 */     switch (b.c(style)) {
/*     */       
/*     */       case 2:
/*  43 */         switch (c.c(style)) {
/*     */           case 2:
/*  45 */             switch (yValue.a()) {
/*     */               case 23:
/*  47 */                 y = (M)yValue;
/*  48 */                 m3 = M.a(100.0D - y.b());
/*     */                 break;
/*     */             } 
/*     */           case 3:
/*  52 */             ad3 = xValue;
/*  53 */             m1 = m3;
/*  54 */             ad2 = ad3;
/*     */             break;
/*     */         } 
/*     */       
/*     */       
/*     */ 
/*     */       
/*     */       case 3:
/*  62 */         switch (c.c(style)) {
/*     */           case 1:
/*  64 */             switch (ad2.a()) {
/*     */               case 23:
/*  66 */                 x = m1;
/*  67 */                 m1 = M.a(100.0D - x.b());
/*     */                 break;
/*     */             } 
/*  70 */             m4 = m1;
/*  71 */             ad1 = ad2;
/*  72 */             m2 = m4;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  81 */     return b.b(ad1, (ad)m2); }
/*     */ 
/*     */   
/*     */   protected d() {
/*  85 */     super("background-position");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  89 */     return (ad)M.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   protected j[] a() {
/*  97 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ad a(ad value, c style) {
/* 104 */     return l.a(value, style);
/*     */   }
/*     */   protected e.a[] a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     E e1, e2;
/* 108 */     if (lu.getLexicalUnitType() == 12) {
/* 109 */       return new e.a[] { new e.a(a, (ad)C.a), new e.a(b, (ad)C.a) };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (lu.getLexicalUnitType() == 35) {
/* 115 */       M m2; String kw1 = lu.getStringValue().toLowerCase();
/* 116 */       if (!kw1.equals("top") && !kw1.equals("bottom") && !kw1.equals("center") && !kw1.equals("left") && 
/* 117 */         !kw1.equals("right")) {
/* 118 */         throw new l();
/*     */       }
/*     */       
/* 121 */       lu = lu.getNextLexicalUnit();
/* 122 */       if (lu == null || lu.getLexicalUnitType() == 35) {
/* 123 */         M m4; String kw2; if (lu == null) {
/* 124 */           kw2 = null;
/*     */         } else {
/* 126 */           kw2 = lu.getStringValue().toLowerCase();
/* 127 */           if (!kw2.equals("top") && !kw2.equals("bottom") && !kw2.equals("center") && !kw2.equals("left") && 
/* 128 */             !kw2.equals("right")) {
/* 129 */             throw new l();
/*     */           }
/*     */         } 
/*     */         
/* 133 */         if (("top".equals(kw1) && "left".equals(kw2)) || ("left".equals(kw1) && "top".equals(kw2))) {
/* 134 */           m2 = m4 = M.a;
/* 135 */         } else if (("top".equals(kw1) && kw2 == null) || ("top".equals(kw1) && "center".equals(kw2)) || ("center"
/* 136 */           .equals(kw1) && "top".equals(kw2))) {
/* 137 */           m2 = M.b;
/* 138 */           m4 = M.a;
/* 139 */         } else if (("right".equals(kw1) && "top".equals(kw2)) || ("top".equals(kw1) && "right".equals(kw2))) {
/* 140 */           m2 = M.c;
/* 141 */           m4 = M.a;
/* 142 */         } else if (("left".equals(kw1) && kw2 == null) || ("left".equals(kw1) && "center".equals(kw2)) || ("center"
/* 143 */           .equals(kw1) && "left".equals(kw2))) {
/* 144 */           m2 = M.a;
/* 145 */           m4 = M.b;
/* 146 */         } else if (("center".equals(kw1) && kw2 == null) || ("center".equals(kw1) && "center".equals(kw2))) {
/* 147 */           m2 = m4 = M.b;
/* 148 */         } else if (("right".equals(kw1) && kw2 == null) || ("right".equals(kw1) && "center".equals(kw2)) || ("center"
/* 149 */           .equals(kw1) && "right".equals(kw2))) {
/* 150 */           m2 = M.c;
/* 151 */           m4 = M.b;
/* 152 */         } else if (("left".equals(kw1) && "bottom".equals(kw2)) || ("bottom"
/* 153 */           .equals(kw1) && "left".equals(kw2))) {
/* 154 */           m2 = M.a;
/* 155 */           m4 = M.c;
/* 156 */         } else if (("bottom".equals(kw1) && kw2 == null) || ("bottom".equals(kw1) && "center".equals(kw2)) || ("center"
/* 157 */           .equals(kw1) && "bottom".equals(kw2))) {
/* 158 */           m2 = M.b;
/* 159 */           m4 = M.c;
/* 160 */         } else if (("bottom".equals(kw1) && "right".equals(kw2)) || ("right"
/* 161 */           .equals(kw1) && "bottom".equals(kw2))) {
/* 162 */           m2 = m4 = M.c;
/*     */         } else {
/* 164 */           throw new l();
/*     */         } 
/*     */         
/* 167 */         return new e.a[] { new e.a(a, (ad)m2), new e.a(b, (ad)m4) };
/*     */       } 
/*     */       
/* 170 */       M m3 = l.d(lu);
/* 171 */       if (m3 == null) {
/* 172 */         e2 = l.a(ua, lu);
/*     */       }
/* 174 */       if (e2 == null) {
/* 175 */         throw new l();
/*     */       }
/* 177 */       if (kw1.equals("left")) {
/* 178 */         m2 = M.a;
/* 179 */       } else if (kw1.equals("center")) {
/* 180 */         m2 = M.b;
/* 181 */       } else if (kw1.equals("right")) {
/* 182 */         m2 = M.c;
/*     */       } else {
/* 184 */         throw new l();
/*     */       } 
/*     */       
/* 187 */       return new e.a[] { new e.a(a, (ad)m2), new e.a(b, (ad)e2) };
/*     */     } 
/*     */     
/* 190 */     M m1 = l.d(lu);
/* 191 */     if (m1 == null) {
/* 192 */       e1 = l.a(ua, lu);
/*     */     }
/* 194 */     if (e1 == null) {
/* 195 */       throw new l();
/*     */     }
/*     */     
/* 198 */     lu = lu.getNextLexicalUnit();
/* 199 */     if (lu == null) {
/* 200 */       e2 = e1;
/* 201 */       return new e.a[] { new e.a(a, (ad)e1), new e.a(b, (ad)e2) };
/*     */     } 
/*     */ 
/*     */     
/* 205 */     if (lu.getLexicalUnitType() == 35) {
/* 206 */       String kw2 = lu.getStringValue().toLowerCase();
/* 207 */       if (kw2.equals("top")) {
/* 208 */         M m2 = M.a;
/* 209 */       } else if (kw2.equals("center")) {
/* 210 */         M m2 = M.b;
/* 211 */       } else if (kw2.equals("bottom")) {
/* 212 */         M m2 = M.c;
/*     */       } else {
/* 214 */         throw new l();
/*     */       } 
/*     */     } else {
/* 217 */       M m2 = l.d(lu);
/* 218 */       if (m2 == null) {
/* 219 */         e2 = l.a(ua, lu);
/*     */       }
/* 221 */       if (e2 == null) {
/* 222 */         throw new l();
/*     */       }
/*     */     } 
/* 225 */     return new e.a[] { new e.a(a, (ad)e1), new e.a(b, (ad)e2) };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */