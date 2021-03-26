package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLVectorElement extends MathMLContentElement {
  int getNcomponents();
  
  MathMLContentElement getComponent(int paramInt);
  
  MathMLContentElement insertComponent(MathMLContentElement paramMathMLContentElement, int paramInt) throws DOMException;
  
  MathMLContentElement setComponent(MathMLContentElement paramMathMLContentElement, int paramInt) throws DOMException;
  
  void deleteComponent(int paramInt) throws DOMException;
  
  MathMLContentElement removeComponent(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLVectorElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */