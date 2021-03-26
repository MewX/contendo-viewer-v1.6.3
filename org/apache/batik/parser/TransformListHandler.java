package org.apache.batik.parser;

public interface TransformListHandler {
  void startTransformList() throws ParseException;
  
  void matrix(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) throws ParseException;
  
  void rotate(float paramFloat) throws ParseException;
  
  void rotate(float paramFloat1, float paramFloat2, float paramFloat3) throws ParseException;
  
  void translate(float paramFloat) throws ParseException;
  
  void translate(float paramFloat1, float paramFloat2) throws ParseException;
  
  void scale(float paramFloat) throws ParseException;
  
  void scale(float paramFloat1, float paramFloat2) throws ParseException;
  
  void skewX(float paramFloat) throws ParseException;
  
  void skewY(float paramFloat) throws ParseException;
  
  void endTransformList() throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/TransformListHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */