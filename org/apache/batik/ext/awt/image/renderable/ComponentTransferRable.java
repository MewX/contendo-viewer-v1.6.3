package org.apache.batik.ext.awt.image.renderable;

import org.apache.batik.ext.awt.image.ComponentTransferFunction;

public interface ComponentTransferRable extends FilterColorInterpolation {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  ComponentTransferFunction getAlphaFunction();
  
  void setAlphaFunction(ComponentTransferFunction paramComponentTransferFunction);
  
  ComponentTransferFunction getRedFunction();
  
  void setRedFunction(ComponentTransferFunction paramComponentTransferFunction);
  
  ComponentTransferFunction getGreenFunction();
  
  void setGreenFunction(ComponentTransferFunction paramComponentTransferFunction);
  
  ComponentTransferFunction getBlueFunction();
  
  void setBlueFunction(ComponentTransferFunction paramComponentTransferFunction);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ComponentTransferRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */