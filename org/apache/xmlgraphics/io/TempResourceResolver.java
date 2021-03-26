package org.apache.xmlgraphics.io;

import java.io.IOException;
import java.io.OutputStream;

public interface TempResourceResolver {
  Resource getResource(String paramString) throws IOException;
  
  OutputStream getOutputStream(String paramString) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/io/TempResourceResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */