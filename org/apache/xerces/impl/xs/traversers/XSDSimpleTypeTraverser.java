package org.apache.xerces.impl.xs.traversers;

import java.util.ArrayList;
import java.util.Vector;
import org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSTypeDefinition;
import org.w3c.dom.Element;

class XSDSimpleTypeTraverser extends XSDAbstractTraverser {
  private boolean fIsBuiltIn = false;
  
  XSDSimpleTypeTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  XSSimpleType traverseGlobal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, true, paramXSDocumentInfo);
    String str = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    if (str == null)
      arrayOfObject[XSAttributeChecker.ATTIDX_NAME] = "(no name)"; 
    XSSimpleType xSSimpleType = traverseSimpleTypeDecl(paramElement, arrayOfObject, paramXSDocumentInfo, paramSchemaGrammar);
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    if (str == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, SchemaSymbols.ATT_NAME }, paramElement);
      xSSimpleType = null;
    } 
    if (xSSimpleType != null) {
      if (paramSchemaGrammar.getGlobalTypeDecl(xSSimpleType.getName()) == null)
        paramSchemaGrammar.addGlobalSimpleTypeDecl(xSSimpleType); 
      String str1 = this.fSchemaHandler.schemaDocument2SystemId(paramXSDocumentInfo);
      XSTypeDefinition xSTypeDefinition = paramSchemaGrammar.getGlobalTypeDecl(xSSimpleType.getName(), str1);
      if (xSTypeDefinition == null)
        paramSchemaGrammar.addGlobalSimpleTypeDecl(xSSimpleType, str1); 
      if (this.fSchemaHandler.fTolerateDuplicates) {
        if (xSTypeDefinition != null && xSTypeDefinition instanceof XSSimpleType)
          xSSimpleType = (XSSimpleType)xSTypeDefinition; 
        this.fSchemaHandler.addGlobalTypeDecl((XSTypeDefinition)xSSimpleType);
      } 
    } 
    return xSSimpleType;
  }
  
  XSSimpleType traverseLocal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    String str = genAnonTypeName(paramElement);
    XSSimpleType xSSimpleType = getSimpleType(str, paramElement, arrayOfObject, paramXSDocumentInfo, paramSchemaGrammar);
    if (xSSimpleType instanceof XSSimpleTypeDecl)
      ((XSSimpleTypeDecl)xSSimpleType).setAnonymous(true); 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSSimpleType;
  }
  
  private XSSimpleType traverseSimpleTypeDecl(Element paramElement, Object[] paramArrayOfObject, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    String str = (String)paramArrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    return getSimpleType(str, paramElement, paramArrayOfObject, paramXSDocumentInfo, paramSchemaGrammar);
  }
  
  private String genAnonTypeName(Element paramElement) {
    StringBuffer stringBuffer = new StringBuffer("#AnonType_");
    for (Element element = DOMUtil.getParent(paramElement); element != null && element != DOMUtil.getRoot(DOMUtil.getDocument(element)); element = DOMUtil.getParent(element))
      stringBuffer.append(element.getAttribute(SchemaSymbols.ATT_NAME)); 
    return stringBuffer.toString();
  }
  
  private XSSimpleType getSimpleType(String paramString, Element paramElement, Object[] paramArrayOfObject, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    XInt xInt = (XInt)paramArrayOfObject[XSAttributeChecker.ATTIDX_FINAL];
    boolean bool1 = (xInt == null) ? paramXSDocumentInfo.fFinalDefault : xInt.intValue();
    Element element1 = DOMUtil.getFirstChildElement(paramElement);
    XSAnnotationImpl[] arrayOfXSAnnotationImpl = null;
    if (element1 != null && DOMUtil.getLocalName(element1).equals(SchemaSymbols.ELT_ANNOTATION)) {
      XSAnnotationImpl xSAnnotationImpl = traverseAnnotationDecl(element1, paramArrayOfObject, false, paramXSDocumentInfo);
      if (xSAnnotationImpl != null)
        arrayOfXSAnnotationImpl = new XSAnnotationImpl[] { xSAnnotationImpl }; 
      element1 = DOMUtil.getNextSiblingElement(element1);
    } else {
      String str1 = DOMUtil.getSyntheticAnnotation(paramElement);
      if (str1 != null) {
        XSAnnotationImpl xSAnnotationImpl = traverseSyntheticAnnotation(paramElement, str1, paramArrayOfObject, false, paramXSDocumentInfo);
        arrayOfXSAnnotationImpl = new XSAnnotationImpl[] { xSAnnotationImpl };
      } 
    } 
    if (element1 == null) {
      reportSchemaError("s4s-elt-must-match.2", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))" }, paramElement);
      return errorType(paramString, paramXSDocumentInfo.fTargetNamespace, (short)2);
    } 
    String str = DOMUtil.getLocalName(element1);
    byte b = 2;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    if (str.equals(SchemaSymbols.ELT_RESTRICTION)) {
      b = 2;
      bool2 = true;
    } else if (str.equals(SchemaSymbols.ELT_LIST)) {
      b = 16;
      bool3 = true;
    } else if (str.equals(SchemaSymbols.ELT_UNION)) {
      b = 8;
      bool4 = true;
    } else {
      reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", str }, paramElement);
      return errorType(paramString, paramXSDocumentInfo.fTargetNamespace, (short)2);
    } 
    Element element2 = DOMUtil.getNextSiblingElement(element1);
    if (element2 != null)
      reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", DOMUtil.getLocalName(element2) }, element2); 
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(element1, false, paramXSDocumentInfo);
    QName qName = (QName)arrayOfObject[bool2 ? XSAttributeChecker.ATTIDX_BASE : XSAttributeChecker.ATTIDX_ITEMTYPE];
    Vector vector = (Vector)arrayOfObject[XSAttributeChecker.ATTIDX_MEMBERTYPES];
    Element element3 = DOMUtil.getFirstChildElement(element1);
    if (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
      XSAnnotationImpl xSAnnotationImpl = traverseAnnotationDecl(element3, arrayOfObject, false, paramXSDocumentInfo);
      if (xSAnnotationImpl != null)
        if (arrayOfXSAnnotationImpl == null) {
          arrayOfXSAnnotationImpl = new XSAnnotationImpl[] { xSAnnotationImpl };
        } else {
          XSAnnotationImpl[] arrayOfXSAnnotationImpl1 = new XSAnnotationImpl[2];
          arrayOfXSAnnotationImpl1[0] = arrayOfXSAnnotationImpl[0];
          arrayOfXSAnnotationImpl = arrayOfXSAnnotationImpl1;
          arrayOfXSAnnotationImpl[1] = xSAnnotationImpl;
        }  
      element3 = DOMUtil.getNextSiblingElement(element3);
    } else {
      String str1 = DOMUtil.getSyntheticAnnotation(element1);
      if (str1 != null) {
        XSAnnotationImpl xSAnnotationImpl = traverseSyntheticAnnotation(element1, str1, arrayOfObject, false, paramXSDocumentInfo);
        if (arrayOfXSAnnotationImpl == null) {
          arrayOfXSAnnotationImpl = new XSAnnotationImpl[] { xSAnnotationImpl };
        } else {
          XSAnnotationImpl[] arrayOfXSAnnotationImpl1 = new XSAnnotationImpl[2];
          arrayOfXSAnnotationImpl1[0] = arrayOfXSAnnotationImpl[0];
          arrayOfXSAnnotationImpl = arrayOfXSAnnotationImpl1;
          arrayOfXSAnnotationImpl[1] = xSAnnotationImpl;
        } 
      } 
    } 
    XSSimpleType xSSimpleType1 = null;
    if ((bool2 || bool3) && qName != null) {
      xSSimpleType1 = findDTValidator(element1, paramString, qName, b, paramXSDocumentInfo);
      if (xSSimpleType1 == null && this.fIsBuiltIn) {
        this.fIsBuiltIn = false;
        return null;
      } 
    } 
    ArrayList arrayList = null;
    XSSimpleType xSSimpleType2 = null;
    if (bool4 && vector != null && vector.size() > 0) {
      int i = vector.size();
      arrayList = new ArrayList(i);
      for (byte b1 = 0; b1 < i; b1++) {
        xSSimpleType2 = findDTValidator(element1, paramString, vector.elementAt(b1), (short)8, paramXSDocumentInfo);
        if (xSSimpleType2 != null)
          if (xSSimpleType2.getVariety() == 3) {
            XSObjectList xSObjectList = xSSimpleType2.getMemberTypes();
            for (byte b2 = 0; b2 < xSObjectList.getLength(); b2++)
              arrayList.add(xSObjectList.item(b2)); 
          } else {
            arrayList.add(xSSimpleType2);
          }  
      } 
    } 
    if (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
      if (bool2 || bool3) {
        if (qName != null)
          reportSchemaError(bool3 ? "src-simple-type.3.a" : "src-simple-type.2.a", null, element3); 
        if (xSSimpleType1 == null)
          xSSimpleType1 = traverseLocal(element3, paramXSDocumentInfo, paramSchemaGrammar); 
        element3 = DOMUtil.getNextSiblingElement(element3);
      } else if (bool4) {
        if (arrayList == null)
          arrayList = new ArrayList(2); 
        do {
          xSSimpleType2 = traverseLocal(element3, paramXSDocumentInfo, paramSchemaGrammar);
          if (xSSimpleType2 != null)
            if (xSSimpleType2.getVariety() == 3) {
              XSObjectList xSObjectList = xSSimpleType2.getMemberTypes();
              for (byte b1 = 0; b1 < xSObjectList.getLength(); b1++)
                arrayList.add(xSObjectList.item(b1)); 
            } else {
              arrayList.add(xSSimpleType2);
            }  
          element3 = DOMUtil.getNextSiblingElement(element3);
        } while (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_SIMPLETYPE));
      } 
    } else if ((bool2 || bool3) && qName == null) {
      reportSchemaError(bool3 ? "src-simple-type.3.b" : "src-simple-type.2.b", null, element1);
    } else if (bool4 && (vector == null || vector.size() == 0)) {
      reportSchemaError("src-union-memberTypes-or-simpleTypes", null, element1);
    } 
    if ((bool2 || bool3) && xSSimpleType1 == null) {
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return errorType(paramString, paramXSDocumentInfo.fTargetNamespace, bool2 ? 2 : 16);
    } 
    if (bool4 && (arrayList == null || arrayList.size() == 0)) {
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return errorType(paramString, paramXSDocumentInfo.fTargetNamespace, (short)8);
    } 
    if (bool3 && isListDatatype(xSSimpleType1)) {
      reportSchemaError("cos-st-restricts.2.1", new Object[] { paramString, xSSimpleType1.getName() }, element1);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return errorType(paramString, paramXSDocumentInfo.fTargetNamespace, (short)16);
    } 
    XSSimpleType xSSimpleType3 = null;
    if (bool2) {
      xSSimpleType3 = this.fSchemaHandler.fDVFactory.createTypeRestriction(paramString, paramXSDocumentInfo.fTargetNamespace, (short)bool1, xSSimpleType1, (arrayOfXSAnnotationImpl == null) ? null : (XSObjectList)new XSObjectListImpl((XSObject[])arrayOfXSAnnotationImpl, arrayOfXSAnnotationImpl.length));
    } else if (bool3) {
      xSSimpleType3 = this.fSchemaHandler.fDVFactory.createTypeList(paramString, paramXSDocumentInfo.fTargetNamespace, (short)bool1, xSSimpleType1, (arrayOfXSAnnotationImpl == null) ? null : (XSObjectList)new XSObjectListImpl((XSObject[])arrayOfXSAnnotationImpl, arrayOfXSAnnotationImpl.length));
    } else if (bool4) {
      XSSimpleType[] arrayOfXSSimpleType = arrayList.<XSSimpleType>toArray(new XSSimpleType[arrayList.size()]);
      xSSimpleType3 = this.fSchemaHandler.fDVFactory.createTypeUnion(paramString, paramXSDocumentInfo.fTargetNamespace, (short)bool1, arrayOfXSSimpleType, (arrayOfXSAnnotationImpl == null) ? null : (XSObjectList)new XSObjectListImpl((XSObject[])arrayOfXSAnnotationImpl, arrayOfXSAnnotationImpl.length));
    } 
    if (bool2 && element3 != null) {
      XSDAbstractTraverser.FacetInfo facetInfo = traverseFacets(element3, xSSimpleType1, paramXSDocumentInfo);
      element3 = facetInfo.nodeAfterFacets;
      try {
        this.fValidationState.setNamespaceSupport((NamespaceContext)paramXSDocumentInfo.fNamespaceSupport);
        xSSimpleType3.applyFacets(facetInfo.facetdata, facetInfo.fPresentFacets, facetInfo.fFixedFacets, (ValidationContext)this.fValidationState);
      } catch (InvalidDatatypeFacetException invalidDatatypeFacetException) {
        reportSchemaError(invalidDatatypeFacetException.getKey(), invalidDatatypeFacetException.getArgs(), element1);
        xSSimpleType3 = this.fSchemaHandler.fDVFactory.createTypeRestriction(paramString, paramXSDocumentInfo.fTargetNamespace, (short)bool1, xSSimpleType1, (arrayOfXSAnnotationImpl == null) ? null : (XSObjectList)new XSObjectListImpl((XSObject[])arrayOfXSAnnotationImpl, arrayOfXSAnnotationImpl.length));
      } 
    } 
    if (element3 != null)
      if (bool2) {
        reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(element3) }, element3);
      } else if (bool3) {
        reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(element3) }, element3);
      } else if (bool4) {
        reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(element3) }, element3);
      }  
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSSimpleType3;
  }
  
  private XSSimpleType findDTValidator(Element paramElement, String paramString, QName paramQName, short paramShort, XSDocumentInfo paramXSDocumentInfo) {
    if (paramQName == null)
      return null; 
    XSTypeDefinition xSTypeDefinition = (XSTypeDefinition)this.fSchemaHandler.getGlobalDecl(paramXSDocumentInfo, 7, paramQName, paramElement);
    if (xSTypeDefinition == null)
      return null; 
    if (xSTypeDefinition.getTypeCategory() != 16) {
      reportSchemaError("cos-st-restricts.1.1", new Object[] { paramQName.rawname, paramString }, paramElement);
      return null;
    } 
    if (xSTypeDefinition == SchemaGrammar.fAnySimpleType && paramShort == 2) {
      if (checkBuiltIn(paramString, paramXSDocumentInfo.fTargetNamespace))
        return null; 
      reportSchemaError("cos-st-restricts.1.1", new Object[] { paramQName.rawname, paramString }, paramElement);
      return null;
    } 
    if ((xSTypeDefinition.getFinal() & paramShort) != 0) {
      if (paramShort == 2) {
        reportSchemaError("st-props-correct.3", new Object[] { paramString, paramQName.rawname }, paramElement);
      } else if (paramShort == 16) {
        reportSchemaError("cos-st-restricts.2.3.1.1", new Object[] { paramQName.rawname, paramString }, paramElement);
      } else if (paramShort == 8) {
        reportSchemaError("cos-st-restricts.3.3.1.1", new Object[] { paramQName.rawname, paramString }, paramElement);
      } 
      return null;
    } 
    return (XSSimpleType)xSTypeDefinition;
  }
  
  private final boolean checkBuiltIn(String paramString1, String paramString2) {
    if (paramString2 != SchemaSymbols.URI_SCHEMAFORSCHEMA)
      return false; 
    if (SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(paramString1) != null)
      this.fIsBuiltIn = true; 
    return this.fIsBuiltIn;
  }
  
  private boolean isListDatatype(XSSimpleType paramXSSimpleType) {
    if (paramXSSimpleType.getVariety() == 2)
      return true; 
    if (paramXSSimpleType.getVariety() == 3) {
      XSObjectList xSObjectList = paramXSSimpleType.getMemberTypes();
      for (byte b = 0; b < xSObjectList.getLength(); b++) {
        if (((XSSimpleType)xSObjectList.item(b)).getVariety() == 2)
          return true; 
      } 
    } 
    return false;
  }
  
  private XSSimpleType errorType(String paramString1, String paramString2, short paramShort) {
    XSSimpleType xSSimpleType = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getTypeDefinition("string");
    switch (paramShort) {
      case 2:
        return this.fSchemaHandler.fDVFactory.createTypeRestriction(paramString1, paramString2, (short)0, xSSimpleType, null);
      case 16:
        return this.fSchemaHandler.fDVFactory.createTypeList(paramString1, paramString2, (short)0, xSSimpleType, null);
      case 8:
        return this.fSchemaHandler.fDVFactory.createTypeUnion(paramString1, paramString2, (short)0, new XSSimpleType[] { xSSimpleType }, null);
    } 
    return null;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/XSDSimpleTypeTraverser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */