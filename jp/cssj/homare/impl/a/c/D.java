/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.ae;
/*    */ import jp.cssj.homare.css.f.o;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class D
/*    */   extends b
/*    */ {
/* 24 */   public static final j a = (j)new D();
/*    */   
/*    */   public static ad[] c(c style) {
/* 27 */     ad value = style.a(a);
/* 28 */     if (value.a() == 1007) {
/* 29 */       return null;
/*    */     }
/* 31 */     return ((ae)value).b();
/*    */   }
/*    */   
/*    */   protected D() {
/* 35 */     super("counter-increment");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 43 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 47 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 51 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/* 53 */         if (l.b(lu) && lu.getNextLexicalUnit() == null) {
/* 54 */           return (ad)H.a;
/*    */         }
/*    */         break;
/*    */     } 
/*    */ 
/*    */     
/* 60 */     List<o> values = new ArrayList<>();
/*    */     
/* 62 */     while (lu != null) {
/*    */       String ident;
/*    */       int delta;
/* 65 */       if (lu.getLexicalUnitType() == 35) {
/* 66 */         ident = lu.getStringValue();
/* 67 */         lu = lu.getNextLexicalUnit();
/*    */       } else {
/* 69 */         throw new l();
/*    */       } 
/* 71 */       if (lu != null && lu.getLexicalUnitType() == 13) {
/* 72 */         delta = lu.getIntegerValue();
/* 73 */         lu = lu.getNextLexicalUnit();
/*    */       } else {
/* 75 */         delta = 1;
/*    */       } 
/* 77 */       values.add(new o(ident, delta));
/*    */     } 
/* 79 */     if (values.isEmpty()) {
/* 80 */       return (ad)H.a;
/*    */     }
/* 82 */     ae fvalues = new ae(values.<ad>toArray(new ad[values.size()]));
/* 83 */     return (ad)fvalues;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */