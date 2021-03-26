package org.apache.batik.gvt.event;

import java.util.EventListener;

public interface SelectionListener extends EventListener {
  void selectionChanged(SelectionEvent paramSelectionEvent);
  
  void selectionDone(SelectionEvent paramSelectionEvent);
  
  void selectionCleared(SelectionEvent paramSelectionEvent);
  
  void selectionStarted(SelectionEvent paramSelectionEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/SelectionListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */