package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLMultiScriptsElement extends MathMLPresentationElement {
  String getSubscriptshift();
  
  void setSubscriptshift(String paramString);
  
  String getSuperscriptshift();
  
  void setSuperscriptshift(String paramString);
  
  MathMLElement getBase();
  
  void setBase(MathMLElement paramMathMLElement);
  
  MathMLNodeList getPrescripts();
  
  MathMLNodeList getScripts();
  
  int getNumprescriptcolumns();
  
  int getNumscriptcolumns();
  
  MathMLElement getPreSubScript(int paramInt);
  
  MathMLElement getSubScript(int paramInt);
  
  MathMLElement getPreSuperScript(int paramInt);
  
  MathMLElement getSuperScript(int paramInt);
  
  MathMLElement insertPreSubScriptBefore(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
  
  MathMLElement setPreSubScriptAt(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
  
  MathMLElement insertSubScriptBefore(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
  
  MathMLElement setSubScriptAt(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
  
  MathMLElement insertPreSuperScriptBefore(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
  
  MathMLElement setPreSuperScriptAt(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
  
  MathMLElement insertSuperScriptBefore(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
  
  MathMLElement setSuperScriptAt(int paramInt, MathMLElement paramMathMLElement) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLMultiScriptsElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */