package org.w3c.dom.ls;

import org.w3c.dom.Document;
import org.w3c.dom.events.Event;

public interface LSLoadEvent extends Event {
  Document getNewDocument();
  
  LSInput getInput();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/ls/LSLoadEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */