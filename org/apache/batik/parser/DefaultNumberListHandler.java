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
/*    */ public class DefaultNumberListHandler
/*    */   implements NumberListHandler
/*    */ {
/* 30 */   public static final NumberListHandler INSTANCE = new DefaultNumberListHandler();
/*    */   
/*    */   public void startNumberList() throws ParseException {}
/*    */   
/*    */   public void endNumberList() throws ParseException {}
/*    */   
/*    */   public void startNumber() throws ParseException {}
/*    */   
/*    */   public void numberValue(float v) throws ParseException {}
/*    */   
/*    */   public void endNumber() throws ParseException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/DefaultNumberListHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */