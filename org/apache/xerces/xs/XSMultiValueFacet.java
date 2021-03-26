package org.apache.xerces.xs;

import org.apache.xerces.xs.datatypes.ObjectList;

public interface XSMultiValueFacet extends XSObject {
  short getFacetKind();
  
  StringList getLexicalFacetValues();
  
  ObjectList getEnumerationValues();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSMultiValueFacet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */