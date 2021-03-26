package org.apache.xmlgraphics.image.loader.spi;

import java.io.IOException;
import java.util.Map;
import org.apache.xmlgraphics.image.loader.Image;
import org.apache.xmlgraphics.image.loader.ImageException;
import org.apache.xmlgraphics.image.loader.ImageFlavor;

public interface ImageConverter {
  public static final int NO_CONVERSION_PENALTY = 0;
  
  public static final int MINIMAL_CONVERSION_PENALTY = 1;
  
  public static final int MEDIUM_CONVERSION_PENALTY = 10;
  
  Image convert(Image paramImage, Map paramMap) throws ImageException, IOException;
  
  ImageFlavor getTargetFlavor();
  
  ImageFlavor getSourceFlavor();
  
  int getConversionPenalty();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/spi/ImageConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */