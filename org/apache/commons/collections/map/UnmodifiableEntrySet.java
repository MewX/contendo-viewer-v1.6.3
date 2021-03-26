/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntryDecorator;
/*     */ import org.apache.commons.collections.set.AbstractSetDecorator;
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
/*     */ public final class UnmodifiableEntrySet
/*     */   extends AbstractSetDecorator
/*     */   implements Unmodifiable
/*     */ {
/*     */   public static Set decorate(Set set) {
/*  47 */     if (set instanceof Unmodifiable) {
/*  48 */       return set;
/*     */     }
/*  50 */     return (Set)new UnmodifiableEntrySet(set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableEntrySet(Set set) {
/*  61 */     super(set);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object) {
/*  66 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  74 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/*  86 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  91 */     return (Iterator)new UnmodifiableEntrySetIterator(((AbstractCollectionDecorator)this).collection.iterator());
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  95 */     Object[] array = ((AbstractCollectionDecorator)this).collection.toArray();
/*  96 */     for (int i = 0; i < array.length; i++) {
/*  97 */       array[i] = new UnmodifiableEntry((Map.Entry)array[i]);
/*     */     }
/*  99 */     return array;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 103 */     Object[] result = array;
/* 104 */     if (array.length > 0)
/*     */     {
/*     */       
/* 107 */       result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0);
/*     */     }
/* 109 */     result = ((AbstractCollectionDecorator)this).collection.toArray(result);
/* 110 */     for (int i = 0; i < result.length; i++) {
/* 111 */       result[i] = new UnmodifiableEntry((Map.Entry)result[i]);
/*     */     }
/*     */ 
/*     */     
/* 115 */     if (result.length > array.length) {
/* 116 */       return result;
/*     */     }
/*     */ 
/*     */     
/* 120 */     System.arraycopy(result, 0, array, 0, result.length);
/* 121 */     if (array.length > result.length) {
/* 122 */       array[result.length] = null;
/*     */     }
/* 124 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final class UnmodifiableEntrySetIterator
/*     */     extends AbstractIteratorDecorator
/*     */   {
/*     */     protected UnmodifiableEntrySetIterator(Iterator iterator) {
/* 134 */       super(iterator);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 138 */       Map.Entry entry = this.iterator.next();
/* 139 */       return new UnmodifiableEntrySet.UnmodifiableEntry(entry);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 143 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final class UnmodifiableEntry
/*     */     extends AbstractMapEntryDecorator
/*     */   {
/*     */     protected UnmodifiableEntry(Map.Entry entry) {
/* 154 */       super(entry);
/*     */     }
/*     */     
/*     */     public Object setValue(Object obj) {
/* 158 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/UnmodifiableEntrySet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */