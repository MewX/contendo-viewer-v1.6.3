package org.apache.wml;

import org.w3c.dom.Element;

public interface WMLElement extends Element {
  void setId(String paramString);
  
  String getId();
  
  void setClassName(String paramString);
  
  String getClassName();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/WMLElement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */