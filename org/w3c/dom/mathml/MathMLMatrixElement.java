package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLMatrixElement extends MathMLContentElement {
  int getNrows();
  
  int getNcols();
  
  MathMLNodeList getRows();
  
  MathMLMatrixrowElement getRow(int paramInt) throws DOMException;
  
  MathMLMatrixrowElement insertRow(MathMLMatrixrowElement paramMathMLMatrixrowElement, int paramInt) throws DOMException;
  
  MathMLMatrixrowElement setRow(MathMLMatrixrowElement paramMathMLMatrixrowElement, int paramInt) throws DOMException;
  
  void deleteRow(int paramInt) throws DOMException;
  
  MathMLMatrixrowElement removeRow(int paramInt) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLMatrixElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */