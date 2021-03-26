package org.apache.xerces.dom;

import java.io.Serializable;

class NodeListCache implements Serializable {
  private static final long serialVersionUID = -7927529254918631002L;
  
  int fLength = -1;
  
  int fChildIndex = -1;
  
  ChildNode fChild;
  
  ParentNode fOwner;
  
  NodeListCache next;
  
  NodeListCache(ParentNode paramParentNode) {
    this.fOwner = paramParentNode;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/NodeListCache.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */