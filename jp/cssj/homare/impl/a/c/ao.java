/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.b.a.c.u;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.M;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ao
/*    */   extends b
/*    */ {
/* 22 */   public static final j a = (j)new ao();
/*    */   
/*    */   public static u c(c style) {
/* 25 */     ad value = style.a(a);
/* 26 */     switch (value.a()) {
/*    */       case 23:
/* 28 */         return u.a(((M)value).c(), (byte)2);
/*    */       case 1000:
/* 30 */         return u.a(((a)value).c(), (byte)1);
/*    */     } 
/* 32 */     throw new IllegalStateException();
/*    */   }
/*    */ 
/*    */   
/*    */   public ao() {
/* 37 */     super("text-indent");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 41 */     return (ad)a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 49 */     return l.a(value, style);
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     M m1;
/* 53 */     E e = l.a(ua, lu);
/* 54 */     if (e == null) {
/* 55 */       m1 = l.d(lu);
/*    */     }
/* 57 */     if (m1 != null) {
/* 58 */       return (ad)m1;
/*    */     }
/* 60 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */