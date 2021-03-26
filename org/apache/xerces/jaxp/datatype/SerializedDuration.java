package org.apache.xerces.jaxp.datatype;

import java.io.ObjectStreamException;
import java.io.Serializable;

final class SerializedDuration implements Serializable {
  private static final long serialVersionUID = 3897193592341225793L;
  
  private final String lexicalValue;
  
  public SerializedDuration(String paramString) {
    this.lexicalValue = paramString;
  }
  
  private Object readResolve() throws ObjectStreamException {
    return (new DatatypeFactoryImpl()).newDuration(this.lexicalValue);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/datatype/SerializedDuration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */