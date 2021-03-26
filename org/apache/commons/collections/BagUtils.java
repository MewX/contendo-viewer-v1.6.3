/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import org.apache.commons.collections.bag.HashBag;
/*     */ import org.apache.commons.collections.bag.PredicatedBag;
/*     */ import org.apache.commons.collections.bag.PredicatedSortedBag;
/*     */ import org.apache.commons.collections.bag.SynchronizedBag;
/*     */ import org.apache.commons.collections.bag.SynchronizedSortedBag;
/*     */ import org.apache.commons.collections.bag.TransformedBag;
/*     */ import org.apache.commons.collections.bag.TransformedSortedBag;
/*     */ import org.apache.commons.collections.bag.TreeBag;
/*     */ import org.apache.commons.collections.bag.TypedBag;
/*     */ import org.apache.commons.collections.bag.TypedSortedBag;
/*     */ import org.apache.commons.collections.bag.UnmodifiableBag;
/*     */ import org.apache.commons.collections.bag.UnmodifiableSortedBag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BagUtils
/*     */ {
/*  48 */   public static final Bag EMPTY_BAG = UnmodifiableBag.decorate((Bag)new HashBag());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final Bag EMPTY_SORTED_BAG = UnmodifiableSortedBag.decorate((SortedBag)new TreeBag());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Bag synchronizedBag(Bag bag) {
/*  90 */     return SynchronizedBag.decorate(bag);
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
/*     */   public static Bag unmodifiableBag(Bag bag) {
/* 103 */     return UnmodifiableBag.decorate(bag);
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
/*     */   public static Bag predicatedBag(Bag bag, Predicate predicate) {
/* 120 */     return PredicatedBag.decorate(bag, predicate);
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
/*     */   public static Bag typedBag(Bag bag, Class type) {
/* 133 */     return TypedBag.decorate(bag, type);
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
/*     */   public static Bag transformedBag(Bag bag, Transformer transformer) {
/* 149 */     return TransformedBag.decorate(bag, transformer);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SortedBag synchronizedSortedBag(SortedBag bag) {
/* 181 */     return SynchronizedSortedBag.decorate(bag);
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
/*     */   public static SortedBag unmodifiableSortedBag(SortedBag bag) {
/* 194 */     return UnmodifiableSortedBag.decorate(bag);
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
/*     */   public static SortedBag predicatedSortedBag(SortedBag bag, Predicate predicate) {
/* 211 */     return PredicatedSortedBag.decorate(bag, predicate);
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
/*     */   public static SortedBag typedSortedBag(SortedBag bag, Class type) {
/* 224 */     return TypedSortedBag.decorate(bag, type);
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
/*     */   public static SortedBag transformedSortedBag(SortedBag bag, Transformer transformer) {
/* 240 */     return TransformedSortedBag.decorate(bag, transformer);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BagUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */