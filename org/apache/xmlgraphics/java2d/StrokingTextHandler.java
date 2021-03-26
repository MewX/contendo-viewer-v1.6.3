/*    */ package org.apache.xmlgraphics.java2d;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Shape;
/*    */ import java.awt.font.FontRenderContext;
/*    */ import java.awt.font.GlyphVector;
/*    */ import java.io.IOException;
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
/*    */ public class StrokingTextHandler
/*    */   implements TextHandler
/*    */ {
/*    */   public void drawString(Graphics2D g2d, String text, float x, float y) throws IOException {
/* 42 */     Font awtFont = g2d.getFont();
/* 43 */     FontRenderContext frc = g2d.getFontRenderContext();
/* 44 */     GlyphVector gv = awtFont.createGlyphVector(frc, text);
/* 45 */     Shape glyphOutline = gv.getOutline(x, y);
/* 46 */     g2d.fill(glyphOutline);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/StrokingTextHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */