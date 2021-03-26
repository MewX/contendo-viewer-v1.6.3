package org.w3c.dom.mathml;

public interface MathMLTableCellElement extends MathMLPresentationContainer {
  String getRowspan();
  
  void setRowspan(String paramString);
  
  String getColumnspan();
  
  void setColumnspan(String paramString);
  
  String getRowalign();
  
  void setRowalign(String paramString);
  
  String getColumnalign();
  
  void setColumnalign(String paramString);
  
  String getGroupalign();
  
  void setGroupalign(String paramString);
  
  boolean getHasaligngroups();
  
  String getCellindex();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLTableCellElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */