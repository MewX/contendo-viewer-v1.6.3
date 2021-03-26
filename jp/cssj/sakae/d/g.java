/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class g
/*    */   implements b
/*    */ {
/*    */   protected final GraphicsNode a;
/*    */   protected final double b;
/*    */   protected final double c;
/*    */   
/*    */   public g(GraphicsNode gvtRoot, double width, double height) {
/* 23 */     this.a = gvtRoot;
/* 24 */     this.b = width;
/* 25 */     this.c = height;
/*    */   }
/*    */   
/*    */   public GraphicsNode d() {
/* 29 */     return this.a;
/*    */   }
/*    */   
/*    */   public double a() {
/* 33 */     return this.b;
/*    */   }
/*    */   
/*    */   public double b() {
/* 37 */     return this.c;
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 41 */     gc.d();
/* 42 */     d d = new d(gc);
/* 43 */     this.a.paint((Graphics2D)d);
/* 44 */     d.dispose();
/* 45 */     gc.e();
/*    */   }
/*    */   
/*    */   public String c() {
/* 49 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */