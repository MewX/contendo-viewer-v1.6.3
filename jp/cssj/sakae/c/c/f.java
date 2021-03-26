/*    */ package jp.cssj.sakae.c.c;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   implements e
/*    */ {
/*    */   protected final b a;
/*    */   protected final AffineTransform b;
/*    */   
/*    */   public f(b image, AffineTransform at) {
/* 19 */     if (!c && at != null && at.getScaleX() == 0.0D) throw new AssertionError(); 
/* 20 */     if (!c && at != null && at.getScaleY() == 0.0D) throw new AssertionError(); 
/* 21 */     this.a = image;
/* 22 */     this.b = at;
/*    */   }
/*    */   
/*    */   public short b() {
/* 26 */     return 2;
/*    */   }
/*    */   
/*    */   public AffineTransform a() {
/* 30 */     return this.b;
/*    */   }
/*    */   
/*    */   public b c() {
/* 34 */     return this.a;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 38 */     return super.toString() + "/image=" + this.a + ",at=" + this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/c/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */