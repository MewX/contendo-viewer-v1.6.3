package org.apache.batik.gvt;

import org.apache.batik.gvt.event.GraphicsNodeChangeListener;
import org.apache.batik.gvt.event.GraphicsNodeKeyListener;
import org.apache.batik.gvt.event.GraphicsNodeMouseListener;
import org.apache.batik.gvt.event.SelectionListener;

public interface Selector extends GraphicsNodeChangeListener, GraphicsNodeKeyListener, GraphicsNodeMouseListener {
  Object getSelection();
  
  boolean isEmpty();
  
  void addSelectionListener(SelectionListener paramSelectionListener);
  
  void removeSelectionListener(SelectionListener paramSelectionListener);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/Selector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */