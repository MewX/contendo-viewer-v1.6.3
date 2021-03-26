package org.w3c.dom.svg;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.EventTarget;

public interface SVGTextContentElement extends EventTarget, SVGElement, SVGExternalResourcesRequired, SVGLangSpace, SVGStylable, SVGTests {
  public static final short LENGTHADJUST_UNKNOWN = 0;
  
  public static final short LENGTHADJUST_SPACING = 1;
  
  public static final short LENGTHADJUST_SPACINGANDGLYPHS = 2;
  
  SVGAnimatedLength getTextLength();
  
  SVGAnimatedEnumeration getLengthAdjust();
  
  int getNumberOfChars();
  
  float getComputedTextLength();
  
  float getSubStringLength(int paramInt1, int paramInt2) throws DOMException;
  
  SVGPoint getStartPositionOfChar(int paramInt) throws DOMException;
  
  SVGPoint getEndPositionOfChar(int paramInt) throws DOMException;
  
  SVGRect getExtentOfChar(int paramInt) throws DOMException;
  
  float getRotationOfChar(int paramInt) throws DOMException;
  
  int getCharNumAtPosition(SVGPoint paramSVGPoint);
  
  void selectSubString(int paramInt1, int paramInt2) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGTextContentElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */