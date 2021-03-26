/*    */ package com.levigo.jbig2.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.ServiceLoader;
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
/*    */ public class ServiceLookup<B>
/*    */ {
/*    */   public Iterator<B> getServices(Class<B> paramClass) {
/* 26 */     return getServices(paramClass, null);
/*    */   }
/*    */   
/*    */   public Iterator<B> getServices(Class<B> paramClass, ClassLoader paramClassLoader) {
/* 30 */     Iterator<B> iterator = ServiceLoader.<B>load(paramClass).iterator();
/*    */     
/* 32 */     if (!iterator.hasNext()) {
/* 33 */       iterator = ServiceLoader.<B>load(paramClass, paramClass.getClass().getClassLoader()).iterator();
/*    */     }
/*    */     
/* 36 */     if (!iterator.hasNext() && paramClassLoader != null) {
/* 37 */       iterator = ServiceLoader.<B>load(paramClass, paramClassLoader).iterator();
/*    */     }
/*    */     
/* 40 */     return iterator;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/ServiceLookup.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */