/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.k;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class v
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new v();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     k value = (k)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   private v() {
/* 27 */     super("float");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)k.f;
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
/*    */       
/*    */       case 35:
/* 47 */         ident = lu.getStringValue().toLowerCase();
/* 48 */         if (ident.equals("none"))
/* 49 */           return (ad)k.f; 
/* 50 */         if (ident.equals("left"))
/* 51 */           return (ad)k.g; 
/* 52 */         if (ident.equals("right"))
/* 53 */           return (ad)k.h; 
/* 54 */         if (ident.equals("start"))
/* 55 */           return (ad)k.i; 
/* 56 */         if (ident.equals("end")) {
/* 57 */           return (ad)k.j;
/*    */         }
/*    */         break;
/*    */     } 
/* 61 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/v.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */