package org.w3c.dom.svg;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

public interface SVGElement extends Element {
  String getId();
  
  void setId(String paramString) throws DOMException;
  
  String getXMLbase();
  
  void setXMLbase(String paramString) throws DOMException;
  
  SVGSVGElement getOwnerSVGElement();
  
  SVGElement getViewportElement();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */