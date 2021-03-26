package javax.xml.stream.events;

public interface StartDocument extends XMLEvent {
  boolean encodingSet();
  
  String getCharacterEncodingScheme();
  
  String getSystemId();
  
  String getVersion();
  
  boolean isStandalone();
  
  boolean standaloneSet();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/StartDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */