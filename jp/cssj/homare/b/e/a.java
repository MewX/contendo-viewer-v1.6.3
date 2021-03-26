/*    */ package jp.cssj.homare.b.e;
/*    */ 
/*    */ import jp.cssj.homare.b.f.e;
/*    */ 
/*    */ 
/*    */ public class a
/*    */ {
/*    */   public double a;
/*    */   public double b;
/*    */   public double c;
/*    */   public double d;
/*    */   
/*    */   public a() {}
/*    */   
/*    */   public a(double top, double right, double bottom, double left) {
/* 16 */     if (!e && e.a(top)) throw new AssertionError(); 
/* 17 */     if (!e && e.a(right)) throw new AssertionError(); 
/* 18 */     if (!e && e.a(bottom)) throw new AssertionError(); 
/* 19 */     if (!e && e.a(left)) throw new AssertionError(); 
/* 20 */     this.a = top;
/* 21 */     this.b = right;
/* 22 */     this.c = bottom;
/* 23 */     this.d = left;
/*    */   }
/*    */   
/*    */   public double a() {
/* 27 */     return this.d + this.b;
/*    */   }
/*    */   
/*    */   public double b() {
/* 31 */     return this.a + this.c;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return (this.a == 0.0D && this.b == 0.0D && this.c == 0.0D && this.d == 0.0D);
/*    */   }
/*    */   
/*    */   public void a(a insets) {
/* 39 */     this.a = insets.a;
/* 40 */     this.b = insets.b;
/* 41 */     this.c = insets.c;
/* 42 */     this.d = insets.d;
/*    */   }
/*    */   
/*    */   public a a(boolean top, boolean right, boolean bottom, boolean left) {
/* 46 */     a insets = c() ? this : new a(top ? this.a : 0.0D, right ? this.b : 0.0D, bottom ? this.c : 0.0D, left ? this.d : 0.0D);
/*    */ 
/*    */     
/* 49 */     return insets;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 53 */     return super.toString() + "[top=" + this.a + ",right=" + this.b + ",bottom=" + this.c + ",left=" + this.d + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/e/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */