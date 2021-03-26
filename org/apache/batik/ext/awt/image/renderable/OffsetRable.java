package org.apache.batik.ext.awt.image.renderable;

public interface OffsetRable extends Filter {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  void setXoffset(double paramDouble);
  
  double getXoffset();
  
  void setYoffset(double paramDouble);
  
  double getYoffset();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/OffsetRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */