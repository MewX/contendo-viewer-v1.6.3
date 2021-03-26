package org.apache.wml.dom;

import org.apache.wml.WMLNoopElement;

public class WMLNoopElementImpl extends WMLElementImpl implements WMLNoopElement {
  private static final long serialVersionUID = -1581314434256075931L;
  
  public WMLNoopElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setClassName(String paramString) {
    setAttribute("class", paramString);
  }
  
  public String getClassName() {
    return getAttribute("class");
  }
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/dom/WMLNoopElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */