package org.apache.batik.dom.svg;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface SVGTextContent {
  int getNumberOfChars();
  
  Rectangle2D getExtentOfChar(int paramInt);
  
  Point2D getStartPositionOfChar(int paramInt);
  
  Point2D getEndPositionOfChar(int paramInt);
  
  float getRotationOfChar(int paramInt);
  
  void selectSubString(int paramInt1, int paramInt2);
  
  float getComputedTextLength();
  
  float getSubStringLength(int paramInt1, int paramInt2);
  
  int getCharNumAtPosition(float paramFloat1, float paramFloat2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGTextContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */