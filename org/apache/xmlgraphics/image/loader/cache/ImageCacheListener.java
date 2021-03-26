package org.apache.xmlgraphics.image.loader.cache;

import java.util.EventListener;

public interface ImageCacheListener extends EventListener {
  void invalidHit(String paramString);
  
  void cacheHitImageInfo(String paramString);
  
  void cacheMissImageInfo(String paramString);
  
  void cacheHitImage(ImageKey paramImageKey);
  
  void cacheMissImage(ImageKey paramImageKey);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/cache/ImageCacheListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */