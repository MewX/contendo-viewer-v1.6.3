package org.apache.batik.ext.awt.image.renderable;

import java.util.List;
import org.apache.batik.ext.awt.image.ARGBChannel;

public interface DisplacementMapRable extends FilterColorInterpolation {
  public static final int CHANNEL_R = 1;
  
  public static final int CHANNEL_G = 2;
  
  public static final int CHANNEL_B = 3;
  
  public static final int CHANNEL_A = 4;
  
  void setSources(List paramList);
  
  void setScale(double paramDouble);
  
  double getScale();
  
  void setXChannelSelector(ARGBChannel paramARGBChannel);
  
  ARGBChannel getXChannelSelector();
  
  void setYChannelSelector(ARGBChannel paramARGBChannel);
  
  ARGBChannel getYChannelSelector();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/DisplacementMapRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */