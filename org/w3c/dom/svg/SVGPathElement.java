package org.w3c.dom.svg;

import org.w3c.dom.events.EventTarget;

public interface SVGPathElement extends EventTarget, SVGAnimatedPathData, SVGElement, SVGExternalResourcesRequired, SVGLangSpace, SVGStylable, SVGTests, SVGTransformable {
  SVGAnimatedNumber getPathLength();
  
  float getTotalLength();
  
  SVGPoint getPointAtLength(float paramFloat);
  
  int getPathSegAtLength(float paramFloat);
  
  SVGPathSegClosePath createSVGPathSegClosePath();
  
  SVGPathSegMovetoAbs createSVGPathSegMovetoAbs(float paramFloat1, float paramFloat2);
  
  SVGPathSegMovetoRel createSVGPathSegMovetoRel(float paramFloat1, float paramFloat2);
  
  SVGPathSegLinetoAbs createSVGPathSegLinetoAbs(float paramFloat1, float paramFloat2);
  
  SVGPathSegLinetoRel createSVGPathSegLinetoRel(float paramFloat1, float paramFloat2);
  
  SVGPathSegCurvetoCubicAbs createSVGPathSegCurvetoCubicAbs(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
  
  SVGPathSegCurvetoCubicRel createSVGPathSegCurvetoCubicRel(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
  
  SVGPathSegCurvetoQuadraticAbs createSVGPathSegCurvetoQuadraticAbs(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  SVGPathSegCurvetoQuadraticRel createSVGPathSegCurvetoQuadraticRel(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  SVGPathSegArcAbs createSVGPathSegArcAbs(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, boolean paramBoolean1, boolean paramBoolean2);
  
  SVGPathSegArcRel createSVGPathSegArcRel(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, boolean paramBoolean1, boolean paramBoolean2);
  
  SVGPathSegLinetoHorizontalAbs createSVGPathSegLinetoHorizontalAbs(float paramFloat);
  
  SVGPathSegLinetoHorizontalRel createSVGPathSegLinetoHorizontalRel(float paramFloat);
  
  SVGPathSegLinetoVerticalAbs createSVGPathSegLinetoVerticalAbs(float paramFloat);
  
  SVGPathSegLinetoVerticalRel createSVGPathSegLinetoVerticalRel(float paramFloat);
  
  SVGPathSegCurvetoCubicSmoothAbs createSVGPathSegCurvetoCubicSmoothAbs(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  SVGPathSegCurvetoCubicSmoothRel createSVGPathSegCurvetoCubicSmoothRel(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  SVGPathSegCurvetoQuadraticSmoothAbs createSVGPathSegCurvetoQuadraticSmoothAbs(float paramFloat1, float paramFloat2);
  
  SVGPathSegCurvetoQuadraticSmoothRel createSVGPathSegCurvetoQuadraticSmoothRel(float paramFloat1, float paramFloat2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGPathElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */