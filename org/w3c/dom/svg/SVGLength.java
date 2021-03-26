package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGLength {
  public static final short SVG_LENGTHTYPE_UNKNOWN = 0;
  
  public static final short SVG_LENGTHTYPE_NUMBER = 1;
  
  public static final short SVG_LENGTHTYPE_PERCENTAGE = 2;
  
  public static final short SVG_LENGTHTYPE_EMS = 3;
  
  public static final short SVG_LENGTHTYPE_EXS = 4;
  
  public static final short SVG_LENGTHTYPE_PX = 5;
  
  public static final short SVG_LENGTHTYPE_CM = 6;
  
  public static final short SVG_LENGTHTYPE_MM = 7;
  
  public static final short SVG_LENGTHTYPE_IN = 8;
  
  public static final short SVG_LENGTHTYPE_PT = 9;
  
  public static final short SVG_LENGTHTYPE_PC = 10;
  
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGLength.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */