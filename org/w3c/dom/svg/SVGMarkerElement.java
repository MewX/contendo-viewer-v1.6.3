package org.w3c.dom.svg;

public interface SVGMarkerElement extends SVGElement, SVGExternalResourcesRequired, SVGFitToViewBox, SVGLangSpace, SVGStylable {
  public static final short SVG_MARKERUNITS_UNKNOWN = 0;
  
  public static final short SVG_MARKERUNITS_USERSPACEONUSE = 1;
  
  public static final short SVG_MARKERUNITS_STROKEWIDTH = 2;
  
  public static final short SVG_MARKER_ORIENT_UNKNOWN = 0;
  
  public static final short SVG_MARKER_ORIENT_AUTO = 1;
  
  public static final short SVG_MARKER_ORIENT_ANGLE = 2;
  
  SVGAnimatedLength getRefX();
  
  SVGAnimatedLength getRefY();
  
  SVGAnimatedEnumeration getMarkerUnits();
  
  SVGAnimatedLength getMarkerWidth();
  
  SVGAnimatedLength getMarkerHeight();
  
  SVGAnimatedEnumeration getOrientType();
  
  SVGAnimatedAngle getOrientAngle();
  
  void setOrientToAuto();
  
  void setOrientToAngle(SVGAngle paramSVGAngle);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGMarkerElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */