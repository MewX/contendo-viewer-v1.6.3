package org.apache.batik.ext.awt.image.renderable;

import java.awt.geom.Rectangle2D;
import org.apache.batik.ext.awt.image.Light;

public interface DiffuseLightingRable extends FilterColorInterpolation {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  Light getLight();
  
  void setLight(Light paramLight);
  
  double getSurfaceScale();
  
  void setSurfaceScale(double paramDouble);
  
  double getKd();
  
  void setKd(double paramDouble);
  
  Rectangle2D getLitRegion();
  
  void setLitRegion(Rectangle2D paramRectangle2D);
  
  double[] getKernelUnitLength();
  
  void setKernelUnitLength(double[] paramArrayOfdouble);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/DiffuseLightingRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */