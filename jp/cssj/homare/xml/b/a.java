/*     */ package jp.cssj.homare.xml.b;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class a<K, V>
/*     */   implements Map<K, V>
/*     */ {
/*     */   protected transient Map<K, V> a;
/*     */   
/*     */   protected a() {}
/*     */   
/*     */   public a(Map<K, V> map) {
/*  65 */     if (map == null) {
/*  66 */       throw new IllegalArgumentException("Map must not be null");
/*     */     }
/*  68 */     this.a = map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<K, V> a() {
/*  77 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  82 */     this.a.clear();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  86 */     return this.a.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  90 */     return this.a.containsValue(value);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/*  94 */     return this.a.entrySet();
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/*  98 */     return this.a.get(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 102 */     return this.a.isEmpty();
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 106 */     return this.a.keySet();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 110 */     return this.a.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 114 */     this.a.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 118 */     return this.a.remove(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 122 */     return this.a.size();
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 126 */     return this.a.values();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 130 */     if (object == this) {
/* 131 */       return true;
/*     */     }
/* 133 */     return this.a.equals(object);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 137 */     return this.a.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 141 */     return this.a.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */