package org.apache.xerces.dom;

public class DeferredNotationImpl extends NotationImpl implements DeferredNode {
  static final long serialVersionUID = 5705337172887990848L;
  
  protected transient int fNodeIndex;
  
  DeferredNotationImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
    super(paramDeferredDocumentImpl, (String)null);
    this.fNodeIndex = paramInt;
    needsSyncData(true);
  }
  
  public int getNodeIndex() {
    return this.fNodeIndex;
  }
  
  protected void synchronizeData() {
    needsSyncData(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    deferredDocumentImpl.getNodeType(this.fNodeIndex);
    this.publicId = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
    this.systemId = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
    int i = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
    deferredDocumentImpl.getNodeType(i);
    this.baseURI = deferredDocumentImpl.getNodeName(i);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredNotationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */