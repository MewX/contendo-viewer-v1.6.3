package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGPathSegArcRel extends SVGPathSeg {
  float getX();
  
  void setX(float paramFloat) throws DOMException;
  
  float getY();
  
  void setY(float paramFloat) throws DOMException;
  
  float getR1();
  
  void setR1(float paramFloat) throws DOMException;
  
  float getR2();
  
  void setR2(float paramFloat) throws DOMException;
  
  float getAngle();
  
  void setAngle(float paramFloat) throws DOMException;
  
  boolean getLargeArcFlag();
  
  void setLargeArcFlag(boolean paramBoolean) throws DOMException;
  
  boolean getSweepFlag();
  
  void setSweepFlag(boolean paramBoolean) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPathSegArcRel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */