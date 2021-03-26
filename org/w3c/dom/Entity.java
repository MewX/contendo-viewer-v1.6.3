package org.w3c.dom;

public interface Entity extends Node {
  String getPublicId();
  
  String getSystemId();
  
  String getNotationName();
  
  String getInputEncoding();
  
  String getXmlEncoding();
  
  String getXmlVersion();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/Entity.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */