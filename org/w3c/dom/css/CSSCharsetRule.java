package org.w3c.dom.css;

import org.w3c.dom.DOMException;

public interface CSSCharsetRule extends CSSRule {
  String getEncoding();
  
  void setEncoding(String paramString) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/css/CSSCharsetRule.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */