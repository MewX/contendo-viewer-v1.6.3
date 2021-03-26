/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.m;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class A
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new A();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     m value = (m)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   private A() {
/* 27 */     super("clear");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)m.a;
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
/* 47 */         if (ident.equals("none"))
/* 48 */           return (ad)m.a; 
/* 49 */         if (ident.equals("left"))
/* 50 */           return (ad)m.b; 
/* 51 */         if (ident.equals("right"))
/* 52 */           return (ad)m.c; 
/* 53 */         if (ident.equals("start"))
/* 54 */           return (ad)m.d; 
/* 55 */         if (ident.equals("end"))
/* 56 */           return (ad)m.e; 
/* 57 */         if (ident.equals("both")) {
/* 58 */           return (ad)m.f;
/*    */         }
/*    */         break;
/*    */     } 
/* 62 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/A.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */