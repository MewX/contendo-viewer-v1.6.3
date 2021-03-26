package javax.xml.stream.events;

public interface EntityDeclaration extends XMLEvent {
  String getBaseURI();
  
  String getName();
  
  String getNotationName();
  
  String getPublicId();
  
  String getReplacementText();
  
  String getSystemId();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/EntityDeclaration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */