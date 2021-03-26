package org.apache.html.dom;

import org.apache.xerces.dom.DOMImplementationImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLDOMImplementation;
import org.w3c.dom.html.HTMLDocument;

public class HTMLDOMImplementationImpl extends DOMImplementationImpl implements HTMLDOMImplementation {
  private static final HTMLDOMImplementation _instance = new HTMLDOMImplementationImpl();
  
  public final HTMLDocument createHTMLDocument(String paramString) throws DOMException {
    if (paramString == null)
      throw new NullPointerException("HTM014 Argument 'title' is null."); 
    HTMLDocumentImpl hTMLDocumentImpl = new HTMLDocumentImpl();
    hTMLDocumentImpl.setTitle(paramString);
    return hTMLDocumentImpl;
  }
  
  public static HTMLDOMImplementation getHTMLDOMImplementation() {
    return _instance;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/html/dom/HTMLDOMImplementationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */