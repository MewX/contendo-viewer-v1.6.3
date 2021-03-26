package org.w3c.dom.events;

import org.w3c.dom.DOMException;

public interface DocumentEvent {
  Event createEvent(String paramString) throws DOMException;
  
  boolean canDispatch(String paramString1, String paramString2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/events/DocumentEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */