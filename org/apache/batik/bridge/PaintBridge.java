package org.apache.batik.bridge;

import java.awt.Paint;
import org.apache.batik.gvt.GraphicsNode;
import org.w3c.dom.Element;

public interface PaintBridge extends Bridge {
  Paint createPaint(BridgeContext paramBridgeContext, Element paramElement1, Element paramElement2, GraphicsNode paramGraphicsNode, float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/PaintBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */