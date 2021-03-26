/*    */ package org.apache.xml.dtm;
/*    */ 
/*    */ import org.w3c.dom.DOMException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DTMDOMException
/*    */   extends DOMException
/*    */ {
/*    */   public DTMDOMException(short code, String message) {
/* 38 */     super(code, message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DTMDOMException(short code) {
/* 49 */     super(code, "");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/DTMDOMException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */