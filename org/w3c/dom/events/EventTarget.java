package org.w3c.dom.events;

import org.w3c.dom.DOMException;

public interface EventTarget {
  void addEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean);
  
  void removeEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean);
  
  boolean dispatchEvent(Event paramEvent) throws EventException, DOMException;
  
  void addEventListenerNS(String paramString1, String paramString2, EventListener paramEventListener, boolean paramBoolean, Object paramObject);
  
  void removeEventListenerNS(String paramString1, String paramString2, EventListener paramEventListener, boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/events/EventTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */