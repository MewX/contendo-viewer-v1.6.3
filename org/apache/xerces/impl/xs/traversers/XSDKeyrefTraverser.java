package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.identity.KeyRef;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import org.apache.xerces.xni.QName;
import org.w3c.dom.Element;

class XSDKeyrefTraverser extends XSDAbstractIDConstraintTraverser {
  public XSDKeyrefTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  void traverse(Element paramElement, XSElementDecl paramXSElementDecl, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    String str = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    if (str == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_NAME }, paramElement);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return;
    } 
    QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_REFER];
    if (qName == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_REFER }, paramElement);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return;
    } 
    UniqueOrKey uniqueOrKey = null;
    IdentityConstraint identityConstraint = (IdentityConstraint)this.fSchemaHandler.getGlobalDecl(paramXSDocumentInfo, 5, qName, paramElement);
    if (identityConstraint != null)
      if (identityConstraint.getCategory() == 1 || identityConstraint.getCategory() == 3) {
        uniqueOrKey = (UniqueOrKey)identityConstraint;
      } else {
        reportSchemaError("src-resolve", new Object[] { qName.rawname, "identity constraint key/unique" }, paramElement);
      }  
    if (uniqueOrKey == null) {
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return;
    } 
    KeyRef keyRef = new KeyRef(paramXSDocumentInfo.fTargetNamespace, str, paramXSElementDecl.fName, uniqueOrKey);
    if (traverseIdentityConstraint((IdentityConstraint)keyRef, paramElement, paramXSDocumentInfo, arrayOfObject))
      if (uniqueOrKey.getFieldCount() != keyRef.getFieldCount()) {
        reportSchemaError("c-props-correct.2", new Object[] { str, uniqueOrKey.getIdentityConstraintName() }, paramElement);
      } else {
        if (paramSchemaGrammar.getIDConstraintDecl(keyRef.getIdentityConstraintName()) == null)
          paramSchemaGrammar.addIDConstraintDecl(paramXSElementDecl, (IdentityConstraint)keyRef); 
        String str1 = this.fSchemaHandler.schemaDocument2SystemId(paramXSDocumentInfo);
        IdentityConstraint identityConstraint1 = paramSchemaGrammar.getIDConstraintDecl(keyRef.getIdentityConstraintName(), str1);
        if (identityConstraint1 == null)
          paramSchemaGrammar.addIDConstraintDecl(paramXSElementDecl, (IdentityConstraint)keyRef, str1); 
        if (this.fSchemaHandler.fTolerateDuplicates) {
          if (identityConstraint1 != null && identityConstraint1 instanceof KeyRef)
            keyRef = (KeyRef)identityConstraint1; 
          this.fSchemaHandler.addIDConstraintDecl((IdentityConstraint)keyRef);
        } 
      }  
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/XSDKeyrefTraverser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */