/*     */ package jp.cssj.homare.impl.a.c;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.a.c.u;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.b;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.O;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.d;
/*     */ import jp.cssj.homare.impl.a.c.a.c;
/*     */ import jp.cssj.homare.impl.a.c.b.b;
/*     */ import jp.cssj.homare.impl.a.c.c.a;
/*     */ import jp.cssj.homare.impl.a.c.c.e;
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
/*     */ public class aw
/*     */   extends b
/*     */ {
/*  31 */   public static final j a = (j)new aw();
/*     */   
/*     */   public static ad c(c style) {
/*     */     j info;
/*  35 */     if (e.c(style) != null)
/*     */     
/*  37 */     { info = a; }
/*     */     else
/*     */     
/*  40 */     { switch (b.c(style))
/*     */       { case 1:
/*  42 */           info = a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  69 */           return style.a(info);case 2: switch (c.c(style)) { case 2: case 3: info = L.a; return style.a(info); }  info = a; return style.a(info);case 3: switch (c.c(style)) { case 1: info = L.a; return style.a(info); }  info = a; return style.a(info); }  throw new IllegalStateException(); }  return style.a(info);
/*     */   }
/*     */   
/*     */   public static u d(c style) {
/*  73 */     return b.a(c(style));
/*     */   }
/*     */   
/*     */   protected aw() {
/*  77 */     super("width");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  81 */     return (ad)d.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  89 */     if (value.a() == 1006) {
/*  90 */       value = a.c(style);
/*     */     }
/*  92 */     return l.a(value, style);
/*     */   }
/*     */   
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*  96 */     if (l.a(lu)) {
/*  97 */       return (ad)d.a;
/*     */     }
/*     */     
/* 100 */     O o = b.c(ua, lu);
/* 101 */     if (o == null) {
/* 102 */       throw new l();
/*     */     }
/* 104 */     return (ad)o;
/*     */   }
/*     */   
/*     */   public int a() {
/* 108 */     return 1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/aw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */