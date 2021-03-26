package org.apache.xerces.xni;

public interface XMLResourceIdentifier {
  void setPublicId(String paramString);
  
  String getPublicId();
  
  void setExpandedSystemId(String paramString);
  
  String getExpandedSystemId();
  
  void setLiteralSystemId(String paramString);
  
  String getLiteralSystemId();
  
  void setBaseSystemId(String paramString);
  
  String getBaseSystemId();
  
  void setNamespace(String paramString);
  
  String getNamespace();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/XMLResourceIdentifier.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */