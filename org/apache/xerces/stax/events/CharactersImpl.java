package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import org.apache.xerces.util.XMLChar;

public final class CharactersImpl extends XMLEventImpl implements Characters {
  private final String fData;
  
  public CharactersImpl(String paramString, int paramInt, Location paramLocation) {
    super(paramInt, paramLocation);
    this.fData = (paramString != null) ? paramString : "";
  }
  
  public String getData() {
    return this.fData;
  }
  
  public boolean isWhiteSpace() {
    byte b1 = (this.fData != null) ? this.fData.length() : 0;
    if (!b1)
      return false; 
    for (byte b2 = 0; b2 < b1; b2++) {
      if (!XMLChar.isSpace(this.fData.charAt(b2)))
        return false; 
    } 
    return true;
  }
  
  public boolean isCData() {
    return (12 == getEventType());
  }
  
  public boolean isIgnorableWhiteSpace() {
    return (6 == getEventType());
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write(this.fData);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/CharactersImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */