package org.apache.batik.dom.svg;

import org.w3c.dom.smil.ElementTimeControl;
import org.w3c.dom.svg.SVGElement;

public interface SVGAnimationContext extends SVGContext, ElementTimeControl {
  SVGElement getTargetElement();
  
  float getStartTime();
  
  float getCurrentTime();
  
  float getSimpleDuration();
  
  float getHyperlinkBeginTime();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGAnimationContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */