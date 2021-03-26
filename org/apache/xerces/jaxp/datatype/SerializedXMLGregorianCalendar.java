package org.apache.xerces.jaxp.datatype;

import java.io.ObjectStreamException;
import java.io.Serializable;

final class SerializedXMLGregorianCalendar implements Serializable {
  private static final long serialVersionUID = -7752272381890705397L;
  
  private final String lexicalValue;
  
  public SerializedXMLGregorianCalendar(String paramString) {
    this.lexicalValue = paramString;
  }
  
  private Object readResolve() throws ObjectStreamException {
    return (new DatatypeFactoryImpl()).newXMLGregorianCalendar(this.lexicalValue);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/datatype/SerializedXMLGregorianCalendar.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */