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
/*    */ public class FlowGlyphLayout
/*    */   extends GlyphLayout
/*    */ {
/*    */   public static final char SOFT_HYPHEN = '­';
/*    */   public static final char ZERO_WIDTH_SPACE = '​';
/*    */   public static final char ZERO_WIDTH_JOINER = '‍';
/*    */   public static final char SPACE = ' ';
/*    */   
/*    */   public FlowGlyphLayout(AttributedCharacterIterator aci, int[] charMap, Point2D offset, FontRenderContext frc) {
/* 47 */     super(aci, charMap, offset, frc);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FlowGlyphLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */