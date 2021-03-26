package org.apache.batik.ext.awt.image.renderable;

public interface FilterResRable extends Filter {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  int getFilterResolutionX();
  
  void setFilterResolutionX(int paramInt);
  
  int getFilterResolutionY();
  
  void setFilterResolutionY(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FilterResRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */