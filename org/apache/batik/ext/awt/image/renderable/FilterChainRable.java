package org.apache.batik.ext.awt.image.renderable;

import java.awt.geom.Rectangle2D;

public interface FilterChainRable extends Filter {
  int getFilterResolutionX();
  
  void setFilterResolutionX(int paramInt);
  
  int getFilterResolutionY();
  
  void setFilterResolutionY(int paramInt);
  
  void setFilterRegion(Rectangle2D paramRectangle2D);
  
  Rectangle2D getFilterRegion();
  
  void setSource(Filter paramFilter);
  
  Filter getSource();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FilterChainRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */