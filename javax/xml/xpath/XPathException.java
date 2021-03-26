package javax.xml.xpath;

import java.io.PrintStream;
import java.io.PrintWriter;

public class XPathException extends Exception {
  private final Throwable a;
  
  private static final long b = -1837080260374986980L;
  
  public XPathException(String paramString) {
    super(paramString);
    if (paramString == null)
      throw new NullPointerException("message can't be null"); 
    this.a = null;
  }
  
  public XPathException(Throwable paramThrowable) {
    super((paramThrowable == null) ? null : paramThrowable.toString());
    this.a = paramThrowable;
    if (paramThrowable == null)
      throw new NullPointerException("cause can't be null"); 
  }
  
  public Throwable getCause() {
    return this.a;
  }
  
  public void printStackTrace(PrintStream paramPrintStream) {
    if (getCause() != null) {
      getCause().printStackTrace(paramPrintStream);
      paramPrintStream.println("--------------- linked to ------------------");
    } 
    super.printStackTrace(paramPrintStream);
  }
  
  public void printStackTrace() {
    printStackTrace(System.err);
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter) {
    if (getCause() != null) {
      getCause().printStackTrace(paramPrintWriter);
      paramPrintWriter.println("--------------- linked to ------------------");
    } 
    super.printStackTrace(paramPrintWriter);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/xpath/XPathException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */