package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGPathSegList {
  int getNumberOfItems();
  
  void clear() throws DOMException;
  
  SVGPathSeg initialize(SVGPathSeg paramSVGPathSeg) throws DOMException, SVGException;
  
  SVGPathSeg getItem(int paramInt) throws DOMException;
  
  SVGPathSeg insertItemBefore(SVGPathSeg paramSVGPathSeg, int paramInt) throws DOMException, SVGException;
  
  SVGPathSeg replaceItem(SVGPathSeg paramSVGPathSeg, int paramInt) throws DOMException, SVGException;
  
  SVGPathSeg removeItem(int paramInt) throws DOMException;
  
  SVGPathSeg appendItem(SVGPathSeg paramSVGPathSeg) throws DOMException, SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPathSegList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */