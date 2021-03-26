package javax.xml.transform.stax;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

public class StAXResult implements Result {
  public static final String FEATURE = "http://javax.xml.transform.stax.StAXResult/feature";
  
  private final XMLStreamWriter a;
  
  private final XMLEventWriter b;
  
  public StAXResult(XMLStreamWriter paramXMLStreamWriter) {
    if (paramXMLStreamWriter == null)
      throw new IllegalArgumentException("XMLStreamWriter cannot be null."); 
    this.a = paramXMLStreamWriter;
    this.b = null;
  }
  
  public StAXResult(XMLEventWriter paramXMLEventWriter) {
    if (paramXMLEventWriter == null)
      throw new IllegalArgumentException("XMLEventWriter cannot be null."); 
    this.a = null;
    this.b = paramXMLEventWriter;
  }
  
  public XMLStreamWriter getXMLStreamWriter() {
    return this.a;
  }
  
  public XMLEventWriter getXMLEventWriter() {
    return this.b;
  }
  
  public String getSystemId() {
    return null;
  }
  
  public void setSystemId(String paramString) {
    throw new UnsupportedOperationException("Setting systemId is not supported.");
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/stax/StAXResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */