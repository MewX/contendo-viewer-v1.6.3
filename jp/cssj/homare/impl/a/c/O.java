/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.b;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.I;
/*    */ import jp.cssj.homare.css.f.M;
/*    */ import jp.cssj.homare.css.f.S;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class O
/*    */   extends b
/*    */ {
/* 27 */   public static final j a = (j)new O();
/*    */   
/*    */   public static double c(c style) {
/* 30 */     ad value = style.a(a);
/* 31 */     switch (value.a()) {
/*    */       case 1017:
/* 33 */         return ((S)value).b() * I.c(style);
/*    */       case 1008:
/* 35 */         return style.b().e() * I.c(style);
/*    */     } 
/* 37 */     return ((a)value).c();
/*    */   }
/*    */ 
/*    */   
/*    */   protected O() {
/* 42 */     super("line-height");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 46 */     return (ad)I.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 54 */     switch (value.a()) {
/*    */       case 1008:
/*    */       case 1017:
/* 57 */         return value;
/*    */       case 23:
/* 59 */         return (ad)a.a(style.b(), ((M)value)
/* 60 */             .c() * I.c(style));
/*    */     } 
/* 62 */     return l.a(value, style);
/*    */   }
/*    */ 
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 67 */     ad lineHeight = b.d(ua, lu);
/* 68 */     if (lineHeight == null) {
/* 69 */       throw new l();
/*    */     }
/* 71 */     return lineHeight;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/O.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */