package org.w3c.dom.svg;

public interface SVGGradientElement extends SVGElement, SVGExternalResourcesRequired, SVGStylable, SVGURIReference, SVGUnitTypes {
  public static final short SVG_SPREADMETHOD_UNKNOWN = 0;
  
  public static final short SVG_SPREADMETHOD_PAD = 1;
  
  public static final short SVG_SPREADMETHOD_REFLECT = 2;
  
  public static final short SVG_SPREADMETHOD_REPEAT = 3;
  
  SVGAnimatedEnumeration getGradientUnits();
  
  SVGAnimatedTransformList getGradientTransform();
  
  SVGAnimatedEnumeration getSpreadMethod();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGGradientElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */