/*    */ package jp.cssj.homare.css.e;
/*    */ 
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.f.X;
/*    */ import jp.cssj.homare.impl.a.c.F;
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
/*    */ public final class j
/*    */ {
/*    */   public static X a(String ident) {
/* 25 */     if (ident.equals("left"))
/* 26 */       return X.h; 
/* 27 */     if (ident.equals("right"))
/* 28 */       return X.i; 
/* 29 */     if (ident.equals("center"))
/* 30 */       return X.j; 
/* 31 */     if (ident.equals("justify"))
/* 32 */       return X.k; 
/* 33 */     if (ident.equals("start"))
/* 34 */       return X.l; 
/* 35 */     if (ident.equals("end"))
/* 36 */       return X.m; 
/* 37 */     if (ident.equals("-cssj-justify-center")) {
/* 38 */       return X.n;
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public static byte a(X value, c style) {
/* 44 */     byte textAlign = value.b();
/* 45 */     switch (textAlign) {
/*    */       case 1:
/* 47 */         if (F.d(style) == 2) {
/* 48 */           return 3;
/*    */         }
/* 50 */         return 1;
/*    */       case 2:
/* 52 */         if (F.d(style) == 2) {
/* 53 */           return 1;
/*    */         }
/* 55 */         return 3;
/*    */       case 3:
/* 57 */         return 2;
/*    */       case 4:
/* 59 */         return 4;
/*    */       case 5:
/* 61 */         return 1;
/*    */       case 6:
/* 63 */         return 3;
/*    */       case 101:
/* 65 */         return 101;
/*    */     } 
/* 67 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */