package org.w3c.dom.css;

import org.w3c.dom.DOMException;
import org.w3c.dom.stylesheets.StyleSheet;

public interface CSSStyleSheet extends StyleSheet {
  CSSRule getOwnerRule();
  
  CSSRuleList getCssRules();
  
  int insertRule(String paramString, int paramInt) throws DOMException;
  
  void deleteRule(int paramInt) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/css/CSSStyleSheet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */