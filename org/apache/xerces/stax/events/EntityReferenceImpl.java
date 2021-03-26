package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;

public final class EntityReferenceImpl extends XMLEventImpl implements EntityReference {
  private final String fName;
  
  private final EntityDeclaration fDecl;
  
  public EntityReferenceImpl(EntityDeclaration paramEntityDeclaration, Location paramLocation) {
    this((paramEntityDeclaration != null) ? paramEntityDeclaration.getName() : "", paramEntityDeclaration, paramLocation);
  }
  
  public EntityReferenceImpl(String paramString, EntityDeclaration paramEntityDeclaration, Location paramLocation) {
    super(9, paramLocation);
    this.fName = (paramString != null) ? paramString : "";
    this.fDecl = paramEntityDeclaration;
  }
  
  public EntityDeclaration getDeclaration() {
    return this.fDecl;
  }
  
  public String getName() {
    return this.fName;
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write(38);
      paramWriter.write(this.fName);
      paramWriter.write(59);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/EntityReferenceImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */