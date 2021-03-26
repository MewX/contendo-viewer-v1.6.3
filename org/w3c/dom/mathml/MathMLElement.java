package org.w3c.dom.mathml;

import org.w3c.dom.Element;

public interface MathMLElement extends Element {
  String getClassName();
  
  void setClassName(String paramString);
  
  String getMathElementStyle();
  
  void setMathElementStyle(String paramString);
  
  String getId();
  
  void setId(String paramString);
  
  String getXref();
  
  void setXref(String paramString);
  
  String getHref();
  
  void setHref(String paramString);
  
  MathMLMathElement getOwnerMathElement();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */