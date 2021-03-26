package org.w3c.dom.svg;

public interface SVGFECompositeElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
  public static final short SVG_FECOMPOSITE_OPERATOR_UNKNOWN = 0;
  
  public static final short SVG_FECOMPOSITE_OPERATOR_OVER = 1;
  
  public static final short SVG_FECOMPOSITE_OPERATOR_IN = 2;
  
  public static final short SVG_FECOMPOSITE_OPERATOR_OUT = 3;
  
  public static final short SVG_FECOMPOSITE_OPERATOR_ATOP = 4;
  
  public static final short SVG_FECOMPOSITE_OPERATOR_XOR = 5;
  
  public static final short SVG_FECOMPOSITE_OPERATOR_ARITHMETIC = 6;
  
  SVGAnimatedString getIn1();
  
  SVGAnimatedString getIn2();
  
  SVGAnimatedEnumeration getOperator();
  
  SVGAnimatedNumber getK1();
  
  SVGAnimatedNumber getK2();
  
  SVGAnimatedNumber getK3();
  
  SVGAnimatedNumber getK4();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGFECompositeElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */