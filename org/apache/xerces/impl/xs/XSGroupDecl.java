package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSModelGroup;
import org.apache.xerces.xs.XSModelGroupDefinition;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObjectList;

public class XSGroupDecl implements XSModelGroupDefinition {
  public String fName = null;
  
  public String fTargetNamespace = null;
  
  public XSModelGroupImpl fModelGroup = null;
  
  public XSObjectList fAnnotations = null;
  
  private XSNamespaceItem fNamespaceItem = null;
  
  public short getType() {
    return 6;
  }
  
  public String getName() {
    return this.fName;
  }
  
  public String getNamespace() {
    return this.fTargetNamespace;
  }
  
  public XSModelGroup getModelGroup() {
    return this.fModelGroup;
  }
  
  public XSAnnotation getAnnotation() {
    return (this.fAnnotations != null) ? (XSAnnotation)this.fAnnotations.item(0) : null;
  }
  
  public XSObjectList getAnnotations() {
    return (this.fAnnotations != null) ? this.fAnnotations : (XSObjectList)XSObjectListImpl.EMPTY_LIST;
  }
  
  public XSNamespaceItem getNamespaceItem() {
    return this.fNamespaceItem;
  }
  
  void setNamespaceItem(XSNamespaceItem paramXSNamespaceItem) {
    this.fNamespaceItem = paramXSNamespaceItem;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/XSGroupDecl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */