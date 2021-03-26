package org.apache.batik.bridge;

import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.gvt.filter.Mask;
import org.w3c.dom.Element;

public interface MaskBridge extends Bridge {
  Mask createMask(BridgeContext paramBridgeContext, Element paramElement1, Element paramElement2, GraphicsNode paramGraphicsNode);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/MaskBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */