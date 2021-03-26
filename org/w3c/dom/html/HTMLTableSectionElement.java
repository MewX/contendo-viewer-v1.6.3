package org.w3c.dom.html;

public interface HTMLTableSectionElement extends HTMLElement {
  String getAlign();
  
  void setAlign(String paramString);
  
  String getCh();
  
  void setCh(String paramString);
  
  String getChOff();
  
  void setChOff(String paramString);
  
  String getVAlign();
  
  void setVAlign(String paramString);
  
  HTMLCollection getRows();
  
  HTMLElement insertRow(int paramInt);
  
  void deleteRow(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/html/HTMLTableSectionElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */