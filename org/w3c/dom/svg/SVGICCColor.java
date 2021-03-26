package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGICCColor {
  String getColorProfile();
  
  void setColorProfile(String paramString) throws DOMException;
  
  SVGNumberList getColors();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGICCColor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */