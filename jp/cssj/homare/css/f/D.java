/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class D
/*    */   implements O, ad
/*    */ {
/*    */   private final int e;
/* 10 */   public static final D a = new D(0);
/*    */   
/* 12 */   public static final D b = new D(1);
/*    */   
/* 14 */   public static final D c = new D(2);
/*    */   
/* 16 */   public static final D d = new D(3);
/*    */   
/*    */   public static D a(int a) {
/* 19 */     switch (a) {
/*    */       case 0:
/* 21 */         return D.a;
/*    */       case 1:
/* 23 */         return b;
/*    */       case 2:
/* 25 */         return c;
/*    */       case 3:
/* 27 */         return d;
/*    */     } 
/* 29 */     return new D(a);
/*    */   }
/*    */ 
/*    */   
/*    */   private D(int intValue) {
/* 34 */     this.e = intValue;
/*    */   }
/*    */   
/*    */   public short a() {
/* 38 */     return 1016;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 42 */     return (this.e < 0);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 46 */     return (this.e == 0);
/*    */   }
/*    */   
/*    */   public int b() {
/* 50 */     return this.e;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     return String.valueOf(this.e);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */