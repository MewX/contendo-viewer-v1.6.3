package org.apache.xerces.stax.events;

import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndDocument;

public final class EndDocumentImpl extends XMLEventImpl implements EndDocument {
  public EndDocumentImpl(Location paramLocation) {
    super(8, paramLocation);
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {}
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/EndDocumentImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */