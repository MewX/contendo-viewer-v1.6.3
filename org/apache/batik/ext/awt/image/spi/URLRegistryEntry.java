package org.apache.batik.ext.awt.image.spi;

import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.util.ParsedURL;

public interface URLRegistryEntry extends RegistryEntry {
  boolean isCompatibleURL(ParsedURL paramParsedURL);
  
  Filter handleURL(ParsedURL paramParsedURL, boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/URLRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */