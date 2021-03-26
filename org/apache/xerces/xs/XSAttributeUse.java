package org.apache.xerces.xs;

public interface XSAttributeUse extends XSObject {
  boolean getRequired();
  
  XSAttributeDeclaration getAttrDeclaration();
  
  short getConstraintType();
  
  String getConstraintValue();
  
  Object getActualVC() throws XSException;
  
  short getActualVCType() throws XSException;
  
  ShortList getItemValueTypes() throws XSException;
  
  XSValue getValueConstraintValue();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSAttributeUse.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */