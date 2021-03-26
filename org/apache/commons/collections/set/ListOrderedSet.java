/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListOrderedSet
/*     */   extends AbstractSerializableSetDecorator
/*     */   implements Set
/*     */ {
/*     */   private static final long serialVersionUID = -228664372470420141L;
/*     */   protected final List setOrder;
/*     */   
/*     */   public static ListOrderedSet decorate(Set set, List list) {
/*  70 */     if (set == null) {
/*  71 */       throw new IllegalArgumentException("Set must not be null");
/*     */     }
/*  73 */     if (list == null) {
/*  74 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/*  76 */     if (set.size() > 0 || list.size() > 0) {
/*  77 */       throw new IllegalArgumentException("Set and List must be empty");
/*     */     }
/*  79 */     return new ListOrderedSet(set, list);
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
/*     */   public static ListOrderedSet decorate(Set set) {
/*  91 */     return new ListOrderedSet(set);
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
/*     */   public static ListOrderedSet decorate(List list) {
/* 103 */     if (list == null) {
/* 104 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/* 106 */     Set set = new HashSet(list);
/* 107 */     list.retainAll(set);
/*     */     
/* 109 */     return new ListOrderedSet(set, list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListOrderedSet() {
/* 120 */     super(new HashSet());
/* 121 */     this.setOrder = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ListOrderedSet(Set set) {
/* 131 */     super(set);
/* 132 */     this.setOrder = new ArrayList(set);
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
/*     */   protected ListOrderedSet(Set set, List list) {
/* 145 */     super(set);
/* 146 */     if (list == null) {
/* 147 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/* 149 */     this.setOrder = list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List asList() {
/* 159 */     return UnmodifiableList.decorate(this.setOrder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 164 */     this.collection.clear();
/* 165 */     this.setOrder.clear();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 169 */     return (Iterator)new OrderedSetIterator(this.setOrder.iterator(), this.collection);
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 173 */     if (this.collection.contains(object))
/*     */     {
/* 175 */       return this.collection.add(object);
/*     */     }
/*     */     
/* 178 */     boolean result = this.collection.add(object);
/* 179 */     this.setOrder.add(object);
/* 180 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 185 */     boolean result = false;
/* 186 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 187 */       Object object = it.next();
/* 188 */       result |= add(object);
/*     */     } 
/* 190 */     return result;
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 194 */     boolean result = this.collection.remove(object);
/* 195 */     this.setOrder.remove(object);
/* 196 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 200 */     boolean result = false;
/* 201 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 202 */       Object object = it.next();
/* 203 */       result |= remove(object);
/*     */     } 
/* 205 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 209 */     boolean result = this.collection.retainAll(coll);
/* 210 */     if (!result)
/* 211 */       return false; 
/* 212 */     if (this.collection.size() == 0) {
/* 213 */       this.setOrder.clear();
/*     */     } else {
/* 215 */       for (Iterator it = this.setOrder.iterator(); it.hasNext(); ) {
/* 216 */         Object object = it.next();
/* 217 */         if (!this.collection.contains(object)) {
/* 218 */           it.remove();
/*     */         }
/*     */       } 
/*     */     } 
/* 222 */     return result;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 226 */     return this.setOrder.toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/* 230 */     return this.setOrder.toArray(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(int index) {
/* 235 */     return this.setOrder.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/* 239 */     return this.setOrder.indexOf(object);
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/* 243 */     if (!contains(object)) {
/* 244 */       this.collection.add(object);
/* 245 */       this.setOrder.add(index, object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 250 */     boolean changed = false;
/* 251 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 252 */       Object object = it.next();
/* 253 */       if (!contains(object)) {
/* 254 */         this.collection.add(object);
/* 255 */         this.setOrder.add(index, object);
/* 256 */         index++;
/* 257 */         changed = true;
/*     */       } 
/*     */     } 
/* 260 */     return changed;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 264 */     Object obj = this.setOrder.remove(index);
/* 265 */     remove(obj);
/* 266 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 276 */     return this.setOrder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class OrderedSetIterator
/*     */     extends AbstractIteratorDecorator
/*     */   {
/*     */     protected final Collection set;
/*     */ 
/*     */     
/*     */     protected Object last;
/*     */ 
/*     */     
/*     */     private OrderedSetIterator(Iterator iterator, Collection set) {
/* 291 */       super(iterator);
/* 292 */       this.set = set;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 296 */       this.last = this.iterator.next();
/* 297 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 301 */       this.set.remove(this.last);
/* 302 */       this.iterator.remove();
/* 303 */       this.last = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/ListOrderedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */