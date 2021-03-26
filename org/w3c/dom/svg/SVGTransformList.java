package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGTransformList {
  int getNumberOfItems();
  
  void clear() throws DOMException;
  
  SVGTransform initialize(SVGTransform paramSVGTransform) throws DOMException, SVGException;
  
  SVGTransform getItem(int paramInt) throws DOMException;
  
  SVGTransform insertItemBefore(SVGTransform paramSVGTransform, int paramInt) throws DOMException, SVGException;
  
  SVGTransform replaceItem(SVGTransform paramSVGTransform, int paramInt) throws DOMException, SVGException;
  
  SVGTransform removeItem(int paramInt) throws DOMException;
  
  SVGTransform appendItem(SVGTransform paramSVGTransform) throws DOMException, SVGException;
  
  SVGTransform createSVGTransformFromMatrix(SVGMatrix paramSVGMatrix);
  
  SVGTransform consolidate();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGTransformList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */