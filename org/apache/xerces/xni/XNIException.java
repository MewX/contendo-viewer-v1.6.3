package org.apache.xerces.xni;

public class XNIException extends RuntimeException {
  static final long serialVersionUID = 9019819772686063775L;
  
  private Exception fException = this;
  
  public XNIException(String paramString) {
    super(paramString);
  }
  
  public XNIException(Exception paramException) {
    super(paramException.getMessage());
    this.fException = paramException;
  }
  
  public XNIException(String paramString, Exception paramException) {
    super(paramString);
    this.fException = paramException;
  }
  
  public Exception getException() {
    return (this.fException != this) ? this.fException : null;
  }
  
  public synchronized Throwable initCause(Throwable paramThrowable) {
    if (this.fException != this)
      throw new IllegalStateException(); 
    if (paramThrowable == this)
      throw new IllegalArgumentException(); 
    this.fException = (Exception)paramThrowable;
    return this;
  }
  
  public Throwable getCause() {
    return getException();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/XNIException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */