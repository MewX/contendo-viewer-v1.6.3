package org.w3c.dom.svg;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.smil.ElementTimeControl;

public interface SVGAnimationElement extends EventTarget, ElementTimeControl, SVGElement, SVGExternalResourcesRequired, SVGTests {
  SVGElement getTargetElement();
  
  float getStartTime();
  
  float getCurrentTime();
  
  float getSimpleDuration() throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGAnimationElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */