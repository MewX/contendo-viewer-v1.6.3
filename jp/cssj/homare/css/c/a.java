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
/*    */ 
/*    */ public abstract class a
/*    */   extends c
/*    */   implements j
/*    */ {
/*    */   protected a(String name) {
/* 22 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract j[] a();
/*    */   
/*    */   protected abstract e.a[] a(LexicalUnit paramLexicalUnit, m paramm, URI paramURI) throws l;
/*    */   
/*    */   public final k a(LexicalUnit lu, m ua, URI uri, boolean important) throws l {
/*    */     e.a[] entries;
/* 32 */     if (lu.getLexicalUnitType() == 12) {
/*    */       
/* 34 */       j[] primitives = a();
/* 35 */       entries = new e.a[primitives.length];
/* 36 */       for (int i = 0; i < entries.length; i++) {
/* 37 */         entries[i] = new e.a(primitives[i], (ad)C.a);
/*    */       }
/*    */     } else {
/* 40 */       entries = a(lu, ua, uri);
/*    */     } 
/* 42 */     return new e(b(), entries, uri, important);
/*    */   }
/*    */   
/*    */   public j a(c style) {
/* 46 */     return this;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */