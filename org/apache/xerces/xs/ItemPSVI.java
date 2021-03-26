package org.apache.xerces.xs;

public interface ItemPSVI {
  public static final short VALIDITY_NOTKNOWN = 0;
  
  public static final short VALIDITY_INVALID = 1;
  
  public static final short VALIDITY_VALID = 2;
  
  public static final short VALIDATION_NONE = 0;
  
  public static final short VALIDATION_PARTIAL = 1;
  
  public static final short VALIDATION_FULL = 2;
  
  String getValidationContext();
  
  short getValidity();
  
  short getValidationAttempted();
  
  StringList getErrorCodes();
  
  StringList getErrorMessages();
  
  String getSchemaNormalizedValue();
  
  Object getActualNormalizedValue() throws XSException;
  
  short getActualNormalizedValueType() throws XSException;
  
  ShortList getItemValueTypes() throws XSException;
  
  XSValue getSchemaValue();
  
  XSTypeDefinition getTypeDefinition();
  
  XSSimpleTypeDefinition getMemberTypeDefinition();
  
  String getSchemaDefault();
  
  boolean getIsSchemaSpecified();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/ItemPSVI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */