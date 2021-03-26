package org.xml.sax.ext;

import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;

public class Locator2Impl extends LocatorImpl implements Locator2 {
  private String a;
  
  private String b;
  
  public Locator2Impl() {}
  
  public Locator2Impl(Locator paramLocator) {
    super(paramLocator);
    if (paramLocator instanceof Locator2) {
      Locator2 locator2 = (Locator2)paramLocator;
      this.b = locator2.getXMLVersion();
      this.a = locator2.getEncoding();
    } 
  }
  
  public String getXMLVersion() {
    return this.b;
  }
  
  public String getEncoding() {
    return this.a;
  }
  
  public void setXMLVersion(String paramString) {
    this.b = paramString;
  }
  
  public void setEncoding(String paramString) {
    this.a = paramString;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/ext/Locator2Impl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */