package org.w3c.dom.svg;

import org.w3c.dom.events.EventTarget;

public interface SVGElementInstance extends EventTarget {
  SVGElement getCorrespondingElement();
  
  SVGUseElement getCorrespondingUseElement();
  
  SVGElementInstance getParentNode();
  
  SVGElementInstanceList getChildNodes();
  
  SVGElementInstance getFirstChild();
  
  SVGElementInstance getLastChild();
  
  SVGElementInstance getPreviousSibling();
  
  SVGElementInstance getNextSibling();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGElementInstance.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */