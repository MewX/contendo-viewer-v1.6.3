package javax.xml.transform;

public interface ErrorListener {
  void warning(TransformerException paramTransformerException) throws TransformerException;
  
  void error(TransformerException paramTransformerException) throws TransformerException;
  
  void fatalError(TransformerException paramTransformerException) throws TransformerException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/ErrorListener.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */