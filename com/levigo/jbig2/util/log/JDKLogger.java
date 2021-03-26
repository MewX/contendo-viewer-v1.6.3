/*    */ package com.levigo.jbig2.util.log;
/*    */ 
/*    */ import java.util.logging.Level;
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
/*    */ public class JDKLogger
/*    */   implements Logger
/*    */ {
/*    */   final Logger wrappedLogger;
/*    */   
/*    */   public JDKLogger(Logger paramLogger) {
/* 26 */     this.wrappedLogger = paramLogger;
/*    */   }
/*    */   
/*    */   public void debug(String paramString) {
/* 30 */     this.wrappedLogger.log(Level.FINE, paramString);
/*    */   }
/*    */   
/*    */   public void debug(String paramString, Throwable paramThrowable) {
/* 34 */     this.wrappedLogger.log(Level.FINE, paramString, paramThrowable);
/*    */   }
/*    */   
/*    */   public void info(String paramString) {
/* 38 */     this.wrappedLogger.log(Level.INFO, paramString);
/*    */   }
/*    */   
/*    */   public void info(String paramString, Throwable paramThrowable) {
/* 42 */     this.wrappedLogger.log(Level.INFO, paramString, paramThrowable);
/*    */   }
/*    */   
/*    */   public void warn(String paramString) {
/* 46 */     this.wrappedLogger.log(Level.WARNING, paramString);
/*    */   }
/*    */   
/*    */   public void warn(String paramString, Throwable paramThrowable) {
/* 50 */     this.wrappedLogger.log(Level.WARNING, paramString, paramThrowable);
/*    */   }
/*    */   
/*    */   public void fatal(String paramString) {
/* 54 */     this.wrappedLogger.log(Level.SEVERE, paramString);
/*    */   }
/*    */   
/*    */   public void fatal(String paramString, Throwable paramThrowable) {
/* 58 */     this.wrappedLogger.log(Level.SEVERE, paramString, paramThrowable);
/*    */   }
/*    */   
/*    */   public void error(String paramString) {
/* 62 */     this.wrappedLogger.log(Level.SEVERE, paramString);
/*    */   }
/*    */   
/*    */   public void error(String paramString, Throwable paramThrowable) {
/* 66 */     this.wrappedLogger.log(Level.SEVERE, paramString, paramThrowable);
/*    */   }
/*    */   
/*    */   public boolean isDebugEnabled() {
/* 70 */     return this.wrappedLogger.isLoggable(Level.FINE);
/*    */   }
/*    */   
/*    */   public boolean isInfoEnabled() {
/* 74 */     return this.wrappedLogger.isLoggable(Level.INFO);
/*    */   }
/*    */   
/*    */   public boolean isWarnEnabled() {
/* 78 */     return this.wrappedLogger.isLoggable(Level.WARNING);
/*    */   }
/*    */   
/*    */   public boolean isFatalEnabled() {
/* 82 */     return this.wrappedLogger.isLoggable(Level.SEVERE);
/*    */   }
/*    */   
/*    */   public boolean isErrorEnabled() {
/* 86 */     return this.wrappedLogger.isLoggable(Level.SEVERE);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/log/JDKLogger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */