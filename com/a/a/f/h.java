/*    */ package com.a.a.f;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class h implements b, d, e {
/*  7 */   HashMap<Object, SoftReference<Object>> a = new HashMap<Object, SoftReference<Object>>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object a(a<?, ?> cache, Object key) {
/* 14 */     Object val = null;
/* 15 */     SoftReference<Object> ref = this.a.remove(key);
/* 16 */     if (ref != null) {
/* 17 */       val = ref.get();
/*    */     }
/* 19 */     return val;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(a<?, ?> cache, Object key, Object entry) {
/* 27 */     this.a.put(key, new SoftReference(entry));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(a<?, ?> cache) {
/* 35 */     this.a.clear();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/f/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */