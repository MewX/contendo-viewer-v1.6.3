package org.apache.batik.ext.awt.image.renderable;

import java.awt.color.ColorSpace;

public interface FilterColorInterpolation extends Filter {
  boolean isColorSpaceLinear();
  
  void setColorSpaceLinear(boolean paramBoolean);
  
  ColorSpace getOperationColorSpace();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FilterColorInterpolation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */