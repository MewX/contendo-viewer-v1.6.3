package org.apache.xerces.dom3.as;

import org.w3c.dom.DOMException;

public interface DocumentAS {
  ASModel getActiveASModel();
  
  void setActiveASModel(ASModel paramASModel);
  
  ASObjectList getBoundASModels();
  
  void setBoundASModels(ASObjectList paramASObjectList);
  
  ASModel getInternalAS();
  
  void setInternalAS(ASModel paramASModel);
  
  void addAS(ASModel paramASModel);
  
  void removeAS(ASModel paramASModel);
  
  ASElementDeclaration getElementDeclaration() throws DOMException;
  
  void validate() throws DOMASException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom3/as/DocumentAS.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */