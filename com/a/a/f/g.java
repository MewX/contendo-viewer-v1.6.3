/*     */ package com.a.a.f;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class g<K, V>
/*     */   implements a<K, V> {
/*     */   private static final int a = -1;
/*  13 */   private final HashMap<K, V> b = new HashMap<K, V>();
/*  14 */   private final LinkedList<K> c = new LinkedList<K>();
/*  15 */   private int d = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private c e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g() {
/*  41 */     this.e = null; } public g(int nums) { this.e = null; this.d = nums; } public g(int nums, c listener) { this.e = null;
/*     */     this.d = nums;
/*     */     a(listener); }
/*     */ 
/*     */   
/*     */   public void a(int limit) {
/*  47 */     this.d = limit;
/*  48 */     synchronized (this.c) {
/*  49 */       d();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V get(Object key) {
/*  68 */     synchronized (this.c) {
/*  69 */       V value = this.b.get(key);
/*  70 */       if (value != null) {
/*  71 */         a((K)key);
/*     */       }
/*  73 */       else if (this.e != null && this.e instanceof d) {
/*  74 */         value = (V)((d)this.e).a(this, key);
/*  75 */         if (value != null) {
/*  76 */           put((K)key, value);
/*     */         }
/*     */       } 
/*     */       
/*  80 */       return value;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V put(K key, V value) {
/*  89 */     synchronized (this.c) {
/*  90 */       V prev = this.b.put(key, value);
/*  91 */       if (prev == null) {
/*  92 */         this.c.addFirst(key);
/*     */       } else {
/*  94 */         a(key);
/*     */       } 
/*     */ 
/*     */       
/*  98 */       d();
/*  99 */       return prev;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void d() {
/* 109 */     if (this.d < 0) {
/*     */       return;
/*     */     }
/* 112 */     while (size() > this.d) {
/* 113 */       K key = this.c.removeLast();
/* 114 */       V entry = this.b.remove(key);
/* 115 */       if (this.e != null && this.e instanceof e) {
/* 116 */         ((e)this.e).a(this, key, entry);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g<K, V> a(c listener) {
/* 127 */     this.e = listener;
/* 128 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(K key) {
/* 137 */     this.c.remove(key);
/* 138 */     this.c.addFirst(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 146 */     return this.b.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 154 */     return this.b.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 162 */     return this.b.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 170 */     return this.b.containsValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V remove(Object key) {
/* 178 */     synchronized (this.c) {
/* 179 */       V value = this.b.remove(key);
/* 180 */       if (value != null) {
/* 181 */         this.c.remove(key);
/*     */       }
/* 183 */       return value;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V b() {
/* 191 */     synchronized (this.c) {
/* 192 */       if (!this.c.isEmpty()) {
/* 193 */         Object key = this.c.removeLast();
/* 194 */         return this.b.remove(key);
/*     */       } 
/*     */     } 
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> m) {
/* 205 */     for (K key : m.keySet()) {
/* 206 */       put(key, m.get(key));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 215 */     synchronized (this.c) {
/* 216 */       this.b.clear();
/* 217 */       this.c.clear();
/* 218 */       if (this.e != null && this.e instanceof b) {
/* 219 */         ((b)this.e).a(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<K> keySet() {
/* 230 */     return this.b.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<V> values() {
/* 238 */     return this.b.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 246 */     return this.b.entrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<K> c() {
/* 254 */     return this.c.iterator();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/f/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */