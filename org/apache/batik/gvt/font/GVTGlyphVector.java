package org.apache.batik.gvt.font;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphJustificationInfo;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;

public interface GVTGlyphVector {
  GVTFont getFont();
  
  FontRenderContext getFontRenderContext();
  
  int getGlyphCode(int paramInt);
  
  int[] getGlyphCodes(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  GlyphJustificationInfo getGlyphJustificationInfo(int paramInt);
  
  Shape getGlyphLogicalBounds(int paramInt);
  
  GVTGlyphMetrics getGlyphMetrics(int paramInt);
  
  Shape getGlyphOutline(int paramInt);
  
  Rectangle2D getGlyphCellBounds(int paramInt);
  
  Point2D getGlyphPosition(int paramInt);
  
  float[] getGlyphPositions(int paramInt1, int paramInt2, float[] paramArrayOffloat);
  
  AffineTransform getGlyphTransform(int paramInt);
  
  Shape getGlyphVisualBounds(int paramInt);
  
  Rectangle2D getLogicalBounds();
  
  int getNumGlyphs();
  
  Shape getOutline();
  
  Shape getOutline(float paramFloat1, float paramFloat2);
  
  Rectangle2D getGeometricBounds();
  
  Rectangle2D getBounds2D(AttributedCharacterIterator paramAttributedCharacterIterator);
  
  void performDefaultLayout();
  
  void setGlyphPosition(int paramInt, Point2D paramPoint2D);
  
  void setGlyphTransform(int paramInt, AffineTransform paramAffineTransform);
  
  void setGlyphVisible(int paramInt, boolean paramBoolean);
  
  boolean isGlyphVisible(int paramInt);
  
  int getCharacterCount(int paramInt1, int paramInt2);
  
  boolean isReversed();
  
  void maybeReverse(boolean paramBoolean);
  
  void draw(Graphics2D paramGraphics2D, AttributedCharacterIterator paramAttributedCharacterIterator);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/GVTGlyphVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */