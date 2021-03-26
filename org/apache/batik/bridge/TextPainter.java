package org.apache.batik.bridge;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public interface TextPainter {
  void paint(TextNode paramTextNode, Graphics2D paramGraphics2D);
  
  Mark selectAt(double paramDouble1, double paramDouble2, TextNode paramTextNode);
  
  Mark selectTo(double paramDouble1, double paramDouble2, Mark paramMark);
  
  Mark selectFirst(TextNode paramTextNode);
  
  Mark selectLast(TextNode paramTextNode);
  
  Mark getMark(TextNode paramTextNode, int paramInt, boolean paramBoolean);
  
  int[] getSelected(Mark paramMark1, Mark paramMark2);
  
  Shape getHighlightShape(Mark paramMark1, Mark paramMark2);
  
  Shape getOutline(TextNode paramTextNode);
  
  Rectangle2D getBounds2D(TextNode paramTextNode);
  
  Rectangle2D getGeometryBounds(TextNode paramTextNode);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/TextPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */