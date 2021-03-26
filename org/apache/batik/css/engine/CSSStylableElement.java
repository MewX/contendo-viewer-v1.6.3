package org.apache.batik.css.engine;

import org.apache.batik.util.ParsedURL;
import org.w3c.dom.Element;

public interface CSSStylableElement extends Element {
  StyleMap getComputedStyleMap(String paramString);
  
  void setComputedStyleMap(String paramString, StyleMap paramStyleMap);
  
  String getXMLId();
  
  String getCSSClass();
  
  ParsedURL getCSSBase();
  
  boolean isPseudoInstanceOf(String paramString);
  
  StyleDeclarationProvider getOverrideStyleDeclarationProvider();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/CSSStylableElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */