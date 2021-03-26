package org.apache.batik.bridge;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.apache.batik.gvt.font.GVTGlyphMetrics;
import org.apache.batik.gvt.font.GVTGlyphVector;
import org.apache.batik.gvt.font.GVTLineMetrics;

public interface TextSpanLayout {
  public static final int DECORATION_UNDERLINE = 1;
  
  public static final int DECORATION_STRIKETHROUGH = 2;
  
  public static final int DECORATION_OVERLINE = 4;
  
  public static final int DECORATION_ALL = 7;
  
  void draw(Graphics2D paramGraphics2D);
  
  Shape getDecorationOutline(int paramInt);
  
  Rectangle2D getBounds2D();
  
  Rectangle2D getGeometricBounds();
  
  Shape getOutline();
  
  Point2D getAdvance2D();
  
  float[] getGlyphAdvances();
  
  GVTGlyphMetrics getGlyphMetrics(int paramInt);
  
  GVTLineMetrics getLineMetrics();
  
  Point2D getTextPathAdvance();
  
  Point2D getOffset();
  
  void setScale(float paramFloat1, float paramFloat2, boolean paramBoolean);
  
  void setOffset(Point2D paramPoint2D);
  
  Shape getHighlightShape(int paramInt1, int paramInt2);
  
  TextHit hitTestChar(float paramFloat1, float paramFloat2);
  
  boolean isVertical();
  
  boolean isOnATextPath();
  
  int getGlyphCount();
  
  int getCharacterCount(int paramInt1, int paramInt2);
  
  int getGlyphIndex(int paramInt);
  
  boolean isLeftToRight();
  
  boolean hasCharacterIndex(int paramInt);
  
  GVTGlyphVector getGlyphVector();
  
  double getComputedOrientationAngle(int paramInt);
  
  boolean isAltGlyph();
  
  boolean isReversed();
  
  void maybeReverse(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/TextSpanLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */