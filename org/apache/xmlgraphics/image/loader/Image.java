package org.apache.xmlgraphics.image.loader;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;

public interface Image {
  ImageInfo getInfo();
  
  ImageSize getSize();
  
  ImageFlavor getFlavor();
  
  boolean isCacheable();
  
  ICC_Profile getICCProfile();
  
  ColorSpace getColorSpace();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/Image.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */