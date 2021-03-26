package javax.xml.transform.stax;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;

public class StAXSource implements Source {
  public static final String FEATURE = "http://javax.xml.transform.stax.StAXSource/feature";
  
  private final XMLStreamReader a;
  
  private final XMLEventReader b;
  
  private final String c;
  
  public StAXSource(XMLStreamReader paramXMLStreamReader) {
    if (paramXMLStreamReader == null)
      throw new IllegalArgumentException("XMLStreamReader cannot be null."); 
    int i = paramXMLStreamReader.getEventType();
    if (i != 7 && i != 1)
      throw new IllegalStateException("The state of the XMLStreamReader must be START_DOCUMENT or START_ELEMENT"); 
    this.a = paramXMLStreamReader;
    this.b = null;
    this.c = paramXMLStreamReader.getLocation().getSystemId();
  }
  
  public StAXSource(XMLEventReader paramXMLEventReader) throws XMLStreamException {
    if (paramXMLEventReader == null)
      throw new IllegalArgumentException("XMLEventReader cannot be null."); 
    XMLEvent xMLEvent = paramXMLEventReader.peek();
    if (!xMLEvent.isStartDocument() && !xMLEvent.isStartElement())
      throw new IllegalStateException("The state of the XMLEventReader must be START_DOCUMENT or START_ELEMENT"); 
    this.a = null;
    this.b = paramXMLEventReader;
    this.c = xMLEvent.getLocation().getSystemId();
  }
  
  public XMLStreamReader getXMLStreamReader() {
    return this.a;
  }
  
  public XMLEventReader getXMLEventReader() {
    return this.b;
  }
  
  public String getSystemId() {
    return this.c;
  }
  
  public void setSystemId(String paramString) {
    throw new UnsupportedOperationException("Setting systemId is not supported.");
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/stax/StAXSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */