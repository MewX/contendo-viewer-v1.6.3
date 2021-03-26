package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGPreserveAspectRatio {
  public static final short SVG_PRESERVEASPECTRATIO_UNKNOWN = 0;
  
  public static final short SVG_PRESERVEASPECTRATIO_NONE = 1;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMINYMIN = 2;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMIDYMIN = 3;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMAXYMIN = 4;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMINYMID = 5;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMIDYMID = 6;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMAXYMID = 7;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMINYMAX = 8;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMIDYMAX = 9;
  
  public static final short SVG_PRESERVEASPECTRATIO_XMAXYMAX = 10;
  
  public static final short SVG_MEETORSLICE_UNKNOWN = 0;
  
  public static final short SVG_MEETORSLICE_MEET = 1;
  
  public static final short SVG_MEETORSLICE_SLICE = 2;
  
  short getAlign();
  
  void setAlign(short paramShort) throws DOMException;
  
  short getMeetOrSlice();
  
  void setMeetOrSlice(short paramShort) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPreserveAspectRatio.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */