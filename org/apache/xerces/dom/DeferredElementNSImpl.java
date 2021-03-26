package org.apache.xerces.dom;

import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xs.XSTypeDefinition;
import org.w3c.dom.NamedNodeMap;

public class DeferredElementNSImpl extends ElementNSImpl implements DeferredNode {
  static final long serialVersionUID = -5001885145370927385L;
  
  protected transient int fNodeIndex;
  
  DeferredElementNSImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
    super(paramDeferredDocumentImpl, (String)null);
    this.fNodeIndex = paramInt;
    needsSyncChildren(true);
  }
  
  public final int getNodeIndex() {
    return this.fNodeIndex;
  }
  
  protected final void synchronizeData() {
    needsSyncData(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument;
    boolean bool = deferredDocumentImpl.mutationEvents;
    deferredDocumentImpl.mutationEvents = false;
    this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    int i = this.name.indexOf(':');
    if (i < 0) {
      this.localName = this.name;
    } else {
      this.localName = this.name.substring(i + 1);
    } 
    this.namespaceURI = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
    this.type = (XSTypeDefinition)deferredDocumentImpl.getTypeInfo(this.fNodeIndex);
    setupDefaultAttributes();
    int j = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
    if (j != -1) {
      NamedNodeMap namedNodeMap = getAttributes();
      boolean bool1 = false;
      do {
        AttrImpl attrImpl = (AttrImpl)deferredDocumentImpl.getNodeObject(j);
        if (!attrImpl.getSpecified() && (bool1 || (attrImpl.getNamespaceURI() != null && attrImpl.getNamespaceURI() != NamespaceContext.XMLNS_URI && attrImpl.getName().indexOf(':') < 0))) {
          bool1 = true;
          namedNodeMap.setNamedItemNS(attrImpl);
        } else {
          namedNodeMap.setNamedItem(attrImpl);
        } 
        j = deferredDocumentImpl.getPrevSibling(j);
      } while (j != -1);
    } 
    deferredDocumentImpl.mutationEvents = bool;
  }
  
  protected final void synchronizeChildren() {
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    deferredDocumentImpl.synchronizeChildren(this, this.fNodeIndex);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredElementNSImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */