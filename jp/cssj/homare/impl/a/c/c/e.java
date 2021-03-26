/*    */ package jp.cssj.homare.impl.a.c.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.V;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.c.c;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new e();
/*    */   
/*    */   public static b c(c style) {
/* 28 */     ad value = style.a(a);
/* 29 */     if (value.a() != 3001) {
/* 30 */       return null;
/*    */     }
/* 32 */     return ((c)value).b();
/*    */   }
/*    */   
/*    */   public static String d(c style) {
/* 36 */     ad value = style.a(a);
/* 37 */     if (value.a() != 1018) {
/* 38 */       return null;
/*    */     }
/* 40 */     return ((V)value).b();
/*    */   }
/*    */   
/*    */   public static void a(c style, b image) {
/* 44 */     style.a(a, (ad)new c(image));
/*    */   }
/*    */   
/*    */   public static void a(c style, String text) {
/* 48 */     style.a(a, (ad)new V(text));
/*    */   }
/*    */   
/*    */   public e() {
/* 52 */     super("-cssj-internal-image");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 56 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 60 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 64 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 68 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/c/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */