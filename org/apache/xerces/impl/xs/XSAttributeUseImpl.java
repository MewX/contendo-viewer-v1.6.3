package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSAttributeUse;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSValue;

public class XSAttributeUseImpl implements XSAttributeUse {
  public XSAttributeDecl fAttrDecl = null;
  
  public short fUse = 0;
  
  public short fConstraintType = 0;
  
  public ValidatedInfo fDefault = null;
  
  public XSObjectList fAnnotations = null;
  
  public void reset() {
    this.fDefault = null;
    this.fAttrDecl = null;
    this.fUse = 0;
    this.fConstraintType = 0;
    this.fAnnotations = null;
  }
  
  public short getType() {
    return 4;
  }
  
  public String getName() {
    return null;
  }
  
  public String getNamespace() {
    return null;
  }
  
  public boolean getRequired() {
    return (this.fUse == 1);
  }
  
  public XSAttributeDeclaration getAttrDeclaration() {
    return this.fAttrDecl;
  }
  
  public short getConstraintType() {
    return this.fConstraintType;
  }
  
  public String getConstraintValue() {
    return (getConstraintType() == 0) ? null : this.fDefault.stringValue();
  }
  
  public XSNamespaceItem getNamespaceItem() {
    return null;
  }
  
  public Object getActualVC() {
    return (getConstraintType() == 0) ? null : this.fDefault.actualValue;
  }
  
  public short getActualVCType() {
    return (getConstraintType() == 0) ? 45 : this.fDefault.actualValueType;
  }
  
  public ShortList getItemValueTypes() {
    return (getConstraintType() == 0) ? null : this.fDefault.itemValueTypes;
  }
  
  public XSValue getValueConstraintValue() {
    return (XSValue)this.fDefault;
  }
  
  public XSObjectList getAnnotations() {
    return (this.fAnnotations != null) ? this.fAnnotations : (XSObjectList)XSObjectListImpl.EMPTY_LIST;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/XSAttributeUseImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */