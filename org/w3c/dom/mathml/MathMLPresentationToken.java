package org.w3c.dom.mathml;

public interface MathMLPresentationToken extends MathMLPresentationElement {
  String getMathvariant();
  
  void setMathvariant(String paramString);
  
  String getMathsize();
  
  void setMathsize(String paramString);
  
  String getMathcolor();
  
  void setMathcolor(String paramString);
  
  String getMathbackground();
  
  void setMathbackground(String paramString);
  
  MathMLNodeList getContents();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLPresentationToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */