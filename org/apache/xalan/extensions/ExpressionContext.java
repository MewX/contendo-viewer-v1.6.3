package org.apache.xalan.extensions;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

public interface ExpressionContext {
  Node getContextNode();
  
  NodeIterator getContextNodes();
  
  ErrorListener getErrorListener();
  
  double toNumber(Node paramNode);
  
  String toString(Node paramNode);
  
  XObject getVariableOrParam(QName paramQName) throws TransformerException;
  
  XPathContext getXPathContext() throws TransformerException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExpressionContext.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */