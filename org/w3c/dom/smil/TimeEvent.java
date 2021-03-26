package org.w3c.dom.smil;

import org.w3c.dom.events.Event;
import org.w3c.dom.views.AbstractView;

public interface TimeEvent extends Event {
  AbstractView getView();
  
  int getDetail();
  
  void initTimeEvent(String paramString, AbstractView paramAbstractView, int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/smil/TimeEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */