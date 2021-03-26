package org.w3c.dom.mathml;

public interface MathMLOperatorElement extends MathMLPresentationToken {
  String getForm();
  
  void setForm(String paramString);
  
  String getFence();
  
  void setFence(String paramString);
  
  String getSeparator();
  
  void setSeparator(String paramString);
  
  String getLspace();
  
  void setLspace(String paramString);
  
  String getRspace();
  
  void setRspace(String paramString);
  
  String getStretchy();
  
  void setStretchy(String paramString);
  
  String getSymmetric();
  
  void setSymmetric(String paramString);
  
  String getMaxsize();
  
  void setMaxsize(String paramString);
  
  String getMinsize();
  
  void setMinsize(String paramString);
  
  String getLargeop();
  
  void setLargeop(String paramString);
  
  String getMovablelimits();
  
  void setMovablelimits(String paramString);
  
  String getAccent();
  
  void setAccent(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLOperatorElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */