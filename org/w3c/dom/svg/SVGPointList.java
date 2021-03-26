package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGPointList {
  int getNumberOfItems();
  
  void clear() throws DOMException;
  
  SVGPoint initialize(SVGPoint paramSVGPoint) throws DOMException, SVGException;
  
  SVGPoint getItem(int paramInt) throws DOMException;
  
  SVGPoint insertItemBefore(SVGPoint paramSVGPoint, int paramInt) throws DOMException, SVGException;
  
  SVGPoint replaceItem(SVGPoint paramSVGPoint, int paramInt) throws DOMException, SVGException;
  
  SVGPoint removeItem(int paramInt) throws DOMException;
  
  SVGPoint appendItem(SVGPoint paramSVGPoint) throws DOMException, SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPointList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */