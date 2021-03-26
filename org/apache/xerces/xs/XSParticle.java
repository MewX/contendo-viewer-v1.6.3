package org.apache.xerces.xs;

public interface XSParticle extends XSObject {
  int getMinOccurs();
  
  int getMaxOccurs();
  
  boolean getMaxOccursUnbounded();
  
  XSTerm getTerm();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSParticle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */