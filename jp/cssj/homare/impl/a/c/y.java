/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.N;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class y
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new y();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     N value = (N)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   private y() {
/* 27 */     super("position");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)N.f;
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
/* 48 */         if (ident.equals("static"))
/* 49 */           return (ad)N.f; 
/* 50 */         if (ident.equals("relative"))
/* 51 */           return (ad)N.g; 
/* 52 */         if (ident.equals("absolute"))
/* 53 */           return (ad)N.h; 
/* 54 */         if (ident.equals("fixed")) {
/* 55 */           return (ad)N.i;
/*    */         }
/*    */         break;
/*    */     } 
/* 59 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */