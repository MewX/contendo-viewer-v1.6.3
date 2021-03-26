package org.apache.xerces.dom;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSValue;

public class PSVIElementNSImpl extends ElementNSImpl implements ElementPSVI {
  static final long serialVersionUID = 6815489624636016068L;
  
  protected XSElementDeclaration fDeclaration = null;
  
  protected XSTypeDefinition fTypeDecl = null;
  
  protected boolean fNil = false;
  
  protected boolean fSpecified = true;
  
  protected ValidatedInfo fValue = new ValidatedInfo();
  
  protected XSNotationDeclaration fNotation = null;
  
  protected short fValidationAttempted = 0;
  
  protected short fValidity = 0;
  
  protected StringList fErrorCodes = null;
  
  protected StringList fErrorMessages = null;
  
  protected String fValidationContext = null;
  
  protected XSModel fSchemaInformation = null;
  
  public PSVIElementNSImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString1, String paramString2, String paramString3) {
    super(paramCoreDocumentImpl, paramString1, paramString2, paramString3);
  }
  
  public PSVIElementNSImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString1, String paramString2) {
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
  
  public boolean getNil() {
    return this.fNil;
  }
  
  public XSNotationDeclaration getNotation() {
    return this.fNotation;
  }
  
  public XSTypeDefinition getTypeDefinition() {
    return this.fTypeDecl;
  }
  
  public XSSimpleTypeDefinition getMemberTypeDefinition() {
    return this.fValue.getMemberTypeDefinition();
  }
  
  public XSElementDeclaration getElementDeclaration() {
    return this.fDeclaration;
  }
  
  public XSModel getSchemaInformation() {
    return this.fSchemaInformation;
  }
  
  public void setPSVI(ElementPSVI paramElementPSVI) {
    this.fDeclaration = paramElementPSVI.getElementDeclaration();
    this.fNotation = paramElementPSVI.getNotation();
    this.fValidationContext = paramElementPSVI.getValidationContext();
    this.fTypeDecl = paramElementPSVI.getTypeDefinition();
    this.fSchemaInformation = paramElementPSVI.getSchemaInformation();
    this.fValidity = paramElementPSVI.getValidity();
    this.fValidationAttempted = paramElementPSVI.getValidationAttempted();
    this.fErrorCodes = paramElementPSVI.getErrorCodes();
    this.fErrorMessages = paramElementPSVI.getErrorMessages();
    if (this.fTypeDecl instanceof XSSimpleTypeDefinition || (this.fTypeDecl instanceof XSComplexTypeDefinition && ((XSComplexTypeDefinition)this.fTypeDecl).getContentType() == 1)) {
      this.fValue.copyFrom(paramElementPSVI.getSchemaValue());
    } else {
      this.fValue.reset();
    } 
    this.fSpecified = paramElementPSVI.getIsSchemaSpecified();
    this.fNil = paramElementPSVI.getNil();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/PSVIElementNSImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */