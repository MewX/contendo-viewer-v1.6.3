/*    */ package jp.cssj.homare.impl.a.c.b;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new a();
/*    */   
/*    */   public static jp.cssj.homare.css.f.b.a c(c style) {
/* 22 */     jp.cssj.homare.css.f.b.a value = (jp.cssj.homare.css.f.b.a)style.a(a);
/* 23 */     return value;
/*    */   }
/*    */   
/*    */   protected a() {
/* 27 */     super("-cssj-break-characters");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 31 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 35 */     return (ad)jp.cssj.homare.css.f.b.a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 39 */     return true;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String head, tail, ident;
/* 43 */     short luType = lu.getLexicalUnitType();
/* 44 */     switch (luType) {
/*    */       case 36:
/* 46 */         head = lu.getStringValue();
/*    */         
/* 48 */         lu = lu.getNextLexicalUnit();
/* 49 */         if (lu == null) {
/* 50 */           tail = "";
/* 51 */         } else if (lu.getLexicalUnitType() == 36) {
/* 52 */           tail = lu.getStringValue();
/* 53 */           lu = lu.getNextLexicalUnit();
/* 54 */           if (lu != null) {
/* 55 */             throw new l();
/*    */           }
/*    */         } else {
/* 58 */           throw new l();
/*    */         } 
/* 60 */         return (ad)new jp.cssj.homare.css.f.b.a(head, tail);
/*    */       
/*    */       case 35:
/* 63 */         ident = lu.getStringValue().toLowerCase();
/* 64 */         if (ident.equals("none")) {
/* 65 */           return (ad)jp.cssj.homare.css.f.b.a.a;
/*    */         }
/*    */         break;
/*    */     } 
/* 69 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */