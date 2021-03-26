package org.apache.batik.parser;

public interface AngleHandler {
  void startAngle() throws ParseException;
  
  void angleValue(float paramFloat) throws ParseException;
  
  void deg() throws ParseException;
  
  void grad() throws ParseException;
  
  void rad() throws ParseException;
  
  void endAngle() throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AngleHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */