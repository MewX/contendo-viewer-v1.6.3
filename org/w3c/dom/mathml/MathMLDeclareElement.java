package org.w3c.dom.mathml;

public interface MathMLDeclareElement extends MathMLContentElement {
  String getType();
  
  void setType(String paramString);
  
  int getNargs();
  
  void setNargs(int paramInt);
  
  String getOccurrence();
  
  void setOccurrence(String paramString);
  
  String getDefinitionURL();
  
  void setDefinitionURL(String paramString);
  
  String getEncoding();
  
  void setEncoding(String paramString);
  
  MathMLCiElement getIdentifier();
  
  void setIdentifier(MathMLCiElement paramMathMLCiElement);
  
  MathMLElement getConstructor();
  
  void setConstructor(MathMLElement paramMathMLElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLDeclareElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */