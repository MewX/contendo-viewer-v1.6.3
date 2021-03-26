package javax.xml.stream;

import java.util.Iterator;
import javax.xml.stream.events.XMLEvent;

public interface XMLEventReader extends Iterator {
  void close() throws XMLStreamException;
  
  String getElementText() throws XMLStreamException;
  
  Object getProperty(String paramString) throws IllegalArgumentException;
  
  boolean hasNext();
  
  XMLEvent nextEvent() throws XMLStreamException;
  
  XMLEvent nextTag() throws XMLStreamException;
  
  XMLEvent peek() throws XMLStreamException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/XMLEventReader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */