package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGGlyphRefElement extends SVGElement, SVGStylable, SVGURIReference {
  String getGlyphRef();
  
  void setGlyphRef(String paramString) throws DOMException;
  
  String getFormat();
  
  void setFormat(String paramString) throws DOMException;
  
  float getX();
  
  void setX(float paramFloat) throws DOMException;
  
  float getY();
  
  void setY(float paramFloat) throws DOMException;
  
  float getDx();
  
  void setDx(float paramFloat) throws DOMException;
  
  float getDy();
  
  void setDy(float paramFloat) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGGlyphRefElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */