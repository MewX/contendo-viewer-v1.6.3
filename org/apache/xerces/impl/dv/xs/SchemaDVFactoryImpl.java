package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.util.SymbolHash;

public class SchemaDVFactoryImpl extends BaseSchemaDVFactory {
  static final SymbolHash fBuiltInTypes = new SymbolHash();
  
  static void createBuiltInTypes() {
    BaseSchemaDVFactory.createBuiltInTypes(fBuiltInTypes, XSSimpleTypeDecl.fAnySimpleType);
  }
  
  public XSSimpleType getBuiltInType(String paramString) {
    return (XSSimpleType)fBuiltInTypes.get(paramString);
  }
  
  public SymbolHash getBuiltInTypes() {
    return fBuiltInTypes.makeClone();
  }
  
  static {
    createBuiltInTypes();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/xs/SchemaDVFactoryImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */