/*    */ package jp.cssj.homare.b.a.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.a;
/*    */ import jp.cssj.homare.b.a.a.g;
/*    */ import jp.cssj.homare.b.a.c.e;
/*    */ import jp.cssj.homare.b.a.c.i;
/*    */ import jp.cssj.homare.b.a.c.m;
/*    */ import jp.cssj.homare.b.a.c.o;
/*    */ import jp.cssj.homare.b.a.c.z;
/*    */ import jp.cssj.homare.b.a.g;
/*    */ import jp.cssj.homare.b.a.k;
/*    */ import jp.cssj.homare.b.c.g;
/*    */ import jp.cssj.homare.b.e.b;
/*    */ import jp.cssj.homare.b.g.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */   extends g
/*    */   implements k
/*    */ {
/*    */   protected final o I;
/*    */   
/*    */   public d(i params, o pos) {
/* 30 */     super(params);
/* 31 */     this.I = pos;
/*    */   }
/*    */ 
/*    */   
/*    */   protected d(i params, o pos, m size, m minSize, b frame, g container) {
/* 36 */     super(params, size, minSize, frame, container);
/* 37 */     this.I = pos;
/*    */   }
/*    */   
/*    */   public final z b_() {
/* 41 */     return (z)this.I;
/*    */   }
/*    */   
/*    */   public final e u() {
/* 45 */     return (e)this.I;
/*    */   }
/*    */   
/*    */   public final o c() {
/* 49 */     return this.I;
/*    */   }
/*    */ 
/*    */   
/*    */   public final void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 54 */     if (this.a.an == 1) {
/* 55 */       g newDrawer = new g(this.a.am);
/* 56 */       drawer.a(newDrawer);
/* 57 */       drawer = newDrawer;
/*    */     } 
/*    */     
/* 60 */     a(pageBox, drawer, clip, transform, x, y);
/* 61 */     super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   protected final a a(m nextSize, m nextMinSize, b nextFrame, g container) {
/* 66 */     return (a)new d(this.a, c(), nextSize, nextMinSize, nextFrame, container);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */