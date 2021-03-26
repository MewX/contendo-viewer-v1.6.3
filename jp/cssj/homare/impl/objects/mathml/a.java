/*    */ package jp.cssj.homare.impl.objects.mathml;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import net.a.a.g.c;
/*    */ 
/*    */ public class a
/*    */   implements b
/*    */ {
/*    */   protected final c a;
/*    */   
/*    */   public a(c view) {
/* 15 */     this.a = view;
/*    */   }
/*    */   
/*    */   public double a() {
/* 19 */     return this.a.a();
/*    */   }
/*    */   
/*    */   public double b() {
/* 23 */     return (this.a.b() + this.a.c());
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 27 */     gc.d();
/* 28 */     jp.cssj.sakae.b.a.a a1 = new jp.cssj.sakae.b.a.a(gc);
/* 29 */     this.a.a((Graphics2D)a1, 0.0F, this.a.b());
/* 30 */     a1.dispose();
/* 31 */     gc.e();
/*    */   }
/*    */   
/*    */   public String c() {
/* 35 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/mathml/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */