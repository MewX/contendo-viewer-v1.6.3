package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.dtd.models.CMNode;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSDeclarationPool;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSParticleDecl;

public class CMBuilder {
  private XSDeclarationPool fDeclPool = null;
  
  private static final XSEmptyCM fEmptyCM = new XSEmptyCM();
  
  private int fLeafCount;
  
  private int fParticleCount;
  
  private final CMNodeFactory fNodeFactory;
  
  public CMBuilder(CMNodeFactory paramCMNodeFactory) {
    this.fDeclPool = null;
    this.fNodeFactory = paramCMNodeFactory;
  }
  
  public void setDeclPool(XSDeclarationPool paramXSDeclarationPool) {
    this.fDeclPool = paramXSDeclarationPool;
  }
  
  public XSCMValidator getContentModel(XSComplexTypeDecl paramXSComplexTypeDecl, boolean paramBoolean) {
    short s = paramXSComplexTypeDecl.getContentType();
    if (s == 1 || s == 0)
      return null; 
    XSParticleDecl xSParticleDecl = (XSParticleDecl)paramXSComplexTypeDecl.getParticle();
    if (xSParticleDecl == null)
      return fEmptyCM; 
    XSCMValidator xSCMValidator = null;
    if (xSParticleDecl.fType == 3 && ((XSModelGroupImpl)xSParticleDecl.fValue).fCompositor == 103) {
      xSCMValidator = createAllCM(xSParticleDecl);
    } else {
      xSCMValidator = createDFACM(xSParticleDecl, paramBoolean);
    } 
    this.fNodeFactory.resetNodeCount();
    if (xSCMValidator == null)
      xSCMValidator = fEmptyCM; 
    return xSCMValidator;
  }
  
  XSCMValidator createAllCM(XSParticleDecl paramXSParticleDecl) {
    if (paramXSParticleDecl.fMaxOccurs == 0)
      return null; 
    XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl)paramXSParticleDecl.fValue;
    XSAllCM xSAllCM = new XSAllCM((paramXSParticleDecl.fMinOccurs == 0), xSModelGroupImpl.fParticleCount);
    for (byte b = 0; b < xSModelGroupImpl.fParticleCount; b++)
      xSAllCM.addElement((XSElementDecl)(xSModelGroupImpl.fParticles[b]).fValue, ((xSModelGroupImpl.fParticles[b]).fMinOccurs == 0)); 
    return xSAllCM;
  }
  
  XSCMValidator createDFACM(XSParticleDecl paramXSParticleDecl, boolean paramBoolean) {
    this.fLeafCount = 0;
    this.fParticleCount = 0;
    CMNode cMNode = useRepeatingLeafNodes(paramXSParticleDecl) ? buildCompactSyntaxTree(paramXSParticleDecl) : buildSyntaxTree(paramXSParticleDecl, paramBoolean);
    return (cMNode == null) ? null : new XSDFACM(cMNode, this.fLeafCount);
  }
  
  private CMNode buildSyntaxTree(XSParticleDecl paramXSParticleDecl, boolean paramBoolean) {
    int i = paramXSParticleDecl.fMaxOccurs;
    int j = paramXSParticleDecl.fMinOccurs;
    boolean bool = false;
    if (paramBoolean) {
      if (j > 1)
        if (i > j || paramXSParticleDecl.getMaxOccursUnbounded()) {
          j = 1;
          bool = true;
        } else {
          j = 2;
          bool = true;
        }  
      if (i > 1) {
        i = 2;
        bool = true;
      } 
    } 
    short s = paramXSParticleDecl.fType;
    CMNode cMNode = null;
    if (s == 2 || s == 1) {
      cMNode = this.fNodeFactory.getCMLeafNode(paramXSParticleDecl.fType, paramXSParticleDecl.fValue, this.fParticleCount++, this.fLeafCount++);
      cMNode = expandContentModel(cMNode, j, i);
      if (cMNode != null)
        cMNode.setIsCompactUPAModel(bool); 
    } else if (s == 3) {
      XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl)paramXSParticleDecl.fValue;
      CMNode cMNode1 = null;
      byte b1 = 0;
      for (byte b2 = 0; b2 < xSModelGroupImpl.fParticleCount; b2++) {
        cMNode1 = buildSyntaxTree(xSModelGroupImpl.fParticles[b2], paramBoolean);
        if (cMNode1 != null) {
          bool |= cMNode1.isCompactedForUPA();
          b1++;
          if (cMNode == null) {
            cMNode = cMNode1;
          } else {
            cMNode = this.fNodeFactory.getCMBinOpNode(xSModelGroupImpl.fCompositor, cMNode, cMNode1);
          } 
        } 
      } 
      if (cMNode != null) {
        if (xSModelGroupImpl.fCompositor == 101 && b1 < xSModelGroupImpl.fParticleCount)
          cMNode = this.fNodeFactory.getCMUniOpNode(5, cMNode); 
        cMNode = expandContentModel(cMNode, j, i);
        cMNode.setIsCompactUPAModel(bool);
      } 
    } 
    return cMNode;
  }
  
  private CMNode expandContentModel(CMNode paramCMNode, int paramInt1, int paramInt2) {
    CMNode cMNode = null;
    if (paramInt1 == 1 && paramInt2 == 1) {
      cMNode = paramCMNode;
    } else if (paramInt1 == 0 && paramInt2 == 1) {
      cMNode = this.fNodeFactory.getCMUniOpNode(5, paramCMNode);
    } else if (paramInt1 == 0 && paramInt2 == -1) {
      cMNode = this.fNodeFactory.getCMUniOpNode(4, paramCMNode);
    } else if (paramInt1 == 1 && paramInt2 == -1) {
      cMNode = this.fNodeFactory.getCMUniOpNode(6, paramCMNode);
    } else if (paramInt2 == -1) {
      cMNode = this.fNodeFactory.getCMUniOpNode(6, paramCMNode);
      cMNode = this.fNodeFactory.getCMBinOpNode(102, multiNodes(paramCMNode, paramInt1 - 1, true), cMNode);
    } else {
      if (paramInt1 > 0)
        cMNode = multiNodes(paramCMNode, paramInt1, false); 
      if (paramInt2 > paramInt1) {
        paramCMNode = this.fNodeFactory.getCMUniOpNode(5, paramCMNode);
        if (cMNode == null) {
          cMNode = multiNodes(paramCMNode, paramInt2 - paramInt1, false);
        } else {
          cMNode = this.fNodeFactory.getCMBinOpNode(102, cMNode, multiNodes(paramCMNode, paramInt2 - paramInt1, true));
        } 
      } 
    } 
    return cMNode;
  }
  
  private CMNode multiNodes(CMNode paramCMNode, int paramInt, boolean paramBoolean) {
    if (paramInt == 0)
      return null; 
    if (paramInt == 1)
      return paramBoolean ? copyNode(paramCMNode) : paramCMNode; 
    int i = paramInt / 2;
    return this.fNodeFactory.getCMBinOpNode(102, multiNodes(paramCMNode, i, paramBoolean), multiNodes(paramCMNode, paramInt - i, true));
  }
  
  private CMNode copyNode(CMNode paramCMNode) {
    int i = paramCMNode.type();
    if (i == 101 || i == 102) {
      XSCMBinOp xSCMBinOp = (XSCMBinOp)paramCMNode;
      paramCMNode = this.fNodeFactory.getCMBinOpNode(i, copyNode(xSCMBinOp.getLeft()), copyNode(xSCMBinOp.getRight()));
    } else if (i == 4 || i == 6 || i == 5) {
      XSCMUniOp xSCMUniOp = (XSCMUniOp)paramCMNode;
      paramCMNode = this.fNodeFactory.getCMUniOpNode(i, copyNode(xSCMUniOp.getChild()));
    } else if (i == 1 || i == 2) {
      XSCMLeaf xSCMLeaf = (XSCMLeaf)paramCMNode;
      paramCMNode = this.fNodeFactory.getCMLeafNode(xSCMLeaf.type(), xSCMLeaf.getLeaf(), xSCMLeaf.getParticleId(), this.fLeafCount++);
    } 
    return paramCMNode;
  }
  
  private CMNode buildCompactSyntaxTree(XSParticleDecl paramXSParticleDecl) {
    int i = paramXSParticleDecl.fMaxOccurs;
    int j = paramXSParticleDecl.fMinOccurs;
    short s = paramXSParticleDecl.fType;
    CMNode cMNode = null;
    if (s == 2 || s == 1)
      return buildCompactSyntaxTree2(paramXSParticleDecl, j, i); 
    if (s == 3) {
      XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl)paramXSParticleDecl.fValue;
      if (xSModelGroupImpl.fParticleCount == 1 && (j != 1 || i != 1))
        return buildCompactSyntaxTree2(xSModelGroupImpl.fParticles[0], j, i); 
      CMNode cMNode1 = null;
      byte b1 = 0;
      for (byte b2 = 0; b2 < xSModelGroupImpl.fParticleCount; b2++) {
        cMNode1 = buildCompactSyntaxTree(xSModelGroupImpl.fParticles[b2]);
        if (cMNode1 != null) {
          b1++;
          if (cMNode == null) {
            cMNode = cMNode1;
          } else {
            cMNode = this.fNodeFactory.getCMBinOpNode(xSModelGroupImpl.fCompositor, cMNode, cMNode1);
          } 
        } 
      } 
      if (cMNode != null && xSModelGroupImpl.fCompositor == 101 && b1 < xSModelGroupImpl.fParticleCount)
        cMNode = this.fNodeFactory.getCMUniOpNode(5, cMNode); 
    } 
    return cMNode;
  }
  
  private CMNode buildCompactSyntaxTree2(XSParticleDecl paramXSParticleDecl, int paramInt1, int paramInt2) {
    CMNode cMNode = null;
    if (paramInt1 == 1 && paramInt2 == 1) {
      cMNode = this.fNodeFactory.getCMLeafNode(paramXSParticleDecl.fType, paramXSParticleDecl.fValue, this.fParticleCount++, this.fLeafCount++);
    } else if (paramInt1 == 0 && paramInt2 == 1) {
      cMNode = this.fNodeFactory.getCMLeafNode(paramXSParticleDecl.fType, paramXSParticleDecl.fValue, this.fParticleCount++, this.fLeafCount++);
      cMNode = this.fNodeFactory.getCMUniOpNode(5, cMNode);
    } else if (paramInt1 == 0 && paramInt2 == -1) {
      cMNode = this.fNodeFactory.getCMLeafNode(paramXSParticleDecl.fType, paramXSParticleDecl.fValue, this.fParticleCount++, this.fLeafCount++);
      cMNode = this.fNodeFactory.getCMUniOpNode(4, cMNode);
    } else if (paramInt1 == 1 && paramInt2 == -1) {
      cMNode = this.fNodeFactory.getCMLeafNode(paramXSParticleDecl.fType, paramXSParticleDecl.fValue, this.fParticleCount++, this.fLeafCount++);
      cMNode = this.fNodeFactory.getCMUniOpNode(6, cMNode);
    } else {
      cMNode = this.fNodeFactory.getCMRepeatingLeafNode(paramXSParticleDecl.fType, paramXSParticleDecl.fValue, paramInt1, paramInt2, this.fParticleCount++, this.fLeafCount++);
      if (paramInt1 == 0) {
        cMNode = this.fNodeFactory.getCMUniOpNode(4, cMNode);
      } else {
        cMNode = this.fNodeFactory.getCMUniOpNode(6, cMNode);
      } 
    } 
    return cMNode;
  }
  
  private boolean useRepeatingLeafNodes(XSParticleDecl paramXSParticleDecl) {
    int i = paramXSParticleDecl.fMaxOccurs;
    int j = paramXSParticleDecl.fMinOccurs;
    short s = paramXSParticleDecl.fType;
    if (s == 3) {
      XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl)paramXSParticleDecl.fValue;
      if (j != 1 || i != 1) {
        if (xSModelGroupImpl.fParticleCount == 1) {
          XSParticleDecl xSParticleDecl = xSModelGroupImpl.fParticles[0];
          short s1 = xSParticleDecl.fType;
          return ((s1 == 1 || s1 == 2) && xSParticleDecl.fMinOccurs == 1 && xSParticleDecl.fMaxOccurs == 1);
        } 
        return (xSModelGroupImpl.fParticleCount == 0);
      } 
      for (byte b = 0; b < xSModelGroupImpl.fParticleCount; b++) {
        if (!useRepeatingLeafNodes(xSModelGroupImpl.fParticles[b]))
          return false; 
      } 
    } 
    return true;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/models/CMBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */