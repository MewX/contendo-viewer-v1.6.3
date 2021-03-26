package org.apache.xerces.xs;

public interface XSNotationDeclaration extends XSObject {
  String getSystemId();
  
  String getPublicId();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSNotationDeclaration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */