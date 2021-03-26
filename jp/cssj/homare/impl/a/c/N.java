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
/*    */ import jp.cssj.homare.css.f.I;
/*    */ import jp.cssj.homare.css.f.M;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class N
/*    */   extends b
/*    */ {
/* 23 */   public static final j a = (j)new N();
/*    */   
/*    */   public static u c(c style) {
/* 26 */     ad value = style.a(a);
/* 27 */     switch (value.a()) {
/*    */       case 1008:
/* 29 */         return u.a(0.0D, (byte)1);
/*    */       case 23:
/* 31 */         return u.a(((M)value).c(), (byte)2);
/*    */       case 1000:
/* 33 */         return u.a(((a)value).c(), (byte)1);
/*    */     } 
/* 35 */     throw new IllegalStateException();
/*    */   }
/*    */ 
/*    */   
/*    */   protected N() {
/* 40 */     super("letter-spacing");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 44 */     return (ad)I.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 52 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 56 */     if (l.c(lu)) {
/* 57 */       return (ad)I.a;
/*    */     }
/*    */     
/* 60 */     E e = l.a(ua, lu);
/* 61 */     if (e != null) {
/* 62 */       return (ad)e;
/*    */     }
/* 64 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/N.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */