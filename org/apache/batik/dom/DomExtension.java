package org.apache.batik.dom;

public interface DomExtension {
  float getPriority();
  
  String getAuthor();
  
  String getContactAddress();
  
  String getURL();
  
  String getDescription();
  
  void registerTags(ExtensibleDOMImplementation paramExtensibleDOMImplementation);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/DomExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */