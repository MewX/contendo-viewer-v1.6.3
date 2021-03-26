package javax.xml.stream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventConsumer;

public interface XMLEventWriter extends XMLEventConsumer {
  void add(XMLEvent paramXMLEvent) throws XMLStreamException;
  
  void add(XMLEventReader paramXMLEventReader) throws XMLStreamException;
  
  void close() throws XMLStreamException;
  
  void flush() throws XMLStreamException;
  
  NamespaceContext getNamespaceContext();
  
  String getPrefix(String paramString) throws XMLStreamException;
  
  void setDefaultNamespace(String paramString) throws XMLStreamException;
  
  void setNamespaceContext(NamespaceContext paramNamespaceContext) throws XMLStreamException;
  
  void setPrefix(String paramString1, String paramString2) throws XMLStreamException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/XMLEventWriter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */