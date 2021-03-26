package javax.xml.stream.events;

public interface Characters extends XMLEvent {
  String getData();
  
  boolean isCData();
  
  boolean isIgnorableWhiteSpace();
  
  boolean isWhiteSpace();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/Characters.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */