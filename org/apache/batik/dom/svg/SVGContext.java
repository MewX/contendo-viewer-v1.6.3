package org.apache.batik.dom.svg;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public interface SVGContext {
  public static final int PERCENTAGE_FONT_SIZE = 0;
  
  public static final int PERCENTAGE_VIEWPORT_WIDTH = 1;
  
  public static final int PERCENTAGE_VIEWPORT_HEIGHT = 2;
  
  public static final int PERCENTAGE_VIEWPORT_SIZE = 3;
  
  float getPixelUnitToMillimeter();
  
  float getPixelToMM();
  
  Rectangle2D getBBox();
  
  AffineTransform getScreenTransform();
  
  void setScreenTransform(AffineTransform paramAffineTransform);
  
  AffineTransform getCTM();
  
  AffineTransform getGlobalTransform();
  
  float getViewportWidth();
  
  float getViewportHeight();
  
  float getFontSize();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */