package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.w3c.dom.Element;

class XSDAttributeGroupTraverser extends XSDAbstractTraverser {
  XSDAttributeGroupTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  XSAttributeGroupDecl traverseLocal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_REF];
    XSAttributeGroupDecl xSAttributeGroupDecl = null;
    if (qName == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { "attributeGroup (local)", "ref" }, paramElement);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return null;
    } 
    xSAttributeGroupDecl = (XSAttributeGroupDecl)this.fSchemaHandler.getGlobalDecl(paramXSDocumentInfo, 2, qName, paramElement);
    Element element = DOMUtil.getFirstChildElement(paramElement);
    if (element != null) {
      String str = DOMUtil.getLocalName(element);
      if (str.equals(SchemaSymbols.ELT_ANNOTATION)) {
        traverseAnnotationDecl(element, arrayOfObject, false, paramXSDocumentInfo);
        element = DOMUtil.getNextSiblingElement(element);
      } else {
        String str1 = DOMUtil.getSyntheticAnnotation(element);
        if (str1 != null)
          traverseSyntheticAnnotation(element, str1, arrayOfObject, false, paramXSDocumentInfo); 
      } 
      if (element != null) {
        Object[] arrayOfObject1 = { qName.rawname, "(annotation?)", DOMUtil.getLocalName(element) };
        reportSchemaError("s4s-elt-must-match.1", arrayOfObject1, element);
      } 
    } 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSAttributeGroupDecl;
  }
  
  XSAttributeGroupDecl traverseGlobal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    XSObjectListImpl xSObjectListImpl;
    XSAttributeGroupDecl xSAttributeGroupDecl1 = new XSAttributeGroupDecl();
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, true, paramXSDocumentInfo);
    String str1 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    if (str1 == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { "attributeGroup (global)", "name" }, paramElement);
      str1 = "(no name)";
    } 
    xSAttributeGroupDecl1.fName = str1;
    xSAttributeGroupDecl1.fTargetNamespace = paramXSDocumentInfo.fTargetNamespace;
    Element element1 = DOMUtil.getFirstChildElement(paramElement);
    XSAnnotationImpl xSAnnotationImpl = null;
    if (element1 != null && DOMUtil.getLocalName(element1).equals(SchemaSymbols.ELT_ANNOTATION)) {
      xSAnnotationImpl = traverseAnnotationDecl(element1, arrayOfObject, false, paramXSDocumentInfo);
      element1 = DOMUtil.getNextSiblingElement(element1);
    } else {
      String str = DOMUtil.getSyntheticAnnotation(paramElement);
      if (str != null)
        xSAnnotationImpl = traverseSyntheticAnnotation(paramElement, str, arrayOfObject, false, paramXSDocumentInfo); 
    } 
    Element element2 = traverseAttrsAndAttrGrps(element1, xSAttributeGroupDecl1, paramXSDocumentInfo, paramSchemaGrammar, null);
    if (element2 != null) {
      Object[] arrayOfObject1 = { str1, "(annotation?, ((attribute | attributeGroup)*, anyAttribute?))", DOMUtil.getLocalName(element2) };
      reportSchemaError("s4s-elt-must-match.1", arrayOfObject1, element2);
    } 
    if (str1.equals("(no name)")) {
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return null;
    } 
    xSAttributeGroupDecl1.removeProhibitedAttrs();
    XSAttributeGroupDecl xSAttributeGroupDecl2 = (XSAttributeGroupDecl)this.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(2, new QName(XMLSymbols.EMPTY_STRING, str1, str1, paramXSDocumentInfo.fTargetNamespace), paramXSDocumentInfo, paramElement);
    if (xSAttributeGroupDecl2 != null) {
      Object[] arrayOfObject1 = xSAttributeGroupDecl1.validRestrictionOf(str1, xSAttributeGroupDecl2);
      if (arrayOfObject1 != null) {
        reportSchemaError((String)arrayOfObject1[arrayOfObject1.length - 1], arrayOfObject1, element1);
        reportSchemaError("src-redefine.7.2.2", new Object[] { str1, arrayOfObject1[arrayOfObject1.length - 1] }, element1);
      } 
    } 
    if (xSAnnotationImpl != null) {
      xSObjectListImpl = new XSObjectListImpl();
      xSObjectListImpl.addXSObject((XSObject)xSAnnotationImpl);
    } else {
      xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
    } 
    xSAttributeGroupDecl1.fAnnotations = (XSObjectList)xSObjectListImpl;
    if (paramSchemaGrammar.getGlobalAttributeGroupDecl(xSAttributeGroupDecl1.fName) == null)
      paramSchemaGrammar.addGlobalAttributeGroupDecl(xSAttributeGroupDecl1); 
    String str2 = this.fSchemaHandler.schemaDocument2SystemId(paramXSDocumentInfo);
    XSAttributeGroupDecl xSAttributeGroupDecl3 = paramSchemaGrammar.getGlobalAttributeGroupDecl(xSAttributeGroupDecl1.fName, str2);
    if (xSAttributeGroupDecl3 == null)
      paramSchemaGrammar.addGlobalAttributeGroupDecl(xSAttributeGroupDecl1, str2); 
    if (this.fSchemaHandler.fTolerateDuplicates) {
      if (xSAttributeGroupDecl3 != null)
        xSAttributeGroupDecl1 = xSAttributeGroupDecl3; 
      this.fSchemaHandler.addGlobalAttributeGroupDecl(xSAttributeGroupDecl1);
    } 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSAttributeGroupDecl1;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/XSDAttributeGroupTraverser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */