/*    */ package com.a.a.f;
/*    */ 
/*    */ import java.util.AbstractSet;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import java.util.WeakHashMap;
/*    */ 
/*    */ public class j<E>
/*    */   extends AbstractSet<E>
/*    */   implements Set<E> {
/*    */   private WeakHashMap<E, Object> a;
/* 13 */   private static final Object b = new Object();
/*    */   
/*    */   public j() {
/* 16 */     this.a = new WeakHashMap<E, Object>();
/*    */   }
/*    */   
/*    */   public j(Collection<? extends E> c) {
/* 20 */     this.a = new WeakHashMap<E, Object>(Math.max((int)(c.size() / 0.75F) + 1, 16));
/* 21 */     addAll(c);
/*    */   }
/*    */   
/*    */   public j(int initialCapacity, float loadFactor) {
/* 25 */     this.a = new WeakHashMap<E, Object>(initialCapacity, loadFactor);
/*    */   }
/*    */   
/*    */   public j(int initialCapacity) {
/* 29 */     this.a = new WeakHashMap<E, Object>(initialCapacity);
/*    */   }
/*    */   
/*    */   public Iterator<E> iterator() {
/* 33 */     return this.a.keySet().iterator();
/*    */   }
/*    */   
/*    */   public int size() {
/* 37 */     return this.a.size();
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 41 */     return this.a.isEmpty();
/*    */   }
/*    */   
/*    */   public boolean contains(Object o) {
/* 45 */     return this.a.containsKey(o);
/*    */   }
/*    */   
/*    */   public boolean add(E e) {
/* 49 */     return (this.a.put(e, b) == null);
/*    */   }
/*    */   
/*    */   public boolean remove(Object o) {
/* 53 */     return (this.a.remove(o) == b);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 57 */     this.a.clear();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/f/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */