package org.w3c.dom.html;

public interface HTMLSelectElement extends HTMLElement {
  String getType();
  
  int getSelectedIndex();
  
  void setSelectedIndex(int paramInt);
  
  String getValue();
  
  void setValue(String paramString);
  
  int getLength();
  
  HTMLFormElement getForm();
  
  HTMLCollection getOptions();
  
  boolean getDisabled();
  
  void setDisabled(boolean paramBoolean);
  
  boolean getMultiple();
  
  void setMultiple(boolean paramBoolean);
  
  String getName();
  
  void setName(String paramString);
  
  int getSize();
  
  void setSize(int paramInt);
  
  int getTabIndex();
  
  void setTabIndex(int paramInt);
  
  void add(HTMLElement paramHTMLElement1, HTMLElement paramHTMLElement2);
  
  void remove(int paramInt);
  
  void blur();
  
  void focus();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/html/HTMLSelectElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */