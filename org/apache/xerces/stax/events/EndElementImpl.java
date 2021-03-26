package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;

public final class EndElementImpl extends ElementImpl implements EndElement {
  public EndElementImpl(QName paramQName, Iterator paramIterator, Location paramLocation) {
    super(paramQName, false, paramIterator, paramLocation);
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write("</");
      QName qName = getName();
      String str = qName.getPrefix();
      if (str != null && str.length() > 0) {
        paramWriter.write(str);
        paramWriter.write(58);
      } 
      paramWriter.write(qName.getLocalPart());
      paramWriter.write(62);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/EndElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */