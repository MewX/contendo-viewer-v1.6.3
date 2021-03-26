/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.iterators.EmptyIterator;
/*     */ import org.apache.commons.collections.iterators.IteratorChain;
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
/*     */ public class CompositeCollection
/*     */   implements Collection
/*     */ {
/*     */   protected CollectionMutator mutator;
/*  55 */   protected Collection[] all = new Collection[0];
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeCollection() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeCollection(Collection coll) {
/*  64 */     this();
/*  65 */     addComposited(coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeCollection(Collection[] colls) {
/*  75 */     this();
/*  76 */     addComposited(colls);
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
/*     */   public int size() {
/*  88 */     int size = 0;
/*  89 */     for (int i = this.all.length - 1; i >= 0; i--) {
/*  90 */       size += this.all[i].size();
/*     */     }
/*  92 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 103 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 104 */       if (!this.all[i].isEmpty()) {
/* 105 */         return false;
/*     */       }
/*     */     } 
/* 108 */     return true;
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
/*     */   public boolean contains(Object obj) {
/* 120 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 121 */       if (this.all[i].contains(obj)) {
/* 122 */         return true;
/*     */       }
/*     */     } 
/* 125 */     return false;
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
/*     */   public Iterator iterator() {
/* 139 */     if (this.all.length == 0) {
/* 140 */       return EmptyIterator.INSTANCE;
/*     */     }
/* 142 */     IteratorChain chain = new IteratorChain();
/* 143 */     for (int i = 0; i < this.all.length; i++) {
/* 144 */       chain.addIterator(this.all[i].iterator());
/*     */     }
/* 146 */     return (Iterator)chain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 155 */     Object[] result = new Object[size()];
/* 156 */     int i = 0;
/* 157 */     for (Iterator it = iterator(); it.hasNext(); i++) {
/* 158 */       result[i] = it.next();
/*     */     }
/* 160 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 171 */     int size = size();
/* 172 */     Object[] result = null;
/* 173 */     if (array.length >= size) {
/* 174 */       result = array;
/*     */     } else {
/*     */       
/* 177 */       result = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
/*     */     } 
/*     */     
/* 180 */     int offset = 0;
/* 181 */     for (int i = 0; i < this.all.length; i++) {
/* 182 */       for (Iterator it = this.all[i].iterator(); it.hasNext();) {
/* 183 */         result[offset++] = it.next();
/*     */       }
/*     */     } 
/* 186 */     if (result.length > size) {
/* 187 */       result[size] = null;
/*     */     }
/* 189 */     return result;
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
/*     */   public boolean add(Object obj) {
/* 205 */     if (this.mutator == null) {
/* 206 */       throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy");
/*     */     }
/*     */     
/* 209 */     return this.mutator.add(this, this.all, obj);
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
/*     */   public boolean remove(Object obj) {
/* 224 */     if (this.mutator == null) {
/* 225 */       throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy");
/*     */     }
/*     */     
/* 228 */     return this.mutator.remove(this, this.all, obj);
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
/*     */   public boolean containsAll(Collection coll) {
/* 241 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 242 */       if (!contains(it.next())) {
/* 243 */         return false;
/*     */       }
/*     */     } 
/* 246 */     return true;
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
/*     */   public boolean addAll(Collection coll) {
/* 262 */     if (this.mutator == null) {
/* 263 */       throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy");
/*     */     }
/*     */     
/* 266 */     return this.mutator.addAll(this, this.all, coll);
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
/*     */   public boolean removeAll(Collection coll) {
/* 279 */     if (coll.size() == 0) {
/* 280 */       return false;
/*     */     }
/* 282 */     boolean changed = false;
/* 283 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 284 */       changed = (this.all[i].removeAll(coll) || changed);
/*     */     }
/* 286 */     return changed;
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
/*     */   public boolean retainAll(Collection coll) {
/* 300 */     boolean changed = false;
/* 301 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 302 */       changed = (this.all[i].retainAll(coll) || changed);
/*     */     }
/* 304 */     return changed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 315 */     for (int i = 0; i < this.all.length; i++) {
/* 316 */       this.all[i].clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMutator(CollectionMutator mutator) {
/* 327 */     this.mutator = mutator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addComposited(Collection[] comps) {
/* 336 */     ArrayList list = new ArrayList(Arrays.asList((Object[])this.all));
/* 337 */     list.addAll(Arrays.asList(comps));
/* 338 */     this.all = (Collection[])list.toArray((Object[])new Collection[list.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addComposited(Collection c) {
/* 347 */     addComposited(new Collection[] { c });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addComposited(Collection c, Collection d) {
/* 357 */     addComposited(new Collection[] { c, d });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeComposited(Collection coll) {
/* 366 */     ArrayList list = new ArrayList(this.all.length);
/* 367 */     list.addAll(Arrays.asList(this.all));
/* 368 */     list.remove(coll);
/* 369 */     this.all = (Collection[])list.toArray((Object[])new Collection[list.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection toCollection() {
/* 379 */     return new ArrayList(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getCollections() {
/* 388 */     return UnmodifiableList.decorate(Arrays.asList(this.all));
/*     */   }
/*     */   
/*     */   public static interface CollectionMutator {
/*     */     boolean add(CompositeCollection param1CompositeCollection, Collection[] param1ArrayOfCollection, Object param1Object);
/*     */     
/*     */     boolean addAll(CompositeCollection param1CompositeCollection, Collection[] param1ArrayOfCollection, Collection param1Collection);
/*     */     
/*     */     boolean remove(CompositeCollection param1CompositeCollection, Collection[] param1ArrayOfCollection, Object param1Object);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/CompositeCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */