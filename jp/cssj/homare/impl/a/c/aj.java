/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ import jp.cssj.homare.css.b.a;
/*    */ import jp.cssj.homare.css.b.b;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.Q;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.ae;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class aj
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new aj();
/*    */   
/*    */   public static ad[] c(c style) {
/* 28 */     ad value = style.a(a);
/* 29 */     if (value.a() == 1007) {
/* 30 */       return null;
/*    */     }
/* 32 */     return ((ae)value).b();
/*    */   }
/*    */   
/*    */   protected aj() {
/* 36 */     super("quotes");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 40 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 44 */     a lang = b.a((style.a()).G);
/* 45 */     return (ad)lang.b();
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 53 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/* 55 */         if (l.b(lu)) {
/* 56 */           return (ad)H.a;
/*    */         }
/* 58 */         throw new l();
/*    */     } 
/*    */ 
/*    */     
/* 62 */     ArrayList<Q> values = new ArrayList<>();
/*    */     
/* 64 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/* 65 */       String open; switch (lu.getLexicalUnitType()) {
/*    */         case 36:
/* 67 */           open = lu.getStringValue();
/* 68 */           lu = lu.getNextLexicalUnit();
/* 69 */           if (lu != null && lu.getLexicalUnitType() == 36) {
/* 70 */             values.add(new Q(open, lu.getStringValue())); break;
/*    */           } 
/* 72 */           throw new l();
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         default:
/* 78 */           throw new l();
/*    */       } 
/*    */     } 
/* 81 */     if (values.isEmpty()) {
/* 82 */       return (ad)H.a;
/*    */     }
/* 84 */     ae fvalues = new ae(values.<ad>toArray(new ad[values.size()]));
/* 85 */     return (ad)fvalues;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/aj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */