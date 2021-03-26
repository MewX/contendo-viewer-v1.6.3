/*    */ package jp.cssj.homare.css.e;
/*    */ 
/*    */ import jp.cssj.homare.css.f.L;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class g
/*    */ {
/*    */   public static L a(LexicalUnit lu) {
/* 16 */     if (lu.getLexicalUnitType() != 35) {
/* 17 */       return null;
/*    */     }
/* 19 */     String ident = lu.getStringValue().toLowerCase();
/* 20 */     if (ident.equals("auto"))
/* 21 */       return L.n; 
/* 22 */     if (ident.equals("always"))
/* 23 */       return L.o; 
/* 24 */     if (ident.equals("avoid"))
/* 25 */       return L.p; 
/* 26 */     if (ident.equals("left"))
/* 27 */       return L.q; 
/* 28 */     if (ident.equals("right"))
/* 29 */       return L.r; 
/* 30 */     if (ident.equals("page"))
/* 31 */       return L.s; 
/* 32 */     if (ident.equals("column")) {
/* 33 */       return L.t;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 38 */     if (ident.equals("verso") || ident.equals("even"))
/* 39 */       return L.u; 
/* 40 */     if (ident.equals("recto") || ident.equals("odd"))
/* 41 */       return L.v; 
/* 42 */     if (ident.equals("if-left"))
/* 43 */       return L.w; 
/* 44 */     if (ident.equals("if-right"))
/* 45 */       return L.x; 
/* 46 */     if (ident.equals("if-verso"))
/* 47 */       return L.y; 
/* 48 */     if (ident.equals("if-recto")) {
/* 49 */       return L.z;
/*    */     }
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */