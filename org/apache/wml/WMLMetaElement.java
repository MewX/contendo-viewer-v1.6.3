package org.apache.wml;

public interface WMLMetaElement extends WMLElement {
  void setName(String paramString);
  
  String getName();
  
  void setHttpEquiv(String paramString);
  
  String getHttpEquiv();
  
  void setForua(boolean paramBoolean);
  
  boolean getForua();
  
  void setScheme(String paramString);
  
  String getScheme();
  
  void setContent(String paramString);
  
  String getContent();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/WMLMetaElement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */