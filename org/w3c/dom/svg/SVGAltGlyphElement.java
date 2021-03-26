package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGAltGlyphElement extends SVGTextPositioningElement, SVGURIReference {
  String getGlyphRef();
  
  void setGlyphRef(String paramString) throws DOMException;
  
  String getFormat();
  
  void setFormat(String paramString) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGAltGlyphElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */