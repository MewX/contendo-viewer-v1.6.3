package org.apache.batik.parser;

public interface PointsHandler {
  void startPoints() throws ParseException;
  
  void point(float paramFloat1, float paramFloat2) throws ParseException;
  
  void endPoints() throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/PointsHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */