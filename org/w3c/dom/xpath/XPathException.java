/*    */ package org.w3c.dom.xpath;
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
/*    */ public class XPathException
/*    */   extends RuntimeException
/*    */ {
/*    */   public short code;
/*    */   public static final short INVALID_EXPRESSION_ERR = 1;
/*    */   public static final short TYPE_ERR = 2;
/*    */   
/*    */   public XPathException(short code, String message) {
/* 22 */     super(message);
/* 23 */     this.code = code;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/xpath/XPathException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */