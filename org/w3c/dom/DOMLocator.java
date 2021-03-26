package org.w3c.dom;

public interface DOMLocator {
  int getLineNumber();
  
  int getColumnNumber();
  
  int getByteOffset();
  
  int getUtf16Offset();
  
  Node getRelatedNode();
  
  String getUri();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/DOMLocator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */