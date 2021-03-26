/*    */ package jp.cssj.sakae.c.b.a;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.b.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends c
/*    */ {
/*    */   private final AffineTransform c;
/*    */   
/*    */   public a(b image, AffineTransform at) {
/* 19 */     super(image);
/* 20 */     if (!b && image == null) throw new AssertionError(); 
/* 21 */     if (!b && at == null) throw new AssertionError(); 
/* 22 */     this.c = at;
/*    */   }
/*    */   
/*    */   public AffineTransform e() {
/* 26 */     return this.c;
/*    */   }
/*    */   
/*    */   public void a(b gc) {
/* 30 */     gc.d();
/* 31 */     gc.a(this.c);
/* 32 */     this.a.a(gc);
/* 33 */     gc.e();
/*    */   }
/*    */   
/*    */   public String c() {
/* 37 */     return this.a.c();
/*    */   }
/*    */   
/*    */   public double a() {
/* 41 */     return this.a.a() * this.c.getScaleX();
/*    */   }
/*    */   
/*    */   public double b() {
/* 45 */     return this.a.b() * this.c.getScaleY();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 49 */     return super.toString() + "/image=" + this.a + ",at=" + this.c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/b/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */