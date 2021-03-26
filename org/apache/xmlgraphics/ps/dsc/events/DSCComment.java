package org.apache.xmlgraphics.ps.dsc.events;

import java.io.IOException;
import org.apache.xmlgraphics.ps.PSGenerator;

public interface DSCComment extends DSCEvent {
  String getName();
  
  void parseValue(String paramString);
  
  boolean hasValues();
  
  boolean isAtend();
  
  void generate(PSGenerator paramPSGenerator) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */