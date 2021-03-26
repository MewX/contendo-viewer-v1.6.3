/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.T;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.d;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class B
/*    */   extends b
/*    */ {
/* 23 */   public static final j a = (j)new B();
/*    */   
/*    */   public static T c(c style) {
/* 26 */     ad value = style.a(a);
/* 27 */     return (value.a() == 1006) ? null : (T)value;
/*    */   }
/*    */   
/*    */   private B() {
/* 31 */     super("clip");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 35 */     return (ad)d.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 43 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 47 */     if (l.a(lu)) {
/* 48 */       return (ad)d.a;
/*    */     }
/* 50 */     if (lu.getLexicalUnitType() == 38) {
/*    */       E top, left, bottom, right;
/* 52 */       lu = lu.getParameters();
/*    */       
/* 54 */       if (l.a(lu)) {
/* 55 */         a a = a.a;
/*    */       } else {
/* 57 */         top = l.a(ua, lu);
/*    */       } 
/* 59 */       if (top == null) {
/* 60 */         throw new l(String.valueOf(lu));
/*    */       }
/*    */       
/* 63 */       lu = lu.getNextLexicalUnit().getNextLexicalUnit();
/* 64 */       if (l.a(lu)) {
/* 65 */         a a = a.a;
/*    */       } else {
/* 67 */         left = l.a(ua, lu);
/*    */       } 
/* 69 */       if (left == null) {
/* 70 */         throw new l(String.valueOf(lu));
/*    */       }
/*    */       
/* 73 */       lu = lu.getNextLexicalUnit().getNextLexicalUnit();
/* 74 */       if (l.a(lu)) {
/* 75 */         a a = a.a;
/*    */       } else {
/* 77 */         bottom = l.a(ua, lu);
/*    */       } 
/* 79 */       if (bottom == null) {
/* 80 */         throw new l(String.valueOf(lu));
/*    */       }
/*    */       
/* 83 */       lu = lu.getNextLexicalUnit().getNextLexicalUnit();
/* 84 */       if (l.a(lu)) {
/* 85 */         a a = a.a;
/*    */       } else {
/* 87 */         right = l.a(ua, lu);
/*    */       } 
/* 89 */       if (right == null) {
/* 90 */         throw new l(String.valueOf(lu));
/*    */       }
/*    */       
/* 93 */       T rect = new T(top, left, bottom, right);
/* 94 */       return (ad)rect;
/*    */     } 
/* 96 */     throw new l(String.valueOf(lu));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/B.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */