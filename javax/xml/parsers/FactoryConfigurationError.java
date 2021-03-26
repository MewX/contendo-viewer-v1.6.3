package javax.xml.parsers;

public class FactoryConfigurationError extends Error {
  private Exception a = null;
  
  public FactoryConfigurationError() {}
  
  public FactoryConfigurationError(String paramString) {
    super(paramString);
  }
  
  public FactoryConfigurationError(Exception paramException) {
    super(paramException.toString());
  }
  
  public FactoryConfigurationError(Exception paramException, String paramString) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/parsers/FactoryConfigurationError.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */