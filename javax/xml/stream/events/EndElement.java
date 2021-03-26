package javax.xml.stream.events;

import java.util.Iterator;
import javax.xml.namespace.QName;

public interface EndElement extends XMLEvent {
  QName getName();
  
  Iterator getNamespaces();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/EndElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */