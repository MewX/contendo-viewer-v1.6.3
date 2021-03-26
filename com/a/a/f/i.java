/*    */ package com.a.a.f;
/*    */ 
/*    */ import java.util.WeakHashMap;
/*    */ 
/*    */ public class i implements b, d, e {
/*  6 */   WeakHashMap<Object, Object> a = new WeakHashMap<Object, Object>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object a(a<?, ?> cache, Object key) {
/* 13 */     return this.a.remove(key);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(a<?, ?> cache, Object key, Object entry) {
/* 21 */     this.a.put(key, entry);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(a<?, ?> cache) {
/* 29 */     this.a.clear();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/f/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */