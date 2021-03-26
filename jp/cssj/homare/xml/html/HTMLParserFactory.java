/*    */ package jp.cssj.homare.xml.html;
/*    */ 
/*    */ import jp.cssj.homare.xml.ParserFactory;
/*    */ import jp.cssj.homare.xml.e;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HTMLParserFactory
/*    */   implements ParserFactory
/*    */ {
/*    */   public boolean match(String key) {
/* 13 */     return true;
/*    */   }
/*    */   
/*    */   public e createParser() {
/* 17 */     return new c();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/html/HTMLParserFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */