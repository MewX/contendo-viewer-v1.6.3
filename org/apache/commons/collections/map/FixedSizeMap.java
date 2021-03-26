/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
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
/*     */ public class FixedSizeMap
/*     */   extends AbstractMapDecorator
/*     */   implements Serializable, Map, BoundedMap
/*     */ {
/*     */   private static final long serialVersionUID = 7450927208116179316L;
/*     */   
/*     */   public static Map decorate(Map map) {
/*  66 */     return new FixedSizeMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected FixedSizeMap(Map map) {
/*  77 */     super(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  89 */     out.defaultWriteObject();
/*  90 */     out.writeObject(this.map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 102 */     in.defaultReadObject();
/* 103 */     this.map = (Map)in.readObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 108 */     if (!this.map.containsKey(key)) {
/* 109 */       throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */     }
/* 111 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 115 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext();) {
/* 116 */       if (!mapToCopy.containsKey(it.next())) {
/* 117 */         throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */       }
/*     */     } 
/* 120 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 124 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 128 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 132 */     Set set = this.map.entrySet();
/*     */     
/* 134 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 138 */     Set set = this.map.keySet();
/* 139 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 143 */     Collection coll = this.map.values();
/* 144 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 152 */     return size();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/FixedSizeMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */