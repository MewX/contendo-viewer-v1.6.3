package javax.xml.stream.events;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

public interface StartElement extends XMLEvent {
  Attribute getAttributeByName(QName paramQName);
  
  Iterator getAttributes();
  
  QName getName();
  
  NamespaceContext getNamespaceContext();
  
  Iterator getNamespaces();
  
  String getNamespaceURI(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/StartElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */