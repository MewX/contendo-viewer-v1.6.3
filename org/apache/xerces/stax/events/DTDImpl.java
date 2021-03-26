package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.DTD;

public final class DTDImpl extends XMLEventImpl implements DTD {
  private final String fDTD;
  
  public DTDImpl(String paramString, Location paramLocation) {
    super(11, paramLocation);
    this.fDTD = (paramString != null) ? paramString : null;
  }
  
  public String getDocumentTypeDeclaration() {
    return this.fDTD;
  }
  
  public Object getProcessedDTD() {
    return null;
  }
  
  public List getNotations() {
    return Collections.EMPTY_LIST;
  }
  
  public List getEntities() {
    return Collections.EMPTY_LIST;
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write(this.fDTD);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/DTDImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */