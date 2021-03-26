package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGNumberList {
  int getNumberOfItems();
  
  void clear() throws DOMException;
  
  SVGNumber initialize(SVGNumber paramSVGNumber) throws DOMException, SVGException;
  
  SVGNumber getItem(int paramInt) throws DOMException;
  
  SVGNumber insertItemBefore(SVGNumber paramSVGNumber, int paramInt) throws DOMException, SVGException;
  
  SVGNumber replaceItem(SVGNumber paramSVGNumber, int paramInt) throws DOMException, SVGException;
  
  SVGNumber removeItem(int paramInt) throws DOMException;
  
  SVGNumber appendItem(SVGNumber paramSVGNumber) throws DOMException, SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGNumberList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */