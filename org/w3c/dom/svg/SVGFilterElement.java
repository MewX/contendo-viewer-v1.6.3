package org.w3c.dom.svg;

public interface SVGFilterElement extends SVGElement, SVGExternalResourcesRequired, SVGLangSpace, SVGStylable, SVGURIReference, SVGUnitTypes {
  SVGAnimatedEnumeration getFilterUnits();
  
  SVGAnimatedEnumeration getPrimitiveUnits();
  
  SVGAnimatedLength getX();
  
  SVGAnimatedLength getY();
  
  SVGAnimatedLength getWidth();
  
  SVGAnimatedLength getHeight();
  
  SVGAnimatedInteger getFilterResX();
  
  SVGAnimatedInteger getFilterResY();
  
  void setFilterRes(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGFilterElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */