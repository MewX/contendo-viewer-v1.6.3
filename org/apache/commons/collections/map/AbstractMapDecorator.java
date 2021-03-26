/*     */ package org.apache.commons.collections.map;
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
/*     */ public abstract class AbstractMapDecorator
/*     */   implements Map
/*     */ {
/*     */   protected transient Map map;
/*     */   
/*     */   protected AbstractMapDecorator() {}
/*     */   
/*     */   public AbstractMapDecorator(Map map) {
/*  61 */     if (map == null) {
/*  62 */       throw new IllegalArgumentException("Map must not be null");
/*     */     }
/*  64 */     this.map = map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map getMap() {
/*  73 */     return this.map;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  78 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  82 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  86 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  90 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  94 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  98 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 102 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 106 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 110 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 114 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 118 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 122 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 126 */     if (object == this) {
/* 127 */       return true;
/*     */     }
/* 129 */     return this.map.equals(object);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 133 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 137 */     return this.map.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/AbstractMapDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */