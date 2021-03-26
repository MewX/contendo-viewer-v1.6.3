package org.apache.batik.ext.awt.image.renderable;

public interface GaussianBlurRable extends FilterColorInterpolation {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  void setStdDeviationX(double paramDouble);
  
  void setStdDeviationY(double paramDouble);
  
  double getStdDeviationX();
  
  double getStdDeviationY();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/GaussianBlurRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */