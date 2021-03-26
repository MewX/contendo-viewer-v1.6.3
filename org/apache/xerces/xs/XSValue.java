package org.apache.xerces.xs;

public interface XSValue {
  String getNormalizedValue();
  
  Object getActualValue();
  
  XSSimpleTypeDefinition getTypeDefinition();
  
  XSSimpleTypeDefinition getMemberTypeDefinition();
  
  XSObjectList getMemberTypeDefinitions();
  
  short getActualValueType();
  
  ShortList getListValueTypes();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSValue.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */