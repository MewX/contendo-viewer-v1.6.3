package org.apache.xerces.xni;

public interface XMLLocator {
  String getPublicId();
  
  String getLiteralSystemId();
  
  String getBaseSystemId();
  
  String getExpandedSystemId();
  
  int getLineNumber();
  
  int getColumnNumber();
  
  int getCharacterOffset();
  
  String getEncoding();
  
  String getXMLVersion();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/XMLLocator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */