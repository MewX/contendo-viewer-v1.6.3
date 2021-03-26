package org.apache.batik.ext.awt.image;

public interface ComponentTransferFunction {
  public static final int IDENTITY = 0;
  
  public static final int TABLE = 1;
  
  public static final int DISCRETE = 2;
  
  public static final int LINEAR = 3;
  
  public static final int GAMMA = 4;
  
  int getType();
  
  float getSlope();
  
  float[] getTableValues();
  
  float getIntercept();
  
  float getAmplitude();
  
  float getExponent();
  
  float getOffset();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/ComponentTransferFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */