package org.apache.html.dom;

import org.w3c.dom.html.HTMLHeadingElement;

public class HTMLHeadingElementImpl extends HTMLElementImpl implements HTMLHeadingElement {
  private static final long serialVersionUID = 6605827989383069095L;
  
  public String getAlign() {
    return getCapitalized("align");
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public HTMLHeadingElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/html/dom/HTMLHeadingElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */