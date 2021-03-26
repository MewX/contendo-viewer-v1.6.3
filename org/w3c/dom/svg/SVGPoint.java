package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGPoint {
  float getX();
  
  void setX(float paramFloat) throws DOMException;
  
  float getY();
  
  void setY(float paramFloat) throws DOMException;
  
  SVGPoint matrixTransform(SVGMatrix paramSVGMatrix);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPoint.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */