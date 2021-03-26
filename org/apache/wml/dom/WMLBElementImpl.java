package org.apache.wml.dom;

import org.apache.wml.WMLBElement;

public class WMLBElementImpl extends WMLElementImpl implements WMLBElement {
  private static final long serialVersionUID = -758504371498228671L;
  
  public WMLBElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setClassName(String paramString) {
    setAttribute("class", paramString);
  }
  
  public String getClassName() {
    return getAttribute("class");
  }
  
  public void setXmlLang(String paramString) {
    setAttribute("xml:lang", paramString);
  }
  
  public String getXmlLang() {
    return getAttribute("xml:lang");
  }
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/dom/WMLBElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */