package org.apache.xmlgraphics.image.loader.spi;

import java.io.IOException;
import javax.xml.transform.Source;
import org.apache.xmlgraphics.image.loader.ImageContext;
import org.apache.xmlgraphics.image.loader.ImageException;
import org.apache.xmlgraphics.image.loader.ImageInfo;

public interface ImagePreloader {
  public static final int DEFAULT_PRIORITY = 1000;
  
  ImageInfo preloadImage(String paramString, Source paramSource, ImageContext paramImageContext) throws ImageException, IOException;
  
  int getPriority();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/spi/ImagePreloader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */