package org.apache.xerces.util;

import javax.xml.stream.Location;
import org.apache.xerces.xni.XMLLocator;

public final class StAXLocationWrapper implements XMLLocator {
  private Location fLocation = null;
  
  public void setLocation(Location paramLocation) {
    this.fLocation = paramLocation;
  }
  
  public Location getLocation() {
    return this.fLocation;
  }
  
  public String getPublicId() {
    return (this.fLocation != null) ? this.fLocation.getPublicId() : null;
  }
  
  public String getLiteralSystemId() {
    return (this.fLocation != null) ? this.fLocation.getSystemId() : null;
  }
  
  public String getBaseSystemId() {
    return null;
  }
  
  public String getExpandedSystemId() {
    return getLiteralSystemId();
  }
  
  public int getLineNumber() {
    return (this.fLocation != null) ? this.fLocation.getLineNumber() : -1;
  }
  
  public int getColumnNumber() {
    return (this.fLocation != null) ? this.fLocation.getColumnNumber() : -1;
  }
  
  public int getCharacterOffset() {
    return (this.fLocation != null) ? this.fLocation.getCharacterOffset() : -1;
  }
  
  public String getEncoding() {
    return null;
  }
  
  public String getXMLVersion() {
    return null;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/StAXLocationWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */