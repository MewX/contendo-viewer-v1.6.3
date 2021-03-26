package org.apache.batik.ext.awt.image.renderable;

import java.awt.geom.Rectangle2D;

public interface TurbulenceRable extends FilterColorInterpolation {
  void setTurbulenceRegion(Rectangle2D paramRectangle2D);
  
  Rectangle2D getTurbulenceRegion();
  
  int getSeed();
  
  double getBaseFrequencyX();
  
  double getBaseFrequencyY();
  
  int getNumOctaves();
  
  boolean isStitched();
  
  boolean isFractalNoise();
  
  void setSeed(int paramInt);
  
  void setBaseFrequencyX(double paramDouble);
  
  void setBaseFrequencyY(double paramDouble);
  
  void setNumOctaves(int paramInt);
  
  void setStitched(boolean paramBoolean);
  
  void setFractalNoise(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/TurbulenceRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */