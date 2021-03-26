/*    */ package jp.cssj.homare.css.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class b
/*    */   extends c
/*    */   implements j
/*    */ {
/*    */   protected b(String name) {
/* 21 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public final k a(LexicalUnit lu, m ua, URI uri, boolean important) throws l {
/*    */     ad value;
/* 27 */     if (lu.getLexicalUnitType() == 12) {
/*    */       
/* 29 */       C c1 = C.a;
/*    */     } else {
/* 31 */       value = a(lu, ua, uri);
/*    */     } 
/* 33 */     return new i(this, value, uri, important);
/*    */   }
/*    */   
/*    */   public j a(c style) {
/* 37 */     return this;
/*    */   }
/*    */   
/*    */   public abstract ad a(LexicalUnit paramLexicalUnit, m paramm, URI paramURI) throws l;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */