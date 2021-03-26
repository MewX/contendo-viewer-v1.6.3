/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.e;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends b
/*    */ {
/* 24 */   public static final j a = (j)new a();
/*    */   
/*    */   public static byte c(c style) {
/* 27 */     return ((e)style.a(a)).b();
/*    */   }
/*    */   
/*    */   protected a() {
/* 31 */     super("background-attachment");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 35 */     return (ad)e.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 43 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 47 */     e e = c.c(lu);
/* 48 */     if (e != null) {
/* 49 */       return (ad)e;
/*    */     }
/* 51 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */