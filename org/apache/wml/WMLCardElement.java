package org.apache.wml;

public interface WMLCardElement extends WMLElement {
  void setOnEnterBackward(String paramString);
  
  String getOnEnterBackward();
  
  void setOnEnterForward(String paramString);
  
  String getOnEnterForward();
  
  void setOnTimer(String paramString);
  
  String getOnTimer();
  
  void setTitle(String paramString);
  
  String getTitle();
  
  void setNewContext(boolean paramBoolean);
  
  boolean getNewContext();
  
  void setOrdered(boolean paramBoolean);
  
  boolean getOrdered();
  
  void setXmlLang(String paramString);
  
  String getXmlLang();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/WMLCardElement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */