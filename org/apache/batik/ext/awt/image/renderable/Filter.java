package org.apache.batik.ext.awt.image.renderable;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.renderable.RenderableImage;

public interface Filter extends RenderableImage {
  Rectangle2D getBounds2D();
  
  long getTimeStamp();
  
  Shape getDependencyRegion(int paramInt, Rectangle2D paramRectangle2D);
  
  Shape getDirtyRegion(int paramInt, Rectangle2D paramRectangle2D);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/Filter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */