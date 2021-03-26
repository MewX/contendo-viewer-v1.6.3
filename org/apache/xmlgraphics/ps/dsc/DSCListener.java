package org.apache.xmlgraphics.ps.dsc;

import java.io.IOException;
import org.apache.xmlgraphics.ps.dsc.events.DSCEvent;

public interface DSCListener {
  void processEvent(DSCEvent paramDSCEvent, DSCParser paramDSCParser) throws IOException, DSCException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/DSCListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */