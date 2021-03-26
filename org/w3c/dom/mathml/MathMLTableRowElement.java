package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLTableRowElement extends MathMLPresentationElement {
  String getRowalign();
  
  void setRowalign(String paramString);
  
  String getColumnalign();
  
  void setColumnalign(String paramString);
  
  String getGroupalign();
  
  void setGroupalign(String paramString);
  
  MathMLNodeList getCells();
  
  MathMLTableCellElement insertEmptyCell(int paramInt) throws DOMException;
  
  MathMLTableCellElement insertCell(MathMLTableCellElement paramMathMLTableCellElement, int paramInt) throws DOMException;
  
  MathMLTableCellElement setCell(MathMLTableCellElement paramMathMLTableCellElement, int paramInt);
  
  void deleteCell(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLTableRowElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */