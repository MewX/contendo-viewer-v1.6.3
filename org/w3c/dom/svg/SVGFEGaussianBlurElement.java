package org.w3c.dom.svg;

public interface SVGFEGaussianBlurElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
  SVGAnimatedString getIn1();
  
  SVGAnimatedNumber getStdDeviationX();
  
  SVGAnimatedNumber getStdDeviationY();
  
  void setStdDeviation(float paramFloat1, float paramFloat2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGFEGaussianBlurElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */