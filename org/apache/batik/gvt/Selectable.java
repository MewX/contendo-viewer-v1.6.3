package org.apache.batik.gvt;

import java.awt.Shape;

public interface Selectable {
  boolean selectAt(double paramDouble1, double paramDouble2);
  
  boolean selectTo(double paramDouble1, double paramDouble2);
  
  boolean selectAll(double paramDouble1, double paramDouble2);
  
  Object getSelection();
  
  Shape getHighlightShape();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/Selectable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */