package org.apache.wml;

public interface WMLDoElement extends WMLElement {
  void setOptional(String paramString);
  
  String getOptional();
  
  void setLabel(String paramString);
  
  String getLabel();
  
  void setType(String paramString);
  
  String getType();
  
  void setName(String paramString);
  
  String getName();
  
  void setXmlLang(String paramString);
  
  String getXmlLang();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/WMLDoElement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */