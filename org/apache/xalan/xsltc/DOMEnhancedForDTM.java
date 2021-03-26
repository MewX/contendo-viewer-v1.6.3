package org.apache.xalan.xsltc;

public interface DOMEnhancedForDTM extends DOM {
  short[] getMapping(String[] paramArrayOfString1, String[] paramArrayOfString2, int[] paramArrayOfint);
  
  int[] getReverseMapping(String[] paramArrayOfString1, String[] paramArrayOfString2, int[] paramArrayOfint);
  
  short[] getNamespaceMapping(String[] paramArrayOfString);
  
  short[] getReverseNamespaceMapping(String[] paramArrayOfString);
  
  String getDocumentURI();
  
  void setDocumentURI(String paramString);
  
  int getExpandedTypeID2(int paramInt);
  
  boolean hasDOMSource();
  
  int getElementById(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/DOMEnhancedForDTM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */