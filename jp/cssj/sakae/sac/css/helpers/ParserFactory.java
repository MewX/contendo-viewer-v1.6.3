/*    */ package jp.cssj.sakae.sac.css.helpers;
/*    */ 
/*    */ import jp.cssj.sakae.sac.css.Parser;
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
/* 29 */     String className = System.getProperty("jp.cssj.sakae.sac.css.parser");
/* 30 */     if (className == null) {
/* 31 */       throw new NullPointerException("No value for sac.parser property");
/*    */     }
/* 33 */     return (Parser)Class.forName(className).newInstance();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/helpers/ParserFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */