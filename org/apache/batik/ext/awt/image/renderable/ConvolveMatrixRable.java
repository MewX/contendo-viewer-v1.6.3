package org.apache.batik.ext.awt.image.renderable;

import java.awt.Point;
import java.awt.image.Kernel;
import org.apache.batik.ext.awt.image.PadMode;

public interface ConvolveMatrixRable extends FilterColorInterpolation {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  Kernel getKernel();
  
  void setKernel(Kernel paramKernel);
  
  Point getTarget();
  
  void setTarget(Point paramPoint);
  
  double getBias();
  
  void setBias(double paramDouble);
  
  PadMode getEdgeMode();
  
  void setEdgeMode(PadMode paramPadMode);
  
  double[] getKernelUnitLength();
  
  void setKernelUnitLength(double[] paramArrayOfdouble);
  
  boolean getPreserveAlpha();
  
  void setPreserveAlpha(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ConvolveMatrixRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */