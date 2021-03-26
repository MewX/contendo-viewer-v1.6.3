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
/*    */ public class DefaultAngleHandler
/*    */   implements AngleHandler
/*    */ {
/* 31 */   public static final AngleHandler INSTANCE = new DefaultAngleHandler();
/*    */   
/*    */   public void startAngle() throws ParseException {}
/*    */   
/*    */   public void angleValue(float v) throws ParseException {}
/*    */   
/*    */   public void deg() throws ParseException {}
/*    */   
/*    */   public void grad() throws ParseException {}
/*    */   
/*    */   public void rad() throws ParseException {}
/*    */   
/*    */   public void endAngle() throws ParseException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/DefaultAngleHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */