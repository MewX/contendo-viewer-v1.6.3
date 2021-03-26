package org.apache.batik.bridge;

import org.apache.batik.gvt.RootGraphicsNode;
import org.w3c.dom.Document;

public interface DocumentBridge extends Bridge {
  RootGraphicsNode createGraphicsNode(BridgeContext paramBridgeContext, Document paramDocument);
  
  void buildGraphicsNode(BridgeContext paramBridgeContext, Document paramDocument, RootGraphicsNode paramRootGraphicsNode);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/DocumentBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */