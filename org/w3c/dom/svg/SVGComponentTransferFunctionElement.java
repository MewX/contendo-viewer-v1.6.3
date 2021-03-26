package org.w3c.dom.svg;

public interface SVGComponentTransferFunctionElement extends SVGElement {
  public static final short SVG_FECOMPONENTTRANSFER_TYPE_UNKNOWN = 0;
  
  public static final short SVG_FECOMPONENTTRANSFER_TYPE_IDENTITY = 1;
  
  public static final short SVG_FECOMPONENTTRANSFER_TYPE_TABLE = 2;
  
  public static final short SVG_FECOMPONENTTRANSFER_TYPE_DISCRETE = 3;
  
  public static final short SVG_FECOMPONENTTRANSFER_TYPE_LINEAR = 4;
  
  public static final short SVG_FECOMPONENTTRANSFER_TYPE_GAMMA = 5;
  
  SVGAnimatedEnumeration getType();
  
  SVGAnimatedNumberList getTableValues();
  
  SVGAnimatedNumber getSlope();
  
  SVGAnimatedNumber getIntercept();
  
  SVGAnimatedNumber getAmplitude();
  
  SVGAnimatedNumber getExponent();
  
  SVGAnimatedNumber getOffset();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGComponentTransferFunctionElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */