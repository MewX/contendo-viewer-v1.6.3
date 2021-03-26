package org.apache.batik.w3c.dom.events;

import org.w3c.dom.events.Event;

public interface CustomEvent extends Event {
  Object getDetail();
  
  void initCustomEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, Object paramObject);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/w3c/dom/events/CustomEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */