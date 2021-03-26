package org.w3c.dom;

public interface DocumentType extends Node {
  String getName();
  
  NamedNodeMap getEntities();
  
  NamedNodeMap getNotations();
  
  String getPublicId();
  
  String getSystemId();
  
  String getInternalSubset();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/DocumentType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */