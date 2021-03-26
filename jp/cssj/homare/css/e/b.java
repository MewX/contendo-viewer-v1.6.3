/*     */ package jp.cssj.homare.css.e;
/*     */ 
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.a.c.t;
/*     */ import jp.cssj.homare.b.a.c.u;
/*     */ import jp.cssj.homare.b.a.c.w;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.I;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.O;
/*     */ import jp.cssj.homare.css.f.S;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class b
/*     */ {
/*     */   public static ad a(m ua, LexicalUnit lu) throws l {
/*     */     String ident;
/*  36 */     short luType = lu.getLexicalUnitType();
/*  37 */     switch (luType) {
/*     */       case 35:
/*  39 */         ident = lu.getStringValue().toLowerCase();
/*  40 */         if (ident.equals("auto")) {
/*  41 */           return (ad)d.a;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  52 */         return null;
/*     */       case 23:
/*     */         return (ad)l.d(lu);
/*     */     } 
/*     */     return (ad)l.a(ua, lu);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ad b(m device, LexicalUnit lu) throws l {
/*  63 */     return a(device, lu);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static m a(ad widthValue, ad heightValue) {
/*     */     byte widthType;
/*     */     double width;
/*     */     byte heightType;
/*     */     double height;
/*  76 */     switch (widthValue.a()) {
/*     */       case 1000:
/*  78 */         widthType = 1;
/*  79 */         width = ((a)widthValue).c();
/*     */         break;
/*     */       case 23:
/*  82 */         widthType = 2;
/*  83 */         width = ((M)widthValue).c();
/*     */         break;
/*     */       case 1006:
/*     */       case 1007:
/*  87 */         widthType = 3;
/*  88 */         width = 0.0D;
/*     */         break;
/*     */       default:
/*  91 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/*  95 */     switch (heightValue.a()) {
/*     */       case 1000:
/*  97 */         heightType = 1;
/*  98 */         height = ((a)heightValue).c();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 112 */         return m.a(width, height, widthType, heightType);case 23: heightType = 2; height = ((M)heightValue).c(); return m.a(width, height, widthType, heightType);case 1006: case 1007: heightType = 3; height = 0.0D; return m.a(width, height, widthType, heightType);
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static u a(ad value) {
/* 122 */     switch (value.a()) {
/*     */       case 1006:
/*     */       case 1007:
/* 125 */         return u.e;
/*     */       case 23:
/* 127 */         return u.a(((M)value).c(), (byte)2);
/*     */       case 1000:
/* 129 */         return u.a(((a)value).c(), (byte)1);
/*     */     } 
/* 131 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static O c(m ua, LexicalUnit lu) {
/*     */     M m1;
/*     */     E e;
/* 144 */     short luType = lu.getLexicalUnitType();
/* 145 */     switch (luType) {
/*     */       
/*     */       case 23:
/* 148 */         m1 = l.d(lu);
/*     */         break;
/*     */       
/*     */       default:
/* 152 */         e = l.a(ua, lu);
/*     */         break;
/*     */     } 
/* 155 */     if (e != null && e.d()) {
/* 156 */       return null;
/*     */     }
/* 158 */     return (O)e;
/*     */   }
/*     */   public static ad d(m ua, LexicalUnit lu) { S s;
/*     */     M m1;
/* 162 */     if (l.c(lu)) {
/* 163 */       return (ad)I.a;
/*     */     }
/*     */ 
/*     */     
/* 167 */     switch (lu.getLexicalUnitType())
/*     */     { case 13:
/*     */       case 14:
/* 170 */         s = l.e(lu);
/* 171 */         if (s == null || s.d()) {
/* 172 */           return null;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 194 */         return (ad)s;case 23: m1 = l.d(lu); if (m1 == null || m1.d()) return null;  return (ad)m1; }  E e = l.a(ua, lu); if (e == null || e.d()) return null;  return (ad)e; } public static t a(ad top, ad right, ad bottom, ad left) { double topWidth; double rightWidth;
/*     */     double bottomWidth;
/*     */     double leftWidth;
/*     */     short topType;
/*     */     short rightType;
/*     */     short bottomType;
/*     */     short leftType;
/* 201 */     switch (top.a()) {
/*     */       case 1000:
/* 203 */         topType = 1;
/* 204 */         topWidth = ((a)top).c();
/*     */         break;
/*     */       case 23:
/* 207 */         topType = 2;
/* 208 */         topWidth = ((M)top).c();
/*     */         break;
/*     */       case 1006:
/* 211 */         topType = 3;
/* 212 */         topWidth = 0.0D;
/*     */         break;
/*     */       default:
/* 215 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 218 */     switch (right.a()) {
/*     */       case 1000:
/* 220 */         rightType = 1;
/* 221 */         rightWidth = ((a)right).c();
/*     */         break;
/*     */       case 23:
/* 224 */         rightType = 2;
/* 225 */         rightWidth = ((M)right).c();
/*     */         break;
/*     */       case 1006:
/* 228 */         rightType = 3;
/* 229 */         rightWidth = 0.0D;
/*     */         break;
/*     */       default:
/* 232 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 235 */     switch (bottom.a()) {
/*     */       case 1000:
/* 237 */         bottomType = 1;
/* 238 */         bottomWidth = ((a)bottom).c();
/*     */         break;
/*     */       case 23:
/* 241 */         bottomType = 2;
/* 242 */         bottomWidth = ((M)bottom).c();
/*     */         break;
/*     */       case 1006:
/* 245 */         bottomType = 3;
/* 246 */         bottomWidth = 0.0D;
/*     */         break;
/*     */       default:
/* 249 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 252 */     switch (left.a()) {
/*     */       case 1000:
/* 254 */         leftType = 1;
/* 255 */         leftWidth = ((a)left).c();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 269 */         return t.a(topWidth, rightWidth, bottomWidth, leftWidth, topType, rightType, bottomType, leftType);case 23: leftType = 2; leftWidth = ((M)left).c(); return t.a(topWidth, rightWidth, bottomWidth, leftWidth, topType, rightType, bottomType, leftType);case 1006: leftType = 3; leftWidth = 0.0D; return t.a(topWidth, rightWidth, bottomWidth, leftWidth, topType, rightType, bottomType, leftType);
/*     */     } 
/*     */     throw new IllegalStateException(); } public static w b(ad xValue, ad yValue) { short xType;
/*     */     double x;
/*     */     short yType;
/*     */     double y;
/* 275 */     switch (xValue.a()) {
/*     */       case 1000:
/* 277 */         xType = 1;
/* 278 */         x = ((a)xValue).c();
/*     */         break;
/*     */       case 23:
/* 281 */         xType = 2;
/* 282 */         x = ((M)xValue).c();
/*     */         break;
/*     */       case 1006:
/* 285 */         xType = 3;
/* 286 */         x = 0.0D;
/*     */         break;
/*     */       default:
/* 289 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 293 */     switch (yValue.a()) {
/*     */       case 1000:
/* 295 */         yType = 1;
/* 296 */         y = ((a)yValue).c();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 309 */         return w.a(x, y, xType, yType);case 23: yType = 2; y = ((M)yValue).c(); return w.a(x, y, xType, yType);case 1006: yType = 3; y = 0.0D; return w.a(x, y, xType, yType);
/*     */     } 
/*     */     throw new IllegalStateException(); }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */