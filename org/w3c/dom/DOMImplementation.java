package org.w3c.dom;

public interface DOMImplementation {
  boolean hasFeature(String paramString1, String paramString2);
  
  DocumentType createDocumentType(String paramString1, String paramString2, String paramString3) throws DOMException;
  
  Document createDocument(String paramString1, String paramString2, DocumentType paramDocumentType) throws DOMException;
  
  Object getFeature(String paramString1, String paramString2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/DOMImplementation.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */