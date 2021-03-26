package org.apache.wml.dom;

import org.apache.wml.WMLDOMImplementation;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;

public class WMLDOMImplementationImpl extends DOMImplementationImpl implements WMLDOMImplementation {
  static final DOMImplementationImpl singleton = new WMLDOMImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return (DOMImplementation)singleton;
  }
  
  protected CoreDocumentImpl createDocument(DocumentType paramDocumentType) {
    return (CoreDocumentImpl)new WMLDocumentImpl(paramDocumentType);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/dom/WMLDOMImplementationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */