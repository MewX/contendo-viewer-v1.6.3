package org.w3c.dom.svg;

import org.w3c.dom.events.EventTarget;

public interface SVGRectElement extends EventTarget, SVGElement, SVGExternalResourcesRequired, SVGLangSpace, SVGStylable, SVGTests, SVGTransformable {
  SVGAnimatedLength getX();
  
  SVGAnimatedLength getY();
  
  SVGAnimatedLength getWidth();
  
  SVGAnimatedLength getHeight();
  
  SVGAnimatedLength getRx();
  
  SVGAnimatedLength getRy();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGRectElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */