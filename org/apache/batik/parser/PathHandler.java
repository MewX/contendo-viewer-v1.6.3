package org.apache.batik.parser;

public interface PathHandler {
  void startPath() throws ParseException;
  
  void endPath() throws ParseException;
  
  void movetoRel(float paramFloat1, float paramFloat2) throws ParseException;
  
  void movetoAbs(float paramFloat1, float paramFloat2) throws ParseException;
  
  void closePath() throws ParseException;
  
  void linetoRel(float paramFloat1, float paramFloat2) throws ParseException;
  
  void linetoAbs(float paramFloat1, float paramFloat2) throws ParseException;
  
  void linetoHorizontalRel(float paramFloat) throws ParseException;
  
  void linetoHorizontalAbs(float paramFloat) throws ParseException;
  
  void linetoVerticalRel(float paramFloat) throws ParseException;
  
  void linetoVerticalAbs(float paramFloat) throws ParseException;
  
  void curvetoCubicRel(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) throws ParseException;
  
  void curvetoCubicAbs(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) throws ParseException;
  
  void curvetoCubicSmoothRel(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) throws ParseException;
  
  void curvetoCubicSmoothAbs(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) throws ParseException;
  
  void curvetoQuadraticRel(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) throws ParseException;
  
  void curvetoQuadraticAbs(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) throws ParseException;
  
  void curvetoQuadraticSmoothRel(float paramFloat1, float paramFloat2) throws ParseException;
  
  void curvetoQuadraticSmoothAbs(float paramFloat1, float paramFloat2) throws ParseException;
  
  void arcRel(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean1, boolean paramBoolean2, float paramFloat4, float paramFloat5) throws ParseException;
  
  void arcAbs(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean1, boolean paramBoolean2, float paramFloat4, float paramFloat5) throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/PathHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */