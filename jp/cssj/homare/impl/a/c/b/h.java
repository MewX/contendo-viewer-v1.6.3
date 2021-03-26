/*    */ package jp.cssj.homare.impl.a.c.b;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.impl.a.c.G;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */   extends b
/*    */ {
/* 21 */   public static final j a = (j)new h();
/*    */   
/*    */   public static byte c(c style) {
/* 24 */     jp.cssj.homare.css.f.b.h value = (jp.cssj.homare.css.f.b.h)style.a(a);
/* 25 */     return value.b();
/*    */   }
/*    */   
/*    */   protected h() {
/* 29 */     super("-cssj-ruby");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 33 */     byte ruby = ((jp.cssj.homare.css.f.b.h)value).b();
/* 34 */     byte display = G.c(style);
/* 35 */     switch (ruby)
/*    */     
/*    */     { 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       case 0:
/* 56 */         return value;case 1: if (display != 3)
/*    */           return (ad)jp.cssj.homare.css.f.b.h.e; case 2: if (display != 1)
/*    */           return (ad)jp.cssj.homare.css.f.b.h.e; 
/*    */       case 3: if (display != 1)
/* 60 */           return (ad)jp.cssj.homare.css.f.b.h.e;  }  throw new IllegalStateException(); } public ad b(c style) { return (ad)jp.cssj.homare.css.f.b.h.e; }
/*    */ 
/*    */   
/*    */   public boolean c() {
/* 64 */     return false;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 68 */     short luType = lu.getLexicalUnitType();
/* 69 */     switch (luType) {
/*    */       case 35:
/* 71 */         ident = lu.getStringValue().toLowerCase();
/* 72 */         if (ident.equals("none"))
/* 73 */           return (ad)jp.cssj.homare.css.f.b.h.e; 
/* 74 */         if (ident.equals("ruby"))
/* 75 */           return (ad)jp.cssj.homare.css.f.b.h.f; 
/* 76 */         if (ident.equals("rb"))
/* 77 */           return (ad)jp.cssj.homare.css.f.b.h.g; 
/* 78 */         if (ident.equals("rt")) {
/* 79 */           return (ad)jp.cssj.homare.css.f.b.h.h;
/*    */         }
/*    */         break;
/*    */     } 
/* 83 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */