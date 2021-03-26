/*    */ package jp.cssj.sakae.sac.parser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChangeCharacterEncodingException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 0L;
/*    */   private final String encoding;
/*    */   
/*    */   public ChangeCharacterEncodingException(String encoding) {
/* 14 */     this.encoding = encoding;
/*    */   }
/*    */   
/*    */   public String getCharacterEncoding() {
/* 18 */     return this.encoding;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/ChangeCharacterEncodingException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */