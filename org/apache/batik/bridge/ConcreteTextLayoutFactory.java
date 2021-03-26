/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.awt.font.FontRenderContext;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConcreteTextLayoutFactory
/*    */   implements TextLayoutFactory
/*    */ {
/*    */   public TextSpanLayout createTextLayout(AttributedCharacterIterator aci, int[] charMap, Point2D offset, FontRenderContext frc) {
/* 50 */     return new GlyphLayout(aci, charMap, offset, frc);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/ConcreteTextLayoutFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */