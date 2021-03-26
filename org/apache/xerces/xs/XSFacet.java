package org.apache.xerces.xs;

public interface XSFacet extends XSObject {
  short getFacetKind();
  
  String getLexicalFacetValue();
  
  int getIntFacetValue();
  
  Object getActualFacetValue();
  
  boolean getFixed();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSFacet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */