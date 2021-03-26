package org.apache.xerces.xs;

public interface XSAttributeDeclaration extends XSObject {
  XSSimpleTypeDefinition getTypeDefinition();
  
  short getScope();
  
  XSComplexTypeDefinition getEnclosingCTDefinition();
  
  short getConstraintType();
  
  String getConstraintValue();
  
  Object getActualVC() throws XSException;
  
  short getActualVCType() throws XSException;
  
  ShortList getItemValueTypes() throws XSException;
  
  XSValue getValueConstraintValue();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSAttributeDeclaration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */