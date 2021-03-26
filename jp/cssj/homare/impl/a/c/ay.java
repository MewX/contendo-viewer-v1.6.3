/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.D;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.d;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ay
/*    */   extends b
/*    */ {
/* 22 */   public static final j a = (j)new ay();
/*    */   
/*    */   public static int c(c style) {
/* 25 */     ad value = style.a(a);
/* 26 */     return ((D)value).b();
/*    */   }
/*    */   
/*    */   public static byte d(c style) {
/* 30 */     ad value = style.a(a);
/* 31 */     if (value.a() == 1006 || y.c(style) == 0) {
/* 32 */       return 0;
/*    */     }
/* 34 */     return 1;
/*    */   }
/*    */   
/*    */   private ay() {
/* 38 */     super("z-index");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 42 */     return (ad)d.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 50 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 54 */     short luType = lu.getLexicalUnitType();
/* 55 */     switch (luType) {
/*    */       case 35:
/* 57 */         ident = lu.getStringValue().toLowerCase();
/* 58 */         if (ident.equals("auto")) {
/* 59 */           return (ad)D.a;
/*    */         }
/*    */         break;
/*    */       
/*    */       case 13:
/* 64 */         return (ad)D.a(lu.getIntegerValue());
/*    */     } 
/* 66 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */