/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableList
/*     */   extends AbstractSerializableListDecorator
/*     */   implements Unmodifiable
/*     */ {
/*     */   private static final long serialVersionUID = 6595182819922443652L;
/*     */   
/*     */   public static List decorate(List list) {
/*  51 */     if (list instanceof Unmodifiable) {
/*  52 */       return list;
/*     */     }
/*  54 */     return new UnmodifiableList(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableList(List list) {
/*  65 */     super(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  70 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  74 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/*  86 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public ListIterator listIterator() {
/*  99 */     return UnmodifiableListIterator.decorate(getList().listIterator());
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 103 */     return UnmodifiableListIterator.decorate(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/* 107 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 123 */     List sub = getList().subList(fromIndex, toIndex);
/* 124 */     return new UnmodifiableList(sub);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/UnmodifiableList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */