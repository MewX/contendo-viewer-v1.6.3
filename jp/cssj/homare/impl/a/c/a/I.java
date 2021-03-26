/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.a.m;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class I
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new I();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     return ((m)style.a(a)).b();
/*    */   }
/*    */   
/*    */   protected I() {
/* 26 */     super("word-break");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 30 */     return (ad)m.d;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 38 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 42 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/* 44 */         ident = lu.getStringValue().toLowerCase();
/* 45 */         if (ident.equals("normal"))
/* 46 */           return (ad)m.d; 
/* 47 */         if (ident.equals("break-all"))
/* 48 */           return (ad)m.q; 
/* 49 */         if (ident.equals("keep-all")) {
/* 50 */           return (ad)m.r;
/*    */         }
/*    */         break;
/*    */     } 
/* 54 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/I.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */