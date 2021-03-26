package org.w3c.dom.html;

public interface HTMLOptionElement extends HTMLElement {
  HTMLFormElement getForm();
  
  boolean getDefaultSelected();
  
  void setDefaultSelected(boolean paramBoolean);
  
  String getText();
  
  int getIndex();
  
  void setIndex(int paramInt);
  
  boolean getDisabled();
  
  void setDisabled(boolean paramBoolean);
  
  String getLabel();
  
  void setLabel(String paramString);
  
  boolean getSelected();
  
  String getValue();
  
  void setValue(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/html/HTMLOptionElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */