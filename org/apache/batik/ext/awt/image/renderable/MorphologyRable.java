package org.apache.batik.ext.awt.image.renderable;

public interface MorphologyRable extends Filter {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  void setRadiusX(double paramDouble);
  
  void setRadiusY(double paramDouble);
  
  void setDoDilation(boolean paramBoolean);
  
  boolean getDoDilation();
  
  double getRadiusX();
  
  double getRadiusY();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/MorphologyRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */