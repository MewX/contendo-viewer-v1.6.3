package org.apache.batik.ext.awt.image.spi;

import java.util.List;

public interface RegistryEntry {
  List getStandardExtensions();
  
  List getMimeTypes();
  
  String getFormatName();
  
  float getPriority();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/RegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */