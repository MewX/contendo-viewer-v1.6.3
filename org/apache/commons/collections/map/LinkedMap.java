/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableListIterator;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
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
/*     */ public class LinkedMap
/*     */   extends AbstractLinkedMap
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 9077234323521161066L;
/*     */   
/*     */   public LinkedMap() {
/*  68 */     super(16, 0.75F, 12);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinkedMap(int initialCapacity) {
/*  78 */     super(initialCapacity);
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
/*     */   
/*     */   public LinkedMap(int initialCapacity, float loadFactor) {
/*  91 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinkedMap(Map map) {
/* 101 */     super(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 111 */     return super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 118 */     out.defaultWriteObject();
/* 119 */     doWriteObject(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 126 */     in.defaultReadObject();
/* 127 */     doReadObject(in);
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
/*     */   public Object get(int index) {
/* 139 */     return getEntry(index).getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(int index) {
/* 150 */     return getEntry(index).getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(Object key) {
/* 160 */     key = convertKey(key);
/* 161 */     int i = 0;
/* 162 */     for (AbstractLinkedMap.LinkEntry entry = this.header.after; entry != this.header; entry = entry.after, i++) {
/* 163 */       if (isEqualKey(key, entry.key)) {
/* 164 */         return i;
/*     */       }
/*     */     } 
/* 167 */     return -1;
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
/*     */   public Object remove(int index) {
/* 179 */     return remove(get(index));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List asList() {
/* 198 */     return new LinkedMapList(this);
/*     */   }
/*     */ 
/*     */   
/*     */   static class LinkedMapList
/*     */     extends AbstractList
/*     */   {
/*     */     final LinkedMap parent;
/*     */ 
/*     */     
/*     */     LinkedMapList(LinkedMap parent) {
/* 209 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 213 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public Object get(int index) {
/* 217 */       return this.parent.get(index);
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 221 */       return this.parent.containsKey(obj);
/*     */     }
/*     */     
/*     */     public int indexOf(Object obj) {
/* 225 */       return this.parent.indexOf(obj);
/*     */     }
/*     */     
/*     */     public int lastIndexOf(Object obj) {
/* 229 */       return this.parent.indexOf(obj);
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection coll) {
/* 233 */       return this.parent.keySet().containsAll(coll);
/*     */     }
/*     */     
/*     */     public Object remove(int index) {
/* 237 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 241 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection coll) {
/* 245 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection coll) {
/* 249 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 253 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 257 */       return this.parent.keySet().toArray();
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] array) {
/* 261 */       return this.parent.keySet().toArray(array);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 265 */       return UnmodifiableIterator.decorate(this.parent.keySet().iterator());
/*     */     }
/*     */     
/*     */     public ListIterator listIterator() {
/* 269 */       return UnmodifiableListIterator.decorate(super.listIterator());
/*     */     }
/*     */     
/*     */     public ListIterator listIterator(int fromIndex) {
/* 273 */       return UnmodifiableListIterator.decorate(super.listIterator(fromIndex));
/*     */     }
/*     */     
/*     */     public List subList(int fromIndexInclusive, int toIndexExclusive) {
/* 277 */       return UnmodifiableList.decorate(super.subList(fromIndexInclusive, toIndexExclusive));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/LinkedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */