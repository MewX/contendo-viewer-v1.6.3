package org.apache.xmlgraphics.ps.dsc;

import java.io.IOException;
import org.apache.xmlgraphics.ps.dsc.events.DSCEvent;

public interface NestedDocumentHandler {
  void handle(DSCEvent paramDSCEvent, DSCParser paramDSCParser) throws IOException, DSCException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/NestedDocumentHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */