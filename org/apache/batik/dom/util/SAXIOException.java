/*    */ package org.apache.batik.dom.util;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ public class SAXIOException
/*    */   extends IOException
/*    */ {
/*    */   protected SAXException saxe;
/*    */   
/*    */   public SAXIOException(SAXException saxe) {
/* 37 */     super(saxe.getMessage());
/* 38 */     this.saxe = saxe;
/*    */   }
/*    */   
/* 41 */   public SAXException getSAXException() { return this.saxe; } public Throwable getCause() {
/* 42 */     return this.saxe;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/SAXIOException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */