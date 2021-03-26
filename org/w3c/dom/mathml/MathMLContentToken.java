package org.w3c.dom.mathml;

import org.w3c.dom.Node;

public interface MathMLContentToken extends MathMLContentElement {
  MathMLNodeList getArguments();
  
  String getDefinitionURL();
  
  void setDefinitionURL(String paramString);
  
  String getEncoding();
  
  void setEncoding(String paramString);
  
  Node getArgument(int paramInt);
  
  Node insertArgument(Node paramNode, int paramInt);
  
  Node setArgument(Node paramNode, int paramInt);
  
  void deleteArgument(int paramInt);
  
  Node removeArgument(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLContentToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */