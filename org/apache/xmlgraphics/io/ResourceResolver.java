package org.apache.xmlgraphics.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public interface ResourceResolver {
  Resource getResource(URI paramURI) throws IOException;
  
  OutputStream getOutputStream(URI paramURI) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/io/ResourceResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */