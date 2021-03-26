package org.w3c.dom.svg;

public interface SVGFEConvolveMatrixElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
  public static final short SVG_EDGEMODE_UNKNOWN = 0;
  
  public static final short SVG_EDGEMODE_DUPLICATE = 1;
  
  public static final short SVG_EDGEMODE_WRAP = 2;
  
  public static final short SVG_EDGEMODE_NONE = 3;
  
  SVGAnimatedInteger getOrderX();
  
  SVGAnimatedInteger getOrderY();
  
  SVGAnimatedNumberList getKernelMatrix();
  
  SVGAnimatedNumber getDivisor();
  
  SVGAnimatedNumber getBias();
  
  SVGAnimatedInteger getTargetX();
  
  SVGAnimatedInteger getTargetY();
  
  SVGAnimatedEnumeration getEdgeMode();
  
  SVGAnimatedNumber getKernelUnitLengthX();
  
  SVGAnimatedNumber getKernelUnitLengthY();
  
  SVGAnimatedBoolean getPreserveAlpha();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGFEConvolveMatrixElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */