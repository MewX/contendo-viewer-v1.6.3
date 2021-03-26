package org.apache.batik.gvt.event;

import java.util.EventListener;

public interface GraphicsNodeFocusListener extends EventListener {
  void focusGained(GraphicsNodeFocusEvent paramGraphicsNodeFocusEvent);
  
  void focusLost(GraphicsNodeFocusEvent paramGraphicsNodeFocusEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeFocusListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */