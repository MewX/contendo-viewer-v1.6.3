package org.apache.xerces.dom3.as;

public interface ASAttributeDeclaration extends ASObject {
  public static final short VALUE_NONE = 0;
  
  public static final short VALUE_DEFAULT = 1;
  
  public static final short VALUE_FIXED = 2;
  
  ASDataType getDataType();
  
  void setDataType(ASDataType paramASDataType);
  
  String getDataValue();
  
  void setDataValue(String paramString);
  
  String getEnumAttr();
  
  void setEnumAttr(String paramString);
  
  ASObjectList getOwnerElements();
  
  void setOwnerElements(ASObjectList paramASObjectList);
  
  short getDefaultType();
  
  void setDefaultType(short paramShort);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom3/as/ASAttributeDeclaration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */