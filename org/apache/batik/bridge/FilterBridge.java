package org.apache.batik.bridge;

import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.gvt.GraphicsNode;
import org.w3c.dom.Element;

public interface FilterBridge extends Bridge {
  Filter createFilter(BridgeContext paramBridgeContext, Element paramElement1, Element paramElement2, GraphicsNode paramGraphicsNode);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FilterBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */