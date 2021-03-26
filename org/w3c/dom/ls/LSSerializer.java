package org.w3c.dom.ls;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public interface LSSerializer {
  DOMConfiguration getDomConfig();
  
  String getNewLine();
  
  void setNewLine(String paramString);
  
  LSSerializerFilter getFilter();
  
  void setFilter(LSSerializerFilter paramLSSerializerFilter);
  
  boolean write(Node paramNode, LSOutput paramLSOutput) throws LSException;
  
  boolean writeToURI(Node paramNode, String paramString) throws LSException;
  
  String writeToString(Node paramNode) throws DOMException, LSException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/ls/LSSerializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */