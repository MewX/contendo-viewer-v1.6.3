package org.apache.batik.parser;

public interface NumberListHandler {
  void startNumberList() throws ParseException;
  
  void endNumberList() throws ParseException;
  
  void startNumber() throws ParseException;
  
  void endNumber() throws ParseException;
  
  void numberValue(float paramFloat) throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/NumberListHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */