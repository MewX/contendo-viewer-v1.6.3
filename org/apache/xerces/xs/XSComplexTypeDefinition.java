package org.apache.xerces.xs;

public interface XSComplexTypeDefinition extends XSTypeDefinition {
  public static final short CONTENTTYPE_EMPTY = 0;
  
  public static final short CONTENTTYPE_SIMPLE = 1;
  
  public static final short CONTENTTYPE_ELEMENT = 2;
  
  public static final short CONTENTTYPE_MIXED = 3;
  
  short getDerivationMethod();
  
  boolean getAbstract();
  
  XSObjectList getAttributeUses();
  
  XSWildcard getAttributeWildcard();
  
  short getContentType();
  
  XSSimpleTypeDefinition getSimpleType();
  
  XSParticle getParticle();
  
  boolean isProhibitedSubstitution(short paramShort);
  
  short getProhibitedSubstitutions();
  
  XSObjectList getAnnotations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSComplexTypeDefinition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */