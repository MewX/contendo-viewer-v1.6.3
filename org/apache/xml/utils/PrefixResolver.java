package org.apache.xml.utils;

import org.w3c.dom.Node;

public interface PrefixResolver {
  String getNamespaceForPrefix(String paramString);
  
  String getNamespaceForPrefix(String paramString, Node paramNode);
  
  String getBaseIdentifier();
  
  boolean handlesNullPrefixes();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/PrefixResolver.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */