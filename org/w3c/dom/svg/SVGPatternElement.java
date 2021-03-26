package org.w3c.dom.svg;

public interface SVGPatternElement extends SVGElement, SVGExternalResourcesRequired, SVGFitToViewBox, SVGLangSpace, SVGStylable, SVGTests, SVGURIReference, SVGUnitTypes {
  SVGAnimatedEnumeration getPatternUnits();
  
  SVGAnimatedEnumeration getPatternContentUnits();
  
  SVGAnimatedTransformList getPatternTransform();
  
  SVGAnimatedLength getX();
  
  SVGAnimatedLength getY();
  
  SVGAnimatedLength getWidth();
  
  SVGAnimatedLength getHeight();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPatternElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */