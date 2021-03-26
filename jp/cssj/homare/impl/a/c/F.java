/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.s;
/*    */ import jp.cssj.homare.impl.a.c.a.c;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class F
/*    */   extends b
/*    */ {
/* 22 */   public static final j a = (j)new F();
/*    */   
/*    */   public static byte c(c style) {
/* 25 */     switch (c.c(style)) {
/*    */       
/*    */       case 1:
/* 28 */         switch (d(style)) {
/*    */           case 1:
/* 30 */             return 1;
/*    */           case 2:
/* 32 */             return 2;
/*    */         } 
/* 34 */         throw new IllegalStateException();
/*    */ 
/*    */       
/*    */       case 2:
/*    */       case 3:
/* 39 */         return 3;
/*    */     } 
/* 41 */     throw new IllegalStateException();
/*    */   }
/*    */ 
/*    */   
/*    */   public static byte d(c style) {
/* 46 */     s value = (s)style.a(a);
/* 47 */     return value.b();
/*    */   }
/*    */   
/*    */   private F() {
/* 51 */     super("direction");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 55 */     return (ad)s.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 63 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 67 */     short luType = lu.getLexicalUnitType();
/* 68 */     switch (luType) {
/*    */       case 35:
/* 70 */         ident = lu.getStringValue().toLowerCase();
/* 71 */         if (ident.equals("ltr"))
/* 72 */           return (ad)s.a; 
/* 73 */         if (ident.equals("rtl"))
/* 74 */           return (ad)s.b; 
/*    */         break;
/*    */     } 
/* 77 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/F.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */