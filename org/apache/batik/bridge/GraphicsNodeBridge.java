package org.apache.batik.bridge;

import org.apache.batik.gvt.GraphicsNode;
import org.w3c.dom.Element;

public interface GraphicsNodeBridge extends Bridge {
  GraphicsNode createGraphicsNode(BridgeContext paramBridgeContext, Element paramElement);
  
  void buildGraphicsNode(BridgeContext paramBridgeContext, Element paramElement, GraphicsNode paramGraphicsNode);
  
  boolean isComposite();
  
  boolean getDisplay(Element paramElement);
  
  Bridge getInstance();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/GraphicsNodeBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */