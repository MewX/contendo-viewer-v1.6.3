package org.apache.batik.w3c.dom.events;

import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

public interface TextEvent extends UIEvent {
  String getData();
  
  void initTextEvent(String paramString1, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, String paramString2);
  
  void initTextEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, String paramString3);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/w3c/dom/events/TextEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */