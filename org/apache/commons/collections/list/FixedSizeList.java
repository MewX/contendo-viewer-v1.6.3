/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.BoundedCollection;
/*     */ import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedSizeList
/*     */   extends AbstractSerializableListDecorator
/*     */   implements BoundedCollection
/*     */ {
/*     */   private static final long serialVersionUID = -2218010673611160319L;
/*     */   
/*     */   public static List decorate(List list) {
/*  55 */     return new FixedSizeList(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected FixedSizeList(List list) {
/*  66 */     super(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object) {
/*  71 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/*  75 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  79 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/*  83 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public void clear() {
/*  87 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  91 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  95 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  99 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 103 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 107 */     return (ListIterator)new FixedSizeListIterator(getList().listIterator(0));
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 111 */     return (ListIterator)new FixedSizeListIterator(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 115 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 119 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 123 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 127 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 131 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 135 */     List sub = getList().subList(fromIndex, toIndex);
/* 136 */     return new FixedSizeList(sub);
/*     */   }
/*     */ 
/*     */   
/*     */   static class FixedSizeListIterator
/*     */     extends AbstractListIteratorDecorator
/*     */   {
/*     */     protected FixedSizeListIterator(ListIterator iterator) {
/* 144 */       super(iterator);
/*     */     }
/*     */     public void remove() {
/* 147 */       throw new UnsupportedOperationException("List is fixed size");
/*     */     }
/*     */     public void add(Object object) {
/* 150 */       throw new UnsupportedOperationException("List is fixed size");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 159 */     return size();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/FixedSizeList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */