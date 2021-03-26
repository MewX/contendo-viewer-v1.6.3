package org.apache.batik.parser;

public interface LengthHandler {
  void startLength() throws ParseException;
  
  void lengthValue(float paramFloat) throws ParseException;
  
  void em() throws ParseException;
  
  void ex() throws ParseException;
  
  void in() throws ParseException;
  
  void cm() throws ParseException;
  
  void mm() throws ParseException;
  
  void pc() throws ParseException;
  
  void pt() throws ParseException;
  
  void px() throws ParseException;
  
  void percentage() throws ParseException;
  
  void endLength() throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/LengthHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */