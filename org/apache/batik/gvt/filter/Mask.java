package org.apache.batik.gvt.filter;

import java.awt.geom.Rectangle2D;
import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.gvt.GraphicsNode;

public interface Mask extends Filter {
  Rectangle2D getFilterRegion();
  
  void setFilterRegion(Rectangle2D paramRectangle2D);
  
  void setSource(Filter paramFilter);
  
  Filter getSource();
  
  void setMaskNode(GraphicsNode paramGraphicsNode);
  
  GraphicsNode getMaskNode();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/filter/Mask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */