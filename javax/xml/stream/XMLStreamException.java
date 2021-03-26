package javax.xml.stream;

public class XMLStreamException extends Exception {
  private static final long c = 2018819321811497362L;
  
  protected Throwable a;
  
  protected Location b;
  
  public XMLStreamException() {}
  
  public XMLStreamException(String paramString) {
    super(paramString);
  }
  
  public XMLStreamException(Throwable paramThrowable) {
    this.a = paramThrowable;
  }
  
  public XMLStreamException(String paramString, Throwable paramThrowable) {
    super(paramString);
    this.a = paramThrowable;
  }
  
  public XMLStreamException(String paramString, Location paramLocation, Throwable paramThrowable) {
    super(paramString);
    this.b = paramLocation;
    this.a = paramThrowable;
  }
  
  public XMLStreamException(String paramString, Location paramLocation) {
    super(paramString);
    this.b = paramLocation;
  }
  
  public Throwable getNestedException() {
    return this.a;
  }
  
  public Location getLocation() {
    return this.b;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/XMLStreamException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */