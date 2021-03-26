package org.w3c.dom.svg;

import org.w3c.dom.events.EventTarget;

public interface SVGForeignObjectElement extends EventTarget, SVGElement, SVGExternalResourcesRequired, SVGLangSpace, SVGStylable, SVGTests, SVGTransformable {
  SVGAnimatedLength getX();
  
  SVGAnimatedLength getY();
  
  SVGAnimatedLength getWidth();
  
  SVGAnimatedLength getHeight();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGForeignObjectElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */