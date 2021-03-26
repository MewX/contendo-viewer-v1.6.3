package org.apache.batik.parser;

public interface PreserveAspectRatioHandler {
  void startPreserveAspectRatio() throws ParseException;
  
  void none() throws ParseException;
  
  void xMaxYMax() throws ParseException;
  
  void xMaxYMid() throws ParseException;
  
  void xMaxYMin() throws ParseException;
  
  void xMidYMax() throws ParseException;
  
  void xMidYMid() throws ParseException;
  
  void xMidYMin() throws ParseException;
  
  void xMinYMax() throws ParseException;
  
  void xMinYMid() throws ParseException;
  
  void xMinYMin() throws ParseException;
  
  void meet() throws ParseException;
  
  void slice() throws ParseException;
  
  void endPreserveAspectRatio() throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/PreserveAspectRatioHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */