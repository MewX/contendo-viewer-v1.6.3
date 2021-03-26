package org.w3c.dom.mathml;

public interface MathMLIntervalElement extends MathMLContentElement {
  String getClosure();
  
  void setClosure(String paramString);
  
  MathMLContentElement getStart();
  
  void setStart(MathMLContentElement paramMathMLContentElement);
  
  MathMLContentElement getEnd();
  
  void setEnd(MathMLContentElement paramMathMLContentElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLIntervalElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */