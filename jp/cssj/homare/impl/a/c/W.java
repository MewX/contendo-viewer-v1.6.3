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
/*     */ import jp.cssj.homare.impl.a.c.a.c;
/*     */ import jp.cssj.homare.impl.a.c.b.b;
/*     */ import jp.cssj.homare.impl.a.c.c.e;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class W
/*     */   extends b
/*     */ {
/*  26 */   public static final j a = (j)new W();
/*     */   
/*     */   public static ad c(c style) {
/*     */     j info;
/*  30 */     if (e.c(style) != null)
/*     */     
/*  32 */     { info = a; }
/*     */     else
/*     */     
/*  35 */     { switch (b.c(style))
/*     */       { case 1:
/*  37 */           info = a;
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
/*  64 */           return style.a(info);case 2: switch (c.c(style)) { case 2: case 3: info = X.a; return style.a(info); }  info = a; return style.a(info);case 3: switch (c.c(style)) { case 1: info = X.a; return style.a(info); }  info = a; return style.a(info); }  throw new IllegalStateException(); }  return style.a(info);
/*     */   }
/*     */   
/*     */   public static u d(c style) {
/*  68 */     return b.a(c(style));
/*     */   }
/*     */   
/*     */   private W() {
/*  72 */     super("max-height");
/*     */   }
/*     */ 
/*     */   
/*     */   private ad a(m ua) {
/*  77 */     return ua.m();
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  81 */     return a(style.b());
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  89 */     return l.a(value, style);
/*     */   }
/*     */   
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*  93 */     if (l.b(lu)) {
/*  94 */       return a(ua);
/*     */     }
/*  96 */     O o = b.c(ua, lu);
/*  97 */     if (o == null) {
/*  98 */       throw new l();
/*     */     }
/* 100 */     return (ad)o;
/*     */   }
/*     */   
/*     */   public int a() {
/* 104 */     return 1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/W.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */