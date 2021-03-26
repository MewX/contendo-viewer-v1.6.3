/*    */ package jp.cssj.homare.impl.ua.pdf;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import jp.cssj.homare.ua.UserAgentFactory;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ public class PDFUserAgentFactory
/*    */   implements UserAgentFactory {
/* 10 */   public static String MIME_TYPE = "application/pdf";
/*    */   
/*    */   public boolean match(String key) {
/* 13 */     return key.equals(MIME_TYPE);
/*    */   }
/*    */   
/*    */   public Iterator<UserAgentFactory.a> types() {
/* 17 */     return Arrays.<UserAgentFactory.a>asList(new UserAgentFactory.a[] { new UserAgentFactory.a("PDF", MIME_TYPE, "pdf") }).iterator();
/*    */   }
/*    */   
/*    */   public m createUserAgent() {
/* 21 */     return (m)new a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/pdf/PDFUserAgentFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */