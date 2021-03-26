package org.apache.xerces.dom;

public class DeferredTextImpl extends TextImpl implements DeferredNode {
  static final long serialVersionUID = 2310613872100393425L;
  
  protected transient int fNodeIndex;
  
  DeferredTextImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
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
    isIgnorableWhitespace((deferredDocumentImpl.getNodeExtra(this.fNodeIndex) == 1));
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredTextImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */