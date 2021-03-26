package javax.xml.transform;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransformerException extends Exception {
  private static final long serialVersionUID = 975798773772956428L;
  
  SourceLocator locator = null;
  
  Throwable containedException = null;
  
  public SourceLocator getLocator() {
    return this.locator;
  }
  
  public void setLocator(SourceLocator paramSourceLocator) {
    this.locator = paramSourceLocator;
  }
  
  public Throwable getException() {
    return this.containedException;
  }
  
  public Throwable getCause() {
    return (this.containedException == this) ? null : this.containedException;
  }
  
  public synchronized Throwable initCause(Throwable paramThrowable) {
    if (this.containedException != null)
      throw new IllegalStateException("Can't overwrite cause"); 
    if (paramThrowable == this)
      throw new IllegalArgumentException("Self-causation not permitted"); 
    this.containedException = paramThrowable;
    return this;
  }
  
  public TransformerException(String paramString) {
    super(paramString);
  }
  
  public TransformerException(Throwable paramThrowable) {
    super(paramThrowable.toString());
  }
  
  public TransformerException(String paramString, Throwable paramThrowable) {
    super((paramString == null || paramString.length() == 0) ? paramThrowable.toString() : paramString);
  }
  
  public TransformerException(String paramString, SourceLocator paramSourceLocator) {
    super(paramString);
  }
  
  public TransformerException(String paramString, SourceLocator paramSourceLocator, Throwable paramThrowable) {
    super(paramString);
  }
  
  public String getMessageAndLocation() {
    StringBuffer stringBuffer = new StringBuffer();
    String str = getMessage();
    if (null != str)
      stringBuffer.append(str); 
    if (null != this.locator) {
      String str1 = this.locator.getSystemId();
      int i = this.locator.getLineNumber();
      int j = this.locator.getColumnNumber();
      if (null != str1) {
        stringBuffer.append("; SystemID: ");
        stringBuffer.append(str1);
      } 
      if (0 != i) {
        stringBuffer.append("; Line#: ");
        stringBuffer.append(i);
      } 
      if (0 != j) {
        stringBuffer.append("; Column#: ");
        stringBuffer.append(j);
      } 
    } 
    return stringBuffer.toString();
  }
  
  public String getLocationAsString() {
    if (null != this.locator) {
      StringBuffer stringBuffer = new StringBuffer();
      String str = this.locator.getSystemId();
      int i = this.locator.getLineNumber();
      int j = this.locator.getColumnNumber();
      if (null != str) {
        stringBuffer.append("; SystemID: ");
        stringBuffer.append(str);
      } 
      if (0 != i) {
        stringBuffer.append("; Line#: ");
        stringBuffer.append(i);
      } 
      if (0 != j) {
        stringBuffer.append("; Column#: ");
        stringBuffer.append(j);
      } 
      return stringBuffer.toString();
    } 
    return null;
  }
  
  public void printStackTrace() {
    printStackTrace(new PrintWriter(System.err, true));
  }
  
  public void printStackTrace(PrintStream paramPrintStream) {
    printStackTrace(new PrintWriter(paramPrintStream));
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter) {
    if (paramPrintWriter == null)
      paramPrintWriter = new PrintWriter(System.err, true); 
    try {
      String str = getLocationAsString();
      if (null != str)
        paramPrintWriter.println(str); 
      super.printStackTrace(paramPrintWriter);
    } catch (Throwable throwable) {}
    boolean bool = false;
    try {
      Throwable.class.getMethod("getCause", (Class[])null);
      bool = true;
    } catch (NoSuchMethodException noSuchMethodException) {}
    if (!bool) {
      Throwable throwable = getException();
      for (byte b = 0; b < 10 && null != throwable; b++) {
        paramPrintWriter.println("---------");
        try {
          if (throwable instanceof TransformerException) {
            String str = ((TransformerException)throwable).getLocationAsString();
            if (null != str)
              paramPrintWriter.println(str); 
          } 
          throwable.printStackTrace(paramPrintWriter);
        } catch (Throwable throwable1) {
          paramPrintWriter.println("Could not print stack trace...");
        } 
        try {
          Method method = throwable.getClass().getMethod("getException", (Class[])null);
          if (null != method) {
            Throwable throwable1 = throwable;
            throwable = (Throwable)method.invoke(throwable, (Object[])null);
            if (throwable1 == throwable)
              break; 
          } else {
            throwable = null;
          } 
        } catch (InvocationTargetException invocationTargetException) {
          throwable = null;
        } catch (IllegalAccessException illegalAccessException) {
          throwable = null;
        } catch (NoSuchMethodException noSuchMethodException) {
          throwable = null;
        } 
      } 
    } 
    paramPrintWriter.flush();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/TransformerException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */