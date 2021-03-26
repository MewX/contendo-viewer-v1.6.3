package org.apache.batik.dom.xbl;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface XBLManager {
  void startProcessing();
  
  void stopProcessing();
  
  boolean isProcessing();
  
  Node getXblParentNode(Node paramNode);
  
  NodeList getXblChildNodes(Node paramNode);
  
  NodeList getXblScopedChildNodes(Node paramNode);
  
  Node getXblFirstChild(Node paramNode);
  
  Node getXblLastChild(Node paramNode);
  
  Node getXblPreviousSibling(Node paramNode);
  
  Node getXblNextSibling(Node paramNode);
  
  Element getXblFirstElementChild(Node paramNode);
  
  Element getXblLastElementChild(Node paramNode);
  
  Element getXblPreviousElementSibling(Node paramNode);
  
  Element getXblNextElementSibling(Node paramNode);
  
  Element getXblBoundElement(Node paramNode);
  
  Element getXblShadowTree(Node paramNode);
  
  NodeList getXblDefinitions(Node paramNode);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/xbl/XBLManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */