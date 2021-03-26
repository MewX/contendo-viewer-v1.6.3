/*    */ package jp.cssj.homare.xml.xerces;
/*    */ 
/*    */ import jp.cssj.b.a.g;
/*    */ import jp.cssj.homare.xml.ParserFactory;
/*    */ import jp.cssj.homare.xml.e;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XMLParserFactory
/*    */   implements ParserFactory
/*    */ {
/*    */   public boolean match(String key) {
/* 13 */     String mimeType = key;
/* 14 */     if (g.a("text/xml", mimeType) || g.a("application/xml", mimeType) || 
/* 15 */       g.a("text/xhtml", mimeType) || g.a("application/xhtml", mimeType) || 
/* 16 */       g.a("application/xhtml+xml", mimeType)) {
/* 17 */       return true;
/*    */     }
/* 19 */     return false;
/*    */   }
/*    */   
/*    */   public e createParser() {
/* 23 */     return new a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/xerces/XMLParserFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */