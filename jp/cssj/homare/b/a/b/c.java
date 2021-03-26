/*    */ package jp.cssj.homare.b.a.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.c.f;
/*    */ import jp.cssj.homare.b.a.c.n;
/*    */ import jp.cssj.homare.b.a.c.y;
/*    */ import jp.cssj.homare.b.a.e;
/*    */ import jp.cssj.homare.b.c.d;
/*    */ import jp.cssj.homare.b.c.f;
/*    */ import jp.cssj.homare.b.c.g;
/*    */ import jp.cssj.homare.b.g.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends e
/*    */ {
/*    */   protected final n o;
/*    */   
/*    */   public c(n params) {
/* 26 */     this.o = params;
/* 27 */     a(null);
/*    */   }
/*    */   
/*    */   public final f g() {
/* 31 */     return (f)this.o;
/*    */   }
/*    */   
/*    */   public final jp.cssj.homare.b.a.c.c c() {
/* 35 */     return (jp.cssj.homare.b.a.c.c)this.o;
/*    */   }
/*    */   
/*    */   public final y b() {
/* 39 */     return (y)this.o;
/*    */   }
/*    */   
/*    */   public final boolean j() {
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public final void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 49 */     if (this.o.ao != 0.0F && this.o.S.c()) {
/*    */       
/* 51 */       d d = new d(clip, pageBox, this.o.ao, transform, this.o.S, p(), q());
/* 52 */       drawer.a((f)d, x, y);
/*    */     } 
/* 54 */     super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return "[FirstLineBox]" + super.toString() + "[/FirstLineBox]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */