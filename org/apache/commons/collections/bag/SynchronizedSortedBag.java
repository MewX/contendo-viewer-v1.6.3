/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.SortedBag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedSortedBag
/*     */   extends SynchronizedBag
/*     */   implements SortedBag
/*     */ {
/*     */   private static final long serialVersionUID = 722374056718497858L;
/*     */   
/*     */   public static SortedBag decorate(SortedBag bag) {
/*  51 */     return new SynchronizedSortedBag(bag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedSortedBag(SortedBag bag) {
/*  62 */     super((Bag)bag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedSortedBag(Bag bag, Object lock) {
/*  73 */     super(bag, lock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedBag getSortedBag() {
/*  82 */     return (SortedBag)this.collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized Object first() {
/*  87 */     synchronized (this.lock) {
/*  88 */       return getSortedBag().first();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Object last() {
/*  93 */     synchronized (this.lock) {
/*  94 */       return getSortedBag().last();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Comparator comparator() {
/*  99 */     synchronized (this.lock) {
/* 100 */       return getSortedBag().comparator();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/SynchronizedSortedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */