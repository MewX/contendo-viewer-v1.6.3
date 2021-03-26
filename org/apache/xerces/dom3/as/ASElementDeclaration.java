package org.apache.xerces.dom3.as;

public interface ASElementDeclaration extends ASObject {
  public static final short EMPTY_CONTENTTYPE = 1;
  
  public static final short ANY_CONTENTTYPE = 2;
  
  public static final short MIXED_CONTENTTYPE = 3;
  
  public static final short ELEMENTS_CONTENTTYPE = 4;
  
  boolean getStrictMixedContent();
  
  void setStrictMixedContent(boolean paramBoolean);
  
  ASDataType getElementType();
  
  void setElementType(ASDataType paramASDataType);
  
  boolean getIsPCDataOnly();
  
  void setIsPCDataOnly(boolean paramBoolean);
  
  short getContentType();
  
  void setContentType(short paramShort);
  
  String getSystemId();
  
  void setSystemId(String paramString);
  
  ASContentModel getAsCM();
  
  void setAsCM(ASContentModel paramASContentModel);
  
  ASNamedObjectMap getASAttributeDecls();
  
  void setASAttributeDecls(ASNamedObjectMap paramASNamedObjectMap);
  
  void addASAttributeDecl(ASAttributeDeclaration paramASAttributeDeclaration);
  
  ASAttributeDeclaration removeASAttributeDecl(ASAttributeDeclaration paramASAttributeDeclaration);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom3/as/ASElementDeclaration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */