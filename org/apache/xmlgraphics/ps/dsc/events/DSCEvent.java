package org.apache.xmlgraphics.ps.dsc.events;

import java.io.IOException;
import org.apache.xmlgraphics.ps.PSGenerator;
import org.apache.xmlgraphics.ps.dsc.DSCParserConstants;

public interface DSCEvent extends DSCParserConstants {
  int getEventType();
  
  DSCComment asDSCComment();
  
  PostScriptLine asLine();
  
  boolean isDSCComment();
  
  boolean isComment();
  
  boolean isHeaderComment();
  
  boolean isLine();
  
  void generate(PSGenerator paramPSGenerator) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */