package org.apache.xerces.dom;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;

public class PSVIDOMImplementationImpl extends DOMImplementationImpl {
  static final PSVIDOMImplementationImpl singleton = new PSVIDOMImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return singleton;
  }
  
  public boolean hasFeature(String paramString1, String paramString2) {
    return (super.hasFeature(paramString1, paramString2) || paramString1.equalsIgnoreCase("psvi"));
  }
  
  protected CoreDocumentImpl createDocument(DocumentType paramDocumentType) {
    return new PSVIDocumentImpl(paramDocumentType);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/PSVIDOMImplementationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */