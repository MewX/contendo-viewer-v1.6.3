package javax.xml.stream.events;

public interface EntityReference extends XMLEvent {
  EntityDeclaration getDeclaration();
  
  String getName();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/EntityReference.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */