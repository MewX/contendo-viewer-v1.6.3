package org.apache.batik.ext.awt.image.renderable;

import java.awt.Shape;

public interface ClipRable extends Filter {
  void setUseAntialiasedClip(boolean paramBoolean);
  
  boolean getUseAntialiasedClip();
  
  void setSource(Filter paramFilter);
  
  Filter getSource();
  
  void setClipPath(Shape paramShape);
  
  Shape getClipPath();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ClipRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */