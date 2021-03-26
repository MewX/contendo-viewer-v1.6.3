package javax.xml.transform;

public class TransformerConfigurationException extends TransformerException {
  public TransformerConfigurationException() {
    super("Configuration Error");
  }
  
  public TransformerConfigurationException(String paramString) {
    super(paramString);
  }
  
  public TransformerConfigurationException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public TransformerConfigurationException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
  
  public TransformerConfigurationException(String paramString, SourceLocator paramSourceLocator) {
    super(paramString, paramSourceLocator);
  }
  
  public TransformerConfigurationException(String paramString, SourceLocator paramSourceLocator, Throwable paramThrowable) {
    super(paramString, paramSourceLocator, paramThrowable);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/TransformerConfigurationException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */