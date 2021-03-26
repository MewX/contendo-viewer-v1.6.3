/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.text.AttributedCharacterIterator;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.b.a.a;
/*    */ import jp.cssj.sakae.c.a.e;
/*    */ import jp.cssj.sakae.c.a.h;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.d.a.a;
/*    */ import jp.cssj.sakae.c.d.a.b;
/*    */ import jp.cssj.sakae.c.d.b.b;
/*    */ import jp.cssj.sakae.c.d.e;
/*    */ import jp.cssj.sakae.c.d.j;
/*    */ import org.apache.batik.bridge.StrokingTextPainter;
/*    */ import org.apache.batik.bridge.TextSpanLayout;
/*    */ import org.apache.batik.gvt.text.TextPaintInfo;
/*    */ 
/*    */ class i
/*    */   extends StrokingTextPainter {
/*    */   protected final e a;
/*    */   protected final a b;
/*    */   
/*    */   public i(m ua) {
/* 28 */     this.a = ua.q();
/* 29 */     this.b = b.a(null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void paintTextRuns(List<StrokingTextPainter.TextRun> textRuns, Graphics2D g2d) {
/* 35 */     b gc = ((a)g2d).a();
/* 36 */     for (int j = 0; j < textRuns.size(); j++) {
/* 37 */       StrokingTextPainter.TextRun textRun = textRuns.get(j);
/* 38 */       AttributedCharacterIterator aci = textRun.getACI();
/*    */ 
/*    */       
/* 41 */       TextPaintInfo tpi = (TextPaintInfo)aci.getAttribute(StrokingTextPainter.PAINT_INFO);
/* 42 */       if (tpi != null) {
/* 43 */         if (tpi.composite != null) {
/* 44 */           g2d.setComposite(tpi.composite);
/*    */         }
/* 46 */         if (tpi.fillPaint != null) {
/* 47 */           g2d.setPaint(tpi.fillPaint);
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 52 */       b font = (b)aci.getAttribute(GVT_FONT);
/* 53 */       h fontStyle = font.b;
/*    */ 
/*    */       
/* 56 */       char[] ch = new char[aci.getEndIndex() - aci.getBeginIndex()];
/* 57 */       aci.first();
/* 58 */       for (int k = 0; aci.getIndex() < aci.getEndIndex(); k++) {
/* 59 */         ch[k] = aci.current();
/* 60 */         aci.next();
/*    */       } 
/*    */ 
/*    */       
/* 64 */       TextSpanLayout layout = textRun.getLayout();
/* 65 */       Point2D position = layout.getOffset();
/* 66 */       gc.d();
/* 67 */       double x = position.getX();
/* 68 */       double y = position.getY();
/* 69 */       AffineTransform at = AffineTransform.getTranslateInstance(x, y - fontStyle.e());
/* 70 */       gc.a(at);
/* 71 */       b lineHandler = new b();
/* 72 */       lineHandler.a(fontStyle.a());
/* 73 */       lineHandler.a(gc);
/* 74 */       lineHandler.e(Double.MAX_VALUE);
/* 75 */       j tlf = new j(gc, this.b, (e)lineHandler);
/* 76 */       tlf.b(fontStyle.a());
/* 77 */       tlf.a(fontStyle);
/* 78 */       tlf.a(-1, ch, 0, ch.length);
/* 79 */       tlf.a();
/* 80 */       lineHandler.q();
/* 81 */       gc.e();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */