package org.apache.xmlgraphics.java2d;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface Graphics2DImagePainter {
  void paint(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D);
  
  Dimension getImageSize();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/Graphics2DImagePainter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */