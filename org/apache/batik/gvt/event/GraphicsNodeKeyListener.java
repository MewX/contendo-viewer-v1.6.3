package org.apache.batik.gvt.event;

import java.util.EventListener;

public interface GraphicsNodeKeyListener extends EventListener {
  void keyPressed(GraphicsNodeKeyEvent paramGraphicsNodeKeyEvent);
  
  void keyReleased(GraphicsNodeKeyEvent paramGraphicsNodeKeyEvent);
  
  void keyTyped(GraphicsNodeKeyEvent paramGraphicsNodeKeyEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeKeyListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */