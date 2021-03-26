/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.i;
/*    */ import jp.cssj.homare.css.f.W;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class al
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new al();
/*    */   
/*    */   public static byte c(c style) {
/* 23 */     W value = (W)style.a(a);
/* 24 */     return value.b();
/*    */   }
/*    */   
/*    */   protected al() {
/* 28 */     super("table-layout");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 32 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 36 */     return (ad)W.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 40 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 44 */     ad value = i.b(lu);
/* 45 */     if (value == null) {
/* 46 */       throw new l();
/*    */     }
/* 48 */     return value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/al.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */