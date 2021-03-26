package org.apache.xmlgraphics.image.rendered;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.RenderedImage;

public interface CachableRed extends RenderedImage {
  Rectangle getBounds();
  
  Shape getDependencyRegion(int paramInt, Rectangle paramRectangle);
  
  Shape getDirtyRegion(int paramInt, Rectangle paramRectangle);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/rendered/CachableRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */