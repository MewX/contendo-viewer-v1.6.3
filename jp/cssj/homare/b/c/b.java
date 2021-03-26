/*    */ package jp.cssj.homare.b.c;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.b.n;
/*    */ import jp.cssj.sakae.c.b.a;
/*    */ import jp.cssj.sakae.c.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class b
/*    */   implements f
/*    */ {
/*    */   protected final Shape g;
/*    */   protected final n h;
/*    */   protected final float i;
/*    */   protected final AffineTransform j;
/*    */   
/*    */   public b(n pageBox, Shape clip, float opacity, AffineTransform transform) {
/* 20 */     this.h = pageBox;
/* 21 */     this.g = clip;
/* 22 */     this.i = opacity;
/* 23 */     this.j = transform;
/*    */   }
/*    */   public final void b(jp.cssj.sakae.c.b gc, double x, double y) throws c {
/*    */     a a1, a2, ggc;
/* 27 */     if (this.g != null || !this.j.isIdentity()) {
/* 28 */       gc.d();
/* 29 */       if (this.g != null) {
/* 30 */         gc.a(this.g);
/*    */       }
/* 32 */       if (!this.j.isIdentity()) {
/* 33 */         gc.a(this.j);
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     float alpha = gc.m();
/* 41 */     if (this.i != 1.0F) {
/*    */       
/* 43 */       jp.cssj.sakae.c.b xgc = gc;
/* 44 */       ggc = gc.a(this.h.p(), this.h.q());
/* 45 */       a1 = ggc;
/*    */     } else {
/* 47 */       a2 = ggc = null;
/* 48 */       a1.b(this.i);
/*    */     } 
/*    */ 
/*    */     
/* 52 */     a((jp.cssj.sakae.c.b)a1, x, y);
/*    */ 
/*    */     
/* 55 */     if (this.i != 1.0F) {
/*    */       
/* 57 */       jp.cssj.sakae.c.b.b gi = ggc.q();
/* 58 */       a2.b(this.i);
/* 59 */       a2.a(gi);
/* 60 */       a2.b(alpha);
/* 61 */       a1 = a2;
/*    */     } else {
/* 63 */       a1.b(alpha);
/*    */     } 
/*    */ 
/*    */     
/* 67 */     if (this.g != null || !this.j.isIdentity())
/* 68 */       a1.e(); 
/*    */   }
/*    */   
/*    */   public abstract void a(jp.cssj.sakae.c.b paramb, double paramDouble1, double paramDouble2) throws c;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */