package org.apache.batik.swing.gvt;

import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface Interactor extends KeyListener, MouseListener, MouseMotionListener {
  boolean startInteraction(InputEvent paramInputEvent);
  
  boolean endInteraction();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/Interactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */