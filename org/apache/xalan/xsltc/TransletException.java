/*    */ package org.apache.xalan.xsltc;
/*    */ 
/*    */ import org.xml.sax.SAXException;
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
/*    */ public final class TransletException
/*    */   extends SAXException
/*    */ {
/*    */   public TransletException() {
/* 32 */     super("Translet error");
/*    */   }
/*    */   
/*    */   public TransletException(Exception e) {
/* 36 */     super(e.toString());
/*    */   }
/*    */   
/*    */   public TransletException(String message) {
/* 40 */     super(message);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/TransletException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */