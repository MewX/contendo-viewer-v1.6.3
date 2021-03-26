package org.w3c.dom.mathml;

public interface MathMLApplyElement extends MathMLContentContainer {
  MathMLElement getOperator();
  
  void setOperator(MathMLElement paramMathMLElement);
  
  MathMLElement getLowLimit();
  
  void setLowLimit(MathMLElement paramMathMLElement);
  
  MathMLElement getUpLimit();
  
  void setUpLimit(MathMLElement paramMathMLElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLApplyElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */