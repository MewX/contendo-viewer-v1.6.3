package org.apache.batik.bridge;

import java.util.Iterator;
import org.w3c.dom.Element;

public interface BridgeExtension {
  float getPriority();
  
  Iterator getImplementedExtensions();
  
  String getAuthor();
  
  String getContactAddress();
  
  String getURL();
  
  String getDescription();
  
  void registerTags(BridgeContext paramBridgeContext);
  
  boolean isDynamicElement(Element paramElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BridgeExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */