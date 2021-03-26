package org.w3c.dom.mathml;

public interface MathMLFractionElement extends MathMLPresentationElement {
  String getLinethickness();
  
  void setLinethickness(String paramString);
  
  String getNumalign();
  
  void setNumalign(String paramString);
  
  String getDenomalign();
  
  void setDenomalign(String paramString);
  
  String getBevelled();
  
  void setBevelled(String paramString);
  
  MathMLElement getNumerator();
  
  void setNumerator(MathMLElement paramMathMLElement);
  
  MathMLElement getDenominator();
  
  void setDenominator(MathMLElement paramMathMLElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLFractionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */