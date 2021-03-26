package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGColorProfileElement extends SVGElement, SVGRenderingIntent, SVGURIReference {
  String getLocal();
  
  void setLocal(String paramString) throws DOMException;
  
  String getName();
  
  void setName(String paramString) throws DOMException;
  
  short getRenderingIntent();
  
  void setRenderingIntent(short paramShort) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGColorProfileElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */