package org.apache.xpath;

import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.PrefixResolver;

public interface XPathFactory {
  XPath create(String paramString, SourceLocator paramSourceLocator, PrefixResolver paramPrefixResolver, int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/XPathFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */