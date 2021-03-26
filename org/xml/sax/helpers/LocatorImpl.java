package org.xml.sax.helpers;

import org.xml.sax.Locator;

public class LocatorImpl implements Locator {
  private String publicId;
  
  private String systemId;
  
  private int lineNumber;
  
  private int columnNumber;
  
  public LocatorImpl() {}
  
  public LocatorImpl(Locator paramLocator) {
    setPublicId(paramLocator.getPublicId());
    setSystemId(paramLocator.getSystemId());
    setLineNumber(paramLocator.getLineNumber());
    setColumnNumber(paramLocator.getColumnNumber());
  }
  
  public String getPublicId() {
    return this.publicId;
  }
  
  public String getSystemId() {
    return this.systemId;
  }
  
  public int getLineNumber() {
    return this.lineNumber;
  }
  
  public int getColumnNumber() {
    return this.columnNumber;
  }
  
  public void setPublicId(String paramString) {
    this.publicId = paramString;
  }
  
  public void setSystemId(String paramString) {
    this.systemId = paramString;
  }
  
  public void setLineNumber(int paramInt) {
    this.lineNumber = paramInt;
  }
  
  public void setColumnNumber(int paramInt) {
    this.columnNumber = paramInt;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/LocatorImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */