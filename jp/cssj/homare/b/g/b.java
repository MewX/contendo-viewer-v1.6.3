/*    */ package jp.cssj.homare.b.g;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.j;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements a
/*    */ {
/*    */   private final a a;
/*    */   
/*    */   public b(a visitor) {
/* 17 */     this.a = visitor;
/*    */   }
/*    */   
/*    */   public void g() {
/* 21 */     this.a.g();
/*    */   }
/*    */   
/*    */   public void h() {
/* 25 */     this.a.h();
/*    */   }
/*    */   
/*    */   public void a(AffineTransform transform, j box, double x, double y) {
/* 29 */     if (this.a == null) {
/*    */       return;
/*    */     }
/* 32 */     this.a.a(transform, box, x, y);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/g/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */