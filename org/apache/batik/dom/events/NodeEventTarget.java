package org.apache.batik.dom.events;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public interface NodeEventTarget extends EventTarget {
  EventSupport getEventSupport();
  
  NodeEventTarget getParentNodeEventTarget();
  
  boolean dispatchEvent(Event paramEvent) throws EventException, DOMException;
  
  void addEventListenerNS(String paramString1, String paramString2, EventListener paramEventListener, boolean paramBoolean, Object paramObject);
  
  void removeEventListenerNS(String paramString1, String paramString2, EventListener paramEventListener, boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/NodeEventTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */