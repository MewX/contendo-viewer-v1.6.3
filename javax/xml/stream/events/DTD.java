package javax.xml.stream.events;

import java.util.List;

public interface DTD extends XMLEvent {
  String getDocumentTypeDeclaration();
  
  List getEntities();
  
  List getNotations();
  
  Object getProcessedDTD();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/events/DTD.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */