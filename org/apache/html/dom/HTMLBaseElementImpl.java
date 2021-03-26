package org.apache.html.dom;

import org.w3c.dom.html.HTMLBaseElement;

public class HTMLBaseElementImpl extends HTMLElementImpl implements HTMLBaseElement {
  private static final long serialVersionUID = -396648580810072153L;
  
  public String getHref() {
    return getAttribute("href");
  }
  
  public void setHref(String paramString) {
    setAttribute("href", paramString);
  }
  
  public String getTarget() {
    return getAttribute("target");
  }
  
  public void setTarget(String paramString) {
    setAttribute("target", paramString);
  }
  
  public HTMLBaseElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/html/dom/HTMLBaseElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */