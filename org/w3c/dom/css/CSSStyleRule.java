package org.w3c.dom.css;

import org.w3c.dom.DOMException;

public interface CSSStyleRule extends CSSRule {
  String getSelectorText();
  
  void setSelectorText(String paramString) throws DOMException;
  
  CSSStyleDeclaration getStyle();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/css/CSSStyleRule.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */