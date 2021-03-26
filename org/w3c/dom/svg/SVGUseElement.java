package org.w3c.dom.svg;

import org.w3c.dom.events.EventTarget;

public interface SVGUseElement extends EventTarget, SVGElement, SVGExternalResourcesRequired, SVGLangSpace, SVGStylable, SVGTests, SVGTransformable, SVGURIReference {
  SVGAnimatedLength getX();
  
  SVGAnimatedLength getY();
  
  SVGAnimatedLength getWidth();
  
  SVGAnimatedLength getHeight();
  
  SVGElementInstance getInstanceRoot();
  
  SVGElementInstance getAnimatedInstanceRoot();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGUseElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */