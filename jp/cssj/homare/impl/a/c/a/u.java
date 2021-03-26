/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.S;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class u
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new u();
/*    */   
/*    */   public static float c(c style) {
/* 22 */     S real = (S)style.a(a);
/* 23 */     return (float)real.b();
/*    */   }
/*    */   
/*    */   private u() {
/* 27 */     super("opacity");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)S.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     c parent = style.c();
/* 40 */     if (parent == null) {
/* 41 */       return value;
/*    */     }
/* 43 */     S real = (S)value;
/* 44 */     return (ad)S.a(c(parent) * real.b());
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     float op;
/* 48 */     short luType = lu.getLexicalUnitType();
/* 49 */     switch (luType) {
/*    */       case 13:
/* 51 */         op = lu.getIntegerValue();
/* 52 */         if (op >= 0.0F && op <= 1.0F) {
/* 53 */           return (ad)S.a(op);
/*    */         }
/* 55 */         throw new l();
/*    */       
/*    */       case 14:
/* 58 */         op = lu.getFloatValue();
/* 59 */         if (op >= 0.0F && op <= 1.0F) {
/* 60 */           return (ad)S.a(op);
/*    */         }
/* 62 */         throw new l();
/*    */     } 
/*    */     
/* 65 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */