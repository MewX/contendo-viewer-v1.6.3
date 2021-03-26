package org.w3c.dom.svg;

public interface SVGTransform {
  public static final short SVG_TRANSFORM_UNKNOWN = 0;
  
  public static final short SVG_TRANSFORM_MATRIX = 1;
  
  public static final short SVG_TRANSFORM_TRANSLATE = 2;
  
  public static final short SVG_TRANSFORM_SCALE = 3;
  
  public static final short SVG_TRANSFORM_ROTATE = 4;
  
  public static final short SVG_TRANSFORM_SKEWX = 5;
  
  public static final short SVG_TRANSFORM_SKEWY = 6;
  
  short getType();
  
  SVGMatrix getMatrix();
  
  float getAngle();
  
  void setMatrix(SVGMatrix paramSVGMatrix);
  
  void setTranslate(float paramFloat1, float paramFloat2);
  
  void setScale(float paramFloat1, float paramFloat2);
  
  void setRotate(float paramFloat1, float paramFloat2, float paramFloat3);
  
  void setSkewX(float paramFloat);
  
  void setSkewY(float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGTransform.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */