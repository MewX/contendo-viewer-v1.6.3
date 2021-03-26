package org.w3c.dom.mathml;

public interface MathMLScriptElement extends MathMLPresentationElement {
  String getSubscriptshift();
  
  void setSubscriptshift(String paramString);
  
  String getSuperscriptshift();
  
  void setSuperscriptshift(String paramString);
  
  MathMLElement getBase();
  
  void setBase(MathMLElement paramMathMLElement);
  
  MathMLElement getSubscript();
  
  void setSubscript(MathMLElement paramMathMLElement);
  
  MathMLElement getSuperscript();
  
  void setSuperscript(MathMLElement paramMathMLElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLScriptElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */