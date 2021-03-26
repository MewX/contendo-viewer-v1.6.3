package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSValue;

public class ElementPSVImpl implements ElementPSVI {
  protected XSElementDeclaration fDeclaration = null;
  
  protected XSTypeDefinition fTypeDecl = null;
  
  protected boolean fNil = false;
  
  protected boolean fSpecified = false;
  
  protected ValidatedInfo fValue = new ValidatedInfo();
  
  protected XSNotationDeclaration fNotation = null;
  
  protected short fValidationAttempted = 0;
  
  protected short fValidity = 0;
  
  protected String[] fErrors = null;
  
  protected String fValidationContext = null;
  
  protected SchemaGrammar[] fGrammars = null;
  
  protected XSModel fSchemaInformation = null;
  
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
    return (StringList)((this.fErrors == null || this.fErrors.length == 0) ? StringListImpl.EMPTY_LIST : new PSVIErrorList(this.fErrors, true));
  }
  
  public StringList getErrorMessages() {
    return (StringList)((this.fErrors == null || this.fErrors.length == 0) ? StringListImpl.EMPTY_LIST : new PSVIErrorList(this.fErrors, false));
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
  
  public synchronized XSModel getSchemaInformation() {
    if (this.fSchemaInformation == null && this.fGrammars != null)
      this.fSchemaInformation = new XSModelImpl(this.fGrammars); 
    return this.fSchemaInformation;
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
  
  public void reset() {
    this.fDeclaration = null;
    this.fTypeDecl = null;
    this.fNil = false;
    this.fSpecified = false;
    this.fNotation = null;
    this.fValidationAttempted = 0;
    this.fValidity = 0;
    this.fErrors = null;
    this.fValidationContext = null;
    this.fValue.reset();
  }
  
  public void copySchemaInformationTo(ElementPSVImpl paramElementPSVImpl) {
    paramElementPSVImpl.fGrammars = this.fGrammars;
    paramElementPSVImpl.fSchemaInformation = this.fSchemaInformation;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/ElementPSVImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */