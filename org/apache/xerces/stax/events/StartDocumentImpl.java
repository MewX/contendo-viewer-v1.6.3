package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartDocument;

public final class StartDocumentImpl extends XMLEventImpl implements StartDocument {
  private final String fCharEncoding;
  
  private final boolean fEncodingSet;
  
  private final String fVersion;
  
  private final boolean fIsStandalone;
  
  private final boolean fStandaloneSet;
  
  public StartDocumentImpl(String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, Location paramLocation) {
    super(7, paramLocation);
    this.fCharEncoding = paramString1;
    this.fEncodingSet = paramBoolean1;
    this.fIsStandalone = paramBoolean2;
    this.fStandaloneSet = paramBoolean3;
    this.fVersion = paramString2;
  }
  
  public String getSystemId() {
    return getLocation().getSystemId();
  }
  
  public String getCharacterEncodingScheme() {
    return this.fCharEncoding;
  }
  
  public boolean encodingSet() {
    return this.fEncodingSet;
  }
  
  public boolean isStandalone() {
    return this.fIsStandalone;
  }
  
  public boolean standaloneSet() {
    return this.fStandaloneSet;
  }
  
  public String getVersion() {
    return this.fVersion;
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write("<?xml version=\"");
      paramWriter.write((this.fVersion != null && this.fVersion.length() > 0) ? this.fVersion : "1.0");
      paramWriter.write(34);
      if (encodingSet()) {
        paramWriter.write(" encoding=\"");
        paramWriter.write(this.fCharEncoding);
        paramWriter.write(34);
      } 
      if (standaloneSet()) {
        paramWriter.write(" standalone=\"");
        paramWriter.write(this.fIsStandalone ? "yes" : "no");
        paramWriter.write(34);
      } 
      paramWriter.write("?>");
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/StartDocumentImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */