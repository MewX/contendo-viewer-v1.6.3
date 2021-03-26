/*    */ package com.levigo.jbig2.util.cache;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import java.util.HashMap;
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
/*    */ public class SoftReferenceCache
/*    */   implements Cache
/*    */ {
/* 25 */   private HashMap<Object, SoftReference<?>> cache = new HashMap<>();
/*    */   
/*    */   public Object put(Object paramObject1, Object paramObject2, int paramInt) {
/* 28 */     SoftReference<?> softReference1 = new SoftReference(paramObject2);
/* 29 */     SoftReference<?> softReference2 = this.cache.put(paramObject1, softReference1);
/* 30 */     return getValueNullSafe(softReference2);
/*    */   }
/*    */   
/*    */   public Object get(Object paramObject) {
/* 34 */     SoftReference<?> softReference = this.cache.get(paramObject);
/* 35 */     return getValueNullSafe(softReference);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 39 */     this.cache.clear();
/*    */   }
/*    */   
/*    */   public Object remove(Object paramObject) {
/* 43 */     SoftReference<?> softReference = this.cache.remove(paramObject);
/* 44 */     return getValueNullSafe(softReference);
/*    */   }
/*    */   
/*    */   private Object getValueNullSafe(SoftReference<?> paramSoftReference) {
/* 48 */     return (paramSoftReference == null) ? null : paramSoftReference.get();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/cache/SoftReferenceCache.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */