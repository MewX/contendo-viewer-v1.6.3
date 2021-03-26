package org.w3c.dom;

public interface ProcessingInstruction extends Node {
  String getTarget();
  
  String getData();
  
  void setData(String paramString) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/ProcessingInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */