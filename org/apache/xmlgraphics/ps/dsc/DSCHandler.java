package org.apache.xmlgraphics.ps.dsc;

import java.io.IOException;
import org.apache.xmlgraphics.ps.dsc.events.DSCComment;

public interface DSCHandler {
  void startDocument(String paramString) throws IOException;
  
  void endDocument() throws IOException;
  
  void handleDSCComment(DSCComment paramDSCComment) throws IOException;
  
  void line(String paramString) throws IOException;
  
  void comment(String paramString) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/DSCHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */