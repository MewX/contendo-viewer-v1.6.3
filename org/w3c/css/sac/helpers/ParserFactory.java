/*    */ package org.w3c.css.sac.helpers;
/*    */ 
/*    */ import org.w3c.css.sac.Parser;
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
/*    */ public class ParserFactory
/*    */ {
/*    */   public Parser makeParser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NullPointerException, ClassCastException {
/* 33 */     String str = System.getProperty("org.w3c.css.sac.parser");
/* 34 */     if (str == null) {
/* 35 */       throw new NullPointerException("No value for sac.parser property");
/*    */     }
/* 37 */     return (Parser)Class.forName(str).newInstance();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/helpers/ParserFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */