package org.apache.xmlgraphics.ps;

import java.io.IOException;
import java.io.OutputStream;

public interface ImageEncoder {
  void writeTo(OutputStream paramOutputStream) throws IOException;
  
  String getImplicitFilter();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/ImageEncoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */