/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class k
/*    */   implements c
/*    */ {
/* 12 */   public static final k a = new k(new AffineTransform());
/*    */   
/*    */   private final AffineTransform b;
/*    */   
/*    */   public static k a(AffineTransform transform) {
/* 17 */     if (transform.isIdentity()) {
/* 18 */       return a;
/*    */     }
/* 20 */     return new k(transform);
/*    */   }
/*    */   
/*    */   protected k(AffineTransform transform) {
/* 24 */     this.b = transform;
/*    */   }
/*    */   
/*    */   public AffineTransform b() {
/* 28 */     return this.b;
/*    */   }
/*    */   
/*    */   public short a() {
/* 32 */     return 3006;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */