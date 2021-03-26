package org.apache.xpath;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;

public interface WhitespaceStrippingElementMatcher {
  boolean shouldStripWhiteSpace(XPathContext paramXPathContext, Element paramElement) throws TransformerException;
  
  boolean canStripWhiteSpace();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/WhitespaceStrippingElementMatcher.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */