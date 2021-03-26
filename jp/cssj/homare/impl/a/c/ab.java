/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.J;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ab
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new ab();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     J value = (J)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   private ab() {
/* 27 */     super("overflow");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)J.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 43 */     short luType = lu.getLexicalUnitType();
/* 44 */     switch (luType) {
/*    */       case 35:
/* 46 */         ident = lu.getStringValue().toLowerCase();
/* 47 */         if (ident.equals("visible"))
/* 48 */           return (ad)J.a; 
/* 49 */         if (ident.equals("hidden"))
/* 50 */           return (ad)J.b; 
/* 51 */         if (ident.equals("scroll"))
/* 52 */           return (ad)J.d; 
/* 53 */         if (ident.equals("auto")) {
/* 54 */           return (ad)J.c;
/*    */         }
/*    */         break;
/*    */     } 
/* 58 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */