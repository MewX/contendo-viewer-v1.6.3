package org.apache.batik.gvt.event;

import java.util.EventListener;

public interface GraphicsNodeMouseListener extends EventListener {
  void mouseClicked(GraphicsNodeMouseEvent paramGraphicsNodeMouseEvent);
  
  void mousePressed(GraphicsNodeMouseEvent paramGraphicsNodeMouseEvent);
  
  void mouseReleased(GraphicsNodeMouseEvent paramGraphicsNodeMouseEvent);
  
  void mouseEntered(GraphicsNodeMouseEvent paramGraphicsNodeMouseEvent);
  
  void mouseExited(GraphicsNodeMouseEvent paramGraphicsNodeMouseEvent);
  
  void mouseDragged(GraphicsNodeMouseEvent paramGraphicsNodeMouseEvent);
  
  void mouseMoved(GraphicsNodeMouseEvent paramGraphicsNodeMouseEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeMouseListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */