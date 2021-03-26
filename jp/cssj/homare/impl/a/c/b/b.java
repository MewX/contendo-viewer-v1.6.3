/*    */ package jp.cssj.homare.impl.a.c.b;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new b();
/*    */   
/*    */   public static byte c(c style) {
/* 23 */     ad value = style.a(a);
/* 24 */     return ((jp.cssj.homare.css.f.b.b)value).b();
/*    */   }
/*    */   
/*    */   private b() {
/* 28 */     super("-cssj-direction-mode");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 32 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 36 */     return (ad)jp.cssj.homare.css.f.b.b.d;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 40 */     return true;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 44 */     if (lu.getLexicalUnitType() == 12) {
/* 45 */       return (ad)C.a;
/*    */     }
/* 47 */     short luType = lu.getLexicalUnitType();
/* 48 */     switch (luType) {
/*    */       case 35:
/* 50 */         ident = lu.getStringValue().toLowerCase();
/* 51 */         if (ident.equals("physical"))
/* 52 */           return (ad)jp.cssj.homare.css.f.b.b.d; 
/* 53 */         if (ident.equals("logical") || ident.equals("horizontal-tb"))
/* 54 */           return (ad)jp.cssj.homare.css.f.b.b.e; 
/* 55 */         if (ident.equals("vertical-rl")) {
/* 56 */           return (ad)jp.cssj.homare.css.f.b.b.f;
/*    */         }
/*    */         break;
/*    */     } 
/* 60 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */