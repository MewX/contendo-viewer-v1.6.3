package org.w3c.dom.html;

public interface HTMLTableElement extends HTMLElement {
  HTMLTableCaptionElement getCaption();
  
  void setCaption(HTMLTableCaptionElement paramHTMLTableCaptionElement);
  
  HTMLTableSectionElement getTHead();
  
  void setTHead(HTMLTableSectionElement paramHTMLTableSectionElement);
  
  HTMLTableSectionElement getTFoot();
  
  void setTFoot(HTMLTableSectionElement paramHTMLTableSectionElement);
  
  HTMLCollection getRows();
  
  HTMLCollection getTBodies();
  
  String getAlign();
  
  void setAlign(String paramString);
  
  String getBgColor();
  
  void setBgColor(String paramString);
  
  String getBorder();
  
  void setBorder(String paramString);
  
  String getCellPadding();
  
  void setCellPadding(String paramString);
  
  String getCellSpacing();
  
  void setCellSpacing(String paramString);
  
  String getFrame();
  
  void setFrame(String paramString);
  
  String getRules();
  
  void setRules(String paramString);
  
  String getSummary();
  
  void setSummary(String paramString);
  
  String getWidth();
  
  void setWidth(String paramString);
  
  HTMLElement createTHead();
  
  void deleteTHead();
  
  HTMLElement createTFoot();
  
  void deleteTFoot();
  
  HTMLElement createCaption();
  
  void deleteCaption();
  
  HTMLElement insertRow(int paramInt);
  
  void deleteRow(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/html/HTMLTableElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */