package org.apache.batik.apps.rasterizer;

import java.io.IOException;
import java.io.InputStream;

public interface SVGConverterSource {
  String getName();
  
  InputStream openStream() throws IOException;
  
  boolean isSameAs(String paramString);
  
  boolean isReadable();
  
  String getURI();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/SVGConverterSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */