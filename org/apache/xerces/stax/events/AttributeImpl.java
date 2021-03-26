package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;

public class AttributeImpl extends XMLEventImpl implements Attribute {
  private final boolean fIsSpecified;
  
  private final QName fName;
  
  private final String fValue;
  
  private final String fDtdType;
  
  public AttributeImpl(QName paramQName, String paramString1, String paramString2, boolean paramBoolean, Location paramLocation) {
    this(10, paramQName, paramString1, paramString2, paramBoolean, paramLocation);
  }
  
  protected AttributeImpl(int paramInt, QName paramQName, String paramString1, String paramString2, boolean paramBoolean, Location paramLocation) {
    super(paramInt, paramLocation);
    this.fName = paramQName;
    this.fValue = paramString1;
    this.fDtdType = paramString2;
    this.fIsSpecified = paramBoolean;
  }
  
  public final QName getName() {
    return this.fName;
  }
  
  public final String getValue() {
    return this.fValue;
  }
  
  public final String getDTDType() {
    return this.fDtdType;
  }
  
  public final boolean isSpecified() {
    return this.fIsSpecified;
  }
  
  public final void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      String str = this.fName.getPrefix();
      if (str != null && str.length() > 0) {
        paramWriter.write(str);
        paramWriter.write(58);
      } 
      paramWriter.write(this.fName.getLocalPart());
      paramWriter.write("=\"");
      paramWriter.write(this.fValue);
      paramWriter.write(34);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/AttributeImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */