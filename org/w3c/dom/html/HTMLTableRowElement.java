package org.w3c.dom.html;

public interface HTMLTableRowElement extends HTMLElement {
  int getRowIndex();
  
  void setRowIndex(int paramInt);
  
  int getSectionRowIndex();
  
  void setSectionRowIndex(int paramInt);
  
  HTMLCollection getCells();
  
  void setCells(HTMLCollection paramHTMLCollection);
  
  String getAlign();
  
  void setAlign(String paramString);
  
  String getBgColor();
  
  void setBgColor(String paramString);
  
  String getCh();
  
  void setCh(String paramString);
  
  String getChOff();
  
  void setChOff(String paramString);
  
  String getVAlign();
  
  void setVAlign(String paramString);
  
  HTMLElement insertCell(int paramInt);
  
  void deleteCell(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/html/HTMLTableRowElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */