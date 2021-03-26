package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

class XSDUniqueOrKeyTraverser extends XSDAbstractIDConstraintTraverser {
  public XSDUniqueOrKeyTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  void traverse(Element paramElement, XSElementDecl paramXSElementDecl, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    String str = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    if (str == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { DOMUtil.getLocalName(paramElement), SchemaSymbols.ATT_NAME }, paramElement);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return;
    } 
    UniqueOrKey uniqueOrKey = null;
    if (DOMUtil.getLocalName(paramElement).equals(SchemaSymbols.ELT_UNIQUE)) {
      uniqueOrKey = new UniqueOrKey(paramXSDocumentInfo.fTargetNamespace, str, paramXSElementDecl.fName, (short)3);
    } else {
      uniqueOrKey = new UniqueOrKey(paramXSDocumentInfo.fTargetNamespace, str, paramXSElementDecl.fName, (short)1);
    } 
    if (traverseIdentityConstraint((IdentityConstraint)uniqueOrKey, paramElement, paramXSDocumentInfo, arrayOfObject)) {
      if (paramSchemaGrammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName()) == null)
        paramSchemaGrammar.addIDConstraintDecl(paramXSElementDecl, (IdentityConstraint)uniqueOrKey); 
      String str1 = this.fSchemaHandler.schemaDocument2SystemId(paramXSDocumentInfo);
      IdentityConstraint identityConstraint = paramSchemaGrammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName(), str1);
      if (identityConstraint == null)
        paramSchemaGrammar.addIDConstraintDecl(paramXSElementDecl, (IdentityConstraint)uniqueOrKey, str1); 
      if (this.fSchemaHandler.fTolerateDuplicates) {
        if (identityConstraint != null && identityConstraint instanceof UniqueOrKey)
          uniqueOrKey = uniqueOrKey; 
        this.fSchemaHandler.addIDConstraintDecl((IdentityConstraint)uniqueOrKey);
      } 
    } 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/XSDUniqueOrKeyTraverser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */