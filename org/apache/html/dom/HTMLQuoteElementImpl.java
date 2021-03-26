package org.apache.html.dom;

import org.w3c.dom.html.HTMLQuoteElement;

public class HTMLQuoteElementImpl extends HTMLElementImpl implements HTMLQuoteElement {
  private static final long serialVersionUID = -67544811597906132L;
  
  public String getCite() {
    return getAttribute("cite");
  }
  
  public void setCite(String paramString) {
    setAttribute("cite", paramString);
  }
  
  public HTMLQuoteElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/html/dom/HTMLQuoteElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */