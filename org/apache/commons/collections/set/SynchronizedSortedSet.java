/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
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
/*     */ public class SynchronizedSortedSet
/*     */   extends SynchronizedCollection
/*     */   implements SortedSet
/*     */ {
/*     */   private static final long serialVersionUID = 2775582861954500111L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set) {
/*  48 */     return new SynchronizedSortedSet(set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedSortedSet(SortedSet set) {
/*  59 */     super(set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedSortedSet(SortedSet set, Object lock) {
/*  70 */     super(set, lock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedSet getSortedSet() {
/*  79 */     return (SortedSet)this.collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/*  84 */     synchronized (this.lock) {
/*  85 */       SortedSet set = getSortedSet().subSet(fromElement, toElement);
/*     */ 
/*     */       
/*  88 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/*  93 */     synchronized (this.lock) {
/*  94 */       SortedSet set = getSortedSet().headSet(toElement);
/*     */ 
/*     */       
/*  97 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 102 */     synchronized (this.lock) {
/* 103 */       SortedSet set = getSortedSet().tailSet(fromElement);
/*     */ 
/*     */       
/* 106 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object first() {
/* 111 */     synchronized (this.lock) {
/* 112 */       return getSortedSet().first();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object last() {
/* 117 */     synchronized (this.lock) {
/* 118 */       return getSortedSet().last();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 123 */     synchronized (this.lock) {
/* 124 */       return getSortedSet().comparator();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/SynchronizedSortedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */