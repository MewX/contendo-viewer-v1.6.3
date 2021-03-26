/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.a.b;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new i();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     return ((b)style.a(a)).b();
/*    */   }
/*    */   
/*    */   protected i() {
/* 26 */     super("box-sizing");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 30 */     return (ad)b.c;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 34 */     return false;
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
/* 45 */         if (ident.equals("content-box"))
/* 46 */           return (ad)b.c; 
/* 47 */         if (ident.equals("border-box")) {
/* 48 */           return (ad)b.d;
/*    */         }
/*    */         break;
/*    */     } 
/* 52 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */