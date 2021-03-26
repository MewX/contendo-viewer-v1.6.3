package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGMatrix {
  float getA();
  
  void setA(float paramFloat) throws DOMException;
  
  float getB();
  
  void setB(float paramFloat) throws DOMException;
  
  float getC();
  
  void setC(float paramFloat) throws DOMException;
  
  float getD();
  
  void setD(float paramFloat) throws DOMException;
  
  float getE();
  
  void setE(float paramFloat) throws DOMException;
  
  float getF();
  
  void setF(float paramFloat) throws DOMException;
  
  SVGMatrix multiply(SVGMatrix paramSVGMatrix);
  
  SVGMatrix inverse() throws SVGException;
  
  SVGMatrix translate(float paramFloat1, float paramFloat2);
  
  SVGMatrix scale(float paramFloat);
  
  SVGMatrix scaleNonUniform(float paramFloat1, float paramFloat2);
  
  SVGMatrix rotate(float paramFloat);
  
  SVGMatrix rotateFromVector(float paramFloat1, float paramFloat2) throws SVGException;
  
  SVGMatrix flipX();
  
  SVGMatrix flipY();
  
  SVGMatrix skewX(float paramFloat);
  
  SVGMatrix skewY(float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGMatrix.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */