/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*     */ import org.apache.commons.collections.set.SynchronizedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedBag
/*     */   extends SynchronizedCollection
/*     */   implements Bag
/*     */ {
/*     */   private static final long serialVersionUID = 8084674570753837109L;
/*     */   
/*     */   public static Bag decorate(Bag bag) {
/*  52 */     return new SynchronizedBag(bag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedBag(Bag bag) {
/*  63 */     super((Collection)bag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedBag(Bag bag, Object lock) {
/*  74 */     super((Collection)bag, lock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Bag getBag() {
/*  83 */     return (Bag)this.collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object, int count) {
/*  88 */     synchronized (this.lock) {
/*  89 */       return getBag().add(object, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int count) {
/*  94 */     synchronized (this.lock) {
/*  95 */       return getBag().remove(object, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 100 */     synchronized (this.lock) {
/* 101 */       Set set = getBag().uniqueSet();
/* 102 */       return (Set)new SynchronizedBagSet(this, set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getCount(Object object) {
/* 107 */     synchronized (this.lock) {
/* 108 */       return getBag().getCount(object);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class SynchronizedBagSet
/*     */     extends SynchronizedSet
/*     */   {
/*     */     private final SynchronizedBag this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     SynchronizedBagSet(SynchronizedBag this$0, Set set, Object lock) {
/* 123 */       super(set, lock);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/SynchronizedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */