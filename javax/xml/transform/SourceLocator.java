package javax.xml.transform;

public interface SourceLocator {
  String getPublicId();
  
  String getSystemId();
  
  int getLineNumber();
  
  int getColumnNumber();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/SourceLocator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */