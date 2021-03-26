/*    */ package jp.cssj.homare.b.a.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.c;
/*    */ import jp.cssj.homare.b.a.c.i;
/*    */ import jp.cssj.homare.b.a.c.r;
/*    */ import jp.cssj.homare.b.a.c.u;
/*    */ import jp.cssj.homare.b.b.d;
/*    */ import jp.cssj.homare.b.c.g;
/*    */ import jp.cssj.homare.b.f.e;
/*    */ import jp.cssj.homare.b.g.a;
/*    */ 
/*    */ public class m
/*    */   extends h
/*    */ {
/*    */   private double J;
/*    */   
/*    */   public m(i params, r pos) {
/* 20 */     super(params, pos);
/* 21 */     params.K = 3;
/* 22 */     params.h = u.d;
/*    */   }
/*    */   
/*    */   public void a(c containerBox) {
/* 26 */     super.a(containerBox);
/* 27 */     if (e.a(this.a.D)) {
/* 28 */       this.g = 0.0D;
/*    */     } else {
/* 30 */       this.f = 0.0D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a(d builder, double minLineAxis, double maxLineAxis, boolean table) {
/* 35 */     super.a(builder, minLineAxis, maxLineAxis, table);
/* 36 */     this.J = maxLineAxis;
/* 37 */     c containerBox = builder.k();
/* 38 */     if (e.a(this.a.D)) {
/* 39 */       this.J += containerBox.m().a();
/* 40 */       this.g = 0.0D;
/*    */     } else {
/* 42 */       this.J += containerBox.m().b();
/* 43 */       this.f = 0.0D;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 49 */     if (e.a(this.a.D)) {
/* 50 */       y -= this.J;
/*    */     } else {
/* 52 */       x -= this.J;
/*    */     } 
/* 54 */     super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */