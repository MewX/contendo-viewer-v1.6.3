package javax.xml.datatype;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class DatatypeConfigurationException extends Exception {
  private static final long b = -1699373159027047238L;
  
  private Throwable c;
  
  private transient boolean d = false;
  
  static Class a;
  
  public DatatypeConfigurationException() {}
  
  public DatatypeConfigurationException(String paramString) {
    super(paramString);
  }
  
  public DatatypeConfigurationException(String paramString, Throwable paramThrowable) {
    super(paramString);
    a(paramThrowable);
  }
  
  public DatatypeConfigurationException(Throwable paramThrowable) {
    super((paramThrowable == null) ? null : paramThrowable.toString());
    a(paramThrowable);
  }
  
  public void printStackTrace() {
    if (!this.d && this.c != null) {
      a(new PrintWriter(System.err, true));
    } else {
      super.printStackTrace();
    } 
  }
  
  public void printStackTrace(PrintStream paramPrintStream) {
    if (!this.d && this.c != null) {
      a(new PrintWriter(paramPrintStream));
    } else {
      super.printStackTrace(paramPrintStream);
    } 
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter) {
    if (!this.d && this.c != null) {
      a(paramPrintWriter);
    } else {
      super.printStackTrace(paramPrintWriter);
    } 
  }
  
  private void a(PrintWriter paramPrintWriter) {
    this.c.printStackTrace(paramPrintWriter);
    paramPrintWriter.println("------------------------------------------");
    super.printStackTrace(paramPrintWriter);
  }
  
  private void a(Throwable paramThrowable) {
    this.c = paramThrowable;
    try {
      Method method = getClass().getMethod("initCause", new Class[] { (a == null) ? (a = a("java.lang.Throwable")) : a });
      method.invoke(this, new Object[] { paramThrowable });
      this.d = true;
    } catch (Exception exception) {}
  }
  
  private void a(ObjectInputStream paramObjectInputStream) throws IOException, ClassNotFoundException {
    paramObjectInputStream.defaultReadObject();
    try {
      Method method = getClass().getMethod("getCause", new Class[0]);
      Throwable throwable = (Throwable)method.invoke(this, new Object[0]);
      if (this.c == null) {
        this.c = throwable;
      } else if (throwable == null) {
        Method method1 = getClass().getMethod("initCause", new Class[] { (a == null) ? (a = a("java.lang.Throwable")) : a });
        method1.invoke(this, new Object[] { this.c });
      } 
      this.d = true;
    } catch (Exception exception) {}
  }
  
  static Class a(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/datatype/DatatypeConfigurationException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */