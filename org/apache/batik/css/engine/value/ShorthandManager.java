package org.apache.batik.css.engine.value;

import org.apache.batik.css.engine.CSSEngine;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

public interface ShorthandManager {
  String getPropertyName();
  
  boolean isAnimatableProperty();
  
  boolean isAdditiveProperty();
  
  void setValues(CSSEngine paramCSSEngine, PropertyHandler paramPropertyHandler, LexicalUnit paramLexicalUnit, boolean paramBoolean) throws DOMException;
  
  public static interface PropertyHandler {
    void property(String param1String, LexicalUnit param1LexicalUnit, boolean param1Boolean);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/ShorthandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */