/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import java.awt.font.FontRenderContext;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.text.AttributedCharacterIterator;
/*    */ import org.apache.batik.bridge.TextLayoutFactory;
/*    */ import org.apache.batik.bridge.TextSpanLayout;
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
/*    */ public class FlowExtTextLayoutFactory
/*    */   implements TextLayoutFactory
/*    */ {
/*    */   public TextSpanLayout createTextLayout(AttributedCharacterIterator aci, int[] charMap, Point2D offset, FontRenderContext frc) {
/* 51 */     return (TextSpanLayout)new FlowExtGlyphLayout(aci, charMap, offset, frc);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/FlowExtTextLayoutFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */