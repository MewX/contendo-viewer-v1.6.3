/*    */ package com.levigo.jbig2.util.log;
/*    */ 
/*    */ import com.levigo.jbig2.util.ServiceLookup;
/*    */ import java.util.Iterator;
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
/*    */ public class LoggerFactory
/*    */ {
/*    */   private static LoggerBridge loggerBridge;
/*    */   private static ClassLoader clsLoader;
/*    */   
/*    */   public static Logger getLogger(Class<?> paramClass, ClassLoader paramClassLoader) {
/* 34 */     if (null == loggerBridge) {
/* 35 */       ServiceLookup serviceLookup = new ServiceLookup();
/* 36 */       Iterator<LoggerBridge> iterator = serviceLookup.getServices(LoggerBridge.class, paramClassLoader);
/*    */       
/* 38 */       if (!iterator.hasNext()) {
/* 39 */         throw new IllegalStateException("No implementation of " + LoggerBridge.class + " was avaliable using META-INF/services lookup");
/*    */       }
/*    */       
/* 42 */       loggerBridge = iterator.next();
/*    */     } 
/* 44 */     return loggerBridge.getLogger(paramClass);
/*    */   }
/*    */   
/*    */   public static Logger getLogger(Class<?> paramClass) {
/* 48 */     return getLogger(paramClass, (clsLoader != null) ? clsLoader : LoggerBridge.class.getClassLoader());
/*    */   }
/*    */   
/*    */   public static void setClassLoader(ClassLoader paramClassLoader) {
/* 52 */     clsLoader = paramClassLoader;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/log/LoggerFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */