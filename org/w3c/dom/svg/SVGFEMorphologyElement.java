package org.w3c.dom.svg;

public interface SVGFEMorphologyElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
  public static final short SVG_MORPHOLOGY_OPERATOR_UNKNOWN = 0;
  
  public static final short SVG_MORPHOLOGY_OPERATOR_ERODE = 1;
  
  public static final short SVG_MORPHOLOGY_OPERATOR_DILATE = 2;
  
  SVGAnimatedString getIn1();
  
  SVGAnimatedEnumeration getOperator();
  
  SVGAnimatedNumber getRadiusX();
  
  SVGAnimatedNumber getRadiusY();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGFEMorphologyElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */