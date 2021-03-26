package org.apache.xerces.xs;

public interface XSWildcard extends XSTerm {
  public static final short NSCONSTRAINT_ANY = 1;
  
  public static final short NSCONSTRAINT_NOT = 2;
  
  public static final short NSCONSTRAINT_LIST = 3;
  
  public static final short PC_STRICT = 1;
  
  public static final short PC_SKIP = 2;
  
  public static final short PC_LAX = 3;
  
  short getConstraintType();
  
  StringList getNsConstraintList();
  
  short getProcessContents();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSWildcard.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */