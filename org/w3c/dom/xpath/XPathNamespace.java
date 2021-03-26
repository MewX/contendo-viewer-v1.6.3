package org.w3c.dom.xpath;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface XPathNamespace extends Node {
  public static final short XPATH_NAMESPACE_NODE = 13;
  
  Element getOwnerElement();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/xpath/XPathNamespace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */