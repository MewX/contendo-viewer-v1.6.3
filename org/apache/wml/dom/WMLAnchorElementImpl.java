package org.apache.wml.dom;

import org.apache.wml.WMLAnchorElement;

public class WMLAnchorElementImpl extends WMLElementImpl implements WMLAnchorElement {
  private static final long serialVersionUID = 5720492496046133176L;
  
  public WMLAnchorElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
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
  
  public void setTitle(String paramString) {
    setAttribute("title", paramString);
  }
  
  public String getTitle() {
    return getAttribute("title");
  }
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/wml/dom/WMLAnchorElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */