package org.apache.batik.ext.awt.image.renderable;

public interface ColorMatrixRable extends FilterColorInterpolation {
  public static final int TYPE_MATRIX = 0;
  
  public static final int TYPE_SATURATE = 1;
  
  public static final int TYPE_HUE_ROTATE = 2;
  
  public static final int TYPE_LUMINANCE_TO_ALPHA = 3;
  
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  int getType();
  
  float[][] getMatrix();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ColorMatrixRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */