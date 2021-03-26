/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.b.a.a;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.pdf.d;
/*    */ import jp.cssj.sakae.pdf.d.a;
/*    */ import jp.cssj.sakae.pdf.d.b;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.apache.batik.gvt.filter.GraphicsNodeRable8Bit;
/*    */ 
/*    */ class c
/*    */   extends GraphicsNodeRable8Bit
/*    */ {
/*    */   private final boolean a;
/*    */   
/*    */   c(GraphicsNode node, boolean forceVector) {
/* 20 */     super(node);
/* 21 */     this.a = forceVector;
/*    */   }
/*    */   
/*    */   public boolean paintRable(Graphics2D g2d) {
/* 25 */     if (!(g2d instanceof a)) {
/* 26 */       return super.paintRable(g2d);
/*    */     }
/* 28 */     b gc = ((a)g2d).a();
/* 29 */     if (!(gc instanceof a)) {
/* 30 */       return super.paintRable(g2d);
/*    */     }
/*    */     
/* 33 */     a pgc = (a)gc;
/*    */     try {
/* 35 */       d pdfgo = pgc.b();
/* 36 */       if (!this.a) {
/* 37 */         int pdfVersion = pdfgo.d().a().h();
/* 38 */         if (pdfVersion < 1400 || pdfVersion == 1412 || pdfVersion == 1421)
/*    */         {
/*    */           
/* 41 */           return super.paintRable(g2d);
/*    */         }
/*    */       } 
/*    */       
/* 45 */       try (b image2 = pdfgo.d().a(pdfgo.a(), pdfgo.b())) {
/* 46 */         a gc2 = new a((d)image2);
/* 47 */         gc2.d();
/* 48 */         a a = new a((b)gc2, g2d.getDeviceConfiguration());
/* 49 */         GraphicsNode gn = getGraphicsNode();
/* 50 */         if (getUsePrimitivePaint()) {
/* 51 */           gn.primitivePaint((Graphics2D)a);
/*    */         } else {
/* 53 */           gn.paint((Graphics2D)a);
/*    */         } 
/* 55 */         gc2.e();
/* 56 */         gc.a((b)image2);
/*    */       } 
/* 58 */     } catch (IOException e) {
/* 59 */       throw new RuntimeException(e);
/*    */     } 
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */