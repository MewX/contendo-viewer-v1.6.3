package javax.xml.xpath;

import java.util.List;

public interface XPathFunction {
  Object evaluate(List paramList) throws XPathFunctionException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/xpath/XPathFunction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */