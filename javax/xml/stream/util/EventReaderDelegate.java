package javax.xml.stream.util;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class EventReaderDelegate implements XMLEventReader {
  private XMLEventReader a;
  
  public EventReaderDelegate() {}
  
  public EventReaderDelegate(XMLEventReader paramXMLEventReader) {
    this.a = paramXMLEventReader;
  }
  
  public void setParent(XMLEventReader paramXMLEventReader) {
    this.a = paramXMLEventReader;
  }
  
  public XMLEventReader getParent() {
    return this.a;
  }
  
  public void close() throws XMLStreamException {
    this.a.close();
  }
  
  public String getElementText() throws XMLStreamException {
    return this.a.getElementText();
  }
  
  public Object getProperty(String paramString) throws IllegalArgumentException {
    return this.a.getProperty(paramString);
  }
  
  public boolean hasNext() {
    return this.a.hasNext();
  }
  
  public Object next() {
    return this.a.next();
  }
  
  public XMLEvent nextEvent() throws XMLStreamException {
    return this.a.nextEvent();
  }
  
  public XMLEvent nextTag() throws XMLStreamException {
    return this.a.nextTag();
  }
  
  public XMLEvent peek() throws XMLStreamException {
    return this.a.peek();
  }
  
  public void remove() {
    this.a.remove();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/util/EventReaderDelegate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */