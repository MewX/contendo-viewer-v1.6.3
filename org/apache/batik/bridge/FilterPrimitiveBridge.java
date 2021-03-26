package org.apache.batik.bridge;

import java.awt.geom.Rectangle2D;
import java.util.Map;
import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.gvt.GraphicsNode;
import org.w3c.dom.Element;

public interface FilterPrimitiveBridge extends Bridge {
  Filter createFilter(BridgeContext paramBridgeContext, Element paramElement1, Element paramElement2, GraphicsNode paramGraphicsNode, Filter paramFilter, Rectangle2D paramRectangle2D, Map paramMap);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FilterPrimitiveBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */