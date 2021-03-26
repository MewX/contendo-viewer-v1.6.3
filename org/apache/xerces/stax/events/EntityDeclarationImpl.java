package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EntityDeclaration;

public final class EntityDeclarationImpl extends XMLEventImpl implements EntityDeclaration {
  private final String fPublicId;
  
  private final String fSystemId;
  
  private final String fName;
  
  private final String fNotationName;
  
  public EntityDeclarationImpl(String paramString1, String paramString2, String paramString3, String paramString4, Location paramLocation) {
    super(15, paramLocation);
    this.fPublicId = paramString1;
    this.fSystemId = paramString2;
    this.fName = paramString3;
    this.fNotationName = paramString4;
  }
  
  public String getPublicId() {
    return this.fPublicId;
  }
  
  public String getSystemId() {
    return this.fSystemId;
  }
  
  public String getName() {
    return this.fName;
  }
  
  public String getNotationName() {
    return this.fNotationName;
  }
  
  public String getReplacementText() {
    return null;
  }
  
  public String getBaseURI() {
    return null;
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write("<!ENTITY ");
      paramWriter.write(this.fName);
      if (this.fPublicId != null) {
        paramWriter.write(" PUBLIC \"");
        paramWriter.write(this.fPublicId);
        paramWriter.write("\" \"");
        paramWriter.write(this.fSystemId);
        paramWriter.write(34);
      } else {
        paramWriter.write(" SYSTEM \"");
        paramWriter.write(this.fSystemId);
        paramWriter.write(34);
      } 
      if (this.fNotationName != null) {
        paramWriter.write(" NDATA ");
        paramWriter.write(this.fNotationName);
      } 
      paramWriter.write(62);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/EntityDeclarationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */