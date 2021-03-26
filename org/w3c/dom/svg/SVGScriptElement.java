package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGScriptElement extends SVGElement, SVGExternalResourcesRequired, SVGURIReference {
  String getType();
  
  void setType(String paramString) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGScriptElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */