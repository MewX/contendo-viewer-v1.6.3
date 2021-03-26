package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.NotationDeclaration;

public final class NotationDeclarationImpl extends XMLEventImpl implements NotationDeclaration {
  private final String fSystemId;
  
  private final String fPublicId;
  
  private final String fName;
  
  public NotationDeclarationImpl(String paramString1, String paramString2, String paramString3, Location paramLocation) {
    super(14, paramLocation);
    this.fName = paramString1;
    this.fPublicId = paramString2;
    this.fSystemId = paramString3;
  }
  
  public String getName() {
    return this.fName;
  }
  
  public String getPublicId() {
    return this.fPublicId;
  }
  
  public String getSystemId() {
    return this.fSystemId;
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write("<!NOTATION ");
      if (this.fPublicId != null) {
        paramWriter.write("PUBLIC \"");
        paramWriter.write(this.fPublicId);
        paramWriter.write(34);
        if (this.fSystemId != null) {
          paramWriter.write(" \"");
          paramWriter.write(this.fSystemId);
          paramWriter.write(34);
        } 
      } else {
        paramWriter.write("SYSTEM \"");
        paramWriter.write(this.fSystemId);
        paramWriter.write(34);
      } 
      paramWriter.write(this.fName);
      paramWriter.write(62);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/NotationDeclarationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */