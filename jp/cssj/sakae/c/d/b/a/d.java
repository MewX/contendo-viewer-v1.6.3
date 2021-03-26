/*    */ package jp.cssj.sakae.c.d.b.a;
/*    */ 
/*    */ public class d
/*    */   extends a
/*    */ {
/*    */   private final jp.cssj.sakae.c.a.d a;
/*    */   private final int b;
/*  8 */   private double c = 0.0D;
/*    */   
/*    */   public d(jp.cssj.sakae.c.a.d flm, int charOffset) {
/* 11 */     this.a = flm;
/* 12 */     this.c = this.a.a(0).g();
/* 13 */     this.b = charOffset;
/*    */   }
/*    */   
/*    */   public int b() {
/* 17 */     return this.b;
/*    */   }
/*    */   
/*    */   public char e() {
/* 21 */     return ' ';
/*    */   }
/*    */   
/*    */   public double c() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public void a(double wordSpacing) {
/* 29 */     this.c = wordSpacing + this.a.a(0).g();
/*    */   }
/*    */   
/*    */   public void h() {
/* 33 */     this.c = 0.0D;
/*    */   }
/*    */   
/*    */   public double f() {
/* 37 */     return this.a.c();
/*    */   }
/*    */   
/*    */   public double g() {
/* 41 */     return this.a.d();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 45 */     return "[SPACE]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/b/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */