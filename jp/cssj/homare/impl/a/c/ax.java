/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.I;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ax
/*    */   extends b
/*    */ {
/* 22 */   public static final j a = (j)new ax();
/*    */   
/*    */   public static double c(c style) {
/* 25 */     ad value = style.a(a);
/* 26 */     switch (value.a()) {
/*    */       case 1008:
/* 28 */         return 0.0D;
/*    */       case 1000:
/* 30 */         return ((a)value).c();
/*    */     } 
/* 32 */     throw new IllegalStateException();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ax() {
/* 37 */     super("word-spacing");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 41 */     return (ad)I.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 49 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 53 */     if (l.c(lu)) {
/* 54 */       return (ad)I.a;
/*    */     }
/* 56 */     E value = l.a(ua, lu);
/* 57 */     if (value != null) {
/* 58 */       return (ad)value;
/*    */     }
/* 60 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ax.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */