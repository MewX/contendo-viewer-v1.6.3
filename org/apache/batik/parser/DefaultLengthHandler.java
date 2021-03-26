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
/*    */ public class DefaultLengthHandler
/*    */   implements LengthHandler
/*    */ {
/* 31 */   public static final LengthHandler INSTANCE = new DefaultLengthHandler();
/*    */   
/*    */   public void startLength() throws ParseException {}
/*    */   
/*    */   public void lengthValue(float v) throws ParseException {}
/*    */   
/*    */   public void em() throws ParseException {}
/*    */   
/*    */   public void ex() throws ParseException {}
/*    */   
/*    */   public void in() throws ParseException {}
/*    */   
/*    */   public void cm() throws ParseException {}
/*    */   
/*    */   public void mm() throws ParseException {}
/*    */   
/*    */   public void pc() throws ParseException {}
/*    */   
/*    */   public void pt() throws ParseException {}
/*    */   
/*    */   public void px() throws ParseException {}
/*    */   
/*    */   public void percentage() throws ParseException {}
/*    */   
/*    */   public void endLength() throws ParseException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/DefaultLengthHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */