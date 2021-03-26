package org.apache.xerces.dom;

public class DeferredProcessingInstructionImpl extends ProcessingInstructionImpl implements DeferredNode {
  static final long serialVersionUID = -4643577954293565388L;
  
  protected transient int fNodeIndex;
  
  DeferredProcessingInstructionImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
    super(paramDeferredDocumentImpl, (String)null, (String)null);
    this.fNodeIndex = paramInt;
    needsSyncData(true);
  }
  
  public int getNodeIndex() {
    return this.fNodeIndex;
  }
  
  protected void synchronizeData() {
    needsSyncData(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    this.target = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    this.data = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredProcessingInstructionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */