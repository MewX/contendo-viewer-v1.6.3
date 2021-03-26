/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.D;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class aa
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new aa();
/*    */   
/*    */   public static int c(c style) {
/* 22 */     D value = (D)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   private aa() {
/* 27 */     super("orphans");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)D.c;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     int a;
/* 43 */     switch (lu.getLexicalUnitType()) {
/*    */       case 13:
/* 45 */         a = lu.getIntegerValue();
/* 46 */         return (ad)D.a(a);
/*    */     } 
/*    */ 
/*    */     
/* 50 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/aa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */