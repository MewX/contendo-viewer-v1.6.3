/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.a.f;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class r
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new r();
/*    */   
/*    */   public static byte c(c style) {
/* 23 */     f value = (f)style.a(a);
/* 24 */     return value.b();
/*    */   }
/*    */   
/*    */   protected r() {
/* 28 */     super("-cssj-column-span");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 32 */     return (ad)f.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 40 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 44 */     switch (lu.getLexicalUnitType()) {
/*    */       case 13:
/* 46 */         if (lu.getIntegerValue() == 1) {
/* 47 */           return (ad)f.b;
/*    */         }
/* 49 */         throw new l();
/*    */       
/*    */       case 35:
/* 52 */         ident = lu.getStringValue().toLowerCase();
/* 53 */         if (ident.equals("all")) {
/* 54 */           return (ad)f.c;
/*    */         }
/* 56 */         throw new l();
/*    */     } 
/*    */     
/* 59 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/r.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */