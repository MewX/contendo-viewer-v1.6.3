package org.apache.batik.css.engine.value;

import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

public interface ValueManager {
  String getPropertyName();
  
  boolean isInheritedProperty();
  
  boolean isAnimatableProperty();
  
  boolean isAdditiveProperty();
  
  int getPropertyType();
  
  Value getDefaultValue();
  
  Value createValue(LexicalUnit paramLexicalUnit, CSSEngine paramCSSEngine) throws DOMException;
  
  Value createFloatValue(short paramShort, float paramFloat) throws DOMException;
  
  Value createStringValue(short paramShort, String paramString, CSSEngine paramCSSEngine) throws DOMException;
  
  Value computeValue(CSSStylableElement paramCSSStylableElement, String paramString, CSSEngine paramCSSEngine, int paramInt, StyleMap paramStyleMap, Value paramValue);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/ValueManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */