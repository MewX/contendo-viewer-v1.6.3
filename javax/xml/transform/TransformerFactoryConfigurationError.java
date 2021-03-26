package javax.xml.transform;

public class TransformerFactoryConfigurationError extends Error {
  private Exception a = null;
  
  public TransformerFactoryConfigurationError() {}
  
  public TransformerFactoryConfigurationError(String paramString) {
    super(paramString);
  }
  
  public TransformerFactoryConfigurationError(Exception paramException) {
    super(paramException.toString());
  }
  
  public TransformerFactoryConfigurationError(Exception paramException, String paramString) {
    super(paramString);
  }
  
  public String getMessage() {
    String str = super.getMessage();
    return (str == null && this.a != null) ? this.a.getMessage() : str;
  }
  
  public Exception getException() {
    return this.a;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/TransformerFactoryConfigurationError.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */