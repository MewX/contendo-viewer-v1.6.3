package javax.xml.xpath;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.xml.sax.InputSource;

public interface XPath {
  void reset();
  
  void setXPathVariableResolver(XPathVariableResolver paramXPathVariableResolver);
  
  XPathVariableResolver getXPathVariableResolver();
  
  void setXPathFunctionResolver(XPathFunctionResolver paramXPathFunctionResolver);
  
  XPathFunctionResolver getXPathFunctionResolver();
  
  void setNamespaceContext(NamespaceContext paramNamespaceContext);
  
  NamespaceContext getNamespaceContext();
  
  XPathExpression compile(String paramString) throws XPathExpressionException;
  
  Object evaluate(String paramString, Object paramObject, QName paramQName) throws XPathExpressionException;
  
  String evaluate(String paramString, Object paramObject) throws XPathExpressionException;
  
  Object evaluate(String paramString, InputSource paramInputSource, QName paramQName) throws XPathExpressionException;
  
  String evaluate(String paramString, InputSource paramInputSource) throws XPathExpressionException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/xpath/XPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */