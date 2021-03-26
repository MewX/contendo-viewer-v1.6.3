package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGLengthList {
  int getNumberOfItems();
  
  void clear() throws DOMException;
  
  SVGLength initialize(SVGLength paramSVGLength) throws DOMException, SVGException;
  
  SVGLength getItem(int paramInt) throws DOMException;
  
  SVGLength insertItemBefore(SVGLength paramSVGLength, int paramInt) throws DOMException, SVGException;
  
  SVGLength replaceItem(SVGLength paramSVGLength, int paramInt) throws DOMException, SVGException;
  
  SVGLength removeItem(int paramInt) throws DOMException;
  
  SVGLength appendItem(SVGLength paramSVGLength) throws DOMException, SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGLengthList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */