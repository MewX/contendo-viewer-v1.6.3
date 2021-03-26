package org.w3c.dom;

public interface NameList {
  String getName(int paramInt);
  
  String getNamespaceURI(int paramInt);
  
  int getLength();
  
  boolean contains(String paramString);
  
  boolean containsNS(String paramString1, String paramString2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/NameList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */