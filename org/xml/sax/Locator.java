package org.xml.sax;

public interface Locator {
  String getPublicId();
  
  String getSystemId();
  
  int getLineNumber();
  
  int getColumnNumber();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/Locator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */