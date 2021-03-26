package javax.xml.stream.events;

public interface Namespace extends Attribute {
  String getNamespaceURI();
  
  String getPrefix();
  
  boolean isDefaultNamespaceDeclaration();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/Namespace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */