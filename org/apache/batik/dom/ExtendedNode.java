package org.apache.batik.dom;

import org.apache.batik.dom.events.NodeEventTarget;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface ExtendedNode extends NodeEventTarget, Node {
  void setNodeName(String paramString);
  
  boolean isReadonly();
  
  void setReadonly(boolean paramBoolean);
  
  void setOwnerDocument(Document paramDocument);
  
  void setParentNode(Node paramNode);
  
  void setPreviousSibling(Node paramNode);
  
  void setNextSibling(Node paramNode);
  
  void setSpecified(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/ExtendedNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */