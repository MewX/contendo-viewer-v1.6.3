package javax.xml.stream;

public class FactoryConfigurationError extends Error {
  private static final long a = -2994412584589975744L;
  
  private Exception b;
  
  public FactoryConfigurationError() {}
  
  public FactoryConfigurationError(Exception paramException) {
    this.b = paramException;
  }
  
  public FactoryConfigurationError(Exception paramException, String paramString) {
    super(paramString);
    this.b = paramException;
  }
  
  public FactoryConfigurationError(String paramString) {
    super(paramString);
  }
  
  public FactoryConfigurationError(String paramString, Exception paramException) {
    super(paramString);
    this.b = paramException;
  }
  
  public Exception getException() {
    return this.b;
  }
  
  public String getMessage() {
    String str = super.getMessage();
    if (str != null)
      return str; 
    if (this.b != null) {
      str = this.b.getMessage();
      if (str == null)
        str = this.b.getClass().toString(); 
    } 
    return str;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/FactoryConfigurationError.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */