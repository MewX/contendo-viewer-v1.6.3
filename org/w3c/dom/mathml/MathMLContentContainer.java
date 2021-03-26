package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLContentContainer extends MathMLContainer, MathMLContentElement {
  int getNBoundVariables();
  
  MathMLConditionElement getCondition();
  
  void setCondition(MathMLConditionElement paramMathMLConditionElement);
  
  MathMLElement getOpDegree();
  
  void setOpDegree(MathMLElement paramMathMLElement);
  
  MathMLElement getDomainOfApplication();
  
  void setDomainOfApplication(MathMLElement paramMathMLElement);
  
  MathMLElement getMomentAbout();
  
  void setMomentAbout(MathMLElement paramMathMLElement);
  
  MathMLBvarElement getBoundVariable(int paramInt);
  
  MathMLBvarElement insertBoundVariable(MathMLBvarElement paramMathMLBvarElement, int paramInt) throws DOMException;
  
  MathMLBvarElement setBoundVariable(MathMLBvarElement paramMathMLBvarElement, int paramInt) throws DOMException;
  
  void deleteBoundVariable(int paramInt);
  
  MathMLBvarElement removeBoundVariable(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLContentContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */