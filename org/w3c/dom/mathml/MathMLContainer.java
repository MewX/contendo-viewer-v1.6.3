package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLContainer {
  int getNArguments();
  
  MathMLNodeList getArguments();
  
  MathMLNodeList getDeclarations();
  
  MathMLElement getArgument(int paramInt) throws DOMException;
  
  MathMLElement setArgument(MathMLElement paramMathMLElement, int paramInt) throws DOMException;
  
  MathMLElement insertArgument(MathMLElement paramMathMLElement, int paramInt) throws DOMException;
  
  void deleteArgument(int paramInt) throws DOMException;
  
  MathMLElement removeArgument(int paramInt) throws DOMException;
  
  MathMLDeclareElement getDeclaration(int paramInt) throws DOMException;
  
  MathMLDeclareElement setDeclaration(MathMLDeclareElement paramMathMLDeclareElement, int paramInt) throws DOMException;
  
  MathMLDeclareElement insertDeclaration(MathMLDeclareElement paramMathMLDeclareElement, int paramInt) throws DOMException;
  
  MathMLDeclareElement removeDeclaration(int paramInt) throws DOMException;
  
  void deleteDeclaration(int paramInt) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */