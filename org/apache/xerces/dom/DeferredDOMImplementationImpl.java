package org.apache.xerces.dom;

import org.w3c.dom.DOMImplementation;

public class DeferredDOMImplementationImpl extends DOMImplementationImpl {
  static final DeferredDOMImplementationImpl singleton = new DeferredDOMImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return singleton;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredDOMImplementationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */