package org.w3c.dom.mathml;

import org.w3c.dom.DOMException;

public interface MathMLTableElement extends MathMLPresentationElement {
  String getAlign();
  
  void setAlign(String paramString);
  
  String getRowalign();
  
  void setRowalign(String paramString);
  
  String getColumnalign();
  
  void setColumnalign(String paramString);
  
  String getGroupalign();
  
  void setGroupalign(String paramString);
  
  String getAlignmentscope();
  
  void setAlignmentscope(String paramString);
  
  String getColumnwidth();
  
  void setColumnwidth(String paramString);
  
  String getWidth();
  
  void setWidth(String paramString);
  
  String getRowspacing();
  
  void setRowspacing(String paramString);
  
  String getColumnspacing();
  
  void setColumnspacing(String paramString);
  
  String getRowlines();
  
  void setRowlines(String paramString);
  
  String getColumnlines();
  
  void setColumnlines(String paramString);
  
  String getFrame();
  
  void setFrame(String paramString);
  
  String getFramespacing();
  
  void setFramespacing(String paramString);
  
  String getEqualrows();
  
  void setEqualrows(String paramString);
  
  String getEqualcolumns();
  
  void setEqualcolumns(String paramString);
  
  String getDisplaystyle();
  
  void setDisplaystyle(String paramString);
  
  String getSide();
  
  void setSide(String paramString);
  
  String getMinlabelspacing();
  
  void setMinlabelspacing(String paramString);
  
  MathMLNodeList getRows();
  
  MathMLTableRowElement insertEmptyRow(long paramLong) throws DOMException;
  
  MathMLLabeledRowElement insertEmptyLabeledRow(long paramLong) throws DOMException;
  
  MathMLTableRowElement getRow(long paramLong);
  
  MathMLTableRowElement insertRow(long paramLong, MathMLTableRowElement paramMathMLTableRowElement) throws DOMException;
  
  MathMLTableRowElement setRow(long paramLong, MathMLTableRowElement paramMathMLTableRowElement) throws DOMException;
  
  void deleteRow(long paramLong) throws DOMException;
  
  MathMLTableRowElement removeRow(long paramLong) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/mathml/MathMLTableElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */