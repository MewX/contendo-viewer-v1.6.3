package org.apache.batik.gvt.filter;

import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.gvt.GraphicsNode;

public interface GraphicsNodeRable extends Filter {
  GraphicsNode getGraphicsNode();
  
  void setGraphicsNode(GraphicsNode paramGraphicsNode);
  
  boolean getUsePrimitivePaint();
  
  void setUsePrimitivePaint(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/filter/GraphicsNodeRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */