package org.apache.batik.ext.awt.image.renderable;

import java.awt.Paint;
import java.awt.geom.Rectangle2D;

public interface FloodRable extends Filter {
  void setFloodPaint(Paint paramPaint);
  
  Paint getFloodPaint();
  
  void setFloodRegion(Rectangle2D paramRectangle2D);
  
  Rectangle2D getFloodRegion();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FloodRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */