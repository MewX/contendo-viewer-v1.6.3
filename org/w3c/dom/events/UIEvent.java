package org.w3c.dom.events;

import org.w3c.dom.views.AbstractView;

public interface UIEvent extends Event {
  AbstractView getView();
  
  int getDetail();
  
  void initUIEvent(String paramString, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, int paramInt);
  
  void initUIEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/events/UIEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */