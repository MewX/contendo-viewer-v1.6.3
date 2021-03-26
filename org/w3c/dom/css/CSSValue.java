package org.w3c.dom.css;

import org.w3c.dom.DOMException;

public interface CSSValue {
  public static final short CSS_INHERIT = 0;
  
  public static final short CSS_PRIMITIVE_VALUE = 1;
  
  public static final short CSS_VALUE_LIST = 2;
  
  public static final short CSS_CUSTOM = 3;
  
  String getCssText();
  
  void setCssText(String paramString) throws DOMException;
  
  short getCssValueType();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/css/CSSValue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */