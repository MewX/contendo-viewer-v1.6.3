package org.apache.batik.css.engine.value.svg;

import org.apache.batik.css.engine.value.Value;
import org.w3c.dom.DOMException;

public interface SVGValue extends Value {
  short getPaintType() throws DOMException;
  
  String getUri() throws DOMException;
  
  short getColorType() throws DOMException;
  
  String getColorProfile() throws DOMException;
  
  int getNumberOfColors() throws DOMException;
  
  float getColor(int paramInt) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/SVGValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */