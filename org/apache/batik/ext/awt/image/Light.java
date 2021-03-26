package org.apache.batik.ext.awt.image;

import java.awt.Color;

public interface Light {
  boolean isConstant();
  
  void getLight(double paramDouble1, double paramDouble2, double paramDouble3, double[] paramArrayOfdouble);
  
  double[][][] getLightMap(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt1, int paramInt2, double[][][] paramArrayOfdouble);
  
  double[][] getLightRow(double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double[][] paramArrayOfdouble1, double[][] paramArrayOfdouble2);
  
  double[] getColor(boolean paramBoolean);
  
  void setColor(Color paramColor);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/Light.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */