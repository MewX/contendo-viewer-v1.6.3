package org.apache.xerces.stax;

import javax.xml.stream.Location;

public final class EmptyLocation implements Location {
  private static final EmptyLocation EMPTY_LOCATION_INSTANCE = new EmptyLocation();
  
  public static EmptyLocation getInstance() {
    return EMPTY_LOCATION_INSTANCE;
  }
  
  public int getLineNumber() {
    return -1;
  }
  
  public int getColumnNumber() {
    return -1;
  }
  
  public int getCharacterOffset() {
    return -1;
  }
  
  public String getPublicId() {
    return null;
  }
  
  public String getSystemId() {
    return null;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/EmptyLocation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */