package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLMatrixrowElement extends MathMLContentElement {
  int getNEntries();
  
  MathMLContentElement getEntry(int paramInt) throws DOMException;
  
  MathMLContentElement insertEntry(MathMLContentElement paramMathMLContentElement, int paramInt) throws DOMException;
  
  MathMLContentElement setEntry(MathMLContentElement paramMathMLContentElement, int paramInt) throws DOMException;
  
  void deleteEntry(int paramInt) throws DOMException;
  
  MathMLContentElement removeEntry(int paramInt) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLMatrixrowElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */