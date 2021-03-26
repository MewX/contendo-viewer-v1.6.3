package org.w3c.dom.mathml;

public interface MathMLMathElement extends MathMLContainer, MathMLElement {
  String getMacros();
  
  void setMacros(String paramString);
  
  String getDisplay();
  
  void setDisplay(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLMathElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */