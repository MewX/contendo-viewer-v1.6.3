/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.ah;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class au
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new au();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     return ((ah)style.a(a)).b();
/*    */   }
/*    */   
/*    */   protected au() {
/* 26 */     super("white-space");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 30 */     return (ad)ah.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 38 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 42 */     if (lu.getLexicalUnitType() == 35) {
/* 43 */       ah ah; String ident = lu.getStringValue().toLowerCase();
/*    */       
/* 45 */       if (ident.equals("normal")) {
/* 46 */         ah = ah.a;
/* 47 */       } else if (ident.equals("pre")) {
/* 48 */         ah = ah.b;
/* 49 */       } else if (ident.equals("nowrap")) {
/* 50 */         ah = ah.c;
/* 51 */       } else if (ident.equals("pre-wrap")) {
/* 52 */         ah = ah.d;
/* 53 */       } else if (ident.equals("pre-line")) {
/* 54 */         ah = ah.e;
/*    */       } else {
/* 56 */         throw new l();
/*    */       } 
/* 58 */       return (ad)ah;
/*    */     } 
/*    */     
/* 61 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/au.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */