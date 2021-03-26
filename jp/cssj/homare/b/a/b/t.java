/*    */ package jp.cssj.homare.b.a.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.c.F;
/*    */ import jp.cssj.homare.b.a.c.s;
/*    */ import jp.cssj.homare.b.a.c.z;
/*    */ import jp.cssj.homare.b.a.d;
/*    */ import jp.cssj.homare.b.a.j;
/*    */ import jp.cssj.homare.b.a.m;
/*    */ import jp.cssj.homare.b.c.c;
/*    */ import jp.cssj.homare.b.c.f;
/*    */ import jp.cssj.homare.b.c.g;
/*    */ import jp.cssj.homare.b.g.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class t
/*    */   extends d
/*    */ {
/*    */   protected final F e;
/*    */   
/*    */   public t(s params, F pos) {
/* 27 */     super(params);
/* 28 */     this.e = pos;
/*    */   }
/*    */   
/*    */   public byte a() {
/* 32 */     return 9;
/*    */   }
/*    */   
/*    */   public final z b_() {
/* 36 */     return (z)this.e;
/*    */   }
/*    */   
/*    */   public final F g() {
/* 40 */     return this.e;
/*    */   }
/*    */   
/*    */   public final void a(double lineSize) {
/* 44 */     this.c = lineSize;
/*    */   }
/*    */   
/*    */   public final void b(double pageSize) {
/* 48 */     this.d = pageSize;
/*    */   }
/*    */ 
/*    */   
/*    */   public final void a(m containerBox) {}
/*    */ 
/*    */   
/*    */   public void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/* 56 */     if (this.a.ao == 0.0F) {
/*    */       return;
/*    */     }
/* 59 */     if (!this.a.a.c()) {
/*    */       return;
/*    */     }
/*    */     
/* 63 */     c c = new c(pageBox, clip, this.a.ao, transform, this.a.a, this.a.b, p(), q());
/* 64 */     drawer.a((f)c, x, y);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public final void a(StringBuffer textBuff) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public final void a(n pageBox, g drawer, a visitor, Shape clip, double contextX, double contextY, double x, double y) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 78 */     visitor.a(transform, (j)this, x, y);
/*    */   }
/*    */   
/*    */   public t a(double prevPageSize, double nextPageSize) {
/* 82 */     this.d = prevPageSize;
/* 83 */     t column = new t(this.a, this.e);
/* 84 */     column.a(this.b);
/* 85 */     column.c = this.c;
/* 86 */     column.d = nextPageSize;
/* 87 */     return column;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/t.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */