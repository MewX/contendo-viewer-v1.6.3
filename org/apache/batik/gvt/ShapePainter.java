package org.apache.batik.gvt;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface ShapePainter {
  void paint(Graphics2D paramGraphics2D);
  
  Shape getPaintedArea();
  
  Rectangle2D getPaintedBounds2D();
  
  boolean inPaintedArea(Point2D paramPoint2D);
  
  Shape getSensitiveArea();
  
  Rectangle2D getSensitiveBounds2D();
  
  boolean inSensitiveArea(Point2D paramPoint2D);
  
  void setShape(Shape paramShape);
  
  Shape getShape();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/ShapePainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */