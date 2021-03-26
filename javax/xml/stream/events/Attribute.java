package javax.xml.stream.events;

import javax.xml.namespace.QName;

public interface Attribute extends XMLEvent {
  String getDTDType();
  
  QName getName();
  
  String getValue();
  
  boolean isSpecified();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/Attribute.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */