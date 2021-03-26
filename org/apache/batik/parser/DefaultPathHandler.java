/*    */ package org.apache.batik.parser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultPathHandler
/*    */   implements PathHandler
/*    */ {
/* 32 */   public static final PathHandler INSTANCE = new DefaultPathHandler();
/*    */   
/*    */   public void startPath() throws ParseException {}
/*    */   
/*    */   public void endPath() throws ParseException {}
/*    */   
/*    */   public void movetoRel(float x, float y) throws ParseException {}
/*    */   
/*    */   public void movetoAbs(float x, float y) throws ParseException {}
/*    */   
/*    */   public void closePath() throws ParseException {}
/*    */   
/*    */   public void linetoRel(float x, float y) throws ParseException {}
/*    */   
/*    */   public void linetoAbs(float x, float y) throws ParseException {}
/*    */   
/*    */   public void linetoHorizontalRel(float x) throws ParseException {}
/*    */   
/*    */   public void linetoHorizontalAbs(float x) throws ParseException {}
/*    */   
/*    */   public void linetoVerticalRel(float y) throws ParseException {}
/*    */   
/*    */   public void linetoVerticalAbs(float y) throws ParseException {}
/*    */   
/*    */   public void curvetoCubicRel(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {}
/*    */   
/*    */   public void curvetoCubicAbs(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {}
/*    */   
/*    */   public void curvetoCubicSmoothRel(float x2, float y2, float x, float y) throws ParseException {}
/*    */   
/*    */   public void curvetoCubicSmoothAbs(float x2, float y2, float x, float y) throws ParseException {}
/*    */   
/*    */   public void curvetoQuadraticRel(float x1, float y1, float x, float y) throws ParseException {}
/*    */   
/*    */   public void curvetoQuadraticAbs(float x1, float y1, float x, float y) throws ParseException {}
/*    */   
/*    */   public void curvetoQuadraticSmoothRel(float x, float y) throws ParseException {}
/*    */   
/*    */   public void curvetoQuadraticSmoothAbs(float x, float y) throws ParseException {}
/*    */   
/*    */   public void arcRel(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {}
/*    */   
/*    */   public void arcAbs(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/DefaultPathHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */