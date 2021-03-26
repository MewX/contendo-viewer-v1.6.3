/*    */ package jp.cssj.homare.impl.a.c.b;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.V;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class g
/*    */   extends b
/*    */ {
/* 21 */   public static final j a = (j)new g();
/*    */   
/*    */   public static String c(c style) {
/* 24 */     ad value = style.a(a);
/* 25 */     if (value.a() == 1007) {
/* 26 */       return null;
/*    */     }
/* 28 */     return ((V)value).b();
/*    */   }
/*    */   
/*    */   private g() {
/* 32 */     super("-cssj-regeneratable");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 36 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 40 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 44 */     return false;
/*    */   } public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     V v;
/*    */     String ident;
/* 48 */     if (lu.getLexicalUnitType() == 12) {
/* 49 */       return (ad)C.a;
/*    */     }
/*    */ 
/*    */     
/* 53 */     short luType = lu.getLexicalUnitType();
/* 54 */     switch (luType) {
/*    */       case 35:
/* 56 */         ident = lu.getStringValue().toLowerCase();
/* 57 */         if (ident.equals("none")) {
/* 58 */           H h = H.a;
/*    */         } else {
/* 60 */           v = new V(lu.getStringValue());
/*    */         } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 72 */         return (ad)v;
/*    */       case 36:
/*    */         return (ad)new V(lu.getStringValue());
/*    */     } 
/*    */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */