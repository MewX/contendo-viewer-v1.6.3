package org.apache.html.dom;

import org.w3c.dom.html.HTMLDirectoryElement;

public class HTMLDirectoryElementImpl extends HTMLElementImpl implements HTMLDirectoryElement {
  private static final long serialVersionUID = -1010376135190194454L;
  
  public boolean getCompact() {
    return getBinary("compact");
  }
  
  public void setCompact(boolean paramBoolean) {
    setAttribute("compact", paramBoolean);
  }
  
  public HTMLDirectoryElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/html/dom/HTMLDirectoryElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */