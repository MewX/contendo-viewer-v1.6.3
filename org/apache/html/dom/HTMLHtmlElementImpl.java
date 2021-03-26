package org.apache.html.dom;

import org.w3c.dom.html.HTMLHtmlElement;

public class HTMLHtmlElementImpl extends HTMLElementImpl implements HTMLHtmlElement {
  private static final long serialVersionUID = -4489734201536616166L;
  
  public String getVersion() {
    return capitalize(getAttribute("version"));
  }
  
  public void setVersion(String paramString) {
    setAttribute("version", paramString);
  }
  
  public HTMLHtmlElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/html/dom/HTMLHtmlElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */