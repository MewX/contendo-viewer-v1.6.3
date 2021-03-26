/*    */ package com.levigo.jbig2.util.log;
/*    */ 
/*    */ import java.util.logging.Logger;
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
/*    */ public class JDKLoggerBridge
/*    */   implements LoggerBridge
/*    */ {
/*    */   public Logger getLogger(Class<?> paramClass) {
/* 23 */     return new JDKLogger(Logger.getLogger(paramClass.getName()));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/log/JDKLoggerBridge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */