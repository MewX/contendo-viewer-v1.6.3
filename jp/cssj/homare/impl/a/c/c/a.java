/*    */ package jp.cssj.homare.impl.a.c.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.b.a.c.u;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.b;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.O;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.d;
/*    */ import jp.cssj.homare.impl.a.c.aw;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new a();
/*    */   
/*    */   public static ad c(c style) {
/* 28 */     return style.a(a);
/*    */   }
/*    */   
/*    */   public static u d(c style) {
/* 32 */     return b.a(aw.c(style));
/*    */   }
/*    */   
/*    */   protected a() {
/* 36 */     super("-cssj-auto-width");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 40 */     return (ad)d.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 48 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 52 */     if (l.a(lu)) {
/* 53 */       return (ad)d.a;
/*    */     }
/*    */     
/* 56 */     O o = b.c(ua, lu);
/* 57 */     if (o == null) {
/* 58 */       throw new l();
/*    */     }
/* 60 */     return (ad)o;
/*    */   }
/*    */   
/*    */   public int a() {
/* 64 */     return 2;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */