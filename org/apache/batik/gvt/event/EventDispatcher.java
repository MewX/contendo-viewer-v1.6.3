package org.apache.batik.gvt.event;

import java.awt.event.InputEvent;
import java.awt.geom.AffineTransform;
import java.util.EventListener;
import java.util.EventObject;
import org.apache.batik.gvt.GraphicsNode;

public interface EventDispatcher {
  void setRootNode(GraphicsNode paramGraphicsNode);
  
  GraphicsNode getRootNode();
  
  void setBaseTransform(AffineTransform paramAffineTransform);
  
  AffineTransform getBaseTransform();
  
  void dispatchEvent(EventObject paramEventObject);
  
  void addGraphicsNodeMouseListener(GraphicsNodeMouseListener paramGraphicsNodeMouseListener);
  
  void removeGraphicsNodeMouseListener(GraphicsNodeMouseListener paramGraphicsNodeMouseListener);
  
  void addGraphicsNodeMouseWheelListener(GraphicsNodeMouseWheelListener paramGraphicsNodeMouseWheelListener);
  
  void removeGraphicsNodeMouseWheelListener(GraphicsNodeMouseWheelListener paramGraphicsNodeMouseWheelListener);
  
  void addGraphicsNodeKeyListener(GraphicsNodeKeyListener paramGraphicsNodeKeyListener);
  
  void removeGraphicsNodeKeyListener(GraphicsNodeKeyListener paramGraphicsNodeKeyListener);
  
  EventListener[] getListeners(Class paramClass);
  
  void setNodeIncrementEvent(InputEvent paramInputEvent);
  
  void setNodeDecrementEvent(InputEvent paramInputEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/EventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */