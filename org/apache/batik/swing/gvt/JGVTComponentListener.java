package org.apache.batik.swing.gvt;

import java.awt.event.ComponentEvent;

public interface JGVTComponentListener {
  public static final int COMPONENT_TRANSFORM_CHANGED = 1337;
  
  void componentTransformChanged(ComponentEvent paramComponentEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/JGVTComponentListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */