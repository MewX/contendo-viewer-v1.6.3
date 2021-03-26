package org.xml.sax;

public class SAXException extends Exception {
  private Exception exception = null;
  
  static final long serialVersionUID = 583241635256073760L;
  
  public SAXException() {}
  
  public SAXException(String paramString) {
    super(paramString);
  }
  
  public SAXException(Exception paramException) {}
  
  public SAXException(String paramString, Exception paramException) {
    super(paramString);
  }
  
  public String getMessage() {
    String str = super.getMessage();
    return (str == null && this.exception != null) ? this.exception.getMessage() : str;
  }
  
  public Exception getException() {
    return this.exception;
  }
  
  public String toString() {
    return (this.exception != null) ? this.exception.toString() : super.toString();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/SAXException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */