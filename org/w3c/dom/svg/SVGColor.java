package org.w3c.dom.svg;

import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.RGBColor;

public interface SVGColor extends CSSValue {
  public static final short SVG_COLORTYPE_UNKNOWN = 0;
  
  public static final short SVG_COLORTYPE_RGBCOLOR = 1;
  
  public static final short SVG_COLORTYPE_RGBCOLOR_ICCCOLOR = 2;
  
  public static final short SVG_COLORTYPE_CURRENTCOLOR = 3;
  
  short getColorType();
  
  RGBColor getRGBColor();
  
  SVGICCColor getICCColor();
  
  void setRGBColor(String paramString) throws SVGException;
  
  void setRGBColorICCColor(String paramString1, String paramString2) throws SVGException;
  
  void setColor(short paramShort, String paramString1, String paramString2) throws SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGColor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */