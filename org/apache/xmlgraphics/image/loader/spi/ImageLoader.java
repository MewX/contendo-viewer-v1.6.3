package org.apache.xmlgraphics.image.loader.spi;

import java.io.IOException;
import java.util.Map;
import org.apache.xmlgraphics.image.loader.Image;
import org.apache.xmlgraphics.image.loader.ImageException;
import org.apache.xmlgraphics.image.loader.ImageFlavor;
import org.apache.xmlgraphics.image.loader.ImageInfo;
import org.apache.xmlgraphics.image.loader.ImageSessionContext;

public interface ImageLoader {
  public static final int NO_LOADING_PENALTY = 0;
  
  public static final int MEDIUM_LOADING_PENALTY = 10;
  
  Image loadImage(ImageInfo paramImageInfo, Map paramMap, ImageSessionContext paramImageSessionContext) throws ImageException, IOException;
  
  Image loadImage(ImageInfo paramImageInfo, ImageSessionContext paramImageSessionContext) throws ImageException, IOException;
  
  ImageFlavor getTargetFlavor();
  
  int getUsagePenalty();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/spi/ImageLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */