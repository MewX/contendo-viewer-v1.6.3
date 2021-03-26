package org.w3c.dom.svg;

public interface SVGViewSpec extends SVGFitToViewBox, SVGZoomAndPan {
  SVGTransformList getTransform();
  
  SVGElement getViewTarget();
  
  String getViewBoxString();
  
  String getPreserveAspectRatioString();
  
  String getTransformString();
  
  String getViewTargetString();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGViewSpec.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */