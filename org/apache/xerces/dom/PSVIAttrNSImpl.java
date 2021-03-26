package org.apache.xerces.dom;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSValue;

public class PSVIAttrNSImpl extends AttrNSImpl implements AttributePSVI {
  static final long serialVersionUID = -3241738699421018889L;
  
  protected XSAttributeDeclaration fDeclaration = null;
  
  protected XSTypeDefinition fTypeDecl = null;
  
  protected boolean fSpecified = true;
  
  protected ValidatedInfo fValue = new ValidatedInfo();
  
  protected short fValidationAttempted = 0;
  
  protected short fValidity = 0;
  
  protected StringList fErrorCodes = null;
  
  protected StringList fErrorMessages = null;
  
  protected String fValidationContext = null;
  
  public PSVIAttrNSImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString1, String paramString2, String paramString3) {
    super(paramCoreDocumentImpl, paramString1, paramString2, paramString3);
  }
  
  public PSVIAttrNSImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString1, String paramString2) {
    super(paramCoreDocumentImpl, paramString1, paramString2);
  }
  
  public String getSchemaDefault() {
    return (this.fDeclaration == null) ? null : this.fDeclaration.getConstraintValue();
  }
  
  public String getSchemaNormalizedValue() {
    return this.fValue.getNormalizedValue();
  }
  
  public boolean getIsSchemaSpecified() {
    return this.fSpecified;
  }
  
  public short getValidationAttempted() {
    return this.fValidationAttempted;
  }
  
  public short getValidity() {
    return this.fValidity;
  }
  
  public StringList getErrorCodes() {
    return (StringList)((this.fErrorCodes != null) ? this.fErrorCodes : StringListImpl.EMPTY_LIST);
  }
  
  public StringList getErrorMessages() {
    return (StringList)((this.fErrorMessages != null) ? this.fErrorMessages : StringListImpl.EMPTY_LIST);
  }
  
  public String getValidationContext() {
    return this.fValidationContext;
  }
  
  public XSTypeDefinition getTypeDefinition() {
    return this.fTypeDecl;
  }
  
  public XSSimpleTypeDefinition getMemberTypeDefinition() {
    return this.fValue.getMemberTypeDefinition();
  }
  
  public XSAttributeDeclaration getAttributeDeclaration() {
    return this.fDeclaration;
  }
  
  public void setPSVI(AttributePSVI paramAttributePSVI) {
    this.fDeclaration = paramAttributePSVI.getAttributeDeclaration();
    this.fValidationContext = paramAttributePSVI.getValidationContext();
    this.fValidity = paramAttributePSVI.getValidity();
    this.fValidationAttempted = paramAttributePSVI.getValidationAttempted();
    this.fErrorCodes = paramAttributePSVI.getErrorCodes();
    this.fErrorMessages = paramAttributePSVI.getErrorMessages();
    this.fValue.copyFrom(paramAttributePSVI.getSchemaValue());
    this.fTypeDecl = paramAttributePSVI.getTypeDefinition();
    this.fSpecified = paramAttributePSVI.getIsSchemaSpecified();
  }
  
  public Object getActualNormalizedValue() {
    return this.fValue.getActualValue();
  }
  
  public short getActualNormalizedValueType() {
    return this.fValue.getActualValueType();
  }
  
  public ShortList getItemValueTypes() {
    return this.fValue.getListValueTypes();
  }
  
  public XSValue getSchemaValue() {
    return (XSValue)this.fValue;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream) throws IOException {
    throw new NotSerializableException(getClass().getName());
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream) throws IOException, ClassNotFoundException {
    throw new NotSerializableException(getClass().getName());
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/PSVIAttrNSImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */