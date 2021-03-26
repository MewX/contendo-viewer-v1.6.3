/*     */ package jp.cssj.homare.impl.a.c.d;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.css.c.d;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.c.o;
/*     */ import jp.cssj.homare.css.e.b;
/*     */ import jp.cssj.homare.css.e.d;
/*     */ import jp.cssj.homare.css.f.A;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.I;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.x;
/*     */ import jp.cssj.homare.css.f.y;
/*     */ import jp.cssj.homare.css.f.z;
/*     */ import jp.cssj.homare.impl.a.c.I;
/*     */ import jp.cssj.homare.impl.a.c.J;
/*     */ import jp.cssj.homare.impl.a.c.K;
/*     */ import jp.cssj.homare.impl.a.c.O;
/*     */ import jp.cssj.homare.impl.a.c.w;
/*     */ import jp.cssj.homare.impl.a.c.x;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class j
/*     */   extends d
/*     */ {
/*  32 */   public static final o a = (o)new j();
/*     */   
/*     */   protected j() {
/*  35 */     super("font");
/*     */   }
/*     */   
/*     */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*  39 */     if (lu.getLexicalUnitType() == 12) {
/*  40 */       primitives.a(x.a, (ad)C.a);
/*  41 */       primitives.a(J.a, (ad)C.a);
/*  42 */       primitives.a(K.a, (ad)C.a);
/*  43 */       primitives.a(I.a, (ad)C.a);
/*  44 */       primitives.a(O.a, (ad)C.a);
/*  45 */       primitives.a((jp.cssj.homare.css.c.j)w.a, (ad)C.a); return;
/*     */     } 
/*  47 */     if (lu.getLexicalUnitType() == 35) {
/*  48 */       String ident = lu.getStringValue().toLowerCase();
/*  49 */       if (ident.equals("caption") || ident.equals("icon") || ident.equals("menu") || ident.equals("message-box") || ident
/*  50 */         .equals("small-caption") || ident.equals("status-bar")) {
/*     */         
/*  52 */         x defaultFamily = ua.i();
/*  53 */         primitives.a(x.a, (ad)y.a);
/*  54 */         primitives.a(J.a, (ad)z.c);
/*  55 */         primitives.a(K.a, (ad)A.n);
/*  56 */         primitives.a(I.a, 
/*  57 */             (ad)a.a(ua, ua.d((byte)4)));
/*  58 */         primitives.a(O.a, (ad)I.a);
/*  59 */         primitives.a((jp.cssj.homare.css.c.j)w.a, (ad)defaultFamily);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  64 */     y fontStyle = null;
/*  65 */     z fontVariant = null;
/*  66 */     A fontWeight = null;
/*     */     label64: do {
/*  68 */       if (fontStyle == null) {
/*  69 */         fontStyle = d.a(lu);
/*  70 */         if (fontStyle != null) {
/*  71 */           lu = lu.getNextLexicalUnit();
/*     */           continue;
/*     */         } 
/*     */       } 
/*  75 */       if (fontVariant == null) {
/*  76 */         fontVariant = d.c(lu);
/*  77 */         if (fontVariant != null) {
/*  78 */           lu = lu.getNextLexicalUnit();
/*     */           continue;
/*     */         } 
/*     */       } 
/*  82 */       if (fontWeight == null)
/*  83 */       { fontWeight = d.d(lu);
/*  84 */         if (fontWeight != null)
/*  85 */         { lu = lu.getNextLexicalUnit(); }
/*     */         else { break label64; }
/*     */          }
/*     */       else { break label64; }
/*     */     
/*  90 */     } while (lu != null);
/*     */     
/*     */     ad fontSize;
/*  93 */     if (lu == null || (fontSize = d.b(ua, lu)) == null) {
/*  94 */       throw new l("フォントサイズが未指定です");
/*     */     }
/*  96 */     lu = lu.getNextLexicalUnit();
/*     */     
/*  98 */     if (lu == null) {
/*  99 */       throw new l("フォントファミリが未指定です");
/*     */     }
/*     */     
/* 102 */     ad lineHeight = null;
/* 103 */     if (lu.getLexicalUnitType() == 4) {
/* 104 */       lu = lu.getNextLexicalUnit();
/* 105 */       if (lu == null || (lineHeight = b.d(ua, lu)) == null) {
/* 106 */         throw new l("行高さが不正です");
/*     */       }
/* 108 */       lu = lu.getNextLexicalUnit();
/*     */     } 
/*     */     
/*     */     x fontFamily;
/* 112 */     if (lu == null || (fontFamily = d.a(ua, lu)) == null) {
/* 113 */       throw new l("フォントファミリが未指定です");
/*     */     }
/*     */     
/* 116 */     primitives.a(I.a, fontSize);
/* 117 */     if (lineHeight == null) {
/* 118 */       lineHeight = O.a.b(null);
/*     */     }
/* 120 */     primitives.a(O.a, lineHeight);
/* 121 */     if (fontStyle == null) {
/* 122 */       fontStyle = (y)x.a.b(null);
/*     */     }
/* 124 */     primitives.a(x.a, (ad)fontStyle);
/* 125 */     if (fontVariant == null) {
/* 126 */       fontVariant = (z)J.a.b(null);
/*     */     }
/* 128 */     primitives.a(J.a, (ad)fontVariant);
/* 129 */     if (fontWeight == null) {
/* 130 */       fontWeight = (A)K.a.b(null);
/*     */     }
/* 132 */     primitives.a(K.a, (ad)fontWeight);
/* 133 */     primitives.a((jp.cssj.homare.css.c.j)w.a, (ad)fontFamily);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */