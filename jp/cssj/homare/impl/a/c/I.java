/*     */ package jp.cssj.homare.impl.a.c;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.d;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.U;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.a.d;
/*     */ import jp.cssj.homare.css.f.a.h;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.u;
/*     */ import jp.cssj.homare.css.f.w;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class I
/*     */   extends b
/*     */ {
/*  27 */   public static final j a = (j)new I();
/*     */   
/*     */   public static double c(c style) {
/*  30 */     return ((a)style.a(a)).c();
/*     */   }
/*     */   
/*     */   protected I() {
/*  34 */     super("font-size");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  38 */     m ua = style.b();
/*  39 */     return (ad)a.a(ua, ua.d((byte)4));
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  43 */     return true; } public ad a(ad value, c style) { a a; c parentStyle; double fontSize; m ua; u emLength; w exLength; h remLength; d chLength;
/*     */     double d1;
/*     */     M percentage;
/*     */     U relativeSize;
/*  47 */     switch (value.a()) {
/*     */       case 23:
/*  49 */         parentStyle = style.c();
/*  50 */         if (parentStyle == null) {
/*  51 */           parentStyle = style;
/*     */         }
/*  53 */         fontSize = c(parentStyle);
/*  54 */         percentage = (M)value;
/*  55 */         a = a.a(parentStyle.b(), percentage.c() * fontSize);
/*     */         break;
/*     */       
/*     */       case 1014:
/*  59 */         parentStyle = style.c();
/*  60 */         if (parentStyle == null) {
/*  61 */           parentStyle = style;
/*     */         }
/*  63 */         ua = parentStyle.b();
/*  64 */         d1 = c(parentStyle);
/*  65 */         relativeSize = (U)a;
/*  66 */         switch (relativeSize.b()) {
/*     */           case 1:
/*  68 */             a = a.a(ua, ua.a(d1));
/*     */             break;
/*     */           case 2:
/*  71 */             a = a.a(ua, ua.b(d1));
/*     */             break;
/*     */         } 
/*  74 */         throw new IllegalStateException();
/*     */ 
/*     */ 
/*     */       
/*     */       case 1001:
/*  79 */         parentStyle = style.c();
/*  80 */         if (parentStyle == null) {
/*  81 */           parentStyle = style;
/*     */         }
/*  83 */         emLength = (u)a;
/*  84 */         a = emLength.a(parentStyle);
/*     */         break;
/*     */       
/*     */       case 1002:
/*  88 */         parentStyle = style.c();
/*  89 */         if (parentStyle == null) {
/*  90 */           parentStyle = style;
/*     */         }
/*  92 */         exLength = (w)a;
/*  93 */         a = exLength.a(parentStyle);
/*     */         break;
/*     */       
/*     */       case 3008:
/*  97 */         parentStyle = style.c();
/*  98 */         if (parentStyle == null) {
/*  99 */           parentStyle = style;
/*     */         }
/* 101 */         remLength = (h)a;
/* 102 */         a = remLength.a(parentStyle);
/*     */         break;
/*     */       
/*     */       case 3009:
/* 106 */         parentStyle = style.c();
/* 107 */         if (parentStyle == null) {
/* 108 */           parentStyle = style;
/*     */         }
/* 110 */         chLength = (d)a;
/* 111 */         a = chLength.a(parentStyle);
/*     */         break;
/*     */     } 
/*     */     
/* 115 */     return (ad)a; }
/*     */ 
/*     */   
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 119 */     ad value = d.b(ua, lu);
/* 120 */     if (value == null) {
/* 121 */       throw new l();
/*     */     }
/* 123 */     return value;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/I.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */