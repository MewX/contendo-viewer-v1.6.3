package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLPiecewiseElement extends MathMLContentElement {
  MathMLNodeList getPieces();
  
  MathMLContentElement getOtherwise();
  
  void setOtherwise(MathMLContentElement paramMathMLContentElement);
  
  MathMLCaseElement getCase(int paramInt);
  
  MathMLCaseElement setCase(int paramInt, MathMLCaseElement paramMathMLCaseElement) throws DOMException;
  
  void deleteCase(int paramInt) throws DOMException;
  
  MathMLCaseElement removeCase(int paramInt) throws DOMException;
  
  MathMLCaseElement insertCase(int paramInt, MathMLCaseElement paramMathMLCaseElement) throws DOMException;
  
  MathMLContentElement getCaseValue(int paramInt) throws DOMException;
  
  MathMLContentElement setCaseValue(int paramInt, MathMLContentElement paramMathMLContentElement) throws DOMException;
  
  MathMLContentElement getCaseCondition(int paramInt) throws DOMException;
  
  MathMLContentElement setCaseCondition(int paramInt, MathMLContentElement paramMathMLContentElement) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLPiecewiseElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */