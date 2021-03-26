/*    */ package jp.cssj.homare.b.a;
/*    */ 
/*    */ import jp.cssj.homare.b.a.c.G;
/*    */ import jp.cssj.homare.b.a.c.s;
/*    */ import jp.cssj.homare.b.a.c.y;
/*    */ import jp.cssj.homare.b.f.e;
/*    */ 
/*    */ public abstract class d
/*    */   extends b implements o {
/*    */   protected final s a;
/*    */   protected G b;
/*    */   
/*    */   public d(s params) {
/* 14 */     this.a = params;
/*    */   }
/*    */   protected double c; protected double d;
/*    */   public final y b() {
/* 18 */     return (y)this.a;
/*    */   }
/*    */   
/*    */   public final s c() {
/* 22 */     return this.a;
/*    */   }
/*    */   
/*    */   public final void a(G tableParams) {
/* 26 */     this.b = tableParams;
/*    */   }
/*    */   
/*    */   public final double e() {
/* 30 */     return this.c;
/*    */   }
/*    */   
/*    */   public final double f() {
/* 34 */     return this.d;
/*    */   }
/*    */   
/*    */   public final double p() {
/* 38 */     return e.a(this.b.D) ? this.d : this.c;
/*    */   }
/*    */   
/*    */   public final double q() {
/* 42 */     return e.a(this.b.D) ? this.c : this.d;
/*    */   }
/*    */   
/*    */   public final double s() {
/* 46 */     return p();
/*    */   }
/*    */   
/*    */   public final double t() {
/* 50 */     return q();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */