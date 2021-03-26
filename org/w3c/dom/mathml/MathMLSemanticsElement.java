package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLSemanticsElement extends MathMLElement {
  MathMLElement getBody();
  
  void setBody(MathMLElement paramMathMLElement);
  
  int getNAnnotations();
  
  MathMLElement getAnnotation(int paramInt);
  
  MathMLElement insertAnnotation(MathMLElement paramMathMLElement, int paramInt) throws DOMException;
  
  MathMLElement setAnnotation(MathMLElement paramMathMLElement, int paramInt) throws DOMException;
  
  void deleteAnnotation(int paramInt);
  
  MathMLElement removeAnnotation(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLSemanticsElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */