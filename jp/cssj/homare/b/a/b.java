/*    */ package jp.cssj.homare.b.a;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.c.w;
/*    */ 
/*    */ public abstract class b
/*    */   implements j {
/*    */   public byte d() {
/*  9 */     return 0;
/*    */   }
/*    */   
/*    */   protected final AffineTransform a(AffineTransform transform, double x, double y) {
/* 13 */     AffineTransform ct = (b()).ap;
/* 14 */     if (ct.isIdentity()) {
/* 15 */       return transform;
/*    */     }
/* 17 */     transform = new AffineTransform(transform);
/* 18 */     double ax = x;
/* 19 */     double ay = y;
/* 20 */     w offset = (b()).aq;
/* 21 */     switch (offset.a()) {
/*    */       case 1:
/* 23 */         ax += offset.c();
/*    */         break;
/*    */       case 2:
/* 26 */         ax += p() * offset.c();
/*    */         break;
/*    */       default:
/* 29 */         throw new IllegalStateException();
/*    */     } 
/* 31 */     switch (offset.a()) {
/*    */       case 1:
/* 33 */         ay += offset.d();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 42 */         transform.translate(ax, ay);
/* 43 */         transform.concatenate(ct);
/* 44 */         transform.translate(-ax, -ay);
/* 45 */         return transform;case 2: ay += q() * offset.d(); transform.translate(ax, ay); transform.concatenate(ct); transform.translate(-ax, -ay); return transform;
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   } public String toString() {
/* 49 */     return super.toString() + "[width=" + p() + ",height=" + q() + ",params=" + 
/* 50 */       b() + ",pos=" + b_() + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */