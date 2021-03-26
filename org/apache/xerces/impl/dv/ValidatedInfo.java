package org.apache.xerces.impl.dv;

import org.apache.xerces.impl.xs.util.ShortListImpl;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSValue;

public class ValidatedInfo implements XSValue {
  public String normalizedValue;
  
  public Object actualValue;
  
  public short actualValueType;
  
  public XSSimpleType actualType;
  
  public XSSimpleType memberType;
  
  public XSSimpleType[] memberTypes;
  
  public ShortList itemValueTypes;
  
  public void reset() {
    this.normalizedValue = null;
    this.actualValue = null;
    this.actualValueType = 45;
    this.actualType = null;
    this.memberType = null;
    this.memberTypes = null;
    this.itemValueTypes = null;
  }
  
  public String stringValue() {
    return (this.actualValue == null) ? this.normalizedValue : this.actualValue.toString();
  }
  
  public static boolean isComparable(ValidatedInfo paramValidatedInfo1, ValidatedInfo paramValidatedInfo2) {
    short s1 = convertToPrimitiveKind(paramValidatedInfo1.actualValueType);
    short s2 = convertToPrimitiveKind(paramValidatedInfo2.actualValueType);
    if (s1 != s2)
      return ((s1 == 1 && s2 == 2) || (s1 == 2 && s2 == 1)); 
    if (s1 == 44 || s1 == 43) {
      ShortList shortList1 = paramValidatedInfo1.itemValueTypes;
      ShortList shortList2 = paramValidatedInfo2.itemValueTypes;
      byte b1 = (shortList1 != null) ? shortList1.getLength() : 0;
      byte b2 = (shortList2 != null) ? shortList2.getLength() : 0;
      if (b1 != b2)
        return false; 
      byte b3 = 0;
      while (b3 < b1) {
        short s3 = convertToPrimitiveKind(shortList1.item(b3));
        short s4 = convertToPrimitiveKind(shortList2.item(b3));
        if (s3 == s4 || (s3 == 1 && s4 == 2) || (s3 == 2 && s4 == 1)) {
          b3++;
          continue;
        } 
        return false;
      } 
    } 
    return true;
  }
  
  private static short convertToPrimitiveKind(short paramShort) {
    return (paramShort <= 20) ? paramShort : ((paramShort <= 29) ? 2 : ((paramShort <= 42) ? 4 : paramShort));
  }
  
  public Object getActualValue() {
    return this.actualValue;
  }
  
  public short getActualValueType() {
    return this.actualValueType;
  }
  
  public ShortList getListValueTypes() {
    return (this.itemValueTypes == null) ? (ShortList)ShortListImpl.EMPTY_LIST : this.itemValueTypes;
  }
  
  public XSObjectList getMemberTypeDefinitions() {
    return (XSObjectList)((this.memberTypes == null) ? XSObjectListImpl.EMPTY_LIST : new XSObjectListImpl((XSObject[])this.memberTypes, this.memberTypes.length));
  }
  
  public String getNormalizedValue() {
    return this.normalizedValue;
  }
  
  public XSSimpleTypeDefinition getTypeDefinition() {
    return this.actualType;
  }
  
  public XSSimpleTypeDefinition getMemberTypeDefinition() {
    return this.memberType;
  }
  
  public void copyFrom(XSValue paramXSValue) {
    if (paramXSValue == null) {
      reset();
    } else if (paramXSValue instanceof ValidatedInfo) {
      ValidatedInfo validatedInfo = (ValidatedInfo)paramXSValue;
      this.normalizedValue = validatedInfo.normalizedValue;
      this.actualValue = validatedInfo.actualValue;
      this.actualValueType = validatedInfo.actualValueType;
      this.actualType = validatedInfo.actualType;
      this.memberType = validatedInfo.memberType;
      this.memberTypes = validatedInfo.memberTypes;
      this.itemValueTypes = validatedInfo.itemValueTypes;
    } else {
      this.normalizedValue = paramXSValue.getNormalizedValue();
      this.actualValue = paramXSValue.getActualValue();
      this.actualValueType = paramXSValue.getActualValueType();
      this.actualType = (XSSimpleType)paramXSValue.getTypeDefinition();
      this.memberType = (XSSimpleType)paramXSValue.getMemberTypeDefinition();
      XSSimpleType xSSimpleType = (this.memberType == null) ? this.actualType : this.memberType;
      if (xSSimpleType != null && xSSimpleType.getBuiltInKind() == 43) {
        XSObjectList xSObjectList = paramXSValue.getMemberTypeDefinitions();
        this.memberTypes = new XSSimpleType[xSObjectList.getLength()];
        for (byte b = 0; b < xSObjectList.getLength(); b++)
          this.memberTypes[b] = (XSSimpleType)xSObjectList.get(b); 
      } else {
        this.memberTypes = null;
      } 
      this.itemValueTypes = paramXSValue.getListValueTypes();
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/ValidatedInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */