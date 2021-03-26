package javax.xml.stream.events;

import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

public interface XMLEvent extends XMLStreamConstants {
  Characters asCharacters();
  
  EndElement asEndElement();
  
  StartElement asStartElement();
  
  int getEventType();
  
  Location getLocation();
  
  QName getSchemaType();
  
  boolean isAttribute();
  
  boolean isCharacters();
  
  boolean isEndDocument();
  
  boolean isEndElement();
  
  boolean isEntityReference();
  
  boolean isNamespace();
  
  boolean isProcessingInstruction();
  
  boolean isStartDocument();
  
  boolean isStartElement();
  
  void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/XMLEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */