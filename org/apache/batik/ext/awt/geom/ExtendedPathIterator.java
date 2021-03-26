package org.apache.batik.ext.awt.geom;

public interface ExtendedPathIterator {
  public static final int SEG_CLOSE = 4;
  
  public static final int SEG_MOVETO = 0;
  
  public static final int SEG_LINETO = 1;
  
  public static final int SEG_QUADTO = 2;
  
  public static final int SEG_CUBICTO = 3;
  
  public static final int SEG_ARCTO = 4321;
  
  public static final int WIND_EVEN_ODD = 0;
  
  public static final int WIND_NON_ZERO = 1;
  
  int currentSegment();
  
  int currentSegment(double[] paramArrayOfdouble);
  
  int currentSegment(float[] paramArrayOffloat);
  
  int getWindingRule();
  
  boolean isDone();
  
  void next();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/ExtendedPathIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */