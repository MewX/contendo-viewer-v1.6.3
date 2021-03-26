package org.apache.xerces.dom;

public class DeferredEntityReferenceImpl extends EntityReferenceImpl implements DeferredNode {
  static final long serialVersionUID = 390319091370032223L;
  
  protected transient int fNodeIndex;
  
  DeferredEntityReferenceImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
    super(paramDeferredDocumentImpl, (String)null);
    this.fNodeIndex = paramInt;
    needsSyncData(true);
  }
  
  public int getNodeIndex() {
    return this.fNodeIndex;
  }
  
  protected void synchronizeData() {
    needsSyncData(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument;
    this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    this.baseURI = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
  }
  
  protected void synchronizeChildren() {
    needsSyncChildren(false);
    isReadOnly(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    deferredDocumentImpl.synchronizeChildren(this, this.fNodeIndex);
    setReadOnly(true, true);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredEntityReferenceImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */