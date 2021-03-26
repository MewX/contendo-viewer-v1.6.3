package org.apache.xmlgraphics.java2d.ps;

import java.io.IOException;
import org.apache.xmlgraphics.java2d.TextHandler;

public interface PSTextHandler extends TextHandler {
  void writeSetup() throws IOException;
  
  void writePageSetup() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/ps/PSTextHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */