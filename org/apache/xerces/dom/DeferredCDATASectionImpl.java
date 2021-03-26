package org.apache.xerces.dom;

public class DeferredCDATASectionImpl extends CDATASectionImpl implements DeferredNode {
  static final long serialVersionUID = 1983580632355645726L;
  
  protected transient int fNodeIndex;
  
  DeferredCDATASectionImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
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
    this.data = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredCDATASectionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */