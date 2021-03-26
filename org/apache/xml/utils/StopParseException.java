/*    */ package org.apache.xml.utils;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StopParseException
/*    */   extends SAXException
/*    */ {
/*    */   StopParseException() {
/* 36 */     super("Stylesheet PIs found, stop the parse");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StopParseException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */