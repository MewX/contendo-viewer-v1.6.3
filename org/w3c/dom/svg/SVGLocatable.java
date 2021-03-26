package org.w3c.dom.svg;

public interface SVGLocatable {
  SVGElement getNearestViewportElement();
  
  SVGElement getFarthestViewportElement();
  
  SVGRect getBBox();
  
  SVGMatrix getCTM();
  
  SVGMatrix getScreenCTM();
  
  SVGMatrix getTransformToElement(SVGElement paramSVGElement) throws SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGLocatable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */