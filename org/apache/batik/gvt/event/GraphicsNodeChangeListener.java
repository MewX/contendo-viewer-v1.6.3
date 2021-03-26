package org.apache.batik.gvt.event;

import java.util.EventListener;

public interface GraphicsNodeChangeListener extends EventListener {
  void changeStarted(GraphicsNodeChangeEvent paramGraphicsNodeChangeEvent);
  
  void changeCompleted(GraphicsNodeChangeEvent paramGraphicsNodeChangeEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeChangeListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */