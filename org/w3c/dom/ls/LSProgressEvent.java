package org.w3c.dom.ls;

import org.w3c.dom.events.Event;

public interface LSProgressEvent extends Event {
  LSInput getInput();
  
  int getPosition();
  
  int getTotalSize();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/ls/LSProgressEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */