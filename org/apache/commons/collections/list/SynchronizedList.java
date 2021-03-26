/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedList
/*     */   extends SynchronizedCollection
/*     */   implements List
/*     */ {
/*     */   private static final long serialVersionUID = -1403835447328619437L;
/*     */   
/*     */   public static List decorate(List list) {
/*  49 */     return new SynchronizedList(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedList(List list) {
/*  60 */     super(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedList(List list, Object lock) {
/*  71 */     super(list, lock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getList() {
/*  80 */     return (List)this.collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Object object) {
/*  85 */     synchronized (this.lock) {
/*  86 */       getList().add(index, object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/*  91 */     synchronized (this.lock) {
/*  92 */       return getList().addAll(index, coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  97 */     synchronized (this.lock) {
/*  98 */       return getList().get(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/* 103 */     synchronized (this.lock) {
/* 104 */       return getList().indexOf(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 109 */     synchronized (this.lock) {
/* 110 */       return getList().lastIndexOf(object);
/*     */     } 
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
/*     */   public ListIterator listIterator() {
/* 125 */     return getList().listIterator();
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
/*     */   public ListIterator listIterator(int index) {
/* 139 */     return getList().listIterator(index);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 143 */     synchronized (this.lock) {
/* 144 */       return getList().remove(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 149 */     synchronized (this.lock) {
/* 150 */       return getList().set(index, object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 155 */     synchronized (this.lock) {
/* 156 */       List list = getList().subList(fromIndex, toIndex);
/*     */ 
/*     */       
/* 159 */       return new SynchronizedList(list, this.lock);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/SynchronizedList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */