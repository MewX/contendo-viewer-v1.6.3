package org.apache.xerces.xs;

public interface XSAttributeGroupDefinition extends XSObject {
  XSObjectList getAttributeUses();
  
  XSWildcard getAttributeWildcard();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSAttributeGroupDefinition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */