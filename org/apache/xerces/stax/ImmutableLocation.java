package org.apache.xerces.stax;

import javax.xml.stream.Location;

public class ImmutableLocation implements Location {
  private final int fCharacterOffset;
  
  private final int fColumnNumber;
  
  private final int fLineNumber;
  
  private final String fPublicId;
  
  private final String fSystemId;
  
  public ImmutableLocation(Location paramLocation) {
    this(paramLocation.getCharacterOffset(), paramLocation.getColumnNumber(), paramLocation.getLineNumber(), paramLocation.getPublicId(), paramLocation.getSystemId());
  }
  
  public ImmutableLocation(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2) {
    this.fCharacterOffset = paramInt1;
    this.fColumnNumber = paramInt2;
    this.fLineNumber = paramInt3;
    this.fPublicId = paramString1;
    this.fSystemId = paramString2;
  }
  
  public int getCharacterOffset() {
    return this.fCharacterOffset;
  }
  
  public int getColumnNumber() {
    return this.fColumnNumber;
  }
  
  public int getLineNumber() {
    return this.fLineNumber;
  }
  
  public String getPublicId() {
    return this.fPublicId;
  }
  
  public String getSystemId() {
    return this.fSystemId;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/ImmutableLocation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */