package org.w3c.dom.svg;

public interface SVGPaint extends SVGColor {
  public static final short SVG_PAINTTYPE_UNKNOWN = 0;
  
  public static final short SVG_PAINTTYPE_RGBCOLOR = 1;
  
  public static final short SVG_PAINTTYPE_RGBCOLOR_ICCCOLOR = 2;
  
  public static final short SVG_PAINTTYPE_NONE = 101;
  
  public static final short SVG_PAINTTYPE_CURRENTCOLOR = 102;
  
  public static final short SVG_PAINTTYPE_URI_NONE = 103;
  
  public static final short SVG_PAINTTYPE_URI_CURRENTCOLOR = 104;
  
  public static final short SVG_PAINTTYPE_URI_RGBCOLOR = 105;
  
  public static final short SVG_PAINTTYPE_URI_RGBCOLOR_ICCCOLOR = 106;
  
  public static final short SVG_PAINTTYPE_URI = 107;
  
  short getPaintType();
  
  String getUri();
  
  void setUri(String paramString);
  
  void setPaint(short paramShort, String paramString1, String paramString2, String paramString3) throws SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPaint.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */