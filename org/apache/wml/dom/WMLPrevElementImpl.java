package org.apache.wml.dom;

import org.apache.wml.WMLPrevElement;

public class WMLPrevElementImpl extends WMLElementImpl implements WMLPrevElement {
  private static final long serialVersionUID = -1545713716925433554L;
  
  public WMLPrevElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/dom/WMLPrevElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */