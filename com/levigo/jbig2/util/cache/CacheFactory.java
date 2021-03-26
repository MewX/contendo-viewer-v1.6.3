/*    */ package com.levigo.jbig2.util.cache;
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
/*    */ 
/*    */ public class CacheFactory
/*    */ {
/*    */   private static CacheBridge cacheBridge;
/*    */   private static ClassLoader clsLoader;
/*    */   
/*    */   public static Cache getCache(ClassLoader paramClassLoader) {
/* 35 */     if (null == cacheBridge) {
/* 36 */       ServiceLookup serviceLookup = new ServiceLookup();
/* 37 */       Iterator<CacheBridge> iterator = serviceLookup.getServices(CacheBridge.class, paramClassLoader);
/*    */       
/* 39 */       if (!iterator.hasNext()) {
/* 40 */         throw new IllegalStateException("No implementation of " + CacheBridge.class + " was avaliable using META-INF/services lookup");
/*    */       }
/*    */       
/* 43 */       cacheBridge = iterator.next();
/*    */     } 
/* 45 */     return cacheBridge.getCache();
/*    */   }
/*    */   
/*    */   public static Cache getCache() {
/* 49 */     return getCache((clsLoader != null) ? clsLoader : CacheBridge.class.getClassLoader());
/*    */   }
/*    */   
/*    */   public static void setClassLoader(ClassLoader paramClassLoader) {
/* 53 */     clsLoader = paramClassLoader;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/cache/CacheFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */