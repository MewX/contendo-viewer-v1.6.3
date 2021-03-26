package org.w3c.dom.mathml;

public interface MathMLUnderOverElement extends MathMLPresentationElement {
  String getAccentunder();
  
  void setAccentunder(String paramString);
  
  String getAccent();
  
  void setAccent(String paramString);
  
  MathMLElement getBase();
  
  void setBase(MathMLElement paramMathMLElement);
  
  MathMLElement getUnderscript();
  
  void setUnderscript(MathMLElement paramMathMLElement);
  
  MathMLElement getOverscript();
  
  void setOverscript(MathMLElement paramMathMLElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLUnderOverElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */