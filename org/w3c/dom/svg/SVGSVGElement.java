package org.w3c.dom.svg;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.DocumentCSS;
import org.w3c.dom.css.ViewCSS;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.EventTarget;

public interface SVGSVGElement extends DocumentCSS, ViewCSS, DocumentEvent, EventTarget, SVGElement, SVGExternalResourcesRequired, SVGFitToViewBox, SVGLangSpace, SVGLocatable, SVGStylable, SVGTests, SVGZoomAndPan {
  SVGAnimatedLength getX();
  
  SVGAnimatedLength getY();
  
  SVGAnimatedLength getWidth();
  
  SVGAnimatedLength getHeight();
  
  String getContentScriptType();
  
  void setContentScriptType(String paramString) throws DOMException;
  
  String getContentStyleType();
  
  void setContentStyleType(String paramString) throws DOMException;
  
  SVGRect getViewport();
  
  float getPixelUnitToMillimeterX();
  
  float getPixelUnitToMillimeterY();
  
  float getScreenPixelToMillimeterX();
  
  float getScreenPixelToMillimeterY();
  
  boolean getUseCurrentView();
  
  void setUseCurrentView(boolean paramBoolean) throws DOMException;
  
  SVGViewSpec getCurrentView();
  
  float getCurrentScale();
  
  void setCurrentScale(float paramFloat) throws DOMException;
  
  SVGPoint getCurrentTranslate();
  
  int suspendRedraw(int paramInt);
  
  void unsuspendRedraw(int paramInt) throws DOMException;
  
  void unsuspendRedrawAll();
  
  void forceRedraw();
  
  void pauseAnimations();
  
  void unpauseAnimations();
  
  boolean animationsPaused();
  
  float getCurrentTime();
  
  void setCurrentTime(float paramFloat);
  
  NodeList getIntersectionList(SVGRect paramSVGRect, SVGElement paramSVGElement);
  
  NodeList getEnclosureList(SVGRect paramSVGRect, SVGElement paramSVGElement);
  
  boolean checkIntersection(SVGElement paramSVGElement, SVGRect paramSVGRect);
  
  boolean checkEnclosure(SVGElement paramSVGElement, SVGRect paramSVGRect);
  
  void deselectAll();
  
  SVGNumber createSVGNumber();
  
  SVGLength createSVGLength();
  
  SVGAngle createSVGAngle();
  
  SVGPoint createSVGPoint();
  
  SVGMatrix createSVGMatrix();
  
  SVGRect createSVGRect();
  
  SVGTransform createSVGTransform();
  
  SVGTransform createSVGTransformFromMatrix(SVGMatrix paramSVGMatrix);
  
  Element getElementById(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGSVGElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */