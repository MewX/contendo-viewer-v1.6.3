package org.apache.batik.gvt.renderer;

import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Collection;

public interface ImageRenderer extends Renderer {
  void dispose();
  
  void updateOffScreen(int paramInt1, int paramInt2);
  
  void setTransform(AffineTransform paramAffineTransform);
  
  AffineTransform getTransform();
  
  void setRenderingHints(RenderingHints paramRenderingHints);
  
  RenderingHints getRenderingHints();
  
  BufferedImage getOffScreen();
  
  void clearOffScreen();
  
  void flush();
  
  void flush(Rectangle paramRectangle);
  
  void flush(Collection paramCollection);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/renderer/ImageRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */