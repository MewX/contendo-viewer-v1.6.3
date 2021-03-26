/*    */ package jp.cssj.homare.impl.a.c.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.ab;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   extends b
/*    */ {
/* 22 */   public static final j a = (j)new f();
/*    */   
/*    */   public static URI c(c style) {
/* 25 */     ad value = style.a(a);
/* 26 */     if (value.a() == 1007) {
/* 27 */       return null;
/*    */     }
/* 29 */     return ((ab)value).b();
/*    */   }
/*    */   
/*    */   public static void a(c style, URI uri) {
/* 33 */     style.a(a, (ad)ab.a(uri));
/*    */   }
/*    */   
/*    */   public f() {
/* 37 */     super("-cssj-internal-link");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 41 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 45 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 53 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/c/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */