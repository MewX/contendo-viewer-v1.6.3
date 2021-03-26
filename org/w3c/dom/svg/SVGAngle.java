package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGAngle {
  public static final short SVG_ANGLETYPE_UNKNOWN = 0;
  
  public static final short SVG_ANGLETYPE_UNSPECIFIED = 1;
  
  public static final short SVG_ANGLETYPE_DEG = 2;
  
  public static final short SVG_ANGLETYPE_RAD = 3;
  
  public static final short SVG_ANGLETYPE_GRAD = 4;
  
  short getUnitType();
  
  float getValue();
  
  void setValue(float paramFloat) throws DOMException;
  
  float getValueInSpecifiedUnits();
  
  void setValueInSpecifiedUnits(float paramFloat) throws DOMException;
  
  String getValueAsString();
  
  void setValueAsString(String paramString) throws DOMException;
  
  void newValueSpecifiedUnits(short paramShort, float paramFloat);
  
  void convertToSpecifiedUnits(short paramShort);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGAngle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */