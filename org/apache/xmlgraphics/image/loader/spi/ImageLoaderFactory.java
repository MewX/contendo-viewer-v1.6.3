package org.apache.xmlgraphics.image.loader.spi;

import org.apache.xmlgraphics.image.loader.ImageFlavor;
import org.apache.xmlgraphics.image.loader.ImageInfo;

public interface ImageLoaderFactory {
  String[] getSupportedMIMETypes();
  
  ImageFlavor[] getSupportedFlavors(String paramString);
  
  boolean isSupported(ImageInfo paramImageInfo);
  
  ImageLoader newImageLoader(ImageFlavor paramImageFlavor);
  
  int getUsagePenalty(String paramString, ImageFlavor paramImageFlavor);
  
  boolean isAvailable();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/spi/ImageLoaderFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */