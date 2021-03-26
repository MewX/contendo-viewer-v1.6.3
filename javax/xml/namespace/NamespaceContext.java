package javax.xml.namespace;

import java.util.Iterator;

public interface NamespaceContext {
  String getNamespaceURI(String paramString);
  
  String getPrefix(String paramString);
  
  Iterator getPrefixes(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/namespace/NamespaceContext.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */