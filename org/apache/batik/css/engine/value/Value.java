package org.apache.batik.css.engine.value;

import org.w3c.dom.DOMException;

public interface Value {
  String getCssText();
  
  short getCssValueType();
  
  short getPrimitiveType();
  
  float getFloatValue() throws DOMException;
  
  String getStringValue() throws DOMException;
  
  Value getRed() throws DOMException;
  
  Value getGreen() throws DOMException;
  
  Value getBlue() throws DOMException;
  
  int getLength() throws DOMException;
  
  Value item(int paramInt) throws DOMException;
  
  Value getTop() throws DOMException;
  
  Value getRight() throws DOMException;
  
  Value getBottom() throws DOMException;
  
  Value getLeft() throws DOMException;
  
  String getIdentifier() throws DOMException;
  
  String getListStyle() throws DOMException;
  
  String getSeparator() throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/Value.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */