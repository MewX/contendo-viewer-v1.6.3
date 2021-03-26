package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGStyleElement extends SVGElement {
  String getXMLspace();
  
  void setXMLspace(String paramString) throws DOMException;
  
  String getType();
  
  void setType(String paramString) throws DOMException;
  
  String getMedia();
  
  void setMedia(String paramString) throws DOMException;
  
  String getTitle();
  
  void setTitle(String paramString) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGStyleElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */