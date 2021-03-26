package org.apache.batik.ext.awt.image.spi;

import java.io.InputStream;
import java.io.StreamCorruptedException;
import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.util.ParsedURL;

public interface StreamRegistryEntry extends RegistryEntry {
  int getReadlimit();
  
  boolean isCompatibleStream(InputStream paramInputStream) throws StreamCorruptedException;
  
  Filter handleStream(InputStream paramInputStream, ParsedURL paramParsedURL, boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/StreamRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */