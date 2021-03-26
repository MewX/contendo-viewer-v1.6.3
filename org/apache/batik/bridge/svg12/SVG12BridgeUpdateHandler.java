package org.apache.batik.bridge.svg12;

import org.apache.batik.bridge.BridgeUpdateHandler;
import org.w3c.dom.Element;

public interface SVG12BridgeUpdateHandler extends BridgeUpdateHandler {
  void handleBindingEvent(Element paramElement1, Element paramElement2);
  
  void handleContentSelectionChangedEvent(ContentSelectionChangedEvent paramContentSelectionChangedEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12BridgeUpdateHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */