package org.w3c.dom.svg;

import org.w3c.dom.events.UIEvent;

public interface SVGZoomEvent extends UIEvent {
  SVGRect getZoomRectScreen();
  
  float getPreviousScale();
  
  SVGPoint getPreviousTranslate();
  
  float getNewScale();
  
  SVGPoint getNewTranslate();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGZoomEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */