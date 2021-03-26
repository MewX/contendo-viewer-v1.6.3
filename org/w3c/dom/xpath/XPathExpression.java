package org.w3c.dom.xpath;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public interface XPathExpression {
  Object evaluate(Node paramNode, short paramShort, Object paramObject) throws XPathException, DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/xpath/XPathExpression.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */