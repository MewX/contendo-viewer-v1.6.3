package org.apache.batik.ext.awt.image.renderable;

import java.awt.geom.AffineTransform;

public interface AffineRable extends Filter {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  void setAffine(AffineTransform paramAffineTransform);
  
  AffineTransform getAffine();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/AffineRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */