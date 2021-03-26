package org.apache.batik.dom.svg;

import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRect;

public interface SVGSVGContext extends SVGContext {
  List getIntersectionList(SVGRect paramSVGRect, Element paramElement);
  
  List getEnclosureList(SVGRect paramSVGRect, Element paramElement);
  
  boolean checkIntersection(Element paramElement, SVGRect paramSVGRect);
  
  boolean checkEnclosure(Element paramElement, SVGRect paramSVGRect);
  
  void deselectAll();
  
  int suspendRedraw(int paramInt);
  
  boolean unsuspendRedraw(int paramInt);
  
  void unsuspendRedrawAll();
  
  void forceRedraw();
  
  void pauseAnimations();
  
  void unpauseAnimations();
  
  boolean animationsPaused();
  
  float getCurrentTime();
  
  void setCurrentTime(float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGSVGContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */