package javax.xml.stream.util;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public interface XMLEventAllocator {
  XMLEvent allocate(XMLStreamReader paramXMLStreamReader) throws XMLStreamException;
  
  void allocate(XMLStreamReader paramXMLStreamReader, XMLEventConsumer paramXMLEventConsumer) throws XMLStreamException;
  
  XMLEventAllocator newInstance();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/util/XMLEventAllocator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */