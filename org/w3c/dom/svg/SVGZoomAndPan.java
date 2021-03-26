package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGZoomAndPan {
  public static final short SVG_ZOOMANDPAN_UNKNOWN = 0;
  
  public static final short SVG_ZOOMANDPAN_DISABLE = 1;
  
  public static final short SVG_ZOOMANDPAN_MAGNIFY = 2;
  
  short getZoomAndPan();
  
  void setZoomAndPan(short paramShort) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGZoomAndPan.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */