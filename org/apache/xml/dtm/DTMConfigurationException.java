/*    */ package org.apache.xml.dtm;
/*    */ 
/*    */ import javax.xml.transform.SourceLocator;
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
/*    */ public class DTMConfigurationException
/*    */   extends DTMException
/*    */ {
/*    */   public DTMConfigurationException() {
/* 33 */     super("Configuration Error");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DTMConfigurationException(String msg) {
/* 43 */     super(msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DTMConfigurationException(Throwable e) {
/* 54 */     super(e);
/*    */   }
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
/*    */   public DTMConfigurationException(String msg, Throwable e) {
/* 67 */     super(msg, e);
/*    */   }
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
/*    */   public DTMConfigurationException(String message, SourceLocator locator) {
/* 82 */     super(message, locator);
/*    */   }
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
/*    */   public DTMConfigurationException(String message, SourceLocator locator, Throwable e) {
/* 96 */     super(message, locator, e);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/DTMConfigurationException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */