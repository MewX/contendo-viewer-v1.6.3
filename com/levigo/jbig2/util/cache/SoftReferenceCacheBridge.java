/*    */ package com.levigo.jbig2.util.cache;
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
/*    */ public class SoftReferenceCacheBridge
/*    */   implements CacheBridge
/*    */ {
/* 22 */   private static final SoftReferenceCache cache = new SoftReferenceCache();
/*    */   
/*    */   public Cache getCache() {
/* 25 */     return cache;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/cache/SoftReferenceCacheBridge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */