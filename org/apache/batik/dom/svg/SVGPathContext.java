package org.apache.batik.dom.svg;

import java.awt.geom.Point2D;

public interface SVGPathContext extends SVGContext {
  float getTotalLength();
  
  Point2D getPointAtLength(float paramFloat);
  
  int getPathSegAtLength(float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGPathContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */