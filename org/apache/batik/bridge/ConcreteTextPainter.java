/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.font.TextLayout;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.text.AttributedCharacterIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ConcreteTextPainter
/*    */   extends BasicTextPainter
/*    */ {
/*    */   public void paint(AttributedCharacterIterator aci, Point2D location, TextNode.Anchor anchor, Graphics2D g2d) {
/* 46 */     TextLayout layout = new TextLayout(aci, this.fontRenderContext);
/* 47 */     float advance = layout.getAdvance();
/* 48 */     float tx = 0.0F;
/*    */     
/* 50 */     switch (anchor.getType()) {
/*    */       case 1:
/* 52 */         tx = -advance / 2.0F;
/*    */         break;
/*    */       case 2:
/* 55 */         tx = -advance; break;
/*    */     } 
/* 57 */     layout.draw(g2d, (float)(location.getX() + tx), (float)location.getY());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/ConcreteTextPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */