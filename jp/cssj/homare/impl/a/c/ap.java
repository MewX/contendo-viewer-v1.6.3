/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.Z;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ap
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new ap();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     Z value = (Z)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   protected ap() {
/* 27 */     super("text-transform");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)Z.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 43 */     if (lu.getLexicalUnitType() == 35) {
/* 44 */       String ident = lu.getStringValue().toLowerCase();
/* 45 */       if (ident.equals("none"))
/* 46 */         return (ad)Z.a; 
/* 47 */       if (ident.equals("capitalize"))
/* 48 */         return (ad)Z.b; 
/* 49 */       if (ident.equals("uppercase"))
/* 50 */         return (ad)Z.c; 
/* 51 */       if (ident.equals("lowercase")) {
/* 52 */         return (ad)Z.d;
/*    */       }
/*    */     } 
/* 55 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */