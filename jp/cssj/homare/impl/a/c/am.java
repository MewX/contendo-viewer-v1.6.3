/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.j;
/*    */ import jp.cssj.homare.css.f.X;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class am
/*    */   extends b
/*    */ {
/* 21 */   public static final j a = (j)new am();
/*    */   
/*    */   public static byte c(c style) {
/* 24 */     X value = (X)style.a(a);
/* 25 */     return j.a(value, style);
/*    */   }
/*    */   
/*    */   protected am() {
/* 29 */     super("text-align");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 33 */     return (ad)X.l;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 41 */     return value;
/*    */   } public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/*    */     X value;
/* 45 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/* 47 */         ident = lu.getStringValue().toLowerCase();
/* 48 */         value = j.a(ident);
/* 49 */         if (value != null) {
/* 50 */           return (ad)value;
/*    */         }
/*    */         break;
/*    */     } 
/* 54 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/am.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */