package org.apache.batik.ext.awt.image.renderable;

import java.awt.geom.Rectangle2D;
import org.apache.batik.ext.awt.image.PadMode;

public interface PadRable extends Filter {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  void setPadRect(Rectangle2D paramRectangle2D);
  
  Rectangle2D getPadRect();
  
  void setPadMode(PadMode paramPadMode);
  
  PadMode getPadMode();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/PadRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */